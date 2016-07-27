package com.zenibryum.knolth.blocks;

import com.zenibryum.knolth.tileentity.TileEntityTube;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockLaser extends Block
{
	public BlockLaser(Material materialIn) {
		super(materialIn);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
    public boolean isFullCube()
    {
        return false;
    }
	
	@Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        return new AxisAlignedBB((double)pos.getX() + this.minX, (double)pos.getY() + this.minY, (double)pos.getZ() + this.minZ, (double)pos.getX() + this.maxX, (double)pos.getY() + this.maxY, (double)pos.getZ() + this.maxZ);
    }
	
	@Override
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
		this.setBlockBounds(5F/16F, 2F/16F, 5F/16F, 11F/16F, 14F/16F, 11F/16F);	
        return new AxisAlignedBB((double)pos.getX() + this.minX, (double)pos.getY() + this.minY, (double)pos.getZ() + this.minZ, (double)pos.getX() + this.maxX, (double)pos.getY() + this.maxY, (double)pos.getZ() + this.maxZ);
    }
}
