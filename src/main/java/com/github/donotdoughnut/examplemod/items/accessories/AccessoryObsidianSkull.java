package com.github.donotdoughnut.examplemod.items.accessories;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;

import java.util.List;

import com.github.donotdoughnut.examplemod.init.ExampleModRegistry.ACCESSORY;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class AccessoryObsidianSkull extends ACCESSORY {
	
	private int lastFireTimer = 0;

	private final int maxTime;
	
	public AccessoryObsidianSkull(double seconds) {
		super("obsidian_skull");
		this.maxTime = (int) (20 * seconds);
	}

	@Override
	public void onCurioTick(String identifier, int index, LivingEntity livingEntity) {
		if (!livingEntity.getEntityWorld().isRemote) {
			if (livingEntity.isBurning()) {
				if (livingEntity.getFireTimer() + (int) maxTime < lastFireTimer) {
					
					livingEntity.setFireTimer(0);
					
					lastFireTimer = 0;
					
					LOGGER.error("Fire off");
					
				} else if(livingEntity.getFireTimer() > lastFireTimer){
					lastFireTimer = livingEntity.getFireTimer();
					
				}
				LOGGER.error("Burning! Fire timer currently: " + livingEntity.getFireTimer() + ", previously: " + lastFireTimer);
			}
			
		}
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(stack, world, list, flag);
		list.add(new StringTextComponent(TextFormatting.ITALIC + "Stops you from burning after a while"));
	}

}
