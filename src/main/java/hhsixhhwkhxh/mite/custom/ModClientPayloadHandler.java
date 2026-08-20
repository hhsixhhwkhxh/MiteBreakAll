package hhsixhhwkhxh.mite.custom;

import hhsixhhwkhxh.mite.accessor.PlayerMixinAccessor;
import hhsixhhwkhxh.mite.packet.ClientboundSetWaterLevelPacket;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ModClientPayloadHandler {
    public static void handleDataOnMain(ClientboundSetWaterLevelPacket clientboundSetWaterLevelPacket, IPayloadContext iPayloadContext) {
        PlayerWaterData waterData = ((PlayerMixinAccessor)(Object)iPayloadContext.player()).getWaterData();
        waterData.setWaterLevel(clientboundSetWaterLevelPacket.waterLevel());
    }
}
