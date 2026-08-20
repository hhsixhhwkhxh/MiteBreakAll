package hhsixhhwkhxh.mite.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import hhsixhhwkhxh.mite.accessor.PlayerMixinAccessor;
import hhsixhhwkhxh.mite.custom.PlayerWaterData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements PlayerMixinAccessor {
    @Unique
    private final PlayerWaterData waterData = new PlayerWaterData();

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "readAdditionalSaveData",at = @At("TAIL"))
    protected void readAdditionalSaveData(ValueInput input, CallbackInfo ci) {
        this.waterData.readAdditionalSaveData(input);
    }

    @Inject(method = "addAdditionalSaveData",at = @At("TAIL"))
    protected void addAdditionalSaveData(ValueOutput output, CallbackInfo ci) {
        this.waterData.addAdditionalSaveData(output);
    }

    @Inject(method = "tick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;tick(Lnet/minecraft/server/level/ServerPlayer;)V",shift = At.Shift.AFTER))
    public void tick(CallbackInfo ci, @Local ServerPlayer serverplayer) {
        this.waterData.tick(serverplayer);
    }

    @Override
    public PlayerWaterData getWaterData() {
        return waterData;
    }
}
