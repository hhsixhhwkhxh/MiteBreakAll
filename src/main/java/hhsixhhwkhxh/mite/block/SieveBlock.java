package hhsixhhwkhxh.mite.block;

import com.mojang.serialization.MapCodec;
import hhsixhhwkhxh.mite.custom.MeshType;
import hhsixhhwkhxh.mite.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

import java.util.*;

import static net.minecraft.world.level.block.LevelEvent.COMPOSTER_FILL;

public class SieveBlock extends Block {
    public static final MapCodec<SieveBlock> CODEC = simpleCodec(SieveBlock::new);
    public static final IntegerProperty GRAVEL_LEVEL = IntegerProperty.create("gravel_level", 0, 4);
    public static final EnumProperty<MeshType> MESH_TYPE = EnumProperty.create("mesh_type", MeshType.class);
    public static final IntegerProperty MESH_DAMAGE = IntegerProperty.create("mesh_damage", 0, 15);


    private static final Map<ItemLike, Float> lootPool = Map.ofEntries(
            Map.entry(ModItems.OBSIDIAN_SHARD, 0.12F),
            Map.entry(ModItems.FLINT_SHARD, 0.5389F),
            Map.entry(ModItems.COPPER_NUGGET, 0.2F),
            Map.entry(Items.FLINT,0.07F),
            Map.entry(ModItems.HARD_NUGGET,0.004F),
            Map.entry(ModItems.SILVER_NUGGET,0.05F),
            Map.entry(Items.GOLD_NUGGET,0.01F),
            Map.entry(ModItems.EMERALD_SHARD,0.004F),
            Map.entry(ModItems.DIAMOND_SHARD,0.002F),
            Map.entry(ModItems.MITHRIL_NUGGET,0.001F),
            Map.entry(ModItems.ADAMANTIUM_NUGGET,1.0E-4F)
    );


    private static final VoxelShape[] SHAPES = Util.make(
        () -> {
            return Block.boxes(
                4, level -> Shapes.join(Shapes.block(), Block.column(14.0, Math.clamp((long)(10 + level), 2, 16), 16.0), BooleanOp.ONLY_FIRST)
            );
        }
    );

    private static final VoxelShape EMPTY_SHAPE =Shapes.join(Shapes.block(), Block.column(14.0, Math.clamp(0, 2, 16), 16.0), BooleanOp.ONLY_FIRST);

    @Override
    public MapCodec<SieveBlock> codec() {
        return CODEC;
    }



