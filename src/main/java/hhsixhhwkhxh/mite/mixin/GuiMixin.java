package hhsixhhwkhxh.mite.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.accessor.PlayerMixinAccessor;
import hhsixhhwkhxh.mite.custom.ModFoodData;
import hhsixhhwkhxh.mite.custom.PlayerWaterData;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
    @Mutable
    @Final
    @Shadow
    private final RandomSource random;

    @Shadow
    private int tickCount;
    
    @Unique
    private static final ResourceLocation WATER_HALF_POISON_SPRITE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"hud/water_half_poison");
    @Unique
    private static final ResourceLocation WATER_FULL_POISON_SPRITE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"hud/water_full_poison");
    @Unique
    private static final ResourceLocation WATER_EMPTY_SPRITE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"hud/water_empty");
    @Unique
    private static final ResourceLocation WATER_HALF_SPRITE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"hud/water_half");
    @Unique
    private static final ResourceLocation WATER_FULL_SPRITE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"hud/water_full");
    
    public GuiMixin(RandomSource random) {
        this.random = random;
    }

    @Inject(method = "renderFood",at = @At("TAIL"))
    private void renderWater(GuiGraphics guiGraphics, Player player, int baseY, int baseX, CallbackInfo ci) {
        PlayerWaterData waterData = ((PlayerMixinAccessor) player).getWaterData();
        int currentWaterLevel = waterData.getWaterLevel();
        baseY-=10;
        //MiteBreakAll.LOGGER.debug("baseY"+baseY);

        for (int waterIconIndex = 0; waterIconIndex < waterData.getMaxWaterLevel()/2; waterIconIndex++) {
            int iconY = baseY;
            ResourceLocation emptyFoodSprite;
            ResourceLocation halfFoodSprite;
            ResourceLocation fullFoodSprite;

            if (player.hasEffect(MobEffects.POISON)) {
                emptyFoodSprite = WATER_EMPTY_SPRITE;
                halfFoodSprite = WATER_HALF_POISON_SPRITE;
                fullFoodSprite = WATER_FULL_POISON_SPRITE;
            } else {
                emptyFoodSprite = WATER_EMPTY_SPRITE;
                halfFoodSprite = WATER_HALF_SPRITE;
                fullFoodSprite = WATER_FULL_SPRITE;
            }

            if (waterData.getWaterLevel() <= 1.0F && this.tickCount % (currentWaterLevel * 3 + 1) == 0) {
                iconY = baseY + (this.random.nextInt(3) - 1);
            }

            int iconX = baseX - waterIconIndex * 8 - 9;
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, emptyFoodSprite, iconX, iconY, 9, 9);
            if (waterIconIndex * 2 + 1 < currentWaterLevel) {
                guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, fullFoodSprite, iconX, iconY, 9, 9);
            }

            if (waterIconIndex * 2 + 1 == currentWaterLevel) {
                guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, halfFoodSprite, iconX, iconY, 9, 9);
            }
        }
    }

    @ModifyConstant(method = "renderFood",constant = @Constant(intValue = 10))
    private int renderFood(int constant, @Local FoodData foodData) {
        ModFoodData modFoodData = (ModFoodData) foodData;
        return modFoodData.getMaxFoodLevel()/2;
    }
}
