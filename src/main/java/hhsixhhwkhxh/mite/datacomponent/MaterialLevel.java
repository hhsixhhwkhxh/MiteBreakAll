package hhsixhhwkhxh.mite.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record MaterialLevel(int value) {
    public static final Codec<MaterialLevel> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("value1").forGetter(MaterialLevel::value)
            ).apply(instance, MaterialLevel::new)
    );

    public static final StreamCodec<ByteBuf, MaterialLevel> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, MaterialLevel::value,
            MaterialLevel::new
    );
}
