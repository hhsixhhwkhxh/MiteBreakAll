package hhsixhhwkhxh.mite.datagen;


import com.google.gson.JsonObject;
import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.MiteAnvilBlock;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.block.SieveBlock;
import hhsixhhwkhxh.mite.custom.AnvilItemState;
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
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.numeric.CrossbowPull;
import net.minecraft.client.renderer.item.properties.select.Charge;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
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


        generateFlatItemEx(blockModels,ModItems.ADAMANTIUM_INGOT.get(),"ingots/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.ANCIENT_METAL_INGOT.get(),"ingots/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.HARD_INGOT.get(),"ingots/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.MERCURY_INGOT.get(),"ingots/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.MITHRIL_INGOT.get(),"ingots/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.SILVER_INGOT.get(),"ingots/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.TIN_INGOT.get(),"ingots/",ModelTemplates.FLAT_ITEM);


        generateFlatItemEx(blockModels,ModItems.ADAMANTIUM_HELMET.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.ADAMANTIUM_CHESTPLATE.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.ADAMANTIUM_LEGGINGS.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.ADAMANTIUM_BOOTS.get(),"armor/",ModelTemplates.FLAT_ITEM);

        generateFlatItemEx(blockModels,ModItems.ANCIENT_METAL_HELMET.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.ANCIENT_METAL_CHESTPLATE.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.ANCIENT_METAL_LEGGINGS.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.ANCIENT_METAL_BOOTS.get(),"armor/",ModelTemplates.FLAT_ITEM);

        generateFlatItemEx(blockModels,ModItems.HARD_HELMET.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.HARD_CHESTPLATE.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.HARD_LEGGINGS.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.HARD_BOOTS.get(),"armor/",ModelTemplates.FLAT_ITEM);

        generateFlatItemEx(blockModels,ModItems.MITHRIL_HELMET.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.MITHRIL_CHESTPLATE.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.MITHRIL_LEGGINGS.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.MITHRIL_BOOTS.get(),"armor/",ModelTemplates.FLAT_ITEM);

        generateFlatItemEx(blockModels,ModItems.RUSTED_IRON_HELMET.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.RUSTED_IRON_CHESTPLATE.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.RUSTED_IRON_LEGGINGS.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.RUSTED_IRON_BOOTS.get(),"armor/",ModelTemplates.FLAT_ITEM);

        generateFlatItemEx(blockModels,ModItems.SILVER_HELMET.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.SILVER_CHESTPLATE.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.SILVER_LEGGINGS.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.SILVER_BOOTS.get(),"armor/",ModelTemplates.FLAT_ITEM);

        generateFlatItemEx(blockModels,ModItems.BRONZE_HELMET.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.BRONZE_CHESTPLATE.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.BRONZE_LEGGINGS.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.BRONZE_BOOTS.get(),"armor/",ModelTemplates.FLAT_ITEM);

        generateFlatItemEx(blockModels,ModItems.HIGH_CARBON_STEEL_HELMET.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.HIGH_CARBON_STEEL_CHESTPLATE.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.HIGH_CARBON_STEEL_LEGGINGS.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.HIGH_CARBON_STEEL_BOOTS.get(),"armor/",ModelTemplates.FLAT_ITEM);

        generateFlatItemEx(blockModels,ModItems.SILVER_COPPER_HELMET.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.SILVER_COPPER_CHESTPLATE.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.SILVER_COPPER_LEGGINGS.get(),"armor/",ModelTemplates.FLAT_ITEM);
        generateFlatItemEx(blockModels,ModItems.SILVER_COPPER_BOOTS.get(),"armor/",ModelTemplates.FLAT_ITEM);

        createAnvil(blockModels,ModBlocks.ADAMANTIUM_ANVIL);
        createAnvil(blockModels,ModBlocks.ANCIENT_METAL_ANVIL);
        createAnvil(blockModels,ModBlocks.COPPER_ANVIL);
        createAnvil(blockModels,ModBlocks.GOLD_ANVIL);
        createAnvil(blockModels,ModBlocks.HARD_ANVIL);
        createAnvil(blockModels,ModBlocks.IRON_ANVIL);
        createAnvil(blockModels,ModBlocks.MITHRIL_ANVIL);
        createAnvil(blockModels,ModBlocks.SILVER_ANVIL);
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
                                        BlockModelGenerators.condition().term(SieveBlock.GRAVEL_LEVEL, 1), BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/gravel_layer1"))
                                )
                                .with(
                                        BlockModelGenerators.condition().term(SieveBlock.GRAVEL_LEVEL, 2), BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/gravel_layer2"))
                                )
                                .with(
                                        BlockModelGenerators.condition().term(SieveBlock.GRAVEL_LEVEL, 3), BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/gravel_layer3"))
                                )
                                .with(
                                        BlockModelGenerators.condition().term(SieveBlock.GRAVEL_LEVEL, 4), BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/gravel_layer4"))
                                )

                );

    }

    public void registerSimpleItemModel(BlockModelGenerators blockModels,Item item, ResourceLocation model) {
        blockModels.itemModelOutput.accept(item, ItemModelUtils.plainModel(model));
    }

    public static ResourceKey<EquipmentAsset> createEquipmentAssetId(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,name));
    }


    public void createAnvil(BlockModelGenerators blockModels, DeferredBlock<Block> anvilBlock) {
        final ModelTemplate ANVIL = ModelTemplates.create(MiteBreakAll.MODID+":anvils/template_anvil", TextureSlot.PARTICLE,TextureSlot.ALL);
        final TexturedModel.Provider MITE_ANVIL = TexturedModel.createDefault(ModModelProvider::anvil, ANVIL);

        ResourceLocation resourceLocationBase = MITE_ANVIL.create(anvilBlock.get(), blockModels.modelOutput);

        ResourceLocation resourceLocationTop0 =
                TexturedModel.createDefault(ModModelProvider::anvilTop,
                        ModelTemplates.create(MiteBreakAll.MODID+":anvils/anvil_top_0", TextureSlot.ALL,TextureSlot.PARTICLE,TextureSlot.LAYER0,TextureSlot.LAYER1,TextureSlot.LAYER2)
                ).createWithSuffix(anvilBlock.get(), "_top_0",blockModels.modelOutput);

        ResourceLocation resourceLocationTop1 =
                TexturedModel.createDefault(ModModelProvider::anvilTop,
                        ModelTemplates.create(MiteBreakAll.MODID+":anvils/anvil_top_1", TextureSlot.ALL,TextureSlot.PARTICLE,TextureSlot.LAYER0,TextureSlot.LAYER1,TextureSlot.LAYER2)
                ).createWithSuffix(anvilBlock.get(), "_top_1",blockModels.modelOutput);

        ResourceLocation resourceLocationTop2 =
                TexturedModel.createDefault(ModModelProvider::anvilTop,
                        ModelTemplates.create(MiteBreakAll.MODID+":anvils/anvil_top_2", TextureSlot.ALL,TextureSlot.PARTICLE,TextureSlot.LAYER0,TextureSlot.LAYER1,TextureSlot.LAYER2)
                ).createWithSuffix(anvilBlock.get(), "_top_2",blockModels.modelOutput);

        MultiPartGenerator generator = MultiPartGenerator.multiPart(anvilBlock.get());

        for (int i = 0;i < 4;i++) {
            generator.with(BlockModelGenerators.condition()
                            .term(BlockStateProperties.HORIZONTAL_FACING, Direction.from2DDataValue(i)),
                    BlockModelGenerators.plainVariant(resourceLocationBase).with(getYRotVariantMutator(i)));

            for (MiteAnvilBlock.AnvilStage stage : MiteAnvilBlock.AnvilStage.values()) {
                ResourceLocation topLocation;
                switch (stage) {
                    case NORMAL -> topLocation = resourceLocationTop0;
                    case CRACKED -> topLocation = resourceLocationTop1;
                    case DAMAGED -> topLocation = resourceLocationTop2;
                    default -> throw new IllegalStateException("Unexpected stage: " + stage);
                }

                generator.with(
                        BlockModelGenerators.condition()
                                .term(MiteAnvilBlock.ANVIL_STAGE, stage)
                                .term(BlockStateProperties.HORIZONTAL_FACING, Direction.from2DDataValue(i)),

                        BlockModelGenerators.plainVariant(topLocation)
                                .with(getYRotVariantMutator(i))
                );
            }

        }

        blockModels.blockStateOutput.accept(generator);

        ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.plainModel(resourceLocationBase);
        ItemModel.Unbaked itemmodel$unbaked1 = ItemModelUtils.composite(ItemModelUtils.plainModel(resourceLocationTop0),itemmodel$unbaked);
        ItemModel.Unbaked itemmodel$unbaked2 = ItemModelUtils.composite(ItemModelUtils.plainModel(resourceLocationTop1),itemmodel$unbaked);
        ItemModel.Unbaked itemmodel$unbaked3 = ItemModelUtils.composite(ItemModelUtils.plainModel(resourceLocationTop2),itemmodel$unbaked);


        blockModels.itemModelOutput
                .accept(
                        anvilBlock.asItem(),
                        ItemModelUtils.select(
                                new AnvilItemState(),
                                ItemModelUtils.when(MiteAnvilBlock.AnvilStage.NORMAL, itemmodel$unbaked1),
                                ItemModelUtils.when(MiteAnvilBlock.AnvilStage.CRACKED, itemmodel$unbaked2),
                                ItemModelUtils.when(MiteAnvilBlock.AnvilStage.DAMAGED, itemmodel$unbaked3)
                        )
                );
    }

    public static TextureMapping anvil(Block block) {
        return new TextureMapping()
                .put(TextureSlot.PARTICLE, getBlockTextureWithPrefix(block, "anvils/","/base"))
                .put(TextureSlot.ALL, getBlockTextureWithPrefix(block, "anvils/","/base"))
                ;
    }

    public static TextureMapping anvilTop(Block block) {
        return new TextureMapping()
                .put(TextureSlot.LAYER0, getBlockTextureWithPrefix(block, "anvils/","/top_damaged_0"))
                .put(TextureSlot.LAYER1, getBlockTextureWithPrefix(block, "anvils/","/top_damaged_1"))
                .put(TextureSlot.LAYER2, getBlockTextureWithPrefix(block, "anvils/","/top_damaged_2"))
                .put(TextureSlot.PARTICLE, getBlockTextureWithPrefix(block, "anvils/","/base"))
                .put(TextureSlot.ALL, getBlockTextureWithPrefix(block, "anvils/","/base"))
                ;
    }

    public static VariantMutator getYRotVariantMutator(int i){
        switch (i){
            case 0 -> {
                return BlockModelGenerators.NOP;
            }
            case 1 -> {
                return BlockModelGenerators.Y_ROT_90;
            }
            case 2 -> {
                return BlockModelGenerators.Y_ROT_180;
            }
            case 3 -> {
                return BlockModelGenerators.Y_ROT_270;
            }
        }
        throw new IllegalStateException("i should be included in {0,1,2,3}");
    }
}
