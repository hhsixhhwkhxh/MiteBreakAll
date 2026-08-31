package hhsixhhwkhxh.mite.datagen;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.item.ModItems;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEnUsLangProvider extends LanguageProvider {
    public ModEnUsLangProvider(PackOutput output) {
        super(output, MiteBreakAll.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.WILD_APPLE.get(), "Wild Apple");

        add(ModBlocks.SILVER_ORE.get(),"Silver Ore");
        add(ModBlocks.HARD_ORE.get(),"Hard Ore");
        add(ModBlocks.MERCURY_ORE.get(),"Mercury Ore");
        add(ModBlocks.MITHRIL_ORE.get(),"Mithril Ore");
        add(ModBlocks.ADAMANTIUM_ORE.get(),"Adamantium Ore");
        add(ModBlocks.TIN_ORE.get(),"Tin Ore");

        add(ModBlocks.FLINT_CRAFTING_TABLE.get(), "Flint Crafting Table");
        add("craft_table.flint","Flint Crafting Table");
        add(ModBlocks.STRAWBERRY_BUSH.get(), "StrawBerry Bush");
        add(ModItems.STRAWBERRIES.get(), "StrawBerries");

        add("enchantment.mite_break_all.overpowered","Overpowered");

        add(ModItems.DIAMOND_SHARD.get(), "Diamond Shard");
        add(ModItems.EMERALD_SHARD.get(), "Emerald Shard");
        add(ModItems.FLINT_SHARD.get(), "Flint Shard");
        add(ModItems.GLASS_SHARD.get(), "Glass Shard");
        add(ModItems.OBSIDIAN_SHARD.get(), "Obsidian Shard");
        add(ModItems.QUARTZ_SHARD.get(), "Quartz Shard");

        add(ModItems.ADAMANTIUM_NUGGET.get(), "Adamantium Nugget");
        add(ModItems.ANCIENT_METAL_NUGGET.get(), "Ancient Metal Nugget");
        add(ModItems.HARD_NUGGET.get(), "Hard Nugget");
        add(ModItems.MERCURY_NUGGET.get(), "Mercury Nugget");
        add(ModItems.MITHRIL_NUGGET.get(), "Mithril Nugget");
        add(ModItems.SILVER_NUGGET.get(), "Silver Nugget");
        add(ModItems.COPPER_NUGGET.get(), "Copper Nugget");

        add(ModItems.MESH_LEATHER.get(), "Mesh Leather");
        add(ModItems.MESH_STRING.get(), "Mesh String");
        add(ModBlocks.SIEVE.get(), "Sieve");

        add("itemGroup.mite","MITE");

        add(ModItems.ADAMANTIUM_INGOT.get(), "Adamantium Ingot");
        add(ModItems.ANCIENT_METAL_INGOT.get(), "Ancient Metal Ingot");
        add(ModItems.HARD_INGOT.get(), "Hard Ingot");
        add(ModItems.MERCURY_INGOT.get(), "Mercury Ingot");
        add(ModItems.MITHRIL_INGOT.get(), "Mithril Ingot");
        add(ModItems.SILVER_INGOT.get(), "Silver Ingot");
        add(ModItems.TIN_INGOT.get(), "Tin Ingot");

        add(ModItems.ADAMANTIUM_HELMET.get(),"Adamantium Helmet");
        add(ModItems.ADAMANTIUM_CHESTPLATE.get(),"Adamantium Chestplate");
        add(ModItems.ADAMANTIUM_LEGGINGS.get(),"Adamantium Leggings");
        add(ModItems.ADAMANTIUM_BOOTS.get(),"Adamantium Boots");

        add(ModItems.ANCIENT_METAL_HELMET.get(),"Ancient Metal Helmet");
        add(ModItems.ANCIENT_METAL_CHESTPLATE.get(),"Ancient Metal Chestplate");
        add(ModItems.ANCIENT_METAL_LEGGINGS.get(),"Ancient Metal Leggings");
        add(ModItems.ANCIENT_METAL_BOOTS.get(),"Ancient Metal Boots");

        add(ModItems.HARD_HELMET.get(),"Hard Helmet");
        add(ModItems.HARD_CHESTPLATE.get(),"Hard Chestplate");
        add(ModItems.HARD_LEGGINGS.get(),"Hard Leggings");
        add(ModItems.HARD_BOOTS.get(),"Hard Boots");

        add(ModItems.MITHRIL_HELMET.get(),"Mithril Helmet");
        add(ModItems.MITHRIL_CHESTPLATE.get(),"Mithril Chestplate");
        add(ModItems.MITHRIL_LEGGINGS.get(),"Mithril Leggings");
        add(ModItems.MITHRIL_BOOTS.get(),"Mithril Boots");

        add(ModItems.RUSTED_IRON_HELMET.get(),"Rusted Iron Helmet");
        add(ModItems.RUSTED_IRON_CHESTPLATE.get(),"Rusted Iron Chestplate");
        add(ModItems.RUSTED_IRON_LEGGINGS.get(),"Rusted Iron Leggings");
        add(ModItems.RUSTED_IRON_BOOTS.get(),"Rusted Iron Boots");

        add(ModItems.SILVER_HELMET.get(),"Silver Helmet");
        add(ModItems.SILVER_CHESTPLATE.get(),"Silver Chestplate");
        add(ModItems.SILVER_LEGGINGS.get(),"Silver Leggings");
        add(ModItems.SILVER_BOOTS.get(),"Silver Boots");

        add(ModItems.BRONZE_HELMET.get(),"Bronze Helmet");
        add(ModItems.BRONZE_CHESTPLATE.get(),"Bronze Chestplate");
        add(ModItems.BRONZE_LEGGINGS.get(),"Bronze Leggings");
        add(ModItems.BRONZE_BOOTS.get(),"Bronze Boots");

        add(ModItems.HIGH_CARBON_STEEL_HELMET.get(),"High Carbon Steel Helmet");
        add(ModItems.HIGH_CARBON_STEEL_CHESTPLATE.get(),"High Carbon Steel Chestplate");
        add(ModItems.HIGH_CARBON_STEEL_LEGGINGS.get(),"High Carbon Steel Leggings");
        add(ModItems.HIGH_CARBON_STEEL_BOOTS.get(),"High Carbon Steel Boots");

        add(ModItems.SILVER_COPPER_HELMET.get(),"Silver Copper Helmet");
        add(ModItems.SILVER_COPPER_CHESTPLATE.get(),"Silver Copper Chestplate");
        add(ModItems.SILVER_COPPER_LEGGINGS.get(),"Silver Copper Leggings");
        add(ModItems.SILVER_COPPER_BOOTS.get(),"Silver Copper Boots");

        add(ModItems.COPPER_HELMET.get(),"Copper Helmet");
        add(ModItems.COPPER_CHESTPLATE.get(),"Copper Chestplate");
        add(ModItems.COPPER_LEGGINGS.get(),"Copper Leggings");
        add(ModItems.COPPER_BOOTS.get(),"Copper Boots");

        add(ModBlocks.ADAMANTIUM_ANVIL.get(),"Adamantium Anvil");
        add(ModBlocks.ANCIENT_METAL_ANVIL.get(),"Ancient Metal Anvil");
        add(ModBlocks.COPPER_ANVIL.get(),"Copper Anvil");
        add(ModBlocks.GOLD_ANVIL.get(),"Gold Anvil");
        add(ModBlocks.HARD_ANVIL.get(),"Hard Anvil");
        add(ModBlocks.IRON_ANVIL.get(),"Iron Anvil");
        add(ModBlocks.MITHRIL_ANVIL.get(),"Mithril Anvil");
        add(ModBlocks.SILVER_ANVIL.get(),"Silver Anvil");

        add("container.repair.higher_anvil","Require higher level anvil");
        add("container.repair.unsupported_item","This item cannot be used here");

        add(ModItems.FLINT_PICKAXE.get(), "Flint Pickaxe");
        add(ModItems.FLINT_SHOVEL.get(), "Flint Shovel");
        add(ModItems.FLINT_AXE.get(), "Flint Axe");
        add(ModItems.FLINT_HATCHET.get(), "Flint Hatchet");

        add(ModItems.OBSIDIAN_SHOVEL.get(), "Obsidian Shovel");
        add(ModItems.OBSIDIAN_AXE.get(), "Obsidian Axe");
        add(ModItems.OBSIDIAN_HATCHET.get(), "Obsidian Hatchet");

        add(ModItems.IRON_WAR_HAMMER.get(), "Iron War Hammer");
        add(ModItems.IRON_BATTLE_AXE.get(), "Iron Battle Axe");
        add(ModItems.IRON_MATTOCK.get(), "Iron Mattock");
        add(ModItems.IRON_SCYTHE.get(), "Iron Scythe");
        add(ModItems.IRON_SHEARS.get(), "Iron Shears");
        add(ModItems.IRON_DAGGER.get(), "Iron Dagger");
        add(ModItems.IRON_HATCHET.get(), "Iron Hatchet");

        add(ModItems.GOLDEN_WAR_HAMMER.get(), "Golden War Hammer");
        add(ModItems.GOLDEN_BATTLE_AXE.get(), "Golden Battle Axe");
        add(ModItems.GOLDEN_MATTOCK.get(), "Golden Mattock");
        add(ModItems.GOLDEN_SCYTHE.get(), "Golden Scythe");
        add(ModItems.GOLDEN_SHEARS.get(), "Golden Shears");
        add(ModItems.GOLDEN_DAGGER.get(), "Golden Dagger");
        add(ModItems.GOLDEN_HATCHET.get(), "Golden Hatchet");

        add(ModItems.COPPER_WAR_HAMMER.get(), "Copper War Hammer");
        add(ModItems.COPPER_BATTLE_AXE.get(), "Copper Battle Axe");
        add(ModItems.COPPER_MATTOCK.get(), "Copper Mattock");
        add(ModItems.COPPER_SCYTHE.get(), "Copper Scythe");
        add(ModItems.COPPER_SHEARS.get(), "Copper Shears");
        add(ModItems.COPPER_DAGGER.get(), "Copper Dagger");
        add(ModItems.COPPER_HATCHET.get(), "Copper Hatchet");

        add(ModItems.BRONZE_SWORD.get(), "Bronze Sword");
        add(ModItems.BRONZE_PICKAXE.get(), "Bronze Pickaxe");
        add(ModItems.BRONZE_SHOVEL.get(), "Bronze Shovel");
        add(ModItems.BRONZE_AXE.get(), "Bronze Axe");
        add(ModItems.BRONZE_WAR_HAMMER.get(), "Bronze War Hammer");
        add(ModItems.BRONZE_BATTLE_AXE.get(), "Bronze Battle Axe");

        add(ModItems.SILVER_COPPER_SWORD.get(), "Silver-Copper Sword");
        add(ModItems.SILVER_COPPER_PICKAXE.get(), "Silver-Copper Pickaxe");
        add(ModItems.SILVER_COPPER_SHOVEL.get(), "Silver-Copper Shovel");
        add(ModItems.SILVER_COPPER_AXE.get(), "Silver-Copper Axe");
        add(ModItems.SILVER_COPPER_WAR_HAMMER.get(), "Silver-Copper War Hammer");
        add(ModItems.SILVER_COPPER_BATTLE_AXE.get(), "Silver-Copper Battle Axe");

        add(ModItems.SILVER_SWORD.get(), "Silver Sword");
        add(ModItems.SILVER_PICKAXE.get(), "Silver Pickaxe");
        add(ModItems.SILVER_SHOVEL.get(), "Silver Shovel");
        add(ModItems.SILVER_AXE.get(), "Silver Axe");
        add(ModItems.SILVER_HOE.get(), "Silver Hoe");
        add(ModItems.SILVER_WAR_HAMMER.get(), "Silver War Hammer");
        add(ModItems.SILVER_BATTLE_AXE.get(), "Silver Battle Axe");
        add(ModItems.SILVER_MATTOCK.get(), "Silver Mattock");
        add(ModItems.SILVER_SCYTHE.get(), "Silver Scythe");
        add(ModItems.SILVER_SHEARS.get(), "Silver Shears");
        add(ModItems.SILVER_DAGGER.get(), "Silver Dagger");
        add(ModItems.SILVER_HATCHET.get(), "Silver Hatchet");

        add(ModItems.RUSTED_IRON_SWORD.get(), "Rusted Iron Sword");
        add(ModItems.RUSTED_IRON_PICKAXE.get(), "Rusted Iron Pickaxe");
        add(ModItems.RUSTED_IRON_SHOVEL.get(), "Rusted Iron Shovel");
        add(ModItems.RUSTED_IRON_AXE.get(), "Rusted Iron Axe");
        add(ModItems.RUSTED_IRON_HOE.get(), "Rusted Iron Hoe");
        add(ModItems.RUSTED_IRON_WAR_HAMMER.get(), "Rusted Iron War Hammer");
        add(ModItems.RUSTED_IRON_BATTLE_AXE.get(), "Rusted Iron Battle Axe");
        add(ModItems.RUSTED_IRON_MATTOCK.get(), "Rusted Iron Mattock");
        add(ModItems.RUSTED_IRON_SCYTHE.get(), "Rusted Iron Scythe");
        add(ModItems.RUSTED_IRON_SHEARS.get(), "Rusted Iron Shears");
        add(ModItems.RUSTED_IRON_DAGGER.get(), "Rusted Iron Dagger");
        add(ModItems.RUSTED_IRON_HATCHET.get(), "Rusted Iron Hatchet");

        add(ModItems.HIGH_CARBON_STEEL_SWORD.get(), "High Carbon Steel Sword");
        add(ModItems.HIGH_CARBON_STEEL_PICKAXE.get(), "High Carbon Steel Pickaxe");
        add(ModItems.HIGH_CARBON_STEEL_SHOVEL.get(), "High Carbon Steel Shovel");
        add(ModItems.HIGH_CARBON_STEEL_AXE.get(), "High Carbon Steel Axe");
        add(ModItems.HIGH_CARBON_STEEL_HOE.get(), "High Carbon Steel Hoe");
        add(ModItems.HIGH_CARBON_STEEL_WAR_HAMMER.get(), "High Carbon Steel War Hammer");
        add(ModItems.HIGH_CARBON_STEEL_BATTLE_AXE.get(), "High Carbon Steel Battle Axe");
        add(ModItems.HIGH_CARBON_STEEL_MATTOCK.get(), "High Carbon Steel Mattock");
        add(ModItems.HIGH_CARBON_STEEL_SCYTHE.get(), "High Carbon Steel Scythe");
        add(ModItems.HIGH_CARBON_STEEL_SHEARS.get(), "High Carbon Steel Shears");
        add(ModItems.HIGH_CARBON_STEEL_DAGGER.get(), "High Carbon Steel Dagger");
        add(ModItems.HIGH_CARBON_STEEL_HATCHET.get(), "High Carbon Steel Hatchet");

        add(ModItems.HARD_SWORD.get(), "Hard Sword");
        add(ModItems.HARD_PICKAXE.get(), "Hard Pickaxe");
        add(ModItems.HARD_SHOVEL.get(), "Hard Shovel");
        add(ModItems.HARD_AXE.get(), "Hard Axe");
        add(ModItems.HARD_HOE.get(), "Hard Hoe");
        add(ModItems.HARD_WAR_HAMMER.get(), "Hard War Hammer");
        add(ModItems.HARD_BATTLE_AXE.get(), "Hard Battle Axe");
        add(ModItems.HARD_MATTOCK.get(), "Hard Mattock");
        add(ModItems.HARD_SCYTHE.get(), "Hard Scythe");
        add(ModItems.HARD_SHEARS.get(), "Hard Shears");
        add(ModItems.HARD_DAGGER.get(), "Hard Dagger");
        add(ModItems.HARD_HATCHET.get(), "Hard Hatchet");

        add(ModItems.ANCIENT_METAL_SWORD.get(), "Ancient Metal Sword");
        add(ModItems.ANCIENT_METAL_PICKAXE.get(), "Ancient Metal Pickaxe");
        add(ModItems.ANCIENT_METAL_SHOVEL.get(), "Ancient Metal Shovel");
        add(ModItems.ANCIENT_METAL_AXE.get(), "Ancient Metal Axe");
        add(ModItems.ANCIENT_METAL_HOE.get(), "Ancient Metal Hoe");
        add(ModItems.ANCIENT_METAL_WAR_HAMMER.get(), "Ancient Metal War Hammer");
        add(ModItems.ANCIENT_METAL_BATTLE_AXE.get(), "Ancient Metal Battle Axe");
        add(ModItems.ANCIENT_METAL_MATTOCK.get(), "Ancient Metal Mattock");
        add(ModItems.ANCIENT_METAL_SCYTHE.get(), "Ancient Metal Scythe");
        add(ModItems.ANCIENT_METAL_SHEARS.get(), "Ancient Metal Shears");
        add(ModItems.ANCIENT_METAL_DAGGER.get(), "Ancient Metal Dagger");
        add(ModItems.ANCIENT_METAL_HATCHET.get(), "Ancient Metal Hatchet");

        add(ModItems.MITHRIL_SWORD.get(), "Mithril Sword");
        add(ModItems.MITHRIL_PICKAXE.get(), "Mithril Pickaxe");
        add(ModItems.MITHRIL_SHOVEL.get(), "Mithril Shovel");
        add(ModItems.MITHRIL_AXE.get(), "Mithril Axe");
        add(ModItems.MITHRIL_HOE.get(), "Mithril Hoe");
        add(ModItems.MITHRIL_WAR_HAMMER.get(), "Mithril War Hammer");
        add(ModItems.MITHRIL_BATTLE_AXE.get(), "Mithril Battle Axe");
        add(ModItems.MITHRIL_MATTOCK.get(), "Mithril Mattock");
        add(ModItems.MITHRIL_SCYTHE.get(), "Mithril Scythe");
        add(ModItems.MITHRIL_SHEARS.get(), "Mithril Shears");
        add(ModItems.MITHRIL_DAGGER.get(), "Mithril Dagger");
        add(ModItems.MITHRIL_HATCHET.get(), "Mithril Hatchet");

        add(ModItems.ADAMANTIUM_SWORD.get(), "Adamantium Sword");
        add(ModItems.ADAMANTIUM_PICKAXE.get(), "Adamantium Pickaxe");
        add(ModItems.ADAMANTIUM_SHOVEL.get(), "Adamantium Shovel");
        add(ModItems.ADAMANTIUM_AXE.get(), "Adamantium Axe");
        add(ModItems.ADAMANTIUM_HOE.get(), "Adamantium Hoe");
        add(ModItems.ADAMANTIUM_WAR_HAMMER.get(), "Adamantium War Hammer");
        add(ModItems.ADAMANTIUM_BATTLE_AXE.get(), "Adamantium Battle Axe");
        add(ModItems.ADAMANTIUM_MATTOCK.get(), "Adamantium Mattock");
        add(ModItems.ADAMANTIUM_SCYTHE.get(), "Adamantium Scythe");
        add(ModItems.ADAMANTIUM_SHEARS.get(), "Adamantium Shears");
        add(ModItems.ADAMANTIUM_DAGGER.get(), "Adamantium Dagger");
        add(ModItems.ADAMANTIUM_HATCHET.get(), "Adamantium Hatchet");

        add("tooltip.item.deprecated","This item is not meant for normal survival mode.");
    }
}
