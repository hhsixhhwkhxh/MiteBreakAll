package hhsixhhwkhxh.mite.entity.renderer;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

//@OnlyIn(Dist.CLIENT)
public class MallardRenderState extends LivingEntityRenderState {
    private static final ResourceLocation DEFAULT_TEXTURE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID,"textures/entity/mallard.png");

    public float flap;
    public float flapSpeed;

    public ResourceLocation texture = DEFAULT_TEXTURE;
}
