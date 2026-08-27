package hhsixhhwkhxh.mite.menu;

import com.mojang.logging.LogUtils;
import hhsixhhwkhxh.mite.block.MiteAnvilBlock;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;

import javax.annotation.Nullable;

public class MiteAnvilMenu extends ItemCombinerMenu {
    public static final int INPUT_SLOT = 0;
    public static final int ADDITIONAL_SLOT = 1;
    public static final int RESULT_SLOT = 2;
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final int MAX_NAME_LENGTH = 50;
    public int repairItemCountCost;
    @Nullable
    private String itemName;
    private final DataSlot cost = DataSlot.standalone();
    private boolean onlyRenaming;
    private static final int COST_FAIL = 0;
    private static final int COST_BASE = 1;
    private static final int COST_ADDED_BASE = 1;
    private static final int COST_REPAIR_MATERIAL = 1;
    private static final int COST_REPAIR_SACRIFICE = 2;
    private static final int COST_INCOMPATIBLE_PENALTY = 1;
    private static final int COST_RENAME = 1;
    private static final int INPUT_SLOT_X_PLACEMENT = 27;
    private static final int ADDITIONAL_SLOT_X_PLACEMENT = 76;
    private static final int RESULT_SLOT_X_PLACEMENT = 134;
    private static final int SLOT_Y_PLACEMENT = 47;

    private final ContainerData dataAccess;

