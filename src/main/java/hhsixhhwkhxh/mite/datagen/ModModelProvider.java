package hhsixhhwkhxh.mite.datagen;


import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.BiFunction;

import static net.minecraft.client.data.models.BlockModelGenerators.ROTATION_HORIZONTAL_FACING;


public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, MiteBreakAll.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        createFlintCraftingTable(blockModels,ModBlocks.FLINT_CRAFTING_TABLE.get(), Blocks.OAK_LOG);
        blockModels.createTrivialCube(ModBlocks.SILVER_ORE.get());

        itemModels.generateFlatItem(ModItems.WILD_APPLE.get(), ModelTemplates.FLAT_ITEM);
    }

    public void createFlintCraftingTable(BlockModelGenerators blockModels,Block craftingTableBlock, Block craftingTableMaterialBlock) {
        /*
        BiFunction<Block, Block, TextureMapping> textureMappingGetter = (b, block)->{
            return new TextureMapping().put(TextureSlot.EAST, TextureMapping.getBlockTexture(block))
                    .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block))
                    .put(TextureSlot.WEST, TextureMapping.getBlockTexture(block))
                    .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block))
                    .put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(block))
                    .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(block,"_top"))
                    .put(TextureSlot.UP, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/craft_table/flint/top"));
        };*/
        BiFunction<Block, Block, TextureMapping> textureMappingGetter = (b, block)->{
            return new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block))
                    .put(TextureSlot.FRONT,TextureMapping.getBlockTexture(block))
                    .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block,"_top"))
                    .put(TextureSlot.TOP, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/craft_table/flint/top"));
        };

        //this.createHorizontallyRotatedBlock(Blocks.LOOM, TexturedModel.ORIENTABLE)
        //blockModels.createCraftingTableLike(craftingTableBlock,craftingTableMaterialBlock,textureMappingGetter);
        TextureMapping texturemapping = textureMappingGetter.apply(craftingTableBlock, craftingTableMaterialBlock);
        //MultiVariant multivariant = BlockModelGenerators.plainVariant(TexturedModel.ORIENTABLE.create(craftingTableBlock, blockModels.modelOutput));
        MultiVariant multivariant = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM.create(craftingTableBlock, texturemapping,blockModels.modelOutput));

        //blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(craftingTableBlock,multivariant));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(craftingTableBlock, multivariant).with(ROTATION_HORIZONTAL_FACING));

    }


}
