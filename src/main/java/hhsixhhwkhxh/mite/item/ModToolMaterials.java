package hhsixhhwkhxh.mite.item;

import hhsixhhwkhxh.mite.block.ModBlockTags;
import hhsixhhwkhxh.mite.custom.MaterialFamilyType;
import hhsixhhwkhxh.mite.custom.MaterialType;
import hhsixhhwkhxh.mite.datacomponent.MaterialLevel;
import hhsixhhwkhxh.mite.datacomponent.ModDataComponents;
import hhsixhhwkhxh.mite.datacomponent.ReachBonus;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.function.Function;

public class ModToolMaterials {

    public static final ToolMaterial WOOD = new ToolMaterial(MaterialType.WOOD, BlockTags.INCORRECT_FOR_WOODEN_TOOL, 2,  0.0F, 10, 1.0F, ModItemTags.NULL);
    public static final ToolMaterial FLINT = new ToolMaterial(MaterialType.FLINT, BlockTags.INCORRECT_FOR_STONE_TOOL, 4,  1.0F, 1, 1.25F, ModItemTags.NULL);
    public static final ToolMaterial OBSIDIAN = new ToolMaterial(MaterialType.OBSIDIAN, BlockTags.INCORRECT_FOR_STONE_TOOL, 8,  1.5F, 1, 1.5F, ModItemTags.NULL);

