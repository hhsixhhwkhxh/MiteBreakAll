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

        add(ModItems.COPPER_HELMET.get(),"铜头盔");
        add(ModItems.COPPER_CHESTPLATE.get(),"铜胸甲");
        add(ModItems.COPPER_LEGGINGS.get(),"铜护腿");
        add(ModItems.COPPER_BOOTS.get(),"铜靴子");

        add(ModBlocks.ADAMANTIUM_ANVIL.get(),"艾德曼砧");
        add(ModBlocks.ANCIENT_METAL_ANVIL.get(),"远古金属砧");
        add(ModBlocks.COPPER_ANVIL.get(),"铜砧");
        add(ModBlocks.GOLD_ANVIL.get(),"金砧");
        add(ModBlocks.HARD_ANVIL.get(),"硬石砧");
        add(ModBlocks.IRON_ANVIL.get(),"铁砧");
        add(ModBlocks.MITHRIL_ANVIL.get(),"秘银砧");
        add(ModBlocks.SILVER_ANVIL.get(),"银砧");

        add("container.repair.higher_anvil","需要更高级的砧");
        add("container.repair.unsupported_item","该物品不支持此操作");

        add(ModItems.FLINT_PICKAXE.get(), "燧石镐");
        add(ModItems.FLINT_SHOVEL.get(), "燧石锹");
        add(ModItems.FLINT_AXE.get(), "燧石斧");
        add(ModItems.FLINT_HATCHET.get(), "燧石手斧");

        add(ModItems.OBSIDIAN_SHOVEL.get(), "黑曜石锹");
        add(ModItems.OBSIDIAN_AXE.get(), "黑曜石斧");
        add(ModItems.OBSIDIAN_HATCHET.get(), "黑曜石手斧");

        add(ModItems.IRON_WAR_HAMMER.get(), "铁战锤");
        add(ModItems.IRON_BATTLE_AXE.get(), "铁战斧");
        add(ModItems.IRON_MATTOCK.get(), "铁鸭嘴锄");
        add(ModItems.IRON_SCYTHE.get(), "铁镰刀");
        add(ModItems.IRON_SHEARS.get(), "铁剪刀");
        add(ModItems.IRON_DAGGER.get(), "铁短剑");
        add(ModItems.IRON_HATCHET.get(), "铁手斧");

        add(ModItems.GOLDEN_WAR_HAMMER.get(), "金战锤");
        add(ModItems.GOLDEN_BATTLE_AXE.get(), "金战斧");
        add(ModItems.GOLDEN_MATTOCK.get(), "金鸭嘴锄");
        add(ModItems.GOLDEN_SCYTHE.get(), "金镰刀");
        add(ModItems.GOLDEN_SHEARS.get(), "金剪刀");
        add(ModItems.GOLDEN_DAGGER.get(), "金短剑");
        add(ModItems.GOLDEN_HATCHET.get(), "金手斧");

        add(ModItems.COPPER_WAR_HAMMER.get(), "铜战锤");
        add(ModItems.COPPER_BATTLE_AXE.get(), "铜战斧");
        add(ModItems.COPPER_MATTOCK.get(), "铜鸭嘴锄");
        add(ModItems.COPPER_SCYTHE.get(), "铜镰刀");
        add(ModItems.COPPER_SHEARS.get(), "铜剪刀");
        add(ModItems.COPPER_DAGGER.get(), "铜短剑");
        add(ModItems.COPPER_HATCHET.get(), "铜手斧");

        add(ModItems.BRONZE_SWORD.get(), "青铜剑");
        add(ModItems.BRONZE_PICKAXE.get(), "青铜镐");
        add(ModItems.BRONZE_SHOVEL.get(), "青铜锹");
        add(ModItems.BRONZE_AXE.get(), "青铜斧");
        add(ModItems.BRONZE_WAR_HAMMER.get(), "青铜战锤");
        add(ModItems.BRONZE_BATTLE_AXE.get(), "青铜战斧");

        add(ModItems.SILVER_COPPER_SWORD.get(), "银铜剑");
        add(ModItems.SILVER_COPPER_PICKAXE.get(), "银铜镐");
        add(ModItems.SILVER_COPPER_SHOVEL.get(), "银铜锹");
        add(ModItems.SILVER_COPPER_AXE.get(), "银铜斧");
        add(ModItems.SILVER_COPPER_WAR_HAMMER.get(), "银铜战锤");
        add(ModItems.SILVER_COPPER_BATTLE_AXE.get(), "银铜战斧");

        add(ModItems.SILVER_SWORD.get(), "银剑");
        add(ModItems.SILVER_PICKAXE.get(), "银镐");
        add(ModItems.SILVER_SHOVEL.get(), "银锹");
        add(ModItems.SILVER_AXE.get(), "银斧");
        add(ModItems.SILVER_HOE.get(), "银锄");
        add(ModItems.SILVER_WAR_HAMMER.get(), "银战锤");
        add(ModItems.SILVER_BATTLE_AXE.get(), "银战斧");
        add(ModItems.SILVER_MATTOCK.get(), "银鸭嘴锄");
        add(ModItems.SILVER_SCYTHE.get(), "银镰刀");
        add(ModItems.SILVER_SHEARS.get(), "银剪刀");
        add(ModItems.SILVER_DAGGER.get(), "银短剑");
        add(ModItems.SILVER_HATCHET.get(), "银手斧");

        add(ModItems.RUSTED_IRON_SWORD.get(), "锈铁剑");
        add(ModItems.RUSTED_IRON_PICKAXE.get(), "锈铁镐");
        add(ModItems.RUSTED_IRON_SHOVEL.get(), "锈铁锹");
        add(ModItems.RUSTED_IRON_AXE.get(), "锈铁斧");
        add(ModItems.RUSTED_IRON_HOE.get(), "锈铁锄");
        add(ModItems.RUSTED_IRON_WAR_HAMMER.get(), "锈铁战锤");
        add(ModItems.RUSTED_IRON_BATTLE_AXE.get(), "锈铁战斧");
        add(ModItems.RUSTED_IRON_MATTOCK.get(), "锈铁鸭嘴锄");
        add(ModItems.RUSTED_IRON_SCYTHE.get(), "锈铁镰刀");
        add(ModItems.RUSTED_IRON_SHEARS.get(), "锈铁剪刀");
        add(ModItems.RUSTED_IRON_DAGGER.get(), "锈铁短剑");
        add(ModItems.RUSTED_IRON_HATCHET.get(), "锈铁手斧");

        add(ModItems.HIGH_CARBON_STEEL_SWORD.get(), "高碳钢剑");
        add(ModItems.HIGH_CARBON_STEEL_PICKAXE.get(), "高碳钢镐");
        add(ModItems.HIGH_CARBON_STEEL_SHOVEL.get(), "高碳钢锹");
        add(ModItems.HIGH_CARBON_STEEL_AXE.get(), "高碳钢斧");
        add(ModItems.HIGH_CARBON_STEEL_HOE.get(), "高碳钢锄");
        add(ModItems.HIGH_CARBON_STEEL_WAR_HAMMER.get(), "高碳钢战锤");
        add(ModItems.HIGH_CARBON_STEEL_BATTLE_AXE.get(), "高碳钢战斧");
        add(ModItems.HIGH_CARBON_STEEL_MATTOCK.get(), "高碳钢鸭嘴锄");
        add(ModItems.HIGH_CARBON_STEEL_SCYTHE.get(), "高碳钢镰刀");
        add(ModItems.HIGH_CARBON_STEEL_SHEARS.get(), "高碳钢剪刀");
        add(ModItems.HIGH_CARBON_STEEL_DAGGER.get(), "高碳钢短剑");
        add(ModItems.HIGH_CARBON_STEEL_HATCHET.get(), "高碳钢手斧");

        add(ModItems.HARD_SWORD.get(), "硬石剑");
        add(ModItems.HARD_PICKAXE.get(), "硬石镐");
        add(ModItems.HARD_SHOVEL.get(), "硬石锹");
        add(ModItems.HARD_AXE.get(), "硬石斧");
        add(ModItems.HARD_HOE.get(), "硬石锄");
        add(ModItems.HARD_WAR_HAMMER.get(), "硬石战锤");
        add(ModItems.HARD_BATTLE_AXE.get(), "硬石战斧");
        add(ModItems.HARD_MATTOCK.get(), "硬石鸭嘴锄");
        add(ModItems.HARD_SCYTHE.get(), "硬石镰刀");
        add(ModItems.HARD_SHEARS.get(), "硬石剪刀");
        add(ModItems.HARD_DAGGER.get(), "硬石短剑");
        add(ModItems.HARD_HATCHET.get(), "硬石手斧");

        add(ModItems.ANCIENT_METAL_SWORD.get(), "远古金属剑");
        add(ModItems.ANCIENT_METAL_PICKAXE.get(), "远古金属镐");
        add(ModItems.ANCIENT_METAL_SHOVEL.get(), "远古金属锹");
        add(ModItems.ANCIENT_METAL_AXE.get(), "远古金属斧");
        add(ModItems.ANCIENT_METAL_HOE.get(), "远古金属锄");
        add(ModItems.ANCIENT_METAL_WAR_HAMMER.get(), "远古金属战锤");
        add(ModItems.ANCIENT_METAL_BATTLE_AXE.get(), "远古金属战斧");
        add(ModItems.ANCIENT_METAL_MATTOCK.get(), "远古金属鸭嘴锄");
        add(ModItems.ANCIENT_METAL_SCYTHE.get(), "远古金属镰刀");
        add(ModItems.ANCIENT_METAL_SHEARS.get(), "远古金属剪刀");
        add(ModItems.ANCIENT_METAL_DAGGER.get(), "远古金属短剑");
        add(ModItems.ANCIENT_METAL_HATCHET.get(), "远古金属手斧");

        add(ModItems.MITHRIL_SWORD.get(), "秘银剑");
        add(ModItems.MITHRIL_PICKAXE.get(), "秘银镐");
        add(ModItems.MITHRIL_SHOVEL.get(), "秘银锹");
        add(ModItems.MITHRIL_AXE.get(), "秘银斧");
        add(ModItems.MITHRIL_HOE.get(), "秘银锄");
        add(ModItems.MITHRIL_WAR_HAMMER.get(), "秘银战锤");
        add(ModItems.MITHRIL_BATTLE_AXE.get(), "秘银战斧");
        add(ModItems.MITHRIL_MATTOCK.get(), "秘银鸭嘴锄");
        add(ModItems.MITHRIL_SCYTHE.get(), "秘银镰刀");
        add(ModItems.MITHRIL_SHEARS.get(), "秘银剪刀");
        add(ModItems.MITHRIL_DAGGER.get(), "秘银短剑");
        add(ModItems.MITHRIL_HATCHET.get(), "秘银手斧");

        add(ModItems.ADAMANTIUM_SWORD.get(), "艾德曼剑");
        add(ModItems.ADAMANTIUM_PICKAXE.get(), "艾德曼镐");
        add(ModItems.ADAMANTIUM_SHOVEL.get(), "艾德曼锹");
        add(ModItems.ADAMANTIUM_AXE.get(), "艾德曼斧");
        add(ModItems.ADAMANTIUM_HOE.get(), "艾德曼锄");
        add(ModItems.ADAMANTIUM_WAR_HAMMER.get(), "艾德曼战锤");
        add(ModItems.ADAMANTIUM_BATTLE_AXE.get(), "艾德曼战斧");
        add(ModItems.ADAMANTIUM_MATTOCK.get(), "艾德曼鸭嘴锄");
        add(ModItems.ADAMANTIUM_SCYTHE.get(), "艾德曼镰刀");
        add(ModItems.ADAMANTIUM_SHEARS.get(), "艾德曼剪刀");
        add(ModItems.ADAMANTIUM_DAGGER.get(), "艾德曼短剑");
        add(ModItems.ADAMANTIUM_HATCHET.get(), "艾德曼手斧");

        add("tooltip.item.deprecated","此物品不应当出现在正常生存流程中");
    }
}
