package hhsixhhwkhxh.mite.datagen;


import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.MiteAnvilBlock;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.block.SieveBlock;
import hhsixhhwkhxh.mite.custom.AnvilItemState;
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
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.registries.DeferredBlock;
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

        createSimpleTrivialCube(blockModels,ModBlocks.SILVER_ORE.get(), FolderType.ORES);
        createSimpleTrivialCube(blockModels,ModBlocks.HARD_ORE.get(), FolderType.ORES);
        createSimpleTrivialCube(blockModels,ModBlocks.MERCURY_ORE.get(), FolderType.ORES);
        createSimpleTrivialCube(blockModels,ModBlocks.MITHRIL_ORE.get(), FolderType.ORES);
        createSimpleTrivialCube(blockModels,ModBlocks.ADAMANTIUM_ORE.get(), FolderType.ORES);
        createSimpleTrivialCube(blockModels,ModBlocks.TIN_ORE.get(), FolderType.ORES);

        generateSimpleFlatItem(blockModels,ModItems.WILD_APPLE.get(),FolderType.FOOD);

        createStrawBerryBush(blockModels);

        generateSimpleFlatItem(blockModels,ModItems.FLINT_SHARD.get(),FolderType.SHARDS);
        generateSimpleFlatItem(blockModels,ModItems.OBSIDIAN_SHARD.get(),FolderType.SHARDS);
        generateSimpleFlatItem(blockModels,ModItems.DIAMOND_SHARD.get(),FolderType.SHARDS);
        generateSimpleFlatItem(blockModels,ModItems.EMERALD_SHARD.get(),FolderType.SHARDS);
        generateSimpleFlatItem(blockModels,ModItems.GLASS_SHARD.get(),FolderType.SHARDS);
        generateSimpleFlatItem(blockModels,ModItems.QUARTZ_SHARD.get(),FolderType.SHARDS);

        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_NUGGET.get(),FolderType.NUGGETS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_NUGGET.get(),FolderType.NUGGETS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_NUGGET.get(),FolderType.NUGGETS);
        generateSimpleFlatItem(blockModels,ModItems.MERCURY_NUGGET.get(),FolderType.NUGGETS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_NUGGET.get(),FolderType.NUGGETS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_NUGGET.get(),FolderType.NUGGETS);
        generateSimpleFlatItem(blockModels,ModItems.COPPER_NUGGET.get(),FolderType.NUGGETS);

        createSieve(blockModels);

        itemModels.generateFlatItem(ModItems.MESH_LEATHER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MESH_STRING.get(), ModelTemplates.FLAT_ITEM);


        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_INGOT.get(),FolderType.INGOTS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_INGOT.get(),FolderType.INGOTS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_INGOT.get(),FolderType.INGOTS);
        generateSimpleFlatItem(blockModels,ModItems.MERCURY_INGOT.get(),FolderType.INGOTS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_INGOT.get(),FolderType.INGOTS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_INGOT.get(),FolderType.INGOTS);
        generateSimpleFlatItem(blockModels,ModItems.TIN_INGOT.get(),FolderType.INGOTS);


        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_HELMET.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_CHESTPLATE.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_LEGGINGS.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_BOOTS.get(),FolderType.ARMOR);

        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_HELMET.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_CHESTPLATE.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_LEGGINGS.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_BOOTS.get(),FolderType.ARMOR);

        generateSimpleFlatItem(blockModels,ModItems.HARD_HELMET.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.HARD_CHESTPLATE.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.HARD_LEGGINGS.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.HARD_BOOTS.get(),FolderType.ARMOR);

        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_HELMET.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_CHESTPLATE.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_LEGGINGS.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_BOOTS.get(),FolderType.ARMOR);

        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_HELMET.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_CHESTPLATE.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_LEGGINGS.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_BOOTS.get(),FolderType.ARMOR);

        generateSimpleFlatItem(blockModels,ModItems.SILVER_HELMET.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_CHESTPLATE.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_LEGGINGS.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_BOOTS.get(),FolderType.ARMOR);

        generateSimpleFlatItem(blockModels,ModItems.BRONZE_HELMET.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.BRONZE_CHESTPLATE.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.BRONZE_LEGGINGS.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.BRONZE_BOOTS.get(),FolderType.ARMOR);

        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_HELMET.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_CHESTPLATE.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_LEGGINGS.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_BOOTS.get(),FolderType.ARMOR);

        generateSimpleFlatItem(blockModels,ModItems.SILVER_COPPER_HELMET.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_COPPER_CHESTPLATE.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_COPPER_LEGGINGS.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_COPPER_BOOTS.get(),FolderType.ARMOR);

        generateSimpleFlatItem(blockModels,ModItems.COPPER_HELMET.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.COPPER_CHESTPLATE.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.COPPER_LEGGINGS.get(),FolderType.ARMOR);
        generateSimpleFlatItem(blockModels,ModItems.COPPER_BOOTS.get(),FolderType.ARMOR);

        createAnvil(blockModels,ModBlocks.ADAMANTIUM_ANVIL);
        createAnvil(blockModels,ModBlocks.ANCIENT_METAL_ANVIL);
        createAnvil(blockModels,ModBlocks.COPPER_ANVIL);
        createAnvil(blockModels,ModBlocks.GOLD_ANVIL);
        createAnvil(blockModels,ModBlocks.HARD_ANVIL);
        createAnvil(blockModels,ModBlocks.IRON_ANVIL);
        createAnvil(blockModels,ModBlocks.MITHRIL_ANVIL);
        createAnvil(blockModels,ModBlocks.SILVER_ANVIL);



        generateSimpleFlatItem(blockModels,ModItems.FLINT_PICKAXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.FLINT_SHOVEL.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.FLINT_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.FLINT_HATCHET.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.OBSIDIAN_SHOVEL.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.OBSIDIAN_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.OBSIDIAN_HATCHET.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.IRON_WAR_HAMMER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.IRON_BATTLE_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.IRON_MATTOCK.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.IRON_SCYTHE.get(),FolderType.TOOLS);
        useVanillaItemModel(blockModels,ModItems.IRON_SHEARS.get(),Items.SHEARS);
        generateSimpleFlatItem(blockModels,ModItems.IRON_DAGGER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.IRON_HATCHET.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.GOLDEN_WAR_HAMMER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.GOLDEN_BATTLE_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.GOLDEN_MATTOCK.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.GOLDEN_SCYTHE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.GOLDEN_SHEARS.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.GOLDEN_DAGGER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.GOLDEN_HATCHET.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.COPPER_WAR_HAMMER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.COPPER_BATTLE_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.COPPER_MATTOCK.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.COPPER_SCYTHE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.COPPER_SHEARS.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.COPPER_DAGGER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.COPPER_HATCHET.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.BRONZE_SWORD.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.BRONZE_PICKAXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.BRONZE_SHOVEL.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.BRONZE_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.BRONZE_WAR_HAMMER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.BRONZE_BATTLE_AXE.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.SILVER_COPPER_SWORD.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_COPPER_PICKAXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_COPPER_SHOVEL.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_COPPER_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_COPPER_WAR_HAMMER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_COPPER_BATTLE_AXE.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.SILVER_SWORD.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_PICKAXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_SHOVEL.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_HOE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_WAR_HAMMER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_BATTLE_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_MATTOCK.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_SCYTHE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_SHEARS.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_DAGGER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.SILVER_HATCHET.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_SWORD.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_PICKAXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_SHOVEL.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_HOE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_WAR_HAMMER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_BATTLE_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_MATTOCK.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_SCYTHE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_SHEARS.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_DAGGER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.RUSTED_IRON_HATCHET.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_SWORD.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_PICKAXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_SHOVEL.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_HOE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_WAR_HAMMER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_BATTLE_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_MATTOCK.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_SCYTHE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_SHEARS.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_DAGGER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HIGH_CARBON_STEEL_HATCHET.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.HARD_SWORD.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_PICKAXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_SHOVEL.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_HOE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_WAR_HAMMER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_BATTLE_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_MATTOCK.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_SCYTHE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_SHEARS.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_DAGGER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.HARD_HATCHET.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_SWORD.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_PICKAXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_SHOVEL.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_HOE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_WAR_HAMMER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_BATTLE_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_MATTOCK.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_SCYTHE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_SHEARS.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_DAGGER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ANCIENT_METAL_HATCHET.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_SWORD.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_PICKAXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_SHOVEL.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_HOE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_WAR_HAMMER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_BATTLE_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_MATTOCK.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_SCYTHE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_SHEARS.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_DAGGER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.MITHRIL_HATCHET.get(),FolderType.TOOLS);

        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_SWORD.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_PICKAXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_SHOVEL.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_HOE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_WAR_HAMMER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_BATTLE_AXE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_MATTOCK.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_SCYTHE.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_SHEARS.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_DAGGER.get(),FolderType.TOOLS);
        generateSimpleFlatItem(blockModels,ModItems.ADAMANTIUM_HATCHET.get(),FolderType.TOOLS);

        createProxyItemModel(blockModels);
    }

    public enum FolderType{
        ORES("ores/"),
        FOOD("food/"),
        SHARDS("shards/"),
        NUGGETS("nuggets/"),
        INGOTS("ingots/"),
        ARMOR("armor/"),
        TOOLS("tools/")
        ;
        final String path;
        FolderType(String path) {
            this.path = path;
        }
        public String getPath(){
            return path;
        }
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
        generateSimpleFlatItem(blockModels,ModItems.STRAWBERRIES.get(), FolderType.FOOD);
        generateSimpleFlatItem(blockModels,ModBlocks.STRAWBERRY_BUSH.asItem(), FolderType.FOOD);
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
        ResourceLocation resourceLocation = modelTemplate.create(ModelLocationUtils.getModelLocation(item), new TextureMapping().put(TextureSlot.LAYER0, getItemTextureWithPrefix(item,prefix)), blockModels.modelOutput);
        blockModels.itemModelOutput.accept(item, ItemModelUtils.plainModel(resourceLocation));
    }

    public void generateSimpleFlatItem(BlockModelGenerators blockModels,Item item,FolderType folderType) {
        generateFlatItemEx(blockModels,item,folderType.getPath(),ModelTemplates.FLAT_ITEM);
    }

    public static ResourceLocation getItemTextureWithPrefix(Item item,String prefix) {
        ResourceLocation resourcelocation = BuiltInRegistries.ITEM.getKey(item);
        return resourcelocation.withPrefix("item/"+prefix);
    }


    public void createProxyItemModel(BlockModelGenerators blockModels){
        ModItems.proxyItemMap.forEach((item,deferredItem)-> useVanillaItemModel(blockModels,deferredItem.get(),Items.BARRIER));
    }

    public void useVanillaItemModel(BlockModelGenerators blockModels,Item modItem,Item vanillaItem){
        blockModels.itemModelOutput.accept(modItem, ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(vanillaItem)));
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

    public void createSimpleTrivialCube(BlockModelGenerators blockModels,Block block, FolderType folderType) {
        createTrivialCubeEx(blockModels,block,folderType.getPath(),TexturedModel.CUBE);
    }

    public void createSieve(BlockModelGenerators blockModels) {
        registerSimpleItemModel(blockModels,ModBlocks.SIEVE.asItem(),ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/sieve"));
        blockModels.blockStateOutput
                .accept(
                        MultiPartGenerator.multiPart(ModBlocks.SIEVE.get())
                                .with(BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/sieve")))
                                .with(
                                        BlockModelGenerators.condition().term(SieveBlock.MESH_TYPE, SieveBlock.MeshType.LEATHER), BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/mesh_leather"))
                                )
                                .with(
                                        BlockModelGenerators.condition().term(SieveBlock.MESH_TYPE, SieveBlock.MeshType.STRING), BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"block/sieve/mesh_string"))
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
