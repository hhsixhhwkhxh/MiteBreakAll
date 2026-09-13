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

public class MiteCraftingTableBlockEntity extends BlockEntity implements MenuProvider {

    private int craftingTimer = 0, craftTotalTime = 0;
    public static final int CRAFTING_TIMER = 0;
    public static final int CRAFTING_TOTAL_TIME = 1;
    public Runnable onCraftFinishedListener = null;

    private final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case CRAFTING_TIMER -> craftingTimer;
                case CRAFTING_TOTAL_TIME -> craftTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case CRAFTING_TIMER:
                    craftingTimer = value;
                    break;
                case CRAFTING_TOTAL_TIME:
                    craftTotalTime = value;
                    break;
            }
        }

        @Override
        public int getCount() {
            return 2;
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
        return new MiteCraftingMenu(containerId, playerInventory, ContainerLevelAccess.create(level, worldPosition),dataAccess, this);
    }


    public static void serverTick(ServerLevel level, BlockPos pos, BlockState state, MiteCraftingTableBlockEntity craftTable) {
        int craftingTotalTime = craftTable.getCraftTotalTime();
        if(craftingTotalTime<=0){
            return;
        }

        int craftingTimer = craftTable.getCraftingTimer();
        craftingTimer++;
        craftTable.setCraftingTimer(craftingTimer);

        if(craftingTimer < craftingTotalTime){
            return;
        }

        //结算
        if(craftTable.onCraftFinishedListener != null){
            craftTable.onCraftFinishedListener.run();
        }
    }

    public void setOnCraftFinishedListener(Runnable listener){
        this.onCraftFinishedListener = listener;
    }

    public int getCraftingTimer(){
        return dataAccess.get(CRAFTING_TIMER);
    }

    public void setCraftingTimer(int value){
        dataAccess.set(CRAFTING_TIMER, value);
    }

    public int getCraftTotalTime(){
        return dataAccess.get(CRAFTING_TOTAL_TIME);
    }
}
