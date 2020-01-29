package com.github.donotdoughnut.examplemod.api;

import static com.github.donotdoughnut.examplemod.ExampleModMain.MOD_ID;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabs.GROUP_ACCESSORIES;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabs.GROUP_ARMOR;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabs.GROUP_BASIC;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabs.GROUP_TOOLS;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabs.GROUP_WEAPONS;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.donotdoughnut.examplemod.api.items.MultitoolItem;
import com.github.donotdoughnut.examplemod.api.items.types.ExampleModMaterials.ARMORTYPE;
import com.github.donotdoughnut.examplemod.api.items.types.ExampleModMaterials.ITEMTYPE;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;

public class ExampleModRegistries {


	public static class ITEM extends Item {

		public ITEM(String regName) {
			super(new Item.Properties().group(GROUP_BASIC));
			this.setRegistryName(MOD_ID, regName);
		}

	}

	public static class BLOCK extends Block {

		public BLOCK(Material mat, float hardness, float resisitance, int lightValue, SoundType walkSound,
				String registryName) {
			super(Block.Properties.create(mat).hardnessAndResistance(3.0f, 3.0f).lightValue(1).sound(walkSound));
			this.setRegistryName(MOD_ID, registryName);
		}

	}

	public static class BLOCKITEM extends BlockItem {

		public BLOCKITEM(Block block) {
			super(block, new Item.Properties().group(GROUP_BASIC));
			this.setRegistryName(block.getRegistryName());
		}

	}

	public static class MULTITOOL extends MultitoolItem {

		public MULTITOOL(ITEMTYPE tier, float attackDamageIn, float attackSpeedIn, String registryName, ToolType type1,
				ToolType type2) {
			super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(GROUP_TOOLS), type1, type2);
			this.setRegistryName(MOD_ID, tier.getType() + "_multitool_" + registryName);
		}

		public MULTITOOL(ITEMTYPE tier, float attackDamageIn, float attackSpeedIn, String registryName, ToolType type1,
				ToolType type2, ToolType type3) {
			super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(GROUP_TOOLS), type1, type2, type3);
			this.setRegistryName(MOD_ID, tier.getType() + "_multitool_" + registryName);
		}

	}

	public static class PICKAXE extends PickaxeItem {

		public PICKAXE(ITEMTYPE tier, int attackDamageIn, float attackSpeedIn) {
			super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(GROUP_TOOLS));
			this.setRegistryName(MOD_ID, tier.getType() + "_pickaxe");
		}

	}
	
	public static class AXE extends AxeItem {

		public AXE(ITEMTYPE tier, int attackDamageIn, float attackSpeedIn) {
			super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(GROUP_TOOLS));
			this.setRegistryName(MOD_ID, tier.getType() + "_axe");
		}

	}
	
	public static class SHOVEL extends ShovelItem {

		public SHOVEL(ITEMTYPE tier, int attackDamageIn, float attackSpeedIn) {
			super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(GROUP_TOOLS));
			this.setRegistryName(MOD_ID, tier.getType() + "_shovel");
		}

	}

	public static class SWORD extends SwordItem {

		public SWORD(ITEMTYPE tier, int attackDamageIn, float attackSpeedIn) {
			super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(GROUP_WEAPONS));
			this.setRegistryName(MOD_ID, tier.getType() + "_sword");
		}

	}

	public static class ARMOR extends ArmorItem {

		public ARMOR(ARMORTYPE material, EquipmentSlotType slot) {
			super(material, slot, new Item.Properties().group(GROUP_ARMOR));

			String armorSlot = getSlotName(slot.getSlotIndex()), variant = material.getVariant();

			if (variant == null || variant == "")
				this.setRegistryName(MOD_ID, material.getType() + "_" + armorSlot);
			else
				this.setRegistryName(MOD_ID, material.getType() + "_" + variant + "_" + armorSlot);
		}

		/*
		 * public ARMOR(ARMORTYPE material, EquipmentSlotType slot, String registryName)
		 * { super(material, slot, new Item.Properties().group(GROUP_ARMOR));
		 * this.setRegistryName(MOD_ID, material.getType() + "_" +
		 * material.getBasicName() + "_armor_" + registryName); }
		 */

		private String getSlotName(int i) {
			if (i == 1)
				return "boots";
			else if (i == 2)
				return "leggings";
			else if (i == 3)
				return "chestplate";
			else if (i == 4)
				return "helmet";
			else
				return "none";
		}

	}

	public static class ACCESSORY extends Item implements ICurio {

		public ACCESSORY(String registryName) {
			super(new Item.Properties().group(GROUP_ACCESSORIES));
			this.setRegistryName(MOD_ID, "accessory_" + registryName);
		}

		@Override
		public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
			return new Provider(this);
		}

		@Override
		public boolean canRightClickEquip() {
			return true;
		}
		
		private static class Provider implements ICapabilityProvider {

			 final LazyOptional<ICurio> capability;
			
			 public Provider(ICurio curio) {
				 this.capability = LazyOptional.of(() -> curio);
			 }
			 
			 @Nonnull
			 @Override
			 public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
				 return CuriosCapability.ITEM.orEmpty(cap, capability);
			 }
			
		}

	}

	
}
