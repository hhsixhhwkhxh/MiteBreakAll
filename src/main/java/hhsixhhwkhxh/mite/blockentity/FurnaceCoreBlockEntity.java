package hhsixhhwkhxh.mite.blockentity;

import hhsixhhwkhxh.mite.Utils;
import hhsixhhwkhxh.mite.block.FurnaceWallBlock;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.menu.LargeFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static hhsixhhwkhxh.mite.block.FurnaceCore.*;
import static hhsixhhwkhxh.mite.menu.LargeFurnaceMenu.*;
import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public class FurnaceCoreBlockEntity extends BaseContainerBlockEntity {

    private BlockPos furnaceCentrePos = null;
    private BlockPos realFurnacePos = null;
    private Set<BlockPos> shadowCores = new HashSet<>(3);

    public final Block wallBlock;
    public final Block coreBlock;
    public final Block wallWrapperBlock;

    protected NonNullList<ItemStack> items = NonNullList.withSize(44, ItemStack.EMPTY);

    public static RecipeType<SmeltingRecipe> RECIPE_TYPE = RecipeType.SMELTING;
    private final static RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> QUICK_CHECK =RecipeManager.createCheck(RECIPE_TYPE);

    private final int[] dataArray = new int[DATA_COUNT];

    public final static int TEMPERATURE;
    public final static int[] FUEL_BURN_TIME_REMAINING = new int[4];
    public final static int[] COOKING_TIMER = new int[4];
    public final static int[] COOKING_TOTAL_TIME = new int[4];

    private static int dataIndexCounter = 0;
    static {
        TEMPERATURE = assignIndexForArray();
        assignIndexForArray(FUEL_BURN_TIME_REMAINING);
        assignIndexForArray(COOKING_TIMER);
    }

    public static void assignIndexForArray(int[] array){
        for (int index = 0; index < array.length; index++) {
            array[index] = dataIndexCounter++;
        }
    }

    public static int assignIndexForArray(){
        return dataIndexCounter++;
    }

    private final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return dataArray[index];
        }

        @Override
        public void set(int index, int value) {
            dataArray[index] = value;
        }

        @Override
        public int getCount() {
            return DATA_COUNT;
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

    public static void serverTick(ServerLevel level, BlockPos pos, BlockState state, FurnaceCoreBlockEntity furnace) {

        if(!state.getValue(ACTIVATED)||state.getValue(SHADOW)){
            return;
        }

        int temperature = furnace.getTemperature();
        if(temperature <= 0){
            if(state.getValue(LIT)){
                level.setBlock(pos,state.setValue(LIT,false),UPDATE_ALL);
                furnace.setChanged();
                return;
            }
        }else{
            if(!state.getValue(LIT)){
                level.setBlock(pos,state.setValue(LIT,true),UPDATE_ALL);
            }
        }

        //燃料
        boolean hasAnyFuelBurning = false;
        for (int i = 0; i < furnace.getCoreQuantity(); i++){

            ItemStack fuelStack = furnace.getItems().get(LargeFurnaceMenu.FUEL_SLOT[i]);

            int fuelBurnTimeRemaining = furnace.getFuelBurnTimeRemaining(i);
            if(fuelBurnTimeRemaining>0){
                hasAnyFuelBurning = true;
                fuelBurnTimeRemaining--;
                temperature++;

                furnace.setFuelBurnTimeRemaining(i,fuelBurnTimeRemaining);
            }else if(!fuelStack.isEmpty()){
                fuelBurnTimeRemaining = getBurnDuration(level.fuelValues(),fuelStack);
                furnace.setFuelBurnTimeRemaining(i,fuelBurnTimeRemaining);
                fuelStack.shrink(1);
            }
        }

        if(!hasAnyFuelBurning){
            if(temperature<=0){
                return;
            }
            temperature--;
        }

        //熔炼 计时器
        for (int i = 0; i < furnace.getCoreQuantity(); i++){
            int cookingTimer = furnace.getCookingTimer(i);
            int cookingTotalTime = furnace.getCookingTotalTime(i);

            ItemStack inputStack = furnace.getItems().get(INGREDIENT_SLOT[i]);
            if(cookingTotalTime <= 0){
                //没有任务进行
                if(inputStack.isEmpty()){
                    continue;
                }
                //开始新任务
                cookingTotalTime = getItemTotalCookTime(level,inputStack);
                if(cookingTimer<=0){
                    continue;
                }
                inputStack.shrink(1);
                furnace.setCookingTimer(i,0);
                furnace.setCookingTotalTime(i,cookingTotalTime);
                continue;
            }
            if(cookingTimer >= cookingTotalTime){
                //结算
                int finalI = i;
                furnace.getBurnOutput(level,inputStack).ifPresent(itemStack->{
                    furnace.getItems().set(BURN_RESULT_SLOT[finalI],itemStack);
                });
                cookingTotalTime = cookingTimer = 0;
                furnace.setCookingTotalTime(i,cookingTotalTime);
                furnace.setCookingTimer(i,cookingTimer);
                continue;
            }
            cookingTimer++;
            furnace.setCookingTimer(i,cookingTimer);
        }

        furnace.setTemperature(temperature);
        furnace.setChanged();

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

        Utils.loadBlockPosCollection(input, "shadow_cores", worldPosition, new HashSet<>(3)).ifPresent(set->{
            shadowCores = set;
        });
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        Utils.saveBlockPos(output,"real_furnace", worldPosition, realFurnacePos);
        Utils.saveBlockPos(output,"furnace_centre", worldPosition, furnaceCentrePos);
        ContainerHelper.saveAllItems(output, this.items);

        Utils.saveBlockPosCollection(output,"shadow_cores",worldPosition,shadowCores);
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
        return items.size();
    }

    public int getTemperature() {
        return dataAccess.get(TEMPERATURE);
    }

    public void setTemperature(int value) {
        dataAccess.set(TEMPERATURE, value);
    }

    public int getFuelBurnTimeRemaining(int index){
        return dataAccess.get(FUEL_BURN_TIME_REMAINING[index]);
    }

    public void setFuelBurnTimeRemaining(int index,int value){
        dataAccess.set(FUEL_BURN_TIME_REMAINING[index], value);
    }

    public int getCookingTimer(int index){
        return dataAccess.get(COOKING_TIMER[index]);
    }

    public void setCookingTimer(int index, int value){
        dataAccess.set(COOKING_TIMER[index], value);
    }

    public int getCookingTotalTime(int index){
        return dataAccess.get(COOKING_TOTAL_TIME[index]);
    }

    public void setCookingTotalTime(int index, int value){
        dataAccess.set(COOKING_TOTAL_TIME[index], value);
    }

    private int getCoreQuantity(){
        return shadowCores.size()+1;
    }

    @Nullable
    public static <T extends BlockEntity> BlockEntityTicker<T> createFurnaceTicker(
            Level level, BlockEntityType<T> serverType, BlockEntityType<?> clientType
    ) {
        if(level instanceof ServerLevel serverLevel && serverType == clientType){
            return ((pLevel, pBlockPos, pBlockState, pBlockEntity) -> serverTick(serverLevel, pBlockPos, pBlockState,(FurnaceCoreBlockEntity) pBlockEntity));
        }
        return null;
    }

    public static int getItemTotalCookTime(ServerLevel level, ItemStack input) {
        SingleRecipeInput singlerecipeinput = new SingleRecipeInput(input);
        return QUICK_CHECK.getRecipeFor(singlerecipeinput, level).map(p_379263_ -> p_379263_.value().cookingTime()).orElse(200);
    }

    public static int getBurnDuration(FuelValues fuelValues, ItemStack stack) {
        return stack.getBurnTime(RECIPE_TYPE, fuelValues);
    }

    public Optional<ItemStack> getBurnOutput(ServerLevel level, ItemStack input){
        SingleRecipeInput singlerecipeinput = new SingleRecipeInput(input);
        var recipeholder = QUICK_CHECK.getRecipeFor(singlerecipeinput, level).orElse(null);
        if (recipeholder == null) {
            return Optional.empty();
        }

        return Optional.of(recipeholder.value().assemble(singlerecipeinput, level.registryAccess()));
    }

    public static class FindResult{
        public static final FindResult FAIL = new FindResult();
        static {
            FAIL.setValid(false);
        }
        private final Set<BlockPos> corePosSet = new HashSet<>(4);
        private final Set<BlockPos> wallPosSet = new HashSet<>(22);

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
