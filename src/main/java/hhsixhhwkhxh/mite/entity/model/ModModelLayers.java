package hhsixhhwkhxh.mite.entity.model;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation BROWN_BEAR = register("brown_bear");
    public static final ModelLayerLocation BROWN_BEAR_BABY = register("brown_bear_baby");

    private static ModelLayerLocation register(String path){
        return new ModelLayerLocation(
                ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID, path),
                "main"
        );
    }
}
