package com.github.donotdoughnut.examplemod.api.item;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabsList.*;

import net.minecraft.item.Item;

public abstract class ExampleModItem extends Item {

	public ExampleModItem(String registryName) {
		super(new Item.Properties().group(GROUP_BASIC));
		this.setRegistryName(MOD_ID, registryName);
	}

}
