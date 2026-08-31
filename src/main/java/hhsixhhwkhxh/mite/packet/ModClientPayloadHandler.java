package hhsixhhwkhxh.mite.packet;

import hhsixhhwkhxh.mite.Utils;
import hhsixhhwkhxh.mite.accessor.PlayerMixinAccessor;
import hhsixhhwkhxh.mite.custom.PlayerWaterData;
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
