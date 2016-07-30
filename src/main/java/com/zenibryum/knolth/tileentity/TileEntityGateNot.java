package com.zenibryum.knolth.tileentity;

import com.zenibryum.knolth.blocks.BlockGateAnd;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileEntityGateNot extends TileEntity implements ITickable{
	public boolean canTransmit = true;
	
	public void getState(World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		if(state.getValue(BlockGateAnd.FACING) == EnumFacing.NORTH) {
			if(worldIn.getTileEntity(pos.south()) instanceof TileEntityTube) {
				TileEntityTube t = (TileEntityTube) worldIn.getTileEntity(pos.west());
				if(t.power) this.canTransmit = false;
			}
		} else if(state.getValue(BlockGateAnd.FACING) == EnumFacing.EAST) {
			if(worldIn.getTileEntity(pos.west()) instanceof TileEntityTube) {
				TileEntityTube t = (TileEntityTube) worldIn.getTileEntity(pos.west());
				if(t.power) this.canTransmit = false;
			}
		} else if(state.getValue(BlockGateAnd.FACING) == EnumFacing.SOUTH) {
			if(worldIn.getTileEntity(pos.north()) instanceof TileEntityTube) {
				TileEntityTube t = (TileEntityTube) worldIn.getTileEntity(pos.west());
				if(t.power) this.canTransmit = false;
			}
		} else if(state.getValue(BlockGateAnd.FACING) == EnumFacing.WEST) {
			if(worldIn.getTileEntity(pos.east()) instanceof TileEntityTube) {
				TileEntityTube t = (TileEntityTube) worldIn.getTileEntity(pos.west());
				if(t.power) this.canTransmit = false;
			}
		}
	}
	
	@Override
	public void update() {
		
	}
}
