package hhsixhhwkhxh.mite.blockentity;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.Utils;
import hhsixhhwkhxh.mite.block.FurnaceWrapperBlock;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.menu.LargeFurnaceMenu;
import hhsixhhwkhxh.mite.recipe.MeltingRecipe;
import hhsixhhwkhxh.mite.recipe.ModRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import static hhsixhhwkhxh.mite.Utils.getHorizontalNeighbourPosList;
import static hhsixhhwkhxh.mite.Utils.isMould;
import static hhsixhhwkhxh.mite.block.FurnaceCore.*;
import static hhsixhhwkhxh.mite.menu.LargeFurnaceMenu.*;
import static net.minecraft.world.Containers.dropItemStack;
import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public class FurnaceCoreBlockEntity extends BaseContainerBlockEntity {

    private BlockPos furnaceCentrePos = null;
    private BlockPos realFurnacePos = null;
    private Set<BlockPos> shadowCores = new HashSet<>(3);

    public final Block brickBlock;
    public final Block coreBlock;
    public final Block brickWrapperBlock;
    private final List<BlockPos> layerUnderFurnacePosList = new ArrayList<>(9);

    protected NonNullList<ItemStack> items = NonNullList.withSize(44, ItemStack.EMPTY);

    private final static RecipeManager.CachedCheck<SingleRecipeInput, SmeltingRecipe> QUICK_CHECK_SMELTING = RecipeManager.createCheck(RecipeType.SMELTING);
    private final static RecipeManager.CachedCheck<SingleRecipeInput, MeltingRecipe> QUICK_CHECK_MELTING = RecipeManager.createCheck(ModRecipeTypes.MELTING_RECIPE.get());

    private final List<Consumer<Integer>> slotChangeListeners = new ArrayList<>();

    private final int[] dataArray = new int[DATA_COUNT];

    public final static int TEMPERATURE;
    public final static int[] FUEL_BURN_TIME_REMAINING = new int[4];
    public final static int[] COOKING_TIMER = new int[4];
    public final static int[] COOKING_TOTAL_TIME = new int[4];
    public final static int CORE_QUANTITY;
    public final static int[] IS_MELTING_RECIPE = new int[4];
    public final static int[] MELTING_OUTPUT_NAME = new int[4];
    public final static int TEMPERATURE_LIMIT;
    public final static int LAVA_BLOCK_COUNT;
    public final static int TICK_COUNTER;
    public final static int CRAFTING_TIMER;
    public final static int CRAFTING_TOTAL_TIME;
    public final static int MIN_CRAFTING_TEMPERATURE;

    public final static int TEMPERATURE_MULTIPLIER = 10000;


    private static int dataIndexCounter = 0;
    static {
        CORE_QUANTITY = assignSingleIndex();
        TEMPERATURE = assignSingleIndex();
        TEMPERATURE_LIMIT = assignSingleIndex();
        LAVA_BLOCK_COUNT = assignSingleIndex();
        TICK_COUNTER = assignSingleIndex();

        assignArrayIndex(FUEL_BURN_TIME_REMAINING);
        assignArrayIndex(COOKING_TIMER);
        assignArrayIndex(COOKING_TOTAL_TIME);

        assignArrayIndex(IS_MELTING_RECIPE);
        assignArrayIndex(MELTING_OUTPUT_NAME);

        CRAFTING_TIMER = assignSingleIndex();
        CRAFTING_TOTAL_TIME = assignSingleIndex();
        MIN_CRAFTING_TEMPERATURE = assignSingleIndex();

        Objects.checkIndex(dataIndexCounter, DATA_COUNT);
    }

    public static void assignArrayIndex(int[] array){
        for (int index = 0; index < array.length; index++) {
            array[index] = dataIndexCounter++;
        }
    }

    public static int assignSingleIndex(){
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
        this.brickBlock = Blocks.COBBLESTONE;
        this.coreBlock = ModBlocks.STONE_FURNACE_CORE.get();
        this.brickWrapperBlock = ModBlocks.COBBLESTONE_WRAPPER_BLOCK.get();
    }

    public FurnaceCoreBlockEntity(BlockPos pos, BlockState blockState, Block brickBlock, Block coreBlock) {
        super(ModBlockEntities.FURNACE_CORE.get(), pos, blockState);
        this.brickBlock = brickBlock;
        this.coreBlock = coreBlock;

        var brickBlockState = brickBlock.defaultBlockState();
        if(brickBlockState.is(Blocks.COBBLESTONE)){
            brickWrapperBlock = ModBlocks.COBBLESTONE_WRAPPER_BLOCK.get();
            setTemperatureLimit(2000);
        }else if (brickBlockState.is(Blocks.OBSIDIAN)){
            brickWrapperBlock = ModBlocks.OBSIDIAN_WRAPPER_BLOCK.get();
            setTemperatureLimit(5000);
        }else if (brickBlockState.is(Blocks.NETHERRACK)){
            brickWrapperBlock = ModBlocks.NETHERRACK_WRAPPER_BLOCK.get();
            setTemperatureLimit(7000);
        }else{
            throw new IllegalStateException("Unexcepted brickBlockState: "+brickBlockState);
        }
    }

    public static void serverTick(ServerLevel level, BlockPos pos, BlockState state, FurnaceCoreBlockEntity furnace) {

        if(!state.getValue(ACTIVATED)||state.getValue(SHADOW)){
            return;
        }

        if(furnace.updateTickCounter()){
            int lavaCount = 0;
            for (BlockPos bottomPos : furnace.getLayerUnderFurnacePosList()) {
                if(level.getBlockState(bottomPos).is(Blocks.LAVA)){
                    lavaCount++;
                }
            }
            furnace.setLavaBlockCount(lavaCount);
        }

        float temperature = furnace.getTemperature();
        if(temperature <= 0 && furnace.getLavaBlockCount() <= 0){
            if(state.getValue(LIT)){
                furnace.setLitWithShadow(level,state,false);
                return;
            }
        }else{
            if(!state.getValue(LIT)){
                furnace.setLitWithShadow(level,state,true);
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

                if(temperature < furnace.getTemperatureLimit()){
                    temperature+=0.05F;
                }

                furnace.setFuelBurnTimeRemaining(i,fuelBurnTimeRemaining);
            }else if(!fuelStack.isEmpty()){
                fuelBurnTimeRemaining = getBurnDuration(level.fuelValues(),fuelStack);
                furnace.setFuelBurnTimeRemaining(i,fuelBurnTimeRemaining);
                fuelStack.shrink(1);
            }
        }

        //岩浆
        int lavaCount = furnace.getLavaBlockCount();
        if(lavaCount > 0){
            temperature += (1.5f + 0.075f * Math.max(0, lavaCount - 1))*lavaCount;
        }

        if(!hasAnyFuelBurning){
            if(temperature<=0){
                return;
            }
            //temperature--;
            temperature = furnace.reduceTemperature(temperature);
        }

        //熔炼 计时器
        for (int i = 0; i < furnace.getCoreQuantity(); i++){
            int cookingTimer = furnace.getCookingTimer(i);
            int cookingTotalTime = furnace.getCookingTotalTime(i);

            ItemStack inputStack = furnace.getItems().get(INGREDIENT_SLOT[i]);
            ItemStack mouldStack = furnace.getItems().get(MOULD_SLOT[i]);

            //检查是否满足粒->锭的条件 用于更新dataAccess 方便客户端Screen获取信息
            var smeltingRecipeOpt = getMeltingRecipe(level,new SingleRecipeInput(inputStack));
            boolean isSmeltingRecipe = (smeltingRecipeOpt.isPresent());
            furnace.setIsMeltingRecipe(i,isSmeltingRecipe);
            if(isSmeltingRecipe){
                furnace.setMeltingOutputName(i,smeltingRecipeOpt.get().getResult().getItem().getDescriptionId());
            }

            if(cookingTotalTime <= 0){
                //没有任务进行
                if(inputStack.isEmpty()){
                    continue;
                }
                if(isSmeltingRecipe&&!isMeltingConditionMet(smeltingRecipeOpt.get(),inputStack,mouldStack,temperature)){
                    continue;
                }

                //开始新任务
                cookingTotalTime = furnace.getItemTotalCookTime(level,inputStack);

                if(cookingTotalTime<=0){
                    continue;
                }
                furnace.setCookingTimer(i,0);
                furnace.setCookingTotalTime(i,cookingTotalTime);
                continue;
            }
            if(cookingTimer >= cookingTotalTime){
                //尝试结算
                ItemStack outputStack = furnace.getItems().get(BURN_RESULT_SLOT[i]);
                AtomicBoolean isSucceeded = new AtomicBoolean(false);
                int finalI = i;
                furnace.getBurnOutput(level,inputStack).ifPresent(itemStack->{
                    if(ItemStack.isSameItemSameComponents(outputStack,itemStack)){
                        int count = outputStack.getCount();
                        if(count < outputStack.getMaxStackSize()){
                            outputStack.setCount(count + 1);
                            isSucceeded.set(true);
                        }
                        return;
                    }
                    if(outputStack.isEmpty()){
                        furnace.getItems().set(BURN_RESULT_SLOT[finalI],itemStack);
                        isSucceeded.set(true);
                        return;
                    }

                });

                if(isSucceeded.get()){
                    if(smeltingRecipeOpt.isPresent()){
                        inputStack.shrink(9);

                        int dataDamage = mouldStack.getDamageValue() + 1;
                        if(dataDamage < mouldStack.getMaxDamage()){
                            mouldStack.setDamageValue(dataDamage);
                        }else{
                            furnace.getItems().set(MOULD_SLOT[i],ItemStack.EMPTY);
                        }
                    }else{
                        inputStack.shrink(1);
                    }

                    cookingTotalTime = cookingTimer = 0;
                    furnace.setCookingTotalTime(i,cookingTotalTime);
                    furnace.setCookingTimer(i,cookingTimer);
                }
                continue;
            }

            if (cookingTimer>=0 && (inputStack.isEmpty()|| (isSmeltingRecipe&&!isMeltingConditionMet(smeltingRecipeOpt.get(),inputStack,mouldStack,temperature)))){
                cookingTimer--;
                furnace.setIsMeltingRecipe(i,false);
            }else{
                cookingTimer++;
            }
            furnace.setCookingTimer(i,cookingTimer);
        }

        if(furnace.getCraftingTotalTime()>0){
            if(temperature<furnace.getMinCraftingTemperature()){
                furnace.setCraftingTimer(0);
            }else{
                int craftingTimer = furnace.getCraftingTimer();
                furnace.setCraftingTimer(craftingTimer+1);
            }
        }

        furnace.setTemperature(temperature);
        furnace.setChanged();

    }

    public float reduceTemperature(float currentTemp){
        float delta;
        if(currentTemp <= 100){
            delta = 0.025F;
        }else if(currentTemp <= 500){
            delta = 0.05F;
        }else if(currentTemp <= 1000){
            delta = 0.1F;
        }else if(currentTemp <= 2000){
            delta = 0.066F;
        }else if(currentTemp <= 3000){
            delta = 0.0625F;
        }else if(currentTemp <= 4000){
            delta = 0.075F;
        }else if(currentTemp <= 5000){
            delta = 0.07F;
        }else{
            delta = 0.083F;
        }
        currentTemp -= delta * getCoreQuantity();
        return (currentTemp);
    }

    public void trySpawnLargeFurnace(LevelAccessor level){
        if(!hasBrickBlockAboveAndBelow(level,worldPosition)){
            return;
        }

        for (BlockPos neighbourBlockPos : getHorizontalNeighbourPosList(worldPosition)) {
            FindResult findResult = isCenterPos(level,neighbourBlockPos,true);
            if(findResult.isValid){
                furnaceCentrePos = neighbourBlockPos;
                initLayerUnderFurnacePosList();
                setShadow(level,false);

                findResult.brickPosSet.forEach(wallBlockPos-> wrapBrickBlock(level,wallBlockPos));

                findResult.corePosSet.forEach(coreBlockPos->{
                    final AtomicReference<BlockState> coreBlockState = new AtomicReference<>(level.getBlockState(coreBlockPos).setValue(ACTIVATED, true).setValue(LIT, false));
                    Utils.getRelativeHorizontalDirection(furnaceCentrePos,coreBlockPos).ifPresent(direction-> coreBlockState.set(coreBlockState.get().setValue(FACING,direction)));
                    level.setBlock(coreBlockPos, coreBlockState.get(),UPDATE_ALL);

                    if(coreBlockPos.equals(worldPosition)){
                        return;
                    }
                    shadowCores.add(coreBlockPos);

                    getBlockEntity(level,coreBlockPos).ifPresent(blockEntity->{
                        blockEntity.setShadow(level,true);
                        blockEntity.setRealFurnacePos(worldPosition);
                    });

                });
                dataAccess.set(CORE_QUANTITY,findResult.corePosSet.size());

                return;
            }
        }
    }

    public void deactivationCore(Level level,@Nullable BlockPos dropItemPos){
        if(level.isClientSide()){
            return;
        }

        if(isShadow(level)){
            getBlockEntity(level, realFurnacePos).ifPresent(blockEntity-> blockEntity.deactivationCore(level,worldPosition));
        }

        if(furnaceCentrePos==null){
            return;
        }

        if(dropItemPos!=null){
            for (int i = 0; i < this.getContainerSize(); i++) {
                dropItemStack(level, dropItemPos.getX(), dropItemPos.getY(), dropItemPos.getZ(), this.getItem(i));
            }
        }

        FindResult findResult = isCenterPos(level, furnaceCentrePos, false);
        findResult.brickPosSet.forEach(wallBlockPos-> unwrapBrickBlock(level,wallBlockPos));

        findResult.corePosSet.forEach(coreBlockPos->{
            level.setBlock(coreBlockPos,level.getBlockState(coreBlockPos).setValue(ACTIVATED,false),UPDATE_ALL);
        });

    }



    public void wrapBrickBlock(LevelAccessor level, BlockPos blockPos){
        level.setBlock(blockPos,
                brickWrapperBlock.defaultBlockState()
                        .setValue(FurnaceWrapperBlock.MATERIAL_TYPE,
                                FurnaceWrapperBlock.Type.getTypeByBlock(brickBlock)
                        )
                ,UPDATE_ALL);

        FurnaceWrapperBlock.setRealFurnacePos(level, blockPos, worldPosition);
    }

    public void unwrapBrickBlock(LevelAccessor level, BlockPos blockPos){
        level.setBlock(blockPos, brickBlock.defaultBlockState(), UPDATE_ALL);
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

    private boolean isBrickBlock(LevelAccessor level, BlockPos pos){
        return level.getBlockState(pos).is(brickBlock)||level.getBlockState(pos).is(brickWrapperBlock);
    }

    private boolean isCoreBlock(LevelAccessor level, BlockPos pos){
        return level.getBlockState(pos).is(coreBlock);
    }


    private FindResult isLegalPillar(LevelAccessor level, BlockPos pos){
        FindResult findResult1 = isBrickBlockPillar(level,pos);
        FindResult findResult2 = isCoreBlockPillar(level,pos);
        if(findResult1.isValid){
            return findResult1;
        }
        if(findResult2.isValid){
            return findResult2;
        }
        return FindResult.FAIL;
    }



    private FindResult isCenterPos(LevelAccessor level, BlockPos pos, boolean strictMode){

        FindResult totalResult = new FindResult();
        totalResult.setValid(true);

        for (BlockPos cornerBlockPos : Utils.getHorizontalCornerPosList(pos)) {
            FindResult findResult = isBrickBlockPillar(level,cornerBlockPos);
            if(strictMode&&!findResult.isValid){
                return FindResult.FAIL;
            }
            totalResult.merge(findResult);
        }

        for (BlockPos neighbourBlockPos : getHorizontalNeighbourPosList(pos)) {
            FindResult findResult = isLegalPillar(level,neighbourBlockPos);
            if(strictMode&&!findResult.isValid){
                return FindResult.FAIL;
            }
            totalResult.merge(findResult);
        }

        if(hasBrickBlockAboveAndBelow(level,pos)){
            totalResult.addBrick(pos.offset(0,-1,0));
            totalResult.addBrick(pos.offset(0,1,0));
            return totalResult;
        }

        if(strictMode){
            return FindResult.FAIL;
        }
        return totalResult;
    }

    //判断是否存在以pos为中心的1x3的材料方块(材料柱)
    private FindResult isBrickBlockPillar(LevelAccessor level, BlockPos pos){
        FindResult result = new FindResult();

        result.setValid(false);

        if((isBrickBlock(level,pos))&& hasBrickBlockAboveAndBelow(level,pos)){
            result.addBrick(pos);
            result.addBrick(pos.offset(0,-1,0));
            result.addBrick(pos.offset(0,1,0));
            result.setValid(true);
        }

        return result;
    }

    private FindResult isCoreBlockPillar(LevelAccessor level, BlockPos pos){
        FindResult result = new FindResult();

        result.setValid(false);

        if((isCoreBlock(level,pos))&& hasBrickBlockAboveAndBelow(level,pos)){
            result.addCore(pos);
            result.addBrick(pos.offset(0,-1,0));
            result.addBrick(pos.offset(0,1,0));
            result.setValid(true);
        }

        return result;
    }

    private boolean hasBrickBlockAboveAndBelow(LevelAccessor level, BlockPos pos){
        return (isBrickBlock(level,pos.offset(0,-1,0)))&&(isBrickBlock(level,pos.offset(0,1,0)));
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput input) {
        super.loadAdditional(input);
        Utils.loadBlockPos(input,"real_furnace", worldPosition).ifPresent(pos-> realFurnacePos = pos);
        Utils.loadBlockPos(input,"furnace_centre", worldPosition).ifPresent((pos)-> {
            furnaceCentrePos = pos;
            initLayerUnderFurnacePosList();
        });
        ContainerHelper.loadAllItems(input, this.items);

        Utils.loadBlockPosCollection(input, "shadow_cores", worldPosition, new HashSet<>(3)).ifPresent(set->{
            shadowCores = set;
        });
        dataAccess.set(CORE_QUANTITY,shadowCores.size()+1);

        input.getIntArray("container_data").ifPresent(array-> System.arraycopy(array, 0, dataArray, 0, array.length));
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput output) {
        super.saveAdditional(output);
        Utils.saveBlockPos(output,"real_furnace", worldPosition, realFurnacePos);
        Utils.saveBlockPos(output,"furnace_centre", worldPosition, furnaceCentrePos);
        ContainerHelper.saveAllItems(output, this.items);

        Utils.saveBlockPosCollection(output,"shadow_cores",worldPosition,shadowCores);

        output.putIntArray("container_data",dataArray);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.empty();
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.empty();
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> items) {

    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        LevelAccessor levelAccessor = inventory.player.level();
        if (isShadow(levelAccessor)) {
            Optional<FurnaceCoreBlockEntity> blockEntity = getBlockEntity(levelAccessor,realFurnacePos);
            if(blockEntity.isPresent()){
                return blockEntity.get().createMenu(containerId,inventory);
            }else{
                MiteBreakAll.LOGGER.error("Menu redirect failed");
                return new LargeFurnaceMenu(containerId,inventory,this,dataAccess);
            }
        }else{
            return new LargeFurnaceMenu(containerId,inventory,this,dataAccess);
        }

    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    public float getTemperature() {
        return (float) dataAccess.get(TEMPERATURE) / TEMPERATURE_MULTIPLIER;
    }

    public void setTemperature(float value) {
        dataAccess.set(TEMPERATURE, Mth.floor(Math.min(value, getTemperatureLimit()) * TEMPERATURE_MULTIPLIER));
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
        return dataAccess.get(CORE_QUANTITY);
    }

    public void setLitWithShadow(LevelAccessor level, BlockState mainState,boolean value){
        level.setBlock(worldPosition,mainState.setValue(LIT,value),UPDATE_ALL);
        shadowCores.forEach(pos->{
            BlockState state = level.getBlockState(pos);
            level.setBlock(pos,state.setValue(LIT,value),UPDATE_ALL);
        });
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

    public int getItemTotalCookTime(ServerLevel level, ItemStack input) {
        SingleRecipeInput singleRecipeInput = new SingleRecipeInput(input);
        var meltingRecipe = getMeltingRecipe(level, singleRecipeInput);
        if(meltingRecipe.isPresent()){
            return meltingRecipe.get().getFinalMeltTicks(brickBlock,getCoreQuantity());
        }

        var smeltingRecipe = getSmeltingRecipe(level, singleRecipeInput);

        return smeltingRecipe.map(AbstractCookingRecipe::cookingTime).orElse(200);
    }



    public static Optional<MeltingRecipe> getMeltingRecipe(ServerLevel level, SingleRecipeInput singleRecipeInput){
        return QUICK_CHECK_MELTING.getRecipeFor(singleRecipeInput, level).map(RecipeHolder::value);
    }

    public static Optional<SmeltingRecipe> getSmeltingRecipe(ServerLevel level, SingleRecipeInput singleRecipeInput){
        return QUICK_CHECK_SMELTING.getRecipeFor(singleRecipeInput, level).map(RecipeHolder::value);
    }

    public static int getBurnDuration(FuelValues fuelValues, ItemStack stack) {
        return stack.getBurnTime(RecipeType.SMELTING, fuelValues);
    }

    public Optional<ItemStack> getBurnOutput(ServerLevel level, ItemStack input){
        SingleRecipeInput singleRecipeInput = new SingleRecipeInput(input);
        var meltingRecipeOpt = getMeltingRecipe(level,singleRecipeInput);
        if(meltingRecipeOpt.isPresent()){
            return Optional.of(meltingRecipeOpt.get().getResult());
        }
        var smeltingRecipeOpt = getSmeltingRecipe(level, singleRecipeInput);
        return smeltingRecipeOpt.map(recipe -> recipe.assemble(singleRecipeInput, level.registryAccess()));

    }

    public void setIsMeltingRecipe(int index, boolean value){
        dataAccess.set(IS_MELTING_RECIPE[index],value?1:0);
    }

    public void setMeltingOutputName(int index, String value){
        dataAccess.set(MELTING_OUTPUT_NAME[index],value.hashCode());
    }


    public int getTemperatureLimit(){
        return dataAccess.get(TEMPERATURE_LIMIT) ;
    }

    private void setTemperatureLimit(int value){
        dataAccess.set(TEMPERATURE_LIMIT, value);
    }

    private static boolean isMeltingConditionMet(@Nonnull MeltingRecipe meltingRecipe, ItemStack inputStack, ItemStack mouldStack, float temperature){
        return  (isMould(mouldStack) && inputStack.getCount()>=9 && temperature >= meltingRecipe.getMeltingPoint());
    }

    private boolean updateTickCounter(){
        int counter = dataAccess.get(TICK_COUNTER);
        if(counter%20==0){
            dataAccess.set(TICK_COUNTER,0);
            return true;
        }
        return false;
    }

    public List<BlockPos> getLayerUnderFurnacePosList(){
        return layerUnderFurnacePosList;
    }

    public void setLavaBlockCount(int value){
        dataAccess.set(LAVA_BLOCK_COUNT,value);
    }

    public int getLavaBlockCount(){
        return dataAccess.get(LAVA_BLOCK_COUNT);
    }

    private void initLayerUnderFurnacePosList(){
        BlockPos bottomCentrePos = furnaceCentrePos.offset(0,-2,0);
        layerUnderFurnacePosList.addAll(Arrays.asList(Utils.getHorizontalCornerPosList(bottomCentrePos)));
        layerUnderFurnacePosList.addAll(Arrays.asList(Utils.getHorizontalNeighbourPosList(bottomCentrePos)));
    }

    public int getCraftingTimer(){
        return dataAccess.get(CRAFTING_TIMER);
    }

    public void setCraftingTimer(int value){
        dataAccess.set(CRAFTING_TIMER,value);
    }

    public int getCraftingTotalTime(){
        return dataAccess.get(CRAFTING_TOTAL_TIME);
    }

    public void setCraftingTotalTime(int value){
        dataAccess.set(CRAFTING_TOTAL_TIME,value);
    }

    public int getMinCraftingTemperature(){
        return dataAccess.get(MIN_CRAFTING_TEMPERATURE);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        super.setItem(slot, stack);
        slotChangeListeners.forEach(consumer-> consumer.accept(slot));
    }

    public void addSlotListener(Consumer<Integer> consumer){
        slotChangeListeners.add(consumer);
    }

    public void removeSlotListener(Consumer<Integer> consumer){
        slotChangeListeners.remove(consumer);
    }

    public static class FindResult{
        public static final FindResult FAIL = new FindResult();
        static {
            FAIL.setValid(false);
        }
        private final Set<BlockPos> corePosSet = new HashSet<>(4);
        private final Set<BlockPos> brickPosSet = new HashSet<>(22);

        public void setValid(boolean valid) {
            isValid = valid;
        }

        boolean isValid = true;


        FindResult(){}

        public void addCore(BlockPos blockPos){
            corePosSet.add(blockPos);
        }

        public void addBrick(BlockPos blockPos){
            brickPosSet.add(blockPos);
        }

        public void merge(FindResult findResult){
            this.corePosSet.addAll(findResult.corePosSet);
            this.brickPosSet.addAll(findResult.brickPosSet);
            if(!isValid){
                return;
            }
            this.isValid = findResult.isValid;
        }
    }
}
