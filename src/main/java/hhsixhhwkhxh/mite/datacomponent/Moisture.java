package hhsixhhwkhxh.mite.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record Moisture(int value) {
    public static final Codec<Moisture> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("value1").forGetter(Moisture::value)
            ).apply(instance, Moisture::new)
    );

    public static final StreamCodec<ByteBuf, Moisture> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, Moisture::value,
            Moisture::new
    );
}