    public static final ToolMaterial IRON = new ToolMaterial(MaterialType.IRON, BlockTags.INCORRECT_FOR_IRON_TOOL, 32,  4.0F, 30, 2.0F, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial GOLD = new ToolMaterial(MaterialType.GOLD, BlockTags.INCORRECT_FOR_GOLD_TOOL, 16, 2.0F, 50, 1.75F, ItemTags.GOLD_TOOL_MATERIALS);

    public static final ToolMaterial COPPER = new ToolMaterial(MaterialType.COPPER, BlockTags.INCORRECT_FOR_GOLD_TOOL, 16,  3.0F, 30, 1.75F, ModItemTags.COPPER_TOOL_MATERIALS);
    public static final ToolMaterial BRONZE = new ToolMaterial(MaterialType.BRONZE, BlockTags.INCORRECT_FOR_GOLD_TOOL, 20,  0.0F, 30, 1.75F, ModItemTags.COPPER_TOOL_MATERIALS);
    public static final ToolMaterial SILVER_COPPER = new ToolMaterial(MaterialType.SILVER_COPPER, BlockTags.INCORRECT_FOR_GOLD_TOOL, 30,  3.5F, 30, 1.75F, ModItemTags.SILVER_COPPER_TOOL_MATERIALS);
    public static final ToolMaterial SILVER = new ToolMaterial(MaterialType.SILVER, BlockTags.INCORRECT_FOR_GOLD_TOOL, 16,  3.0F, 30, 1.75F, ModItemTags.SILVER_TOOL_MATERIALS);

    public static final ToolMaterial RUSTED_IRON = new ToolMaterial(MaterialType.RUSTED_IRON, BlockTags.INCORRECT_FOR_GOLD_TOOL, 12,  2.0F, 1, 1.25F, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial HIGH_CARBON_STEEL = new ToolMaterial(MaterialType.HIGH_CARBON_STEEL, BlockTags.INCORRECT_FOR_GOLD_TOOL, 42,  4.0F, 30, 2.0F, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial HARD = new ToolMaterial(MaterialType.HARD, BlockTags.INCORRECT_FOR_GOLD_TOOL, 58,  4.0F, 35, 2.0F, ModItemTags.HARD_TOOL_MATERIALS);
    public static final ToolMaterial ANCIENT_METAL = new ToolMaterial(MaterialType.ANCIENT_METAL, BlockTags.INCORRECT_FOR_GOLD_TOOL, 84,  5.0F, 40, 2.0F, ModItemTags.ANCIENT_METAL_TOOL_MATERIALS);

    public static final ToolMaterial MITHRIL = new ToolMaterial(MaterialType.MITHRIL, BlockTags.INCORRECT_FOR_GOLD_TOOL, 336,  7.0F, 100, 2.5F, ModItemTags.MITHRIL_TOOL_MATERIALS);
    public static final ToolMaterial ADAMANTIUM = new ToolMaterial(MaterialType.ADAMANTIUM, BlockTags.INCORRECT_FOR_GOLD_TOOL, 1024,  6.0F, 40, 3.0F, ModItemTags.ADAMANTIUM_TOOL_MATERIALS);



    public static Item.Properties tool(Item.Properties properties,ToolMaterial material, ToolType toolType,TagKey<Block> mineableBlocks, float attackSpeed, float disableBlockingForSeconds) {
        return material.applyToolProperties(properties, toolType, mineableBlocks, toolType.baseAttackDamage + material.attackDamageBonus, attackSpeed, disableBlockingForSeconds);
    }

    public static Item.Properties pickaxe(Item.Properties properties,ToolMaterial material) {
        return tool(properties, material, ToolType.PICKAXE, ModBlockTags.getToolMineableTag(material.materialType,ToolType.PICKAXE), -2.8F, 0.0F);
    }

    public static Item.Properties axe(Item.Properties properties,ToolMaterial material) {
        return tool(properties, material, ToolType.AXE, ModBlockTags.getToolMineableTag(material.materialType,ToolType.AXE), -3.2F, 5.0F);
    }

    public static Item.Properties hoe(Item.Properties properties,ToolMaterial material) {
        return tool(properties, material, ToolType.HOE, ModBlockTags.getToolMineableTag(material.materialType,ToolType.HOE), -2.0F, 0.0F);
    }

    public static Item.Properties shovel(Item.Properties properties,ToolMaterial material) {
        return tool(properties, material, ToolType.SHOVEL, ModBlockTags.getToolMineableTag(material.materialType,ToolType.SHOVEL), -3.0F, 0.0F);
    }

    public static Item.Properties sword(Item.Properties properties,ToolMaterial material) {
        return material.applySwordProperties(properties, 4, -2.4F);
    }

    public static Item.Properties warHammer(Item.Properties properties,ToolMaterial material) {
        return tool(properties, material, ToolType.WAR_HAMMER, ModBlockTags.getToolMineableTag(material.materialType,ToolType.WAR_HAMMER), -2.8F, 0.0F);
    }

    public static Item.Properties battleAxe(Item.Properties properties,ToolMaterial material) {
        return tool(properties, material, ToolType.BATTLE_AXE, ModBlockTags.getToolMineableTag(material.materialType,ToolType.BATTLE_AXE), -3.2F, 0.0F);
    }

    public static Item.Properties mattock(Item.Properties properties,ToolMaterial material) {
        return tool(properties, material, ToolType.MATTOCK, ModBlockTags.getToolMineableTag(material.materialType,ToolType.MATTOCK), -2.5F, 0.0F);
    }

    public static Item.Properties scythe(Item.Properties properties,ToolMaterial material) {
        return tool(properties, material, ToolType.SCYTHE, ModBlockTags.getToolMineableTag(material.materialType,ToolType.SCYTHE), -2.0F, 0.0F);
    }

    public static Item.Properties shears(Item.Properties properties,ToolMaterial material) {
        return tool(properties, material, ToolType.SHEARS, ModBlockTags.getToolMineableTag(material.materialType,ToolType.SHEARS), 0F, 0.0F);
    }

    public static Item.Properties dagger(Item.Properties properties,ToolMaterial material) {
        return tool(properties, material, ToolType.DAGGER, ModBlockTags.getToolMineableTag(material.materialType,ToolType.DAGGER), -2.2F, 0.0F);
    }

    public static Item.Properties hatchet(Item.Properties properties,ToolMaterial material) {
        return tool(properties, material, ToolType.HATCHET, ModBlockTags.getToolMineableTag(material.materialType,ToolType.HATCHET), -3.0F, 0.0F);
    }

    public enum ToolType {
        SWORD("sword", 200,4, 0.75F,e->2.0F),
        PICKAXE("pickaxe", 300,2,0.75F,e->e),
        SHOVEL("shovel", 100,1,0.75F,e->e),
        AXE("axe", 300,3,0.75F,e->e),
        HOE("hoe", 200,1,0.75F,e->e),
        WAR_HAMMER("war_hammer", 500,2,0.75F,e->e*0.75F),
        BATTLE_AXE("battle_axe",400,4,0.75F,e->e*0.75F),
        MATTOCK("mattock", 400,1,0.75F,e->e*0.75F),
        SCYTHE("scythe", 200,1,1.0F,e->e),
        SHEARS("shears", 200,2,0.5F,e->4.0F),
        DAGGER("dagger", 100,2,0.5F,e->e),
        HATCHET("hatchet",100,2,0.5F,e->e*0.5F);


        final String name;
        final int unitDurability;
        final float baseAttackDamage;
        final float reachBonus;
        final Function<Float,Float> efficiencyModifier;

        ToolType(String name, int unitDurability, float baseAttackDamage, float reachBonus, Function<Float, Float> efficiencyModifier){
            this.unitDurability = unitDurability;
            this.name = name;
            this.baseAttackDamage = baseAttackDamage;
            this.reachBonus = reachBonus;
            this.efficiencyModifier = efficiencyModifier;
        }

        public int getDurability(int durabilityMultiplier) {
            return this.unitDurability * durabilityMultiplier;
        }

        public float getEfficiency(float efficiencyMultiplier) {
            return efficiencyModifier.apply(efficiencyMultiplier);
        }

        public String getName() {
            return this.name;
        }

    }

    public record ToolMaterial(
            MaterialType materialType, TagKey<Block> incorrectBlocksForDrops, int durability, float attackDamageBonus, int enchantmentValue, float efficiencyMultiplier, TagKey<Item> repairItems
    ) {

        private Item.Properties applyCommonProperties(Item.Properties properties,ToolType toolType) {
            return properties.durability(toolType.getDurability(durability)).repairable(this.repairItems).enchantable(this.enchantmentValue);
        }

        public Item.Properties applyToolProperties(Item.Properties properties, ToolType toolType,TagKey<Block> mineableBlocks, float attackDamage, float attackSpeed, float disableBlockingForSeconds) {
            HolderGetter<Block> holdergetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);

            return this.applyCommonProperties(properties,toolType)
                    .component(
                            DataComponents.TOOL,
                            new Tool(
                                    List.of(
                                            Tool.Rule.deniesDrops(holdergetter.getOrThrow(this.incorrectBlocksForDrops)),
                                            Tool.Rule.minesAndDrops(holdergetter.getOrThrow(mineableBlocks), toolType.getEfficiency(efficiencyMultiplier))
                                    ),
                                    0.0F,
                                    1,
                                    true
                            )
                    )
                    .component(ModDataComponents.REACH_BONUS.get(),new ReachBonus(toolType.reachBonus))
                    .component(ModDataComponents.MATERIAL_LEVEL,new MaterialLevel(MaterialFamilyType.getFamily(materialType).getLevel()))
                    .attributes(this.createToolAttributes(attackDamage, attackSpeed))
                    .component(DataComponents.WEAPON, new Weapon(2, disableBlockingForSeconds));
        }

        private ItemAttributeModifiers createToolAttributes(float attackDamage, float attackSpeed) {
            return ItemAttributeModifiers.builder()
                    .add(
                            Attributes.ATTACK_DAMAGE,
                            new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamage + this.attackDamageBonus, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND
                    )
                    .add(
                            Attributes.ATTACK_SPEED,
                            new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND
                    )
                    .build();
        }

        public Item.Properties applySwordProperties(Item.Properties properties, float attackDamage, float attackSpeed) {
            HolderGetter<Block> holdergetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
            return this.applyCommonProperties(properties,ToolType.SWORD)
                    .component(
                            DataComponents.TOOL,
                            new Tool(
                                    List.of(
                                            Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
                                            Tool.Rule.overrideSpeed(holdergetter.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
                                            Tool.Rule.overrideSpeed(holdergetter.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
                                    ),
                                    0.0F,
                                    2,
                                    false
                            )
                    )
                    .attributes(this.createSwordAttributes(attackDamage, attackSpeed))
                    .component(DataComponents.WEAPON, new Weapon(1));
        }

        private ItemAttributeModifiers createSwordAttributes(float attackDamage, float attackSpeed) {
            return ItemAttributeModifiers.builder()
                    .add(
                            Attributes.ATTACK_DAMAGE,
                            new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamage + this.attackDamageBonus, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND
                    )
                    .add(
                            Attributes.ATTACK_SPEED,
                            new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND
                    )
                    .build();
        }
    }
}
