package hhsixhhwkhxh.mite.menu;

import hhsixhhwkhxh.mite.slot.CraftingResultSlot;
import hhsixhhwkhxh.mite.slot.LargeFurnaceFuelSlot;
import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class LargeFurnaceMenu extends RecipeBookMenu {
    public static final int[] INGREDIENT_SLOT = new int[4];
    public static final int[] FUEL_SLOT = new int[4];
    public static final int[] MOULD_SLOT = new int[4];
    public static final int[] INGOT_RESULT_SLOT = new int[4];

    public static final int[] CRAFT_INPUT_SLOT = new int[9];

    public static final int CRAFT_RESULT_SLOT;
    public static int SLOT_COUNT = 0;
    public static final int DATA_COUNT = 44;

    final Container container;
    private final ContainerData data;
    protected final Level level;
    private final RecipeType<? extends AbstractCookingRecipe> recipeType = RecipeType.SMELTING;
    private final RecipePropertySet acceptedInputs;
    private final RecipeBookType recipeBookType;

    protected final CraftingContainer craftSlots;
    private final CraftingResultSlot craftingResultSlot;

    static {
        assignSlotIndex(INGREDIENT_SLOT);
        assignSlotIndex(FUEL_SLOT);
        assignSlotIndex(MOULD_SLOT);
        assignSlotIndex(INGOT_RESULT_SLOT);
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

        for (int index = 0; index < INGREDIENT_SLOT.length; index ++) {
            this.addSlot(new Slot(container, INGREDIENT_SLOT[index], 72 + 20*index, 14));
        }

        for (int index = 0; index < FUEL_SLOT.length; index ++) {
            this.addSlot(new LargeFurnaceFuelSlot(this, container, FUEL_SLOT[index], 20, 14 + 18*index));
        }

        for (int index = 0; index < MOULD_SLOT.length; index ++) {
            this.addSlot(new Slot(container, MOULD_SLOT[index], 72 + 20*index, 32));
        }

        for (int index = 0; index < INGOT_RESULT_SLOT.length; index ++) {
            this.addSlot(new FurnaceResultSlot(inventory.player, container, INGOT_RESULT_SLOT[index], 72 + 20*index, 68));
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

//    public Slot getResultSlot() {
//        return this.slots.get(RESULT_SLOT);
//    }

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
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 2) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (this.canSmelt(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }


    protected boolean canSmelt(ItemStack stack) {
        return this.acceptedInputs.test(stack);
    }

    public boolean isFuel(ItemStack stack) {
        return stack.getBurnTime(this.recipeType, this.level.fuelValues()) > 0;
    }

//    public float getBurnProgress() {
//        int i = this.data.get(2);
//        int j = this.data.get(3);
//        return j != 0 && i != 0 ? Mth.clamp((float)i / j, 0.0F, 1.0F) : 0.0F;
//    }
//
//    public float getLitProgress() {
//        int i = this.data.get(1);
//        if (i == 0) {
//            i = 200;
//        }
//
//        return Mth.clamp((float)this.data.get(INGREDIENT_SLOT) / i, 0.0F, 1.0F);
//    }
//
//    public boolean isLit() {
//        return this.data.get(INGREDIENT_SLOT) > 0;
//    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return this.recipeBookType;
    }

    @Override
    public PostPlaceAction handlePlacement(
        boolean useMaxItems, boolean isCreative, RecipeHolder<?> recipe, final ServerLevel level, Inventory playerInventory
    ) {
        final List<Slot> list = List.of(this.getSlot(0), this.getSlot(2));
        return ServerPlaceRecipe.placeRecipe(new ServerPlaceRecipe.CraftingMenuAccess<AbstractCookingRecipe>() {
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
