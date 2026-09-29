package hhsixhhwkhxh.mite.entity;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.createEntities(MiteBreakAll.MOD_ID);

    public static final Supplier<EntityType<BrownBear>> BROWN_BEAR = ENTITY_TYPES.register("brown_bear",()->EntityType.Builder.of(BrownBear::new, MobCategory.CREATURE)
            .sized(1.4F, 1.4F).clientTrackingRange(10)
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID, "brown_bear")
    )));

    public static final Supplier<EntityType<Elephant>> ELEPHANT = ENTITY_TYPES.register("elephant",()->EntityType.Builder.of(Elephant::new, MobCategory.CREATURE)
            .sized(2F, 2.8F).clientTrackingRange(10)
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID, "elephant")
    )));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
