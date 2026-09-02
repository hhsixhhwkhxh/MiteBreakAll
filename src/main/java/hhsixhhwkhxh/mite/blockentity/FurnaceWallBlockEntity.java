package hhsixhhwkhxh.mite.blockentity;

import hhsixhhwkhxh.mite.Utils;
import hhsixhhwkhxh.mite.block.FurnaceWallBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public class FurnaceWallBlockEntity extends BlockEntity {


    private BlockPos realFurnacePos = null;
    private final FurnaceWallBlock.Type wallType;


    public FurnaceWallBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.FURNACE_WALL.get(), pos, blockState);
        this.wallType = FurnaceWallBlock.Type.COBBLESTONE;
    }

    public FurnaceWallBlockEntity(BlockPos pos, BlockState blockState, FurnaceWallBlock.Type wallType) {
        super(ModBlockEntities.FURNACE_WALL.get(), pos, blockState);
        this.wallType = wallType;
    }

    public BlockPos getRealFurnacePos() {
        return realFurnacePos;
    }

    public void setRealFurnacePos(BlockPos realFurnacePos) {
        this.realFurnacePos = realFurnacePos;
    }

    public void onDestroy(LevelAccessor level){
        if(realFurnacePos==null){
            return;
        }
        BlockEntity blockEntity = level.getBlockEntity(realFurnacePos);
        if(blockEntity instanceof FurnaceCoreBlockEntity furnaceCoreBlockEntity){
            furnaceCoreBlockEntity.deactivationCore(level);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        Utils.loadBlockPos(input,"real_furnace").ifPresentOrElse(pos-> realFurnacePos = pos,()->{
            if(level!=null){
                level.setBlock(worldPosition,wallType.getBlock().defaultBlockState(),UPDATE_ALL);
            }
        });
    }



    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        Utils.saveBlockPos(output,"real_furnace",realFurnacePos);
    }
}