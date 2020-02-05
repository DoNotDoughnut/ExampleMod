package com.github.donotdoughnut.examplemod.lists;

import static com.github.donotdoughnut.examplemod.lists.ExampleModBlockList.mythril_anvil;

import com.github.donotdoughnut.examplemod.block.materials.mythril.MythrilAnvilBlock.MythrilAnvilTile;

import net.minecraft.tileentity.TileEntityType;

public class ExampleModTileEntityTypeList {

	public static TileEntityType<MythrilAnvilTile> MYTHRIL_ANVIL_TILE = TileEntityType.Builder.create(MythrilAnvilTile::new, mythril_anvil).build(null);
	
	static {
		MYTHRIL_ANVIL_TILE.setRegistryName(mythril_anvil.getRegistryName());
	}
	
}
