package com.github.donotdoughnut.examplemod.lists;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.*;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ExampleModTabs extends ItemGroup {
	
	public static ItemGroup GROUP_BASIC = new ExampleModTabs("basic", hallowed_ingot),
			GROUP_TOOLS = new ExampleModTabs("basic", hallowed_pickaxe_axe),
			GROUP_WEAPONS = new ExampleModTabs("basic", hallowed_sword),
			GROUP_ARMOR = new ExampleModTabs("basic", hallowed_chestplate),
			GROUP_ACCESSORIES = new ExampleModTabs("basic", accessory_aglet);
	
	private Item displayItem;
	
	public ExampleModTabs(String name, Item displayItem) {
			super(MOD_ID+"_"+name);
			this.displayItem=displayItem;
		}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(displayItem);
	}

}
