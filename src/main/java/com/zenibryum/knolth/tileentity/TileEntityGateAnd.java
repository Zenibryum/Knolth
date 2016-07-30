package com.zenibryum.knolth.tileentity;

import com.zenibryum.knolth.blocks.BlockGateAnd;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileEntityGateAnd extends TileEntity implements ITickable{
	public boolean canTransmit = false;
	
	public void getState(World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		if(state.getValue(BlockGateAnd.FACING) == EnumFacing.NORTH) {
			if(worldIn.getTileEntity(pos.east()) instanceof TileEntityTube &&
			   worldIn.getTileEntity(pos.west()) instanceof TileEntityTube) {
				TileEntityTube t1 = (TileEntityTube) worldIn.getTileEntity(pos.east());
				TileEntityTube t2 = (TileEntityTube) worldIn.getTileEntity(pos.west());
				if(t1.power && t2.power) this.canTransmit = true;
			}
		} else if(state.getValue(BlockGateAnd.FACING) == EnumFacing.EAST) {
			if(worldIn.getTileEntity(pos.north()) instanceof TileEntityTube &&
			   worldIn.getTileEntity(pos.south()) instanceof TileEntityTube) {
				TileEntityTube t1 = (TileEntityTube) worldIn.getTileEntity(pos.east());
				TileEntityTube t2 = (TileEntityTube) worldIn.getTileEntity(pos.west());
				if(t1.power && t2.power) this.canTransmit = true;
			}
		} else if(state.getValue(BlockGateAnd.FACING) == EnumFacing.SOUTH) {
			if(worldIn.getTileEntity(pos.east()) instanceof TileEntityTube &&
			   worldIn.getTileEntity(pos.west()) instanceof TileEntityTube) {
				TileEntityTube t1 = (TileEntityTube) worldIn.getTileEntity(pos.east());
				TileEntityTube t2 = (TileEntityTube) worldIn.getTileEntity(pos.west());
				if(t1.power && t2.power) this.canTransmit = true;
			}
		} else if(state.getValue(BlockGateAnd.FACING) == EnumFacing.WEST) {
			if(worldIn.getTileEntity(pos.north()) instanceof TileEntityTube &&
			   worldIn.getTileEntity(pos.south()) instanceof TileEntityTube) {
				TileEntityTube t1 = (TileEntityTube) worldIn.getTileEntity(pos.east());
				TileEntityTube t2 = (TileEntityTube) worldIn.getTileEntity(pos.west());
				if(t1.power && t2.power) this.canTransmit = true;
			}
		}
	}
	
	@Override
	public void update() {
		
	}
}
