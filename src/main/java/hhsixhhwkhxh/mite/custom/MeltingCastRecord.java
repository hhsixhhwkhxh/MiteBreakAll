package hhsixhhwkhxh.mite.custom;

import hhsixhhwkhxh.mite.item.ModItems;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;

public record MeltingCastRecord(Item outputItem, int meltingPoint, int baseMeltTick) {
    public static final HashMap<Item,MeltingCastRecord> MeltingCastMap= new HashMap<>();

    static {
        MeltingCastMap.put(ModItems.SILVER_NUGGET.get(), new MeltingCastRecord(ModItems.SILVER_INGOT.get(),962,120));
        MeltingCastMap.put(ModItems.COPPER_NUGGET.get(), new MeltingCastRecord(Items.COPPER_INGOT,1084,140));
        MeltingCastMap.put(Items.GOLD_NUGGET, new MeltingCastRecord(Items.GOLD_INGOT,1070,100));
        MeltingCastMap.put(Items.IRON_NUGGET, new MeltingCastRecord(Items.IRON_INGOT,1535,180));
        MeltingCastMap.put(ModItems.HARD_NUGGET.get(), new MeltingCastRecord(ModItems.HARD_INGOT.get(),3550,240));
        MeltingCastMap.put(ModItems.ANCIENT_METAL_NUGGET.get(), new MeltingCastRecord(ModItems.ANCIENT_METAL_INGOT.get(),3550,240));
        MeltingCastMap.put(ModItems.MITHRIL_NUGGET.get(), new MeltingCastRecord(ModItems.MITHRIL_INGOT.get(),4630,300));
        MeltingCastMap.put(ModItems.ADAMANTIUM_NUGGET.get(), new MeltingCastRecord(ModItems.ADAMANTIUM_INGOT.get(),6210,400));

    }

    public int getFinalMeltTicks(Block coreType, int coreQuantity){
        return getLevelBaseTicks(coreType) + getCoreModifierTicks(coreQuantity);
    }

    private int getLevelBaseTicks(Block furnaceType){
        float materialTick;
        if (furnaceType == Blocks.COBBLESTONE) {
            materialTick = (float)200 * 1.2F;
        } else if (furnaceType == Blocks.NETHERRACK) {
            materialTick = (float)200 / 1.2F;
        } else {
            materialTick = (float)200;
        }
        return Mth.ceil(materialTick);
    }

    private int getCoreModifierTicks(int coreQuantity) {
        float var2 = 1.0F + (float)Math.max(0, coreQuantity - 1) * 0.1F;
        return Mth.ceil(baseMeltTick / var2);
    }
}
