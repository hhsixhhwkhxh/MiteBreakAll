package hhsixhhwkhxh.mite.datagen;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.item.ModItems;
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
    }
}
