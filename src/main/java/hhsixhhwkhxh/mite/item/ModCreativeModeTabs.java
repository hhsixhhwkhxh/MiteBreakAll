package hhsixhhwkhxh.mite.item;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
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

            output.accept(ModItems.COPPER_HELMET);
            output.accept(ModItems.COPPER_CHESTPLATE);
            output.accept(ModItems.COPPER_LEGGINGS);
            output.accept(ModItems.COPPER_BOOTS);

            output.accept(ModBlocks.ADAMANTIUM_ANVIL);
            output.accept(ModBlocks.MITHRIL_ANVIL);
            output.accept(ModBlocks.ANCIENT_METAL_ANVIL);
            output.accept(ModBlocks.HARD_ANVIL);
            output.accept(ModBlocks.IRON_ANVIL);
            output.accept(ModBlocks.GOLD_ANVIL);
            output.accept(ModBlocks.SILVER_ANVIL);
            output.accept(ModBlocks.COPPER_ANVIL);



            output.accept(ModItems.FLINT_PICKAXE);
            output.accept(ModItems.FLINT_SHOVEL);
            output.accept(ModItems.FLINT_AXE);

            output.accept(ModItems.FLINT_HATCHET);

            output.accept(ModItems.OBSIDIAN_SHOVEL);
            output.accept(ModItems.OBSIDIAN_AXE);

            output.accept(ModItems.OBSIDIAN_HATCHET);

            output.accept(ModItems.IRON_WAR_HAMMER);
            output.accept(ModItems.IRON_BATTLE_AXE);
            output.accept(ModItems.IRON_MATTOCK);
            output.accept(ModItems.IRON_SCYTHE);
            output.accept(ModItems.IRON_SHEARS);
            output.accept(ModItems.IRON_DAGGER);
            output.accept(ModItems.IRON_HATCHET);

            output.accept(ModItems.GOLDEN_WAR_HAMMER);
            output.accept(ModItems.GOLDEN_BATTLE_AXE);
            output.accept(ModItems.GOLDEN_MATTOCK);
            output.accept(ModItems.GOLDEN_SCYTHE);
            output.accept(ModItems.GOLDEN_SHEARS);
            output.accept(ModItems.GOLDEN_DAGGER);
            output.accept(ModItems.GOLDEN_HATCHET);

            output.accept(ModItems.COPPER_WAR_HAMMER);
            output.accept(ModItems.COPPER_BATTLE_AXE);
            output.accept(ModItems.COPPER_MATTOCK);
            output.accept(ModItems.COPPER_SCYTHE);
            output.accept(ModItems.COPPER_SHEARS);
            output.accept(ModItems.COPPER_DAGGER);
            output.accept(ModItems.COPPER_HATCHET);
            output.accept(ModItems.BRONZE_SWORD);
            output.accept(ModItems.BRONZE_PICKAXE);
            output.accept(ModItems.BRONZE_SHOVEL);
            output.accept(ModItems.BRONZE_AXE);
            output.accept(ModItems.BRONZE_WAR_HAMMER);
            output.accept(ModItems.BRONZE_BATTLE_AXE);
            output.accept(ModItems.SILVER_COPPER_SWORD);
            output.accept(ModItems.SILVER_COPPER_PICKAXE);
            output.accept(ModItems.SILVER_COPPER_SHOVEL);
            output.accept(ModItems.SILVER_COPPER_AXE);
            output.accept(ModItems.SILVER_COPPER_WAR_HAMMER);
            output.accept(ModItems.SILVER_COPPER_BATTLE_AXE);
            output.accept(ModItems.SILVER_SWORD);
            output.accept(ModItems.SILVER_PICKAXE);
            output.accept(ModItems.SILVER_SHOVEL);
            output.accept(ModItems.SILVER_AXE);
            output.accept(ModItems.SILVER_HOE);
            output.accept(ModItems.SILVER_WAR_HAMMER);
            output.accept(ModItems.SILVER_BATTLE_AXE);
            output.accept(ModItems.SILVER_MATTOCK);
            output.accept(ModItems.SILVER_SCYTHE);
            output.accept(ModItems.SILVER_SHEARS);
            output.accept(ModItems.SILVER_DAGGER);
            output.accept(ModItems.SILVER_HATCHET);
            output.accept(ModItems.RUSTED_IRON_SWORD);
            output.accept(ModItems.RUSTED_IRON_PICKAXE);
            output.accept(ModItems.RUSTED_IRON_SHOVEL);
            output.accept(ModItems.RUSTED_IRON_AXE);
            output.accept(ModItems.RUSTED_IRON_HOE);
            output.accept(ModItems.RUSTED_IRON_WAR_HAMMER);
            output.accept(ModItems.RUSTED_IRON_BATTLE_AXE);
            output.accept(ModItems.RUSTED_IRON_MATTOCK);
            output.accept(ModItems.RUSTED_IRON_SCYTHE);
            output.accept(ModItems.RUSTED_IRON_SHEARS);
            output.accept(ModItems.RUSTED_IRON_DAGGER);
            output.accept(ModItems.RUSTED_IRON_HATCHET);
            output.accept(ModItems.HIGH_CARBON_STEEL_SWORD);
            output.accept(ModItems.HIGH_CARBON_STEEL_PICKAXE);
            output.accept(ModItems.HIGH_CARBON_STEEL_SHOVEL);
            output.accept(ModItems.HIGH_CARBON_STEEL_AXE);
            output.accept(ModItems.HIGH_CARBON_STEEL_HOE);
            output.accept(ModItems.HIGH_CARBON_STEEL_WAR_HAMMER);
            output.accept(ModItems.HIGH_CARBON_STEEL_BATTLE_AXE);
            output.accept(ModItems.HIGH_CARBON_STEEL_MATTOCK);
            output.accept(ModItems.HIGH_CARBON_STEEL_SCYTHE);
            output.accept(ModItems.HIGH_CARBON_STEEL_SHEARS);
            output.accept(ModItems.HIGH_CARBON_STEEL_DAGGER);
            output.accept(ModItems.HIGH_CARBON_STEEL_HATCHET);
            output.accept(ModItems.HARD_SWORD);
            output.accept(ModItems.HARD_PICKAXE);
            output.accept(ModItems.HARD_SHOVEL);
            output.accept(ModItems.HARD_AXE);
            output.accept(ModItems.HARD_HOE);
            output.accept(ModItems.HARD_WAR_HAMMER);
            output.accept(ModItems.HARD_BATTLE_AXE);
            output.accept(ModItems.HARD_MATTOCK);
            output.accept(ModItems.HARD_SCYTHE);
            output.accept(ModItems.HARD_SHEARS);
            output.accept(ModItems.HARD_DAGGER);
            output.accept(ModItems.HARD_HATCHET);
            output.accept(ModItems.ANCIENT_METAL_SWORD);
            output.accept(ModItems.ANCIENT_METAL_PICKAXE);
            output.accept(ModItems.ANCIENT_METAL_SHOVEL);
            output.accept(ModItems.ANCIENT_METAL_AXE);
            output.accept(ModItems.ANCIENT_METAL_HOE);
            output.accept(ModItems.ANCIENT_METAL_WAR_HAMMER);
            output.accept(ModItems.ANCIENT_METAL_BATTLE_AXE);
            output.accept(ModItems.ANCIENT_METAL_MATTOCK);
            output.accept(ModItems.ANCIENT_METAL_SCYTHE);
            output.accept(ModItems.ANCIENT_METAL_SHEARS);
            output.accept(ModItems.ANCIENT_METAL_DAGGER);
            output.accept(ModItems.ANCIENT_METAL_HATCHET);
            output.accept(ModItems.MITHRIL_SWORD);
            output.accept(ModItems.MITHRIL_PICKAXE);
            output.accept(ModItems.MITHRIL_SHOVEL);
            output.accept(ModItems.MITHRIL_AXE);
            output.accept(ModItems.MITHRIL_HOE);
            output.accept(ModItems.MITHRIL_WAR_HAMMER);
            output.accept(ModItems.MITHRIL_BATTLE_AXE);
            output.accept(ModItems.MITHRIL_MATTOCK);
            output.accept(ModItems.MITHRIL_SCYTHE);
            output.accept(ModItems.MITHRIL_SHEARS);
            output.accept(ModItems.MITHRIL_DAGGER);
            output.accept(ModItems.MITHRIL_HATCHET);
            output.accept(ModItems.ADAMANTIUM_SWORD);
            output.accept(ModItems.ADAMANTIUM_PICKAXE);
            output.accept(ModItems.ADAMANTIUM_SHOVEL);
            output.accept(ModItems.ADAMANTIUM_AXE);
            output.accept(ModItems.ADAMANTIUM_HOE);
            output.accept(ModItems.ADAMANTIUM_WAR_HAMMER);
            output.accept(ModItems.ADAMANTIUM_BATTLE_AXE);
            output.accept(ModItems.ADAMANTIUM_MATTOCK);
            output.accept(ModItems.ADAMANTIUM_SCYTHE);
            output.accept(ModItems.ADAMANTIUM_SHEARS);
            output.accept(ModItems.ADAMANTIUM_DAGGER);
            output.accept(ModItems.ADAMANTIUM_HATCHET);

        }).build();
    });

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
