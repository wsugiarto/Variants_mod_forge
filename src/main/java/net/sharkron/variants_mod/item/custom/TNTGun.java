package net.sharkron.variants_mod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;

import net.minecraft.world.entity.item.PrimedTnt;

public class TNTGun extends Item{

    public TNTGun(Properties pProperties){
        super(pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        int maxUseBeforeBroken = itemstack.getMaxDamage() - 1;

        if(!hasTnt(player) || itemstack.getDamageValue() >= maxUseBeforeBroken){
            return InteractionResultHolder.fail(itemstack);
        }
        else{ 
            if (!level.isClientSide) {
                Vec3 look = player.getLookAngle(); // Get the player's look vector
                double speed = 1.5; // Adjust this value to control the initial speed of the TNT

                PrimedTnt the_tnt = new PrimedTnt(level, player.getX(), player.getY() + player.getEyeHeight(), player.getZ(), player);
                the_tnt.setDeltaMovement(look.x * speed, look.y * speed, look.z * speed); // Set the initial velocity
                the_tnt.setFuse(20);

                level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS, 1.0F, 1.0F);

                level.addFreshEntity(the_tnt); // adding the entity into the world level
                consumeTnt(player);
                player.getCooldowns().addCooldown(this, 20);

                itemstack.hurtAndBreak(1, player, 
                    p -> p.broadcastBreakEvent(hand));
                    
                player.awardStat(Stats.ITEM_USED.get(this));
                player.gameEvent(GameEvent.ITEM_INTERACT_START);
            }

        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());

    }

    private boolean hasTnt(Player player){
        if(player.getAbilities().instabuild){
            return true;
        }

        for(int i = 0; i < player.getInventory().getContainerSize(); i++){
            ItemStack stack = player.getInventory().getItem(i); // the stack item that's currently being looked at
            if(!stack.isEmpty() && stack.getItem() == Items.TNT){
                return true;
            }
        }
        return false;
    }

    private void consumeTnt(Player player){
        for(int i = 0; i < player.getInventory().getContainerSize(); i++){
            ItemStack stack = player.getInventory().getItem(i); // the stack item that's currently being looked at
            if(!stack.isEmpty() && stack.getItem() == Items.TNT){
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

    public boolean isValidRepairItem(ItemStack p_41134_, ItemStack p_41135_) {
        return p_41135_.is(Items.IRON_INGOT);
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