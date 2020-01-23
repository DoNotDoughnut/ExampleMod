package com.github.donotdoughnut.examplemod.items.hallowed;

import com.github.donotdoughnut.examplemod.ExampleModMain;
import com.github.donotdoughnut.examplemod.items.ExampleModItems;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class Excalibur extends SwordItem {

	public Excalibur(IItemTier tier, int attackDamageIn, float attackSpeedIn) {
		super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(ExampleModMain.group));
		setRegistryName(ExampleModMain.MOD_ID, "excalibur");
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return true;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == ExampleModItems.hallowed_bar;
	}

}
