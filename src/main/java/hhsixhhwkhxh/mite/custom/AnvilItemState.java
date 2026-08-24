package hhsixhhwkhxh.mite.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import hhsixhhwkhxh.mite.block.MiteAnvilBlock;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;

import javax.annotation.Nullable;


public record AnvilItemState() implements SelectItemModelProperty<MiteAnvilBlock.AnvilStage> {
    public static final Codec<MiteAnvilBlock.AnvilStage> VALUE_CODEC = MiteAnvilBlock.AnvilStage.CODEC;
    public static final SelectItemModelProperty.Type<AnvilItemState, MiteAnvilBlock.AnvilStage> TYPE = SelectItemModelProperty.Type.create(
            MapCodec.unit(new AnvilItemState()), VALUE_CODEC
    );

    public MiteAnvilBlock.AnvilStage get(
            ItemStack p_387321_, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int p_387536_, ItemDisplayContext p_387489_
    ) {
        BlockItemStateProperties properties = p_387321_.get(DataComponents.BLOCK_STATE);
        if (properties == null || properties.isEmpty()) {
            return MiteAnvilBlock.AnvilStage.NORMAL;
        }
        return properties.get(MiteAnvilBlock.ANVIL_STAGE);
    }

    @Override
    public SelectItemModelProperty.Type<AnvilItemState, MiteAnvilBlock.AnvilStage> type() {
        return TYPE;
    }

    @Override
    public Codec<MiteAnvilBlock.AnvilStage> valueCodec() {
        return VALUE_CODEC;
    }
}
