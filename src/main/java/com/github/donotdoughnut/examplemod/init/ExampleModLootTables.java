package com.github.donotdoughnut.examplemod.init;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExampleModLootTables {

	private static String mc = "minecraft:chests/";
	
	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent e) {
		
		String res = e.getName().toString();
		
		if(res.startsWith(mc)) {
			
			String loc = res.substring(res.indexOf(mc) + mc.length());

			switch (loc) {
			
			case "simple_dungeon": e.getTable().addPool(createPool(loc)); break;
			
			}
			
			
		}
	}

	private LootPool createPool(String resource) {
		return LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MOD_ID, resource)).weight(1)).bonusRolls(0, 1).name(MOD_ID+"_imported").build();
	}

}
