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
    }
}
