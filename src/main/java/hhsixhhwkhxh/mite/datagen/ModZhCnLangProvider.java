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
        add(ModBlocks.HARD_ORE.get(),"硬石矿");
        add(ModBlocks.MERCURY_ORE.get(),"汞矿");
        add(ModBlocks.MITHRIL_ORE.get(),"秘银矿");
        add(ModBlocks.ADAMANTIUM_ORE.get(),"艾德曼矿");
        add(ModBlocks.TIN_ORE.get(),"锡矿");

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

        add(ModItems.MESH_LEATHER.get(), "皮筛网");
        add(ModItems.MESH_STRING.get(), "线筛网");
        add(ModBlocks.SIEVE.get(), "木筛");

        add("itemGroup.mite","MITE");

        add(ModItems.ADAMANTIUM_INGOT.get(), "艾德曼锭");
        add(ModItems.ANCIENT_METAL_INGOT.get(), "远古金属锭");
        add(ModItems.HARD_INGOT.get(), "硬石锭");
        add(ModItems.MERCURY_INGOT.get(), "汞锭");
        add(ModItems.MITHRIL_INGOT.get(), "秘银锭");
        add(ModItems.SILVER_INGOT.get(), "银锭");
        add(ModItems.TIN_INGOT.get(), "锡锭");

        add(ModItems.ADAMANTIUM_HELMET.get(),"艾德曼头盔");
        add(ModItems.ADAMANTIUM_CHESTPLATE.get(),"艾德曼胸甲");
        add(ModItems.ADAMANTIUM_LEGGINGS.get(),"艾德曼护腿");
        add(ModItems.ADAMANTIUM_BOOTS.get(),"艾德曼靴子");

        add(ModItems.ANCIENT_METAL_HELMET.get(),"远古金属头盔");
        add(ModItems.ANCIENT_METAL_CHESTPLATE.get(),"远古金属胸甲");
        add(ModItems.ANCIENT_METAL_LEGGINGS.get(),"远古金属护腿");
        add(ModItems.ANCIENT_METAL_BOOTS.get(),"远古金属靴子");

        add(ModItems.HARD_HELMET.get(),"硬石头盔");
        add(ModItems.HARD_CHESTPLATE.get(),"硬石胸甲");
        add(ModItems.HARD_LEGGINGS.get(),"硬石护腿");
        add(ModItems.HARD_BOOTS.get(),"硬石靴子");

        add(ModItems.MITHRIL_HELMET.get(),"秘银头盔");
        add(ModItems.MITHRIL_CHESTPLATE.get(),"秘银胸甲");
        add(ModItems.MITHRIL_LEGGINGS.get(),"秘银护腿");
        add(ModItems.MITHRIL_BOOTS.get(),"秘银靴子");

        add(ModItems.RUSTED_IRON_HELMET.get(),"锈铁头盔");
        add(ModItems.RUSTED_IRON_CHESTPLATE.get(),"锈铁胸甲");
        add(ModItems.RUSTED_IRON_LEGGINGS.get(),"锈铁护腿");
        add(ModItems.RUSTED_IRON_BOOTS.get(),"锈铁靴子");

        add(ModItems.SILVER_HELMET.get(),"银头盔");
        add(ModItems.SILVER_CHESTPLATE.get(),"银胸甲");
        add(ModItems.SILVER_LEGGINGS.get(),"银护腿");
        add(ModItems.SILVER_BOOTS.get(),"银靴子");

        add(ModItems.BRONZE_HELMET.get(),"青铜头盔");
        add(ModItems.BRONZE_CHESTPLATE.get(),"青铜胸甲");
        add(ModItems.BRONZE_LEGGINGS.get(),"青铜护腿");
        add(ModItems.BRONZE_BOOTS.get(),"青铜靴子");

        add(ModItems.HIGH_CARBON_STEEL_HELMET.get(),"高碳钢头盔");
        add(ModItems.HIGH_CARBON_STEEL_CHESTPLATE.get(),"高碳钢胸甲");
        add(ModItems.HIGH_CARBON_STEEL_LEGGINGS.get(),"高碳钢护腿");
        add(ModItems.HIGH_CARBON_STEEL_BOOTS.get(),"高碳钢靴子");

        add(ModItems.SILVER_COPPER_HELMET.get(),"银铜头盔");
        add(ModItems.SILVER_COPPER_CHESTPLATE.get(),"银铜胸甲");
        add(ModItems.SILVER_COPPER_LEGGINGS.get(),"银铜护腿");
        add(ModItems.SILVER_COPPER_BOOTS.get(),"银铜靴子");
    }
}
