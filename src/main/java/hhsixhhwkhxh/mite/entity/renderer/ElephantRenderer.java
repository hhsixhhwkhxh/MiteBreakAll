package hhsixhhwkhxh.mite.entity.renderer;

import hhsixhhwkhxh.mite.entity.Elephant;
import hhsixhhwkhxh.mite.entity.model.ElephantModel;
import hhsixhhwkhxh.mite.entity.model.ModModelLayers;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;

//@OnlyIn(Dist.CLIENT)
public class ElephantRenderer extends AgeableMobRenderer<Elephant, ElephantRenderState, ElephantModel> {
    public ElephantRenderer(EntityRendererProvider.Context context) {
        super(context, new ElephantModel(context.bakeLayer(ModModelLayers.ELEPHANT)), new ElephantModel(context.bakeLayer(ModModelLayers.ELEPHANT_BABY)), 0.5F);
        //this.addLayer(new WolfArmorLayer(this, context.getModelSet(), context.getEquipmentRenderer()));
        //this.addLayer(new WolfCollarLayer(this));
    }

    protected int getModelTint(ElephantRenderState renderState) {
        float f = renderState.wetShade;
        return f == 1.0F ? -1 : ARGB.colorFromFloat(1.0F, f, f, f);
    }

    public ResourceLocation getTextureLocation(ElephantRenderState renderState) {
        return renderState.texture;
    }

    public ElephantRenderState createRenderState() {
        return new ElephantRenderState();
    }

    public void extractRenderState(Elephant elephant, ElephantRenderState renderState, float p_362105_) {
        super.extractRenderState(elephant, renderState, p_362105_);
        renderState.isAngry = elephant.isAngry();
        renderState.tailAngle = elephant.getTailAngle();
        renderState.headRollAngle = elephant.getHeadRollAngle(p_362105_);
        renderState.shakeAnim = elephant.getShakeAnim(p_362105_);
        //renderState.texture = elephant.getTexture();
        renderState.wetShade = elephant.getWetShade(p_362105_);
    }
}
