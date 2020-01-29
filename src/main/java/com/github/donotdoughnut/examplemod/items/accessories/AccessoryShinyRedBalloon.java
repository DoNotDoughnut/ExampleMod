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

public class AccessoryShinyRedBalloon extends com.github.donotdoughnut.examplemod.api.ExampleModRegistries.ACCESSORY {

	public AccessoryShinyRedBalloon() {
		super("shiny_red_balloon");
	}

	@Override
	public void onCurioTick(String identifier, int index, LivingEntity livingEntity) {

		if (!livingEntity.getEntityWorld().isRemote && livingEntity.ticksExisted % 80 == 0)
			livingEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 90, 1, true, false));

	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(stack, world, list, flag);
		list.add(new StringTextComponent(TextFormatting.ITALIC + "Increases jump height"));
	}

}
