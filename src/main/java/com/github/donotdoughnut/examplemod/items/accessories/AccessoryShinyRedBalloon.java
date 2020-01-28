package com.github.donotdoughnut.examplemod.items.accessories;

import static com.github.donotdoughnut.examplemod.ExampleModMain.*;
import static com.github.donotdoughnut.examplemod.lists.ExampleModTabsList.*;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.capability.ICurio;
import top.theillusivec4.curios.common.capability.CapCurioItem;

public class AccessoryShinyRedBalloon extends Item {

	public AccessoryShinyRedBalloon() {
		super(new Item.Properties().group(GROUP_ACCESSORY));
		this.setRegistryName(MOD_ID, "accessory_shiny_red_balloon");
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
		return CapCurioItem.createProvider(new ICurio() {

			@Override
			public void onCurioTick(String identifier, int index, LivingEntity livingEntity) {

				if (!livingEntity.getEntityWorld().isRemote && livingEntity.ticksExisted % 80 == 0)
					livingEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 90, 1, true, false));

			}

			@Override
			public boolean canRightClickEquip() {
				return true;
			}

		});

	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(stack, world, list, flag);
		list.add(new StringTextComponent(TextFormatting.ITALIC + "Increases jump height"));
	}

}
