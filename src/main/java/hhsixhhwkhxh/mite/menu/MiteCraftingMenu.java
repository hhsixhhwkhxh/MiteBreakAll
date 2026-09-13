package hhsixhhwkhxh.mite.menu;


import hhsixhhwkhxh.mite.slot.CraftingResultSlot;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.blockentity.MiteCraftingTableBlockEntity;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class MiteCraftingMenu extends AbstractCraftingMenu {
    private final ContainerLevelAccess access;
    private final Player player;
    private boolean placingRecipe;
    private final CraftingResultSlot craftingResultSlot;
    private final ContainerData data;

    public static final int CRAFTING_TIMER = MiteCraftingTableBlockEntity.CRAFTING_TIMER;
    public static final int CRAFTING_TOTAL_TIME = MiteCraftingTableBlockEntity.CRAFTING_TOTAL_TIME;


    public MiteCraftingMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL,new SimpleContainerData(2),null);
    }

    public MiteCraftingMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access, ContainerData data,@Nullable MiteCraftingTableBlockEntity blockEntity) {
        super(ModMenuTypes.MITE_CRAFTING_MENU.get(), containerId, 3, 3);

        this.access = access;
        this.player = playerInventory.player;
        //this.addResultSlot(this.player, 124, 35);
        craftingResultSlot =new CraftingResultSlot(player, this.craftSlots, this.resultSlots, 0,  124, 35);
        craftingResultSlot.setMayPickup(false);
        this.addSlot(craftingResultSlot);
        this.addCraftingGridSlots(30, 17);
        this.addStandardInventorySlots(playerInventory, 8, 84);

        this.data = data;
        this.addDataSlot(DataSlot.forContainer(data, CRAFTING_TIMER));
        this.addDataSlot(DataSlot.forContainer(data, CRAFTING_TOTAL_TIME));

        if(this.player instanceof ServerPlayer serverPlayer){
            craftingResultSlot.setOnAttemptPickup((mayPickUp)->{
                //给super.clicked开绿灯
                if(mayPickUp){
                    return true;
                }

                if(getCraftingProgress()!=0){
                    return false;
                }else{
                    setCraftingTimer(0);
                    setCraftingTotalTime(-getCraftTotalTime());
                }
                return false;
            });
        }

        if(blockEntity!=null){
            blockEntity.setOnCraftFinishedListener(this::onCraftFinished);
        }

    }

    public void onCraftFinished(){
        ItemStack itemStack = this.quickMoveStack(player,0);

        if(itemStack == ItemStack.EMPTY){
            craftingResultSlot.setMayPickup(true);
            super.clicked(0,0,ClickType.THROW,player);
            craftingResultSlot.setMayPickup(false);
            return;
        }
        return;
    }


    protected void slotChangedCraftingGrid(
            ServerLevel level,

            @Nullable RecipeHolder<CraftingRecipe> recipe
    ) {
        CraftingInput craftinginput = craftSlots.asCraftInput();
        ServerPlayer serverplayer = (ServerPlayer)player;
        ItemStack itemstack = ItemStack.EMPTY;
        Optional<RecipeHolder<CraftingRecipe>> optional = level.getServer()
                .getRecipeManager()
                .getRecipeFor(RecipeType.CRAFTING, craftinginput, level, recipe);
        if (optional.isPresent()) {
            RecipeHolder<CraftingRecipe> recipeholder = optional.get();
            CraftingRecipe craftingrecipe = recipeholder.value();
            if (resultSlots.setRecipeUsed(serverplayer, recipeholder)) {
                ItemStack itemstack1 = craftingrecipe.assemble(craftinginput, level.registryAccess());
                if (itemstack1.isItemEnabled(level.enabledFeatures())) {
                    itemstack = itemstack1;
                }
            }
        }

        resultSlots.setItem(0, itemstack);
        if(!itemstack.isEmpty()){
            setCraftingTotalTime(-40);
        }else{
            setCraftingTimer(0);
            setCraftingTotalTime(0);
        }

        this.setRemoteSlot(0, itemstack);
        serverplayer.connection.send(new ClientboundContainerSetSlotPacket(containerId, incrementStateId(), 0, itemstack));

    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void slotsChanged(Container inventory) {
        if (!this.placingRecipe) {
            this.access.execute((p_379187_, p_379188_) -> {
                if (p_379187_ instanceof ServerLevel serverlevel) {
                    slotChangedCraftingGrid(serverlevel, null);
                }
            });
        }
    }

    @Override
    public void beginPlacingRecipe() {
        this.placingRecipe = true;
    }

    @Override
    public void finishPlacingRecipe(ServerLevel level, RecipeHolder<CraftingRecipe> recipe) {
        this.placingRecipe = false;
        slotChangedCraftingGrid(level, recipe);
    }

    /**
     * Called when the container is closed.
     */
    @Override
    public void removed(Player player) {
        super.removed(player);
        this.access.execute((p_39371_, p_39372_) -> this.clearContainer(player, this.craftSlots));

        setCraftingTimer(0);
        setCraftingTotalTime(0);
    }

    /**
     * Determines whether supplied player can use this container
     */
    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.FLINT_CRAFTING_TABLE.get());
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
            if (index == 0) {
                itemstack1.getItem().onCraftedBy(itemstack1, player);
                if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index >= 10 && index < 46) {
                if (!this.moveItemStackTo(itemstack1, 1, 10, false)) {
                    if (index < 37) {
                        if (!this.moveItemStackTo(itemstack1, 37, 46, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 10, 46, false)) {
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
            if (index == 0) {
                player.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in is null for the initial slot that was double-clicked.
     */
    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }

    @Override
    public Slot getResultSlot() {
        return this.slots.get(0);
    }

    @Override
    public List<Slot> getInputGridSlots() {
        return this.slots.subList(1, 10);
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    @Override
    protected Player owner() {
        return this.player;
    }


    public float getCraftingProgress(){
        int craftTime = this.data.get(CRAFTING_TIMER);
        int craftTimeTotal = this.data.get(CRAFTING_TOTAL_TIME);
        return craftTimeTotal != 0 && craftTime != 0 ? Mth.clamp((float)craftTime / craftTimeTotal, 0.0F, 1.0F) : 0.0F;
    }


    public int getCraftingTimer(){
        return data.get(CRAFTING_TIMER);
    }

    public void setCraftingTimer(int value){
        data.set(CRAFTING_TIMER, value);
    }

    public void setCraftingTotalTime(int value){
        data.set(CRAFTING_TOTAL_TIME, value);
    }

    public int getCraftTotalTime(){
        return data.get(CRAFTING_TOTAL_TIME);
    }
}
