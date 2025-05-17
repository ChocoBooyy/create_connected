package com.hlysine.create_connected.content.fluidvessel;

import com.hlysine.create_connected.CCBlockEntityTypes;
import com.hlysine.create_connected.CCBlocks;
import com.simibubi.create.api.connectivity.ConnectivityHandler;
import com.simibubi.create.content.equipment.symmetryWand.SymmetryWandItem;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import net.createmod.catnip.math.VecHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidVesselItem extends BlockItem {

	public FluidVesselItem(Block p_i48527_1_, Properties p_i48527_2_) {
		super(p_i48527_1_, p_i48527_2_);
	}

	@Override
	public InteractionResult place(BlockPlaceContext ctx) {
		InteractionResult initialResult = super.place(ctx);
		if (!initialResult.consumesAction())
			return initialResult;
		tryMultiPlace(ctx);
		return initialResult;
	}

	@Override
	protected boolean updateCustomBlockEntityTag(BlockPos blockPos, Level level, Player player,
												 ItemStack itemStack, BlockState blockState) {
		MinecraftServer minecraftserver = level.getServer();
		if (minecraftserver == null)
			return false;
		CustomData blockEntityData = itemStack.get(DataComponents.BLOCK_ENTITY_DATA);
		if (blockEntityData != null) {
			CompoundTag nbt = blockEntityData.copyTag();
			nbt.remove("Luminosity");
			nbt.remove("Size");
			nbt.remove("Height");
			nbt.remove("Controller");
			nbt.remove("LastKnownPos");
			if (nbt.contains("TankContent")) {
				FluidStack fluid = FluidStack.parseOptional(minecraftserver.registryAccess(), nbt.getCompound("TankContent"));
				if (!fluid.isEmpty()) {
					fluid.setAmount(Math.min(FluidTankBlockEntity.getCapacityMultiplier(), fluid.getAmount()));
					nbt.put("TankContent", fluid.saveOptional(minecraftserver.registryAccess()));
				}
			}
			BlockEntity.addEntityType(nbt, ((IBE<?>) this.getBlock()).getBlockEntityType());
			itemStack.set(DataComponents.BLOCK_ENTITY_DATA, CustomData.of(nbt));
		}
		return super.updateCustomBlockEntityTag(blockPos, level, player, itemStack, blockState);
	}

	private void tryMultiPlace(BlockPlaceContext ctx) {
		Player player = ctx.getPlayer();
		if (player == null)
			return;
		if (player.isShiftKeyDown())
			return;
		Direction face = ctx.getClickedFace();
		if (!face.getAxis()
			.isHorizontal())
			return;
		ItemStack stack = ctx.getItemInHand();
		Level world = ctx.getLevel();
		BlockPos pos = ctx.getClickedPos();
		BlockPos placedOnPos = pos.relative(face.getOpposite());
		BlockState placedOnState = world.getBlockState(placedOnPos);

		if (!FluidVesselBlock.isVessel(placedOnState))
			return;
		if (SymmetryWandItem.presentInHotbar(player))
			return;
		boolean creative = getBlock().equals(CCBlocks.CREATIVE_FLUID_VESSEL.get());
		FluidVesselBlockEntity tankAt = ConnectivityHandler.partAt(
			creative ? CCBlockEntityTypes.CREATIVE_FLUID_VESSEL.get() : CCBlockEntityTypes.FLUID_VESSEL.get(), world, placedOnPos
		);
		if (tankAt == null)
			return;
		FluidVesselBlockEntity controllerBE = tankAt.getControllerBE();
		if (controllerBE == null)
			return;

		int width = controllerBE.getWidth();
		if (width == 1)
			return;

		int tanksToPlace = 0;
		Axis vesselAxis = placedOnState.getOptionalValue(FluidVesselBlock.AXIS).orElse(null);
		if (vesselAxis == null)
			return;
		if (face.getAxis() != vesselAxis)
			return;

		Direction vesselFacing = Direction.fromAxisAndDirection(vesselAxis, Direction.AxisDirection.POSITIVE);
		BlockPos startPos = face == vesselFacing.getOpposite()
				? controllerBE.getBlockPos().relative(vesselFacing.getOpposite())
				: controllerBE.getBlockPos().relative(vesselFacing, controllerBE.getHeight());

		if (VecHelper.getCoordinate(startPos, vesselAxis) != VecHelper.getCoordinate(pos, vesselAxis))
			return;

		for (int xOffset = 0; xOffset < width; xOffset++) {
			for (int zOffset = 0; zOffset < width; zOffset++) {
				BlockPos offsetPos = vesselAxis == Axis.X
						? startPos.offset(0, xOffset, zOffset)
						: startPos.offset(xOffset, zOffset, 0);
				BlockState blockState = world.getBlockState(offsetPos);
				if (FluidVesselBlock.isVessel(blockState))
					continue;
				if (!blockState.canBeReplaced())
					return;
				tanksToPlace++;
			}
		}

		if (!player.isCreative() && stack.getCount() < tanksToPlace)
			return;

		for (int xOffset = 0; xOffset < width; xOffset++) {
			for (int zOffset = 0; zOffset < width; zOffset++) {
				BlockPos offsetPos = vesselAxis == Axis.X
						? startPos.offset(0, xOffset, zOffset)
						: startPos.offset(xOffset, zOffset, 0);
				BlockState blockState = world.getBlockState(offsetPos);
				if (FluidVesselBlock.isVessel(blockState))
					continue;
				BlockPlaceContext context = BlockPlaceContext.at(ctx, offsetPos, face);
				player.getPersistentData()
						.putBoolean("SilenceVesselSound", true);
				super.place(context);
				player.getPersistentData().remove("SilenceVesselSound");
			}
		}
	}

}
