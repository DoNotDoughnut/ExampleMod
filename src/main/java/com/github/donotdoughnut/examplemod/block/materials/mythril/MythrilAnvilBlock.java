package com.github.donotdoughnut.examplemod.block.materials.mythril;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.donotdoughnut.examplemod.lib.ExampleModRegistry.BLOCK;
import com.github.donotdoughnut.examplemod.lists.ExampleModBlockList;
import com.github.donotdoughnut.examplemod.lists.ExampleModContainerTypeList;
import com.github.donotdoughnut.examplemod.lists.ExampleModItemList;
import com.github.donotdoughnut.examplemod.lists.ExampleModTileEntityTypeList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class MythrilAnvilBlock extends BLOCK {

	public MythrilAnvilBlock(String registryName) {
		super(Material.ANVIL, 3.0f, 3.0f, 1, SoundType.ANVIL, registryName);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
		if (entity != null)
			world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
	}

	private Direction getFacingFromEntity(BlockPos pos, LivingEntity entity) {
		return Direction.getFacingFromVector((float) (entity.lastTickPosX - pos.getX()), (float) (entity.lastTickPosY - pos.getY()), (float) (entity.lastTickPosZ - pos.getZ()));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.FACING);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new MythrilAnvilTile();
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
		if (!world.isRemote) {
			TileEntity tileEntity = world.getTileEntity(pos);
			if (tileEntity instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
			} else {
				throw new IllegalStateException("Our named container provider is missing!");
			}
			return ActionResultType.SUCCESS;
		}
		return super.onBlockActivated(state, world, pos, player, hand, trace);
	}

	public static class MythrilAnvilTile extends TileEntity implements INamedContainerProvider {
		
		private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

		public MythrilAnvilTile() {
			super(ExampleModTileEntityTypeList.MYTHRIL_ANVIL_TILE);
		}

		@Nullable
		@Override
		public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
			return new MythrilAnvilContainer(windowId, world, pos, playerInventory, player);
		}
		
	    @Nonnull
	    @Override
	    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
	        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
	            return handler.cast();
	        }
	        return super.getCapability(cap, side);
	    }
		
	    private IItemHandler createHandler() {
	        return new ItemStackHandler(1) {

	            @Override
	            protected void onContentsChanged(int slot) {
	                markDirty();
	            }

	            @Override
	            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
	                return stack.getItem() == ExampleModItemList.hallowed_ingot;
	            }

	            @Nonnull
	            @Override
	            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
	                if (stack.getItem() != ExampleModItemList.hallowed_ingot) {
	                    return stack;
	                }
	                return super.insertItem(slot, stack, simulate);
	            }
	        };
	    }

		@Override
		public ITextComponent getDisplayName() {
			return new StringTextComponent(getType().getRegistryName().getPath());
		}

	}

	public static class MythrilAnvilContainer extends Container {

		private TileEntity tileEntity;
		private PlayerEntity player;
		private IItemHandler playerInventory;

		public MythrilAnvilContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
			super(ExampleModContainerTypeList.MYTHRIL_ANVIL_CONTAINER_TYPE, windowId);
			
			this.tileEntity = world.getTileEntity(pos);
			this.player = player;
			this.playerInventory = new InvWrapper(playerInventory);

			tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 64, 24)));

			layoutPlayerInventorySlots(10, 70);

		}

		@Override
		public boolean canInteractWith(PlayerEntity playerIn) {
			return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), player, ExampleModBlockList.hallowed_ingot_block);
		}

		@Override
		public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
			ItemStack itemstack = ItemStack.EMPTY;
			Slot slot = this.inventorySlots.get(index);
			if (slot != null && slot.getHasStack()) {
				ItemStack stack = slot.getStack();
				itemstack = stack.copy();
				if (index == 0) {
					if (!this.mergeItemStack(stack, 1, 37, true)) {
						return ItemStack.EMPTY;
					}
					slot.onSlotChange(stack, itemstack);
				} else {
					if (stack.getItem() == Items.DIAMOND) {
						if (!this.mergeItemStack(stack, 0, 1, false)) {
							return ItemStack.EMPTY;
						}
					} else if (index < 28) {
						if (!this.mergeItemStack(stack, 28, 37, false)) {
							return ItemStack.EMPTY;
						}
					} else if (index < 37 && !this.mergeItemStack(stack, 1, 28, false)) {
						return ItemStack.EMPTY;
					}
				}

				if (stack.isEmpty()) {
					slot.putStack(ItemStack.EMPTY);
				} else {
					slot.onSlotChanged();
				}

				if (stack.getCount() == itemstack.getCount()) {
					return ItemStack.EMPTY;
				}

				slot.onTake(playerIn, stack);
			}

			return itemstack;
		}

		private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
			for (int i = 0; i < amount; i++) {
				addSlot(new SlotItemHandler(handler, index, x, y));
				x += dx;
				index++;
			}
			return index;
		}

		private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
			for (int j = 0; j < verAmount; j++) {
				index = addSlotRange(handler, index, x, y, horAmount, dx);
				y += dy;
			}
			return index;
		}

		private void layoutPlayerInventorySlots(int leftCol, int topRow) {
			// Player inventory
			addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

			// Hotbar
			topRow += 58;
			addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
		}

	}

	public class MythrilAnvilScreen extends ContainerScreen<MythrilAnvilContainer> {

		public MythrilAnvilScreen(MythrilAnvilContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
			super(screenContainer, inv, titleIn);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		}

	}

}
