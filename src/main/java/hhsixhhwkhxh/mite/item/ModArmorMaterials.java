package hhsixhhwkhxh.mite.item;

import com.google.common.collect.Maps;
import hhsixhhwkhxh.mite.custom.ModItemTags;
import hhsixhhwkhxh.mite.datagen.ModModelProvider;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Map;

public interface ModArmorMaterials {
    ModArmorMaterial ADAMANTIUM = new ModArmorMaterial(
            256, makeDefense(1.67, 2.92, 3.33, 2.08, 0), 40, SoundEvents.ARMOR_EQUIP_LEATHER, 3.0F, 0.1F, ModItemTags.REPAIRS_ADAMANTIUM_ARMOR, ModModelProvider.createEquipmentAssetId("adamantium")
    );
    ModArmorMaterial ANCIENT_METAL = new ModArmorMaterial(
            64, makeDefense(1.50, 2.50, 2.90, 1.90, 0), 40, SoundEvents.ARMOR_EQUIP_CHAIN, 2.5F, 0.0F, ModItemTags.REPAIRS_ANCIENT_METAL_ARMOR, ModModelProvider.createEquipmentAssetId("ancient_metal")
    );
    ModArmorMaterial HARD = new ModArmorMaterial(
            32, makeDefense(1.40, 2.40, 2.80, 1.80, 0), 35, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.0F, ModItemTags.REPAIRS_HARD_ARMOR, ModModelProvider.createEquipmentAssetId("hard")
    );
    ModArmorMaterial MITHRIL = new ModArmorMaterial(
            256, makeDefense(1.60, 2.70, 3.00, 2.00, 0), 100, SoundEvents.ARMOR_EQUIP_GOLD, 2.7F, 0.0F, ModItemTags.REPAIRS_MITHRIL_ARMOR, ModModelProvider.createEquipmentAssetId("mithril")
    );
    ModArmorMaterial SILVER = new ModArmorMaterial(
            4, makeDefense(1.17, 2.40, 2.33, 1.46, 0), 30, SoundEvents.ARMOR_EQUIP_DIAMOND, 0.0F, 0.0F, ModItemTags.REPAIRS_SILVER_ARMOR, ModModelProvider.createEquipmentAssetId("silver")
    );

    ModArmorMaterial RUSTED_IRON = new ModArmorMaterial(
            3, makeDefense(1.00, 1.75, 2.00, 1.25, 0), 1, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.0F, 0.0F, ItemTags.REPAIRS_IRON_ARMOR, ModModelProvider.createEquipmentAssetId("rusted_iron")
    );

    ModArmorMaterial BRONZE = new ModArmorMaterial(
            6, makeDefense(1.30, 2.10, 2.40, 1.60, 0), 30, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, ModItemTags.REPAIRS_COPPER_ARMOR, ModModelProvider.createEquipmentAssetId("bronze")
    );
    ModArmorMaterial HIGH_CARBON_STEEL = new ModArmorMaterial(
            27, makeDefense(1.30, 2.30, 2.70, 1.70, 0), 30, SoundEvents.ARMOR_EQUIP_DIAMOND, 1.0F, 0.0F, ItemTags.REPAIRS_IRON_ARMOR, ModModelProvider.createEquipmentAssetId("high_carbon_steel")
    );

    ModArmorMaterial SILVER_COPPER = new ModArmorMaterial(
            6, makeDefense(1.30, 2.20, 2.50, 1.70, 0), 30, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.0F, 0.0F, ModItemTags.REPAIRS_SILVER_COPPER_ARMOR, ModModelProvider.createEquipmentAssetId("silver_copper")
    );

    ModArmorMaterial IRON = new ModArmorMaterial(
            8, makeDefense(1.33, 2.33, 2.67, 1.67, 0), 30, null, 0.0F, 0.0F, ModItemTags.NULL, null
    );
    ModArmorMaterial GOLD = new ModArmorMaterial(
            4, makeDefense(1.00, 1.75, 2.00, 1.25, 0), 50, null, 0.0F, 0.0F, ModItemTags.NULL, null
    );

    ModArmorMaterial COPPER = new ModArmorMaterial(
            4, makeDefense(1.17, 2.04, 2.33, 1.46, 0), 30, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, ModItemTags.REPAIRS_SILVER_COPPER_ARMOR, ModModelProvider.createEquipmentAssetId("copper")
    );

    ModArmorMaterial LEATHER = new ModArmorMaterial(
            1, makeDefense(0.33, 0.58, 0.67, 0.42, 0), 10, null, 0.0F, 0.0F, ModItemTags.NULL, null
    );
    private static Map<ArmorType, Double> makeDefense(double boots, double leggings, double chestplate, double helmet, double body) {
        return Maps.newEnumMap(
                Map.of(
                        ArmorType.BOOTS,
                        boots,
                        ArmorType.LEGGINGS,
                        leggings,
                        ArmorType.CHESTPLATE,
                        chestplate,
                        ArmorType.HELMET,
                        helmet,
                        ArmorType.BODY,
                        body
                )
        );
    }
    public record ModArmorMaterial(
            int durability,
            Map<ArmorType, Double> defense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            TagKey<Item> repairIngredient,
            ResourceKey<EquipmentAsset> assetId
    ) {
        public ItemAttributeModifiers createAttributes(ArmorType armorType) {
            double i = this.defense.getOrDefault(armorType, 0D);
            ItemAttributeModifiers.Builder itemattributemodifiers$builder = ItemAttributeModifiers.builder();
            EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(armorType.getSlot());
            ResourceLocation resourcelocation = ResourceLocation.withDefaultNamespace("armor." + armorType.getName());
            itemattributemodifiers$builder.add(
                    Attributes.ARMOR, new AttributeModifier(resourcelocation, i, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup
            );
            itemattributemodifiers$builder.add(
                    Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourcelocation, this.toughness, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup
            );
            if (this.knockbackResistance > 0.0F) {
                itemattributemodifiers$builder.add(
                        Attributes.KNOCKBACK_RESISTANCE,
                        new AttributeModifier(resourcelocation, this.knockbackResistance, AttributeModifier.Operation.ADD_VALUE),
                        equipmentslotgroup
                );
            }

            return itemattributemodifiers$builder.build();
        }
    }
}
