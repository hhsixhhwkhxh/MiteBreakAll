package hhsixhhwkhxh.mite.datagen;


import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;

import static net.minecraft.client.data.models.BlockModelGenerators.ROTATION_HORIZONTAL_FACING;


public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, MiteBreakAll.MODID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        createFlintCraftingTable(blockModels,ModBlocks.FLINT_CRAFTING_TABLE.get(), Blocks.OAK_LOG);
        blockModels.createTrivialCube(ModBlocks.SILVER_ORE.get());

        generateFlatItemEx(blockModels,ModItems.WILD_APPLE.get(),"food/",ModelTemplates.FLAT_ITEM);

        createStrawBerryBush(blockModels);
    }

    public void createFlintCraftingTable(BlockModelGenerators blockModels,Block craftingTableBlock, Block craftingTableMaterialBlock) {

        BiFunction<Block, Block, TextureMapping> textureMappingGetter = (b, block)-> new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block))
                .put(TextureSlot.FRONT,TextureMapping.getBlockTexture(block))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block,"_top"))
                .put(TextureSlot.TOP, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/craft_table/flint/top"));

        TextureMapping texturemapping = textureMappingGetter.apply(craftingTableBlock, craftingTableMaterialBlock);
        MultiVariant multivariant = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM.create(craftingTableBlock, texturemapping,blockModels.modelOutput));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(craftingTableBlock, multivariant).with(ROTATION_HORIZONTAL_FACING));

    }

    public void createStrawBerryBush(BlockModelGenerators blockModels) {
        //blockModels.registerSimpleFlatItemModel(ModItems.STRAWBERRIES.get());
        generateFlatItemEx(blockModels,ModItems.STRAWBERRIES.get(),"food/",ModelTemplates.FLAT_ITEM);
        //blockModels.registerSimpleFlatItemModel(ModBlocks.STRAWBERRY_BUSH.get());
        generateFlatItemEx(blockModels,ModBlocks.STRAWBERRY_BUSH.asItem(),"food/",ModelTemplates.FLAT_ITEM);
        blockModels.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(ModBlocks.STRAWBERRY_BUSH.get())
                                .with(
                                        PropertyDispatch.initial(BlockStateProperties.AGE_2)
                                                .generate(
                                                        p_408965_ -> BlockModelGenerators.plainVariant(
                                                                createSuffixedVariantEx(blockModels,ModBlocks.STRAWBERRY_BUSH.get(), "bushes/","_stage" + p_408965_, ModelTemplates.CROSS.extend().renderType("cutout").build(), TextureMapping::cross)
                                                        )
                                                )
                                )
                );
    }

    public void generateFlatItemEx(BlockModelGenerators blockModels,Item item,String prefix, ModelTemplate modelTemplate) {
        ResourceLocation resourceLocation =  modelTemplate.create(ModelLocationUtils.getModelLocation(item), new TextureMapping().put(TextureSlot.LAYER0, getItemTextureWithPrefix(item,prefix)), blockModels.modelOutput);
        blockModels.itemModelOutput.accept(item, ItemModelUtils.plainModel(resourceLocation));
    }

    public static ResourceLocation getItemTextureWithPrefix(Item item,String prefix) {
        ResourceLocation resourcelocation = BuiltInRegistries.ITEM.getKey(item);
        return resourcelocation.withPrefix("item/"+prefix);
    }

    public static ResourceLocation getBlockTextureWithPrefix(Block block, String prefix,String suffix) {
        ResourceLocation resourcelocation = BuiltInRegistries.BLOCK.getKey(block);
        return resourcelocation.withPath(p_388162_ -> "block/" + prefix + p_388162_ + suffix);
    }

    public ResourceLocation createSuffixedVariantEx(BlockModelGenerators blockModels,
            Block block, String prefix,String suffix, ModelTemplate modelTemplate, Function<ResourceLocation, TextureMapping> textureMappingGetter
    ) {
        return modelTemplate.createWithSuffix(block, suffix, textureMappingGetter.apply(getBlockTextureWithPrefix(block, prefix, suffix)), blockModels.modelOutput);
    }
}
