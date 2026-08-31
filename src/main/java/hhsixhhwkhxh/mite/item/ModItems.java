package hhsixhhwkhxh.mite.item;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.custom.MaterialFamilyType;
import hhsixhhwkhxh.mite.datacomponent.MaterialLevel;
import hhsixhhwkhxh.mite.datacomponent.ModDataComponents;
import hhsixhhwkhxh.mite.datacomponent.Moisture;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.enchantment.Enchantable;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;

import static net.minecraft.world.item.component.Consumables.defaultFood;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MiteBreakAll.MODID);
    public static final DeferredItem<Item> WILD_APPLE = ITEMS.registerItem("wild_apple", Item::new,new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    public static final DeferredItem<Item> STRAWBERRIES = ITEMS.registerItem("strawberries", properties ->  new Item(properties.food(Foods.SWEET_BERRIES).component(ModDataComponents.MOISTURE,new Moisture(2))));

    public static final DeferredItem<Item> FLINT_SHARD = ITEMS.registerItem("flint_shard", Item::new, new Item.Properties());
    public static final DeferredItem<Item> OBSIDIAN_SHARD = ITEMS.registerItem("obsidian_shard", Item::new, new Item.Properties());
    public static final DeferredItem<Item> DIAMOND_SHARD = ITEMS.registerItem("diamond_shard", Item::new, new Item.Properties());
    public static final DeferredItem<Item> EMERALD_SHARD = ITEMS.registerItem("emerald_shard", Item::new, new Item.Properties());
    public static final DeferredItem<Item> GLASS_SHARD = ITEMS.registerItem("glass_shard", Item::new, new Item.Properties());
    public static final DeferredItem<Item> QUARTZ_SHARD = ITEMS.registerItem("quartz_shard", Item::new, new Item.Properties());

    public static final DeferredItem<Item> ADAMANTIUM_NUGGET = ITEMS.registerItem("adamantium_nugget", Item::new, new Item.Properties());
    public static final DeferredItem<Item> ANCIENT_METAL_NUGGET = ITEMS.registerItem("ancient_metal_nugget", Item::new, new Item.Properties());
    public static final DeferredItem<Item> HARD_NUGGET = ITEMS.registerItem("hard_nugget", Item::new, new Item.Properties());
    public static final DeferredItem<Item> MERCURY_NUGGET = ITEMS.registerItem("mercury_nugget", Item::new, new Item.Properties());
    public static final DeferredItem<Item> MITHRIL_NUGGET = ITEMS.registerItem("mithril_nugget", Item::new, new Item.Properties());
    public static final DeferredItem<Item> SILVER_NUGGET = ITEMS.registerItem("silver_nugget", Item::new, new Item.Properties());
    public static final DeferredItem<Item> COPPER_NUGGET = ITEMS.registerItem("copper_nugget", Item::new, new Item.Properties());

    public static final DeferredItem<Item> MESH_STRING = ITEMS.registerItem("mesh_string", Item::new, new Item.Properties().durability(16));
    public static final DeferredItem<Item> MESH_LEATHER = ITEMS.registerItem("mesh_leather", Item::new, new Item.Properties().durability(8));

    public static final DeferredItem<Item> ADAMANTIUM_INGOT = ITEMS.registerItem("adamantium_ingot",Item::new,new Item.Properties());
    public static final DeferredItem<Item> ANCIENT_METAL_INGOT = ITEMS.registerItem("ancient_metal_ingot",Item::new,new Item.Properties());
    public static final DeferredItem<Item> HARD_INGOT = ITEMS.registerItem("hard_ingot",Item::new,new Item.Properties());
    public static final DeferredItem<Item> MERCURY_INGOT = ITEMS.registerItem("mercury_ingot",Item::new,new Item.Properties());
    public static final DeferredItem<Item> MITHRIL_INGOT = ITEMS.registerItem("mithril_ingot",Item::new,new Item.Properties());
    public static final DeferredItem<Item> SILVER_INGOT = ITEMS.registerItem("silver_ingot",Item::new,new Item.Properties());
    public static final DeferredItem<Item> TIN_INGOT = ITEMS.registerItem("tin_ingot",Item::new,new Item.Properties());

    public static final DeferredItem<Item> ADAMANTIUM_HELMET = ITEMS.registerItem("adamantium_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ADAMANTIUM, ArmorType.HELMET).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.ADAMANTIUM.level))));
    public static final DeferredItem<Item> ADAMANTIUM_CHESTPLATE = ITEMS.registerItem("adamantium_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ADAMANTIUM, ArmorType.CHESTPLATE).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.ADAMANTIUM.level))));
    public static final DeferredItem<Item> ADAMANTIUM_LEGGINGS = ITEMS.registerItem("adamantium_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ADAMANTIUM, ArmorType.LEGGINGS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.ADAMANTIUM.level))));
    public static final DeferredItem<Item> ADAMANTIUM_BOOTS = ITEMS.registerItem("adamantium_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ADAMANTIUM, ArmorType.BOOTS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.ADAMANTIUM.level))));

    public static final DeferredItem<Item> ANCIENT_METAL_HELMET = ITEMS.registerItem("ancient_metal_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ANCIENT_METAL, ArmorType.HELMET).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.ANCIENT_HARDENED_FAMILY.level))));
    public static final DeferredItem<Item> ANCIENT_METAL_CHESTPLATE = ITEMS.registerItem("ancient_metal_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ANCIENT_METAL, ArmorType.CHESTPLATE).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.ANCIENT_HARDENED_FAMILY.level))));
    public static final DeferredItem<Item> ANCIENT_METAL_LEGGINGS = ITEMS.registerItem("ancient_metal_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ANCIENT_METAL, ArmorType.LEGGINGS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.ANCIENT_HARDENED_FAMILY.level))));
    public static final DeferredItem<Item> ANCIENT_METAL_BOOTS = ITEMS.registerItem("ancient_metal_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ANCIENT_METAL, ArmorType.BOOTS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.ANCIENT_HARDENED_FAMILY.level))));

    public static final DeferredItem<Item> BRONZE_HELMET = ITEMS.registerItem("bronze_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.BRONZE, ArmorType.HELMET).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));
    public static final DeferredItem<Item> BRONZE_CHESTPLATE = ITEMS.registerItem("bronze_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.BRONZE, ArmorType.CHESTPLATE).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));
    public static final DeferredItem<Item> BRONZE_LEGGINGS = ITEMS.registerItem("bronze_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.BRONZE, ArmorType.LEGGINGS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));
    public static final DeferredItem<Item> BRONZE_BOOTS = ITEMS.registerItem("bronze_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.BRONZE, ArmorType.BOOTS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));

    public static final DeferredItem<Item> HARD_HELMET = ITEMS.registerItem("hard_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HARD, ArmorType.HELMET).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.ANCIENT_HARDENED_FAMILY.level))));
    public static final DeferredItem<Item> HARD_CHESTPLATE = ITEMS.registerItem("hard_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HARD, ArmorType.CHESTPLATE).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.ANCIENT_HARDENED_FAMILY.level))));
    public static final DeferredItem<Item> HARD_LEGGINGS = ITEMS.registerItem("hard_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HARD, ArmorType.LEGGINGS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.ANCIENT_HARDENED_FAMILY.level))));
    public static final DeferredItem<Item> HARD_BOOTS = ITEMS.registerItem("hard_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HARD, ArmorType.BOOTS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.ANCIENT_HARDENED_FAMILY.level))));

    public static final DeferredItem<Item> HIGH_CARBON_STEEL_HELMET = ITEMS.registerItem("high_carbon_steel_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HIGH_CARBON_STEEL, ArmorType.HELMET).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.IRON_STEEL_FAMILY.level))));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_CHESTPLATE = ITEMS.registerItem("high_carbon_steel_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HIGH_CARBON_STEEL, ArmorType.CHESTPLATE).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.IRON_STEEL_FAMILY.level))));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_LEGGINGS = ITEMS.registerItem("high_carbon_steel_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HIGH_CARBON_STEEL, ArmorType.LEGGINGS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.IRON_STEEL_FAMILY.level))));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_BOOTS = ITEMS.registerItem("high_carbon_steel_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HIGH_CARBON_STEEL, ArmorType.BOOTS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.IRON_STEEL_FAMILY.level))));

    public static final DeferredItem<Item> MITHRIL_HELMET = ITEMS.registerItem("mithril_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.MITHRIL, ArmorType.HELMET).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.MITHRIL.level))));
    public static final DeferredItem<Item> MITHRIL_CHESTPLATE = ITEMS.registerItem("mithril_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.MITHRIL, ArmorType.CHESTPLATE).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.MITHRIL.level))));
    public static final DeferredItem<Item> MITHRIL_LEGGINGS = ITEMS.registerItem("mithril_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.MITHRIL, ArmorType.LEGGINGS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.MITHRIL.level))));
    public static final DeferredItem<Item> MITHRIL_BOOTS = ITEMS.registerItem("mithril_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.MITHRIL, ArmorType.BOOTS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.MITHRIL.level))));

    public static final DeferredItem<Item> RUSTED_IRON_HELMET = ITEMS.registerItem("rusted_iron_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.RUSTED_IRON, ArmorType.HELMET).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.IRON_STEEL_FAMILY.level))));
    public static final DeferredItem<Item> RUSTED_IRON_CHESTPLATE = ITEMS.registerItem("rusted_iron_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.RUSTED_IRON, ArmorType.CHESTPLATE).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.IRON_STEEL_FAMILY.level))));
    public static final DeferredItem<Item> RUSTED_IRON_LEGGINGS = ITEMS.registerItem("rusted_iron_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.RUSTED_IRON, ArmorType.LEGGINGS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.IRON_STEEL_FAMILY.level))));
    public static final DeferredItem<Item> RUSTED_IRON_BOOTS = ITEMS.registerItem("rusted_iron_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.RUSTED_IRON, ArmorType.BOOTS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.IRON_STEEL_FAMILY.level))));

    public static final DeferredItem<Item> SILVER_HELMET = ITEMS.registerItem("silver_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER, ArmorType.HELMET).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));
    public static final DeferredItem<Item> SILVER_CHESTPLATE = ITEMS.registerItem("silver_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER, ArmorType.CHESTPLATE).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));
    public static final DeferredItem<Item> SILVER_LEGGINGS = ITEMS.registerItem("silver_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER, ArmorType.LEGGINGS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));
    public static final DeferredItem<Item> SILVER_BOOTS = ITEMS.registerItem("silver_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER, ArmorType.BOOTS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));

    public static final DeferredItem<Item> SILVER_COPPER_HELMET = ITEMS.registerItem("silver_copper_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER_COPPER, ArmorType.HELMET).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));
    public static final DeferredItem<Item> SILVER_COPPER_CHESTPLATE = ITEMS.registerItem("silver_copper_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER_COPPER, ArmorType.CHESTPLATE).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));
    public static final DeferredItem<Item> SILVER_COPPER_LEGGINGS = ITEMS.registerItem("silver_copper_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER_COPPER, ArmorType.LEGGINGS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));
    public static final DeferredItem<Item> SILVER_COPPER_BOOTS = ITEMS.registerItem("silver_copper_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER_COPPER, ArmorType.BOOTS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));

    public static final DeferredItem<Item> COPPER_HELMET = ITEMS.registerItem("copper_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.COPPER, ArmorType.HELMET).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));
    public static final DeferredItem<Item> COPPER_CHESTPLATE = ITEMS.registerItem("copper_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.COPPER, ArmorType.CHESTPLATE).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));
    public static final DeferredItem<Item> COPPER_LEGGINGS = ITEMS.registerItem("copper_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.COPPER, ArmorType.LEGGINGS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));
    public static final DeferredItem<Item> COPPER_BOOTS = ITEMS.registerItem("copper_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.COPPER, ArmorType.BOOTS).component(ModDataComponents.MATERIAL_LEVEL, new MaterialLevel(MaterialFamilyType.GOLD_COPPER_FAMILY.level))));


    public static final DeferredItem<Item> FLINT_PICKAXE = ITEMS.registerItem("flint_pickaxe",(props)-> new Item(ModToolMaterials.pickaxe(props,ModToolMaterials.FLINT)));
    public static final DeferredItem<Item> FLINT_SHOVEL = ITEMS.registerItem("flint_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.FLINT)));
    public static final DeferredItem<Item> FLINT_AXE = ITEMS.registerItem("flint_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.FLINT)));
    public static final DeferredItem<Item> FLINT_HATCHET = ITEMS.registerItem("flint_hatchet",(props)-> new Item(ModToolMaterials.hatchet(props,ModToolMaterials.FLINT)));

    public static final DeferredItem<Item> OBSIDIAN_SHOVEL = ITEMS.registerItem("obsidian_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.OBSIDIAN)));
    public static final DeferredItem<Item> OBSIDIAN_AXE = ITEMS.registerItem("obsidian_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.OBSIDIAN)));
    public static final DeferredItem<Item> OBSIDIAN_HATCHET = ITEMS.registerItem("obsidian_hatchet",(props)-> new Item(ModToolMaterials.hatchet(props,ModToolMaterials.OBSIDIAN)));

    public static final DeferredItem<Item> IRON_WAR_HAMMER = ITEMS.registerItem("iron_war_hammer",(props)-> new Item(ModToolMaterials.warHammer(props,ModToolMaterials.IRON)));
    public static final DeferredItem<Item> IRON_BATTLE_AXE = ITEMS.registerItem("iron_battle_axe",(props)-> new Item(ModToolMaterials.battleAxe(props,ModToolMaterials.IRON)));
    public static final DeferredItem<Item> IRON_MATTOCK = ITEMS.registerItem("iron_mattock",(props)-> new Item(ModToolMaterials.mattock(props,ModToolMaterials.IRON)));
    public static final DeferredItem<Item> IRON_SCYTHE = ITEMS.registerItem("iron_scythe",(props)-> new Item(ModToolMaterials.scythe(props,ModToolMaterials.IRON)));
    public static final DeferredItem<Item> IRON_SHEARS = ITEMS.registerItem("iron_shears",(props)-> new Item(ModToolMaterials.shears(props,ModToolMaterials.IRON)));
    public static final DeferredItem<Item> IRON_DAGGER = ITEMS.registerItem("iron_dagger",(props)-> new Item(ModToolMaterials.dagger(props,ModToolMaterials.IRON)));
    public static final DeferredItem<Item> IRON_HATCHET = ITEMS.registerItem("iron_hatchet",(props)-> new Item(ModToolMaterials.hatchet(props,ModToolMaterials.IRON)));

    public static final DeferredItem<Item> GOLD_WAR_HAMMER = ITEMS.registerItem("gold_war_hammer",(props)-> new Item(ModToolMaterials.warHammer(props,ModToolMaterials.GOLD)));
    public static final DeferredItem<Item> GOLD_BATTLE_AXE = ITEMS.registerItem("gold_battle_axe",(props)-> new Item(ModToolMaterials.battleAxe(props,ModToolMaterials.GOLD)));
    public static final DeferredItem<Item> GOLD_MATTOCK = ITEMS.registerItem("gold_mattock",(props)-> new Item(ModToolMaterials.mattock(props,ModToolMaterials.GOLD)));
    public static final DeferredItem<Item> GOLD_SCYTHE = ITEMS.registerItem("gold_scythe",(props)-> new Item(ModToolMaterials.scythe(props,ModToolMaterials.GOLD)));
    public static final DeferredItem<Item> GOLD_SHEARS = ITEMS.registerItem("gold_shears",(props)-> new Item(ModToolMaterials.shears(props,ModToolMaterials.GOLD)));
    public static final DeferredItem<Item> GOLD_DAGGER = ITEMS.registerItem("gold_dagger",(props)-> new Item(ModToolMaterials.dagger(props,ModToolMaterials.GOLD)));
    public static final DeferredItem<Item> GOLD_HATCHET = ITEMS.registerItem("gold_hatchet",(props)-> new Item(ModToolMaterials.hatchet(props,ModToolMaterials.GOLD)));

    public static final DeferredItem<Item> COPPER_WAR_HAMMER = ITEMS.registerItem("copper_war_hammer",(props)-> new Item(ModToolMaterials.warHammer(props,ModToolMaterials.COPPER)));
    public static final DeferredItem<Item> COPPER_BATTLE_AXE = ITEMS.registerItem("copper_battle_axe",(props)-> new Item(ModToolMaterials.battleAxe(props,ModToolMaterials.COPPER)));
    public static final DeferredItem<Item> COPPER_MATTOCK = ITEMS.registerItem("copper_mattock",(props)-> new Item(ModToolMaterials.mattock(props,ModToolMaterials.COPPER)));
    public static final DeferredItem<Item> COPPER_SCYTHE = ITEMS.registerItem("copper_scythe",(props)-> new Item(ModToolMaterials.scythe(props,ModToolMaterials.COPPER)));
    public static final DeferredItem<Item> COPPER_SHEARS = ITEMS.registerItem("copper_shears",(props)-> new Item(ModToolMaterials.shears(props,ModToolMaterials.COPPER)));
    public static final DeferredItem<Item> COPPER_DAGGER = ITEMS.registerItem("copper_dagger",(props)-> new Item(ModToolMaterials.dagger(props,ModToolMaterials.COPPER)));
    public static final DeferredItem<Item> COPPER_HATCHET = ITEMS.registerItem("copper_hatchet",(props)-> new Item(ModToolMaterials.hatchet(props,ModToolMaterials.COPPER)));

    public static final DeferredItem<Item> BRONZE_SWORD = ITEMS.registerItem("bronze_sword",(props)-> new Item(ModToolMaterials.sword(props,ModToolMaterials.BRONZE)));
    public static final DeferredItem<Item> BRONZE_PICKAXE = ITEMS.registerItem("bronze_pickaxe",(props)-> new Item(ModToolMaterials.pickaxe(props,ModToolMaterials.BRONZE)));
    public static final DeferredItem<Item> BRONZE_SHOVEL = ITEMS.registerItem("bronze_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.BRONZE)));
    public static final DeferredItem<Item> BRONZE_AXE = ITEMS.registerItem("bronze_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.BRONZE)));
    public static final DeferredItem<Item> BRONZE_WAR_HAMMER = ITEMS.registerItem("bronze_war_hammer",(props)-> new Item(ModToolMaterials.warHammer(props,ModToolMaterials.BRONZE)));
    public static final DeferredItem<Item> BRONZE_BATTLE_AXE = ITEMS.registerItem("bronze_battle_axe",(props)-> new Item(ModToolMaterials.battleAxe(props,ModToolMaterials.BRONZE)));

    public static final DeferredItem<Item> SILVER_COPPER_SWORD = ITEMS.registerItem("silver_copper_sword",(props)-> new Item(ModToolMaterials.sword(props,ModToolMaterials.SILVER_COPPER)));
    public static final DeferredItem<Item> SILVER_COPPER_PICKAXE = ITEMS.registerItem("silver_copper_pickaxe",(props)-> new Item(ModToolMaterials.pickaxe(props,ModToolMaterials.SILVER_COPPER)));
    public static final DeferredItem<Item> SILVER_COPPER_SHOVEL = ITEMS.registerItem("silver_copper_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.SILVER_COPPER)));
    public static final DeferredItem<Item> SILVER_COPPER_AXE = ITEMS.registerItem("silver_copper_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.SILVER_COPPER)));
    public static final DeferredItem<Item> SILVER_COPPER_WAR_HAMMER = ITEMS.registerItem("silver_copper_war_hammer",(props)-> new Item(ModToolMaterials.warHammer(props,ModToolMaterials.SILVER_COPPER)));
    public static final DeferredItem<Item> SILVER_COPPER_BATTLE_AXE = ITEMS.registerItem("silver_copper_battle_axe",(props)-> new Item(ModToolMaterials.battleAxe(props,ModToolMaterials.SILVER_COPPER)));

    public static final DeferredItem<Item> SILVER_SWORD = ITEMS.registerItem("silver_sword",(props)-> new Item(ModToolMaterials.sword(props,ModToolMaterials.SILVER)));
    public static final DeferredItem<Item> SILVER_PICKAXE = ITEMS.registerItem("silver_pickaxe",(props)-> new Item(ModToolMaterials.pickaxe(props,ModToolMaterials.SILVER)));
    public static final DeferredItem<Item> SILVER_SHOVEL = ITEMS.registerItem("silver_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.SILVER)));
    public static final DeferredItem<Item> SILVER_AXE = ITEMS.registerItem("silver_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.SILVER)));
    public static final DeferredItem<Item> SILVER_HOE = ITEMS.registerItem("silver_hoe",(props)-> new Item(ModToolMaterials.hoe(props,ModToolMaterials.SILVER)));
    public static final DeferredItem<Item> SILVER_WAR_HAMMER = ITEMS.registerItem("silver_war_hammer",(props)-> new Item(ModToolMaterials.warHammer(props,ModToolMaterials.SILVER)));
    public static final DeferredItem<Item> SILVER_BATTLE_AXE = ITEMS.registerItem("silver_battle_axe",(props)-> new Item(ModToolMaterials.battleAxe(props,ModToolMaterials.SILVER)));
    public static final DeferredItem<Item> SILVER_MATTOCK = ITEMS.registerItem("silver_mattock",(props)-> new Item(ModToolMaterials.mattock(props,ModToolMaterials.SILVER)));
    public static final DeferredItem<Item> SILVER_SCYTHE = ITEMS.registerItem("silver_scythe",(props)-> new Item(ModToolMaterials.scythe(props,ModToolMaterials.SILVER)));
    public static final DeferredItem<Item> SILVER_SHEARS = ITEMS.registerItem("silver_shears",(props)-> new Item(ModToolMaterials.shears(props,ModToolMaterials.SILVER)));
    public static final DeferredItem<Item> SILVER_DAGGER = ITEMS.registerItem("silver_dagger",(props)-> new Item(ModToolMaterials.dagger(props,ModToolMaterials.SILVER)));
    public static final DeferredItem<Item> SILVER_HATCHET = ITEMS.registerItem("silver_hatchet",(props)-> new Item(ModToolMaterials.hatchet(props,ModToolMaterials.SILVER)));

    public static final DeferredItem<Item> RUSTED_IRON_SWORD = ITEMS.registerItem("rusted_iron_sword",(props)-> new Item(ModToolMaterials.sword(props,ModToolMaterials.RUSTED_IRON)));
    public static final DeferredItem<Item> RUSTED_IRON_PICKAXE = ITEMS.registerItem("rusted_iron_pickaxe",(props)-> new Item(ModToolMaterials.pickaxe(props,ModToolMaterials.RUSTED_IRON)));
    public static final DeferredItem<Item> RUSTED_IRON_SHOVEL = ITEMS.registerItem("rusted_iron_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.RUSTED_IRON)));
    public static final DeferredItem<Item> RUSTED_IRON_AXE = ITEMS.registerItem("rusted_iron_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.RUSTED_IRON)));
    public static final DeferredItem<Item> RUSTED_IRON_HOE = ITEMS.registerItem("rusted_iron_hoe",(props)-> new Item(ModToolMaterials.hoe(props,ModToolMaterials.RUSTED_IRON)));
    public static final DeferredItem<Item> RUSTED_IRON_WAR_HAMMER = ITEMS.registerItem("rusted_iron_war_hammer",(props)-> new Item(ModToolMaterials.warHammer(props,ModToolMaterials.RUSTED_IRON)));
    public static final DeferredItem<Item> RUSTED_IRON_BATTLE_AXE = ITEMS.registerItem("rusted_iron_battle_axe",(props)-> new Item(ModToolMaterials.battleAxe(props,ModToolMaterials.RUSTED_IRON)));
    public static final DeferredItem<Item> RUSTED_IRON_MATTOCK = ITEMS.registerItem("rusted_iron_mattock",(props)-> new Item(ModToolMaterials.mattock(props,ModToolMaterials.RUSTED_IRON)));
    public static final DeferredItem<Item> RUSTED_IRON_SCYTHE = ITEMS.registerItem("rusted_iron_scythe",(props)-> new Item(ModToolMaterials.scythe(props,ModToolMaterials.RUSTED_IRON)));
    public static final DeferredItem<Item> RUSTED_IRON_SHEARS = ITEMS.registerItem("rusted_iron_shears",(props)-> new Item(ModToolMaterials.shears(props,ModToolMaterials.RUSTED_IRON)));
    public static final DeferredItem<Item> RUSTED_IRON_DAGGER = ITEMS.registerItem("rusted_iron_dagger",(props)-> new Item(ModToolMaterials.dagger(props,ModToolMaterials.RUSTED_IRON)));
    public static final DeferredItem<Item> RUSTED_IRON_HATCHET = ITEMS.registerItem("rusted_iron_hatchet",(props)-> new Item(ModToolMaterials.hatchet(props,ModToolMaterials.RUSTED_IRON)));

    public static final DeferredItem<Item> HIGH_CARBON_STEEL_SWORD = ITEMS.registerItem("high_carbon_steel_sword",(props)-> new Item(ModToolMaterials.sword(props,ModToolMaterials.HIGH_CARBON_STEEL)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_PICKAXE = ITEMS.registerItem("high_carbon_steel_pickaxe",(props)-> new Item(ModToolMaterials.pickaxe(props,ModToolMaterials.HIGH_CARBON_STEEL)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_SHOVEL = ITEMS.registerItem("high_carbon_steel_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.HIGH_CARBON_STEEL)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_AXE = ITEMS.registerItem("high_carbon_steel_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.HIGH_CARBON_STEEL)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_HOE = ITEMS.registerItem("high_carbon_steel_hoe",(props)-> new Item(ModToolMaterials.hoe(props,ModToolMaterials.HIGH_CARBON_STEEL)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_WAR_HAMMER = ITEMS.registerItem("high_carbon_steel_war_hammer",(props)-> new Item(ModToolMaterials.warHammer(props,ModToolMaterials.HIGH_CARBON_STEEL)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_BATTLE_AXE = ITEMS.registerItem("high_carbon_steel_battle_axe",(props)-> new Item(ModToolMaterials.battleAxe(props,ModToolMaterials.HIGH_CARBON_STEEL)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_MATTOCK = ITEMS.registerItem("high_carbon_steel_mattock",(props)-> new Item(ModToolMaterials.mattock(props,ModToolMaterials.HIGH_CARBON_STEEL)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_SCYTHE = ITEMS.registerItem("high_carbon_steel_scythe",(props)-> new Item(ModToolMaterials.scythe(props,ModToolMaterials.HIGH_CARBON_STEEL)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_SHEARS = ITEMS.registerItem("high_carbon_steel_shears",(props)-> new Item(ModToolMaterials.shears(props,ModToolMaterials.HIGH_CARBON_STEEL)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_DAGGER = ITEMS.registerItem("high_carbon_steel_dagger",(props)-> new Item(ModToolMaterials.dagger(props,ModToolMaterials.HIGH_CARBON_STEEL)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_HATCHET = ITEMS.registerItem("high_carbon_steel_hatchet",(props)-> new Item(ModToolMaterials.hatchet(props,ModToolMaterials.HIGH_CARBON_STEEL)));

    public static final DeferredItem<Item> HARD_SWORD = ITEMS.registerItem("hard_sword",(props)-> new Item(ModToolMaterials.sword(props,ModToolMaterials.HARD)));
    public static final DeferredItem<Item> HARD_PICKAXE = ITEMS.registerItem("hard_pickaxe",(props)-> new Item(ModToolMaterials.pickaxe(props,ModToolMaterials.HARD)));
    public static final DeferredItem<Item> HARD_SHOVEL = ITEMS.registerItem("hard_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.HARD)));
    public static final DeferredItem<Item> HARD_AXE = ITEMS.registerItem("hard_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.HARD)));
    public static final DeferredItem<Item> HARD_HOE = ITEMS.registerItem("hard_hoe",(props)-> new Item(ModToolMaterials.hoe(props,ModToolMaterials.HARD)));
    public static final DeferredItem<Item> HARD_WAR_HAMMER = ITEMS.registerItem("hard_war_hammer",(props)-> new Item(ModToolMaterials.warHammer(props,ModToolMaterials.HARD)));
    public static final DeferredItem<Item> HARD_BATTLE_AXE = ITEMS.registerItem("hard_battle_axe",(props)-> new Item(ModToolMaterials.battleAxe(props,ModToolMaterials.HARD)));
    public static final DeferredItem<Item> HARD_MATTOCK = ITEMS.registerItem("hard_mattock",(props)-> new Item(ModToolMaterials.mattock(props,ModToolMaterials.HARD)));
    public static final DeferredItem<Item> HARD_SCYTHE = ITEMS.registerItem("hard_scythe",(props)-> new Item(ModToolMaterials.scythe(props,ModToolMaterials.HARD)));
    public static final DeferredItem<Item> HARD_SHEARS = ITEMS.registerItem("hard_shears",(props)-> new Item(ModToolMaterials.shears(props,ModToolMaterials.HARD)));
    public static final DeferredItem<Item> HARD_DAGGER = ITEMS.registerItem("hard_dagger",(props)-> new Item(ModToolMaterials.dagger(props,ModToolMaterials.HARD)));
    public static final DeferredItem<Item> HARD_HATCHET = ITEMS.registerItem("hard_hatchet",(props)-> new Item(ModToolMaterials.hatchet(props,ModToolMaterials.HARD)));

    public static final DeferredItem<Item> ANCIENT_METAL_SWORD = ITEMS.registerItem("ancient_metal_sword",(props)-> new Item(ModToolMaterials.sword(props,ModToolMaterials.ANCIENT_METAL)));
    public static final DeferredItem<Item> ANCIENT_METAL_PICKAXE = ITEMS.registerItem("ancient_metal_pickaxe",(props)-> new Item(ModToolMaterials.pickaxe(props,ModToolMaterials.ANCIENT_METAL)));
    public static final DeferredItem<Item> ANCIENT_METAL_SHOVEL = ITEMS.registerItem("ancient_metal_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.ANCIENT_METAL)));
    public static final DeferredItem<Item> ANCIENT_METAL_AXE = ITEMS.registerItem("ancient_metal_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.ANCIENT_METAL)));
    public static final DeferredItem<Item> ANCIENT_METAL_HOE = ITEMS.registerItem("ancient_metal_hoe",(props)-> new Item(ModToolMaterials.hoe(props,ModToolMaterials.ANCIENT_METAL)));
    public static final DeferredItem<Item> ANCIENT_METAL_WAR_HAMMER = ITEMS.registerItem("ancient_metal_war_hammer",(props)-> new Item(ModToolMaterials.warHammer(props,ModToolMaterials.ANCIENT_METAL)));
    public static final DeferredItem<Item> ANCIENT_METAL_BATTLE_AXE = ITEMS.registerItem("ancient_metal_battle_axe",(props)-> new Item(ModToolMaterials.battleAxe(props,ModToolMaterials.ANCIENT_METAL)));
    public static final DeferredItem<Item> ANCIENT_METAL_MATTOCK = ITEMS.registerItem("ancient_metal_mattock",(props)-> new Item(ModToolMaterials.mattock(props,ModToolMaterials.ANCIENT_METAL)));
    public static final DeferredItem<Item> ANCIENT_METAL_SCYTHE = ITEMS.registerItem("ancient_metal_scythe",(props)-> new Item(ModToolMaterials.scythe(props,ModToolMaterials.ANCIENT_METAL)));
    public static final DeferredItem<Item> ANCIENT_METAL_SHEARS = ITEMS.registerItem("ancient_metal_shears",(props)-> new Item(ModToolMaterials.shears(props,ModToolMaterials.ANCIENT_METAL)));
    public static final DeferredItem<Item> ANCIENT_METAL_DAGGER = ITEMS.registerItem("ancient_metal_dagger",(props)-> new Item(ModToolMaterials.dagger(props,ModToolMaterials.ANCIENT_METAL)));
    public static final DeferredItem<Item> ANCIENT_METAL_HATCHET = ITEMS.registerItem("ancient_metal_hatchet",(props)-> new Item(ModToolMaterials.hatchet(props,ModToolMaterials.ANCIENT_METAL)));

    public static final DeferredItem<Item> MITHRIL_SWORD = ITEMS.registerItem("mithril_sword",(props)-> new Item(ModToolMaterials.sword(props,ModToolMaterials.MITHRIL)));
    public static final DeferredItem<Item> MITHRIL_PICKAXE = ITEMS.registerItem("mithril_pickaxe",(props)-> new Item(ModToolMaterials.pickaxe(props,ModToolMaterials.MITHRIL)));
    public static final DeferredItem<Item> MITHRIL_SHOVEL = ITEMS.registerItem("mithril_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.MITHRIL)));
    public static final DeferredItem<Item> MITHRIL_AXE = ITEMS.registerItem("mithril_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.MITHRIL)));
    public static final DeferredItem<Item> MITHRIL_HOE = ITEMS.registerItem("mithril_hoe",(props)-> new Item(ModToolMaterials.hoe(props,ModToolMaterials.MITHRIL)));
    public static final DeferredItem<Item> MITHRIL_WAR_HAMMER = ITEMS.registerItem("mithril_war_hammer",(props)-> new Item(ModToolMaterials.warHammer(props,ModToolMaterials.MITHRIL)));
    public static final DeferredItem<Item> MITHRIL_BATTLE_AXE = ITEMS.registerItem("mithril_battle_axe",(props)-> new Item(ModToolMaterials.battleAxe(props,ModToolMaterials.MITHRIL)));
    public static final DeferredItem<Item> MITHRIL_MATTOCK = ITEMS.registerItem("mithril_mattock",(props)-> new Item(ModToolMaterials.mattock(props,ModToolMaterials.MITHRIL)));
    public static final DeferredItem<Item> MITHRIL_SCYTHE = ITEMS.registerItem("mithril_scythe",(props)-> new Item(ModToolMaterials.scythe(props,ModToolMaterials.MITHRIL)));
    public static final DeferredItem<Item> MITHRIL_SHEARS = ITEMS.registerItem("mithril_shears",(props)-> new Item(ModToolMaterials.shears(props,ModToolMaterials.MITHRIL)));
    public static final DeferredItem<Item> MITHRIL_DAGGER = ITEMS.registerItem("mithril_dagger",(props)-> new Item(ModToolMaterials.dagger(props,ModToolMaterials.MITHRIL)));
    public static final DeferredItem<Item> MITHRIL_HATCHET = ITEMS.registerItem("mithril_hatchet",(props)-> new Item(ModToolMaterials.hatchet(props,ModToolMaterials.MITHRIL)));

    public static final DeferredItem<Item> ADAMANTIUM_SWORD = ITEMS.registerItem("adamantium_sword",(props)-> new Item(ModToolMaterials.sword(props,ModToolMaterials.ADAMANTIUM)));
    public static final DeferredItem<Item> ADAMANTIUM_PICKAXE = ITEMS.registerItem("adamantium_pickaxe",(props)-> new Item(ModToolMaterials.pickaxe(props,ModToolMaterials.ADAMANTIUM)));
    public static final DeferredItem<Item> ADAMANTIUM_SHOVEL = ITEMS.registerItem("adamantium_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.ADAMANTIUM)));
    public static final DeferredItem<Item> ADAMANTIUM_AXE = ITEMS.registerItem("adamantium_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.ADAMANTIUM)));
    public static final DeferredItem<Item> ADAMANTIUM_HOE = ITEMS.registerItem("adamantium_hoe",(props)-> new Item(ModToolMaterials.hoe(props,ModToolMaterials.ADAMANTIUM)));
    public static final DeferredItem<Item> ADAMANTIUM_WAR_HAMMER = ITEMS.registerItem("adamantium_war_hammer",(props)-> new Item(ModToolMaterials.warHammer(props,ModToolMaterials.ADAMANTIUM)));
    public static final DeferredItem<Item> ADAMANTIUM_BATTLE_AXE = ITEMS.registerItem("adamantium_battle_axe",(props)-> new Item(ModToolMaterials.battleAxe(props,ModToolMaterials.ADAMANTIUM)));
    public static final DeferredItem<Item> ADAMANTIUM_MATTOCK = ITEMS.registerItem("adamantium_mattock",(props)-> new Item(ModToolMaterials.mattock(props,ModToolMaterials.ADAMANTIUM)));
    public static final DeferredItem<Item> ADAMANTIUM_SCYTHE = ITEMS.registerItem("adamantium_scythe",(props)-> new Item(ModToolMaterials.scythe(props,ModToolMaterials.ADAMANTIUM)));
    public static final DeferredItem<Item> ADAMANTIUM_SHEARS = ITEMS.registerItem("adamantium_shears",(props)-> new Item(ModToolMaterials.shears(props,ModToolMaterials.ADAMANTIUM)));
    public static final DeferredItem<Item> ADAMANTIUM_DAGGER = ITEMS.registerItem("adamantium_dagger",(props)-> new Item(ModToolMaterials.dagger(props,ModToolMaterials.ADAMANTIUM)));
    public static final DeferredItem<Item> ADAMANTIUM_HATCHET = ITEMS.registerItem("adamantium_hatchet",(props)-> new Item(ModToolMaterials.hatchet(props,ModToolMaterials.ADAMANTIUM)));

    public static final Map<Item, DeferredItem<Item>> proxyItemMap = Map.ofEntries(
            Map.entry(Items.WOODEN_SHOVEL,
                    ITEMS.registerItem("proxy_wooden_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.WOOD)))
            ),
            Map.entry(Items.IRON_SWORD,
                    ITEMS.registerItem("proxy_iron_sword",(props)-> new Item(ModToolMaterials.sword(props,ModToolMaterials.IRON)))
            ),
            Map.entry(Items.IRON_PICKAXE,
                    ITEMS.registerItem("proxy_iron_pickaxe",(props)-> new Item(ModToolMaterials.pickaxe(props,ModToolMaterials.IRON)))
            ),
            Map.entry(Items.IRON_SHOVEL,
                    ITEMS.registerItem("proxy_iron_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.IRON)))
            ),
            Map.entry(Items.IRON_AXE,
                    ITEMS.registerItem("proxy_iron_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.IRON)))
            ),
            Map.entry(Items.IRON_HOE,
                    ITEMS.registerItem("proxy_iron_hoe",(props)-> new Item(ModToolMaterials.hoe(props,ModToolMaterials.IRON)))
            ),
            Map.entry(Items.GOLDEN_SWORD,
                    ITEMS.registerItem("proxy_golden_sword",(props)-> new Item(ModToolMaterials.sword(props,ModToolMaterials.GOLD)))
            ),
            Map.entry(Items.GOLDEN_PICKAXE,
                    ITEMS.registerItem("proxy_golden_pickaxe",(props)-> new Item(ModToolMaterials.pickaxe(props,ModToolMaterials.GOLD)))
            ),
            Map.entry(Items.GOLDEN_SHOVEL,
                    ITEMS.registerItem("proxy_golden_shovel",(props)-> new Item(ModToolMaterials.shovel(props,ModToolMaterials.GOLD)))
            ),
            Map.entry(Items.GOLDEN_AXE,
                    ITEMS.registerItem("proxy_golden_axe",(props)-> new Item(ModToolMaterials.axe(props,ModToolMaterials.GOLD)))
            ),
            Map.entry(Items.GOLDEN_HOE,
                    ITEMS.registerItem("proxy_golden_hoe",(props)-> new Item(ModToolMaterials.hoe(props,ModToolMaterials.GOLD)))
            )
    );

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);

        modifyVanillaItem(eventBus);
    }


    public static void modifyVanillaItem(IEventBus eventBus){
        Consumable GAConsumable = defaultFood()
                .onConsume(
                        new ApplyStatusEffectsConsumeEffect(
                                List.of(
                                        new MobEffectInstance(MobEffects.REGENERATION, 1200, 0)
                                )
                        )
                )
                .build();
        Consumable EGAConsumable = defaultFood()
                .onConsume(
                        new ApplyStatusEffectsConsumeEffect(
                                List.of(
                                        new MobEffectInstance(MobEffects.REGENERATION, 1200, 1),
                                        new MobEffectInstance(MobEffects.RESISTANCE, 1200, 0),
                                        new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0)
                                )
                        )
                )
                .build();
        eventBus.addListener((ModifyDefaultComponentsEvent event)->{
            event.modify(Items.GOLDEN_APPLE,(builder)->{
                builder.set(DataComponents.CONSUMABLE,GAConsumable);
                builder.set(DataComponents.ENCHANTABLE,new Enchantable(60));
            });
            event.modify(Items.ENCHANTED_GOLDEN_APPLE,(builder)->{
                builder.set(DataComponents.CONSUMABLE,EGAConsumable);
            });

            modifyVanillaArmor(event);
        });
    }
    public static Item.Properties humanoidArmor(Item.Properties properties, ModArmorMaterials.ModArmorMaterial material, ArmorType type) {
        return properties.durability(type.getDurability(material.durability()))
                .attributes(material.createAttributes(type))
                .enchantable(material.enchantmentValue())
                .component(
                        DataComponents.EQUIPPABLE,
                        Equippable.builder(type.getSlot()).setEquipSound(material.equipSound()).setAsset(material.assetId()).build()
                )
                .repairable(material.repairIngredient());
    }

    public static void modifyVanillaArmor(ModifyDefaultComponentsEvent event){
        event.modify(Items.IRON_HELMET,builder -> vanillaHumanoidArmor(builder,ModArmorMaterials.IRON,ArmorType.HELMET, MaterialFamilyType.IRON_STEEL_FAMILY));
        event.modify(Items.IRON_CHESTPLATE,builder -> vanillaHumanoidArmor(builder,ModArmorMaterials.IRON,ArmorType.CHESTPLATE, MaterialFamilyType.IRON_STEEL_FAMILY));
        event.modify(Items.IRON_LEGGINGS,builder -> vanillaHumanoidArmor(builder,ModArmorMaterials.IRON,ArmorType.LEGGINGS, MaterialFamilyType.IRON_STEEL_FAMILY));
        event.modify(Items.IRON_BOOTS,builder -> vanillaHumanoidArmor(builder,ModArmorMaterials.IRON,ArmorType.BOOTS, MaterialFamilyType.IRON_STEEL_FAMILY));

        event.modify(Items.GOLDEN_HELMET,builder -> vanillaHumanoidArmor(builder,ModArmorMaterials.GOLD,ArmorType.HELMET, MaterialFamilyType.GOLD_COPPER_FAMILY));
        event.modify(Items.GOLDEN_CHESTPLATE,builder -> vanillaHumanoidArmor(builder,ModArmorMaterials.GOLD,ArmorType.CHESTPLATE, MaterialFamilyType.GOLD_COPPER_FAMILY));
        event.modify(Items.GOLDEN_LEGGINGS,builder -> vanillaHumanoidArmor(builder,ModArmorMaterials.GOLD,ArmorType.LEGGINGS, MaterialFamilyType.GOLD_COPPER_FAMILY));
        event.modify(Items.GOLDEN_BOOTS,builder -> vanillaHumanoidArmor(builder,ModArmorMaterials.GOLD,ArmorType.BOOTS, MaterialFamilyType.GOLD_COPPER_FAMILY));

        event.modify(Items.LEATHER_HELMET,builder -> vanillaHumanoidArmor(builder,ModArmorMaterials.LEATHER,ArmorType.HELMET, MaterialFamilyType.RUBBISH));
        event.modify(Items.LEATHER_CHESTPLATE,builder -> vanillaHumanoidArmor(builder,ModArmorMaterials.LEATHER,ArmorType.CHESTPLATE, MaterialFamilyType.RUBBISH));
        event.modify(Items.LEATHER_LEGGINGS,builder -> vanillaHumanoidArmor(builder,ModArmorMaterials.LEATHER,ArmorType.LEGGINGS, MaterialFamilyType.RUBBISH));
        event.modify(Items.LEATHER_BOOTS,builder -> vanillaHumanoidArmor(builder,ModArmorMaterials.LEATHER,ArmorType.BOOTS, MaterialFamilyType.RUBBISH));

        proxyItemMap.forEach((item,deferredItem)->{
            event.modify(item,builder-> copyProperties(builder,item));
        });

    }

    public static void vanillaHumanoidArmor(DataComponentPatch.Builder builder, ModArmorMaterials.ModArmorMaterial material, ArmorType type, MaterialFamilyType materialLevelType) {
        builder.set(DataComponents.MAX_DAMAGE, type.getDurability(material.durability()));
        builder.set(DataComponents.ATTRIBUTE_MODIFIERS, material.createAttributes(type));
        builder.set(DataComponents.ENCHANTABLE, new Enchantable(material.enchantmentValue()));
        builder.set(ModDataComponents.MATERIAL_LEVEL.get(), new MaterialLevel(materialLevelType.level));
    }



    public static void copyProperties(DataComponentPatch.Builder builder, Item item) {
        proxyItemMap.get(item).get().components().forEach(
                typedDataComponent->{
                    if(typedDataComponent.type().equals(DataComponents.ITEM_MODEL)||typedDataComponent.type().equals(DataComponents.ITEM_NAME)){
                        return;
                    }
                    copySingleComponent(builder, typedDataComponent);
                }
        );
    }

    private static <T> void copySingleComponent(DataComponentPatch.Builder builder, TypedDataComponent<T> typedDataComponent) {
        builder.set(typedDataComponent.type(), typedDataComponent.value());
    }
}
