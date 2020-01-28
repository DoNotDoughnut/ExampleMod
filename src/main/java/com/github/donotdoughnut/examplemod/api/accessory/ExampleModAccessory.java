package com.github.donotdoughnut.examplemod.api.accessory;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabsList.*;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.capability.ICurio;
import top.theillusivec4.curios.common.capability.CapCurioItem;

public abstract class ExampleModAccessory extends Item implements ICurio {

	public ExampleModAccessory(String regName) {
		super(new Item.Properties().group(GROUP_ACCESSORY));
		this.setRegistryName(MOD_ID, "accessory_"+regName);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
		return CapCurioItem.createProvider(this);
	}
	
}