    public MiteAnvilMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL,new SimpleContainerData(3));
    }

    public MiteAnvilMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access,ContainerData dataAccess) {
        super(ModMenuTypes.MITE_ANVIL_MENU.get(), containerId, playerInventory, access, createInputSlotDefinitions());
        this.addDataSlot(this.cost);
        this.addDataSlots(dataAccess);
        this.dataAccess = dataAccess;
    }

    private static ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
            .withSlot(INPUT_SLOT, INPUT_SLOT_X_PLACEMENT, SLOT_Y_PLACEMENT, p_266635_ -> true)
            .withSlot(ADDITIONAL_SLOT, ADDITIONAL_SLOT_X_PLACEMENT, SLOT_Y_PLACEMENT, p_266634_ -> true)
            .withResultSlot(RESULT_SLOT, RESULT_SLOT_X_PLACEMENT, SLOT_Y_PLACEMENT)
            .build();
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return true;
    }

    @Override
    protected boolean mayPickup(Player player, boolean hasStack) {
        return (player.hasInfiniteMaterials() || cost.get() > 0);
    }

    public ContainerData getDataAccess(){
        return dataAccess;
    }

    @Override
    protected void onTake(Player player, ItemStack stack) {

        //if (!player.hasInfiniteMaterials()) {
        //    player.giveExperienceLevels(-this.cost.get());
        //}
        int damage = cost.get();

        if (this.repairItemCountCost > 0) {
            ItemStack itemstack = this.inputSlots.getItem(ADDITIONAL_SLOT);
            if (!itemstack.isEmpty() && itemstack.getCount() > this.repairItemCountCost) {
                itemstack.shrink(this.repairItemCountCost);
                this.inputSlots.setItem(ADDITIONAL_SLOT, itemstack);
            } else {
                this.inputSlots.setItem(ADDITIONAL_SLOT, ItemStack.EMPTY);
            }
        } else if (!this.onlyRenaming) {
            this.inputSlots.setItem(ADDITIONAL_SLOT, ItemStack.EMPTY);
        }


        //ItemStack itemstack = this.inputSlots.getItem(1);
        //this.inputSlots.setItem(1, itemstack);


        if (player instanceof ServerPlayer serverplayer
            && !StringUtil.isBlank(this.itemName)
            && !this.inputSlots.getItem(INPUT_SLOT).getHoverName().getString().equals(this.itemName)) {
            serverplayer.getTextFilter().processStreamMessage(this.itemName);
        }




        this.inputSlots.setItem(INPUT_SLOT, ItemStack.EMPTY);


        this.access.execute((worldLevel, blockPos) -> {
            BlockState blockstate = worldLevel.getBlockState(blockPos);
            //if (!player.hasInfiniteMaterials()) {
                BlockState blockstate1 = MiteAnvilBlock.damage(blockstate,dataAccess,damage);
                if (blockstate1 == null) {
                    worldLevel.removeBlock(blockPos, false);
                    worldLevel.levelEvent(1029, blockPos, 0);
                } else {
                    worldLevel.setBlock(blockPos, blockstate1, 2);
                    worldLevel.levelEvent(1030, blockPos, 0);
                }
            //} else {
            //    worldLevel.levelEvent(1030, blockPos, 0);
            //}
        });

        this.cost.set(0);
        }

    @Override
    public final void createResult() {
        // Neo: Override the real createResult() method to invoke the vanilla logic, and then call the event hook.
        // Since the vanilla createResult() has multiple returns, we need to wrap it. The choices are this, or pack it in a lambda.
        this.createResultInternal();

    }

    /**
     * Neo: This is the original createResult() method, which is called by the above method ({@link #createResult()}.
     * <p>
     * If you would normally override {@link #createResult()}, you should override this method instead.
     *
     * @implNote This replacement exists so we can fire the AnvilUpdateEvent with the vanilla result, despite the multiple returns in the original method.
     */
    protected void createResultInternal() {
        ItemStack inputLeftStack = this.inputSlots.getItem(INPUT_SLOT);
        this.onlyRenaming = false;
        this.cost.set(COST_BASE);
        int totalXpCost = 0;
        long combinedRepairCostSum = 0L;
        int renameXpCost = 0;

        if (!inputLeftStack.isEmpty() && EnchantmentHelper.canStoreEnchantments(inputLeftStack)) {
            ItemStack outputStack = inputLeftStack.copy();
            ItemStack inputRightStack = this.inputSlots.getItem(ADDITIONAL_SLOT);
            ItemEnchantments.Mutable itemenchantments$mutable = new ItemEnchantments.Mutable(EnchantmentHelper.getEnchantmentsForCrafting(outputStack));
            combinedRepairCostSum += (long) inputLeftStack.getOrDefault(DataComponents.REPAIR_COST, 0).intValue() + inputRightStack.getOrDefault(DataComponents.REPAIR_COST, 0).intValue();
            this.repairItemCountCost = 0;
            if (!inputRightStack.isEmpty()) {
                boolean rightStackIsEnchantedBook = inputRightStack.has(DataComponents.STORED_ENCHANTMENTS);

                //材料修补
                if (outputStack.isDamageableItem() && inputLeftStack.isValidRepairItem(inputRightStack)) {
                    int repairPerMaterial = Math.min(outputStack.getDamageValue(), outputStack.getMaxDamage() / 4);
                    if (repairPerMaterial <= 0) {
                        this.resultSlots.setItem(INPUT_SLOT, ItemStack.EMPTY);
                        this.cost.set(COST_FAIL);
                        return;
                    }

                    int usedMaterialCount;
                    for (usedMaterialCount = 0; repairPerMaterial > 0 && usedMaterialCount < inputRightStack.getCount(); usedMaterialCount++) {
                        int k3 = outputStack.getDamageValue() - repairPerMaterial;
                        outputStack.setDamageValue(k3);
                        totalXpCost++;
                        repairPerMaterial = Math.min(outputStack.getDamageValue(), outputStack.getMaxDamage() / 4);
                    }

                    this.repairItemCountCost = usedMaterialCount;
                } else {
                    if (!rightStackIsEnchantedBook && (!outputStack.is(inputRightStack.getItem()) || !outputStack.isDamageableItem())) {
                        this.resultSlots.setItem(INPUT_SLOT, ItemStack.EMPTY);
                        this.cost.set(COST_FAIL);
                        return;
                    }

                    //合并
                    if (outputStack.isDamageableItem() && !rightStackIsEnchantedBook) {
                        int leftRemainingDurability = inputLeftStack.getMaxDamage() - inputLeftStack.getDamageValue();
                        int rightRemainingDurability = inputRightStack.getMaxDamage() - inputRightStack.getDamageValue();
                        int bonusDurability = rightRemainingDurability + outputStack.getMaxDamage() * 12 / 100;
                        int totalDurabilityResult = leftRemainingDurability + bonusDurability;
                        int finalDamageValue = outputStack.getMaxDamage() - totalDurabilityResult;
                        if (finalDamageValue < 0) {
                            finalDamageValue = 0;
                        }

                        if (finalDamageValue < outputStack.getDamageValue()) {
                            outputStack.setDamageValue(finalDamageValue);
                            totalXpCost += 2;
                        }
                    }

                    ItemEnchantments itemenchantments = EnchantmentHelper.getEnchantmentsForCrafting(inputRightStack);
                    boolean hasAnyValidEnchantMerge = false;
                    boolean hasIncompatibleEnchantFound = false;

                    for (Entry<Holder<Enchantment>> entry : itemenchantments.entrySet()) {
                        Holder<Enchantment> holder = entry.getKey();
                        int currentOutputLevel = itemenchantments$mutable.getLevel(holder);
                        int incomingEnchantLevel = entry.getIntValue();
                        incomingEnchantLevel = currentOutputLevel == incomingEnchantLevel ? incomingEnchantLevel + 1 : Math.max(incomingEnchantLevel, currentOutputLevel);
                        Enchantment enchantment = holder.value();
                        // Neo: Respect IItemExtension#supportsEnchantment - we also delegate the logic for Enchanted Books to this method.
                        // Though we still allow creative players to combine any item with any enchantment in the anvil here.
                        boolean canApplyEnchant = inputLeftStack.supportsEnchantment(holder);
                        if (this.player.getAbilities().instabuild) {
                            canApplyEnchant = true;
                        }

                        for (Holder<Enchantment> holder1 : itemenchantments$mutable.keySet()) {
                            if (!holder1.equals(holder) && !Enchantment.areCompatible(holder, holder1)) {
                                canApplyEnchant = false;
                                totalXpCost++;
                            }
                        }

                        if (!canApplyEnchant) {
                            hasIncompatibleEnchantFound = true;
                        } else {
                            hasAnyValidEnchantMerge = true;
                            if (incomingEnchantLevel > enchantment.getMaxLevel()) {
                                incomingEnchantLevel = enchantment.getMaxLevel();
                            }

                            itemenchantments$mutable.set(holder, incomingEnchantLevel);
                            int enchantXpCost = enchantment.getAnvilCost();
                            if (rightStackIsEnchantedBook) {
                                enchantXpCost = Math.max(1, enchantXpCost / 2);
                            }

                            totalXpCost += enchantXpCost * incomingEnchantLevel;
                            if (inputLeftStack.getCount() > 1) {
                                //totalXpCost = 40;
                                this.cost.set(COST_FAIL);
                                this.resultSlots.setItem(INPUT_SLOT, ItemStack.EMPTY);
                                return;
                            }
                        }
                    }

                    if (hasIncompatibleEnchantFound && !hasAnyValidEnchantMerge) {
                        this.resultSlots.setItem(INPUT_SLOT, ItemStack.EMPTY);
                        this.cost.set(COST_FAIL);
                        return;
                    }
                }
            }

            if (this.itemName != null && !StringUtil.isBlank(this.itemName)) {
                if (!this.itemName.equals(inputLeftStack.getHoverName().getString())) {
                    renameXpCost = COST_RENAME;
                    totalXpCost += renameXpCost;
                    outputStack.set(DataComponents.CUSTOM_NAME, Component.literal(this.itemName));
                }
            } else if (inputLeftStack.has(DataComponents.CUSTOM_NAME)) {
                renameXpCost = COST_RENAME;
                totalXpCost += renameXpCost;
                outputStack.remove(DataComponents.CUSTOM_NAME);
            }


            int finalLevelCost = totalXpCost <= 0 ? 0 : (int) Mth.clamp(combinedRepairCostSum + totalXpCost, 0L, 2147483647L);
            this.cost.set(finalLevelCost);
            if (totalXpCost <= 0) {
                outputStack = ItemStack.EMPTY;
            }

            if (renameXpCost == totalXpCost && renameXpCost > 0) {
                //if (this.cost.get() >= 40) {
                //    this.cost.set(39);
                //}

                this.onlyRenaming = true;
            }

            //if (this.cost.get() >= 40 && !this.player.hasInfiniteMaterials()) {
            //    outputStack = ItemStack.EMPTY;
            //}


            if (!outputStack.isEmpty()) {
                int i3 = outputStack.getOrDefault(DataComponents.REPAIR_COST, 0);
                if (i3 < inputRightStack.getOrDefault(DataComponents.REPAIR_COST, 0)) {
                    i3 = inputRightStack.getOrDefault(DataComponents.REPAIR_COST, 0);
                }

                if (renameXpCost != totalXpCost || renameXpCost == 0) {
                    i3 = calculateIncreasedRepairCost(i3);
                }

                outputStack.set(DataComponents.REPAIR_COST, i3);
                EnchantmentHelper.setEnchantments(outputStack, itemenchantments$mutable.toImmutable());
            }

            this.resultSlots.setItem(INPUT_SLOT, outputStack);
            this.broadcastChanges();
        } else {
            this.resultSlots.setItem(INPUT_SLOT, ItemStack.EMPTY);
            this.cost.set(COST_FAIL);
        }
    }

    public static int calculateIncreasedRepairCost(int oldRepairCost) {
        return (int)Math.min((long)oldRepairCost * 2L + 1L, 2147483647L);
    }

    public boolean setItemName(String itemName) {
        String s = validateName(itemName);
        if (s != null && !s.equals(this.itemName)) {
            this.itemName = s;
            if (this.getSlot(RESULT_SLOT).hasItem()) {
                ItemStack itemstack = this.getSlot(RESULT_SLOT).getItem();
                if (StringUtil.isBlank(s)) {
                    itemstack.remove(DataComponents.CUSTOM_NAME);
                } else {
                    itemstack.set(DataComponents.CUSTOM_NAME, Component.literal(s));
                }
            }

            this.createResult();
            return true;
        } else {
            return false;
        }
    }

    @Nullable
    private static String validateName(String itemName) {
        String s = StringUtil.filterText(itemName);
        return s.length() <= MAX_NAME_LENGTH ? s : null;
    }

    public int getCost() {
        return cost.get();
    }


}
