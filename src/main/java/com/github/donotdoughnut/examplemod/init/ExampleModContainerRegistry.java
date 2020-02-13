package com.github.donotdoughnut.examplemod.init;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;

import com.github.donotdoughnut.examplemod.block.materials.mythril.MythrilAnvilBlock.MythrilAnvilContainer;
import com.github.donotdoughnut.examplemod.lists.ExampleModBlockList;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExampleModContainerRegistry {
	
	@SubscribeEvent
	public static void registerContainers(RegistryEvent.Register<ContainerType<?>> event) {
		
		event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> { 
			
			BlockPos pos = data.readBlockPos();
			
			
			return new MythrilAnvilContainer(windowId, proxy.getClientWorld(), pos, inv, inv.player); }).setRegistryName(ExampleModBlockList.mythril_anvil.getRegistryName()));
		
		LOGGER.info(NAME + ": Containers registered");
	}
	
}
