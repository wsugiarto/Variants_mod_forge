package net.sharkron.variants_mod.entity.custom;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.sharkron.variants_mod.entity.ModEntity;

public class VexxProjectile extends AbstractHurtingProjectile{
    private int life;
    private int pierces;

    public VexxProjectile(EntityType<? extends VexxProjectile> p, Level level){
        super(p, level);
    }

    // This should be constructor for non abstract
    public VexxProjectile(Level level, LivingEntity owner, double x, double y, double z){
        this(ModEntity.VEXX_PROJECTILE.get(), level);
        this.setOwner(owner);
        this.setPos(owner.getX(), owner.getY() + owner.getEyeHeight(), owner.getZ());
        // this.setRot(-owner.getYRot(), -owner.getXRot());
        this.shoot(x, y, z, 2.0f, 0.0F);
        
    }

    // To change speed
    // Basically called every tick it's alive
    public void tick(){
        super.tick();
        Vec3 vec3 = this.getDeltaMovement();
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult))
        this.onHit(hitresult);
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();
        this.setPos(d0, d1, d2);
        this.level().addParticle(this.getTrailParticle(), d0, d1 + 0.3, d2, 0.0D, 0.0D, 0.0D);
        if (this.level().getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) { // If in a block
            // this.tickDespawn();
        } else if (this.isInWaterOrBubble()) { // under water
            this.setDeltaMovement(vec3.scale((double)1.2F));
        } else { // in air
            this.setDeltaMovement(vec3.scale((double)1.2F)); // this changes friction
            
            // if (!this.isNoGravity()) { // if gravity
            //     this.setDeltaMovement(this.getDeltaMovement().add(0.0D, (double)0, 0.0D));
            // }
        }
        this.tickDespawn();
    }

    // These should be in the non abstract class 
    protected void onHitEntity(EntityHitResult hit) {
        super.onHitEntity(hit);
        float damage = 5.0F;
        Entity entity = this.getOwner();
        if (entity instanceof LivingEntity livingentity) {
            hit.getEntity().hurt(this.damageSources().mobProjectile(this, livingentity), damage + life);
            this.pierces++;
        }
        if(this.pierces >= 3){
            this.discard();
        }

    }

    protected void onHitBlock(BlockHitResult hit) {
        super.onHitBlock(hit); // apparently this is just so the block knows it got hit
        if (!this.level().isClientSide()) {
        }

    }

    public boolean shouldRenderAtSqrDistance(double dist) {
        double d0 = this.getBoundingBox().getSize() * 4.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }

        d0 *= 64.0D;
        return dist < d0 * d0;
    }

    
    // Timer before it despawns
    protected void tickDespawn() {
        ++this.life;
        if (this.life >= 20) {
            this.discard();
        }

    }

    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.BUBBLE;
    }

    protected void defineSynchedData() {
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
