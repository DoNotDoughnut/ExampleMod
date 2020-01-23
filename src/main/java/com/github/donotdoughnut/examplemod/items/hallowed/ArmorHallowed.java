package com.github.donotdoughnut.examplemod.items.hallowed;

import com.github.donotdoughnut.examplemod.ExampleModMain;
import com.github.donotdoughnut.examplemod.items.ExampleModItems;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ArmorHallowed extends ArmorItem {

	public ArmorHallowed(IArmorMaterial material, EquipmentSlotType slot) {
		super(material, slot, new Item.Properties().group(ExampleModMain.group));
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ExampleModItems.hallowed_bar;
	}
	
}
