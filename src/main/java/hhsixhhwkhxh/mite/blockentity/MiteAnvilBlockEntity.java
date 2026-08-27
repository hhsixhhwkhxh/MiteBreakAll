package hhsixhhwkhxh.mite.blockentity;

import hhsixhhwkhxh.mite.block.MiteAnvilBlock;
import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.menu.MiteAnvilMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Clearable;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.registries.DeferredBlock;

public class MiteAnvilBlockEntity extends BlockEntity implements Clearable, MenuProvider {
    private int durability = -1,maxDamage,unbreakable = 0;
    private final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index){
                case DURABILITY -> durability;
                case MAX_DAMAGE -> maxDamage;
                case UNBREAKABLE -> unbreakable;
                default -> throw new IllegalStateException("Unexpected index: " + index);
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index){
                case DURABILITY -> durability=value;
                case MAX_DAMAGE -> maxDamage=value;
                case UNBREAKABLE -> unbreakable=value;
                default -> throw new IllegalStateException("Unexpected index: " + index);
            };
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    public static final int DURABILITY = 0;
    public static final int MAX_DAMAGE = 1;
    public static final int UNBREAKABLE = 2;

    public MiteAnvilBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.MITE_ANVIL.get(), pos, blockState);
    }



    @Override
    public void onLoad() {
        super.onLoad();

        if(level==null){
            return;
        }

        DeferredBlock<Block> block;
        MiteAnvilBlock.AnvilVariant anvilVariant = level.getBlockState(worldPosition).getValue(MiteAnvilBlock.ANVIL_VARIANT);
        switch (anvilVariant){
            case ADAMANTIUM -> {
                block = ModBlocks.ADAMANTIUM_ANVIL;
                dataAccess.set(UNBREAKABLE,1);
            }
            case ANCIENT_METAL -> block = ModBlocks.ANCIENT_METAL_ANVIL;
            case COPPER -> block = ModBlocks.COPPER_ANVIL;
            case GOLD -> block = ModBlocks.GOLD_ANVIL;
            case HARD -> block = ModBlocks.HARD_ANVIL;
            case IRON -> block = ModBlocks.IRON_ANVIL;
            case MITHRIL -> block = ModBlocks.MITHRIL_ANVIL;
            case SILVER -> block = ModBlocks.SILVER_ANVIL;
            default -> throw new IllegalStateException("Unexpected anvilVariant: "+anvilVariant);
        }

        int maxDamage = new ItemStack(block.asItem()).getMaxDamage();
        dataAccess.set(MAX_DAMAGE,maxDamage);

        if(dataAccess.get(DURABILITY)<0){
            dataAccess.set(DURABILITY,maxDamage);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        dataAccess.set(DURABILITY,input.getIntOr("durability",-1));
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("durability",dataAccess.get(DURABILITY));
    }

    @Override
    public void clearContent() {

    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        super.preRemoveSideEffects(pos,state);
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MiteAnvilMenu(containerId, playerInventory, ContainerLevelAccess.create(level, worldPosition),dataAccess);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.repair");
    }


    public ContainerData getDataAccess(){
        return dataAccess;
    }

}
