package com.zenibryum.knolth.blocks;

import com.zenibryum.knolth.tileentity.TileEntityGateNot;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGateNot extends BlockOrientable
{
	public BlockGateNot(Material materialIn) {
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
		return new TileEntityGateNot();
	}
}
