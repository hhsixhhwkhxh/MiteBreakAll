package hhsixhhwkhxh.mite.world;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> COPPER_ORE_PLACED_KEY = createResourceKey("copper_ore");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(COPPER_ORE_PLACED_KEY,new PlacedFeature(configuredFeatureGetter.getOrThrow(ModConfigureFeatures.COPPER_ORE_KEY), List.of(
                CountPlacement.of(10),InSquarePlacement.spread(),HeightRangePlacement.uniform(VerticalAnchor.absolute(32),VerticalAnchor.absolute(128)))));
    }

    private static ResourceKey<PlacedFeature> createResourceKey(String path){
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID, path));
    }
}
