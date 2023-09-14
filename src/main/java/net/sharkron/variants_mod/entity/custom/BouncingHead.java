package net.sharkron.variants_mod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class BouncingHead extends WitherSkull {
   private static final EntityDataAccessor<Boolean> DATA_DANGEROUS = SynchedEntityData.defineId(BouncingHead.class, EntityDataSerializers.BOOLEAN);

   public BouncingHead(EntityType<? extends BouncingHead> p_37598_, Level p_37599_) {
      super(p_37598_, p_37599_);
   }

   
   public BouncingHead(Level level, Player player, double x, double y, double z) {
        super(level, player, x, y, z);
    }


protected float getInertia() {
      return this.isDangerous() ? 0.73F : super.getInertia();
   }

   public boolean isOnFire() {
      return false;
   }

   public float getBlockExplosionResistance(Explosion p_37619_, BlockGetter p_37620_, BlockPos p_37621_, BlockState p_37622_, FluidState p_37623_, float p_37624_) {
      return this.isDangerous()  ? Math.min(0.8F, p_37624_) : p_37624_;
      // && p_37622_.canEntityDestroy(p_37620_, p_37621_, this)
   }

   protected void onHitEntity(EntityHitResult p_37626_) {
      super.onHitEntity(p_37626_);
      if (!this.level().isClientSide) {
         Entity entity = p_37626_.getEntity();
         Entity entity1 = this.getOwner();
         boolean flag;
         if (entity1 instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)entity1;
            flag = entity.hurt(this.damageSources().witherSkull(this, livingentity), 8.0F);
            if (flag) {
               if (entity.isAlive()) {
                  this.doEnchantDamageEffects(livingentity, entity);
               } else {
                  livingentity.heal(5.0F);
               }
            }
         } else {
            flag = entity.hurt(this.damageSources().magic(), 5.0F);
         }

         if (flag && entity instanceof LivingEntity) {
            LivingEntity livingentity1 = (LivingEntity)entity;
            int i = 0;
            if (this.level().getDifficulty() == Difficulty.NORMAL) {
               i = 10;
            } else if (this.level().getDifficulty() == Difficulty.HARD) {
               i = 40;
            }

            if (i > 0) {
               livingentity1.addEffect(new MobEffectInstance(MobEffects.WITHER, 20 * i, 1), this.getEffectSource());
            }
         }

      }
   }

   protected void onHit(HitResult p_37628_) {
      super.onHit(p_37628_);
      if (!this.level().isClientSide()) {
         //this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1.5F, true, Level.ExplosionInteraction.BLOCK);
         this.discard();
      }

   }

   public boolean isPickable() {
      return false;
   }

   public boolean hurt(DamageSource p_37616_, float p_37617_) {
      return false;
   }

   protected void defineSynchedData() {
      this.entityData.define(DATA_DANGEROUS, false);
   }

   public boolean isDangerous() {
      return this.entityData.get(DATA_DANGEROUS);
   }

   public void setDangerous(boolean p_37630_) {
      this.entityData.set(DATA_DANGEROUS, p_37630_);
   }

   protected boolean shouldBurn() {
      return false;
   }
}

