package hhsixhhwkhxh.mite;

import hhsixhhwkhxh.mite.screen.MiteCraftingScreen;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.blockentity.ModBlockEntities;
import hhsixhhwkhxh.mite.item.ModItems;
import hhsixhhwkhxh.mite.menu.ModMenuTypes;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MiteBreakAll.MODID)
public class MiteBreakAll {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "mite_break_all";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public MiteBreakAll(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (MiteBreakAll) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::onRegisterMenuScreens);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey()==CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(ModItems.WILD_APPLE);
            event.accept(ModItems.STRAWBERRIES);
            event.accept(ModBlocks.STRAWBERRY_BUSH);
        } else if (event.getTabKey()==CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.SILVER_ORE);
        } else if(event.getTabKey()==CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(ModBlocks.FLINT_CRAFTING_TABLE);
        } else if(event.getTabKey()==CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.FLINT_SHARD);
            event.accept(ModItems.OBSIDIAN_SHARD);
            event.accept(ModItems.DIAMOND_SHARD);
            event.accept(ModItems.EMERALD_SHARD);
            event.accept(ModItems.GLASS_SHARD);
            event.accept(ModItems.QUARTZ_SHARD);

            event.accept(ModItems.ADAMANTIUM_NUGGET);
            event.accept(ModItems.ANCIENT_METAL_NUGGET);
            event.accept(ModItems.HARD_NUGGET);
            event.accept(ModItems.MERCURY_NUGGET);
            event.accept(ModItems.MITHRIL_NUGGET);
            event.accept(ModItems.SILVER_NUGGET);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    private void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        // 绑定 MenuType → Screen构造器
        event.register(
                ModMenuTypes.MITE_CRAFTING_MENU.get(),
                MiteCraftingScreen::new
        );
    }

    
}
