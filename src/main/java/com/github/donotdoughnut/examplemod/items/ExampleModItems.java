package com.github.donotdoughnut.examplemod.items;

import static com.github.donotdoughnut.examplemod.items.materials.ArmorMaterialLists.*;
import static com.github.donotdoughnut.examplemod.items.ExampleModItemList.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import com.github.donotdoughnut.examplemod.ExampleModMain;

import com.github.donotdoughnut.examplemod.items.materials.MaterialTiers;
import com.google.common.collect.Sets;
import com.google.common.collect.ImmutableMap.Builder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.WorldEvents;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ExampleModItems {

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			event.getRegistry().registerAll(

					hallowed_bar = new ExampleModItem("hallowed_bar"),
					excalibur = new Excalibur(MaterialTiers.hallowed, 5, -2.4f),
					pickaxe_axe = new PickaxeAxe(MaterialTiers.hallowed, 3, -2.8f),
					hallowed_helmet = new ArmorHallowed(hallowed, EquipmentSlotType.HEAD)
							.setRegistryName("hallowed_helmet"),
					hallowed_mask = new ArmorHallowed(hallowed, EquipmentSlotType.HEAD)
							.setRegistryName("hallowed_mask"),
					hallowed_headgear = new ArmorHallowed(hallowed, EquipmentSlotType.HEAD)
							.setRegistryName("hallowed_headgear"),
					hallowed_chestplate = new ArmorHallowed(hallowed, EquipmentSlotType.CHEST)
							.setRegistryName("hallowed_chestplate"),
					hallowed_leggings = new ArmorHallowed(hallowed, EquipmentSlotType.LEGS)
							.setRegistryName("hallowed_leggings"),
					hallowed_boots = new ArmorHallowed(hallowed, EquipmentSlotType.FEET)
							.setRegistryName("hallowed_boots"));

			ExampleModMain.logger.info(ExampleModMain.NAME + ": Items registered.");
		}

	}

}

class ExampleModItem extends Item {

	public ExampleModItem(String registryName) {
		super(new Item.Properties().group(ExampleModMain.group));
		this.setRegistryName(ExampleModMain.MOD_ID, registryName);
	}

}

class ArmorHallowed extends ArmorItem {

	public ArmorHallowed(IArmorMaterial material, EquipmentSlotType slot) {
		super(material, slot, new Item.Properties().group(ExampleModMain.group));
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == hallowed_bar;
	}

}

class Excalibur extends SwordItem {

	public Excalibur(IItemTier tier, int attackDamageIn, float attackSpeedIn) {
		super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(ExampleModMain.group));
		setRegistryName(ExampleModMain.MOD_ID, "excalibur");
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == hallowed_bar;
	}

}

class PickaxeAxe extends ToolItem {

	public static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE,
			Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL,
			Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK,
			Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE,
			Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE,
			Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE,
			Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE,
			Blocks.STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB,
			Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB,
			Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE,
			Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE,
			Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS,
			Blocks.DARK_OAK_PLANKS, Blocks.BOOKSHELF, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD,
			Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, Blocks.OAK_LOG, Blocks.SPRUCE_LOG,
			Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.CHEST, Blocks.PUMPKIN,
			Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN, Blocks.MELON, Blocks.LADDER, Blocks.OAK_BUTTON,
			Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON,
			Blocks.ACACIA_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE,
			Blocks.JUNGLE_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE, Blocks.BAMBOO,
			Blocks.CACTUS, Blocks.MELON, Blocks.PUMPKIN);

	public static final Map<Block, Block> BLOCK_STRIPPING_MAP = (new Builder<Block, Block>())
			.put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG)
			.put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
			.put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
			.put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
			.put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
			.put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
			.put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).build();

	public PickaxeAxe(IItemTier tier, float attackDamageIn, float attackSpeedIn) {
		super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, new Item.Properties().group(ExampleModMain.group));
		setRegistryName(ExampleModMain.MOD_ID, "pickaxe_axe");
	}

	public boolean canHarvestBlock(BlockState blockIn) {
		int i = this.getTier().getHarvestLevel();
		return i >= blockIn.getHarvestLevel();
	}

	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK
				&& material != Material.WOOD ? super.getDestroySpeed(stack, state) : this.efficiency;
	}

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
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == hallowed_bar;
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(stack, world, list, flag);
		list.add(new StringTextComponent("\\u00A7o\\u00A79Not to be confused with a hamdrill"));
	}

}
