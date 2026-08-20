package hhsixhhwkhxh.mite.mixin;

import com.mojang.authlib.GameProfile;
import hhsixhhwkhxh.mite.accessor.PlayerMixinAccessor;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {

    public LocalPlayerMixin(ClientLevel clientLevel, GameProfile gameProfile) {
        super(clientLevel, gameProfile);
    }

    @Inject(method = "hasEnoughFoodToSprint",at = @At("HEAD"), cancellable = true)
    private void hasEnoughFoodToSprint(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(this.isPassenger() ||( this.getFoodData().getFoodLevel() > 0 && ((PlayerMixinAccessor)this).getWaterData().getWaterLevel() > 0 )|| this.mayFly());
    }
}
