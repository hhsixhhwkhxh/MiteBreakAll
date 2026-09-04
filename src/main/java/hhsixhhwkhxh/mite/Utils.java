package hhsixhhwkhxh.mite;

import hhsixhhwkhxh.mite.accessor.PlayerMixinAccessor;
import hhsixhhwkhxh.mite.custom.ModFoodData;
import hhsixhhwkhxh.mite.custom.PlayerWaterData;
import hhsixhhwkhxh.mite.packet.ClientboundSetVitalStatMaxValuePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public final class Utils {
    public static int getVitalStatMaxValue(int level){
        return Mth.clamp((level/5)*2+6,6,20);
    }

    public static void setVitalStatMaxValue(Player player, int value){
        PlayerWaterData waterData = ((PlayerMixinAccessor) player).getWaterData();
        waterData.setMaxWaterLevel(value);

        ModFoodData modFoodData = (ModFoodData) player.getFoodData();
        modFoodData.setMaxFoodLevel(value);

        AttributeInstance maxHealthAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttr != null) {
            maxHealthAttr.setBaseValue(value);
        }
    }

    //server专属
    public static void updateVitalStat(ServerPlayer player){
        int vitalStatMaxValue = Utils.getVitalStatMaxValue(player.experienceLevel);

        Utils.setVitalStatMaxValue(player,vitalStatMaxValue);
        PacketDistributor.sendToPlayer( player, new ClientboundSetVitalStatMaxValuePacket(vitalStatMaxValue));
    }

    public static Optional<BlockPos> loadBlockPos(ValueInput input, String name, BlockPos basePos){
        Optional<Integer> xOpt = input.getInt(name+"_pos_x");
        Optional<Integer> yOpt = input.getInt(name+"_pos_y");
        Optional<Integer> zOpt = input.getInt(name+"_pos_z");

        if(xOpt.isEmpty()|| yOpt.isEmpty()|| zOpt.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(getAbsolutePos(basePos,new BlockPos(xOpt.get(),yOpt.get(),zOpt.get())));
    }

    public static void saveBlockPos(ValueOutput output, String name, BlockPos basePos, @Nullable BlockPos pos){
        if(pos == null){
            return;
        }
        pos = getRelativePos(basePos,pos);
        output.putInt(name+"_pos_x", pos.getX());
        output.putInt(name+"_pos_y", pos.getY());
        output.putInt(name+"_pos_z", pos.getZ());
    }

    public static List<IntegerProperty> createBlockPosProperty(String name){
        return List.of(
                IntegerProperty.create(name + "_pos_x", 0,4),
                IntegerProperty.create(name + "_pos_y", 0,4),
                IntegerProperty.create(name + "_pos_z", 0,4)
        );
    }

    public static BlockPos getAbsolutePosFromBlockState(BlockPos basePos, BlockState blockState, List<IntegerProperty> list){
        return basePos.offset(
                blockState.getValue(list.getFirst())-2,
                blockState.getValue(list.get(1))-2,
                blockState.getValue(list.getLast())-2
        );
    }

    public static BlockPos getRelativePos(BlockPos basePos, BlockPos targetPos){
        return new BlockPos(
                targetPos.getX() - basePos.getX(),
                targetPos.getY() - basePos.getY(),
                targetPos.getZ() - basePos.getZ()
        );
    }

    public static BlockPos getAbsolutePos(BlockPos basePos, BlockPos offset){
        return basePos.offset(
                offset.getX(),
                offset.getY(),
                offset.getZ()
        );
    }

    public static BlockState setPropertyBlockPos(BlockState blockState, List<IntegerProperty> properties, BlockPos offset){
        return blockState
                .setValue(properties.get(0), offset.getX()+2)
                .setValue(properties.get(1),offset.getY()+2)
                .setValue(properties.get(2),offset.getZ()+2);
    }
}
