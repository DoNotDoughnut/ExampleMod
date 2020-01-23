package com.github.donotdoughnut.examplemod.items;

import com.github.donotdoughnut.examplemod.ExampleModMain;
import com.github.donotdoughnut.examplemod.items.hallowed.ArmorHallowed;
import com.github.donotdoughnut.examplemod.items.hallowed.Excalibur;
import com.github.donotdoughnut.examplemod.items.hallowed.PickaxeAxe;
import com.github.donotdoughnut.examplemod.items.materials.ArmorMaterialLists;
import com.github.donotdoughnut.examplemod.items.materials.MaterialTiers;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ExampleModItems {
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll (
				
				hallowed_bar = new DonutItem("hallowed_bar"),
				excalibur = new Excalibur(MaterialTiers.hallowed, 5, -2.4f),
				pickaxe_axe = new PickaxeAxe(MaterialTiers.hallowed, 3, -2.8f),
				hallowed_helmet = new ArmorHallowed(ArmorMaterialLists.hallowed, EquipmentSlotType.HEAD).setRegistryName("hallowed_helmet"),
				hallowed_mask = new ArmorHallowed(ArmorMaterialLists.hallowed, EquipmentSlotType.HEAD).setRegistryName("hallowed_mask"),
				hallowed_headgear = new ArmorHallowed(ArmorMaterialLists.hallowed, EquipmentSlotType.HEAD).setRegistryName("hallowed_headgear"),
				hallowed_chestplate = new ArmorHallowed(ArmorMaterialLists.hallowed, EquipmentSlotType.CHEST).setRegistryName("hallowed_chestplate"),
				hallowed_leggings = new ArmorHallowed(ArmorMaterialLists.hallowed, EquipmentSlotType.LEGS).setRegistryName("hallowed_leggings"),
				hallowed_boots = new ArmorHallowed(ArmorMaterialLists.hallowed, EquipmentSlotType.FEET).setRegistryName("hallowed_boots")
		);
		
		ExampleModMain.logger.info(ExampleModMain.NAME+": Items registered.");
	}
	
	}
	
	public static Item 
	hallowed_bar, 
	excalibur, 
	pickaxe_axe,
	hallowed_helmet,
	hallowed_mask,
	hallowed_headgear,
	hallowed_chestplate,
	hallowed_leggings,
	hallowed_boots,
	hallowed_repeater;

}


class DonutItem extends Item {
	
	public DonutItem(String registryName) {
		super(new Item.Properties().group(ExampleModMain.group));
		this.setRegistryName(ExampleModMain.MOD_ID, registryName);
	}
	
}

