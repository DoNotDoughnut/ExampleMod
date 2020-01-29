package com.github.donotdoughnut.examplemod.init;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabs.*;
import static net.minecraft.block.SoundType.*;
import static net.minecraft.block.material.Material.*;
import static net.minecraft.inventory.EquipmentSlotType.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.github.donotdoughnut.examplemod.items.materials.ExampleModMaterials.ARMORTYPE.*;
import static com.github.donotdoughnut.examplemod.items.materials.ExampleModMaterials.ITEMTYPE.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModBlockList.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.*;

import com.github.donotdoughnut.examplemod.items.accessories.*;
import com.github.donotdoughnut.examplemod.items.materials.ExampleModMaterials.*;
import com.github.donotdoughnut.examplemod.items.materials.hallowed.HallowedArmor;
import com.github.donotdoughnut.examplemod.items.materials.hallowed.HallowedPickaxeAxe;
import com.github.donotdoughnut.examplemod.items.tools.MultitoolItem;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExampleModRegistry {
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				
				/* Hallowed Bar (Item ID) */
				hallowed_ingot = new ITEM("hallowed_ingot"),
				
				/* Excalibur (Item Type, Attack Damage, Attack Speed) */
				hallowed_sword = new SWORD(hallowed, 5, -2.4f),
				
				/* Pickaxe Axe (Attack Damage, Attack Speed, Item ID) */
				hallowed_pickaxe_axe = new HallowedPickaxeAxe(3, -2.8f, "pickaxe_axe"),

				/* Hallowed Repeater (Removed) (Item Type, ..., ..., Item ID) */
				// hallowed_bow = new CROSSBOW(ITEMTYPE.hallowed, 0, 0, "hallowed_bow"),

				/* Hallowed Bar Stack (Base block) */
				hallowed_ingot_block_item = new BLOCKITEM(hallowed_ingot_block),

				/* Hallowed Helmet (Armor type, Armor slot) */
				hallowed_ranged_helmet = new HallowedArmor(hallowed_ranged, HEAD),

				/* Hallowed Mask (Armor type, Armor slot) */
				hallowed_melee_helmet = new HallowedArmor(hallowed_melee, HEAD),

				/* Hallowed Headgear (Armor type, Armor slot) */
				hallowed_magic_helmet = new HallowedArmor(hallowed_magic, HEAD),

				/* Hallowed Plate Mail (Armor type, Armor slot) */
				hallowed_chestplate = new ARMOR(hallowed_basic, CHEST),

				/* Hallowed Leggings (Armor type, Armor slot) */
				hallowed_leggings = new ARMOR(hallowed_basic, LEGS),

				/* Hallowed Boots (Armor type, Armor slot) */
				hallowed_boots = new ARMOR(hallowed_basic, FEET),

				/* Aglet () */
				accessory_aglet = new AccessoryAglet(),

				/* Shiny Red Balloon () */
				accessory_shiny_red_balloon = new AccessoryShinyRedBalloon(),
					
				/* Obsidian Skull (Max burn time) */
				accessory_obsidian_skull = new AccessoryObsidianSkull(2)

		);
		
		LOGGER.info(NAME + ": Items registered.");
		
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {

		// DONT FORGET TO CREATE BLOCKITEM WHEN REGISTERING BLOCKS

		event.getRegistry().registerAll(

				/* Hallowed Bar Block (MaterialType, Hardness (Break Speed), Resistance (To Explosions), Light Value Emitted, Walk Sound) */
				hallowed_ingot_block = new BLOCK(IRON, 3.0f, 3.0f, 3, METAL, "hallowed_ingot_block")

		);
		
		LOGGER.info(NAME + ": Blocks registered");

	}

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
