package net.sharkron.variants_mod.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.sharkron.variants_mod.VariantsMod;
import net.sharkron.variants_mod.item.ModItems;
import net.sharkron.variants_mod.util.ModTags;

public class ModItemTagGenerator extends ItemTagsProvider{

    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<Provider> p_275729_,
            CompletableFuture<TagLookup<Block>> p_275322_,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, VariantsMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider p_256380_) {
        this.tag(ModTags.Items.MAGIC_WEAPONS)
            .add(ModItems.CHARCOAL_STAFF.get())
            .add(ModItems.TORCH_TOMB.get())
            .add(ModItems.COPPER_STAFF.get())
            .add(ModItems.SPORE_STAFF.get())
            .add(ModItems.REDSTONE_SPELLBOOK.get())
            .add(ModItems.TOPAZ_STAFF.get())
            .add(ModItems.AMETHYST_SPELLBOOK.get())
            .add(ModItems.DIAMOND_STAFF.get())
            .add(ModItems.NETHERITE_FORK.get());

        
        this.tag(ModTags.Items.SEEDS)
            .add(Items.WHEAT_SEEDS)
            .add(Items.BEETROOT_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(Items.MELON_SEEDS)
            .add(Items.TORCHFLOWER_SEEDS);
    }
    
}
