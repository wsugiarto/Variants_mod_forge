package net.sharkron.variants_mod;

import com.mojang.logging.LogUtils;
// import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sharkron.variants_mod.block.ModBlocks;
import net.sharkron.variants_mod.entity.ModEntity;
import net.sharkron.variants_mod.item.ModCreativeTabs;
import net.sharkron.variants_mod.item.ModItems;

import org.slf4j.Logger;

// REMEMBER, all the folders called "variants_mod" must be the same as the mod id on gradle.properties

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VariantsMod.MODID)
public class VariantsMod {
    public static final String MODID = "variants_mod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public VariantsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // registering creative tab(s)
        ModCreativeTabs.register(modEventBus);

        // registering the items
        ModItems.register(modEventBus);

        // registering blocks
        ModBlocks.register(modEventBus);

        ModEntity.register(modEventBus);

        // listeners are mandatory
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Adding a block to an already existing creative tab (or can make new tab)
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
        //     event.accept(ModItems.NIGHT_STAR);
        // }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}