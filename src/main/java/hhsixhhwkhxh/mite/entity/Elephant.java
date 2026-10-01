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
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.UUID;

public class Elephant extends Animal implements NeutralMob {
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Elephant.class, EntityDataSerializers.INT);


    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @Nullable
    private UUID persistentAngerTarget;

    public AnimationState earFlapAnimationState = new AnimationState();
    public AnimationState tailFlickAnimationState = new AnimationState();
    public AnimationState trunkCurlAnimationState = new AnimationState();


    public Elephant(EntityType<? extends Elephant> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(PathType.POWDER_SNOW, -1.0F);
        this.setPathfindingMalus(PathType.DANGER_POWDER_SNOW, -1.0F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0F, true));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0F));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MOVEMENT_SPEED, 0.2F).add(Attributes.MAX_HEALTH, 60.0).add(Attributes.ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected @org.jetbrains.annotations.Nullable SoundEvent getAmbientSound() {
        return isBaby()?ModSoundEvents.ELEPHANT_BABY_IDLE.get():ModSoundEvents.ELEPHANT_IDLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return ModSoundEvents.ELEPHANT_HURT.get();
    }

    @Override
    protected @org.jetbrains.annotations.Nullable SoundEvent getDeathSound() {
        return ModSoundEvents.ELEPHANT_DEATH.get();
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
    protected void actuallyHurt(@NotNull ServerLevel level, @NotNull DamageSource damageSource, float amount) {
        if (!this.canArmorAbsorb(damageSource)) {
            super.actuallyHurt(level, damageSource, amount);
        } else {
            ItemStack itemstack = this.getBodyArmorItem();
            int i = itemstack.getDamageValue();
            int j = itemstack.getMaxDamage();
            itemstack.hurtAndBreak(Mth.ceil(amount), this, EquipmentSlot.BODY);
            if (Crackiness.WOLF_ARMOR.byDamage(i, j) != Crackiness.WOLF_ARMOR.byDamage(this.getBodyArmorItem())) {
                this.playSound(SoundEvents.WOLF_ARMOR_CRACK);
                level.sendParticles(
                    new ItemParticleOption(ParticleTypes.ITEM, Items.ARMADILLO_SCUTE.getDefaultInstance()),
                    this.getX(),
                    this.getY() + 1.0,
                    this.getZ(),
                    20,
                    0.2,
                    0.1,
                    0.2,
                    0.1
                );
            }
        }
    }

    private boolean canArmorAbsorb(DamageSource damageSource) {
        return this.getBodyArmorItem().is(Items.WOLF_ARMOR) && !damageSource.is(DamageTypeTags.BYPASSES_WOLF_ARMOR);
    }


    @Override
    protected void hurtArmor(@NotNull DamageSource damageSource, float damageAmount) {
        this.doHurtEquipment(damageSource, damageAmount, EquipmentSlot.BODY);
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
    public Elephant getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob otherParent) {
        return ModEntityTypes.ELEPHANT.get().create(level, EntitySpawnReason.BREEDING);
    }


    /**
     * Returns {@code true} if the mob is currently able to mate with the specified mob.
     */



    @Override
    public boolean canBeLeashed() {
        return !this.isAngry();
    }

    @Override
    public Vec3 getLeashOffset() {
        return new Vec3(0.0, 0.6F * this.getEyeHeight(), this.getBbWidth() * 0.4F);
    }



}
