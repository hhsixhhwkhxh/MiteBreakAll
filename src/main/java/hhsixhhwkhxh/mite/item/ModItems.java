package hhsixhhwkhxh.mite.item;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.enchantment.Enchantable;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

import static net.minecraft.world.item.component.Consumables.defaultFood;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MiteBreakAll.MODID);
    public static final DeferredItem<Item> WILD_APPLE = ITEMS.registerItem("wild_apple", Item::new,new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    public static final DeferredItem<Item> STRAWBERRIES = ITEMS.registerItem("strawberries", Item::new, new Item.Properties().food(Foods.SWEET_BERRIES));

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

    public static final DeferredItem<Item> ADAMANTIUM_HELMET = ITEMS.registerItem("adamantium_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ADAMANTIUM, ArmorType.HELMET)));
    
    public static final DeferredItem<Item> ANCIENT_METAL_HELMET = ITEMS.registerItem("ancient_metal_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ANCIENT_METAL, ArmorType.HELMET)));
    public static final DeferredItem<Item> HARD_HELMET = ITEMS.registerItem("hard_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HARD, ArmorType.HELMET)));
    public static final DeferredItem<Item> MITHRIL_HELMET = ITEMS.registerItem("mithril_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.MITHRIL, ArmorType.HELMET)));
    public static final DeferredItem<Item> SILVER_HELMET = ITEMS.registerItem("silver_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER, ArmorType.HELMET)));
    public static final DeferredItem<Item> RUSTED_IRON_HELMET = ITEMS.registerItem("rusted_iron_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.RUSTED_IRON, ArmorType.HELMET)));
    public static final DeferredItem<Item> BRONZE_HELMET = ITEMS.registerItem("bronze_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.BRONZE, ArmorType.HELMET)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_HELMET = ITEMS.registerItem("high_carbon_steel_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HIGH_CARBON_STEEL, ArmorType.HELMET)));
    public static final DeferredItem<Item> SILVER_COPPER_HELMET = ITEMS.registerItem("silver_copper_helmet",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER_COPPER, ArmorType.HELMET)));

    public static final DeferredItem<Item> ADAMANTIUM_CHESTPLATE = ITEMS.registerItem("adamantium_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ADAMANTIUM, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> ANCIENT_METAL_CHESTPLATE = ITEMS.registerItem("ancient_metal_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ANCIENT_METAL, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> HARD_CHESTPLATE = ITEMS.registerItem("hard_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HARD, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> MITHRIL_CHESTPLATE = ITEMS.registerItem("mithril_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.MITHRIL, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> SILVER_CHESTPLATE = ITEMS.registerItem("silver_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> RUSTED_IRON_CHESTPLATE = ITEMS.registerItem("rusted_iron_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.RUSTED_IRON, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> BRONZE_CHESTPLATE = ITEMS.registerItem("bronze_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.BRONZE, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_CHESTPLATE = ITEMS.registerItem("high_carbon_steel_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HIGH_CARBON_STEEL, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> SILVER_COPPER_CHESTPLATE = ITEMS.registerItem("silver_copper_chestplate",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER_COPPER, ArmorType.CHESTPLATE)));

    public static final DeferredItem<Item> ADAMANTIUM_LEGGINGS = ITEMS.registerItem("adamantium_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ADAMANTIUM, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> ANCIENT_METAL_LEGGINGS = ITEMS.registerItem("ancient_metal_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ANCIENT_METAL, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> HARD_LEGGINGS = ITEMS.registerItem("hard_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HARD, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> MITHRIL_LEGGINGS = ITEMS.registerItem("mithril_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.MITHRIL, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> SILVER_LEGGINGS = ITEMS.registerItem("silver_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> RUSTED_IRON_LEGGINGS = ITEMS.registerItem("rusted_iron_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.RUSTED_IRON, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> BRONZE_LEGGINGS = ITEMS.registerItem("bronze_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.BRONZE, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_LEGGINGS = ITEMS.registerItem("high_carbon_steel_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HIGH_CARBON_STEEL, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> SILVER_COPPER_LEGGINGS = ITEMS.registerItem("silver_copper_leggings",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER_COPPER, ArmorType.LEGGINGS)));


    public static final DeferredItem<Item> ADAMANTIUM_BOOTS = ITEMS.registerItem("adamantium_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ADAMANTIUM, ArmorType.BOOTS)));
    public static final DeferredItem<Item> ANCIENT_METAL_BOOTS = ITEMS.registerItem("ancient_metal_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.ANCIENT_METAL, ArmorType.BOOTS)));
    public static final DeferredItem<Item> HARD_BOOTS = ITEMS.registerItem("hard_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HARD, ArmorType.BOOTS)));
    public static final DeferredItem<Item> MITHRIL_BOOTS = ITEMS.registerItem("mithril_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.MITHRIL, ArmorType.BOOTS)));
    public static final DeferredItem<Item> SILVER_BOOTS = ITEMS.registerItem("silver_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER, ArmorType.BOOTS)));
    public static final DeferredItem<Item> RUSTED_IRON_BOOTS = ITEMS.registerItem("rusted_iron_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.RUSTED_IRON, ArmorType.BOOTS)));
    public static final DeferredItem<Item> BRONZE_BOOTS = ITEMS.registerItem("bronze_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.BRONZE, ArmorType.BOOTS)));
    public static final DeferredItem<Item> HIGH_CARBON_STEEL_BOOTS = ITEMS.registerItem("high_carbon_steel_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.HIGH_CARBON_STEEL, ArmorType.BOOTS)));
    public static final DeferredItem<Item> SILVER_COPPER_BOOTS = ITEMS.registerItem("silver_copper_boots",(props)-> new Item(humanoidArmor(props,ModArmorMaterials.SILVER_COPPER, ArmorType.BOOTS)));
    

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);

        modifyGoldenApple(eventBus);
    }


    public static void modifyGoldenApple(IEventBus eventBus){
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
}
