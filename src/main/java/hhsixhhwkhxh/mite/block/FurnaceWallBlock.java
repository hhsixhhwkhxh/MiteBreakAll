package hhsixhhwkhxh.mite.block;

import com.mojang.serialization.MapCodec;
import hhsixhhwkhxh.mite.blockentity.FurnaceWallBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiConsumer;

public class FurnaceWallBlock extends BaseEntityBlock {

    public static final EnumProperty<Type> MATERIAL_TYPE = EnumProperty.create("material_type", Type.class);
    private final Type wallType;


    public FurnaceWallBlock(Properties properties, Type wallType) {
        super(properties);
        this.wallType = wallType;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FurnaceWallBlockEntity(pos,state,wallType);
    }




    @Override
    protected void onExplosionHit(BlockState state, ServerLevel level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {

        getBlockEntity(level,pos).ifPresent(blockEntity->{
            blockEntity.onDestroy(level);
        });
        super.onExplosionHit(state, level, pos, explosion, dropConsumer);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        getBlockEntity(level,pos).ifPresent(blockEntity->{
            blockEntity.onDestroy(level);
        });
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    public static Optional<FurnaceWallBlockEntity> getBlockEntity(LevelAccessor level, BlockPos pos){
        return Optional.ofNullable((FurnaceWallBlockEntity)level.getBlockEntity(pos));
    }

    public enum Type implements StringRepresentable {
        COBBLESTONE(Blocks.COBBLESTONE),
        OBSIDIAN(Blocks.OBSIDIAN),
        NETHERRACK(Blocks.NETHERRACK);
        final Block block;
        Type(Block block){
            this.block = block;
        }
        @Override
        public @NotNull String getSerializedName() {
            return block.getName().getString();
        }

        public Block getBlock(){
            return block;
        }
    }


}
