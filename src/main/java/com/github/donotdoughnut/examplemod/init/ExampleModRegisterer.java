package com.github.donotdoughnut.examplemod.init;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.api.items.types.ExampleModMaterials.ARMORTYPE.*;
import static com.github.donotdoughnut.examplemod.api.items.types.ExampleModMaterials.ITEMTYPE.*;
import static net.minecraft.block.SoundType.*;
import static net.minecraft.block.material.Material.*;
import static net.minecraft.inventory.EquipmentSlotType.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModBlockList.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.*;

import com.github.donotdoughnut.examplemod.api.ExampleModRegistries.*;
import com.github.donotdoughnut.examplemod.items.accessories.*;
import com.github.donotdoughnut.examplemod.items.materials.hallowed.HallowedArmor;
import com.github.donotdoughnut.examplemod.items.materials.hallowed.HallowedPickaxeAxe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExampleModRegisterer {
	
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

				/* Hallowed Bar Block (MaterialType, Hardness (Break Speed), Resistance (To Explosions), Light Value Emitted, Walk Sound, Name) */
				hallowed_ingot_block = new BLOCK(IRON, 3.0f, 3.0f, 3, METAL, "hallowed_ingot_block")

		);
		
		LOGGER.info(NAME + ": Blocks registered");

	}

}
