package net.sharkron.variants_mod.item.custom;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GambleItem extends Item {

    public GambleItem(Properties properties) {
        super(properties);
    
    }
    List<Boolean> chance = Arrays.asList(true, false, false, false, false, false);
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        
        Random rand = new Random();
        int randomIndex = rand.nextInt(chance.size());
        Boolean result = chance.get(randomIndex);
        //chance.remove(randomIndex);
        if(result){
            PrimedTnt the_tnt = new PrimedTnt(level, player.getX(), player.getY() + player.getEyeHeight(), player.getZ(), player);
            
            the_tnt.setFuse(10);
            level.addFreshEntity(the_tnt);
            itemstack.hurtAndBreak(6, player, 
                    p -> p.broadcastBreakEvent(hand));
        }
        else{
            player.sendSystemMessage(Component.literal("*TICK*"));
            itemstack.hurtAndBreak(1, player, 
                    p -> p.broadcastBreakEvent(hand));
        }
        player.getCooldowns().addCooldown(this, 10);
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
