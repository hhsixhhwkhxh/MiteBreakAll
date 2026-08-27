package hhsixhhwkhxh.mite.block;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.item.ModItems;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MiteBreakAll.MODID);


    public static final DeferredBlock<Block> SILVER_ORE = registerOreBlock("silver_ore", 0,3.0F, 3.0F);
    public static final DeferredBlock<Block> HARD_ORE = registerOreBlock("hard_ore", 0,3.0F, 3.0F);
    public static final DeferredBlock<Block> MERCURY_ORE = registerOreBlock("mercury_ore", 0,3.0F, 3.0F);
    public static final DeferredBlock<Block> MITHRIL_ORE = registerOreBlock("mithril_ore", 0,3.0F, 3.0F);
    public static final DeferredBlock<Block> ADAMANTIUM_ORE = registerOreBlock("adamantium_ore", 0,3.0F, 3.0F);
    public static final DeferredBlock<Block> TIN_ORE = registerOreBlock("tin_ore", 0,3.0F, 3.0F);

    public static final DeferredBlock<Block> SIEVE = registerBlock(
            "sieve",
            SieveBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(0.6F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()
    );


    public static final DeferredBlock<Block> FLINT_CRAFTING_TABLE = registerBlock(
            "flint_crafting_table",
            FlintCraftingTable::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5F).sound(SoundType.WOOD).ignitedByLava()
    );

    public static final DeferredBlock<Block> STRAWBERRY_BUSH = registerBlock(
            "strawberry_bush",
            StrawBerryBushBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .randomTicks()
                    .noCollission()
                    .sound(SoundType.SWEET_BERRY_BUSH)
                    .pushReaction(PushReaction.DESTROY)
    );

    public static final DeferredBlock<Block> ADAMANTIUM_ANVIL = registerAnvil("adamantium_anvil",MiteAnvilBlock.AnvilVariant.ADAMANTIUM,12697600);
    public static final DeferredBlock<Block> ANCIENT_METAL_ANVIL = registerAnvil("ancient_metal_anvil",MiteAnvilBlock.AnvilVariant.ANCIENT_METAL,1388800);
    public static final DeferredBlock<Block> COPPER_ANVIL = registerAnvil("copper_anvil",MiteAnvilBlock.AnvilVariant.COPPER,198400);
    public static final DeferredBlock<Block> GOLD_ANVIL = registerAnvil("gold_anvil",MiteAnvilBlock.AnvilVariant.GOLD,198400);
    public static final DeferredBlock<Block> HARD_ANVIL = registerAnvil("hard_anvil",MiteAnvilBlock.AnvilVariant.HARD,595200);
    public static final DeferredBlock<Block> IRON_ANVIL = registerAnvil("iron_anvil",MiteAnvilBlock.AnvilVariant.IRON,396800);
    public static final DeferredBlock<Block> MITHRIL_ANVIL = registerAnvil("mithril_anvil",MiteAnvilBlock.AnvilVariant.MITHRIL,9523200);
    public static final DeferredBlock<Block> SILVER_ANVIL = registerAnvil("silver_anvil",MiteAnvilBlock.AnvilVariant.SILVER,198400);


    public static <B extends Block> DeferredBlock<B> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> func, BlockBehaviour.Properties props) {
        DeferredBlock<B> block = BLOCKS.registerBlock(name,func,props);;
        ModItems.ITEMS.registerSimpleBlockItem(name, block,new Item.Properties());
        return block;
    }

    private static DeferredBlock<Block> registerOreBlock(String name, int exp, float destroyTime, float explosionResistance){
        return registerBlock(
                name,
                p_368251_ -> new DropExperienceBlock(ConstantInt.of(exp), p_368251_),
                BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(destroyTime, explosionResistance)
        );
    }

    public static DeferredBlock<Block> registerAnvil(String name, MiteAnvilBlock.AnvilVariant anvilVariant,int maxDamage){
        DeferredBlock<Block> block =  BLOCKS.registerBlock(
                name,
                properties -> new MiteAnvilBlock(anvilVariant,properties),
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.METAL)
                        .requiresCorrectToolForDrops()
                        .strength(5.0F, 1200.0F)
                        .sound(SoundType.ANVIL)
                        .pushReaction(PushReaction.BLOCK)
        );
        ModItems.ITEMS.registerSimpleBlockItem(name, block,new Item.Properties().durability(maxDamage));
        return block;
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
