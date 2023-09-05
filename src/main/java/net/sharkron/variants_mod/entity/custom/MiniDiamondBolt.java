package net.sharkron.variants_mod.entity.custom;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ShulkerBullet;

public class MiniDiamondBolt extends ShulkerBullet{
    private int life;

    public MiniDiamondBolt(EntityType<? extends MiniDiamondBolt> p, Level level){
        super(p, level);
    }

    // This should be constructor for non abstract
    public MiniDiamondBolt(Level level, LivingEntity owner, Entity target, Projectile source){
        super(level, owner, target, source.getDirection().getAxis());
        
        
    }

    // To change speed
    // Basically called every tick it's alive
    public void tick(){
        super.tick();
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.level().addParticle(this.getTrailParticle(), d0, d1 + 0.3, d2, 0.0D, 0.0D, 0.0D);
        if (this.level().getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) { // If in a block
            this.tickDespawn();
        } else if (this.isInWaterOrBubble()) { // under water
            this.setDeltaMovement(vec3.scale((double)1.0F));
            this.tickDespawn();
        } else { // in air
            this.setDeltaMovement(vec3.scale((double)1.1F)); // this changes friction
            this.tickDespawn();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult hit) {
        float damage = 7.0F;
        Entity entity = this.getOwner();
        if (entity instanceof LivingEntity livingentity) {
            hit.getEntity().hurt(this.damageSources().mobProjectile(this, livingentity), damage);
        }
        this.discard();

    }

    @Override
    protected void onHitBlock(BlockHitResult hit) {
        BlockState blockstate = this.level().getBlockState(hit.getBlockPos());
        blockstate.onProjectileHit(this.level(), blockstate, hit, this);
        if (!this.level().isClientSide) {
        }

    }

    // Timer before it despawns
    protected void tickDespawn() {
        ++this.life;
        if (this.life >= 80) {
            this.discard();
        }

    }

    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.SNOWFLAKE;
    }

    protected void defineSynchedData() {
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
