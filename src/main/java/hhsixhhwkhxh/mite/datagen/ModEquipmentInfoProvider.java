package hhsixhhwkhxh.mite.datagen;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEquipmentInfoProvider implements DataProvider {
    private final PackOutput.PathProvider path;

    public ModEquipmentInfoProvider(PackOutput output) {
        this.path = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    private void add(BiConsumer<ResourceLocation, EquipmentClientInfo> registrar) {
        String[] armorList = {"adamantium","ancient_metal","bronze","hard","high_carbon_steel","mithril","rusted_iron","silver_copper","silver","copper"};

        for(String armorName:armorList){
            registrar.accept(
                    // Must match Equippable#assetId
                    ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID, armorName),
                    EquipmentClientInfo.builder()
                            // For humanoid head, chest, and feet
                            .addLayers(
                                    EquipmentClientInfo.LayerType.HUMANOID,
                                    // Base texture
                                    new EquipmentClientInfo.Layer(
                                            // The relative texture of the armor
                                            // Points to assets/examplemod/textures/entity/equipment/humanoid/xxx.png
                                            ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID, armorName),
                                            Optional.empty(),
                                            false
                                    )
                            )
                            // For humanoid legs
                            .addLayers(
                                    EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS,
                                    new EquipmentClientInfo.Layer(
                                            // Points to assets/examplemod/textures/entity/equipment/humanoid_leggings/xxx.png
                                            ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID, armorName),
                                            Optional.empty(),
                                            false
                                    )
                            )
                            .build()
            );
        }

    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<ResourceLocation, EquipmentClientInfo> map = new HashMap<>();
        this.add((name, info) -> {
            if (map.putIfAbsent(name, info) != null) {
                throw new IllegalStateException("Tried to register equipment client info twice for id: " + name);
            }
        });
        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.path, map);
    }

    @Override
    public String getName() {
        return "Equipment Client Infos: " + MiteBreakAll.MODID;
    }
}
