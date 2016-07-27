package com.zenibryum.knolth.blocks;

import com.zenibryum.knolth.tileentity.TileEntityTube;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;

public class BlockTube extends BlockContainer{
	public BlockTube() {
		super(Material.rock);
		this.useNeighborBrightness = true;
	}

	// UP=0, DOWN=1, NORTH=2, SOUTH=3, EAST=4, WEST=5
	
	/*
	     DOWN(0, 1, -1, "down", EnumFacing.AxisDirection.NEGATIVE, EnumFacing.Axis.Y, new Vec3i(0, -1, 0)),
    UP(1, 0, -1, "up", EnumFacing.AxisDirection.POSITIVE, EnumFacing.Axis.Y, new Vec3i(0, 1, 0)),
    NORTH(2, 3, 2, "north", EnumFacing.AxisDirection.NEGATIVE, EnumFacing.Axis.Z, new Vec3i(0, 0, -1)),
    SOUTH(3, 2, 0, "south", EnumFacing.AxisDirection.POSITIVE, EnumFacing.Axis.Z, new Vec3i(0, 0, 1)),
    WEST(4, 5, 1, "west", EnumFacing.AxisDirection.NEGATIVE, EnumFacing.Axis.X, new Vec3i(-1, 0, 0)),
    EAST(5, 4, 3, "east", EnumFacing.AxisDirection.POSITIVE, EnumFacing.Axis.X, new Vec3i(1, 0, 0));
    */
	
	@Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
		/*
    	TileEntityTube tube = (TileEntityTube)worldIn.getTileEntity(pos);
		float t = 11F/32F;
		float xmax,ymax,zmax,xmin,ymin,zmin;
		xmax=ymax=zmax=1-t;
		xmin=ymin=zmin=t;
		
		
		xmin -= tube.dirs[5]!=null?t:0;//west
		xmax += tube.dirs[4]!=null?t:0;//east
		
		ymin -= tube.dirs[1]!=null?t:0;//down
		ymax += tube.dirs[0]!=null?t:0;//up
		
		zmin -= tube.dirs[2]!=null?t:0;//north
		zmax += tube.dirs[3]!=null?t:0;//south
		
		
		this.setBlockBounds(xmin,ymin,zmin,xmax,ymax,zmax);*/
        return new AxisAlignedBB((double)pos.getX() + this.minX, (double)pos.getY() + this.minY, (double)pos.getZ() + this.minZ, (double)pos.getX() + this.maxX, (double)pos.getY() + this.maxY, (double)pos.getZ() + this.maxZ);
    }
	
	@Override
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
    	TileEntityTube tube = (TileEntityTube)worldIn.getTileEntity(pos);
		float t = 11F/32F;
		float xmax,ymax,zmax,xmin,ymin,zmin;
		xmax=ymax=zmax=1-t;
		xmin=ymin=zmin=t;
		
		
		xmin -= tube.dirs[5]!=null?t:0;//west
		xmax += tube.dirs[4]!=null?t:0;//east
		
		ymin -= tube.dirs[1]!=null?t:0;//down
		ymax += tube.dirs[0]!=null?t:0;//up
		
		zmin -= tube.dirs[2]!=null?t:0;//north
		zmax += tube.dirs[3]!=null?t:0;//south
		
		
		this.setBlockBounds(xmin,ymin,zmin,xmax,ymax,zmax);
    	
        return new AxisAlignedBB((double)pos.getX() + this.minX, (double)pos.getY() + this.minY, (double)pos.getZ() + this.minZ, (double)pos.getX() + this.maxX, (double)pos.getY() + this.maxY, (double)pos.getZ() + this.maxZ);
    }
	
	public int getRenderType() {
		return -1;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityTube();
	}
}
