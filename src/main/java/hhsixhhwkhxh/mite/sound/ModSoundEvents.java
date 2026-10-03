package hhsixhhwkhxh.mite.sound;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MiteBreakAll.MOD_ID);

    public static final Supplier<SoundEvent> ELEPHANT_DEATH = SOUND_EVENTS.register(
            "elephant_death",
            SoundEvent::createVariableRangeEvent
    );

    public static final Supplier<SoundEvent> ELEPHANT_HURT = SOUND_EVENTS.register(
            "elephant_hurt",
            SoundEvent::createVariableRangeEvent
    );

    public static final Supplier<SoundEvent> ELEPHANT_IDLE = SOUND_EVENTS.register(
            "elephant_idle",
            SoundEvent::createVariableRangeEvent
    );
    public static final Supplier<SoundEvent> ELEPHANT_BABY_IDLE = SOUND_EVENTS.register(
            "elephant_baby_idle",
            SoundEvent::createVariableRangeEvent
    );

    public static final Supplier<SoundEvent> COUGAR_DEATH = SOUND_EVENTS.register(
            "cougar_death",
            SoundEvent::createVariableRangeEvent
    );

    public static final Supplier<SoundEvent> COUGAR_HURT = SOUND_EVENTS.register(
            "cougar_hurt",
            SoundEvent::createVariableRangeEvent
    );

    public static final Supplier<SoundEvent> COUGAR_ATTACK = SOUND_EVENTS.register(
            "cougar_attack",
            SoundEvent::createVariableRangeEvent
    );
    public static final Supplier<SoundEvent> COUGAR_AMBIENT = SOUND_EVENTS.register(
            "cougar_ambient",
            SoundEvent::createVariableRangeEvent
    );

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
