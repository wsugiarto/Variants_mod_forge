package net.sharkron.variants_mod.block;
import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.item.Item;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sharkron.variants_mod.VariantsMod;
import net.sharkron.variants_mod.block.custom.SoundBlock;
import net.sharkron.variants_mod.block.custom.TNTMortarBlock;
import net.sharkron.variants_mod.item.ModItems;

public class ModBlocks {
    // Deferred register
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, VariantsMod.MODID);
    
    // Creating the new blocks
    public static final RegistryObject<Block> TOPAZ_BLOCK = registerBlock("topaz_block",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).sound(SoundType.AMETHYST_CLUSTER)));
        // copying the block property of Gold Block, but using the soundtype of Amethyst Cluster
        // (can also get from ground up, ctrl click the block for template)

    // change the fortune loots
    public static final RegistryObject<Block> TOPAZ_ORE = registerBlock("topaz_ore",
        () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)
        .requiresCorrectToolForDrops().strength(3.0F), UniformInt.of(2, 5)));

    
    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block",
        () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).sound(SoundType.AMETHYST_CLUSTER)));
        
    public static final RegistryObject<Block> MORTAR_BLOCK = registerBlock("mortar_block",
        () -> new TNTMortarBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).sound(SoundType.NETHERITE_BLOCK)));
        
    // Register the block itself, AND the item associated with it
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    
    // Register item associated with a block
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    // The .register above isn't this one, but instead the one that's from the forge library
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
