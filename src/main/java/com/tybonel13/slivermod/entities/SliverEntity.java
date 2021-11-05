package com.tybonel13.slivermod.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import javax.annotation.Nullable;
import java.util.*;

public class SliverEntity extends MonsterEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (!(limbSwingAmount > -0.25F && limbSwingAmount < 0.25F)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sliver.walk", true));
        }
        else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sliver.idle", true));
        }
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState predicate1(AnimationEvent<E> event) {
        if(getAttacking() == 1) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sliver.attack", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController(this, "controller1", 0, this::predicate1));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.setVariant(this.rand.nextInt(9));
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(SliverEntity.class, DataSerializers.VARINT);

    public int getVariant() {
        return MathHelper.clamp(this.dataManager.get(VARIANT), 0, 8);
    }

    public void setVariant(int variantIn) {
        this.dataManager.set(VARIANT, variantIn);
    }

    private static final DataParameter<Integer> ATTACKING = EntityDataManager.createKey(SliverEntity.class, DataSerializers.VARINT);

    public int getAttacking() {
        return this.dataManager.get(ATTACKING);
    }

    public void setAttacking(int attackIn) {
        this.dataManager.set(ATTACKING, attackIn);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(VARIANT, 0);
        this.dataManager.register(ATTACKING, 0);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Variant", this.getVariant());
        compound.putInt("Attacking", this.getAttacking());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setVariant(compound.getInt("Variant"));
        if(this.getVariant()==1) {
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2);
        }
        if(this.getVariant()==2) {
            this.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(.5);
        }
        if(this.getVariant()==3) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(5);
        }
        if(this.getVariant()==4) {
            this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(64);
        }
        if(this.getVariant()==5) {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(.4);
        }
        if(this.getVariant()==6) {
            this.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(5);
        }
        if(this.getVariant()==7) {
            this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
        }
        if(this.getVariant()==8) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(30);
        }
    }

    public SliverEntity(EntityType<? extends MonsterEntity> p_i48553_1_, World p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
        this.ignoreFrustumCheck = true;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.00)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3)
                .createMutableAttribute(Attributes.ARMOR, 0)
                .createMutableAttribute(Attributes.ARMOR_TOUGHNESS, 0)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 0)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 32.0D);
    }

    public void update() {
        double range = (this.getAttribute(Attributes.FOLLOW_RANGE).getValue())/6.4;
        double px = (double) this.getPosition().getX() + range;
        double py = (double) this.getPosition().getY() + range;
        double pz = (double) this.getPosition().getZ() + range;
        double nx = (double) this.getPosition().getX() - range;
        double ny = (double) this.getPosition().getY() - range;
        double nz = (double) this.getPosition().getZ() - range;
        AxisAlignedBB scanBox = new AxisAlignedBB(px, py, pz, nx, ny, nz);
        List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(this, scanBox);

        List<SliverEntity> attackList = new ArrayList<SliverEntity>();
        ModifiableAttributeInstance SliverAttack = this.getAttribute(Attributes.ATTACK_DAMAGE);

        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getClass() == SliverEntity.class) {
                SliverEntity sEntity = ((SliverEntity) entities.get(i));
                if (sEntity.getVariant() == 1) {
                    if(!attackList.contains(sEntity)) {
                        attackList.add(sEntity);
                    }
                }
            }
        }
        if(SliverAttack.getValue() - SliverAttack.getBaseValue() != attackList.size()) {
            SliverAttack.removeAllModifiers();
            SliverAttack.applyPersistentModifier(new AttributeModifier("sAttackMod", attackList.size(), AttributeModifier.Operation.ADDITION));
        }
        for (int i = 0; i < attackList.size(); i++) {
            if(!(entities.contains(attackList.get(i)))) {
                attackList.remove(attackList.get(i));
            }
        }
        ITextComponent name = new StringTextComponent("" + getAttacking());
        this.setCustomName(name);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.applyEntityAI();
    }

    protected void applyEntityAI() {
        this.goalSelector.addGoal(1, new SliverMeleeAttackGoal(this, 1.0D, false, 0));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));

    }

    public void livingTick() {
        if (this.isAlive()) {
            if(!this.world.isRemote && !this.isAIDisabled()) {
                update();
            }
        }
        super.livingTick();
    }



        @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 1 + this.world.rand.nextInt(4);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PHANTOM_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CREEPER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SILVERFISH_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_SILVERFISH_STEP, 0.15F, 1.0F);
    }
}
