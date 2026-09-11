package hhsixhhwkhxh.mite.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class MeltingRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final CookingBookCategory bookCategory;
    private final Item result;
    private final ItemStack stackResult; // Neo: add stack result support
    private final Ingredient ingredient;
    private final float experience;
    private final int baseMeltTick;
    private final int meltingPoint;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;

    private MeltingRecipeBuilder(
        RecipeCategory category,
        CookingBookCategory bookCategory,
        ItemLike result,
        Ingredient ingredient,
        float experience,
        int baseMeltTick,
        int meltingPoint
    ) {
        this(category, bookCategory, new ItemStack(result), ingredient, experience, baseMeltTick, meltingPoint);
    }

    private MeltingRecipeBuilder(
            RecipeCategory p_251345_,
            CookingBookCategory p_251607_,
            ItemStack result,
            Ingredient p_250362_,
            float p_251204_,
            int p_250189_,
            int meltingPoint
    ) {
        this.category = p_251345_;
        this.bookCategory = p_251607_;
        this.result = result.getItem();
        this.stackResult = result;
        this.ingredient = p_250362_;
        this.experience = p_251204_;
        this.baseMeltTick = p_250189_;
        this.meltingPoint = meltingPoint;
    }


    public static MeltingRecipeBuilder melting(Ingredient ingredient, RecipeCategory category, ItemLike result, float experience, int cookingTime, int meltingPoint) {
        return new MeltingRecipeBuilder(
            category, determineSmeltingRecipeCategory(result), result, ingredient, experience, cookingTime, meltingPoint
        );
    }


    public MeltingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public MeltingRecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> resourceKey) {
        this.ensureValid(resourceKey);
        Advancement.Builder advancement$builder = output.advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey))
            .rewards(AdvancementRewards.Builder.recipe(resourceKey))
            .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);

        MeltingRecipe meltingRecipe = new MeltingRecipe(Objects.requireNonNullElse(this.group, ""), this.bookCategory, this.ingredient, this.stackResult, this.experience, this.baseMeltTick, meltingPoint);

        output.accept(
            resourceKey, meltingRecipe, advancement$builder.build(resourceKey.location().withPrefix("recipes/" + this.category.getFolderName() + "/"))
        );
    }

    private static CookingBookCategory determineSmeltingRecipeCategory(ItemLike result) {
        if (result.asItem().components().has(DataComponents.FOOD)) {
            return CookingBookCategory.FOOD;
        } else {
            return result.asItem() instanceof BlockItem ? CookingBookCategory.BLOCKS : CookingBookCategory.MISC;
        }
    }

    private static CookingBookCategory determineBlastingRecipeCategory(ItemLike result) {
        return result.asItem() instanceof BlockItem ? CookingBookCategory.BLOCKS : CookingBookCategory.MISC;
    }

    private static CookingBookCategory determineRecipeCategory(RecipeSerializer<? extends AbstractCookingRecipe> serializer, ItemLike result) {
        if (serializer == RecipeSerializer.SMELTING_RECIPE) {
            return determineSmeltingRecipeCategory(result);
        } else if (serializer == RecipeSerializer.BLASTING_RECIPE) {
            return determineBlastingRecipeCategory(result);
        } else if (serializer != RecipeSerializer.SMOKING_RECIPE && serializer != RecipeSerializer.CAMPFIRE_COOKING_RECIPE) {
            throw new IllegalStateException("Unknown cooking recipe type");
        } else {
            return CookingBookCategory.FOOD;
        }
    }

    private void ensureValid(ResourceKey<Recipe<?>> recipe) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipe.location());
        }
    }
}
