package hhsixhhwkhxh.mite.block;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MiteBreakAll.MODID);


    public static final DeferredBlock<Block> SILVER_ORE = registerBlock(
            "silver_ore",
            p_368251_ -> new DropExperienceBlock(ConstantInt.of(0), p_368251_),
            BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)
    );

    public static final DeferredBlock<Block> FLINT_CRAFTING_TABLE = registerBlock(
            "flint_crafting_table",
            FlintCraftingTable::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5F).sound(SoundType.WOOD).ignitedByLava()
    );

    public static <B extends Block> DeferredBlock<B> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> func, BlockBehaviour.Properties props) {
        DeferredBlock<B> block = BLOCKS.registerBlock(name,func,props);;
        ModItems.ITEMS.registerSimpleBlockItem(name, block,new Item.Properties());
        return block;
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);

    }
}
