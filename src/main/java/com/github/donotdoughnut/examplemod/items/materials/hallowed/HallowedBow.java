package com.github.donotdoughnut.examplemod.items.materials.hallowed;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabs.*;

import java.util.function.Predicate;

import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class HallowedBow extends CrossbowItem {

	public HallowedBow(String regName) {
		super(new Item.Properties().group(GROUP_BASIC).maxStackSize(1).group(ItemGroup.COMBAT).maxDamage(326));
		setRegistryName(MOD_ID, regName);
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
