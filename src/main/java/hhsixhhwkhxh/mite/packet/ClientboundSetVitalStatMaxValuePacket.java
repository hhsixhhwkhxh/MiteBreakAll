package hhsixhhwkhxh.mite.packet;

import hhsixhhwkhxh.mite.MiteBreakAll;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ClientboundSetVitalStatMaxValuePacket(int vitalStatMaxValue) implements CustomPacketPayload {

    public static final Type<ClientboundSetVitalStatMaxValuePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID, "vital_stat_max_data"));

    public static final StreamCodec<ByteBuf, ClientboundSetVitalStatMaxValuePacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            ClientboundSetVitalStatMaxValuePacket::vitalStatMaxValue,
            ClientboundSetVitalStatMaxValuePacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
