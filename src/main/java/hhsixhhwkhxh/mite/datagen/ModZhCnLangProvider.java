package hhsixhhwkhxh.mite.datagen;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModZhCnLangProvider extends LanguageProvider {
    public ModZhCnLangProvider(PackOutput output) {
        super(output, MiteBreakAll.MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.WILD_APPLE.get(), "野果");
        add(ModBlocks.SILVER_ORE.get(),"银矿");
        add(ModBlocks.FLINT_CRAFTING_TABLE.get(), "燧石工作台");
        add("craft_table.flint","燧石工作台");

        add("enchantment.mite_break_all.overpowered","君临天下");
    }
}
