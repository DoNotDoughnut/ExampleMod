package com.github.donotdoughnut.examplemod.lib.items.types;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.*;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ExampleModMaterials {

	public static enum ITEMTYPE implements IItemTier {
		
		// Harvest Level, Durability, Efficiency, Attack Damage, Enchantability, Repair Material
		
		hallowed("hallowed", 3, 576, 9F, 3.5F, 13, hallowed_ingot);
		
		
		
		private Item repairMaterial;
		private int enchantability, harvestLevel, durability;
		private float efficiency;
		private float attackDamage;
		private String type;

		private ITEMTYPE(String type, int harvestLevel, int durability, float efficiency, float attackDamage, int enchantability, Item repairMaterial) {
			this.type = type;
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
		
		public String getType() {
			return type;
		}

	}

	public static enum ARMORTYPE implements IArmorMaterial {
		
		// Type, Name, Durability (Is scaled later), Toughness, Enchantability, Damage Reduction Amounts, Equip Sound, Repair Item
		
		hallowed_basic("hallowed", 51, 1.0f, 15, new int[] {3, 6, 7, 3}, "item.armor.equip_generic", hallowed_ingot),
		
		hallowed_melee(hallowed_basic.getType(), "melee", 64, 1.0f, 15, new int[] {3, 6, 7, 3}, "item.armor.equip_generic", hallowed_ingot),
		hallowed_magic(hallowed_basic.getType(), "magic", 20, 1.0f, 15, new int[] {3, 6, 7, 3}, "item.armor.equip_generic", hallowed_ingot),
		hallowed_ranged(hallowed_basic.getType(), "ranged", 36, 1.0f, 15, new int[] {3, 6, 7, 3}, "item.armor.equip_generic", hallowed_ingot);
		
		
		
		private static final int[] max_damage_array = new int[] {13, 15, 16, 11};
		private String name, equipSound, type;
		private int durability, enchantability;
		private Item repairItem;
		private int[] damageReductionAmounts;
		private float toughness;
		
		private ARMORTYPE(String type, String name, int durability, float toughness, int enchantability, int[] damageReductionAmounts, String equipSound, Item repairItem) {
			this.type = type;
			this.name = name;
			this.equipSound = equipSound;
			this.durability = durability;
			this.enchantability = enchantability;
			this.repairItem = repairItem;
			this.damageReductionAmounts = damageReductionAmounts;
			this.toughness = toughness;
		}
		
		private ARMORTYPE(String type, int durability, float toughness, int enchantability, int[] damageReductionAmounts, String equipSound, Item repairItem) {
			this.type = type;
			this.name = "";
			this.equipSound = equipSound;
			this.durability = durability;
			this.enchantability = enchantability;
			this.repairItem = repairItem;
			this.damageReductionAmounts = damageReductionAmounts;
			this.toughness = toughness;
		}
		
		@Override
		public int getDurability(EquipmentSlotType slotIn) {
			return max_damage_array[slotIn.getIndex()] * this.durability;
		}

		@Override
		public int getDamageReductionAmount(EquipmentSlotType slotIn) {
			return this.damageReductionAmounts[slotIn.getIndex()];
		}

		@Override
		public int getEnchantability() {
			return this.enchantability;
		}

		@Override
		public SoundEvent getSoundEvent() {
			return new SoundEvent(new ResourceLocation(this.equipSound));
		}

		@Override
		public Ingredient getRepairMaterial() {
			return Ingredient.fromItems(this.repairItem);
		}

		@Override
		public String getName() {
			if(this.name.equals(""))
				return MOD_ID + ":" + this.type + "_armor";
			else
				return MOD_ID + ":" + this.type + "_" + this.name + "_armor";
		}

		@Override
		public float getToughness() {
			return this.toughness;
		}
		
		public String getType() {
			return type;
		}
		
		public String getVariant() {
			return name;
		}

	}
	
}
