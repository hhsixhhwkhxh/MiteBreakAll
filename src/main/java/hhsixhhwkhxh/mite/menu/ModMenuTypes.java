package hhsixhhwkhxh.mite.menu;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, MiteBreakAll.MODID);

    public static Supplier<MenuType<MiteCraftingMenu>> MITE_CRAFTING_MENU = MENUS.register("mite_crafting_menu",()->{return new MenuType<>(MiteCraftingMenu::new, FeatureFlags.DEFAULT_FLAGS);});
    public static Supplier<MenuType<MiteAnvilMenu>> MITE_ANVIL_MENU = MENUS.register("mite_anvil_menu",()->{return new MenuType<>(MiteAnvilMenu::new, FeatureFlags.DEFAULT_FLAGS);});


    public static void register(IEventBus bus){
        MENUS.register(bus);
    }
}
