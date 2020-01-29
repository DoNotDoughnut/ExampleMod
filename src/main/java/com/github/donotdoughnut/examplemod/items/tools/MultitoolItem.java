package com.github.donotdoughnut.examplemod.items.tools;

import java.util.Map;

import javax.annotation.Nonnull;

import com.github.donotdoughnut.examplemod.items.materials.ExampleModMaterials.ITEMTYPE;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.Constants.WorldEvents;

public class MultitoolItem extends ToolItem {

	public static final Map<Block, Block> AXE_STRIPPING_MAP = (new Builder<Block, Block>())
			.put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG)
			.put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
			.put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
			.put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
			.put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
			.put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
			.put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).build();
	
	public static final Map<Block, BlockState> SHOVEL_PATH_MAP = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH.getDefaultState()));

	public MultitoolItem(ITEMTYPE tier, float attackDamageIn, float attackSpeedIn, Properties builder, ToolType type1, ToolType type2) {
		super(attackDamageIn, attackSpeedIn, tier, Sets.newHashSet(), builder.addToolType(type1, tier.getHarvestLevel()).addToolType(type2, tier.getHarvestLevel()));
	}
	
	public MultitoolItem(ITEMTYPE tier, float attackDamageIn, float attackSpeedIn, Properties builder, ToolType type1, ToolType type2, ToolType type3) {
		super(attackDamageIn, attackSpeedIn, tier, Sets.newHashSet(), builder.addToolType(type1, tier.getHarvestLevel()).addToolType(type2, tier.getHarvestLevel()).addToolType(type3, tier.getHarvestLevel()));

	}

	public boolean canHarvestBlock(BlockState state) {
		return getToolTypes(new ItemStack(this.getItem())).stream().anyMatch(e -> state.isToolEffective(e));
	}

	@Nonnull
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {		
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		PlayerEntity player = context.getPlayer();
		BlockState blockOriginalState = world.getBlockState(blockpos);
		BlockState blockResult = null;
		
		
		// Axe

		if (getToolTypes(new ItemStack(this.getItem())).contains(ToolType.AXE)) {
			
			Block stripAbility = AXE_STRIPPING_MAP.get(blockOriginalState.getBlock());
			
			// Strip wood
			
			if(stripAbility != null) {
				world.playSound(player, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
				blockResult = stripAbility.getDefaultState().with(RotatedPillarBlock.AXIS, blockOriginalState.get(RotatedPillarBlock.AXIS));
			}
			
			
		}
		
		// Shovel
		
		if(getToolTypes(new ItemStack(this.getItem())).contains(ToolType.SHOVEL)){
			
			BlockState blockAbility = SHOVEL_PATH_MAP.get(blockOriginalState.getBlock());
			
			if (context.getFace() == Direction.DOWN)
				return ActionResultType.PASS;

			// Extinguish Campfire
			
			else if (blockOriginalState.getBlock() instanceof CampfireBlock && blockOriginalState.get(CampfireBlock.LIT)) {
				world.playEvent(null, WorldEvents.FIRE_EXTINGUISH_SOUND, blockpos, 0);
				blockResult = blockOriginalState.with(CampfireBlock.LIT, false);
			}
			
			// Create path
			
			else if (blockAbility != null && world.isAirBlock(blockpos.up())) {
	            world.playSound(player, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
	            blockResult = blockAbility;
			}
		}
		
		// Change clicked-on block

		if (blockResult == null)
			return ActionResultType.PASS;

		else if (!world.isRemote) {
			world.setBlockState(blockpos, blockResult, 11);

			if (player != null)
				context.getItem().damageItem(1, player, onBroken -> onBroken.sendBreakAnimation(context.getHand()));
		}

		return ActionResultType.SUCCESS;
	}

}
