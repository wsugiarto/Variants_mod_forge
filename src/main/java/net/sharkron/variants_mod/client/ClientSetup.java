package net.sharkron.variants_mod.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.sharkron.variants_mod.VariantsMod;
import net.sharkron.variants_mod.client.renderer.AmethystShardBoltRenderer;
import net.sharkron.variants_mod.client.renderer.CharcoalStaffBoltRenderer;
import net.sharkron.variants_mod.client.renderer.CopperStaffBoltRenderer;
import net.sharkron.variants_mod.client.renderer.DiamondStaffBoltRenderer;
import net.sharkron.variants_mod.client.renderer.GenericBulletRenderer;
import net.sharkron.variants_mod.client.renderer.MiniDiamondBoltRenderer;
import net.sharkron.variants_mod.client.renderer.NetheriteForkProjectileRenderer;
import net.sharkron.variants_mod.client.renderer.RedstoneSpellbookProjectileRenderer;
import net.sharkron.variants_mod.client.renderer.SeedBulletRenderer;
import net.sharkron.variants_mod.client.renderer.SporeStaffBoltRenderer;
import net.sharkron.variants_mod.client.renderer.StoneLauncherProjectileRenderer;
import net.sharkron.variants_mod.client.renderer.TopazSpellbookBoltRenderer;
import net.sharkron.variants_mod.client.renderer.TopazStaffBoltRenderer;
import net.sharkron.variants_mod.client.renderer.TorchTombProjectileRenderer;
import net.sharkron.variants_mod.entity.ModEntity;

@Mod.EventBusSubscriber(modid = VariantsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntity.TOPAZ_BOLT.get(), TopazStaffBoltRenderer::new);
        EntityRenderers.register(ModEntity.DIAMOND_BOLT.get(), DiamondStaffBoltRenderer::new);
        EntityRenderers.register(ModEntity.NETHERITE_FORK_PROJECTILE.get(), NetheriteForkProjectileRenderer::new);
        EntityRenderers.register(ModEntity.AMETHYST_SHARD_BOLT.get(), AmethystShardBoltRenderer::new);
        EntityRenderers.register(ModEntity.CHARCOAL_BOLT.get(), CharcoalStaffBoltRenderer::new);
        EntityRenderers.register(ModEntity.TORCH_TOMB_PROJECTILE.get(), TorchTombProjectileRenderer::new);
        EntityRenderers.register(ModEntity.SEED_BULLET.get(), SeedBulletRenderer::new);
        EntityRenderers.register(ModEntity.COPPER_BOLT.get(), CopperStaffBoltRenderer::new);
        EntityRenderers.register(ModEntity.STONE_LAUNCHER_PROJECTILE.get(), StoneLauncherProjectileRenderer::new);
        EntityRenderers.register(ModEntity.SPORE_STAFF_BOLT.get(), SporeStaffBoltRenderer::new);
        EntityRenderers.register(ModEntity.REDSTONE_SPELLBOOK_PROJECTILE.get(), RedstoneSpellbookProjectileRenderer::new);
        EntityRenderers.register(ModEntity.GENERIC_BULLET.get(), GenericBulletRenderer::new);
        EntityRenderers.register(ModEntity.TOPAZ_SPELLBOOK_MAIN_BOLT.get(), TopazSpellbookBoltRenderer::new);
        EntityRenderers.register(ModEntity.MINI_DIAMOND_BOLT.get(), MiniDiamondBoltRenderer::new);
    }
}
