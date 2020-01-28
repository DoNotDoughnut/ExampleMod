package com.github.donotdoughnut.examplemod.api.armor;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

public abstract class ExampleModArmor extends ArmorItem {

	public ExampleModArmor(IArmorMaterial material, EquipmentSlotType slot, Properties properties, String regName) {
		super(material, slot, properties);
		this.setRegistryName(MOD_ID, regName);
	}
	
	

}
