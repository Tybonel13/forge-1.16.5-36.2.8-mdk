package com.tybonel13.slivermod.entities;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SliverMeleeAttackGoal extends MeleeAttackGoal {
    protected final SliverEntity attacker;
    private final double speedTowardsTarget;
    private final boolean longMemory;
    private Path path;
    private double targetX;
    private double targetY;
    private double targetZ;
    private int delayCounter;
    private int swingCooldown;
    private final int attackInterval = 20;
    private long lastCheckTime;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;
    private int attackcheck;


    public SliverMeleeAttackGoal(SliverEntity creature, double speedIn, boolean useLongMemory, int attack) {
        super(creature, speedIn, useLongMemory);
        this.attacker = creature;
        this.attackcheck = attack;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() { //canUse
        return super.shouldExecute();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        LivingEntity livingentity = this.attacker.getAttackTarget();
        if (livingentity == null) {
            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else if (!this.longMemory) {
            return !this.attacker.getNavigator().noPath();
        } else if (!this.attacker.isWithinHomeDistanceFromPosition(livingentity.getPosition())) {
            return false;
        } else {
            return !(livingentity instanceof PlayerEntity) || !livingentity.isSpectator() && !((PlayerEntity)livingentity).isCreative();
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() { //start
        super.startExecuting();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() { //stop
        super.resetTask();
        this.attacker.setAttacking(0);
    }



    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        LivingEntity livingentity = this.attacker.getAttackTarget();
        this.attacker.getLookController().setLookPositionWithEntity(livingentity, 30.0F, 30.0F);
        double d0 = this.attacker.getDistanceSq(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ());
        this.delayCounter = Math.max(this.delayCounter - 1, 0);
        if ((this.longMemory || this.attacker.getEntitySenses().canSee(livingentity)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || livingentity.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F)) {
            this.targetX = livingentity.getPosX();
            this.targetY = livingentity.getPosY();
            this.targetZ = livingentity.getPosZ();
            this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
            if (this.canPenalize) {
                this.delayCounter += failedPathFindingPenalty;
                if (this.attacker.getNavigator().getPath() != null) {
                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
                    if (finalPathPoint != null && livingentity.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                        failedPathFindingPenalty = 0;
                    else
                        failedPathFindingPenalty += 10;
                } else {
                    failedPathFindingPenalty += 10;
                }
            }
            if (d0 > 1024.0D) {
                this.delayCounter += 10;
            } else if (d0 > 256.0D) {
                this.delayCounter += 5;
            }

            if (!this.attacker.getNavigator().tryMoveToEntityLiving(livingentity, this.speedTowardsTarget)) {
                this.delayCounter += 15;
            }
        }

        this.swingCooldown = Math.max(this.swingCooldown - 1, 0);
        this.checkAndPerformAttack(livingentity, d0);
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.getAttackReachSqr(enemy);
        if ((distToEnemySqr <= d0 && this.swingCooldown <= 5) && !(distToEnemySqr <= d0 && this.swingCooldown <= 0)) {
            this.attacker.setAttacking(1);
        }
        else if (distToEnemySqr <= d0 && this.swingCooldown <= 0) {
            this.resetSwingCooldown();
            this.attacker.swingArm(Hand.MAIN_HAND);
            this.attacker.attackEntityAsMob(enemy);
        }
    }

    protected void resetSwingCooldown() {
        this.swingCooldown = 20;
    }

    protected int func_234042_k_() {
        return 20;
    }

    protected double getAttackReachSqr(LivingEntity attackTarget) {
        return (double)(this.attacker.getWidth() * 2.0F * this.attacker.getWidth() * 2.0F + attackTarget.getWidth());
    }
}
