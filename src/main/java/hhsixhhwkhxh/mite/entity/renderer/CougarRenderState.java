package hhsixhhwkhxh.mite.entity.renderer;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;

//@OnlyIn(Dist.CLIENT)
public class CougarRenderState extends LivingEntityRenderState {
    public static final ResourceLocation DEFAULT_TEXTURE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID,"textures/entity/cougar.png");
    public boolean isAngry;

//    public final AnimationState earFlapAnimationState = new AnimationState();
//    public final AnimationState tailFlickAnimationState = new AnimationState();
//    public final AnimationState trunkCurlAnimationState = new AnimationState();
}
