package hhsixhhwkhxh.mite.entity.renderer;

import hhsixhhwkhxh.mite.entity.Elephant;
import hhsixhhwkhxh.mite.entity.model.ElephantModel;
import hhsixhhwkhxh.mite.entity.model.ModModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

//@OnlyIn(Dist.CLIENT)
public class ElephantRenderer extends AgeableMobRenderer<Elephant, ElephantRenderState, ElephantModel> {
    public ElephantRenderer(EntityRendererProvider.Context context) {
        super(context, new ElephantModel(context.bakeLayer(ModModelLayers.ELEPHANT)), new ElephantModel(context.bakeLayer(ModModelLayers.ELEPHANT_BABY)), 0.5F);
    }

    protected int getModelTint(@NotNull ElephantRenderState renderState) {
        return -1;
    }

    public @NotNull ResourceLocation getTextureLocation(ElephantRenderState renderState) {
        return renderState.texture;
    }

    public @NotNull ElephantRenderState createRenderState() {
        return new ElephantRenderState();
    }

    public void extractRenderState(@NotNull Elephant elephant, @NotNull ElephantRenderState renderState, float partialTick) {
        super.extractRenderState(elephant, renderState, partialTick);
        renderState.isAngry = elephant.isAngry();

        renderState.earFlapAnimationState.copyFrom(elephant.earFlapAnimationState);
        renderState.tailFlickAnimationState.copyFrom(elephant.tailFlickAnimationState);
        renderState.trunkCurlAnimationState.copyFrom(elephant.trunkCurlAnimationState);
    }

    @Override
    protected float getShadowRadius(ElephantRenderState renderState) {
        return super.getShadowRadius(renderState) * 2.4F;
    }
}
