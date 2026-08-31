package hhsixhhwkhxh.mite.block;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.custom.MaterialType;
import hhsixhhwkhxh.mite.item.ModToolMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    //public static final TagKey<Block> MINEABLE_WITH_PICKAXE = create("mineable/pickaxe");

    public static TagKey<Block> getToolMineableTag(MaterialType materialType, ModToolMaterials.ToolType toolType){
        return create("mineable/"+materialType.getName()+"_"+toolType.getName());
    }


    private static TagKey<Block> create(String name) {

        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,name));
    }
}
