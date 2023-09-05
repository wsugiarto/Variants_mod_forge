package net.sharkron.variants_mod.entity.custom;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.sharkron.variants_mod.entity.ModEntity;

import java.util.ArrayList;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public class UniversalSpellbookBolt extends AbstractStaffBolt{
    private int life;

    public UniversalSpellbookBolt(EntityType<? extends UniversalSpellbookBolt> p, Level level){
        super(p, level);
    }

    // This should be constructor for non abstract
    public UniversalSpellbookBolt(Level level, LivingEntity owner, double x, double y, double z){
        this(ModEntity.UNIVERSAL_SPELLBOOK_BOLT.get(), level);
        this.setOwner(owner);
        this.setPos(owner.getX(), owner.getY() + owner.getEyeHeight(), owner.getZ());
        
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
            // this.discard(); // can pierce through blocks this way
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
        float damage = 8.0F;
        Entity entity = this.getOwner();
        Entity hit_entity = hit.getEntity();
        if (entity instanceof LivingEntity livingentity) {
            hit.getEntity().hurt(this.damageSources().mobProjectile(this, livingentity), damage);
            if(hit_entity instanceof LivingEntity){
                LivingEntity hit_living_entity = (LivingEntity) hit_entity;
                ArrayList<MobEffectInstance> list = new ArrayList<>();
                list.add(new MobEffectInstance(MobEffects.POISON, 50, 1));
                list.add(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 1));
                list.add(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 50, 1));
                list.add(new MobEffectInstance(MobEffects.HARM, 1, 1));
                list.add(new MobEffectInstance(MobEffects.CONFUSION, 50, 1));
                list.add(new MobEffectInstance(MobEffects.BLINDNESS, 50, 1));
                list.add(new MobEffectInstance(MobEffects.HUNGER, 50, 1));
                list.add(new MobEffectInstance(MobEffects.WEAKNESS, 50, 1));
                list.add(new MobEffectInstance(MobEffects.WITHER, 50, 1));
                list.add(new MobEffectInstance(MobEffects.GLOWING, 50, 1));
                list.add(new MobEffectInstance(MobEffects.LEVITATION, 50, 1));
                for(MobEffectInstance mobeffectinstance: list){
                    hit_living_entity.addEffect(mobeffectinstance, livingentity);
                }
                hit_living_entity.setSecondsOnFire(1);
                hit_living_entity.setTicksFrozen(20);
            }
        }

    }

    protected void onHitBlock(BlockHitResult hit) {
        super.onHitBlock(hit);
        if (!this.level().isClientSide) {
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
