package hhsixhhwkhxh.mite.entity.renderer;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

//@OnlyIn(Dist.CLIENT)
public class ElephantRenderState extends LivingEntityRenderState {
    private static final ResourceLocation DEFAULT_TEXTURE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID,"textures/entity/elephant_asian.png");
    public boolean isAngry;
    public float tailAngle = (float) (Math.PI / 5);
    public float headRollAngle;
    public float shakeAnim;
    public float wetShade = 1.0F;
    public ResourceLocation texture = DEFAULT_TEXTURE;

    public float getBodyRollAngle(float angle) {
        float f = (this.shakeAnim + angle) / 1.8F;
        if (f < 0.0F) {
            f = 0.0F;
        } else if (f > 1.0F) {
            f = 1.0F;
        }

        return Mth.sin(f * (float) Math.PI) * Mth.sin(f * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
    }
}
