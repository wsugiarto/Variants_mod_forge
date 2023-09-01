package net.sharkron.variants_mod.entity.custom;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;

public abstract class AbstractStaffBolt extends Projectile{
    public AbstractStaffBolt(EntityType<? extends AbstractStaffBolt> p, Level level){
        super(p, level);
    }

    // This should be constructor for non abstract
    // public StaffBoltEntity(Level level, LivingEntity owner, double x, double y, double z){
    //     this(ModEntity.STAFF_BOLT, level);
    //     this.setOwner(owner);
    //     this.setPos(x, y, z);

    // }

    // How the projectile works
    public void tick() {
        super.tick();
        Vec3 vec3 = this.getDeltaMovement();
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult))
        this.onHit(hitresult);
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.setPos(d0, d1, d2);
    }

    public boolean shouldRenderAtSqrDistance(double dist) {
        double d0 = this.getBoundingBox().getSize() * 4.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }

        d0 *= 64.0D;
        return dist < d0 * d0;
    }
    

    // These should be in the non abstract class 

    // protected void onHitEntity(EntityHitResult p_37241_) {
    //     super.onHitEntity(p_37241_);
    //     Entity entity = this.getOwner();
    //     if (entity instanceof LivingEntity livingentity) {
    //         p_37241_.getEntity().hurt(this.damageSources().mobProjectile(this, livingentity), 1.0F);
    //     }

    // }

    // protected void onHitBlock(BlockHitResult p_37239_) {
    //     super.onHitBlock(p_37239_);
    //     if (!this.level().isClientSide) {
    //         this.discard();
    //     }

    // }

    protected void defineSynchedData() {
    }

    public void recreateFromPacket(ClientboundAddEntityPacket p_150162_) {
        super.recreateFromPacket(p_150162_);
        double d0 = p_150162_.getXa();
        double d1 = p_150162_.getYa();
        double d2 = p_150162_.getZa();

        for(int i = 0; i < 7; ++i) {
            double d3 = 0.4D + 0.1D * (double)i;
            this.level().addParticle(ParticleTypes.SPIT, this.getX(), this.getY(), this.getZ(), d0 * d3, d1, d2 * d3);
        }

        this.setDeltaMovement(d0, d1, d2);
    }

}
