package com.zenibryum.knolth.blocks;

import net.minecraft.block.material.Material;

public class BlockGate extends BlockOrientable
{
	public BlockGate(Material materialIn, String type) {
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
}
