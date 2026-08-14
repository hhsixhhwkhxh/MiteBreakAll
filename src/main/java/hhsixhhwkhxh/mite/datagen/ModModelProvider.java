package hhsixhhwkhxh.mite.datagen;


import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.block.SieveBlock;
import hhsixhhwkhxh.mite.custom.MeshType;
import hhsixhhwkhxh.mite.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
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




public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, MiteBreakAll.MODID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        createFlintCraftingTable(blockModels,ModBlocks.FLINT_CRAFTING_TABLE.get(), Blocks.OAK_LOG);

        createTrivialCubeEx(blockModels,ModBlocks.SILVER_ORE.get(),"ores/",TexturedModel.CUBE);
        createTrivialCubeEx(blockModels,ModBlocks.HARD_ORE.get(),"ores/",TexturedModel.CUBE);
        createTrivialCubeEx(blockModels,ModBlocks.MERCURY_ORE.get(),"ores/",TexturedModel.CUBE);
        createTrivialCubeEx(blockModels,ModBlocks.MITHRIL_ORE.get(),"ores/",TexturedModel.CUBE);
        createTrivialCubeEx(blockModels,ModBlocks.ADAMANTIUM_ORE.get(),"ores/",TexturedModel.CUBE);
        createTrivialCubeEx(blockModels,ModBlocks.TIN_ORE.get(),"ores/",TexturedModel.CUBE);

        generateFlatItemEx(blockModels,ModItems.WILD_APPLE.get(),"food/",ModelTemplates.FLAT_ITEM);

        createStrawBerryBush(blockModels);

        generateFlatItemEx(blockModels,ModItems.FLINT_SHARD.get(),"shards/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.OBSIDIAN_SHARD.get(),"shards/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.DIAMOND_SHARD.get(),"shards/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.EMERALD_SHARD.get(),"shards/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.GLASS_SHARD.get(),"shards/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.QUARTZ_SHARD.get(),"shards/",ModelTemplates.FLAT_ITEM);

        generateFlatItemEx(blockModels,ModItems.ADAMANTIUM_NUGGET.get(),"nuggets/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.ANCIENT_METAL_NUGGET.get(),"nuggets/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.HARD_NUGGET.get(),"nuggets/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.MERCURY_NUGGET.get(),"nuggets/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.MITHRIL_NUGGET.get(),"nuggets/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.SILVER_NUGGET.get(),"nuggets/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.COPPER_NUGGET.get(),"nuggets/",ModelTemplates.FLAT_ITEM);

        createSieve(blockModels);

        itemModels.generateFlatItem(ModItems.MESH_LEATHER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MESH_STRING.get(), ModelTemplates.FLAT_ITEM);

    }

    public void createFlintCraftingTable(BlockModelGenerators blockModels,Block craftingTableBlock, Block craftingTableMaterialBlock) {

        BiFunction<Block, Block, TextureMapping> textureMappingGetter = (b, block)-> new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block))
                .put(TextureSlot.FRONT,TextureMapping.getBlockTexture(block))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block,"_top"))
                .put(TextureSlot.TOP, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/craft_table/flint/top"));

        TextureMapping texturemapping = textureMappingGetter.apply(craftingTableBlock, craftingTableMaterialBlock);
        MultiVariant multivariant = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM.create(craftingTableBlock, texturemapping,blockModels.modelOutput));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(craftingTableBlock, multivariant).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING));

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

    public void createTrivialCubeEx(BlockModelGenerators blockModels,Block block, String prefix,TexturedModel.Provider provider) {
        TexturedModel texturedModel = provider.get(block);

        Function<ResourceLocation, TextureMapping> textureMappingGetter = (TextureMapping::cube);

        ResourceLocation resourceLocation = texturedModel.getTemplate().create(BuiltInRegistries.BLOCK.getKey(block).withPath(name -> "block/" + name), textureMappingGetter.apply(getBlockTextureWithPrefix(block, prefix,"")), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(resourceLocation)));


    }

    public void createSieve(BlockModelGenerators blockModels) {
        registerSimpleItemModel(blockModels,ModBlocks.SIEVE.asItem(),ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/sieve"));
        blockModels.blockStateOutput
                .accept(
                        MultiPartGenerator.multiPart(ModBlocks.SIEVE.get())
                                .with(BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/sieve")))
                                .with(
                                        BlockModelGenerators.condition().term(SieveBlock.MESH_TYPE, MeshType.LEATHER), BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/mesh_leather"))
                                )
                                .with(
                                        BlockModelGenerators.condition().term(SieveBlock.MESH_TYPE, MeshType.STRING), BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/mesh_string"))
                                )
                                .with(
                                        BlockModelGenerators.condition().term(SieveBlock.LEVEL, 1), BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/gravel_layer1"))
                                )
                                .with(
                                        BlockModelGenerators.condition().term(SieveBlock.LEVEL, 2), BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/gravel_layer2"))
                                )
                                .with(
                                        BlockModelGenerators.condition().term(SieveBlock.LEVEL, 3), BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/gravel_layer3"))
                                )
                                .with(
                                        BlockModelGenerators.condition().term(SieveBlock.LEVEL, 4), BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/gravel_layer4"))
                                )

                );

    }

    public void registerSimpleItemModel(BlockModelGenerators blockModels,Item item, ResourceLocation model) {
        blockModels.itemModelOutput.accept(item, ItemModelUtils.plainModel(model));
    }
}
