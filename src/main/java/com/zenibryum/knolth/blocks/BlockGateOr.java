package com.zenibryum.knolth.blocks;

import com.zenibryum.knolth.tileentity.TileEntityGateOr;
import com.zenibryum.knolth.tileentity.TileEntityTube;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockGateOr extends BlockOrientable implements ITileEntityProvider
{
	public BlockGateOr(Material materialIn) {
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
		return new TileEntityGateOr();
	}
	
	@Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        //if (!worldIn.isRemote)
        {
            EnumFacing facing = worldIn.getBlockState(pos).getValue(BlockGateOr.FACING);
            
            BlockPos outputPos = pos.add( facing.getDirectionVec() );
        	
        	if ( worldIn.getTileEntity( outputPos ) instanceof TileEntityTube ){
				TileEntityTube t = (TileEntityTube) worldIn.getTileEntity( outputPos );
				t.power = false;
				t.propagatePowerOff( facing );
        	}
        }
    }
}
