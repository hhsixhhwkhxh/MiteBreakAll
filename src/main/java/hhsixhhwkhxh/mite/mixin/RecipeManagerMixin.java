package hhsixhhwkhxh.mite.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.SortedMap;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin extends SimplePreparableReloadListener<RecipeMap> implements RecipeAccess {

    private static List<String> banList = List.of(
            "golden_apple","crafting_table",
            "diamond_helmet","diamond_chestplate","diamond_leggings","diamond_boots",
            "diamond_sword","diamond_shovel","diamond_pickaxe","diamond_axe","diamond_hoe"
    );

    @Inject(method = "prepare(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Lnet/minecraft/world/item/crafting/RecipeMap;",
            at = @At(value = "INVOKE", target = "Ljava/util/ArrayList;<init>(I)V"),locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    protected void prepare(ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfoReturnable<RecipeMap> cir,@Local SortedMap<ResourceLocation, Recipe<?>> sortedmap) {

        //sortedmap.remove(ResourceLocation.withDefaultNamespace("golden_apple"));
        banList.forEach(str -> {
            if(sortedmap.remove(ResourceLocation.withDefaultNamespace(str))==null){
                MiteBreakAll.LOGGER.error("Failed to remove recipe: {}", str);
            }
        });
    }

}
