package hhsixhhwkhxh.mite.datacomponent;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModDataComponents {


    public static final DeferredRegister.DataComponents DATACOMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MiteBreakAll.MODID);

    public static final Supplier<DataComponentType<Moisture>> MOISTURE = DATACOMPONENTS.registerComponentType(
            "moisture",
            builder -> builder
                    // 磁盘读写的编解码器
                    .persistent(Moisture.CODEC)
                    // 网络传输的流编解码器
                    .networkSynchronized(Moisture.STREAM_CODEC)
    );

    public static final Supplier<DataComponentType<MaterialLevel>> MATERIAL_LEVEL = DATACOMPONENTS.registerComponentType(
            "material_level",
            builder -> builder
                    .persistent(MaterialLevel.CODEC)
                    .networkSynchronized(MaterialLevel.STREAM_CODEC)
    );

    public static final Supplier<DataComponentType<ReachBonus>> REACH_BONUS = DATACOMPONENTS.registerComponentType(
            "reach_bonus",
            builder -> builder
                    .persistent(ReachBonus.CODEC)
                    .networkSynchronized(ReachBonus.STREAM_CODEC)
    );


    public static void register(IEventBus bus){
        DATACOMPONENTS.register(bus);
    }
}
