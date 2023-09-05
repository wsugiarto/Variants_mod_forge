package net.sharkron.variants_mod.entity.custom;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.sharkron.variants_mod.entity.ModEntity;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public class GenericBullet extends AbstractStaffBolt{
    private int life;
    protected float activeDamage;
    protected float damage0;
    protected float damage1;
    protected float damage2;
    ParticleOptions particle = ParticleTypes.CRIT;
    protected int fallOff1;
    protected int fallOff2;
    protected int end;

    public GenericBullet(EntityType<? extends GenericBullet> p, Level level){
        super(p, level);
    }

    // This should be constructor for non abstract
    public GenericBullet(Level level, LivingEntity owner, double x, double y, double z, float damage0, int fallOff1, float damage1, int fallOff2, float damage2, int end){
        this(ModEntity.GENERIC_BULLET.get(), level);
        this.setOwner(owner);
        this.setPos(owner.getX(), owner.getY() + owner.getEyeHeight(), owner.getZ());
        this.shoot(x, y, z, 4.0f, 0.0F);

        this.fallOff1 = fallOff1;
        this.fallOff2 = fallOff2;
        this.end = end;
        this.damage0 = damage0;
        this.damage1 = damage1;
        this.damage2 = damage2;
        this.activeDamage = this.damage0;
        
    }

    // To change speed
    // Basically called every tick it's alive
    public void tick(){
        super.tick();
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.level().addParticle(this.getTrailParticle(), d0, d1, d2, 0.0D, 0.0D, 0.0D); // adding particle
        if (this.level().getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) { // If in a block
            this.discard(); // can pierce through blocks this way
        } else if (this.isInWaterOrBubble()) { // under water
            this.setDeltaMovement(vec3.scale((double)0.5F));
            this.tickDespawn();
        } else { // in air
            this.setDeltaMovement(vec3.scale((double)1.0F)); // this changes friction
            this.tickDespawn();
            // if (!this.isNoGravity()) { // if gravity
            //     this.setDeltaMovement(this.getDeltaMovement().add(0.0D, (double)0, 0.0D));
            // }
        }
    }

    // These should be in the non abstract class 
    protected void onHitEntity(EntityHitResult hit) {
        super.onHitEntity(hit);
        Entity entity = this.getOwner();
        if (entity instanceof LivingEntity livingentity) {
            hit.getEntity().hurt(this.damageSources().mobProjectile(this, livingentity), this.activeDamage);
        }
        
        this.discard();

    }

    protected void onHitBlock(BlockHitResult hit) {
        super.onHitBlock(hit);
        if (!this.level().isClientSide) {
            this.discard();
        }

    }

    // Timer before it despawns
    protected void tickDespawn() {
        ++this.life;

        if (this.life >= fallOff1) {
            this.activeDamage = this.damage1;
            this.setTrailParticle(ParticleTypes.SMOKE);
        }
        
        if (this.life >= fallOff2) {
            this.activeDamage = this.damage2;
        }

        if (this.life >= end) {
            this.discard();
        }

    }

    protected ParticleOptions getTrailParticle() {
        return particle;
    }

    protected void setTrailParticle(ParticleOptions p) {
        this.particle = p;
    }

    protected void defineSynchedData() {
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
