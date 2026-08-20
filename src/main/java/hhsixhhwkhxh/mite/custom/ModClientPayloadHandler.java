package hhsixhhwkhxh.mite.custom;

import hhsixhhwkhxh.mite.Utils;
import hhsixhhwkhxh.mite.accessor.PlayerMixinAccessor;
import hhsixhhwkhxh.mite.packet.ClientboundSetVitalStatMaxValuePacket;
import hhsixhhwkhxh.mite.packet.ClientboundSetWaterLevelPacket;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ModClientPayloadHandler {

    public static void handleSetWaterLevelPacket(ClientboundSetWaterLevelPacket clientboundSetWaterLevelPacket, IPayloadContext iPayloadContext) {
        PlayerWaterData waterData = ((PlayerMixinAccessor) iPayloadContext.player()).getWaterData();
        waterData.setWaterLevel(clientboundSetWaterLevelPacket.waterLevel());
    }

    public static void handleSetVitalStatMaxValuePacket(ClientboundSetVitalStatMaxValuePacket packet, IPayloadContext context) {

        Utils.setVitalStatMaxValue(context.player(),packet.vitalStatMaxValue());
    }
}
