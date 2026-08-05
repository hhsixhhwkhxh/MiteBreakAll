package hhsixhhwkhxh.mite.mixin;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin extends AbstractContainerMenu {

    @Final
    @Shadow
    private Container enchantSlots;
    @Shadow
    public final int[] costs = new int[3];
    @Shadow
    public final int[] enchantClue = new int[]{-1, -1, -1};
    @Shadow
    public final int[] levelClue = new int[]{-1, -1, -1};

    @Shadow
    private final ContainerLevelAccess access;

    protected EnchantmentMenuMixin(@Nullable MenuType<?> menuType, int containerId, ContainerLevelAccess access) {
        super(menuType, containerId);
        this.access = access;
    }

    @Inject(method = "slotsChanged", at = @At("HEAD"), cancellable = true)
    public void slotsChanged(Container inventory, CallbackInfo ci) {
        if (inventory != this.enchantSlots) {
            return;
        }
        ItemStack itemstack = inventory.getItem(0);
        if (itemstack.getItem() != Items.GOLDEN_APPLE) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            costs[i] = 200;
            enchantClue[i] = 1;
            levelClue[i] = 1;
        }

        ci.cancel();
    }

    @Inject(method = "clickMenuButton", at = @At(value = "INVOKE",target = "Lnet/minecraft/world/inventory/ContainerLevelAccess;execute(Ljava/util/function/BiConsumer;)V"), cancellable = true)
    public void clickMenuButton(Player player, int id, CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemstack = this.enchantSlots.getItem(0);
        if(itemstack.getItem() != Items.GOLDEN_APPLE){
            return;
        }
        cir.setReturnValue(true);

        this.access
                .execute(
                        (p_347276_, p_347277_) -> {
                            ItemStack itemstack2 = itemstack;
                            ItemStack itemstack1 = this.enchantSlots.getItem(1);
                            //ItemStack itemstack = this.enchantSlots.getItem(0);
                            //List<EnchantmentInstance> list = this.getEnchantmentList(p_347276_.registryAccess(), itemstack, id, this.costs[id]);

                                player.onEnchantmentPerformed(itemstack, 0);
                                // Neo: Allow items to transform themselves when enchanted, instead of relying on hardcoded transformations for Items.BOOK
                                //itemstack2 = itemstack.getItem().applyEnchantments(itemstack, list);
                                itemstack2 = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE,itemstack.getCount());
                                this.enchantSlots.setItem(0, itemstack2);
                                //net.neoforged.neoforge.common.CommonHooks.onPlayerEnchantItem(player, itemstack2, list);

                                itemstack1.consume(1, player);
                                //if (itemstack1.isEmpty()) {
                                    //this.enchantSlots.setItem(1, ItemStack.EMPTY);
                                //}

                                player.awardStat(Stats.ENCHANT_ITEM);
                                if (player instanceof ServerPlayer) {
                                    CriteriaTriggers.ENCHANTED_ITEM.trigger((ServerPlayer)player, itemstack2, 0);
                                }

                                this.enchantSlots.setChanged();
                                //this.enchantmentSeed.set(player.getEnchantmentSeed());
                                this.slotsChanged(this.enchantSlots);
                                p_347276_.playSound(
                                        null, p_347277_, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0F, p_347276_.random.nextFloat() * 0.1F + 0.9F
                                );

                        }
                );
    }


}