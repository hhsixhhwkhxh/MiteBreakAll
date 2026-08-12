package hhsixhhwkhxh.mite.world;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.Tags;

public class ModConfigureFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> COPPER_ORE_KEY = createResourceKey("copper_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRAVEL_KEY = createResourceKey("gravel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_ORE_KEY = createResourceKey("coal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HARD_ORE_KEY = createResourceKey("hard_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE_KEY = createResourceKey("silver_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLD_ORE_KEY = createResourceKey("gold_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> IRON_ORE_KEY = createResourceKey("iron_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MITHRIL_ORE_KEY = createResourceKey("mithril_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ADAMANTIUM_ORE_KEY = createResourceKey("adamantium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDSTONE_ORE_KEY = createResourceKey("redstone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIAMOND_ORE_KEY = createResourceKey("diamond_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAPIS_ORE_KEY = createResourceKey("lapis_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_FISH_KEY = createResourceKey("silver_fish");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE_KEY = createResourceKey("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MERCURY_ORE_KEY = createResourceKey("mercury_ore");




    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(Tags.Blocks.STONES);

        context.register(COPPER_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable,Blocks.COPPER_ORE.defaultBlockState(),6)));
        context.register(GRAVEL_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable,Blocks.GRAVEL.defaultBlockState(),32)));
        context.register(COAL_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable,Blocks.COAL_ORE.defaultBlockState(),16)));
        context.register(HARD_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable, ModBlocks.HARD_ORE.get().defaultBlockState(),5)));
        context.register(SILVER_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable, ModBlocks.SILVER_ORE.get().defaultBlockState(),6)));
        context.register(GOLD_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable, Blocks.GOLD_ORE.defaultBlockState(),4)));
        context.register(IRON_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable, Blocks.IRON_ORE.defaultBlockState(),6)));
        context.register(MITHRIL_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable, ModBlocks.MITHRIL_ORE.get().defaultBlockState(),2)));
        context.register(ADAMANTIUM_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable, ModBlocks.ADAMANTIUM_ORE.get().defaultBlockState(),3)));
        context.register(REDSTONE_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable, Blocks.REDSTONE_ORE.defaultBlockState(),5)));
        context.register(DIAMOND_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable, Blocks.DIAMOND_ORE.defaultBlockState(),3)));
        context.register(LAPIS_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable, Blocks.LAPIS_ORE.defaultBlockState(),3)));
        context.register(SILVER_FISH_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable, Blocks.INFESTED_STONE.defaultBlockState(),3)));
        context.register(TIN_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable, ModBlocks.TIN_ORE.get().defaultBlockState(),4)));
        context.register(MERCURY_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable, ModBlocks.MERCURY_ORE.get().defaultBlockState(),1)));


    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createResourceKey(String path){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID, path));
    }
}
