package hhsixhhwkhxh.mite.datagen;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.datacomponent.MeltingPoint;
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


        createMeltingRecipe(ModItems.SILVER_NUGGET,ModItems.SILVER_INGOT,120);
        createMeltingRecipe(ModItems.COPPER_NUGGET,Items.COPPER_INGOT,140);
        createMeltingRecipe(Items.GOLD_NUGGET, Items.GOLD_INGOT,100);
        createMeltingRecipe(Items.IRON_NUGGET, Items.IRON_INGOT,180);
        createMeltingRecipe(ModItems.HARD_NUGGET,ModItems.HARD_INGOT,240);
        createMeltingRecipe(ModItems.ANCIENT_METAL_NUGGET,ModItems.ANCIENT_METAL_INGOT,240);
        createMeltingRecipe(ModItems.MITHRIL_NUGGET,ModItems.MITHRIL_INGOT,300);
        createMeltingRecipe(ModItems.ADAMANTIUM_NUGGET,ModItems.ADAMANTIUM_INGOT,400);


        createHelmetRecipe(ModItems.ADAMANTIUM_INGOT,ModItems.ADAMANTIUM_HELMET,128000);
        createChestplateRecipe(ModItems.ADAMANTIUM_INGOT,ModItems.ADAMANTIUM_CHESTPLATE,204800);
        createLeggingsRecipe(ModItems.ADAMANTIUM_INGOT,ModItems.ADAMANTIUM_LEGGINGS,179200);
        createBootsRecipe(ModItems.ADAMANTIUM_INGOT,ModItems.ADAMANTIUM_BOOTS,102400);

        createHelmetRecipe(Items.COPPER_INGOT, ModItems.COPPER_HELMET, 2000);
        createChestplateRecipe(Items.COPPER_INGOT, ModItems.COPPER_CHESTPLATE, 3200);
        createLeggingsRecipe(Items.COPPER_INGOT, ModItems.COPPER_LEGGINGS, 2800);
        createBootsRecipe(Items.COPPER_INGOT, ModItems.COPPER_BOOTS, 1600);

        createHelmetRecipe(ModItems.SILVER_INGOT, ModItems.SILVER_HELMET, 2000);
        createChestplateRecipe(ModItems.SILVER_INGOT, ModItems.SILVER_CHESTPLATE, 3200);
        createLeggingsRecipe(ModItems.SILVER_INGOT, ModItems.SILVER_LEGGINGS, 2800);
        createBootsRecipe(ModItems.SILVER_INGOT, ModItems.SILVER_BOOTS, 1600);

        createHelmetRecipe(Items.GOLD_INGOT, Items.GOLDEN_HELMET, 2000);
        createChestplateRecipe(Items.GOLD_INGOT, Items.GOLDEN_CHESTPLATE, 3200);
        createLeggingsRecipe(Items.GOLD_INGOT, Items.GOLDEN_LEGGINGS, 2800);
        createBootsRecipe(Items.GOLD_INGOT, Items.GOLDEN_BOOTS, 1600);

        createHelmetRecipe(Items.IRON_INGOT, Items.IRON_HELMET, 4000);
        createChestplateRecipe(Items.IRON_INGOT, Items.IRON_CHESTPLATE, 6400);
        createLeggingsRecipe(Items.IRON_INGOT, Items.IRON_LEGGINGS, 5600);
        createBootsRecipe(Items.IRON_INGOT, Items.IRON_BOOTS, 3200);

        createHelmetRecipe(ModItems.HARD_INGOT, ModItems.HARD_HELMET, 8000);
        createChestplateRecipe(ModItems.HARD_INGOT, ModItems.HARD_CHESTPLATE, 12800);
        createLeggingsRecipe(ModItems.HARD_INGOT, ModItems.HARD_LEGGINGS, 11200);
        createBootsRecipe(ModItems.HARD_INGOT, ModItems.HARD_BOOTS, 6400);

        createHelmetRecipe(ModItems.ANCIENT_METAL_INGOT, ModItems.ANCIENT_METAL_HELMET, 8000);
        createChestplateRecipe(ModItems.ANCIENT_METAL_INGOT, ModItems.ANCIENT_METAL_CHESTPLATE, 12800);
        createLeggingsRecipe(ModItems.ANCIENT_METAL_INGOT, ModItems.ANCIENT_METAL_LEGGINGS, 11200);
        createBootsRecipe(ModItems.ANCIENT_METAL_INGOT, ModItems.ANCIENT_METAL_BOOTS, 6400);

        createHelmetRecipe(ModItems.MITHRIL_INGOT, ModItems.MITHRIL_HELMET, 32000);
        createChestplateRecipe(ModItems.MITHRIL_INGOT, ModItems.MITHRIL_CHESTPLATE, 51200);
        createLeggingsRecipe(ModItems.MITHRIL_INGOT, ModItems.MITHRIL_LEGGINGS, 44800);
        createBootsRecipe(ModItems.MITHRIL_INGOT, ModItems.MITHRIL_BOOTS, 25600);
    }

    public LargeFurnaceCraftingRecipeBuilder addFurnaceCraftingRecipe(RecipeCategory category, ItemLike result, int craftTime){
        return LargeFurnaceCraftingRecipeBuilder.shaped(items, category, result, craftTime);
    }

    public void createHelmetRecipe(ItemLike ingredient, ItemLike result, int craftTime){
        this.addFurnaceCraftingRecipe(RecipeCategory.COMBAT, result, craftTime)
                .define('#',ingredient)
                .pattern("###").pattern("# #")
                .unlockedBy("has_ingredient", this.has(ingredient))
                .meltPoint(((MeltingPoint)ingredient.asItem().components().getOrDefault(ModDataComponents.MELTING_POINT,0)).meltingPoint())
                .save(output, getDefaultRecipeResourceLocation(result));
    }

    public void createChestplateRecipe(ItemLike ingredient, ItemLike result, int craftTime){
        this.addFurnaceCraftingRecipe(RecipeCategory.COMBAT, result, craftTime)
                .define('#',ingredient)
                .pattern("# #").pattern("###").pattern("###")
                .unlockedBy("has_ingredient", this.has(ingredient))
                .meltPoint(((MeltingPoint)ingredient.asItem().components().getOrDefault(ModDataComponents.MELTING_POINT,0)).meltingPoint())
                .save(output, getDefaultRecipeResourceLocation(result));
    }

    public void createLeggingsRecipe(ItemLike ingredient, ItemLike result, int craftTime){
        this.addFurnaceCraftingRecipe(RecipeCategory.COMBAT, result, craftTime)
                .define('#',ingredient)
                .pattern("###").pattern("# #").pattern("# #")
                .unlockedBy("has_ingredient", this.has(ingredient))
                .meltPoint(((MeltingPoint)ingredient.asItem().components().getOrDefault(ModDataComponents.MELTING_POINT,0)).meltingPoint())
                .save(output, getDefaultRecipeResourceLocation(result));
    }

    public void createBootsRecipe(ItemLike ingredient, ItemLike result, int craftTime){
        this.addFurnaceCraftingRecipe(RecipeCategory.COMBAT, result, craftTime)
                .define('#',ingredient)
                .pattern("# #").pattern("# #")
                .unlockedBy("has_ingredient", this.has(ingredient))
                .meltPoint(((MeltingPoint)ingredient.asItem().components().getOrDefault(ModDataComponents.MELTING_POINT,0)).meltingPoint())
                .save(output, getDefaultRecipeResourceLocation(result));
    }

    public MeltingRecipeBuilder addMeltingRecipe(ItemLike inputItem, RecipeCategory category, ItemLike result, float experience, int baseMeltTick){
        int meltingPoint = 0;
        MeltingPoint meltingCast = new ItemStack(inputItem).get(ModDataComponents.MELTING_POINT);
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
