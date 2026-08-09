package hhsixhhwkhxh.mite.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.phys.Vec3;

public class ModRangedBowAttackGoal<T extends Mob & RangedAttackMob> extends RangedBowAttackGoal<T> {

    private Vec3 oldPos;
    private T mob;

    public ModRangedBowAttackGoal(T mob, double speedModifier, int attackIntervalMin, float attackRadius) {
        super(mob, speedModifier, attackIntervalMin, attackRadius);
        this.mob = mob;
    }




    @Override
    public void tick() {
        super.tick();
        LivingEntity target = this.mob.getTarget();
        if(target!=null){
            oldPos = target.position();
        }
    }

    public Vec3 getOldPos() {
        return oldPos;
    }
}
