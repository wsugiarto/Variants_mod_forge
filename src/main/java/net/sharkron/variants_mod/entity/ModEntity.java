package net.sharkron.variants_mod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sharkron.variants_mod.VariantsMod;
import net.sharkron.variants_mod.entity.custom.AmethystShardBolt;
import net.sharkron.variants_mod.entity.custom.CharcoalStaffBolt;
import net.sharkron.variants_mod.entity.custom.CopperStaffBolt;
import net.sharkron.variants_mod.entity.custom.DiamondStaffBolt;
import net.sharkron.variants_mod.entity.custom.GenericBullet;
import net.sharkron.variants_mod.entity.custom.NetheriteForkProjectile;
import net.sharkron.variants_mod.entity.custom.RedstoneSpellbookProjectile;
import net.sharkron.variants_mod.entity.custom.SeedBullet;
import net.sharkron.variants_mod.entity.custom.SporeStaffBolt;
import net.sharkron.variants_mod.entity.custom.StoneLauncherProjectile;
import net.sharkron.variants_mod.entity.custom.TopazStaffBolt;
import net.sharkron.variants_mod.entity.custom.TorchTombProjectile;
import net.sharkron.variants_mod.entity.custom.VexxProjectile;

public class ModEntity {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VariantsMod.MODID);

    public static final RegistryObject<EntityType<TopazStaffBolt>> TOPAZ_BOLT = ENTITY_TYPES.register("topaz_bolt",
        () -> EntityType.Builder.of((EntityType.EntityFactory<TopazStaffBolt>) TopazStaffBolt::new, MobCategory.MISC).sized(0.5F, 0.5F).build("topaz_bolt"));

    public static final RegistryObject<EntityType<DiamondStaffBolt>> DIAMOND_BOLT = ENTITY_TYPES.register("diamond_bolt",
        () -> EntityType.Builder.of((EntityType.EntityFactory<DiamondStaffBolt>) DiamondStaffBolt::new, MobCategory.MISC).sized(0.5F, 0.5F).build("diamond_bolt"));
    
    public static final RegistryObject<EntityType<NetheriteForkProjectile>> NETHERITE_FORK_PROJECTILE = ENTITY_TYPES.register("netherite_fork_projectile",
        () -> EntityType.Builder.of((EntityType.EntityFactory<NetheriteForkProjectile>) NetheriteForkProjectile::new, MobCategory.MISC).sized(1F, 1F).build("netherite_fork_projectile"));
    
    public static final RegistryObject<EntityType<AmethystShardBolt>> AMETHYST_SHARD_BOLT = ENTITY_TYPES.register("amethyst_shard",
        () -> EntityType.Builder.of((EntityType.EntityFactory<AmethystShardBolt>) AmethystShardBolt::new, MobCategory.MISC).sized(0.5F, 0.5F).build("amethyst_shard"));

    public static final RegistryObject<EntityType<CharcoalStaffBolt>> CHARCOAL_BOLT = ENTITY_TYPES.register("charcoal_bolt",
        () -> EntityType.Builder.of((EntityType.EntityFactory<CharcoalStaffBolt>) CharcoalStaffBolt::new, MobCategory.MISC).sized(0.5F, 0.5F).build("charcoal_bolt"));
    
    public static final RegistryObject<EntityType<TorchTombProjectile>> TORCH_TOMB_PROJECTILE = ENTITY_TYPES.register("torch_tomb_projectile",
        () -> EntityType.Builder.of((EntityType.EntityFactory<TorchTombProjectile>) TorchTombProjectile::new, MobCategory.MISC).sized(0.5F, 0.5F).build("torch_tomb_projectile"));

    public static final RegistryObject<EntityType<SeedBullet>> SEED_BULLET = ENTITY_TYPES.register("seed_bullet",
        () -> EntityType.Builder.of((EntityType.EntityFactory<SeedBullet>) SeedBullet::new, MobCategory.MISC).sized(0.5F, 0.5F).build("seed_bullet"));

    public static final RegistryObject<EntityType<CopperStaffBolt>> COPPER_BOLT = ENTITY_TYPES.register("copper_bolt",
        () -> EntityType.Builder.of((EntityType.EntityFactory<CopperStaffBolt>) CopperStaffBolt::new, MobCategory.MISC).sized(0.5F, 0.5F).build("copper_bolt"));

    public static final RegistryObject<EntityType<StoneLauncherProjectile>> STONE_LAUNCHER_PROJECTILE = ENTITY_TYPES.register("stone_launcher_projectile",
        () -> EntityType.Builder.of((EntityType.EntityFactory<StoneLauncherProjectile>) StoneLauncherProjectile::new, MobCategory.MISC).sized(0.5F, 0.5F).build("stone_launcher_projectile"));

    public static final RegistryObject<EntityType<SporeStaffBolt>> SPORE_STAFF_BOLT = ENTITY_TYPES.register("spore_staff_bolt",
        () -> EntityType.Builder.of((EntityType.EntityFactory<SporeStaffBolt>) SporeStaffBolt::new, MobCategory.MISC).sized(0.5F, 0.5F).build("spore_staff_bolt"));

    public static final RegistryObject<EntityType<RedstoneSpellbookProjectile>> REDSTONE_SPELLBOOK_PROJECTILE = ENTITY_TYPES.register("redstone_spellbook_projectile",
        () -> EntityType.Builder.of((EntityType.EntityFactory<RedstoneSpellbookProjectile>) RedstoneSpellbookProjectile::new, MobCategory.MISC).sized(0.5F, 0.5F).build("redstone_spellbook_projectile"));
    
    public static final RegistryObject<EntityType<GenericBullet>> GENERIC_BULLET = ENTITY_TYPES.register("generic_bullet",
            () -> EntityType.Builder.of((EntityType.EntityFactory<GenericBullet>) GenericBullet::new, MobCategory.MISC).sized(0.5F, 0.5F).build("generic_bullet"));

    public static final RegistryObject<EntityType<VexxProjectile>> VEXX_PROJECTILE = ENTITY_TYPES.register("vexx_projectile", 
            () -> EntityType.Builder.of((EntityType.EntityFactory<VexxProjectile>) VexxProjectile::new, MobCategory.MISC).sized(0.5F,0.5F).build("vexx_projectile"));     

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
