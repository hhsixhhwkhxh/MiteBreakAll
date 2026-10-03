package hhsixhhwkhxh.mite.entity;

import hhsixhhwkhxh.mite.sound.ModSoundEvents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.UUID;

public class Cougar extends Animal implements NeutralMob {
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Cougar.class, EntityDataSerializers.INT);


    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @Nullable
    private UUID persistentAngerTarget;

    public AnimationState earFlapAnimationState = new AnimationState();
    public AnimationState tailFlickAnimationState = new AnimationState();
    public AnimationState trunkCurlAnimationState = new AnimationState();


    public Cougar(EntityType<? extends Cougar> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(PathType.POWDER_SNOW, -1.0F);
        this.setPathfindingMalus(PathType.DANGER_POWDER_SNOW, -1.0F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0F, true){
            @Override
            public boolean canUse() {
                return super.canUse()&&!isBaby();
            }
        });
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0F));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false,null));

        this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, true));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 30.0).add(Attributes.ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected @org.jetbrains.annotations.Nullable SoundEvent getAmbientSound() {
        return ModSoundEvents.COUGAR_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return ModSoundEvents.COUGAR_HURT.get();
    }

    @Override
    protected @org.jetbrains.annotations.Nullable SoundEvent getDeathSound() {
        return ModSoundEvents.COUGAR_DEATH.get();
    }

    @Override
    public boolean doHurtTarget(@NotNull ServerLevel level, @NotNull Entity source) {
        this.playSound(ModSoundEvents.COUGAR_ATTACK.get(), getSoundVolume(), this.getVoicePitch());
        return super.doHurtTarget(level, source);
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.isAlive()) {
            return;
        }
        if(!level().isClientSide){
            if(tickCount%20 == 0 && getRandom().nextFloat() < 0.3){
                this.level().broadcastEntityEvent(this, (byte)(60 + getRandom().nextInt(3)));
            }
        }

    }

    /**
     * Called when the mob's health reaches 0.
     */
    @Override
    public void die(DamageSource cause) {
        super.die(cause);
    }


    @Override
    public boolean hurtServer(@NotNull ServerLevel level, @NotNull DamageSource damageSource, float amount) {
        if (this.isInvulnerableTo(level, damageSource)) {
            return false;
        } else {
            return super.hurtServer(level, damageSource, amount);
        }
    }


    @Override
    public void handleEntityEvent(byte id) {
        //不要使用63
        switch (id){
            case 60 -> earFlapAnimationState.start(this.tickCount);
            case 61 -> tailFlickAnimationState.start(this.tickCount);
            case 62 -> trunkCurlAnimationState.start(this.tickCount);
            default -> super.handleEntityEvent(id);
        }
    }



    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on the animal type)
     */
    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ItemTags.WOLF_FOOD);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 8;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int remainingPersistentAngerTime) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, remainingPersistentAngerTime);
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID persistentAngerTarget) {
        this.persistentAngerTarget = persistentAngerTarget;
    }


    @Nullable
    public Cougar getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob otherParent) {
        return ModEntityTypes.COUGAR.get().create(level, EntitySpawnReason.BREEDING);
    }



    @Override
    public boolean canBeLeashed() {
        return !this.isAngry();
    }

    @Override
    public Vec3 getLeashOffset() {
        return new Vec3(0.0, 0.6F * this.getEyeHeight(), this.getBbWidth() * 0.4F);
    }



}
