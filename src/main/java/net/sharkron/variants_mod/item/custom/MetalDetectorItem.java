package net.sharkron.variants_mod.item.custom;

import net.minecraft.world.entity.player.Player;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.sharkron.variants_mod.util.ModTags;

public class MetalDetectorItem extends Item{
    public MetalDetectorItem(Properties pProperties){
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()){
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();

            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++){
                // Goes down every iteration from the position clicked
                BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));

                // If the block is valuables, then send chat
                if(state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES)){
                    player.sendSystemMessage(Component.literal("Found " + I18n.get(state.getBlock().getDescriptionId()) + " at "
                    + "(" + positionClicked.below(i).getX() + ", " + positionClicked.below(i).getY() + ", "+ positionClicked.below(i).getZ() + ")"));
                    
                    foundBlock = true;
                    break;
                }
            }

            if(!foundBlock){
                player.sendSystemMessage(Component.literal("No valuable item found"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), 
            player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
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
