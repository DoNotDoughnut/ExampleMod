package com.github.donotdoughnut.examplemod.api.armor.basic;

import static com.github.donotdoughnut.examplemod.lists.ExampleModTabsList.*;

import com.github.donotdoughnut.examplemod.api.armor.ExampleModArmor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BasicModArmorItem extends ExampleModArmor {

	private Item repairItem;
	
	public BasicModArmorItem(IArmorMaterial material, EquipmentSlotType slot, Item repairItem, String regName) {
		super(material, slot, new Item.Properties().group(GROUP_BASIC), regName);
		this.repairItem = repairItem;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == repairItem;
	}

}
