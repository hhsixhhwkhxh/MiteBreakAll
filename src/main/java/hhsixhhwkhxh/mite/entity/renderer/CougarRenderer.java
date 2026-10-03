package hhsixhhwkhxh.mite.entity.renderer;

import hhsixhhwkhxh.mite.entity.Cougar;
import hhsixhhwkhxh.mite.entity.model.CougarModel;
import hhsixhhwkhxh.mite.entity.model.ModModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

//@OnlyIn(Dist.CLIENT)
public class CougarRenderer extends AgeableMobRenderer<Cougar, CougarRenderState, CougarModel> {
    public CougarRenderer(EntityRendererProvider.Context context) {
        super(context, new CougarModel(context.bakeLayer(ModModelLayers.COUGAR)), new CougarModel(context.bakeLayer(ModModelLayers.COUGAR_BABY)), 0.5F);
    }

    protected int getModelTint(@NotNull CougarRenderState renderState) {
        return -1;
    }

    public @NotNull ResourceLocation getTextureLocation(CougarRenderState renderState) {
        return CougarRenderState.DEFAULT_TEXTURE;
    }

    public @NotNull CougarRenderState createRenderState() {
        return new CougarRenderState();
    }

    public void extractRenderState(@NotNull Cougar elephant, @NotNull CougarRenderState renderState, float partialTick) {
        super.extractRenderState(elephant, renderState, partialTick);
        renderState.isAngry = elephant.isAngry();

//        renderState.earFlapAnimationState.copyFrom(elephant.earFlapAnimationState);
//        renderState.tailFlickAnimationState.copyFrom(elephant.tailFlickAnimationState);
//        renderState.trunkCurlAnimationState.copyFrom(elephant.trunkCurlAnimationState);
    }

//    @Override
//    protected float getShadowRadius(CougarRenderState renderState) {
//        return super.getShadowRadius(renderState) * 2.4F;
//    }
}
