package hhsixhhwkhxh.mite.menu;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import hhsixhhwkhxh.mite.Utils;
import hhsixhhwkhxh.mite.custom.MeltingCastRecord;
import hhsixhhwkhxh.mite.slot.CraftingResultSlot;
import hhsixhhwkhxh.mite.slot.LargeFurnaceFuelSlot;
import hhsixhhwkhxh.mite.slot.LockableSlot;
import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static hhsixhhwkhxh.mite.blockentity.FurnaceCoreBlockEntity.*;

public class LargeFurnaceMenu extends RecipeBookMenu {
    public static final int[] INGREDIENT_SLOT = new int[4];
    public static final int[] FUEL_SLOT = new int[4];
    public static final int[] MOULD_SLOT = new int[4];
    public static final int[] BURN_RESULT_SLOT = new int[4];

    public static final int[] CRAFT_INPUT_SLOT = new int[9];

    public static final int CRAFT_RESULT_SLOT;

    public static int SLOT_COUNT = 0;
    public static final int DATA_COUNT = 26;

    final Container container;
    private final ContainerData data;
    protected final Level level;
    private final RecipeType<? extends AbstractCookingRecipe> recipeType = RecipeType.SMELTING;
    private final RecipePropertySet acceptedInputs;
    private final RecipeBookType recipeBookType;

    protected final CraftingContainer craftSlots;
    private final CraftingResultSlot craftingResultSlot;

    private boolean isClientSide = false;
    private final Multimap<Integer,LockableSlot> lazyLockSlotMap = ArrayListMultimap.create();

    private final List<Predicate<ItemStack>> itemAssignMethodlist = List.of(
            (itemStack)->{
                if(!this.canSmelt(itemStack)&&!hasSmeltingRecipe(itemStack.getItem())){return false;}
                return this.moveItemStackTo(itemStack, INGREDIENT_SLOT[0], INGREDIENT_SLOT[3] + 1, false);
            },
            (itemStack)->{
                if (!this.isFuel(itemStack)) {return false;}
                return this.moveItemStackTo(itemStack, FUEL_SLOT[0], FUEL_SLOT[3] + 1, false);
            },
            (itemStack)->{
                if(!Utils.isMould(itemStack)){return false;}
                return this.moveItemStackTo(itemStack, MOULD_SLOT[0], MOULD_SLOT[3] + 1, false);
            },
            (itemStack)->{
                return this.moveItemStackTo(itemStack, CRAFT_INPUT_SLOT[0], CRAFT_INPUT_SLOT[8] + 1, false);
            }
    );

    static {
        assignSlotIndex(INGREDIENT_SLOT);
        assignSlotIndex(FUEL_SLOT);
        assignSlotIndex(MOULD_SLOT);
        assignSlotIndex(BURN_RESULT_SLOT);
        assignSlotIndex(CRAFT_INPUT_SLOT);

        CRAFT_RESULT_SLOT = assignSingleSlotIndex();
    }

    private static void assignSlotIndex(int[] array){
        for (int i = 0; i < array.length; i++) {
            array[i] = SLOT_COUNT++;
        }
    }

    private static int assignSingleSlotIndex(){
        return SLOT_COUNT++;
    }

    protected LargeFurnaceMenu(
        int containerId,
        Inventory inventory
    ) {

        this(containerId, inventory, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(DATA_COUNT));

    }

    public LargeFurnaceMenu(
            int containerId,
            Inventory inventory,
            Container container,
            ContainerData data
    ) {
        super(ModMenuTypes.LARGE_FURNACE_MENU.get(), containerId);
        this.recipeBookType = RecipeBookType.FURNACE;
        checkContainerSize(container, 3);
        checkContainerDataCount(data, 4);
        this.container = container;
        this.data = data;
        this.level = inventory.player.level();
        this.acceptedInputs = this.level.recipeAccess().propertySet(RecipePropertySet.FURNACE_INPUT);

        int coreQuantity = getCoreQuantity();
        isClientSide = !(inventory.player instanceof ServerPlayer);

        for (int index = 0; index < INGREDIENT_SLOT.length; index ++) {
            LockableSlot slot = new LockableSlot(container, INGREDIENT_SLOT[index], 72 + 20*index, 14);
            addLockableSlot(index,slot,coreQuantity);
        }

        for (int index = 0; index < FUEL_SLOT.length; index ++) {
            LockableSlot slot = new LargeFurnaceFuelSlot(this, container, FUEL_SLOT[index], 20, 14 + 18*index);
            addLockableSlot(index,slot,coreQuantity);
        }

        for (int index = 0; index < MOULD_SLOT.length; index ++) {
            LockableSlot slot = new LockableSlot(container, MOULD_SLOT[index], 72 + 20*index, 32);
            addLockableSlot(index,slot,coreQuantity);
        }

        for (int index = 0; index < BURN_RESULT_SLOT.length; index ++) {
            this.addSlot(new FurnaceResultSlot(inventory.player, container, BURN_RESULT_SLOT[index], 72 + 20*index, 68));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.addSlot(new Slot(this.container, CRAFT_INPUT_SLOT[j + i * 3], 20 + j * 18, 95 + i * 18));
            }
        }

