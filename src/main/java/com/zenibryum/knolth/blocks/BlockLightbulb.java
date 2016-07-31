package com.zenibryum.knolth.blocks;

import com.zenibryum.knolth.tileentity.TileEntityLightbulb;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLightbulb extends Block implements ITileEntityProvider
{
	public boolean isOn;
	public BlockLightbulb(Material materialIn, boolean on) {
		super(materialIn);
	    //this.setLightOpacity(255);
		if ( on )
			this.setLightLevel(1.0F);
		else
			this.setLightLevel(0.0F);
		this.isOn = on;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	//This removes suffocation damage
	@Override
    public boolean isFullCube()
    {
        return false;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityLightbulb( );
	}
}
