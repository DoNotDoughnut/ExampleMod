package com.github.donotdoughnut.examplemod.items.materials;

import static com.github.donotdoughnut.examplemod.items.ExampleModItemList.hallowed_ingot;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public enum MaterialTiers implements IItemTier {
	
	// Harvest Level, Durability, Efficiency, Attack Damage, Enchantability, Repair Material
	
	hallowed(3, 576, 9F, 3.5F, 13, hallowed_ingot);
	
	private Item repairMaterial;
	private int enchantability, harvestLevel, durability;
	private float efficiency;
	private float attackDamage;

	private MaterialTiers(int harvestLevel, int durability, float efficiency, float attackDamage, int enchantability, Item repairMaterial) {
		this.harvestLevel=harvestLevel;
		this.durability=durability;
		this.efficiency=efficiency;
		this.attackDamage=attackDamage;		
		this.enchantability=enchantability;
		this.repairMaterial=repairMaterial;
	}
	
	@Override
	public int getMaxUses() {
		return this.durability;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(this.repairMaterial);
	}

}
