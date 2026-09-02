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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.Optional;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

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

    public static Optional<BlockPos> loadBlockPos(ValueInput input,String name){
        Optional<Integer> xOpt = input.getInt(name+"_pos_x");
        Optional<Integer> yOpt = input.getInt(name+"_pos_y");
        Optional<Integer> zOpt = input.getInt(name+"_pos_z");

        if(xOpt.isEmpty()|| yOpt.isEmpty()|| zOpt.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(new BlockPos(xOpt.get(),yOpt.get(),zOpt.get()));
    }

    public static void saveBlockPos(ValueOutput output, String name,@Nullable BlockPos pos){
        if(pos == null){
            return;
        }
        output.putInt(name+"_pos_x", pos.getX());
        output.putInt(name+"_pos_y", pos.getY());
        output.putInt(name+"_pos_z", pos.getZ());
    }
}
