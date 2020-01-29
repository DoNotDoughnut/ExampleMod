package com.github.donotdoughnut.examplemod.items.accessories;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class AccessoryAglet extends com.github.donotdoughnut.examplemod.api.ExampleModRegistries.ACCESSORY {

	public AccessoryAglet() {
		super("aglet");
	}

	@Override
	public void onCurioTick(String identifier, int index, LivingEntity livingEntity) {
		if (!livingEntity.getEntityWorld().isRemote && livingEntity.ticksExisted % 40 == 0)
			livingEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 80, 0, true, false));
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(stack, world, list, flag);
		list.add(new StringTextComponent(TextFormatting.ITALIC + "Increased movement speed"));
	}

}
