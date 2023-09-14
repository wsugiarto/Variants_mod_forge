package net.sharkron.variants_mod.item.custom;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.sharkron.variants_mod.entity.custom.TopazStaffBolt;

public class TopazStaff extends Item{
    public TopazStaff(Properties pProperties){
        super(pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        int manaCost = 24;
        int maxUseBeforeBroken = itemstack.getMaxDamage() - manaCost;

        if (level.isClientSide || itemstack.getDamageValue() >= maxUseBeforeBroken) {
            return InteractionResultHolder.fail(itemstack);
        }
        else{
            Vec3 look = player.getLookAngle(); // Get the player's look vector

            TopazStaffBolt proj1 = new TopazStaffBolt(level, player, look.x, look.y, look.z);
            proj1.shoot(look.x, look.y, look.z, 1.5f, 0.0F);

            level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_RETURN, SoundSource.PLAYERS, 1.0F, 1.0F);
            
            level.addFreshEntity(proj1); // adding the entity into the world level
            player.getCooldowns().addCooldown(this, 15);
            itemstack.hurtAndBreak(manaCost, player, 
                    p -> p.broadcastBreakEvent(hand));

            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public boolean canBeDepleted(){
        return false;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("Consumes 24 Mana"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
