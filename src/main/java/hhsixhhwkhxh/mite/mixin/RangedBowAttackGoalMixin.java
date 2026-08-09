package hhsixhhwkhxh.mite.mixin;

import hhsixhhwkhxh.mite.accessor.RangedBowAttackGoalAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RangedBowAttackGoal.class)
public abstract class RangedBowAttackGoalMixin<T extends net.minecraft.world.entity.Mob & RangedAttackMob> extends Goal implements RangedBowAttackGoalAccessor {
    @Shadow
    private final T mob;

    @Unique
    Vec3 oldPos;

    protected RangedBowAttackGoalMixin(T mob) {
        this.mob = mob;
    }

    @Inject(method = "tick",at = @At("RETURN"))
    public void tick(CallbackInfo ci) {
        LivingEntity target = this.mob.getTarget();
        if(target!=null){
            oldPos = target.position();
        }
    }

    @Override
    public Vec3 getOldPos() {
        return oldPos;
    }
}
