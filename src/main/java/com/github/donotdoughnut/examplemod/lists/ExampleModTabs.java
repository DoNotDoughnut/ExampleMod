package com.github.donotdoughnut.examplemod.lists;

import static com.github.donotdoughnut.examplemod.ExampleModMain.MOD_ID;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.accessory_aglet;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.hallowed_chestplate;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.hallowed_ingot;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.hallowed_pickaxe_axe;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.hallowed_sword;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ExampleModTabs {

	public static final ItemGroup 
	GROUP_BASIC = new ExampleModBasicTab(),
	GROUP_TOOLS = new ExampleModToolsTab(),
	GROUP_WEAPONS = new ExampleModWeaponsTab(),
	GROUP_ARMOR = new ExampleModArmorTab(),
	GROUP_ACCESSORIES = new ExampleModAccessoriesTab();

}

class ExampleModBasicTab extends ItemGroup {

	public ExampleModBasicTab() {
		super(MOD_ID+"_basic");
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(hallowed_ingot);
	}

}

class ExampleModToolsTab extends ItemGroup {

	public ExampleModToolsTab() {
		super(MOD_ID+"_tools");
}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(hallowed_pickaxe_axe);
	}

}

class ExampleModWeaponsTab extends ItemGroup {

	public ExampleModWeaponsTab() {
		super(MOD_ID+"_weapons");
}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(hallowed_sword);
	}

}

class ExampleModArmorTab extends ItemGroup {

	public ExampleModArmorTab() {
		super(MOD_ID+"_armor");
}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(hallowed_chestplate);
	}

}

class ExampleModAccessoriesTab extends ItemGroup {

	public ExampleModAccessoriesTab() {
		super(MOD_ID+"_accessories");
}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(accessory_aglet);
	}

}