package com.github.donotdoughnut.examplemod.lists;

import static com.github.donotdoughnut.examplemod.ExampleModMain.MOD_ID;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ExampleModTabsList {

	public static ItemGroup GROUP_BASIC = new ExampleModBasicTab(),
							GROUP_ACCESSORY = new ExampleModAccessoryTab();

}

class ExampleModBasicTab extends ItemGroup {

	public ExampleModBasicTab() {
		super(MOD_ID+"_basic");
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ExampleModItemList.hallowed_ingot);
	}

}

class ExampleModAccessoryTab extends ItemGroup {

	public ExampleModAccessoryTab() {
		super(MOD_ID+"_accessory");
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ExampleModItemList.accessory_aglet);
	}

}