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
        add(ModBlocks.FLINT_CRAFTING_TABLE.get(), "Flint Crafting Table");
        add("craft_table.flint","Flint Crafting Table");

        add("enchantment.mite_break_all.overpowered","Overpowered");
    }
}
