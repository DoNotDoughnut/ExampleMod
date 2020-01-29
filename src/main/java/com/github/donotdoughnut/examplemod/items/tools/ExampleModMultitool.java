package com.github.donotdoughnut.examplemod.items.tools;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import com.github.donotdoughnut.examplemod.items.materials.ExampleModMaterials.ITEMTYPE;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.Constants.WorldEvents;

public class ExampleModMultitool extends ToolItem {

	public static final Map<Block, Block> BLOCK_STRIPPING_MAP = (new Builder<Block, Block>())
			.put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG)
			.put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
			.put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
			.put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
			.put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
			.put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
			.put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).build();

	public ExampleModMultitool(ITEMTYPE tier, float attackDamageIn, float attackSpeedIn, Properties builder, ToolType type1, ToolType type2) {
		super(attackDamageIn, attackSpeedIn, tier, Sets.newHashSet(), builder.addToolType(type1, tier.getHarvestLevel()).addToolType(type2, tier.getHarvestLevel()));
	}
	
	public ExampleModMultitool(ITEMTYPE tier, float attackDamageIn, float attackSpeedIn, Properties builder, ToolType type1, ToolType type2, ToolType type3) {
		super(attackDamageIn, attackSpeedIn, tier, Sets.newHashSet(), builder.addToolType(type1, tier.getHarvestLevel()).addToolType(type2, tier.getHarvestLevel()).addToolType(type3, tier.getHarvestLevel()));

	}

	public boolean canHarvestBlock(BlockState state) {
		return getToolTypes(new ItemStack(this.getItem())).stream().anyMatch(e -> state.isToolEffective(e));
	}

	//Fix below
	
	@Nonnull
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		PlayerEntity player = context.getPlayer();
		BlockState blockstate = world.getBlockState(blockpos);
		BlockState resultToSet = null;
		Block strippedResult = BLOCK_STRIPPING_MAP.get(blockstate.getBlock());

		if (strippedResult != null) {
			world.playSound(player, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			resultToSet = strippedResult.getDefaultState().with(RotatedPillarBlock.AXIS,
					blockstate.get(RotatedPillarBlock.AXIS));
		} else {
			if (context.getFace() == Direction.DOWN)
				return ActionResultType.PASS;

			else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.get(CampfireBlock.LIT)) {
				world.playEvent(null, WorldEvents.FIRE_EXTINGUISH_SOUND, blockpos, 0);
				resultToSet = blockstate.with(CampfireBlock.LIT, false);
			}
		}

		if (resultToSet == null)
			return ActionResultType.PASS;

		if (!world.isRemote) {
			world.setBlockState(blockpos, resultToSet, 11);

			if (player != null)
				context.getItem().damageItem(1, player, onBroken -> onBroken.sendBreakAnimation(context.getHand()));
		}

		return ActionResultType.SUCCESS;
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(stack, world, list, flag);
		list.add(new StringTextComponent(TextFormatting.ITALIC + "'Not to be confused with a hamdrill'"));
	}

}
