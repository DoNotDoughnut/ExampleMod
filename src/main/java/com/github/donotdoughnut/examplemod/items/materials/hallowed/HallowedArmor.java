package com.github.donotdoughnut.examplemod.items.materials.hallowed;

import static com.github.donotdoughnut.examplemod.lists.ExampleModItemList.*;

import com.github.donotdoughnut.examplemod.api.armor.basic.BasicModArmorItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class HallowedArmor extends BasicModArmorItem {
	
	public HallowedArmor(IArmorMaterial material, EquipmentSlotType slot, String regName) {
		super(material, slot, hallowed_ingot, regName);
	}
	
	public void onArmorTick(final ItemStack stack, final World world, final PlayerEntity player)
	{
		if(player instanceof PlayerEntity && player.ticksExisted % 40 == 0)
		{
			ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD),
					  chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST),
					  legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS),
					  feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

		    if(head.getItem() != null && chest.getItem() == hallowed_chestplate && legs.getItem() == hallowed_leggings && feet.getItem() == hallowed_boots)
		    	
		    	if(head.getItem() == hallowed_magic_helmet) {
		    		
		    		player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 80, 0, true, false));
		    		
		    	} else if(head.getItem() == hallowed_melee_helmet) {
		    		
		    		player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 80, 0, true, false));
		    		
		    	} else if(head.getItem() == hallowed_ranged_helmet) {
		    		
		    		player.addPotionEffect(new EffectInstance(Effects.SPEED, 80, 0, true, false));
		    		
		    	}
		}
	}

}