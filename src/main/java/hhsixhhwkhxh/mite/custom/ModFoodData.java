package hhsixhhwkhxh.mite.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.food.FoodConstants;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class ModFoodData extends FoodData {
    private int maxFoodLevel = 6;
    private int foodLevel = 6;
    private float saturationLevel = 5.0F;
    private float exhaustionLevel;
    private int tickTimer;

    private void add(int foodLevel, float saturationLevel) {
        this.foodLevel = Mth.clamp(foodLevel + this.foodLevel, 0, maxFoodLevel);
        this.saturationLevel = Mth.clamp(saturationLevel + this.saturationLevel, 0.0F, (float)this.foodLevel);
    }

    /**
     * Add food stats.
     */
    public void eat(int foodLevelModifier, float saturationLevelModifier) {
        this.add(foodLevelModifier, FoodConstants.saturationByModifier(foodLevelModifier, saturationLevelModifier));
    }

    public void eat(FoodProperties foodProperties) {
        this.add(foodProperties.nutrition(), foodProperties.saturation());
    }

    public void tick(ServerPlayer player) {
        ServerLevel serverlevel = player.level();
        Difficulty difficulty = serverlevel.getDifficulty();



        if (this.exhaustionLevel > 4.0F) {
            this.exhaustionLevel -= 4.0F;
            if (this.saturationLevel > 0.0F) {
                this.saturationLevel = Math.max(this.saturationLevel - 1.0F, 0.0F);
            } else if (difficulty != Difficulty.PEACEFUL) {
                this.foodLevel = Math.max(this.foodLevel - 1, 0);
            }
        }

        boolean flag = serverlevel.getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION);
        if (flag && this.saturationLevel > 0.0F && player.isHurt() && this.foodLevel >= maxFoodLevel) {
            this.tickTimer++;
            if (this.tickTimer >= 10) {
                float f = Math.min(this.saturationLevel, 6.0F);
                player.heal(f / 6.0F);
                this.addExhaustion(f);
                this.tickTimer = 0;
            }
        } else if (flag && this.foodLevel >= maxFoodLevel*0.9 && player.isHurt()) {
            this.tickTimer++;
            if (this.tickTimer >= 80) {
                player.heal(1.0F);
                this.addExhaustion(6.0F);
                this.tickTimer = 0;
            }
        } else if (this.foodLevel <= 0) {
            this.tickTimer++;
            if (this.tickTimer >= 80) {
                if (player.getHealth() > maxFoodLevel*0.5 || difficulty == Difficulty.HARD || player.getHealth() > 1.0F && difficulty == Difficulty.NORMAL) {
                    player.hurtServer(serverlevel, player.damageSources().starve(), 1.0F);
                }

                this.tickTimer = 0;
            }
        } else {
            this.tickTimer = 0;
        }
    }

    public void readAdditionalSaveData(ValueInput input) {
        this.maxFoodLevel = input.getIntOr("maxFoodLevel",6);
        this.foodLevel = input.getIntOr("foodLevel", maxFoodLevel);
        this.tickTimer = input.getIntOr("foodTickTimer", 0);
        this.saturationLevel = input.getFloatOr("foodSaturationLevel", 5.0F);
        this.exhaustionLevel = input.getFloatOr("foodExhaustionLevel", 0.0F);
    }

    public void addAdditionalSaveData(ValueOutput output) {
        output.putInt("foodLevel", this.foodLevel);
        output.putInt("foodTickTimer", this.tickTimer);
        output.putFloat("foodSaturationLevel", this.saturationLevel);
        output.putFloat("foodExhaustionLevel", this.exhaustionLevel);
        output.putInt("maxFoodLevel",maxFoodLevel);
    }

    public int getFoodLevel() {
        return this.foodLevel;
    }

    public int getMaxFoodLevel() {
        return this.maxFoodLevel;
    }

    public void setMaxFoodLevel(int maxFoodLevel) {
        this.maxFoodLevel = maxFoodLevel;
    }

    public boolean needsFood() {
        return this.foodLevel < maxFoodLevel;
    }

    /**
     * Adds input to {@code foodExhaustionLevel} to a max of 40.
     */
    public void addExhaustion(float exhaustion) {
        this.exhaustionLevel = Math.min(this.exhaustionLevel + exhaustion, 40.0F);
    }

    public float getSaturationLevel() {
        return this.saturationLevel;
    }

    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    public void setSaturation(float saturationLevel) {
        this.saturationLevel = saturationLevel;
    }
}
