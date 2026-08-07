package hhsixhhwkhxh.mite.mixin;

import com.llamalad7.mixinextras.sugar.Local;
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

import java.util.SortedMap;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin  extends SimplePreparableReloadListener<RecipeMap> implements RecipeAccess {
    @Inject(method = "prepare(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Lnet/minecraft/world/item/crafting/RecipeMap;",
            at = @At(value = "INVOKE", target = "Ljava/util/ArrayList;<init>(I)V"),locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    protected void prepare(ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfoReturnable<RecipeMap> cir,@Local SortedMap<ResourceLocation, Recipe<?>> sortedmap) {

        sortedmap.remove(ResourceLocation.withDefaultNamespace("golden_apple"));
    }

}
