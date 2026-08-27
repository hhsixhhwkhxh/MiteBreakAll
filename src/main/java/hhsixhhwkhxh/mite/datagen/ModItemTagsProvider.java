package hhsixhhwkhxh.mite.datagen;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.custom.ModItemTags;
import hhsixhhwkhxh.mite.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    private static final Map<Item, ItemLike> SubstituteMap = Map.ofEntries(
            Map.entry(Items.DIAMOND, ModItems.DIAMOND_SHARD),
            Map.entry(Items.GOLD_INGOT, Items.GOLD_NUGGET),
            Map.entry(Items.IRON_INGOT, Items.IRON_NUGGET),
            Map.entry(Items.COPPER_INGOT,ModItems.COPPER_NUGGET)

    );

    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MiteBreakAll.MODID);
    }


    @Override
    protected void addTags(HolderLookup.Provider provider) {

        replace(ItemTags.DIAMOND_TOOL_MATERIALS,Items.DIAMOND);
        replace(ItemTags.GOLD_TOOL_MATERIALS,Items.GOLD_INGOT);
        replace(ItemTags.IRON_TOOL_MATERIALS,Items.IRON_INGOT);

        replace(ItemTags.REPAIRS_DIAMOND_ARMOR,Items.DIAMOND);
        replace(ItemTags.REPAIRS_IRON_ARMOR,Items.IRON_INGOT);
        replace(ItemTags.REPAIRS_GOLD_ARMOR,Items.GOLD_INGOT);

        this.tag(ModItemTags.REPAIRS_ADAMANTIUM_ARMOR).add(ModItems.ADAMANTIUM_NUGGET.get());
        this.tag(ModItemTags.REPAIRS_ANCIENT_METAL_ARMOR).add(ModItems.ANCIENT_METAL_NUGGET.get());
        this.tag(ModItemTags.REPAIRS_COPPER_ARMOR).add(ModItems.COPPER_NUGGET.get());
        this.tag(ModItemTags.REPAIRS_HARD_ARMOR).add(ModItems.HARD_NUGGET.get());
        this.tag(ModItemTags.REPAIRS_MITHRIL_ARMOR).add(ModItems.MITHRIL_NUGGET.get());
        this.tag(ModItemTags.REPAIRS_SILVER_ARMOR).add(ModItems.SILVER_NUGGET.get());
        this.tag(ModItemTags.REPAIRS_SILVER_COPPER_ARMOR).add(ModItems.SILVER_NUGGET.get()).add(ModItems.COPPER_NUGGET.get());
    }

    private void replace(TagKey<Item> key,Item oldMaterial){
        if(!SubstituteMap.containsKey(oldMaterial)){
            throw new RuntimeException(oldMaterial+" wasn't included in SubstituteMap");
        }
        TagAppender<Item, Item> tagAppender = this.tag(key);
        tagAppender.remove(oldMaterial);
        tagAppender.add(SubstituteMap.get(oldMaterial).asItem());
    }

}
