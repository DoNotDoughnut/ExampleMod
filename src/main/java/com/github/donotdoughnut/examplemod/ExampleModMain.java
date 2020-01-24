package com.github.donotdoughnut.examplemod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.donotdoughnut.examplemod.items.ExampleModItemList;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExampleModMain.MOD_ID)
public class ExampleModMain {

	public static final String NAME = "Example Mod", 
							   MOD_ID = "examplemod";
	
	public static final Logger logger = LogManager.getLogger(MOD_ID);
	
	public static final ItemGroup GROUP = new ExampleModCreativeTab();
	
	public ExampleModMain() {
	
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientInit);
		
        MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void init(final FMLCommonSetupEvent event) {    	
        logger.info(NAME+" initialized.");
    }

    private void clientInit(final FMLClientSetupEvent event) {

    	logger.info(NAME+": Client initialized");
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event)
    {

    	logger.info(NAME+" server-side initialized.");
    }
	
}

class ExampleModCreativeTab extends ItemGroup {

	public ExampleModCreativeTab() {
		super(ExampleModMain.MOD_ID);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ExampleModItemList.hallowed_ingot);
	}

}
