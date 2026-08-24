package hhsixhhwkhxh.mite;

import hhsixhhwkhxh.mite.accessor.PlayerMixinAccessor;
import hhsixhhwkhxh.mite.custom.ModFoodData;
import hhsixhhwkhxh.mite.custom.PlayerWaterData;
import hhsixhhwkhxh.mite.packet.ClientboundSetVitalStatMaxValuePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

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
}
