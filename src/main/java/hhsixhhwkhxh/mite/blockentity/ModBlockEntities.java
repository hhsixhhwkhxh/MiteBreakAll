package hhsixhhwkhxh.mite.blockentity;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MiteBreakAll.MODID);

    public static final Supplier<BlockEntityType<MiteCraftingTableBlockEntity>> MITE_CRAFTING_TABLE = BLOCK_ENTITIES.register("mite_crafting_table",()->{return new BlockEntityType<>(MiteCraftingTableBlockEntity::new,false, ModBlocks.FLINT_CRAFTING_TABLE.get());});

    public static void register(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }
}
