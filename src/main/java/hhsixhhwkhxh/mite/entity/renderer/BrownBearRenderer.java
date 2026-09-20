package hhsixhhwkhxh.mite.entity.renderer;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.entity.BrownBear;
import hhsixhhwkhxh.mite.entity.model.BrownBearModel;
import net.minecraft.client.model.PolarBearModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.PolarBearRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.PolarBear;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

//@OnlyIn(Dist.CLIENT)
public class BrownBearRenderer extends AgeableMobRenderer<BrownBear, BearRenderState, BrownBearModel> {
    private static final ResourceLocation BEAR_LOCATION = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID,"textures/entity/bear/brown_bear.png");

    public BrownBearRenderer(EntityRendererProvider.Context context) {
        super(
            context,
            new BrownBearModel(context.bakeLayer(ModelLayers.POLAR_BEAR)),
            new BrownBearModel(context.bakeLayer(ModelLayers.POLAR_BEAR_BABY)),
            0.9F
        );
    }


    public BearRenderState createRenderState() {
        return new BearRenderState();
    }

    public void extractRenderState(BrownBear brownBear, BearRenderState bearRenderState, float p_363931_) {
        super.extractRenderState( brownBear, bearRenderState, p_363931_);
        bearRenderState.standScale = brownBear.getStandingAnimationScale(p_363931_);
    }

    @Override
    public ResourceLocation getTextureLocation(BearRenderState renderState) {
        return BEAR_LOCATION;
    }
}
