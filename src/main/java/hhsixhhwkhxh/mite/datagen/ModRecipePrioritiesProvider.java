package hhsixhhwkhxh.mite.datagen;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.RecipePrioritiesProvider;

import java.util.concurrent.CompletableFuture;

public class ModRecipePrioritiesProvider extends RecipePrioritiesProvider {
    public ModRecipePrioritiesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, MiteBreakAll.MODID);
    }

    @Override
    protected void start() {
        //this.add("minecraft:golden_apple",1);
    }
}
