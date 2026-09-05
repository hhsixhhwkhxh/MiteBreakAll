package hhsixhhwkhxh.mite.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class LockableSlot extends Slot {
    public void setActive(boolean active) {
        isActive = active;
    }

    private boolean isActive = true;
    public LockableSlot(Container container, int slot, int x, int y) {
        super(container, slot, x, y);
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if(!isActive){
            return false;
        }
        return super.mayPlace(stack);
    }
}
