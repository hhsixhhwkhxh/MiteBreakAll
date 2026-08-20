package hhsixhhwkhxh.mite.custom;

import hhsixhhwkhxh.mite.packet.ClientboundSetWaterLevelPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.network.PacketDistributor;

public class PlayerWaterData {
    private int waterLevel = 20;
    private float exhaustionLevel;
    private int tickTimer;
    private boolean shouldUpdate = true;

    public void addWaterLevel(int waterLevel) {
        this.waterLevel = Mth.clamp(waterLevel + this.waterLevel, 0, 20);
    }

    public void readAdditionalSaveData(ValueInput input) {
        this.waterLevel = input.getIntOr("waterLevel", 20);
        this.tickTimer = input.getIntOr("waterTickTimer", 0);
        this.exhaustionLevel = input.getFloatOr("waterExhaustionLevel", 0.0F);

    }

    public void addAdditionalSaveData(ValueOutput output) {
        output.putInt("waterLevel", this.waterLevel);
        output.putInt("waterTickTimer", this.tickTimer);
        output.putFloat("waterExhaustionLevel", this.exhaustionLevel);
    }

    public int getWaterLevel() {
        return this.waterLevel;
    }

    public boolean needsWater() {
        return this.waterLevel < 20;
    }

    public void addExhaustion(float exhaustion) {
        this.exhaustionLevel = Math.min(this.exhaustionLevel + exhaustion, 40.0F);
    }


    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }


    public void tick(ServerPlayer player){
        ServerLevel serverlevel = player.level();
        Difficulty difficulty = serverlevel.getDifficulty();
        if (this.exhaustionLevel > 4.0F) {
            this.exhaustionLevel -= 4.0F;
            if (difficulty != Difficulty.PEACEFUL) {
                this.waterLevel = Math.max(this.waterLevel - 1, 0);
                shouldUpdate = true;
            }
        }


        if (this.waterLevel >= 18 && player.isHurt()) {
            this.tickTimer++;
            if (this.tickTimer >= 80) {
                this.addExhaustion(6.0F);
                this.tickTimer = 0;
            }
        } else if (this.waterLevel <= 0) {
            this.tickTimer++;
            if (this.tickTimer >= 80) {
                player.hurtServer(serverlevel, player.damageSources().starve(), 1.0F);
                this.tickTimer = 0;
            }
        } else {
            this.tickTimer = 0;
        }

        if(shouldUpdate){
            shouldUpdate = false;
            PacketDistributor.sendToPlayer(player, new ClientboundSetWaterLevelPacket(waterLevel));
        }
    }

}
