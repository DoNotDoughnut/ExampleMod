package com.github.donotdoughnut.examplemod.items.materials.hallowed;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabsList.*;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HallowedArmor extends ArmorItem {
	
	public HallowedArmor(IArmorMaterial material, EquipmentSlotType slot, String regName) {
		super(material, slot, new Item.Properties().group(GROUP_BASIC));
		this.setRegistryName(MOD_ID, regName);
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == hallowed_ingot;
	}
	
	public void onArmorTick(final ItemStack stack, final World world, final PlayerEntity player)
	{
		if(player instanceof PlayerEntity)
		{
			ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD),
					  chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST),
					  legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS),
					  feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

		    if(head.getItem() != null && chest.getItem() == hallowed_chestplate && legs.getItem() == hallowed_leggings && feet.getItem() == hallowed_boots)
		    	
		    	if(head.getItem() == hallowed_magic_helmet) {
		    		
		    		
		    	} else if(head.getItem() == hallowed_melee_helmet) {
		    		
		    		
		    		
		    	} else if(head.getItem() == hallowed_ranged_helmet) {
		    		
		    		
		    		
		    	}
		}
	}

}