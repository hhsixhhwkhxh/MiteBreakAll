package hhsixhhwkhxh.mite.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import hhsixhhwkhxh.mite.entity.Mallard;
import hhsixhhwkhxh.mite.entity.model.MallardModel;
import hhsixhhwkhxh.mite.entity.model.ModModelLayers;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

//@OnlyIn(Dist.CLIENT)
public class MallardRenderer extends MobRenderer<Mallard, MallardRenderState, MallardModel> {

    public MallardRenderer(EntityRendererProvider.Context context) {
        super(context, new MallardModel(context.bakeLayer(ModModelLayers.MALLARD)), 0.3F);
    }



    public void render(@NotNull MallardRenderState renderState, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    public @NotNull ResourceLocation getTextureLocation(MallardRenderState renderState) {
        return renderState.texture;
    }

    public @NotNull MallardRenderState createRenderState() {
        return new MallardRenderState();
    }

    public void extractRenderState(@NotNull Mallard entity, @NotNull MallardRenderState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.flap = Mth.lerp(partialTick, entity.oFlap, entity.flap);
        reusedState.flapSpeed = Mth.lerp(partialTick, entity.oFlapSpeed, entity.flapSpeed);
    }
}
