package com.zenibryum.knolth.tileentity;

import com.zenibryum.knolth.blocks.BlockNormal;
import com.zenibryum.knolth.blocks.BlockOrientable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileEntityTube extends TileEntity implements ITickable {
	public EnumFacing[] dirs = new EnumFacing[6];
	
	public TileEntityTube() {
		
	}
	
	@Override
	public void update() {
		this.updateDirs();
	}
	
	public void updateDirs() {
		BlockPos position = this.getPos();
		int xCoord = position.getX();
		int yCoord = position.getY();
		int zCoord = position.getZ();
		World worldObj = this.worldObj;
		//this.worldObj.getTileEntity(position)
		
		//position.up() returns a BlockPos, offsetted 1 block up..
		if(worldObj.getTileEntity(position.up()) instanceof TileEntityTube) dirs[0] = EnumFacing.UP; else dirs[0] = null;
		if(worldObj.getTileEntity(position.down()) instanceof TileEntityTube) dirs[1] = EnumFacing.DOWN; else dirs[1] = null;
		if(worldObj.getTileEntity(position.north()) instanceof TileEntityTube) dirs[2] = EnumFacing.NORTH; else dirs[2] = null;
		if(worldObj.getTileEntity(position.south()) instanceof TileEntityTube) dirs[3] = EnumFacing.SOUTH; else dirs[3] = null;
		if(worldObj.getTileEntity(position.east()) instanceof TileEntityTube) dirs[4] = EnumFacing.EAST; else dirs[4] = null;
		if(worldObj.getTileEntity(position.west()) instanceof TileEntityTube) dirs[5] = EnumFacing.WEST; else dirs[5] = null;
	    
		if(worldObj.getBlockState(pos.up()).getBlock() instanceof BlockOrientable) dirs[0] = EnumFacing.UP;
		if(worldObj.getBlockState(pos.down()).getBlock() instanceof BlockOrientable) dirs[1] = EnumFacing.DOWN;
		if(worldObj.getBlockState(pos.north()).getBlock() instanceof BlockOrientable) dirs[2] = EnumFacing.NORTH;
		if(worldObj.getBlockState(pos.south()).getBlock() instanceof BlockOrientable) dirs[3] = EnumFacing.SOUTH;
		if(worldObj.getBlockState(pos.east()).getBlock() instanceof BlockOrientable) dirs[4] = EnumFacing.EAST;
		if(worldObj.getBlockState(pos.west()).getBlock() instanceof BlockOrientable) dirs[5] = EnumFacing.WEST;
		
		if(worldObj.getBlockState(pos.up()).getBlock() instanceof BlockNormal) dirs[0] = EnumFacing.UP;
		if(worldObj.getBlockState(pos.down()).getBlock() instanceof BlockNormal) dirs[1] = EnumFacing.DOWN;
		if(worldObj.getBlockState(pos.north()).getBlock() instanceof BlockNormal) dirs[2] = EnumFacing.NORTH;
		if(worldObj.getBlockState(pos.south()).getBlock() instanceof BlockNormal) dirs[3] = EnumFacing.SOUTH;
		if(worldObj.getBlockState(pos.east()).getBlock() instanceof BlockNormal) dirs[4] = EnumFacing.EAST;
		if(worldObj.getBlockState(pos.west()).getBlock() instanceof BlockNormal) dirs[5] = EnumFacing.WEST;
    }
	
	public boolean onlyOneOpposite()
	{
		int x,y,z,dirsAvailable;
		x=y=z=dirsAvailable=0;
		for(int i = 0; i < dirs.length; i++)
		{
			if(dirs[i] != null)
			{
				dirsAvailable++;
				x += dirs[i].getFrontOffsetX();
				y += dirs[i].getFrontOffsetY();
				z += dirs[i].getFrontOffsetZ();
			}
		}
		
		if(dirsAvailable == 2)
			if (x==0 && y==0 && z==0)
				return true;
		
		return false;
	}
	
}
