package hhsixhhwkhxh.mite;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultSlot;

import java.util.function.Consumer;

public class ExtendedResultSlot extends ResultSlot {
    private boolean mayPickup = true;
    Consumer<ExtendedResultSlot> clickListener;
    public ExtendedResultSlot(Player player, CraftingContainer craftSlots, Container container, int slot, int xPosition, int yPosition) {
        super(player, craftSlots, container, slot, xPosition, yPosition);
    }

    public void setMayPickup(boolean mayPickup){
        this.mayPickup = mayPickup;
    }

    public void setOnClickListener(Consumer<ExtendedResultSlot> clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public boolean mayPickup(Player player) {
        if(!mayPickup&&clickListener!=null){
            clickListener.accept(this);
        }
        return mayPickup;
    }
}
