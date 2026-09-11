package hhsixhhwkhxh.mite.recipe;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipeSerializers {
    public static DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, MiteBreakAll.MOD_ID);

    public static Supplier<LargeFurnaceCraftingRecipe.Serializer> LARGE_FURNACE_CRAFTING_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("large_furnace_crafting_recipe",LargeFurnaceCraftingRecipe.Serializer::new);

    public static Supplier<RecipeSerializer<MeltingRecipe>> MELTING_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("melting",MeltingRecipe.Serializer::new);


    public static void register(IEventBus eventBus){
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
