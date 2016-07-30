package com.zenibryum.knolth.blocks;

import com.zenibryum.knolth.tileentity.TileEntityGateAnd;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGateAnd extends BlockOrientable
{
	public BlockGateAnd(Material materialIn) {
		super(materialIn);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return true;
	}
	
	@Override
    public boolean isFullCube()
    {
        return true;
    }
	
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityGateAnd();
	}
}
