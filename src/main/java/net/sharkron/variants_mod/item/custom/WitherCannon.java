package net.sharkron.variants_mod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.WitherSkull;;

public class WitherCannon extends Item {
    public WitherCannon(Properties pProperties){
        super(pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide || !hasAmmo(player)) {
            Vec3 look = player.getLookAngle(); // Get the player's look vector
            double spawnX = player.getX();
            double spawnY = player.getY() + player.getEyeHeight();
            double spawnZ = player.getZ();
            for(int i=0; i<10; i+=2){
                Projectile proj = new WitherSkull(level, player, look.x, look.y, look.z);
                proj.setPos(spawnX, spawnY, spawnZ);
                level.addFreshEntity(proj); // adding the entity into the world level
            }
            consumeAmmo(player);
            player.getCooldowns().addCooldown(this, 10);
            itemstack.hurtAndBreak(1, player, 
                    p -> p.broadcastBreakEvent(hand));

            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    private boolean hasAmmo(Player player){
        if(player.getAbilities().instabuild){
            return true;
        }

        for(int i = 0; i < player.getInventory().getContainerSize(); i++){
            ItemStack stack = player.getInventory().getItem(i); // the stack item that's currently being looked at
            if(!stack.isEmpty() && stack.is(Items.NETHERRACK)){
                return true;
            }
        }
        return false;
    }

    private void consumeAmmo(Player player){
        for(int i = 0; i < player.getInventory().getContainerSize(); i++){
            ItemStack stack = player.getInventory().getItem(i); // the stack item that's currently being looked at
            if(!stack.isEmpty() && stack.is(Items.NETHERRACK)){
                if (!player.getAbilities().instabuild) { // if not creative
                    stack.shrink(1);
                    if (stack.isEmpty()) {
                        player.getInventory().setItem(i, ItemStack.EMPTY); // Clear the slot if empty
                    }
                }
                return;
            }
        }
    }

    @Override
    public int getEnchantmentValue(){
        return 1;
    }

    @Override
    public boolean canBeDepleted(){
        return true;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("Consumes Netherrack"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
