package com.github.donotdoughnut.examplemod.blocks;

import static com.github.donotdoughnut.examplemod.lists.ExampleModBlockList.*;
import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static net.minecraft.block.SoundType.*;
import static net.minecraft.block.material.Material.*;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ExampleModBlocks {

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {

		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			
			// DONT FORGET TO CREATE BLOCKITEM WHEN REGISTERING BLOCKS
			
			event.getRegistry().registerAll(

					hallowed_block = new ExampleModBlock(IRON, METAL, "hallowed_ingot_block")
					
			);
		}
		
		@SubscribeEvent
	    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
	    {

	    	LOGGER.info(NAME+": Blocks registered");
	    }

	}

}

class ExampleModBlock extends Block {

	public ExampleModBlock(Material mat, SoundType walkSound, String registryName) {
		super(Block.Properties.create(mat).hardnessAndResistance(3.0f, 3.0f).lightValue(1).sound(walkSound));
		this.setRegistryName(MOD_ID, registryName);
	}

}