    public SieveBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(GRAVEL_LEVEL, 0)
                .setValue(MESH_TYPE, MeshType.EMPTY)
                .setValue(MESH_DAMAGE, 0)
        );
    }


    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if(state.getValue(MESH_TYPE)==MeshType.EMPTY){
            return EMPTY_SHAPE;
        }else{
            return SHAPES[state.getValue(GRAVEL_LEVEL)];
        }
    }

    @Override
    protected VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if(state.getValue(MESH_TYPE)==MeshType.EMPTY){
            return EMPTY_SHAPE;
        }else{
            return SHAPES[state.getValue(GRAVEL_LEVEL)];
        }
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (state.getValue(GRAVEL_LEVEL) == 7) {
            level.scheduleTick(pos, state.getBlock(), 20);
        }
        // Neo: Invalidate composter capabilities when a composter is added
        if (!oldState.is(this)) level.invalidateCapabilities(pos);
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState p_394424_, ServerLevel p_394241_, BlockPos p_393520_, boolean p_394545_) {
        super.affectNeighborsAfterRemoval(p_394424_, p_394241_, p_393520_, p_394545_);
        // Neo: Invalidate composter capabilities when a composter is removed
        if (!p_394241_.getBlockState(p_393520_).is(p_394424_.getBlock())) p_394241_.invalidateCapabilities(p_393520_);
    }

    @Override
    protected InteractionResult useItemOn(
        ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {


        if(state.getValue(MESH_TYPE)==MeshType.EMPTY){
            BlockState blockstate;
            if(ItemStack.isSameItem(stack, ModItems.MESH_STRING.toStack())){
                blockstate = state.setValue(MESH_TYPE,MeshType.STRING).setValue(MESH_DAMAGE,stack.getDamageValue());
            }else if(ItemStack.isSameItem(stack, ModItems.MESH_LEATHER.toStack())){
                blockstate = state.setValue(MESH_TYPE,MeshType.LEATHER).setValue(MESH_DAMAGE,stack.getDamageValue());
            }else{
                return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
            }

            if(level.isClientSide()){
                return InteractionResult.SUCCESS;
            }

            updateState(stack,state,blockstate,level,pos,player);
            stack.consume(1, player);

            return InteractionResult.SUCCESS;
        }else{
            if(!ItemStack.isSameItem(stack, Items.GRAVEL.getDefaultInstance())){
                if(state.getValue(GRAVEL_LEVEL)!=0){
                    return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
                }
                removeMesh(state,level,pos,player);
                return InteractionResult.SUCCESS;
            }
            int gravelLevel = state.getValue(GRAVEL_LEVEL);

            BlockState blockstate;
            if(gravelLevel!=0){
                if(gravelLevel==1){
                    blockstate = extractProduce(player, state, level, pos,player);
                }else{
                    blockstate = state.setValue(GRAVEL_LEVEL,gravelLevel-1);
                }
            }else{
                blockstate = state.setValue(GRAVEL_LEVEL,4);
                stack.consume(1, player);
            }

            updateState(stack,state,blockstate,level,pos,player);

            return InteractionResult.SUCCESS;
        }
    }

    private void removeMesh(BlockState state, Level level, BlockPos pos, Player player){
        ItemStack meshItemStack = new ItemStack((ItemLike) ((state.getValue(MESH_TYPE)==MeshType.LEATHER)?ModItems.MESH_LEATHER:ModItems.MESH_STRING));
        meshItemStack.setDamageValue(state.getValue(MESH_DAMAGE));
        dropItem(meshItemStack,level,pos);

        BlockState blockstate = state.setValue(MESH_TYPE,MeshType.EMPTY).setValue(MESH_DAMAGE,0);
        updateState(null,state,blockstate,level,pos,player);
    }

    private static void updateState(ItemStack stack,BlockState oldState,BlockState newState, Level level, BlockPos pos, Player player){
        level.setBlock(pos, newState, UPDATE_ALL);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));

        level.levelEvent(COMPOSTER_FILL, pos, oldState != newState ? 1 : 0);
        if(stack!=null){
            player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        }

    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {


        int gravelLevel = state.getValue(GRAVEL_LEVEL);

        if(gravelLevel==0){
            if(state.getValue(MESH_TYPE)==MeshType.EMPTY){
                return InteractionResult.PASS;
            }
            removeMesh(state,level,pos,player);
        }
        BlockState blockstate;
        if(gravelLevel==1){
            blockstate = extractProduce(player, state, level, pos,player);
        }else{
            blockstate = state.setValue(GRAVEL_LEVEL,gravelLevel-1);
        }


        updateState(null,state,blockstate,level,pos,player);

        return InteractionResult.SUCCESS;
    }

    private static void dropItem(ItemStack itemStack, Level level, BlockPos pos){
        Vec3 vec3 = Vec3.atLowerCornerWithOffset(pos, 0.5, 1.01, 0.5).offsetRandom(level.random, 0.7F);
        ItemEntity itementity = new ItemEntity(level, vec3.x(), vec3.y(), vec3.z(), itemStack);
        itementity.setDefaultPickUpDelay();
        level.addFreshEntity(itementity);
    }

    public static BlockState extractProduce(Entity entity, BlockState state, Level level, BlockPos pos,Player player) {
        BlockState blockState;

        int meshDamage = state.getValue(MESH_DAMAGE);
        int maxDamage = (state.getValue(MESH_TYPE)==MeshType.LEATHER)?7:15;
        if(meshDamage>=maxDamage){
            blockState = state.setValue(MESH_TYPE,MeshType.EMPTY);
        }else{
            blockState = state.setValue(MESH_DAMAGE,meshDamage+1);
        }
        blockState = blockState.setValue(GRAVEL_LEVEL, 0);

        if (!level.isClientSide) {
            ServerLevel serverLevel = (ServerLevel) level;

            if(shouldDropLoot(serverLevel)){
                RandomSource random = serverLevel.getRandom();
                List<ItemStack> resultList = selectItemCounts(selectItemKinds(random,random.nextInt(3)+1),random);

                for(ItemStack itemStack:resultList){
                    dropItem(itemStack,level,pos);
                }
            }
        }

        //BlockState blockstate = empty(entity, state, level, pos);
        level.playSound(null, pos, SoundEvents.COMPOSTER_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
        return blockState;
    }

    private static boolean shouldDropLoot(ServerLevel serverLevel){
        Difficulty difficulty = serverLevel.getDifficulty();
        if((difficulty==Difficulty.PEACEFUL||difficulty==Difficulty.EASY)&&serverLevel.getRandom().nextInt(100)<31){
            return false;
        }
        if((difficulty==Difficulty.NORMAL||difficulty==Difficulty.HARD)&&serverLevel.getRandom().nextInt(20)<11){
            return false;
        }
        return true;
    }

    public static List<ItemLike> selectItemKinds(RandomSource random, int selectCount) {
        if (selectCount > lootPool.size()) {
            throw new IndexOutOfBoundsException();
        } else if (selectCount <= 0) {
            throw new IllegalStateException("count must > 0");
        } else {
            ArrayList<ItemLike> resultList = new ArrayList<>(selectCount);
            ArrayList<Map.Entry<ItemLike,Float>> tempPool = new ArrayList<>(lootPool.entrySet());
            float totalWeight = 1.0F;

            for(int i = 0; i < selectCount; i++) {
                Iterator<Map.Entry<ItemLike,Float>> poolIterator = tempPool.iterator();
                float randomWeightThreshold = random.nextFloat() * totalWeight;
                float accumulatedWeight = 0.0F;

                while(poolIterator.hasNext()) {
                    Map.Entry<ItemLike,Float> weightEntry = poolIterator.next();
                    if ((accumulatedWeight += weightEntry.getValue()) >= randomWeightThreshold) {
                        totalWeight -= weightEntry.getValue();
                        resultList.add(weightEntry.getKey());
                        poolIterator.remove();
                        break;
                    }
                }
            }

            return resultList;
        }
    }

    public static List<ItemStack> selectItemCounts(List<ItemLike> ingredientList,RandomSource random) {
        List<ItemStack> resultList = new ArrayList<>();
        if (ingredientList == null) {
            return resultList;
        }
        int remainingCount = 3;

        for (ItemLike itemLike : ingredientList) {
            ItemStack itemStackWrapper = new ItemStack(itemLike);
            if (remainingCount <= 0) {
                break;
            }
            if (ItemStack.isSameItem(itemStackWrapper,ModItems.COPPER_NUGGET.toStack()) || ItemStack.isSameItem(itemStackWrapper,ModItems.SILVER_NUGGET.toStack()) || ItemStack.isSameItem(itemStackWrapper,ModItems.OBSIDIAN_SHARD.toStack())) {
                int randomGain = random.nextInt(2) + 1;
                int actualSpawnCount = Math.min(remainingCount, randomGain);
                itemStackWrapper.setCount(actualSpawnCount);
                remainingCount -= randomGain;
            } else if (ItemStack.isSameItem(itemStackWrapper,ModItems.FLINT_SHARD.toStack())) {
                int randomGain = random.nextInt(3) + 1;
                int actualSpawnCount = Math.min(remainingCount, randomGain);
                itemStackWrapper.setCount(actualSpawnCount);
                remainingCount -= randomGain;
            }

            resultList.add(itemStackWrapper);
        }

        return resultList;
    }

    static BlockState empty(@Nullable Entity entity, BlockState state, LevelAccessor level, BlockPos pos) {
        BlockState blockstate = state.setValue(GRAVEL_LEVEL, 0);
        level.setBlock(pos, blockstate, 3);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, blockstate));
        return blockstate;
    }



    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(GRAVEL_LEVEL) == 7) {
            level.setBlock(pos, state.cycle(GRAVEL_LEVEL), 3);
            level.playSound(null, pos, SoundEvents.COMPOSTER_READY, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    /**
     * Returns the analog signal this block emits. This is the signal a comparator can read from it.
     */
    @Override
    protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return blockState.getValue(GRAVEL_LEVEL);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GRAVEL_LEVEL);
        builder.add(MESH_TYPE);
        builder.add(MESH_DAMAGE);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        List<ItemStack> resultList = new ArrayList<>();
        resultList.add(new ItemStack(ModBlocks.SIEVE));
        ItemStack itemStack = null;
        switch (state.getValue(MESH_TYPE)){
            case MeshType.LEATHER:
                itemStack = new ItemStack((ItemLike) ModItems.MESH_LEATHER);
                itemStack.setDamageValue(state.getValue(MESH_DAMAGE));
                break;
            case MeshType.STRING:
                itemStack = new ItemStack((ItemLike) ModItems.MESH_STRING);
                itemStack.setDamageValue(state.getValue(MESH_DAMAGE));
                break;
        }
        if(itemStack!=null){
            resultList.add(itemStack);
        }
        return resultList;
    }
}
