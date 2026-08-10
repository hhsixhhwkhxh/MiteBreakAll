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
        add(ModBlocks.STRAWBERRY_BUSH.get(), "草莓丛");
        add(ModItems.STRAWBERRIES.get(), "草莓");

        add("enchantment.mite_break_all.overpowered","君临天下");

        add(ModItems.DIAMOND_SHARD.get(), "钻石碎片");
        add(ModItems.EMERALD_SHARD.get(), "绿宝石碎片");
        add(ModItems.FLINT_SHARD.get(), "燧石碎片");
        add(ModItems.GLASS_SHARD.get(), "玻璃碎片");
        add(ModItems.OBSIDIAN_SHARD.get(), "黑曜石碎片");
        add(ModItems.QUARTZ_SHARD.get(), "石英碎片");

        add(ModItems.ADAMANTIUM_NUGGET.get(), "艾德曼粒");
        add(ModItems.ANCIENT_METAL_NUGGET.get(), "远古金属粒");
        add(ModItems.HARD_NUGGET.get(), "硬石粒");
        add(ModItems.MERCURY_NUGGET.get(), "汞粒");
        add(ModItems.MITHRIL_NUGGET.get(), "秘银粒");
        add(ModItems.SILVER_NUGGET.get(), "银粒");
        add(ModItems.COPPER_NUGGET.get(), "铜粒");
    }
}
