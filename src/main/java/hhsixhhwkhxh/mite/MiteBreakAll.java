package hhsixhhwkhxh.mite;

import hhsixhhwkhxh.mite.accessor.PlayerMixinAccessor;
import hhsixhhwkhxh.mite.custom.ModFoodData;
import hhsixhhwkhxh.mite.custom.PlayerWaterData;
import hhsixhhwkhxh.mite.datacomponent.ModDataComponents;
import hhsixhhwkhxh.mite.datacomponent.Moisture;
import hhsixhhwkhxh.mite.item.ModCreativeModeTabs;
import hhsixhhwkhxh.mite.packet.ClientboundSetWaterLevelPacket;
import hhsixhhwkhxh.mite.packet.ClientboundSetVitalStatMaxValuePacket;
import hhsixhhwkhxh.mite.screen.MiteCraftingScreen;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.blockentity.ModBlockEntities;
import hhsixhhwkhxh.mite.item.ModItems;
import hhsixhhwkhxh.mite.menu.ModMenuTypes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
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

        ModDataComponents.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (MiteBreakAll) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::onRegisterMenuScreens);
        modEventBus.addListener(this::registerPayloads);

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

    private void registerPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playBidirectional(
                ClientboundSetWaterLevelPacket.TYPE,
                ClientboundSetWaterLevelPacket.STREAM_CODEC,
                (packet,context)->{}
        );
        registrar.playBidirectional(
                ClientboundSetVitalStatMaxValuePacket.TYPE,
                ClientboundSetVitalStatMaxValuePacket.STREAM_CODEC,
                (packet,context)->{}
        );
    }

    @SubscribeEvent
    public void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
        var player = event.getEntity();
        if(!(player instanceof Player)){
            return;
        }
        var stack = event.getItem();

        if(!stack.has(DataComponents.FOOD)) {
            return;
        }

        Moisture moisture;
        if(!stack.getComponents().has(ModDataComponents.MOISTURE)||(moisture=stack.getComponents().get(ModDataComponents.MOISTURE))==null){
            return;
        }

        ((PlayerMixinAccessor) player).getWaterData().addWaterLevel(moisture.value());
    }



    @SubscribeEvent
    public void onLevelChange(PlayerXpEvent.LevelChange event){
        var player = event.getEntity();
        int vitalStatMaxValue = Utils.getVitalStatMaxValue(player.experienceLevel+event.getLevels());
        
        PlayerWaterData waterData = ((PlayerMixinAccessor)player).getWaterData();

        if(waterData.getMaxWaterLevel()!=vitalStatMaxValue){
            Utils.updateVitalStat(event.getEntity());
        }
    }

    @SubscribeEvent
    public void onPlayerReborn(PlayerEvent.PlayerRespawnEvent event){
        Utils.updateVitalStat(event.getEntity());
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event){
        Utils.updateVitalStat(event.getEntity());
    }
}
