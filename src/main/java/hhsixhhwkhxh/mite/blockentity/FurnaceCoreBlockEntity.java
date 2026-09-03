package hhsixhhwkhxh.mite.blockentity;

import hhsixhhwkhxh.mite.Utils;
import hhsixhhwkhxh.mite.block.FurnaceWallBlock;
import hhsixhhwkhxh.mite.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static hhsixhhwkhxh.mite.block.FurnaceCore.ACTIVATED;
import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public class FurnaceCoreBlockEntity extends BlockEntity {

    private BlockPos furnaceCentrePos = null;
    //private BlockPos realFurnacePos = null;
    private final Set<BlockPos> shadowCores = new HashSet<>(3);
    public final Block wallBlock;
    public final Block coreBlock;
    public final Block wrapperBlock;

    public BlockPos getFurnaceCentrePos() {
        //return furnaceCentrePos;
        return Utils.getAbsolutePos(worldPosition,furnaceCentrePos);
    }

    public void setFurnaceCentrePos(BlockPos furnaceCentrePos) {
        //this.furnaceCentrePos = furnaceCentrePos;
        this.furnaceCentrePos = Utils.getRelativePos(worldPosition,furnaceCentrePos);
    }

//    public BlockPos getRealFurnacePos() {
//        return realFurnacePos;
//    }
//
//    public void setRealFurnacePos(BlockPos realFurnacePos) {
//        this.realFurnacePos = realFurnacePos;
//    }

    public FurnaceCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.FURNACE_CORE.get(), pos, blockState);
        this.wallBlock = Blocks.COBBLESTONE;
        this.coreBlock = ModBlocks.STONE_FURNACE_CORE.get();
        this.wrapperBlock = ModBlocks.COBBLESTONE_MATERIAL_BLOCK.get();
    }

    public FurnaceCoreBlockEntity(BlockPos pos, BlockState blockState, Block wallBlock, Block coreBlock) {
        super(ModBlockEntities.FURNACE_CORE.get(), pos, blockState);
        this.wallBlock = wallBlock;
        this.coreBlock = coreBlock;

        var wallBlockState = wallBlock.defaultBlockState();
        if(wallBlockState.is(Blocks.COBBLESTONE)){
            wrapperBlock = ModBlocks.COBBLESTONE_MATERIAL_BLOCK.get();
        }else if (wallBlockState.is(Blocks.OBSIDIAN)){
            wrapperBlock = ModBlocks.OBSIDIAN_MATERIAL_BLOCK.get();
        }else if (wallBlockState.is(Blocks.NETHERRACK)){
            wrapperBlock = ModBlocks.NETHERRACK_MATERIAL_BLOCK.get();
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
                //furnaceCentrePos = neighbourBlockPos;
                setFurnaceCentrePos(neighbourBlockPos);

                findResult.wallPosSet.forEach(wallBlockPos->{
                    wrapWallBlock(level,wallBlockPos);
                });

                findResult.corePosSet.forEach(coreBlockPos->{
                    level.setBlock(coreBlockPos,level.getBlockState(coreBlockPos).setValue(ACTIVATED,true),UPDATE_ALL);
                });


                return;
            }
        }
    }

    public void deactivationCore(LevelAccessor level){
        if(level.isClientSide()){
            return;
        }
        if(furnaceCentrePos==null){
            return;
        }

        FindResult findResult = isCenterPos(level,getFurnaceCentrePos(),false);
        findResult.wallPosSet.forEach(wallBlockPos->{
            unwrapWallBlock(level,wallBlockPos);
        });

        findResult.corePosSet.forEach(coreBlockPos->{
            //level.removeBlock(coreBlockPos,false);
            level.setBlock(coreBlockPos,level.getBlockState(coreBlockPos).setValue(ACTIVATED,false),UPDATE_ALL);
        });

    }

    public void wrapWallBlock(LevelAccessor level,BlockPos blockPos){
        level.setBlock(blockPos,wrapperBlock.defaultBlockState(),UPDATE_ALL);
        FurnaceWallBlock.setRealFurnacePos(level,blockPos,worldPosition);
    }

    public void unwrapWallBlock(LevelAccessor level,BlockPos blockPos){
        level.setBlock(blockPos, wallBlock.defaultBlockState(), UPDATE_ALL);
    }


    public static Optional<FurnaceCoreBlockEntity> getBlockEntity(LevelAccessor level, BlockPos pos){
        return Optional.ofNullable((FurnaceCoreBlockEntity)level.getBlockEntity(pos));
    }


//    public Set<BlockPos> getShadowCores() {
//        return shadowCores;
//    }
//
//    public void addShadowCores(BlockPos blockPos) {
//        shadowCores.add(blockPos);
//    }
//
//    public void updateShadowCoreList(LevelAccessor level){
//        shadowCores.clear();
//        for (BlockPos shadowPos : getNeighbourPosList(furnaceCentrePos)) {
//            if(shadowPos.equals(worldPosition)){
//                continue;
//            }
//            if(level.getBlockState(shadowPos).is(coreBlock)){
//                shadowCores.add(shadowPos);
//                level.getBlockState(shadowPos).setValue(FurnaceCore.SHADOW,true);
//                getBlockEntity(level,shadowPos).ifPresent(blockEntity->{
//                    blockEntity.setFurnaceCentrePos(worldPosition);
//                });
//            }
//        }
//    }
//
//    public void syncShadowCoreStates(LevelAccessor level){
//        shadowCores.forEach(shadowPos->{
//
//        });
//    }

    private boolean isWallBlock(LevelAccessor level, BlockPos pos){
        return level.getBlockState(pos).is(wallBlock)||level.getBlockState(pos).is(wrapperBlock);
    }

    private boolean isCoreBlock(LevelAccessor level, BlockPos pos){
        return level.getBlockState(pos).is(coreBlock);
    }

//    public void checkCentreExists(LevelAccessor level,BlockPos pos,FurnaceCoreBlockEntity blockEntity){
//        BlockPos centreBlockPos = blockEntity.getFurnaceCentrePos();
//        if(isCenterPos(level,centreBlockPos)){
//            return;
//        }
//        level.getBlockState(pos).setValue(ACTIVATED,false);
//    }




    private FindResult isLegalPillar(LevelAccessor level, BlockPos pos){
        //return (isWallBlockPillar(level,pos)|| isCoreBlockPillar(level,pos));
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
        //return (isWallBlock(level,pos))&&hasWallBlockAboveAndBelow(level,pos);
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
        //return (isCoreBlock(level,pos))&&hasWallBlockAboveAndBelow(level,pos);
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
        //Utils.loadBlockPos(input,"real_furnace").ifPresent(pos-> realFurnacePos = pos);
        Utils.loadBlockPos(input,"furnace_centre").ifPresent(pos-> furnaceCentrePos = pos);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        //Utils.saveBlockPos(output,"real_furnace", realFurnacePos);
        Utils.saveBlockPos(output,"furnace_centre", furnaceCentrePos);
    }

    public static class FindResult{
        public static final FindResult FAIL = new FindResult();
        static {
            FAIL.setValid(false);
        }
        private Set<BlockPos> corePosSet = new HashSet<>(4);



        private Set<BlockPos> wallPosSet = new HashSet<>(22);


        public boolean isValid() {
            return isValid;
        }

        public void setValid(boolean valid) {
            isValid = valid;
        }

        boolean isValid = true;


        FindResult(){
        }


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
