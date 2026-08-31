package hhsixhhwkhxh.mite.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record DeprecatedMarker(boolean value) {
    public static final Codec<DeprecatedMarker> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("value1").forGetter(DeprecatedMarker::value)
            ).apply(instance, DeprecatedMarker::new)
    );

    public static final StreamCodec<ByteBuf, DeprecatedMarker> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, DeprecatedMarker::value,
            DeprecatedMarker::new
    );
}
