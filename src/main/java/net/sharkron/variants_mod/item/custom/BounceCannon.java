package net.sharkron.variants_mod.item.custom;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.sharkron.variants_mod.entity.custom.BouncingHead;

public class BounceCannon extends Item {
    public BounceCannon(Properties pProperties){
        super(pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        int maxUseBeforeBroken = itemstack.getMaxDamage() - 1;

        if (!level.isClientSide && itemstack.getDamageValue() < maxUseBeforeBroken) {
            Vec3 look = player.getLookAngle(); // Get the player's look vector
            double spawnX = player.getX();
            double spawnY = player.getY() + player.getEyeHeight();
            double spawnZ = player.getZ();
            for(int i=0; i<4; i+=2){
                Projectile proj = new BouncingHead(level, player, look.x, look.y, look.z);
                proj.setPos(spawnX, spawnY, spawnZ);
                level.addFreshEntity(proj); // adding the entity into the world level
            }
            player.getCooldowns().addCooldown(this, 10);
            itemstack.hurtAndBreak(1, player, 
                    p -> p.broadcastBreakEvent(hand));

            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public int getEnchantmentValue(){
        return 1;
    }

    @Override
    public boolean canBeDepleted(){
        return true;
    }
}
