package hhsixhhwkhxh.mite.block;

import com.mojang.serialization.MapCodec;
import hhsixhhwkhxh.mite.blockentity.FurnaceCoreBlockEntity;
import hhsixhhwkhxh.mite.blockentity.MiteAnvilBlockEntity;
import hhsixhhwkhxh.mite.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiConsumer;


public class FurnaceCore extends BaseEntityBlock {

    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty SHADOW = BooleanProperty.create("shadow");

    public final Block materialBlock = Blocks.COBBLESTONE;


    public FurnaceCore(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVATED, false)
                .setValue(LIT, false).setValue(SHADOW,false)
        );
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if(level instanceof ServerLevel){
            //防止WallBlock被破坏 反激活熔炉核心 触发此onPlace导致的再次trySpawnLargeFurnace的bug
            if(!oldState.is(Blocks.AIR)){
                return;
            }
            getBlockEntity(level,pos).ifPresent(blockEntity->{
                blockEntity.trySpawnLargeFurnace(level);
            });
        }


    }

    public static Optional<FurnaceCoreBlockEntity> getBlockEntity(LevelAccessor level, BlockPos pos){
        return Optional.ofNullable((FurnaceCoreBlockEntity)level.getBlockEntity(pos));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            if(!level.getBlockState(pos).getValue(ACTIVATED)){
                return InteractionResult.PASS;
            }
            getBlockEntity(level,pos).ifPresent(blockEntity->{
                player.openMenu(blockEntity);
                player.awardStat(Stats.INTERACT_WITH_FURNACE);
            });
        }

        return InteractionResult.SUCCESS;
    }



    @Override
    protected void onExplosionHit(BlockState state, ServerLevel level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {

        getBlockEntity(level,pos).ifPresent(blockEntity->{
            blockEntity.deactivationCore(level);
            super.destroy(level, pos, state);
        });
        super.onExplosionHit(state, level, pos, explosion, dropConsumer);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        getBlockEntity(level,pos).ifPresent(blockEntity->{
            blockEntity.deactivationCore(level);
            super.destroy(level, pos, state);
        });
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }



    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
        builder.add(LIT);
        builder.add(FACING);
        builder.add(SHADOW);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return FurnaceCoreBlockEntity.createFurnaceTicker(level,blockEntityType, ModBlockEntities.FURNACE_CORE.get());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FurnaceCoreBlockEntity(pos,state,materialBlock,this);
    }
}
