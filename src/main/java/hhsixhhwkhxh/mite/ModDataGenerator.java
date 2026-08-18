package hhsixhhwkhxh.mite;

import hhsixhhwkhxh.mite.datagen.*;
import hhsixhhwkhxh.mite.world.ModBiomeModifiers;
import hhsixhhwkhxh.mite.world.ModConfigureFeatures;
import hhsixhhwkhxh.mite.world.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.NoneBiomeModifier;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = MiteBreakAll.MODID)
public class ModDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        event.addProvider(
                new LootTableProvider(
                        packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTablesProvider::new, LootContextParamSets.BLOCK)),provider
                )
        );

        event.addProvider(new ModEnUsLangProvider(packOutput));
        event.addProvider(new ModZhCnLangProvider(packOutput));
        event.addProvider(new ModModelProvider(packOutput));
        event.addProvider(new ModBlockTagsProvider(packOutput,provider));
        event.createProvider(ModRecipesProvider.Runner::new);
        //event.createProvider(ModRecipePrioritiesProvider::new);
        event.addProvider(new ModEquipmentInfoProvider(packOutput));

        event.addProvider(
                new DatapackBuiltinEntriesProvider(
                        packOutput,
                        provider,
                        new RegistrySetBuilder()
                                .add(Registries.CONFIGURED_FEATURE, ModConfigureFeatures::bootstrap)
                                .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
                                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap),
                        Set.of(MiteBreakAll.MODID,"minecraft")
                )
        );




    }
}
