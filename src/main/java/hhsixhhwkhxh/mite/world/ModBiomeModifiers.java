package hhsixhhwkhxh.mite.world;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_MITE_ORE = createResourceKey("add_mite_ore");

    public static final ResourceKey<BiomeModifier> REMOVE_VANILLA_FEATURES =createResourceKey("remove_vanilla_features");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {

        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        HolderSet<Biome> overworldBiomes = biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD);


        context.register(ADD_MITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(overworldBiomes, HolderSet.direct(
                placedFeatures.getOrThrow(ModPlacedFeatures.COPPER_ORE_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.GRAVEL_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.COAL_ORE_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.HARD_ORE_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.SILVER_ORE_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.GOLD_ORE_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.IRON_ORE_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.MITHRIL_ORE_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.ADAMANTIUM_ORE_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.REDSTONE_ORE_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.DIAMOND_ORE_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.LAPIS_ORE_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.SILVER_FISH_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_PLACED_KEY),
                placedFeatures.getOrThrow(ModPlacedFeatures.MERCURY_ORE_PLACED_KEY)

                ), GenerationStep.Decoration.UNDERGROUND_ORES));


        context.register(REMOVE_VANILLA_FEATURES,
                new BiomeModifiers.RemoveFeaturesBiomeModifier(
                        // The biome(s) to remove from
                        biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                        // The feature(s) to remove from the biomes
                        HolderSet.direct(
                                placedFeatures.getOrThrow(OrePlacements.ORE_COPPER),
                                placedFeatures.getOrThrow(OrePlacements.ORE_COPPER_LARGE),
                                placedFeatures.getOrThrow(OrePlacements.ORE_GRAVEL),
                                placedFeatures.getOrThrow(OrePlacements.ORE_COAL_UPPER),
                                placedFeatures.getOrThrow(OrePlacements.ORE_COAL_LOWER),
                                placedFeatures.getOrThrow(OrePlacements.ORE_IRON_UPPER),
                                placedFeatures.getOrThrow(OrePlacements.ORE_IRON_MIDDLE),
                                placedFeatures.getOrThrow(OrePlacements.ORE_IRON_SMALL),
                                placedFeatures.getOrThrow(OrePlacements.ORE_GOLD_EXTRA),
                                placedFeatures.getOrThrow(OrePlacements.ORE_GOLD_DELTAS),
                                placedFeatures.getOrThrow(OrePlacements.ORE_GOLD),
                                placedFeatures.getOrThrow(OrePlacements.ORE_GOLD_LOWER),
                                placedFeatures.getOrThrow(OrePlacements.ORE_REDSTONE),
                                placedFeatures.getOrThrow(OrePlacements.ORE_REDSTONE_LOWER),
                                placedFeatures.getOrThrow(OrePlacements.ORE_DIAMOND),
                                placedFeatures.getOrThrow(OrePlacements.ORE_DIAMOND_BURIED),
                                placedFeatures.getOrThrow(OrePlacements.ORE_DIAMOND_LARGE),
                                placedFeatures.getOrThrow(OrePlacements.ORE_DIAMOND_MEDIUM),
                                placedFeatures.getOrThrow(OrePlacements.ORE_EMERALD),
                                placedFeatures.getOrThrow(OrePlacements.ORE_LAPIS),
                                placedFeatures.getOrThrow(OrePlacements.ORE_LAPIS_BURIED)
                                ),
                        // The generation steps to remove from
                        Set.of(
                                GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                                GenerationStep.Decoration.UNDERGROUND_ORES
                        )
                )
        );
        //7877195058483743021
    }

    private static ResourceKey<BiomeModifier> createResourceKey(String path){
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID, path));
    }
}
