package hhsixhhwkhxh.mite.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ReachBonus(float value) {
    public static final Codec<ReachBonus> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.FLOAT.fieldOf("value1").forGetter(ReachBonus::value)
            ).apply(instance, ReachBonus::new)
    );

    public static final StreamCodec<ByteBuf, ReachBonus> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, ReachBonus::value,
            ReachBonus::new
    );
}
