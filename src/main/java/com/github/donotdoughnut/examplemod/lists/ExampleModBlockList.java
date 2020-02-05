package com.github.donotdoughnut.examplemod.lists;

import static net.minecraft.block.SoundType.METAL;
import static net.minecraft.block.material.Material.IRON;

import com.github.donotdoughnut.examplemod.block.materials.mythril.MythrilAnvilBlock;
import com.github.donotdoughnut.examplemod.lib.ExampleModRegistry.BLOCK;

import net.minecraft.block.Block;

public class ExampleModBlockList {

	// DO NOT FORGET TO CREATE BLOCKITEM IN ITEMLIST CLASS
	
	public static final Block 
	
	/* Hallowed Bar Block (MaterialType, Hardness (Break Speed), Resistance (To Explosions), Light Value Emitted, Walk Sound, Name) */
	hallowed_ingot_block = new BLOCK(IRON, 3.0f, 3.0f, 3, METAL, "hallowed_ingot_block"),
	
	/* */
	mythril_anvil = new MythrilAnvilBlock("mythril_anvil");
	
}
