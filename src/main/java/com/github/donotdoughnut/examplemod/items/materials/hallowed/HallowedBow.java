package com.github.donotdoughnut.examplemod.items.materials.hallowed;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.hallowed_ingot;

import java.util.function.Predicate;

import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class HallowedBow extends CrossbowItem {

	public HallowedBow(String regName) {
		super(new Item.Properties().group(GROUP).maxStackSize(1).group(ItemGroup.COMBAT).maxDamage(326));
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

	public Predicate<ItemStack> getAmmoPredicate() {
		return ARROWS_OR_FIREWORKS;
	}

	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return ARROWS;
	}

	public int getUseDuration(ItemStack stack) {
		return getChargeTime(stack) - 1;
	}

}
