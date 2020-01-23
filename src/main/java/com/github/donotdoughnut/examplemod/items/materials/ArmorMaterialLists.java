package com.github.donotdoughnut.examplemod.items.materials;

import com.github.donotdoughnut.examplemod.ExampleModMain;
import static com.github.donotdoughnut.examplemod.items.ExampleModItemList.hallowed_bar;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public enum ArmorMaterialLists implements IArmorMaterial {
	
	// Name, Durability (Is scaled later), Toughness, Enchantability, Damage Reduction Amounts, Equip Sound, Repair Item
	
	hallowed("armor_hallowed", 51, 1.0f, 15, new int[] {3, 6, 7, 3}, "item.armor.equip_generic", hallowed_bar);	
	
	private static final int[] max_damage_array = new int[] {13, 15, 16, 11};
	private String name, equipSound;
	private int durability, enchantability;
	private Item repairItem;
	private int[] damageReductionAmounts;
	private float toughness;
	
	private ArmorMaterialLists(String name, int durability, float toughness, int enchantability, int[] damageReductionAmounts, String equipSound, Item repairItem) {
		this.name = name;
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
		return ExampleModMain.MOD_ID + this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

}
