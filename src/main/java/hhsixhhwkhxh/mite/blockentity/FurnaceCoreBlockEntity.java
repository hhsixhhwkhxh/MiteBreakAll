package hhsixhhwkhxh.mite.blockentity;

import hhsixhhwkhxh.mite.Utils;
import hhsixhhwkhxh.mite.block.FurnaceWallBlock;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.menu.LargeFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static hhsixhhwkhxh.mite.block.FurnaceCore.ACTIVATED;
import static hhsixhhwkhxh.mite.block.FurnaceCore.SHADOW;
import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public class FurnaceCoreBlockEntity extends BaseContainerBlockEntity {

    private BlockPos furnaceCentrePos = null;
    private BlockPos realFurnacePos = null;
    private final Set<BlockPos> shadowCores = new HashSet<>(3);

    public final Block wallBlock;
    public final Block coreBlock;
    public final Block wallWrapperBlock;

    protected NonNullList<ItemStack> items = NonNullList.withSize(44, ItemStack.EMPTY);

    private final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return 0;
        }

        @Override
        public void set(int index, int value) {

        }

        @Override
        public int getCount() {
            return 44;
        }
    };

    public void setRealFurnacePos(BlockPos realFurnacePos) {
        this.realFurnacePos = realFurnacePos;
    }

    public FurnaceCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.FURNACE_CORE.get(), pos, blockState);
        this.wallBlock = Blocks.COBBLESTONE;
        this.coreBlock = ModBlocks.STONE_FURNACE_CORE.get();
        this.wallWrapperBlock = ModBlocks.COBBLESTONE_MATERIAL_BLOCK.get();
    }

    public FurnaceCoreBlockEntity(BlockPos pos, BlockState blockState, Block wallBlock, Block coreBlock) {
        super(ModBlockEntities.FURNACE_CORE.get(), pos, blockState);
        this.wallBlock = wallBlock;
        this.coreBlock = coreBlock;

        var wallBlockState = wallBlock.defaultBlockState();
        if(wallBlockState.is(Blocks.COBBLESTONE)){
            wallWrapperBlock = ModBlocks.COBBLESTONE_MATERIAL_BLOCK.get();
        }else if (wallBlockState.is(Blocks.OBSIDIAN)){
            wallWrapperBlock = ModBlocks.OBSIDIAN_MATERIAL_BLOCK.get();
        }else if (wallBlockState.is(Blocks.NETHERRACK)){
            wallWrapperBlock = ModBlocks.NETHERRACK_MATERIAL_BLOCK.get();
        }else{
            throw new IllegalStateException("Unexcepted wallBlockState: "+wallBlockState);
        }
    }

    public void trySpawnLargeFurnace(LevelAccessor level){
        if(!hasWallBlockAboveAndBelow(level,worldPosition)){
            return;
        }

        for (BlockPos neighbourBlockPos : getNeighbourPosList(worldPosition)) {
            FindResult findResult = isCenterPos(level,neighbourBlockPos,true);
            if(findResult.isValid){
                furnaceCentrePos = neighbourBlockPos;
                setShadow(level,false);

                findResult.wallPosSet.forEach(wallBlockPos-> wrapWallBlock(level,wallBlockPos));

                findResult.corePosSet.forEach(coreBlockPos->{
                    level.setBlock(coreBlockPos,level.getBlockState(coreBlockPos).setValue(ACTIVATED,true),UPDATE_ALL);
                    if(coreBlockPos.equals(worldPosition)){
                        return;
                    }
                    shadowCores.add(coreBlockPos);

                    getBlockEntity(level,coreBlockPos).ifPresent(blockEntity->{
                        blockEntity.setShadow(level,true);
                        blockEntity.setRealFurnacePos(worldPosition);
                    });

                });


                return;
            }
        }
    }

    public void deactivationCore(LevelAccessor level){
        if(level.isClientSide()){
            return;
        }

        if(isShadow(level)){
            getBlockEntity(level, realFurnacePos).ifPresent(blockEntity->{
                blockEntity.deactivationCore(level);
            });
        }

        if(furnaceCentrePos==null){
            return;
        }

        FindResult findResult = isCenterPos(level, furnaceCentrePos, false);
        findResult.wallPosSet.forEach(wallBlockPos->{
            unwrapWallBlock(level,wallBlockPos);
        });

        findResult.corePosSet.forEach(coreBlockPos->{
            level.setBlock(coreBlockPos,level.getBlockState(coreBlockPos).setValue(ACTIVATED,false),UPDATE_ALL);
        });

    }

    public void wrapWallBlock(LevelAccessor level, BlockPos blockPos){
        level.setBlock(blockPos,
                wallWrapperBlock.defaultBlockState()
                        .setValue(FurnaceWallBlock.MATERIAL_TYPE,
                                FurnaceWallBlock.Type.getTypeByBlock(wallBlock)
                        )
                ,UPDATE_ALL);

        FurnaceWallBlock.setRealFurnacePos(level, blockPos, worldPosition);
    }

    public void unwrapWallBlock(LevelAccessor level,BlockPos blockPos){
        level.setBlock(blockPos, wallBlock.defaultBlockState(), UPDATE_ALL);
    }


    public static Optional<FurnaceCoreBlockEntity> getBlockEntity(LevelAccessor level, BlockPos pos){
        if(level==null||pos==null){
            return Optional.empty();
        }
        return Optional.ofNullable((FurnaceCoreBlockEntity)level.getBlockEntity(pos));
    }


    private boolean isShadow(LevelAccessor level){
        return level.getBlockState(worldPosition).getValue(SHADOW);
    }

    private void setShadow(LevelAccessor level,boolean value){
        level.setBlock(worldPosition,level.getBlockState(worldPosition).setValue(SHADOW,value),UPDATE_ALL);
    }

    private boolean isWallBlock(LevelAccessor level, BlockPos pos){
        return level.getBlockState(pos).is(wallBlock)||level.getBlockState(pos).is(wallWrapperBlock);
    }

    private boolean isCoreBlock(LevelAccessor level, BlockPos pos){
        return level.getBlockState(pos).is(coreBlock);
    }


    private FindResult isLegalPillar(LevelAccessor level, BlockPos pos){
        FindResult findResult1 = isWallBlockPillar(level,pos);
        FindResult findResult2 = isCoreBlockPillar(level,pos);
        if(findResult1.isValid){
            return findResult1;
        }
        if(findResult2.isValid){
            return findResult2;
        }
        return FindResult.FAIL;
    }

    public static BlockPos[] getNeighbourPosList(BlockPos pos){
        return new BlockPos[]{pos.east(),pos.south(),pos.west(),pos.north()};
    }



    private FindResult isCenterPos(LevelAccessor level, BlockPos pos, boolean strictMode){
        BlockPos[] cornerPosList = {pos.offset(-1,0,-1), pos.offset(1,0,1), pos.offset(1,0,-1), pos.offset(-1,0,1)};

        FindResult totalResult = new FindResult();
        totalResult.setValid(true);

        for (BlockPos cornerBlockPos : cornerPosList) {
            FindResult findResult = isWallBlockPillar(level,cornerBlockPos);
            if(strictMode&&!findResult.isValid){
                return FindResult.FAIL;
            }
            totalResult.merge(findResult);
        }

        for (BlockPos neighbourBlockPos : getNeighbourPosList(pos)) {
            FindResult findResult = isLegalPillar(level,neighbourBlockPos);
            if(strictMode&&!findResult.isValid){
                return FindResult.FAIL;
            }
            totalResult.merge(findResult);
        }

        if(hasWallBlockAboveAndBelow(level,pos)){
            totalResult.addWall(pos.offset(0,-1,0));
            totalResult.addWall(pos.offset(0,1,0));
            return totalResult;
        }

        if(strictMode){
            return FindResult.FAIL;
        }
        return totalResult;
    }

    //判断是否存在以pos为中心的1x3的材料方块(材料柱)
    private FindResult isWallBlockPillar(LevelAccessor level, BlockPos pos){
        FindResult result = new FindResult();

        result.setValid(false);

        if((isWallBlock(level,pos))&&hasWallBlockAboveAndBelow(level,pos)){
            result.addWall(pos);
            result.addWall(pos.offset(0,-1,0));
            result.addWall(pos.offset(0,1,0));
            result.setValid(true);
        }

        return result;
    }

    private FindResult isCoreBlockPillar(LevelAccessor level, BlockPos pos){
        FindResult result = new FindResult();

        result.setValid(false);

        if((isCoreBlock(level,pos))&&hasWallBlockAboveAndBelow(level,pos)){
            result.addCore(pos);
            result.addWall(pos.offset(0,-1,0));
            result.addWall(pos.offset(0,1,0));
            result.setValid(true);
        }

        return result;
    }

    private boolean hasWallBlockAboveAndBelow(LevelAccessor level, BlockPos pos){
        return (isWallBlock(level,pos.offset(0,-1,0)))&&(isWallBlock(level,pos.offset(0,1,0)));
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        Utils.loadBlockPos(input,"real_furnace", worldPosition).ifPresent(pos-> realFurnacePos = pos);
        Utils.loadBlockPos(input,"furnace_centre", worldPosition).ifPresent(pos-> furnaceCentrePos = pos);
        ContainerHelper.loadAllItems(input, this.items);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        Utils.saveBlockPos(output,"real_furnace", worldPosition, realFurnacePos);
        Utils.saveBlockPos(output,"furnace_centre", worldPosition, furnaceCentrePos);
        ContainerHelper.saveAllItems(output, this.items);
    }

    @Override
    public Component getDisplayName() {
        return Component.empty();
    }

    @Override
    protected Component getDefaultName() {
        return Component.empty();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {

    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new LargeFurnaceMenu(containerId,inventory,this,dataAccess);
    }

    @Override
    public int getContainerSize() {
        return 44;
    }

    public static class FindResult{
        public static final FindResult FAIL = new FindResult();
        static {
            FAIL.setValid(false);
        }
        private Set<BlockPos> corePosSet = new HashSet<>(4);
        private Set<BlockPos> wallPosSet = new HashSet<>(22);

        public void setValid(boolean valid) {
            isValid = valid;
        }

        boolean isValid = true;


        FindResult(){}

        public void addCore(BlockPos blockPos){
            corePosSet.add(blockPos);
        }

        public void addWall(BlockPos blockPos){
            wallPosSet.add(blockPos);
        }

        public void merge(FindResult findResult){
            this.corePosSet.addAll(findResult.corePosSet);
            this.wallPosSet.addAll(findResult.wallPosSet);
            if(!isValid){
                return;
            }
            this.isValid = findResult.isValid;
        }
    }
}
