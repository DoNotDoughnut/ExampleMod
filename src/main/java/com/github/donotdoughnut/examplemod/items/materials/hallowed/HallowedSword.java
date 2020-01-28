package com.github.donotdoughnut.examplemod.items.materials.hallowed;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabsList.*;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class HallowedSword extends SwordItem {

	public HallowedSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, String regName) {
		super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(GROUP_BASIC));
		setRegistryName(MOD_ID, regName);
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == hallowed_ingot;
	}

}
