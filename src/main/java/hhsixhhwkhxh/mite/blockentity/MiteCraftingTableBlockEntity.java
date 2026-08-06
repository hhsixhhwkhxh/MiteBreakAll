package hhsixhhwkhxh.mite.blockentity;

import hhsixhhwkhxh.mite.menu.MiteCraftingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MiteCraftingTableBlockEntity extends BlockEntity implements MenuProvider {

    int craftTime = 0,craftTimeTotal = 10,isCrafting = 0,isResultLocked = 1;
    public static final int CRAFT_TIME = 0;
    public static final int CRAFT_TIME_TOTAL = 1;
    public static final int IS_CRAFTING = 2;
    public static final int IS_RESULT_LOCKED = 3;
    public AtomicReference<Supplier<Boolean>> onCraftFinishedSupplier = new AtomicReference<>();

    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case CRAFT_TIME -> craftTime;
                case CRAFT_TIME_TOTAL -> craftTimeTotal;
                case IS_CRAFTING -> isCrafting;
                case IS_RESULT_LOCKED -> isResultLocked;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case CRAFT_TIME:
                    craftTime = value;
                    break;
                case CRAFT_TIME_TOTAL:
                    craftTimeTotal = value;
                    break;
                case IS_CRAFTING:
                    isCrafting = value;
                    break;
                case IS_RESULT_LOCKED:
                    isResultLocked = value;
                    break;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public MiteCraftingTableBlockEntity( BlockPos pos, BlockState blockState){
        super(ModBlockEntities.MITE_CRAFTING_TABLE.get(), pos, blockState);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("craft_table.flint");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MiteCraftingMenu(containerId, playerInventory, ContainerLevelAccess.create(level, worldPosition),dataAccess,onCraftFinishedSupplier);
    }

    public boolean isCrafting(){
        return (dataAccess.get(IS_CRAFTING)!=0);
    }

    public static void serverTick(ServerLevel level, BlockPos pos, BlockState state, MiteCraftingTableBlockEntity craftTable) {
        if(!craftTable.isCrafting()){
            return;
        }
        //craftTable.craftTime++;
        craftTable.dataAccess.set(CRAFT_TIME,craftTable.dataAccess.get(CRAFT_TIME)+1);
        if(craftTable.dataAccess.get(CRAFT_TIME) < craftTable.dataAccess.get(CRAFT_TIME_TOTAL)){
            return;
        }
        //craftTable.dataAccess.set(IS_CRAFTING,0);
        craftTable.dataAccess.set(IS_RESULT_LOCKED,0);
        craftTable.dataAccess.set(CRAFT_TIME,0);

        if(craftTable.onCraftFinishedSupplier.get()!=null){
            craftTable.onCraftFinishedSupplier.get().get();
        }
    }
}
