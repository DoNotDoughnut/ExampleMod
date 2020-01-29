package com.github.donotdoughnut.examplemod.items.materials.hallowed;

import static com.github.donotdoughnut.examplemod.api.items.types.ExampleModMaterials.ITEMTYPE.*;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class HallowedPickaxeAxe extends com.github.donotdoughnut.examplemod.api.ExampleModRegistries.MULTITOOL {
	
	

	public HallowedPickaxeAxe(float attackDamageIn, float attackSpeedIn, String registryName) {
		super(hallowed, attackDamageIn, attackSpeedIn, registryName, ToolType.PICKAXE, ToolType.AXE);
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(stack, world, list, flag);
		list.add(new StringTextComponent(TextFormatting.ITALIC + "'Not to be confused with a hamdrill'"));
	}

}
