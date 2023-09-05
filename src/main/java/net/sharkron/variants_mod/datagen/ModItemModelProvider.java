package net.sharkron.variants_mod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.sharkron.variants_mod.VariantsMod;
import net.sharkron.variants_mod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider{

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VariantsMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.simpleItem(ModItems.AMETHYST_SPELLBOOK);
        this.simpleItem(ModItems.CHARCOAL_STAFF);
        this.simpleItem(ModItems.DIAMOND_DAGGER);
        this.simpleItem(ModItems.DIAMOND_STAFF);
        this.simpleItem(ModItems.GOLD_DAGGER);
        this.simpleItem(ModItems.IRON_DAGGER);
        this.simpleItem(ModItems.METAL_DETECTOR);
        this.simpleItem(ModItems.NETHERITE_DAGGER);
        this.simpleItem(ModItems.NETHERITE_FORK);
        this.simpleItem(ModItems.PONDERING_ORB);
        this.simpleItem(ModItems.STONE_DAGGER);
        this.simpleItem(ModItems.TNT_GUN);
        this.simpleItem(ModItems.TOPAZ);
        this.simpleItem(ModItems.TOPAZ_STAFF);
        this.simpleItem(ModItems.TORCH_TOMB);
        this.simpleItem(ModItems.WOODEN_STAKE);
        this.simpleItem(ModItems.BLOWPIPE);
        this.simpleItem(ModItems.COPPER_STAFF);
        this.simpleItem(ModItems.STONE_LAUNCHER);
        this.simpleItem(ModItems.SPORE_STAFF);
        this.simpleItem(ModItems.REDSTONE_SPELLBOOK);
        this.simpleItem(ModItems.BOULETS);
        this.simpleItem(ModItems.HANDGUN);
        this.simpleItem(ModItems.TOPAZ_SPELLBOOK);
        this.simpleItem(ModItems.TOPAZ_HANDGUN);
        this.simpleItem(ModItems.AMETHYST_RIFLE);
        this.simpleItem(ModItems.DIAMOND_RIFLE);
        this.simpleItem(ModItems.DIAMOND_SNIPER);
        this.simpleItem(ModItems.PRISMARINE_SPELLBOOK);
        this.simpleItem(ModItems.UNIVERSAL_SPELLBOOK);
        this.simpleItem(ModItems.NETHERITE_EAGLE);
        this.simpleItem(ModItems.NETHERITE_RIFLE);
        
        this.simpleItem(ModItems.WITHER_CANNON);
        this.simpleItem(ModItems.GAMBLE_ITEM);
        this.simpleItem(ModItems.VEXX_STAFF);
        this.simpleItem(ModItems.BOUNCE_CANNON);
        this.simpleItem(ModItems.GRENADE_ITEM);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(VariantsMod.MODID,"item/" + item.getId().getPath()));
    }
    
}
