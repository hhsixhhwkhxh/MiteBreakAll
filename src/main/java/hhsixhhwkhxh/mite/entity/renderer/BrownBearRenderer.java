package hhsixhhwkhxh.mite.entity.renderer;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.entity.BrownBear;
import hhsixhhwkhxh.mite.entity.model.BrownBearModel;
import hhsixhhwkhxh.mite.entity.model.ModModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

//@OnlyIn(Dist.CLIENT)
public class BrownBearRenderer extends AgeableMobRenderer<BrownBear, BearRenderState, BrownBearModel> {
    private static final ResourceLocation BEAR_LOCATION = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID,"textures/entity/bear/brown_bear.png");

    public BrownBearRenderer(EntityRendererProvider.Context context) {
        super(
            context,
            new BrownBearModel(context.bakeLayer(ModModelLayers.BROWN_BEAR)),
            new BrownBearModel(context.bakeLayer(ModModelLayers.BROWN_BEAR_BABY)),
            0.9F
        );
    }


    public @NotNull BearRenderState createRenderState() {
        return new BearRenderState();
    }

    public void extractRenderState(@NotNull BrownBear brownBear, @NotNull BearRenderState bearRenderState, float p_363931_) {
        super.extractRenderState(brownBear, bearRenderState, p_363931_);
        bearRenderState.standScale = brownBear.getStandingAnimationScale(p_363931_);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BearRenderState renderState) {
        return BEAR_LOCATION;
    }
}
