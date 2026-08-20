package hhsixhhwkhxh.mite.packet;

import hhsixhhwkhxh.mite.MiteBreakAll;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ClientboundSetWaterLevelPacket (int waterLevel) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ClientboundSetWaterLevelPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID, "water_level_data"));

    public static final StreamCodec<ByteBuf, ClientboundSetWaterLevelPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            ClientboundSetWaterLevelPacket::waterLevel,
            ClientboundSetWaterLevelPacket::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
