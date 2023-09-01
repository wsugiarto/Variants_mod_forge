package net.sharkron.variants_mod.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.sharkron.variants_mod.VariantsMod;
import net.sharkron.variants_mod.block.ModBlocks;
import net.sharkron.variants_mod.util.ModTags;

public class ModBlockTagGenerator extends BlockTagsProvider{

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, VariantsMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider){
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
            .add(ModBlocks.TOPAZ_ORE.get()).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.TOPAZ_BLOCK.get(),
                    ModBlocks.TOPAZ_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.TOPAZ_BLOCK.get(),
                    ModBlocks.TOPAZ_ORE.get());
        
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);
    }

    
}
