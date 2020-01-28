package com.github.donotdoughnut.examplemod.items;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabsList.*;
import static com.github.donotdoughnut.examplemod.lists.ArmorMaterialTierList.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModBlockList.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.*;

import com.github.donotdoughnut.examplemod.items.accessories.AccessoryAglet;
import com.github.donotdoughnut.examplemod.items.accessories.AccessoryShinyRedBalloon;
import com.github.donotdoughnut.examplemod.items.materials.hallowed.HallowedArmor;
import com.github.donotdoughnut.examplemod.items.materials.hallowed.HallowedBow;
import com.github.donotdoughnut.examplemod.items.materials.hallowed.HallowedPickaxeAxe;
import com.github.donotdoughnut.examplemod.items.materials.hallowed.HallowedSword;
import com.github.donotdoughnut.examplemod.lists.MaterialTierList;

import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ExampleModItems {

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			event.getRegistry().registerAll(

					hallowed_ingot = new ExampleModItem("hallowed_ingot"),
					
					hallowed_sword = new HallowedSword(MaterialTierList.hallowed, 5, -2.4f, "hallowed_sword"), // Excalibur
					hallowed_pickaxe_axe = new HallowedPickaxeAxe(MaterialTierList.hallowed, 3, -2.8f, "hallowed_pickaxe_axe"), //Pickaxe Axe
					hallowed_bow = new HallowedBow("hallowed_bow"),
					
					hallowed_ingot_block_item = new ExampleModBlockItem(hallowed_block, "hallowed_ingot_block"),
					
					hallowed_ranged_helmet = new HallowedArmor(hallowed_ranged, EquipmentSlotType.HEAD, "hallowed_ranged_helmet"), //Hallowed Helmet
					hallowed_melee_helmet = new HallowedArmor(hallowed_melee, EquipmentSlotType.HEAD, "hallowed_melee_helmet"), // Hallowed Mask
					hallowed_magic_helmet = new HallowedArmor(hallowed_magic, EquipmentSlotType.HEAD, "hallowed_magic_helmet"), // Hallowed Headgear
					
					hallowed_chestplate = new HallowedArmor(hallowed, EquipmentSlotType.CHEST, "hallowed_chestplate"),
					hallowed_leggings = new HallowedArmor(hallowed, EquipmentSlotType.LEGS, "hallowed_leggings"),
					hallowed_boots = new HallowedArmor(hallowed, EquipmentSlotType.FEET, "hallowed_boots"),
					
					accessory_aglet = new AccessoryAglet(),
					accessory_shiny_red_balloon = new AccessoryShinyRedBalloon()
					
					);

			LOGGER.info(NAME + ": Items registered.");
		}

	}

}

class ExampleModAccessory extends Item {

	public ExampleModAccessory(String registryName) {
		super(new Item.Properties().group(GROUP_ACCESSORY));
		this.setRegistryName(MOD_ID, "accessory_"+registryName);
	}

}

class ExampleModItem extends Item {

	public ExampleModItem(String registryName) {
		super(new Item.Properties().group(GROUP_BASIC));
		this.setRegistryName(MOD_ID, registryName);
	}

}

class ExampleModBlockItem extends BlockItem {

	public ExampleModBlockItem(Block block, String registryName) {
		super(block, new Item.Properties().group(GROUP_BASIC));
		this.setRegistryName(MOD_ID, registryName);
	}

}