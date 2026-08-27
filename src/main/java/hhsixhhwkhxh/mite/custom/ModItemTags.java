package hhsixhhwkhxh.mite.custom;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

    public static final TagKey<Item> REPAIRS_ADAMANTIUM_ARMOR = bind("repairs_adamantium_armor");
    public static final TagKey<Item> REPAIRS_ANCIENT_METAL_ARMOR = bind("repairs_ancient_metal_armor");
    public static final TagKey<Item> REPAIRS_COPPER_ARMOR = bind("repairs_copper_armor");
    public static final TagKey<Item> REPAIRS_HARD_ARMOR = bind("repairs_hard_armor");
    public static final TagKey<Item> REPAIRS_MITHRIL_ARMOR = bind("repairs_mithril_armor");
    public static final TagKey<Item> REPAIRS_SILVER_ARMOR = bind("repairs_silver_armor");
    public static final TagKey<Item> REPAIRS_SILVER_COPPER_ARMOR = bind("repairs_silver_copper_armor");
    public static final TagKey<Item> NULL = bind("null");

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,name));
    }


}
