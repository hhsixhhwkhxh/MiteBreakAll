package hhsixhhwkhxh.mite.block;

import hhsixhhwkhxh.mite.Utils;
import hhsixhhwkhxh.mite.blockentity.FurnaceCoreBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class FurnaceWallBlock extends Block {

    public static final EnumProperty<Type> MATERIAL_TYPE = EnumProperty.create("material_type", Type.class);
    public static final List<IntegerProperty> REAL_FURNACE_POS = Utils.createBlockPosProperty("real_furnace");
    private final Type wallType;


    public FurnaceWallBlock(Properties properties, Type wallType) {
        super(properties);
        this.wallType = wallType;
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(MATERIAL_TYPE);
        REAL_FURNACE_POS.forEach(builder::add);
    }

    public static void setRealFurnacePos(LevelAccessor level, BlockPos pos, BlockPos realFurnacePos) {

        BlockPos offset = Utils.getRelativePos(pos, realFurnacePos);
        BlockState newState = Utils.setPropertyBlockPos(level.getBlockState(pos),REAL_FURNACE_POS,offset);
        level.setBlock(pos,newState,UPDATE_ALL);
    }

    public void onDestroy(LevelAccessor level, BlockPos pos){

        BlockEntity blockEntity = level.getBlockEntity(Utils.getAbsolutePosFromBlockState(pos,level.getBlockState(pos),REAL_FURNACE_POS));
        if(blockEntity instanceof FurnaceCoreBlockEntity furnaceCoreBlockEntity){
            furnaceCoreBlockEntity.deactivationCore(level);
        }
    }

    @Override
    protected void onExplosionHit(BlockState state, ServerLevel level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
        onDestroy(level,pos);
        super.onExplosionHit(state, level, pos, explosion, dropConsumer);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        onDestroy(level,pos);
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    public static Optional<BlockEntity> getBlockEntity(LevelAccessor level, BlockPos pos){
        return Optional.ofNullable(level.getBlockEntity(pos));
    }

    public enum Type implements StringRepresentable {
        COBBLESTONE(Blocks.COBBLESTONE,"cobblestone"),
        OBSIDIAN(Blocks.OBSIDIAN,"obsidian"),
        NETHERRACK(Blocks.NETHERRACK,"netherrack");
        final Block block;
        final String name;
        Type(Block block,String name){
            this.block = block;
            this.name = name;
        }
        @Override
        public @NotNull String getSerializedName() {
            return name;
        }

        public Block getBlock(){
            return block;
        }
    }


}
