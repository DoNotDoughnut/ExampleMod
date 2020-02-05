package com.github.donotdoughnut.examplemod.lists;

import static com.github.donotdoughnut.examplemod.ExampleModMain.proxy;

import com.github.donotdoughnut.examplemod.block.materials.mythril.MythrilAnvilBlock.MythrilAnvilContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;

public class ExampleModContainerTypeList {

	public static ContainerType<MythrilAnvilContainer> MYTHRIL_ANVIL_CONTAINER_TYPE = IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new MythrilAnvilContainer(windowId, proxy.getClientWorld(), pos, inv, proxy.getClientPlayer());
    });
	
	static {
		MYTHRIL_ANVIL_CONTAINER_TYPE.setRegistryName(ExampleModTileEntityTypeList.MYTHRIL_ANVIL_TILE.getRegistryName());
	}
	
}
