package net.sharkron.variants_mod.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import net.sharkron.variants_mod.entity.custom.VexxProjectile;

public class VexxStaff extends Item{
    public VexxStaff(Properties pProperties){
        super(pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        int manaCost = 50;
        int maxUseBeforeBroken = itemstack.getMaxDamage() - manaCost;

        if (level.isClientSide || itemstack.getDamageValue() >= maxUseBeforeBroken) {
            return InteractionResultHolder.fail(itemstack);
        }

        else {
            Vec3 look = player.getLookAngle(); // Get the player's look vector

            // WitherSkull proj1 = new WitherSkull(level, player, look.x, look.y, look.z);
            VexxProjectile proj1 = new VexxProjectile(level, player, look.x, look.y, look.z);

            level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_HIT, SoundSource.PLAYERS, 1.0F, 1.0F);

            level.addFreshEntity(proj1);
            player.getCooldowns().addCooldown(this, 30);
            itemstack.hurtAndBreak(manaCost, player, 
                    p -> p.broadcastBreakEvent(hand));

            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
