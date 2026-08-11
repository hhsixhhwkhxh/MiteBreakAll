package hhsixhhwkhxh.mite.world;

import hhsixhhwkhxh.mite.MiteBreakAll;
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

public class ModConfigureFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> COPPER_ORE_KEY = createResourceKey("copper_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new BlockMatchTest(Blocks.STONE);
        context.register(COPPER_ORE_KEY,new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(stoneReplaceable,Blocks.COPPER_ORE.defaultBlockState(),6)));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createResourceKey(String path){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID, path));
    }
}
