package com.zenibryum.knolth.blocks;

import com.zenibryum.knolth.tileentity.TileEntityBattery;
import com.zenibryum.knolth.tileentity.TileEntityTube;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockBattery extends Block implements ITileEntityProvider
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
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        //if (!worldIn.isRemote)
        {
        	if ( worldIn.getTileEntity( pos.up() ) instanceof TileEntityTube ){
				TileEntityTube t = (TileEntityTube) worldIn.getTileEntity( pos.up() );
				t.power = false;
				t.propagatePowerOff( EnumFacing.UP );
        	}
        }
    }
	
	@Override
    public boolean isFullCube()
    {
        return true;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityBattery();
	}
}
