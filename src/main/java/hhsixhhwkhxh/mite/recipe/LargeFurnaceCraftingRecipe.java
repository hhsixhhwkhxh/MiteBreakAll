package hhsixhhwkhxh.mite.recipe;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapedCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class LargeFurnaceCraftingRecipe implements CraftingRecipe {
    public final ShapedRecipePattern pattern;
    final ItemStack result;
    final String group;
    final CraftingBookCategory category;
    final boolean showNotification;
    @Nullable
    private PlacementInfo placementInfo;

    private final int craftTime,meltPoint;

    public LargeFurnaceCraftingRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result, boolean showNotification,int craftTime,int meltPoint) {
        this.group = group;
        this.category = category;
        this.pattern = pattern;
        this.result = result;
        this.showNotification = showNotification;
        this.craftTime = craftTime;
        this.meltPoint = meltPoint;
    }

    public LargeFurnaceCraftingRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result) {
        this(group, category, pattern, result, true,100,0);
    }

    @Override
    public RecipeSerializer<? extends CraftingRecipe> getSerializer() {
        return ModRecipeSerializers.LARGE_FURNACE_CRAFTING_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<CraftingRecipe> getType() {
        return ModRecipeTypes.LARGE_FURNACE_CRAFTING_RECIPE.get();
    }

    @Override
    public String group() {
        return this.group;
    }

    @Override
    public CraftingBookCategory category() {
        return this.category;
    }

    @VisibleForTesting
    public List<Optional<Ingredient>> getIngredients() {
        return this.pattern.ingredients();
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.placementInfo == null) {
            this.placementInfo = PlacementInfo.createFromOptionals(this.pattern.ingredients());
        }

        return this.placementInfo;
    }

    @Override
    public boolean showNotification() {
        return this.showNotification;
    }

    public boolean matches(CraftingInput input, Level level) {
        return this.pattern.matches(input);
    }

    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        return this.result.copy();
    }

    public int getWidth() {
        return this.pattern.width();
    }

    public int getHeight() {
        return this.pattern.height();
    }

    public int getCraftTime() {
        return craftTime;
    }

    public int getMeltPoint() {
        return meltPoint;
    }


    @Override
    public List<RecipeDisplay> display() {
        return List.of(
            new ShapedCraftingRecipeDisplay(
                this.pattern.width(),
                this.pattern.height(),
                this.pattern.ingredients().stream().map(p_380107_ -> p_380107_.map(Ingredient::display).orElse(SlotDisplay.Empty.INSTANCE)).toList(),
                new SlotDisplay.ItemStackSlotDisplay(this.result),
                new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)
            )
        );
    }

    public static class Serializer implements RecipeSerializer<LargeFurnaceCraftingRecipe> {
        public static final MapCodec<LargeFurnaceCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            recipeInstance -> recipeInstance.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                    CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(recipe -> recipe.category),
                    ShapedRecipePattern.MAP_CODEC.forGetter(recipe -> recipe.pattern),
                    ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                    Codec.BOOL.optionalFieldOf("show_notification", true).forGetter(recipe -> recipe.showNotification),
                    Codec.INT.optionalFieldOf("craft_time",100).forGetter(recipe-> recipe.craftTime),
                    Codec.INT.optionalFieldOf("melting_point",100).forGetter(recipe-> recipe.meltPoint)
                )
                .apply(recipeInstance, LargeFurnaceCraftingRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, LargeFurnaceCraftingRecipe> STREAM_CODEC = StreamCodec.of(
            LargeFurnaceCraftingRecipe.Serializer::toNetwork, LargeFurnaceCraftingRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<LargeFurnaceCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, LargeFurnaceCraftingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static LargeFurnaceCraftingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String s = buffer.readUtf();
            CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
            ShapedRecipePattern shapedrecipepattern = ShapedRecipePattern.STREAM_CODEC.decode(buffer);
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            boolean flag = buffer.readBoolean();
            int craftTime = buffer.readInt();
            int meltingPoint = buffer.readInt();
            return new LargeFurnaceCraftingRecipe(s, craftingbookcategory, shapedrecipepattern, itemstack, flag, craftTime, meltingPoint);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, LargeFurnaceCraftingRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeBoolean(recipe.showNotification);
            buffer.writeInt(recipe.craftTime);
            buffer.writeInt(recipe.meltPoint);
        }
    }
}
