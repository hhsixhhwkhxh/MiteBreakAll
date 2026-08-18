package hhsixhhwkhxh.mite.item;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.ModBlocks;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MiteBreakAll.MODID);

    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> MITE = CREATIVE_MODE_TABS.register("mite",()->{
        return CreativeModeTab.builder().icon(()->{
            return new ItemStack((ItemLike) ModItems.FLINT_SHARD);
        }).title(Component.translatable("itemGroup.mite")).displayItems((itemDisplayParameters,output)->{
            output.accept(ModItems.WILD_APPLE);
            output.accept(ModItems.STRAWBERRIES);
            output.accept(ModBlocks.STRAWBERRY_BUSH);
            output.accept(ModBlocks.SILVER_ORE);
            output.accept(ModBlocks.HARD_ORE);
            output.accept(ModBlocks.MERCURY_ORE);
            output.accept(ModBlocks.MITHRIL_ORE);
            output.accept(ModBlocks.ADAMANTIUM_ORE);
            output.accept(ModBlocks.TIN_ORE);
            output.accept(ModBlocks.FLINT_CRAFTING_TABLE);
            output.accept(ModItems.FLINT_SHARD);
            output.accept(ModItems.OBSIDIAN_SHARD);
            output.accept(ModItems.DIAMOND_SHARD);
            output.accept(ModItems.EMERALD_SHARD);
            output.accept(ModItems.GLASS_SHARD);
            output.accept(ModItems.QUARTZ_SHARD);

            output.accept(ModItems.ADAMANTIUM_NUGGET);
            output.accept(ModItems.ANCIENT_METAL_NUGGET);
            output.accept(ModItems.HARD_NUGGET);
            output.accept(ModItems.MERCURY_NUGGET);
            output.accept(ModItems.MITHRIL_NUGGET);
            output.accept(ModItems.SILVER_NUGGET);
            output.accept(ModItems.COPPER_NUGGET);

            output.accept(ModItems.MESH_LEATHER);
            output.accept(ModItems.MESH_STRING);
            output.accept(ModBlocks.SIEVE);

            output.accept(ModItems.ADAMANTIUM_INGOT);
            output.accept(ModItems.ANCIENT_METAL_INGOT);
            output.accept(ModItems.HARD_INGOT);
            output.accept(ModItems.MERCURY_INGOT);
            output.accept(ModItems.MITHRIL_INGOT);
            output.accept(ModItems.SILVER_INGOT);
            output.accept(ModItems.TIN_INGOT);

            output.accept(ModItems.ADAMANTIUM_HELMET);
            output.accept(ModItems.ADAMANTIUM_CHESTPLATE);
            output.accept(ModItems.ADAMANTIUM_LEGGINGS);
            output.accept(ModItems.ADAMANTIUM_BOOTS);

            output.accept(ModItems.ANCIENT_METAL_HELMET);
            output.accept(ModItems.ANCIENT_METAL_CHESTPLATE);
            output.accept(ModItems.ANCIENT_METAL_LEGGINGS);
            output.accept(ModItems.ANCIENT_METAL_BOOTS);

            output.accept(ModItems.HARD_HELMET);
            output.accept(ModItems.HARD_CHESTPLATE);
            output.accept(ModItems.HARD_LEGGINGS);
            output.accept(ModItems.HARD_BOOTS);

            output.accept(ModItems.MITHRIL_HELMET);
            output.accept(ModItems.MITHRIL_CHESTPLATE);
            output.accept(ModItems.MITHRIL_LEGGINGS);
            output.accept(ModItems.MITHRIL_BOOTS);

            output.accept(ModItems.RUSTED_IRON_HELMET);
            output.accept(ModItems.RUSTED_IRON_CHESTPLATE);
            output.accept(ModItems.RUSTED_IRON_LEGGINGS);
            output.accept(ModItems.RUSTED_IRON_BOOTS);

            output.accept(ModItems.SILVER_HELMET);
            output.accept(ModItems.SILVER_CHESTPLATE);
            output.accept(ModItems.SILVER_LEGGINGS);
            output.accept(ModItems.SILVER_BOOTS);

            output.accept(ModItems.BRONZE_HELMET);
            output.accept(ModItems.BRONZE_CHESTPLATE);
            output.accept(ModItems.BRONZE_LEGGINGS);
            output.accept(ModItems.BRONZE_BOOTS);

            output.accept(ModItems.HIGH_CARBON_STEEL_HELMET);
            output.accept(ModItems.HIGH_CARBON_STEEL_CHESTPLATE);
            output.accept(ModItems.HIGH_CARBON_STEEL_LEGGINGS);
            output.accept(ModItems.HIGH_CARBON_STEEL_BOOTS);

            output.accept(ModItems.SILVER_COPPER_HELMET);
            output.accept(ModItems.SILVER_COPPER_CHESTPLATE);
            output.accept(ModItems.SILVER_COPPER_LEGGINGS);
            output.accept(ModItems.SILVER_COPPER_BOOTS);
        }).build();
    });

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
