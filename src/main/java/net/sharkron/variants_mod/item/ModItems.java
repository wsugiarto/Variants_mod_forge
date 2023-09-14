package net.sharkron.variants_mod.item;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sharkron.variants_mod.VariantsMod;
import net.sharkron.variants_mod.item.custom.AmethystRifle;
import net.sharkron.variants_mod.item.custom.AmethystSpellbook;
import net.sharkron.variants_mod.item.custom.Blowpipe;
import net.sharkron.variants_mod.item.custom.BounceCannon;
import net.sharkron.variants_mod.item.custom.CharcoalStaff;
import net.sharkron.variants_mod.item.custom.CopperStaff;
import net.sharkron.variants_mod.item.custom.DiamondRifle;
import net.sharkron.variants_mod.item.custom.DiamondSniper;
import net.sharkron.variants_mod.item.custom.DiamondStaff;
import net.sharkron.variants_mod.item.custom.GambleItem;
import net.sharkron.variants_mod.item.custom.GrenadeItem;
import net.sharkron.variants_mod.item.custom.Handgun;
import net.sharkron.variants_mod.item.custom.MetalDetectorItem;
import net.sharkron.variants_mod.item.custom.NetheriteEagle;
import net.sharkron.variants_mod.item.custom.NetheriteFork;
import net.sharkron.variants_mod.item.custom.NetheriteRifle;
import net.sharkron.variants_mod.item.custom.PonderingOrb;
import net.sharkron.variants_mod.item.custom.PrismarineSpellbook;
import net.sharkron.variants_mod.item.custom.RedstoneSpellbook;
import net.sharkron.variants_mod.item.custom.SporeStaff;
import net.sharkron.variants_mod.item.custom.StoneLauncher;
import net.sharkron.variants_mod.item.custom.TNTGun;
import net.sharkron.variants_mod.item.custom.TopazHandgun;
import net.sharkron.variants_mod.item.custom.TopazSpellbook;
import net.sharkron.variants_mod.item.custom.TopazStaff;
import net.sharkron.variants_mod.item.custom.TorchTomb;
import net.sharkron.variants_mod.item.custom.UniversalSpellbook;
import net.sharkron.variants_mod.item.custom.VexxStaff;
import net.sharkron.variants_mod.item.custom.WitherCannon;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

