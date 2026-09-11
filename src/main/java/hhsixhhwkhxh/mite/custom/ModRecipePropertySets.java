package hhsixhhwkhxh.mite.custom;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipePropertySet;

import static net.minecraft.world.item.crafting.RecipePropertySet.TYPE_KEY;

public class ModRecipePropertySets {
    public static final ResourceKey<RecipePropertySet> LARGE_FURNACE_INPUT = register("large_furnace_input");

    private static ResourceKey<RecipePropertySet> register(String name) {
        return ResourceKey.create(TYPE_KEY, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID, name));
    }
}
