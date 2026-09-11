package hhsixhhwkhxh.mite.recipe;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipeTypes {
    public static DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, MiteBreakAll.MOD_ID);

    public static Supplier<RecipeType<CraftingRecipe>> LARGE_FURNACE_CRAFTING_RECIPE = RECIPE_TYPES.register("large_furnace_crafting_recipe",() -> RecipeType.simple(getName("large_furnace_crafting_recipe")));

    public static Supplier<RecipeType<MeltingRecipe>> MELTING_RECIPE = RECIPE_TYPES.register("melting_recipe",() -> RecipeType.simple(getName("melting_recipe")));

    public static void register(IEventBus eventBus){
        RECIPE_TYPES.register(eventBus);
    }

    private static ResourceLocation getName(String path){
        return ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID,path);
    }
}
