package hhsixhhwkhxh.mite.item;

import hhsixhhwkhxh.mite.custom.ModItemTags;
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
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class ModToolMaterials {

    public static final ToolMaterial WOOD = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 2, 2.0F, 0.0F, 10, ModItemTags.NULL);
    public static final ToolMaterial FLINT = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 4, 4.0F, 1.0F, 0, ModItemTags.NULL);
    public static final ToolMaterial OBSIDIAN = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 8, 4.0F, 1.0F, 0, ModItemTags.NULL);

    public static final ToolMaterial IRON = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 32, 6.0F, 2.0F, 30, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial GOLD = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 16, 12.0F, 0.0F, 50, ItemTags.GOLD_TOOL_MATERIALS);

    public static final ToolMaterial COPPER = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 16, 12.0F, 0.0F, 30, ModItemTags.COPPER_TOOL_MATERIALS);
    public static final ToolMaterial BRONZE = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 20, 12.0F, 0.0F, 30, ModItemTags.COPPER_TOOL_MATERIALS);
    public static final ToolMaterial SILVER_COPPER = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 30, 12.0F, 0.0F, 30, ModItemTags.SILVER_COPPER_TOOL_MATERIALS);
    public static final ToolMaterial SILVER = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 16, 12.0F, 0.0F, 30, ModItemTags.SILVER_TOOL_MATERIALS);

    public static final ToolMaterial RUSTED_IRON = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 12, 12.0F, 0.0F, 0, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial HIGH_CARBON_STEEL = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 42, 12.0F, 0.0F, 30, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial HARD = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 58, 12.0F, 0.0F, 35, ModItemTags.HARD_TOOL_MATERIALS);
    public static final ToolMaterial ANCIENT_METAL = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 84, 12.0F, 0.0F, 40, ModItemTags.ANCIENT_METAL_TOOL_MATERIALS);

    public static final ToolMaterial MITHRIL = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 336, 12.0F, 0.0F, 100, ModItemTags.MITHRIL_TOOL_MATERIALS);
    public static final ToolMaterial ADAMANTIUM = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 1024, 12.0F, 0.0F, 40, ModItemTags.ADAMANTIUM_TOOL_MATERIALS);



    public Item.Properties tool(Item.Properties properties,ToolMaterial material, ToolType toolType,TagKey<Block> mineableBlocks, float attackDamage, float attackSpeed, float disableBlockingForSeconds) {
        return material.applyToolProperties(properties, toolType, mineableBlocks, attackDamage, attackSpeed, disableBlockingForSeconds);
    }

    public Item.Properties pickaxe(Item.Properties properties,ToolMaterial material, float attackDamage, float attackSpeed) {
        return this.tool(properties, material, ToolType.PICKAXE, BlockTags.MINEABLE_WITH_PICKAXE, attackDamage, attackSpeed, 0.0F);
    }

    public Item.Properties axe(Item.Properties properties,ToolMaterial material, float attackDamage, float attackSpeed) {
        return this.tool(properties, material, ToolType.AXE, BlockTags.MINEABLE_WITH_AXE, attackDamage, attackSpeed, 5.0F);
    }

    public Item.Properties hoe(Item.Properties properties,ToolMaterial material, float attackDamage, float attackSpeed) {
        return this.tool(properties, material, ToolType.HOE, BlockTags.MINEABLE_WITH_HOE, attackDamage, attackSpeed, 0.0F);
    }

    public Item.Properties shovel(Item.Properties properties,ToolMaterial material, float attackDamage, float attackSpeed) {
        return this.tool(properties, material, ToolType.SHOVEL, BlockTags.MINEABLE_WITH_SHOVEL, attackDamage, attackSpeed, 0.0F);
    }

    public Item.Properties sword(Item.Properties properties,ToolMaterial material, float attackDamage, float attackSpeed) {
        return material.applySwordProperties(properties, attackDamage, attackSpeed);
    }



    public enum ToolType {
        SWORD(200,"sword"),PICKAXE(300,"pickaxe"),SHOVEL(100,"shovel"),AXE(300,"axe"),HOE(200,"hoe"),
        WAR_HAMMER(500,"war_hammer"),MATTOCK(400,"mattock"),SCYTHE(200,"scythe"),
        SHEARS(200,"shears"),DAGGER(100,"dagger"),HATCHET(100,"hatchet");
        final int unitDurability;
        final String name;
        ToolType(int unitDurability, String name){
            this.unitDurability = unitDurability;
            this.name = name;
        }

        public int getDurability(int durabilityMultiplier) {
            return this.unitDurability * durabilityMultiplier;
        }

        public String getName() {
            return this.name;
        }

    }

    public record ToolMaterial(
            TagKey<Block> incorrectBlocksForDrops, int durability, float speed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems
    ) {

        private Item.Properties applyCommonProperties(Item.Properties properties,ToolType toolType) {
            return properties.durability(toolType.getDurability(durability)).repairable(this.repairItems).enchantable(this.enchantmentValue);
        }

        public Item.Properties applyToolProperties(Item.Properties properties, ToolType toolType,TagKey<Block> mineableBlocks, float attackDamage, float attackSpeed, float disableBlockingForSeconfs) {
            HolderGetter<Block> holdergetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
            return this.applyCommonProperties(properties,toolType)
                    .component(
                            DataComponents.TOOL,
                            new Tool(
                                    List.of(
                                            Tool.Rule.deniesDrops(holdergetter.getOrThrow(this.incorrectBlocksForDrops)),
                                            Tool.Rule.minesAndDrops(holdergetter.getOrThrow(mineableBlocks), this.speed)
                                    ),
                                    1.0F,
                                    1,
                                    true
                            )
                    )
                    .attributes(this.createToolAttributes(attackDamage, attackSpeed))
                    .component(DataComponents.WEAPON, new Weapon(2, disableBlockingForSeconfs));
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
                                    1.0F,
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
