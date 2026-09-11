package hhsixhhwkhxh.mite.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class MeltingRecipe extends AbstractCookingRecipe {

    private int meltingPoint = 0;

    public ItemStack getResult() {
        return result;
    }

    private final ItemStack result;

    public MeltingRecipe(String group, CookingBookCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        super(group, category, ingredient, result, experience, cookingTime);
        this.result = result;
    }

    public MeltingRecipe(String group, CookingBookCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime, int meltingPoint) {
        super(group, category, ingredient, result, experience, cookingTime);
        this.meltingPoint = meltingPoint;
        this.result = result;
    }

    @Override
    protected Item furnaceIcon() {
        return Items.FURNACE;
    }

    @Override
    public RecipeSerializer<MeltingRecipe> getSerializer() {
        return ModRecipeSerializers.MELTING_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<MeltingRecipe> getType() {
        return ModRecipeTypes.MELTING_RECIPE.get();
    }

    @Override
    public @NotNull RecipeBookCategory recipeBookCategory() {
        return switch (this.category()) {
            case BLOCKS -> RecipeBookCategories.FURNACE_BLOCKS;
            case FOOD -> RecipeBookCategories.FURNACE_FOOD;
            case MISC -> RecipeBookCategories.FURNACE_MISC;
        };
    }

    public int getMeltingPoint() {
        return meltingPoint;
    }

    public int getFinalMeltTicks(Block coreType, int coreQuantity){
        return getLevelBaseTicks(coreType) + getCoreModifierTicks(coreQuantity);
    }

    private int getLevelBaseTicks(Block furnaceType){
        float materialTick;
        if (furnaceType == Blocks.COBBLESTONE) {
            materialTick = (float)200 * 1.2F;
        } else if (furnaceType == Blocks.NETHERRACK) {
            materialTick = (float)200 / 1.2F;
        } else {
            materialTick = (float)200;
        }
        return Mth.ceil(materialTick);
    }

    private int getCoreModifierTicks(int coreQuantity) {
        float var2 = 1.0F + (float)Math.max(0, coreQuantity - 1) * 0.1F;
        return Mth.ceil(cookingTime() / var2);
    }


    public static class Serializer implements RecipeSerializer<MeltingRecipe> {
        public static final MapCodec<MeltingRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(SingleItemRecipe::group),
                                CookingBookCategory.CODEC.fieldOf("category").orElse(CookingBookCategory.MISC).forGetter(AbstractCookingRecipe::category),
                                Ingredient.CODEC.fieldOf("ingredient").forGetter(SingleItemRecipe::input),
                                ItemStack.CODEC.fieldOf("result").forGetter(MeltingRecipe::getResult),
                                Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(AbstractCookingRecipe::experience),
                                Codec.INT.fieldOf("base_melt_tick").orElse(0).forGetter(AbstractCookingRecipe::cookingTime),
                                Codec.INT.fieldOf("melting_point").orElse(0).forGetter(MeltingRecipe::getMeltingPoint)
                    )
                    .apply(instance, MeltingRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, MeltingRecipe> STREAM_CODEC = StreamCodec.of(
            MeltingRecipe.Serializer::toNetwork, MeltingRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<MeltingRecipe> codec() {
        return CODEC;
    }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MeltingRecipe> streamCodec() {
        return STREAM_CODEC;
    }

        private static MeltingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            CookingBookCategory cookingBookCategory = buffer.readEnum(CookingBookCategory.class);
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            float experience = buffer.readFloat();
            int baseMeltTick = buffer.readInt();
            int meltingPoint = buffer.readInt();
            return new MeltingRecipe(group, cookingBookCategory,ingredient, itemstack, experience, baseMeltTick, meltingPoint);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, MeltingRecipe recipe) {
            buffer.writeUtf(recipe.group());
            buffer.writeEnum(recipe.category());
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer,recipe.input());
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeFloat(recipe.experience());
            buffer.writeInt(recipe.cookingTime());
            buffer.writeInt(recipe.meltingPoint);
        }
    }
}
