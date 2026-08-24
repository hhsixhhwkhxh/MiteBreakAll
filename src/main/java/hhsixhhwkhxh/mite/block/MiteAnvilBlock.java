package hhsixhhwkhxh.mite.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import hhsixhhwkhxh.mite.custom.MeshType;
import hhsixhhwkhxh.mite.menu.MiteAnvilMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;

public class MiteAnvilBlock extends FallingBlock {
    final MapCodec<MiteAnvilBlock> CODEC = simpleCodec(properties1 -> new MiteAnvilBlock(AnvilVariant.IRON,properties1));
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    private static final Map<Direction.Axis, VoxelShape> SHAPES = Shapes.rotateHorizontalAxis(
        Shapes.or(Block.column(12.0, 0.0, 4.0), Block.column(8.0, 10.0, 4.0, 5.0), Block.column(4.0, 8.0, 5.0, 10.0), Block.column(10.0, 16.0, 10.0, 16.0))
    );
    private static final Component CONTAINER_TITLE = Component.translatable("container.repair");
    private static final float FALL_DAMAGE_PER_DISTANCE = 2.0F;
    private static final int FALL_DAMAGE_MAX = 40;
    public static final EnumProperty<AnvilVariant> ANVIL_VARIANT = EnumProperty.create(AnvilVariant.COPPER.getSerializedName(), AnvilVariant.class);
    public static final EnumProperty<AnvilStage> ANVIL_STAGE = EnumProperty.create(AnvilStage.NORMAL.getSerializedName(), AnvilStage.class);


    @Override
    public MapCodec<MiteAnvilBlock> codec() {
        return CODEC;
    }


    public MiteAnvilBlock(AnvilVariant anvilVariant,Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
                .setValue(ANVIL_VARIANT, anvilVariant)
                .setValue(ANVIL_STAGE, AnvilStage.NORMAL)
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getClockWise());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(Stats.INTERACT_WITH_ANVIL);
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider(
            (containerId, playerInventory, player) -> new MiteAnvilMenu(containerId, playerInventory, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE
        );
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING).getAxis());
    }

    @Override
    protected void falling(FallingBlockEntity fallingEntity) {
        fallingEntity.setHurtsEntities(FALL_DAMAGE_PER_DISTANCE, FALL_DAMAGE_MAX);
    }

    @Override
    public void onLand(Level level, BlockPos pos, BlockState state, BlockState replaceableState, FallingBlockEntity fallingBlock) {
        if (!fallingBlock.isSilent()) {
            level.levelEvent(1031, pos, 0);
        }
    }

    @Override
    public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity fallingBlock) {
        if (!fallingBlock.isSilent()) {
            level.levelEvent(1029, pos, 0);
        }
    }

    @Override
    public DamageSource getFallDamageSource(Entity entity) {
        return entity.damageSources().anvil(entity);
    }

    @Nullable
    public static BlockState damage(BlockState state) {
        AnvilVariant anvilVariant = state.getValue(ANVIL_VARIANT);
        if (anvilVariant==AnvilVariant.ADAMANTIUM) {
            return state;
        }

        AnvilStage stage = state.getValue(ANVIL_STAGE);

        AnvilStage newStage;
        if(stage==AnvilStage.NORMAL){
            newStage = AnvilStage.CRACKED;
        }else if(stage==AnvilStage.CRACKED){
            newStage = AnvilStage.DAMAGED;
        }else {
            newStage = null;
        }

        if(newStage!=null){
            return state.setValue(ANVIL_STAGE,newStage);
        }
        return null;
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed blockstate.
     */
    @Override
    protected BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(ANVIL_VARIANT);
        builder.add(ANVIL_STAGE);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    public int getDustColor(BlockState state, BlockGetter reader, BlockPos pos) {
        return state.getMapColor(reader, pos).col;
    }

    public enum AnvilVariant implements StringRepresentable {ANCIENT_METAL("ancient_metal"), ADAMANTIUM("adamantium"),  COPPER("copper"),GOLD("gold"),HARD("hard"),IRON("iron"),MITHRIL("mithril"),SILVER("silver");
        private final String name;
        AnvilVariant(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }
    }

    public enum AnvilStage implements StringRepresentable {NORMAL("normal"),CRACKED("cracked"),DAMAGED("damaged");
        private final String name;
        AnvilStage(String name) {
            this.name = name;
        }

        public static final Codec<AnvilStage> CODEC = StringRepresentable.fromEnum(AnvilStage::values);

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }

    }
}


