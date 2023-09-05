package net.sharkron.variants_mod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sharkron.variants_mod.VariantsMod;
import net.sharkron.variants_mod.block.ModBlocks;

public class ModCreativeTabs {
    // Adding own creative tab
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create
        (Registries.CREATIVE_MODE_TAB, VariantsMod.MODID);

    // Creates a creative tab with the id "new_mod:new_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> NEW_MOD_TAB = CREATIVE_MODE_TABS.register("variants_mod_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.PONDERING_ORB.get().getDefaultInstance())
            .title(Component.translatable("creativetab.variants_mod_tab"))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.PONDERING_ORB.get()); // Add items to the tab. For your own tabs, this method is preferred over the event
                output.accept(ModItems.BLOWPIPE.get()); 
                output.accept(ModItems.STONE_LAUNCHER.get()); 
                output.accept(ModItems.HANDGUN.get()); 
                output.accept(ModItems.TOPAZ_HANDGUN.get()); 
                output.accept(ModItems.AMETHYST_RIFLE.get()); 
                output.accept(ModItems.DIAMOND_RIFLE.get()); 
                output.accept(ModItems.DIAMOND_SNIPER.get()); 
                output.accept(ModItems.TNT_GUN.get()); 
                output.accept(ModBlocks.TOPAZ_BLOCK.get());
                output.accept(ModBlocks.TOPAZ_ORE.get());
                output.accept(ModItems.WOODEN_STAKE.get());
                output.accept(ModItems.STONE_DAGGER.get());
                output.accept(ModItems.IRON_DAGGER.get());
                output.accept(ModItems.GOLD_DAGGER.get());
                output.accept(ModItems.DIAMOND_DAGGER.get());
                output.accept(ModItems.NETHERITE_DAGGER.get());
                output.accept(ModItems.TOPAZ.get());
                output.accept(ModItems.BOULETS.get());
                output.accept(ModItems.CHARCOAL_STAFF.get());
                output.accept(ModItems.TORCH_TOMB.get());
                output.accept(ModItems.COPPER_STAFF.get());
                output.accept(ModItems.REDSTONE_SPELLBOOK.get());
                output.accept(ModItems.TOPAZ_STAFF.get());
                output.accept(ModItems.TOPAZ_SPELLBOOK.get());
                output.accept(ModItems.DIAMOND_STAFF.get());
                output.accept(ModItems.NETHERITE_FORK.get());
                output.accept(ModItems.AMETHYST_SPELLBOOK.get());
                output.accept(ModItems.TOPAZ_SPELLBOOK.get());
                output.accept(ModItems.SPORE_STAFF.get());
                output.accept(ModItems.METAL_DETECTOR.get());
                
                output.accept(ModItems.WITHER_CANNON.get());
                output.accept(ModBlocks.SOUND_BLOCK.get());
                output.accept(ModBlocks.MORTAR_BLOCK.get());
            }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
