package hhsixhhwkhxh.mite.mixin;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.custom.ModRangedBowAttackGoal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSkeleton.class)
public abstract class AbstractSkeletonMixin extends Monster implements RangedAttackMob {

    @Final
    @Shadow
    @Mutable
    private RangedBowAttackGoal<AbstractSkeleton> bowGoal;

    protected AbstractSkeletonMixin(EntityType<? extends Monster> entityType, Level level, RangedBowAttackGoal<AbstractSkeleton> bowGoal) {
        super(entityType, level);
        this.bowGoal = bowGoal;
    }

    @Inject(method = "<init>",at = @At("TAIL"))
    public void init(CallbackInfo ci) {
        bowGoal = new ModRangedBowAttackGoal<>(((AbstractSkeleton)(Object)this), 1.0, 20, 30.0F);
    }

    @Inject(method = "createAttributes",at = @At("RETURN"))
    private static void createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue().add(Attributes.FOLLOW_RANGE,30.0);
    }

    @Inject(method = "performRangedAttack",at = @At("HEAD"), cancellable = true)
    public void performRangedAttack(LivingEntity target, float distanceFactor, CallbackInfo ci) {

        ItemStack weapon = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.BowItem));
        ItemStack itemstack1 = this.getProjectile(weapon);
        AbstractArrow abstractarrow = this.getArrow(itemstack1, distanceFactor, weapon);
        if (weapon.getItem() instanceof net.minecraft.world.item.ProjectileWeaponItem weaponItem)
            abstractarrow = weaponItem.customArrow(abstractarrow, itemstack1, weapon);
        double dx = target.getX() - this.getX();
        double dy = target.getY(0.3333333333333333) - abstractarrow.getY();
        double dz = target.getZ() - this.getZ();
        double distance = Math.sqrt(dx * dx + dz * dz);

        //MiteBreakAll.LOGGER.debug("原dx:"+dx+" dz:"+dz);


        double t = distance/(1.6F);
        MiteBreakAll.LOGGER.debug("t:"+t+" distance:"+distance);
        //Vec3 oldPos = ((RangedBowAttackGoalAccessor)bowGoal).getOldPos();
        Vec3 oldPos = ((ModRangedBowAttackGoal<AbstractSkeleton>)bowGoal).getOldPos();

        if(oldPos==null){
            return;
        }else{
            ci.cancel();
        }

        dx += (target.getX()-oldPos.x)*t;
        dy += -0.0784*t;
        dz += (target.getZ()-oldPos.z)*t;

        //MiteBreakAll.LOGGER.debug("现dx:"+dx+" dz:"+dz);

        //MiteBreakAll.LOGGER.debug("target.getDeltaMovement().y:"+target.getDeltaMovement().y);

        double offsetY = 0;
        if(distance<10){
            offsetY = distance * 0.2F;
        }else if(distance<15){
            offsetY = distance * 0.25F;
        }else if(distance<20){
            offsetY = distance * 0.30F;
        }else if(distance<25){
            offsetY = distance * 0.34F;
        }else{
            offsetY = distance * 0.40F;
        }

        if (this.level() instanceof ServerLevel serverlevel) {
            Projectile.spawnProjectileUsingShoot(
                    abstractarrow, serverlevel, itemstack1, dx, dy + offsetY, dz, 1.6F, 0
            );
        }


        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
    }

    @Shadow
    private AbstractArrow getArrow(ItemStack itemstack1, float distanceFactor, ItemStack weapon) {
        return null;
    }

    @ModifyConstant(method = "registerGoals",constant = @Constant(floatValue = 8.0F))
    public float enhanceLookDistance(float constant){
        return 30.0F;
    }

}