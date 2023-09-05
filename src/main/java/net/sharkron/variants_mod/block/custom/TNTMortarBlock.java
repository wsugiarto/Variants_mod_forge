package net.sharkron.variants_mod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class TNTMortarBlock extends Block {

    public TNTMortarBlock(Properties pProperties) {
        super(pProperties);
    }
    
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        Vec3 look = pPlayer.getLookAngle(); // Get the player's look vector
        double speed = 2; // Adjust this value to control the initial speed of the TNT

        PrimedTnt bomb = new PrimedTnt(pLevel, pPos.getX(), pPos.getY() + 2, pPlayer.getZ(), pPlayer);
        bomb.setDeltaMovement(look.x * speed, look.y * speed + 2, look.z * speed);
        bomb.setFuse(100);
        pLevel.addFreshEntity(bomb);

      return InteractionResult.SUCCESS;
    }
    

}
