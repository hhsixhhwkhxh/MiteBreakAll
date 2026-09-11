package hhsixhhwkhxh.mite.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public record MeltingCast(int meltingPoint) {
    public static final Codec<MeltingCast> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("melting_point").forGetter(MeltingCast::meltingPoint)
            ).apply(instance, MeltingCast::new)
    );

    public static final StreamCodec<ByteBuf, MeltingCast> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, MeltingCast::meltingPoint,
            MeltingCast::new
    );


    public Component getMeltingPointToolTip(){
        return Component.translatable("tooltip.mite.melting_point",meltingPoint);
    }
}
