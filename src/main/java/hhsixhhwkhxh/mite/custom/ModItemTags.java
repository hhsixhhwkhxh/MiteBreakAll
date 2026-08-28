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

    public static final TagKey<Item> ADAMANTIUM_TOOL_MATERIALS = bind("adamantium_tool_materials");
    public static final TagKey<Item> ANCIENT_METAL_TOOL_MATERIALS = bind("ancient_metal_tool_materials");
    public static final TagKey<Item> COPPER_TOOL_MATERIALS = bind("copper_tool_materials");
    public static final TagKey<Item> HARD_TOOL_MATERIALS = bind("hard_tool_materials");
    public static final TagKey<Item> MITHRIL_TOOL_MATERIALS = bind("mithril_tool_materials");
    public static final TagKey<Item> SILVER_TOOL_MATERIALS = bind("silver_tool_materials");
    public static final TagKey<Item> SILVER_COPPER_TOOL_MATERIALS = bind("silver_copper_tool_materials");
    
    public static final TagKey<Item> NULL = bind("null");

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,name));
    }


}
