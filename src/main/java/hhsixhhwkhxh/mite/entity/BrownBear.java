package hhsixhhwkhxh.mite.entity;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class BrownBear extends PolarBear {
    public BrownBear(EntityType<? extends PolarBear> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BrownBearMeleeAttackGoal());
        this.goalSelector
                .addGoal(1, new PanicGoal(this, 2.0, p_425376_ -> p_425376_.isBaby() ? DamageTypeTags.PANIC_CAUSES : DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new BrownBear.BrownBearHurtByTargetGoal());
        this.targetSelector.addGoal(2, new BrownBear.BrownBearAttackPlayersGoal());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, null));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Fox.class, 10, true, true, null));
        this.targetSelector.addGoal(5, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    class BrownBearAttackPlayersGoal extends NearestAttackableTargetGoal<Player> {
        public BrownBearAttackPlayersGoal() {
            super(BrownBear.this, Player.class, 20, true, true, null);
        }

        @Override
        public boolean canUse() {
            if (BrownBear.this.isBaby()) {
                return false;
            } else {
                if (super.canUse()) {
                    for (BrownBear brownBear : BrownBear.this.level()
                            .getEntitiesOfClass(BrownBear.class, BrownBear.this.getBoundingBox().inflate(8.0, 4.0, 8.0))) {
                        if (brownBear.isBaby()) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        @Override
        protected double getFollowDistance() {
            return super.getFollowDistance() * 0.5;
        }
    }

    class BrownBearHurtByTargetGoal extends HurtByTargetGoal {
        public BrownBearHurtByTargetGoal() {
            super(BrownBear.this);
        }

        @Override
        public void start() {
            super.start();
            if (BrownBear.this.isBaby()) {
                this.alertOthers();
                this.stop();
            }
        }

        @Override
        protected void alertOther(Mob mob, LivingEntity target) {
            if (mob instanceof BrownBear && !mob.isBaby()) {
                super.alertOther(mob, target);
            }
        }
    }

    class BrownBearMeleeAttackGoal extends MeleeAttackGoal {
        public BrownBearMeleeAttackGoal() {
            super(BrownBear.this, 1.25, true);
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity target) {
            if (this.canPerformAttack(target)) {
                this.resetAttackCooldown();
                this.mob.doHurtTarget(getServerLevel(this.mob), target);
                BrownBear.this.setStanding(false);
            } else if (this.mob.distanceToSqr(target) < (target.getBbWidth() + 3.0F) * (target.getBbWidth() + 3.0F)) {
                if (this.isTimeToAttack()) {
                    BrownBear.this.setStanding(false);
                    this.resetAttackCooldown();
                }

                if (this.getTicksUntilNextAttack() <= 10) {
                    BrownBear.this.setStanding(true);
                    BrownBear.this.playWarningSound();
                }
            } else {
                this.resetAttackCooldown();
                BrownBear.this.setStanding(false);
            }
        }

        @Override
        public void stop() {
            BrownBear.this.setStanding(false);
            super.stop();
        }
    }
}
