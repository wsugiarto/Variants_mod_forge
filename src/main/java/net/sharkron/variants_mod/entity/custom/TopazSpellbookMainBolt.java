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
import net.minecraft.world.entity.player.Player;

public class TopazSpellbookMainBolt extends AbstractStaffBolt{
    private int life;
    private Player player;

    public TopazSpellbookMainBolt(EntityType<? extends TopazSpellbookMainBolt> p, Level level){
        super(p, level);
    }

    // This should be constructor for non abstract
    public TopazSpellbookMainBolt(Level level, LivingEntity owner, double x, double y, double z){
        this(ModEntity.TOPAZ_SPELLBOOK_MAIN_BOLT.get(), level);
        this.setOwner(owner);
        this.setPos(owner.getX(), owner.getY() + owner.getEyeHeight(), owner.getZ());
        this.shoot(x, y, z, 1.5f, 0.0F);
        if(owner instanceof Player){
            this.player = (Player)owner;
        }
    }

    // To change speed
    // Basically called every tick it's alive
    public void tick(){
        super.tick();
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.level().addParticle(this.getTrailParticle(), d0, d1 + 0.3, d2, 0.0D, 0.0D, 0.0D); // adding particle
        if (this.level().getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) { // If in a block
            this.discard(); // can pierce through blocks this way
        } else if (this.isInWaterOrBubble()) { // under water
            this.setDeltaMovement(vec3.scale((double)1.0F));
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
        float damage = 6.0F;
        Entity entity = this.getOwner();
        if (entity instanceof LivingEntity livingentity) {
            hit.getEntity().hurt(this.damageSources().mobProjectile(this, livingentity), damage);

            TopazStaffBolt proj1 = new TopazStaffBolt(this.level(), this.player, 0, 0, 0);
            proj1.setPos(this.getX(), this.getY() + this.getEyeHeight(), this.getZ());
            proj1.shoot(this.getX(), this.getY(), this.getZ(), 0.1F, 1000F);

            TopazStaffBolt proj2 = new TopazStaffBolt(this.level(), this.player, 0, 0, 0);
            proj2.setPos(this.getX(), this.getY() + this.getEyeHeight(), this.getZ());
            proj2.shoot(this.getX(), this.getY(), this.getZ(), 0.1F, 1000F);

            TopazStaffBolt proj3 = new TopazStaffBolt(this.level(), this.player, 0, 0, 0);
            proj3.setPos(this.getX(), this.getY() + this.getEyeHeight(), this.getZ());
            proj3.shoot(this.getX(), this.getY(), this.getZ(), 0.1F, 1000F);

            this.level().addFreshEntity(proj1);
            this.level().addFreshEntity(proj2);
            this.level().addFreshEntity(proj3);
            this.discard();
        }

    }

    protected void onHitBlock(BlockHitResult hit) {
        super.onHitBlock(hit);
        if (!this.level().isClientSide) {
            TopazStaffBolt proj1 = new TopazStaffBolt(this.level(), this.player, 0, 0, 0);
            proj1.setPos(this.getX(), this.getY() + this.getEyeHeight(), this.getZ());
            proj1.shoot(this.getX(), this.getY(), this.getZ(), 0.1F, 1000F);

            TopazStaffBolt proj2 = new TopazStaffBolt(this.level(), this.player, 0, 0, 0);
            proj2.setPos(this.getX(), this.getY() + this.getEyeHeight(), this.getZ());
            proj2.shoot(this.getX(), this.getY(), this.getZ(), 0.1F, 1000F);

            TopazStaffBolt proj3 = new TopazStaffBolt(this.level(), this.player, 0, 0, 0);
            proj3.setPos(this.getX(), this.getY() + this.getEyeHeight(), this.getZ());
            proj3.shoot(this.getX(), this.getY(), this.getZ(), 0.1F, 1000F);
            
            this.level().addFreshEntity(proj1);
            this.level().addFreshEntity(proj2);
            this.level().addFreshEntity(proj3);
            this.discard();
        }

    }

    // Timer before it despawns
    protected void tickDespawn() {
        ++this.life;
        if (this.life >= 20) {
            this.discard();
        }

    }

    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.INSTANT_EFFECT;
    }

    protected void defineSynchedData() {
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
