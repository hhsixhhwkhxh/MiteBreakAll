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
    public static final ResourceKey<PlacedFeature> GRAVEL_PLACED_KEY = createResourceKey("gravel");
    public static final ResourceKey<PlacedFeature> COAL_ORE_PLACED_KEY = createResourceKey("coal_ore");
    public static final ResourceKey<PlacedFeature> HARD_ORE_PLACED_KEY = createResourceKey("hard_ore");
    public static final ResourceKey<PlacedFeature> SILVER_ORE_PLACED_KEY = createResourceKey("silver_ore");
    public static final ResourceKey<PlacedFeature> GOLD_ORE_PLACED_KEY = createResourceKey("gold_ore");
    public static final ResourceKey<PlacedFeature> IRON_ORE_PLACED_KEY = createResourceKey("iron_ore");
    public static final ResourceKey<PlacedFeature> MITHRIL_ORE_PLACED_KEY = createResourceKey("mithril_ore");
    public static final ResourceKey<PlacedFeature> ADAMANTIUM_ORE_PLACED_KEY = createResourceKey("adamantium_ore");
    public static final ResourceKey<PlacedFeature> REDSTONE_ORE_PLACED_KEY = createResourceKey("redstone_ore");
    public static final ResourceKey<PlacedFeature> DIAMOND_ORE_PLACED_KEY = createResourceKey("diamond_ore");
    public static final ResourceKey<PlacedFeature> LAPIS_ORE_PLACED_KEY = createResourceKey("lapis_ore");
    public static final ResourceKey<PlacedFeature> SILVER_FISH_PLACED_KEY = createResourceKey("silver_fish");
    public static final ResourceKey<PlacedFeature> TIN_ORE_PLACED_KEY = createResourceKey("tin_ore");
    public static final ResourceKey<PlacedFeature> MERCURY_ORE_PLACED_KEY = createResourceKey("mercury_ore");



    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        //context.register(COPPER_ORE_PLACED_PLACED_KEY,new PlacedFeature(configuredFeatureGetter.getOrThrow(ModConfigureFeatures.COPPER_ORE_PLACED_KEY), List.of(
        //        CountPlacement.of(40),InSquarePlacement.spread(),HeightRangePlacement.uniform(VerticalAnchor.absolute(32),VerticalAnchor.absolute(128)))));
        registerOre(COPPER_ORE_PLACED_KEY, ModConfigureFeatures.COPPER_ORE_KEY,40,VerticalAnchor.absolute(32),VerticalAnchor.absolute(128),context,configuredFeatureGetter);
        registerOre(GRAVEL_PLACED_KEY, ModConfigureFeatures.GRAVEL_KEY,200,VerticalAnchor.absolute(24),VerticalAnchor.absolute(128),context,configuredFeatureGetter);
        registerOre(COAL_ORE_PLACED_KEY,ModConfigureFeatures.COAL_ORE_KEY,50,VerticalAnchor.absolute(16),VerticalAnchor.absolute(96),context,configuredFeatureGetter);
        registerOre(HARD_ORE_PLACED_KEY,ModConfigureFeatures.HARD_ORE_KEY,8,VerticalAnchor.aboveBottom(0),VerticalAnchor.absolute(24),context,configuredFeatureGetter);
        registerOre(SILVER_ORE_PLACED_KEY,ModConfigureFeatures.SILVER_ORE_KEY,25,VerticalAnchor.aboveBottom(0),VerticalAnchor.absolute(96),context,configuredFeatureGetter);
        registerOre(GOLD_ORE_PLACED_KEY,ModConfigureFeatures.GOLD_ORE_KEY,20,VerticalAnchor.aboveBottom(0),VerticalAnchor.absolute(48),context,configuredFeatureGetter);
        registerOre(IRON_ORE_PLACED_KEY,ModConfigureFeatures.IRON_ORE_KEY,60,VerticalAnchor.aboveBottom(0),VerticalAnchor.absolute(50),context,configuredFeatureGetter);
        registerOre(MITHRIL_ORE_PLACED_KEY,ModConfigureFeatures.MITHRIL_ORE_KEY,4,VerticalAnchor.aboveBottom(0),VerticalAnchor.absolute(32),context,configuredFeatureGetter);
        registerOre(ADAMANTIUM_ORE_PLACED_KEY,ModConfigureFeatures.ADAMANTIUM_ORE_KEY,3,VerticalAnchor.aboveBottom(0),VerticalAnchor.absolute(24),context,configuredFeatureGetter);
        registerOre(REDSTONE_ORE_PLACED_KEY,ModConfigureFeatures.REDSTONE_ORE_KEY,10,VerticalAnchor.aboveBottom(0),VerticalAnchor.absolute(24),context,configuredFeatureGetter);
        registerOre(DIAMOND_ORE_PLACED_KEY,ModConfigureFeatures.DIAMOND_ORE_KEY,5,VerticalAnchor.aboveBottom(0),VerticalAnchor.absolute(32),context,configuredFeatureGetter);
        registerOre(LAPIS_ORE_PLACED_KEY,ModConfigureFeatures.LAPIS_ORE_KEY,6,VerticalAnchor.absolute(8),VerticalAnchor.absolute(40),context,configuredFeatureGetter);
        registerOre(SILVER_FISH_PLACED_KEY,ModConfigureFeatures.SILVER_FISH_KEY,5,VerticalAnchor.aboveBottom(0),VerticalAnchor.absolute(64),context,configuredFeatureGetter);
        registerOre(TIN_ORE_PLACED_KEY,ModConfigureFeatures.TIN_ORE_KEY,40,VerticalAnchor.aboveBottom(0),VerticalAnchor.absolute(128),context,configuredFeatureGetter);
        registerOre(MERCURY_ORE_PLACED_KEY,ModConfigureFeatures.MERCURY_ORE_KEY,0,VerticalAnchor.aboveBottom(0),VerticalAnchor.absolute(10),context,configuredFeatureGetter);

    }

    public static void registerOre(ResourceKey<PlacedFeature> placeKey,ResourceKey<ConfiguredFeature<?, ?>> ConfiguredKey,int count,VerticalAnchor minInclusive, VerticalAnchor maxInclusive,BootstrapContext<PlacedFeature> context,HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter){
        context.register(placeKey,new PlacedFeature(configuredFeatureGetter.getOrThrow(ConfiguredKey), List.of(
                CountPlacement.of(count),InSquarePlacement.spread(),HeightRangePlacement.uniform(minInclusive,maxInclusive))));

    }

    private static ResourceKey<PlacedFeature> createResourceKey(String path){
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID, path));
    }
}
