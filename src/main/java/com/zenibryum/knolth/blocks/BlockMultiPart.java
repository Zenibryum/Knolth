package com.zenibryum.knolth.blocks;

import com.zenibryum.knolth.init.KnolthBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;

public class BlockMultiPart extends Block {
	
	public BlockMultiPart() {
        super(Material.rock);
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
	
	public static void changeToWorkspace(World worldIn, BlockPos centerPos, EnumFacing.Axis axis )
	{
		IBlockState edgeState = KnolthBlocks.multi_block_edge.getDefaultState();
		IBlockState coreState = KnolthBlocks.multi_block.getDefaultState();
		
		worldIn.setBlockState( centerPos, coreState);
		
		for( EnumFacing direction : EnumFacing.HORIZONTALS )
		{
			if ( direction.getAxis() == axis )
				{
					//worldIn.setBlockState( centerPos.add(direction.getDirectionVec()), edgeState);
					worldIn.setBlockState(centerPos.add(direction.getDirectionVec()), edgeState.withProperty(BlockMultiEdge.FACING, direction.getOpposite()), 2);
				}
		}
	}
	
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
		//worldIn.destroyBlock(pos, false);
		//worldIn.setBlockState(pos, KnolthBlocks.multi_block.getDefaultState() );
		//worldIn.setBlockState(pos.east(), KnolthBlocks.multi_block.getDefaultState());
		
		IBlockState thisBlockState = this.getDefaultState();
		
		if ( worldIn.getBlockState(pos.east()) == thisBlockState &&
			 worldIn.getBlockState(pos.west()) == thisBlockState )
		{
			changeToWorkspace( worldIn, pos, EnumFacing.Axis.X );return;
		}
		if ( worldIn.getBlockState(pos.north()) == thisBlockState && 
			 worldIn.getBlockState(pos.south()) == thisBlockState)
		{
			changeToWorkspace( worldIn, pos, EnumFacing.Axis.Z );return;
		}
		
		for( EnumFacing direction : EnumFacing.HORIZONTALS )
		{
			Vec3i vector = direction.getDirectionVec();
			if ( worldIn.getBlockState( pos.add( vector ) ) == thisBlockState &&
				 worldIn.getBlockState( pos.add( new Vec3i( vector.getX()*2, vector.getY(), vector.getZ()*2 ) ) ) == thisBlockState )
				{
					changeToWorkspace ( worldIn, pos.add( direction.getDirectionVec() ), direction.getAxis() );return;
				}
		}
		
		
		//for ( EnumFacing.)
		
    }
	
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		return true;
	}
	
	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT; // TRANSLUCENT
	}
	/*
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityCoordTransporter();
	}*///implements ITileEntityProvider
	
}
