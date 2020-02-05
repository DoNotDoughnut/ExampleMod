package com.github.donotdoughnut.examplemod.lists;

import static com.github.donotdoughnut.examplemod.lib.items.types.ExampleModMaterials.ARMORTYPE.*;
import static com.github.donotdoughnut.examplemod.lib.items.types.ExampleModMaterials.ITEMTYPE.hallowed;
import static com.github.donotdoughnut.examplemod.lists.ExampleModBlockList.hallowed_ingot_block;
import static com.github.donotdoughnut.examplemod.lists.ExampleModBlockList.mythril_anvil;
import static net.minecraft.inventory.EquipmentSlotType.*;

import com.github.donotdoughnut.examplemod.item.accessories.AccessoryAglet;
import com.github.donotdoughnut.examplemod.item.accessories.AccessoryObsidianSkull;
import com.github.donotdoughnut.examplemod.item.accessories.AccessoryShinyRedBalloon;
import com.github.donotdoughnut.examplemod.item.materials.hallowed.HallowedArmor;
import com.github.donotdoughnut.examplemod.item.materials.hallowed.HallowedPickaxeAxe;
import com.github.donotdoughnut.examplemod.lib.ExampleModRegistry.ARMOR;
import com.github.donotdoughnut.examplemod.lib.ExampleModRegistry.BLOCKITEM;
import com.github.donotdoughnut.examplemod.lib.ExampleModRegistry.ITEM;
import com.github.donotdoughnut.examplemod.lib.ExampleModRegistry.SWORD;

import net.minecraft.item.Item;

public class ExampleModItemList {

	public static final Item
	
	/* Mythril Anvil (Base block) */
	mythril_anvil_item = new BLOCKITEM(mythril_anvil),
	
	/* Hallowed Bar Stack (Base block) */
	hallowed_ingot_block_item = new BLOCKITEM(hallowed_ingot_block),
	
	/* Hallowed Bar (Item ID) */
	hallowed_ingot = new ITEM("hallowed_ingot"),
	
	/* Excalibur (Item Type, Attack Damage, Attack Speed) */
	hallowed_sword = new SWORD(hallowed, 5, -2.4f),
	
	/* Pickaxe Axe (Attack Damage, Attack Speed, Item ID) */
	hallowed_pickaxe_axe = new HallowedPickaxeAxe(3, -2.8f, "pickaxe_axe"),

	/* Hallowed Repeater (Removed) (Item Type, ..., ..., Item ID) */
	// hallowed_bow = new CROSSBOW(ITEMTYPE.hallowed, 0, 0, "hallowed_bow"),

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
	accessory_aglet = new AccessoryAglet("aglet"),

	/* Shiny Red Balloon () */
	accessory_shiny_red_balloon = new AccessoryShinyRedBalloon("shiny_red_balloon"),
		
	/* Obsidian Skull (Max burn time) */
	accessory_obsidian_skull = new AccessoryObsidianSkull(2, "obsidian_skull");
}
