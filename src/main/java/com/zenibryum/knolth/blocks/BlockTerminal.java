package com.zenibryum.knolth.blocks;

import com.zenibryum.knolth.Knolth;
import com.zenibryum.knolth.KnolthGuiHandler;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockTerminal extends BlockNormal{
	public BlockTerminal(Material materialIn) {
		super(materialIn);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) playerIn.openGui(Knolth.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
}