        this.craftSlots = new TransientCraftingContainer(this, 3, 3);

        craftingResultSlot =new CraftingResultSlot(inventory.player, craftSlots, container, CRAFT_RESULT_SLOT,  98, 113);
        this.addSlot(craftingResultSlot);


        this.addStandardInventorySlots(inventory, 6, 155);
        this.addDataSlots(data);
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedItemContents stackedItemContents) {
        if (this.container instanceof StackedContentsCompatible) {
            ((StackedContentsCompatible)this.container).fillStackedContents(stackedItemContents);
        }
    }

    private void addLockableSlot(int index,LockableSlot slot,int coreQuantity){
        this.addSlot(slot);
        if(isClientSide){
            lazyLockSlotMap.put(index,slot);
        }else{
            slot.setActive(index<coreQuantity);
        }
    }


    //客户端 设置槽位锁定状态
    public boolean tryInitLockableSlot(){
        int coreQuantity = getCoreQuantity();
        if(coreQuantity==0){
            return false;
        }
        lazyLockSlotMap.forEach((index,lockableSlot)->{
            lockableSlot.setActive(index<coreQuantity);
        });
        lazyLockSlotMap.clear();
        return true;
    }


    /**
     * Determines whether supplied player can use this container
     */
    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player inventory and the other inventory(s).
     */
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if(!slot.hasItem()){
            return itemstack;
        }

        itemstack = slot.getItem();
        ItemStack originItemstack = itemstack.copy();

        if(index < SLOT_COUNT){//大熔炉槽位
            if(!this.moveItemStackTo(itemstack, SLOT_COUNT, SLOT_COUNT + 36, true)){
                return ItemStack.EMPTY;
            }

        }else{//背包
            for (Predicate<ItemStack> itemStackPredicate : itemAssignMethodlist) {
                itemStackPredicate.test(itemstack);
                if(itemstack.getCount() == 0){
                    slot.setByPlayer(ItemStack.EMPTY);
                    return originItemstack;
                }
            }

            if (originItemstack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }


        }
        slot.setChanged();
        return originItemstack;

    }


    protected boolean canSmelt(ItemStack stack) {
        return this.acceptedInputs.test(stack);
    }

    public static boolean hasSmeltingRecipe(Item item){
        return MeltingCastRecord.MeltingCastMap.containsKey(item);
    }

    public boolean isFuel(ItemStack stack) {

        return stack.getBurnTime(this.recipeType, this.level.fuelValues()) > 0;
    }

    public float getTemperatureProgress(){
        return Mth.clamp(getTemperature() / getTemperatureLimit(), 0.0F, 1.0F);
    }

    public float getTemperature(){
        return (float) data.get(TEMPERATURE) / TEMPERATURE_MULTIPLIER ;
    }

    public int getTemperatureLimit(){
        return data.get(TEMPERATURE_LIMIT) ;
    }

    public int getCoreQuantity(){
        return data.get(CORE_QUANTITY);
    }

    public float getBurnProgress(int index) {
        int cookingTimer = this.data.get(COOKING_TIMER[index]);
        int cookingTotalTime = this.data.get(COOKING_TOTAL_TIME[index]);
        return cookingTotalTime != 0 && cookingTimer != 0 ? Mth.clamp((float)cookingTimer / cookingTotalTime, 0.0F, 1.0F) : 0.0F;
    }

    public boolean isSmeltingRecipe(int index){
        return (data.get(IS_SMELTING_RECIPE[index])!=0);
    }

    public int getSmeltingOutputName(int index){
        return data.get(SMELTING_OUTPUT_NAME[index]);
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return this.recipeBookType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PostPlaceAction handlePlacement(
        boolean useMaxItems, boolean isCreative, RecipeHolder<?> recipe, final ServerLevel level, Inventory playerInventory
    ) {
        final List<Slot> list = List.of(this.getSlot(0), this.getSlot(2));
        return ServerPlaceRecipe.placeRecipe(new ServerPlaceRecipe.CraftingMenuAccess<>() {
            @Override
            public void fillCraftSlotsStackedContents(StackedItemContents stackedItemContents) {
                LargeFurnaceMenu.this.fillCraftSlotsStackedContents(stackedItemContents);
            }

            @Override
            public void clearCraftingContent() {
                list.forEach(p_362814_ -> p_362814_.set(ItemStack.EMPTY));
            }

            @Override
            public boolean recipeMatches(RecipeHolder<AbstractCookingRecipe> p_recipe) {
                return p_recipe.value().matches(new SingleRecipeInput(LargeFurnaceMenu.this.container.getItem(0)), level);
            }
        }, 1, 1, List.of(this.getSlot(0)), list, playerInventory, (RecipeHolder<AbstractCookingRecipe>)recipe, useMaxItems, isCreative);
    }
}
