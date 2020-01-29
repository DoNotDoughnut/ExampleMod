package com.github.donotdoughnut.examplemod.items;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;

import static com.github.donotdoughnut.examplemod.lists.ExampleModBlockList.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModMaterials.*;
import static com.github.donotdoughnut.examplemod.api.registry.ExampleModRegistry.*;

import com.github.donotdoughnut.examplemod.items.accessories.AccessoryAglet;
import com.github.donotdoughnut.examplemod.items.accessories.AccessoryShinyRedBalloon;
import com.github.donotdoughnut.examplemod.items.materials.hallowed.HallowedArmor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ExampleModInit {

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			event.getRegistry().registerAll(

					hallowed_ingot = new ITEM("hallowed_ingot"),
					
					//(hallowed, hallowed_ingot, attackDamageIn, attackSpeedIn, new Item.Properties().group(GROUP_BASIC), regName);
					hallowed_sword = new SWORD(ITEMTYPE.hallowed, 5, -2.4f), // Excalibur
					hallowed_pickaxe_axe = new MULTITOOL(ITEMTYPE.hallowed, 3, -2.8f, "pickaxe_axe", ToolType.PICKAXE, ToolType.AXE), //Pickaxe Axe
					//hallowed_bow = new CROSSBOW(ITEMTYPE.hallowed, 0, 0, "hallowed_bow"),
					
					hallowed_ingot_block_item = new BLOCKITEM(hallowed_block, "hallowed_ingot_block"),
					
					hallowed_ranged_helmet = new HallowedArmor(ARMORTYPE.hallowed_ranged, EquipmentSlotType.HEAD, "hallowed_ranged_helmet"), //Hallowed Helmet
					hallowed_melee_helmet = new HallowedArmor(ARMORTYPE.hallowed_melee, EquipmentSlotType.HEAD, "hallowed_melee_helmet"), // Hallowed Mask
					hallowed_magic_helmet = new HallowedArmor(ARMORTYPE.hallowed_magic, EquipmentSlotType.HEAD, "hallowed_magic_helmet"), // Hallowed Headgear
					
					hallowed_chestplate = new HallowedArmor(ARMORTYPE.hallowed_basic, EquipmentSlotType.CHEST, "hallowed_chestplate"),
					hallowed_leggings = new HallowedArmor(ARMORTYPE.hallowed_basic, EquipmentSlotType.LEGS, "hallowed_leggings"),
					hallowed_boots = new HallowedArmor(ARMORTYPE.hallowed_basic, EquipmentSlotType.FEET, "hallowed_boots"),
					
					accessory_aglet = new AccessoryAglet(),
					accessory_shiny_red_balloon = new AccessoryShinyRedBalloon()
					
					);

			LOGGER.info(NAME + ": Items registered.");
		}

	}

}