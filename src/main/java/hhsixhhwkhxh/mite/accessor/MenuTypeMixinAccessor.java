package hhsixhhwkhxh.mite.accessor;

import hhsixhhwkhxh.mite.menu.MiteCraftingMenu;
import net.minecraft.world.inventory.MenuType;


public interface MenuTypeMixinAccessor {
    public MenuType<MiteCraftingMenu> getMiteCraftingMenu();
}
