package hhsixhhwkhxh.mite.datagen;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.datacomponent.MeltingCast;
import hhsixhhwkhxh.mite.datacomponent.ModDataComponents;
import hhsixhhwkhxh.mite.item.ModItems;
import hhsixhhwkhxh.mite.recipe.LargeFurnaceCraftingRecipeBuilder;
import hhsixhhwkhxh.mite.recipe.MeltingRecipeBuilder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.RecipeBuilder.getDefaultRecipeId;

public class ModRecipesProvider extends RecipeProvider {

    private final HolderGetter<Item> items;

    public ModRecipesProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
        this.items = registries.lookupOrThrow(Registries.ITEM);
    }

    @Override
    protected void buildRecipes() {
        this.shaped(RecipeCategory.MISC, Items.GOLDEN_APPLE)
                .define('#',Items.GOLD_NUGGET)
                .define('X',Items.APPLE)
                .pattern("###").pattern("#X#").pattern("###")
                .unlockedBy("has_apple", this.has(Items.APPLE))
                .save(output, getId("golden_apple"));

//        this.addFurnaceCraftingRecipe(RecipeCategory.COMBAT, ModItems.COPPER_CHESTPLATE)
//                .define('#',Items.COPPER_INGOT)
//                .pattern("# #").pattern("###").pattern("###")
//                .save(output);

        createMeltingRecipe(ModItems.SILVER_NUGGET,ModItems.SILVER_INGOT,120);
        createMeltingRecipe(ModItems.COPPER_NUGGET,Items.COPPER_INGOT,140);
        createMeltingRecipe(Items.GOLD_NUGGET, Items.GOLD_INGOT,100);
        createMeltingRecipe(Items.IRON_NUGGET, Items.IRON_INGOT,180);
        createMeltingRecipe(ModItems.HARD_NUGGET,ModItems.HARD_INGOT,240);
        createMeltingRecipe(ModItems.ANCIENT_METAL_NUGGET,ModItems.ANCIENT_METAL_INGOT,240);
        createMeltingRecipe(ModItems.MITHRIL_NUGGET,ModItems.MITHRIL_INGOT,300);
        createMeltingRecipe(ModItems.ADAMANTIUM_NUGGET,ModItems.ADAMANTIUM_INGOT,400);

    }

    public LargeFurnaceCraftingRecipeBuilder addFurnaceCraftingRecipe(RecipeCategory category, ItemLike result){
        return LargeFurnaceCraftingRecipeBuilder.shaped(items, category, result);
    }

    public void createChestplateRecipe(ItemLike ingredient, ItemLike result){
        this.addFurnaceCraftingRecipe(RecipeCategory.COMBAT, result)
                .define('#',ingredient)
                .pattern("# #").pattern("###").pattern("###")
                .unlockedBy("has_ingredient", this.has(ingredient))
                .save(output, getDefaultRecipeResourceLocation(result));
    }

    public MeltingRecipeBuilder addMeltingRecipe(ItemLike inputItem, RecipeCategory category, ItemLike result, float experience, int baseMeltTick){
        int meltingPoint = 0;
        MeltingCast meltingCast = new ItemStack(inputItem).get(ModDataComponents.MELTING_CAST);
        if(meltingCast != null){
            meltingPoint = meltingCast.meltingPoint();
        }
        return MeltingRecipeBuilder.melting(Ingredient.of(inputItem),
                category, result,  experience, baseMeltTick, meltingPoint
        );
    }

    public void createMeltingRecipe(ItemLike inputItem, ItemLike result, int baseMeltTick){

        addMeltingRecipe(inputItem, RecipeCategory.MISC, result, 0.1F, baseMeltTick)
                .unlockedBy("has_input_item", this.has(inputItem))
                .save(this.output, getDefaultRecipeResourceLocation(result));
    }

    private String getId(String name){
        return ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID,name).toString();
    }

    private ResourceKey<Recipe<?>> getDefaultRecipeResourceLocation(ItemLike result){
        return ResourceKey.create(Registries.RECIPE,getDefaultRecipeId(result));
    }

    // The runner to add to the data generator
    public static class Runner extends RecipeProvider.Runner {
        // Get the parameters from the `GatherDataEvent`s.
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new ModRecipesProvider(provider, output);
        }

        @Override
        public String getName() {
            return "";
        }
    }
}
