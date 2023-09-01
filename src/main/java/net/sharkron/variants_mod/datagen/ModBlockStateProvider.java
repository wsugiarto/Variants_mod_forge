package net.sharkron.variants_mod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.sharkron.variants_mod.VariantsMod;
import net.sharkron.variants_mod.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider{
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper){
        super(output, VariantsMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels(){
        // To register the blocks
        this.blockWithItem(ModBlocks.TOPAZ_BLOCK);
        this.blockWithItem(ModBlocks.TOPAZ_ORE);

        this.blockWithItem(ModBlocks.MORTAR_BLOCK);
        this.blockWithItem(ModBlocks.SOUND_BLOCK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
