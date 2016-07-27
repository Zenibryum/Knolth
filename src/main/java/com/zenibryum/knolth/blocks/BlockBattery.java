package com.zenibryum.knolth.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBattery extends Block
{

	public BlockBattery(Material materialIn) {
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
