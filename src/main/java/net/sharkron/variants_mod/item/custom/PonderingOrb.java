package net.sharkron.variants_mod.item.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.sharkron.variants_mod.item.ModItems;
import net.sharkron.variants_mod.util.ModTags;

public class PonderingOrb extends Item{
    private int life;

    public PonderingOrb(Properties pProperties){
        super(pProperties);
    }
    
    @Override
    public void inventoryTick(ItemStack hand, Level level, Entity owner, int p_41407_, boolean p_41408_) {
        this.life++;
        if(owner instanceof Player && this.life > 30){
            Player player = (Player)owner;
            ItemStack offHand = player.getItemBySlot(EquipmentSlot.OFFHAND);
            ItemStack mainHand = player.getItemBySlot(EquipmentSlot.MAINHAND);

            if(offHand.is(ModItems.PONDERING_ORB.get()) || mainHand.is(ModItems.PONDERING_ORB.get())){
                for(int i = 0; i < player.getInventory().getContainerSize(); i++){
                    ItemStack stack = player.getInventory().getItem(i); // the stack item that's currently being looked at
                    if(!stack.isEmpty() && stack.is(ModTags.Items.MAGIC_WEAPONS) && stack.getDamageValue() != 0){
                        int reduce = stack.getDamageValue() - 15;
                        if(reduce > 0){
                            stack.setDamageValue(reduce);
                        }
                        else{
                            stack.setDamageValue(0);
                        }
                    }
                }
            }
            this.life = 0;
        }
    }
}
