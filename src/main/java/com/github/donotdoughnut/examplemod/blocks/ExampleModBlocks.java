package com.github.donotdoughnut.examplemod.blocks;

import static com.github.donotdoughnut.examplemod.blocks.ExampleModBlockList.*;
import static net.minecraft.block.SoundType.*;
import static net.minecraft.block.material.Material.*;

import com.github.donotdoughnut.examplemod.ExampleModMain;

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
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			event.getRegistry().registerAll(

					hallowed_block = new ExampleModBlock(IRON, METAL, "hallowed_block")
					
			);

			ExampleModMain.logger.info(ExampleModMain.NAME + ": Blocks registered.");
		}

	}

}

class ExampleModBlock extends Block {

	public ExampleModBlock(Material mat, SoundType walkSound, String registryName) {
		super(Block.Properties.create(mat).hardnessAndResistance(3.0f, 3.0f).lightValue(1).sound(walkSound));
		setRegistryName(ExampleModMain.MOD_ID, registryName);
	}

}
