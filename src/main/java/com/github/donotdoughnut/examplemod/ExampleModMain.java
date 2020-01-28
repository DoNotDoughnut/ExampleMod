package com.github.donotdoughnut.examplemod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.donotdoughnut.examplemod.lists.ExampleModItemList;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.imc.CurioIMCMessage;

@Mod(ExampleModMain.MOD_ID)
public class ExampleModMain {

	public static final String NAME = "Example Mod", 
							   MOD_ID = "examplemod";
	
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	
	public static final ItemGroup GROUP = new ExampleModCreativeTab();
	
	public ExampleModMain() {
	
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		
        MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void init(final FMLCommonSetupEvent event) {    
		
		
		
        LOGGER.info(NAME+": Initialized.");
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    	//InterModComms.sendTo("curios", CuriosAPI.IMC.REGISTER_TYPE, () -> new CurioIMCMessage("accessory").setSize(2).setEnabled(true));
    	
    	LOGGER.info(NAME+": Curios initialized");
    }
	
    private void clientInit(final FMLClientSetupEvent event) {

    	LOGGER.info(NAME+": Client initialized");
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event)
    {

    	LOGGER.info(NAME+" server-side initialized.");
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
