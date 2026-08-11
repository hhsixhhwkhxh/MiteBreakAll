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
    public static final ResourceKey<BiomeModifier> ADD_COPPER_ORE = createResourceKey("add_copper_ore");

    public static final ResourceKey<BiomeModifier> REMOVE_VANILLA_COPPER =createResourceKey("remove_vanilla_copper");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {

        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        HolderSet<Biome> overworldBiomes = biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD);

        context.register(ADD_COPPER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(overworldBiomes, HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.COPPER_ORE_PLACED_KEY)), GenerationStep.Decoration.UNDERGROUND_ORES));


        context.register(REMOVE_VANILLA_COPPER,
                new BiomeModifiers.RemoveFeaturesBiomeModifier(
                        // The biome(s) to remove from
                        biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                        // The feature(s) to remove from the biomes
                        HolderSet.direct(placedFeatures.getOrThrow(OrePlacements.ORE_COPPER)),
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
