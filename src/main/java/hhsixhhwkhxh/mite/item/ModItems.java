package hhsixhhwkhxh.mite.item;

import hhsixhhwkhxh.mite.MiteBreakAll;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.enchantment.Enchantable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

import static net.minecraft.world.item.component.Consumables.defaultFood;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MiteBreakAll.MODID);
    //public static final DeferredItem<Item> WILD_APPLE = ITEMS.register("wild_apple",()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> WILD_APPLE = ITEMS.registerItem("wild_apple", Item::new,new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    //public static final DeferredItem<Item> SILVER_ORE = ITEMS.registerItem("silver_ore", Item::new,new Item.Properties());


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);//ENCHANTED_GOLDEN_APPLE

        modifyGoldenApple(eventBus);
    }


    public static void modifyGoldenApple(IEventBus eventBus){
        Consumable GAConsumable = defaultFood()
                .onConsume(
                        new ApplyStatusEffectsConsumeEffect(
                                List.of(
                                        new MobEffectInstance(MobEffects.REGENERATION, 1200, 0)
                                )
                        )
                )
                .build();
        Consumable EGAConsumable = defaultFood()
                .onConsume(
                        new ApplyStatusEffectsConsumeEffect(
                                List.of(
                                        new MobEffectInstance(MobEffects.REGENERATION, 1200, 1),
                                        new MobEffectInstance(MobEffects.RESISTANCE, 1200, 0),
                                        new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0)
                                )
                        )
                )
                .build();
        eventBus.addListener((ModifyDefaultComponentsEvent event)->{
            event.modify(Items.GOLDEN_APPLE,(builder)->{
                builder.set(DataComponents.CONSUMABLE,GAConsumable);
                builder.set(DataComponents.ENCHANTABLE,new Enchantable(60));
            });
            event.modify(Items.ENCHANTED_GOLDEN_APPLE,(builder)->{
                builder.set(DataComponents.CONSUMABLE,EGAConsumable);
            });
        });
    }
}
