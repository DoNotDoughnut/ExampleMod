package com.github.donotdoughnut.examplemod.api.registry;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabs.*;

import com.github.donotdoughnut.examplemod.api.tool.ExampleModMultitool;
import com.github.donotdoughnut.examplemod.lists.ExampleModMaterials.*;

import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.capability.ICurio;
import top.theillusivec4.curios.common.capability.CapCurioItem;

public class ExampleModRegistry {

	public static class ITEM extends Item {

		public ITEM(String regName) {
			super(new Item.Properties().group(GROUP_BASIC));
			this.setRegistryName(MOD_ID, regName);
		}

	}

	public static class BLOCKITEM extends BlockItem {

		public BLOCKITEM(Block block, String registryName) {
			super(block, new Item.Properties().group(GROUP_BASIC));
			this.setRegistryName(MOD_ID, "item_" + registryName);
		}

	}
	
	public static class MULTITOOL extends ExampleModMultitool {

		public MULTITOOL(IItemTier tier, float attackDamageIn, float attackSpeedIn, String regName, ToolType type1, ToolType type2) {
			super(tier, attackDamageIn, attackSpeedIn, regName, type1, type2);
		}
		
		public MULTITOOL(IItemTier tier, float attackDamageIn, float attackSpeedIn, String regName, ToolType type1, ToolType type2, ToolType type3) {
			super(tier, attackDamageIn, attackSpeedIn, regName, type1, type2, type3);
		}

	}

	public static class PICKAXE extends PickaxeItem {

		public PICKAXE(ITEMTYPE tier, int attackDamageIn, float attackSpeedIn) {
			super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(GROUP_TOOLS));
			this.setRegistryName(MOD_ID, "pickaxe_" + tier.getType());
		}

	}

	public static class SWORD extends SwordItem {

		public SWORD(ITEMTYPE tier, int attackDamageIn, float attackSpeedIn) {
			super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(GROUP_WEAPONS));
			this.setRegistryName(MOD_ID, "sword_" + tier.getType());
		}

	}

	public static class ARMOR extends ArmorItem {

		public ARMOR(ARMORTYPE material, EquipmentSlotType slot) {
			super(material, slot, new Item.Properties().group(GROUP_ARMOR));

			String armorSlot = "none";

			if (slot.getSlotIndex() == 1)
				armorSlot = "boots";
			else if (slot.getSlotIndex() == 2)
				armorSlot = "leggings";
			else if (slot.getSlotIndex() == 3)
				armorSlot = "chestplate";
			else if (slot.getSlotIndex() == 4)
				armorSlot = "helmet";

			this.setRegistryName(MOD_ID, "armor_" + material.getType() + "_" + armorSlot);
		}

		public ARMOR(ARMORTYPE material, EquipmentSlotType slot, String registryName) {
			super(material, slot, new Item.Properties().group(GROUP_ARMOR));
			this.setRegistryName(MOD_ID, "armor_" + material.getType() + "_" + registryName);
		}

	}

	public static class ACCESSORIES extends Item implements ICurio {

		public ACCESSORIES(String registryName) {
			super(new Item.Properties().group(GROUP_ACCESSORIES));
			this.setRegistryName(MOD_ID, "accessory_"+registryName);
		}

		@Override
		public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
			return CapCurioItem.createProvider(this);
		}

	}

}