public class ModItems {
    // basically just registers the MODID's items (like in /give @p [MODID]:shit_block)
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VariantsMod.MODID);

    // adding the actual items
    public static final RegistryObject<Item> TOPAZ = ITEMS.register("topaz",
        () -> new Item(new Item.Properties()));
    
    // Daggers
    public static final RegistryObject<Item> WOODEN_STAKE = ITEMS.register("wooden_stake",
        () -> new SwordItem(Tiers.WOOD, 2, 5.0F, new Item.Properties()));
    public static final RegistryObject<Item> STONE_DAGGER = ITEMS.register("stone_dagger",
        () -> new SwordItem(Tiers.STONE, 2, 5.0F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_DAGGER = ITEMS.register("iron_dagger",
        () -> new SwordItem(Tiers.IRON, 2, 5.0F, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_DAGGER = ITEMS.register("gold_dagger",
        () -> new SwordItem(Tiers.GOLD, 2, 5.0F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_DAGGER = ITEMS.register("diamond_dagger",
        () -> new SwordItem(Tiers.DIAMOND, 2, 5.0F, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_DAGGER = ITEMS.register("netherite_dagger",
        () -> new SwordItem(Tiers.NETHERITE, 2, 5.0F, new Item.Properties()));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
        () -> new MetalDetectorItem(new Item.Properties().durability(64)));


    // Magic

    public static final RegistryObject<Item> PONDERING_ORB = ITEMS.register("pondering_orb",
        () -> new PonderingOrb(new Item.Properties()));
    
    public static final RegistryObject<Item> CHARCOAL_STAFF = ITEMS.register("charcoal_staff",
        () -> new CharcoalStaff(new Item.Properties().durability(100)));
    
    public static final RegistryObject<Item> TORCH_TOMB = ITEMS.register("torch_tomb",
        () -> new TorchTomb(new Item.Properties().durability(100)));
    
    public static final RegistryObject<Item> COPPER_STAFF = ITEMS.register("copper_staff",
        () -> new CopperStaff(new Item.Properties().durability(100)));
    
    public static final RegistryObject<Item> SPORE_STAFF = ITEMS.register("spore_staff",
        () -> new SporeStaff(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> REDSTONE_SPELLBOOK = ITEMS.register("redstone_spellbook",
            () -> new RedstoneSpellbook(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> TOPAZ_STAFF = ITEMS.register("topaz_staff",
        () -> new TopazStaff(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> AMETHYST_SPELLBOOK = ITEMS.register("amethyst_spellbook",
        () -> new AmethystSpellbook(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> TOPAZ_SPELLBOOK = ITEMS.register("topaz_spellbook",
        () -> new TopazSpellbook(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> DIAMOND_STAFF = ITEMS.register("diamond_staff",
        () -> new DiamondStaff(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> NETHERITE_FORK = ITEMS.register("netherite_fork",
        () -> new NetheriteFork(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> PRISMARINE_SPELLBOOK = ITEMS.register("prismarine_spellbook",
        () -> new PrismarineSpellbook(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> UNIVERSAL_SPELLBOOK = ITEMS.register("universal_spellbook",
        () -> new UniversalSpellbook(new Item.Properties().durability(100)));

    // Guns
    public static final RegistryObject<Item> BOULETS = ITEMS.register("bullet",
        () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOWPIPE = ITEMS.register("blowpipe",
        () -> new Blowpipe(new Item.Properties().durability(59)));
        
    public static final RegistryObject<Item> STONE_LAUNCHER = ITEMS.register("stone_launcher",
        () -> new StoneLauncher(new Item.Properties().durability(131)));

    public static final RegistryObject<Item> HANDGUN = ITEMS.register("handgun",
        () -> new Handgun(new Item.Properties().durability(250)));

    public static final RegistryObject<Item> TOPAZ_HANDGUN = ITEMS.register("topaz_handgun",
        () -> new TopazHandgun(new Item.Properties().durability(501)));

    public static final RegistryObject<Item> AMETHYST_RIFLE = ITEMS.register("amethyst_rifle",
        () -> new AmethystRifle(new Item.Properties().durability(501)));

    public static final RegistryObject<Item> DIAMOND_RIFLE = ITEMS.register("diamond_rifle",
        () -> new DiamondRifle(new Item.Properties().durability(1561)));

    public static final RegistryObject<Item> DIAMOND_SNIPER = ITEMS.register("diamond_sniper",
        () -> new DiamondSniper(new Item.Properties().durability(1561)));
        
    public static final RegistryObject<Item> NETHERITE_RIFLE = ITEMS.register("netherite_rifle",
        () -> new NetheriteRifle(new Item.Properties().durability(2032)));
        
    public static final RegistryObject<Item> NETHERITE_EAGLE = ITEMS.register("netherite_eagle",
        () -> new NetheriteEagle(new Item.Properties().durability(2032)));

    public static final RegistryObject<Item> TNT_GUN = ITEMS.register("tnt_gun",
        () -> new TNTGun(new Item.Properties().durability(2032)));

    //WILSON
    public static final RegistryObject<Item> WITHER_CANNON = ITEMS.register("wither_cannon",
        () -> new WitherCannon(new Item.Properties().durability(2032)));

    public static final RegistryObject<Item> VEXX_STAFF = ITEMS.register("vexx_staff",
        () -> new VexxStaff(new Item.Properties().durability(100)));
    
    public static final RegistryObject<Item> BOUNCE_CANNON = ITEMS.register("bounce_cannon",
        () -> new BounceCannon(new Item.Properties().durability(1561)));

    public static final RegistryObject<Item> GRENADE_ITEM = ITEMS.register("grenade_item",
        () -> new GrenadeItem(new Item.Properties()));
    
    public static final RegistryObject<Item> GAMBLE_ITEM = ITEMS.register("gamble_item",
        () -> new GambleItem(new Item.Properties().durability(6)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
