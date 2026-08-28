package hhsixhhwkhxh.mite.block;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import hhsixhhwkhxh.mite.blockentity.MiteAnvilBlockEntity;
import hhsixhhwkhxh.mite.custom.MaterialLevelType;
import hhsixhhwkhxh.mite.datacomponent.MaterialLevel;
import hhsixhhwkhxh.mite.item.ModItems;
import hhsixhhwkhxh.mite.menu.MiteAnvilMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiteAnvilBlock extends FallingBlock implements EntityBlock{
    final MapCodec<MiteAnvilBlock> CODEC = simpleCodec(properties1 -> new MiteAnvilBlock(AnvilVariant.IRON,properties1));
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    private static final Map<Direction.Axis, VoxelShape> SHAPES = Shapes.rotateHorizontalAxis(
        Shapes.or(Block.column(12.0, 0.0, 4.0), Block.column(8.0, 10.0, 4.0, 5.0), Block.column(4.0, 8.0, 5.0, 10.0), Block.column(10.0, 16.0, 10.0, 16.0))
    );
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
            //player.openMenu(state.getMenuProvider(level, pos));
            //player.awardStat(Stats.INTERACT_WITH_ANVIL);

            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof MiteAnvilBlockEntity) {
                player.openMenu((MiteAnvilBlockEntity)blockentity);
                player.awardStat(Stats.INTERACT_WITH_LECTERN);
            }
        }

        return InteractionResult.SUCCESS;
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
    public static BlockState damage(BlockState state, ContainerData dataAccess, int value) {

        int durability = dataAccess.get(MiteAnvilBlockEntity.DURABILITY);

        AnvilVariant anvilVariant = state.getValue(ANVIL_VARIANT);
        if (anvilVariant==AnvilVariant.ADAMANTIUM) {
            return state;
        }

        durability-=value;

        if(durability<=0){
            return null;
        }

        dataAccess.set(MiteAnvilBlockEntity.DURABILITY,durability);

        int maxDamage = dataAccess.get(MiteAnvilBlockEntity.MAX_DAMAGE);


        AnvilStage oldStage = state.getValue(ANVIL_STAGE);

        AnvilStage newStage;
        if(durability>=maxDamage*0.5){
            newStage = AnvilStage.NORMAL;
        }else if(durability>=maxDamage*0.2){
            newStage = AnvilStage.CRACKED;
        }else{
            newStage = AnvilStage.DAMAGED;
        }

        if(newStage!=oldStage){
            return state.setValue(ANVIL_STAGE,newStage);
        }
        return state;
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

    @Override
    public @NotNull ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        ItemStack itemStack = super.getCloneItemStack(level, pos, state, includeData, player);
        return getAnvilItemStack(itemStack,(MiteAnvilBlockEntity) level.getBlockEntity(pos),state);
    }


    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        List<ItemStack> list = super.getDrops(state,params);
        if(list.isEmpty()){
            return list;
        }
        MiteAnvilBlockEntity blockEntity = (MiteAnvilBlockEntity) params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        return List.of(getAnvilItemStack(list.getFirst(),blockEntity,state));
    }

    private ItemStack getAnvilItemStack(ItemStack itemStack, MiteAnvilBlockEntity blockEntity, BlockState state){

        if(blockEntity==null){
            return itemStack;
        }

        itemStack.setDamageValue(itemStack.getMaxDamage() - blockEntity.getDataAccess().get(MiteAnvilBlockEntity.DURABILITY));
        BlockItemStateProperties blockItemStateProperties = itemStack.get(DataComponents.BLOCK_STATE);
        if(blockItemStateProperties==null){
            blockItemStateProperties = BlockItemStateProperties.EMPTY;
        }
        itemStack.set(DataComponents.BLOCK_STATE, blockItemStateProperties.with(ANVIL_STAGE, state.getValue(ANVIL_STAGE)));
        return itemStack;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.setPlacedBy(level, pos, state, placer, itemStack);
        MiteAnvilBlockEntity blockEntity = (MiteAnvilBlockEntity) level.getBlockEntity(pos);
        if(blockEntity==null){
            return;
        }
        blockEntity.getDataAccess().set(MiteAnvilBlockEntity.DURABILITY,itemStack.getMaxDamage() - itemStack.getDamageValue());
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MiteAnvilBlockEntity(pos,state);
    }

    public enum AnvilVariant implements StringRepresentable {
        COPPER("copper"), GOLD("gold"), SILVER("silver"),
        IRON("iron"),
        ANCIENT_METAL("ancient_metal"), HARD("hard"),
        MITHRIL("mithril"),ADAMANTIUM("adamantium");
        private final String name;

        AnvilVariant(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }

        public static AnvilVariant byCode(int code){
            return values()[code];
        }

        public boolean canProcessMaterial(int materialLevelType){
            if(this.ordinal() <= SILVER.ordinal()){
                return materialLevelType <= MaterialLevelType.GOLD_COPPER_FAMILY.level;
            }else if(this == IRON){
                return materialLevelType <= MaterialLevelType.IRON_STEEL_FAMILY.level;
            }else if(this.ordinal() <= HARD.ordinal()){
                return materialLevelType <= MaterialLevelType.ANCIENT_HARDENED_FAMILY.level;
            }else if(this == MITHRIL){
                return (materialLevelType != MaterialLevelType.ADAMANTIUM.level);
            }else{
                return true;
            }
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


