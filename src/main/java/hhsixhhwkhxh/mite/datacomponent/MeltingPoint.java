package hhsixhhwkhxh.mite.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record MeltingPoint(int meltingPoint) {
    public static final Codec<MeltingPoint> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("melting_point").forGetter(MeltingPoint::meltingPoint)
            ).apply(instance, MeltingPoint::new)
    );

    public static final StreamCodec<ByteBuf, MeltingPoint> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, MeltingPoint::meltingPoint,
            MeltingPoint::new
    );


    public Component getMeltingPointToolTip(){
        return Component.translatable("tooltip.mite.melting_point",meltingPoint);
    }
}
