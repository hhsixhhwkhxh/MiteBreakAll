package hhsixhhwkhxh.mite.entity.renderer;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

//@OnlyIn(Dist.CLIENT)
public class ElephantRenderState extends LivingEntityRenderState {
    private static final ResourceLocation DEFAULT_TEXTURE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID,"textures/entity/elephant_asian.png");
    public boolean isAngry;
    public ResourceLocation texture = DEFAULT_TEXTURE;

    public final AnimationState earFlapAnimationState = new AnimationState();
    public final AnimationState tailFlickAnimationState = new AnimationState();
    public final AnimationState trunkCurlAnimationState = new AnimationState();
}
