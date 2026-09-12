package hhsixhhwkhxh.mite.slot;

import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class CraftingResultSlot extends ResultSlot {
    private boolean mayPickup = true;
    Function<Boolean,Boolean> onAttemptPickup;
    Consumer<Player> onSlotTake = null;

    public CraftingResultSlot(Player player, CraftingContainer craftSlots, Container container, int slot, int xPosition, int yPosition) {
        super(player, craftSlots, container, slot, xPosition, yPosition);
    }

    public void setMayPickup(boolean mayPickup) {
        this.mayPickup = mayPickup;
    }

    public void setOnAttemptPickup(Function<Boolean,Boolean> onAttemptPickup) {
        this.onAttemptPickup = onAttemptPickup;
    }

    public void setOnSlotTake(Consumer<Player> onSlotTake) {
        this.onSlotTake = onSlotTake;
    }

    @Override
    public boolean mayPickup(Player player) {
        if (onAttemptPickup != null) {
            return onAttemptPickup.apply(mayPickup);
        }
        return mayPickup;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        if (onSlotTake != null) {
            onSlotTake.accept(player);
            return;
        }
        super.onTake(player, stack);
    }

}
