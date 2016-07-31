package com.zenibryum.knolth.blocks;

import com.zenibryum.knolth.tileentity.TileEntityGateNot;
import com.zenibryum.knolth.tileentity.TileEntityTube;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockGateNot extends BlockOrientable implements ITileEntityProvider
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
	
	@Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        //if (!worldIn.isRemote)
        {
            EnumFacing facing = worldIn.getBlockState(pos).getValue(BlockGateNot.FACING);
            
            BlockPos outputPos = pos.add( facing.getDirectionVec() );
        	
        	if ( worldIn.getTileEntity( outputPos ) instanceof TileEntityTube ){
				TileEntityTube t = (TileEntityTube) worldIn.getTileEntity( outputPos );
				t.power = false;
				t.propagatePowerOff( facing );
        	}
        }
    }
	
	/*
	//TODO : Remove this debug function :D
    @Override
    public boolean onBlockActivated(
          World parWorld, 
          BlockPos parBlockPos, 
          IBlockState parIBlockState, 
          EntityPlayer parPlayer, 
          EnumFacing parSide, 
          float hitX, 
          float hitY, 
          float hitZ)
    {
        EnumFacing facing = parIBlockState.getValue(FACING);
        
        parWorld.setBlockState( parBlockPos.add( facing.getDirectionVec() ), Blocks.cobblestone.getDefaultState() );
        
        return false;
    }*/
}
