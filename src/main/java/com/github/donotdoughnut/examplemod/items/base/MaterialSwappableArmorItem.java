package com.github.donotdoughnut.examplemod.items.base;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;

public class MaterialSwappableArmorItem extends ArmorItem {

	protected int damageReduceAmount;
	protected float toughness;
	protected IArmorMaterial material;

	public MaterialSwappableArmorItem(IArmorMaterial material, EquipmentSlotType slot, Item.Properties builder) {
		super(material, slot, builder);
		this.material = material;
		this.toughness = material.getToughness();
		this.damageReduceAmount = material.getDamageReductionAmount(slot);
	}

	protected void setMaterial(IArmorMaterial materialIn) {
		material = materialIn;
		damageReduceAmount = materialIn.getDamageReductionAmount(slot);
		toughness = materialIn.getToughness();
	}

	@Override
	public EquipmentSlotType getEquipmentSlot() {
		return slot;
	}

	@Override
	public int getItemEnchantability() {
		return material.getEnchantability();
	}

	@Override
	public IArmorMaterial getArmorMaterial() {
		return material;
	}

	@Override
	public int getDamageReduceAmount() {
		return damageReduceAmount;
	}

	@Override
	public float getToughness() {
		return toughness;
	}

}
