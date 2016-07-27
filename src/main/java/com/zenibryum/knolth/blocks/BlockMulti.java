package com.zenibryum.knolth.blocks;

import com.zenibryum.knolth.Knolth;
import com.zenibryum.knolth.KnolthGuiHandler;
import com.zenibryum.knolth.tileentity.TileEntityCentrifuge;
import com.zenibryum.knolth.tileentity.TileEntityMulti;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockMulti extends BlockNormal{
	public BlockMulti(Material materialIn) {
		super(materialIn);
	}
	
	/*@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) playerIn.openGui(Knolth.instance, KnolthGuiHandler.KNOLTH_TILE_ENTITY_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}*/
	
	//    public TileEntity getTileEntity(BlockPos pos)
	
	@Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		System.out.println("BLOCK PLACED - MULTI");
        return this.getStateFromMeta(meta);
    }
	
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
     // DEBUG
     System.out.println("BlockMulti createNewTileEntity()");
        return new TileEntityMulti();
    }
}