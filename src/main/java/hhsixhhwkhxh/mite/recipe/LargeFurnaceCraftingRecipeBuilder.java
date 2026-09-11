package hhsixhhwkhxh.mite.recipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LargeFurnaceCraftingRecipeBuilder implements RecipeBuilder {
    private final HolderGetter<Item> items;
    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final ItemStack resultStack; // Neo: add stack result support
    private final List<String> rows = Lists.newArrayList();
    private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;
    private boolean showNotification = true;

    private LargeFurnaceCraftingRecipeBuilder(HolderGetter<Item> items, RecipeCategory category, ItemLike result, int count) {
        this(items, category, new ItemStack(result, count));
    }

    private LargeFurnaceCraftingRecipeBuilder(HolderGetter<Item> items, RecipeCategory category, ItemStack result) {
        this.items = items;
        this.category = category;
        this.result = result.getItem();
        this.count = result.getCount();
        this.resultStack = result;
    }

    public static LargeFurnaceCraftingRecipeBuilder shaped(HolderGetter<Item> items, RecipeCategory category, ItemLike result) {
        return shaped(items, category, result, 1);
    }

    public static LargeFurnaceCraftingRecipeBuilder shaped(HolderGetter<Item> items, RecipeCategory category, ItemLike result, int count) {
        return new LargeFurnaceCraftingRecipeBuilder(items, category, result, count);
    }

    public static LargeFurnaceCraftingRecipeBuilder shaped(HolderGetter<Item> p_365019_, RecipeCategory p_251325_, ItemStack result) {
        return new LargeFurnaceCraftingRecipeBuilder(p_365019_, p_251325_, result);
    }

    /**
     * Adds a key to the recipe pattern.
     */
    public LargeFurnaceCraftingRecipeBuilder define(Character symbol, TagKey<Item> tag) {
        return this.define(symbol, Ingredient.of(this.items.getOrThrow(tag)));
    }

    /**
     * Adds a key to the recipe pattern.
     */
    public LargeFurnaceCraftingRecipeBuilder define(Character symbol, ItemLike item) {
        return this.define(symbol, Ingredient.of(item));
    }

    /**
     * Adds a key to the recipe pattern.
     */
    public LargeFurnaceCraftingRecipeBuilder define(Character symbol, Ingredient ingredient) {
        if (this.key.containsKey(symbol)) {
            throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
        } else if (symbol == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            this.key.put(symbol, ingredient);
            return this;
        }
    }

    /**
     * Adds a new entry to the patterns for this recipe.
     */
    public LargeFurnaceCraftingRecipeBuilder pattern(String pattern) {
        if (!this.rows.isEmpty() && pattern.length() != this.rows.getFirst().length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.rows.add(pattern);
            return this;
        }
    }

    public LargeFurnaceCraftingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public LargeFurnaceCraftingRecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    public LargeFurnaceCraftingRecipeBuilder showNotification(boolean showNotification) {
        this.showNotification = showNotification;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> resourceKey) {
        ShapedRecipePattern shapedrecipepattern = this.ensureValid(resourceKey);
        Advancement.Builder advancement$builder = output.advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey))
            .rewards(AdvancementRewards.Builder.recipe(resourceKey))
            .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        LargeFurnaceCraftingRecipe largeFurnaceCraftingRecipe = new LargeFurnaceCraftingRecipe(
            Objects.requireNonNullElse(this.group, ""),
            RecipeBuilder.determineBookCategory(this.category),
            shapedrecipepattern,
            this.resultStack,
            this.showNotification
        );
        output.accept(resourceKey, largeFurnaceCraftingRecipe, advancement$builder.build(resourceKey.location().withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private ShapedRecipePattern ensureValid(ResourceKey<Recipe<?>> recipe) {
//        if (this.criteria.isEmpty()) {
//            throw new IllegalStateException("No way of obtaining recipe " + recipe.location());
//        } else {
            return ShapedRecipePattern.of(this.key, this.rows);
//        }
    }
}
