package com.zenibryum.knolth.tileentity;

import com.zenibryum.knolth.init.KnolthBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityLightbulb extends TileEntity implements ITickable{
	//public boolean power;
	
	public TileEntityLightbulb( )
	{
		//this.power = on;
	}
	
	
	@Override
	public void update()
	{
		//boolean newPower = this.power;
		if ( !this.worldObj.isRemote ){
			if( this.worldObj.getBlockState(this.pos.down()).getBlock() == KnolthBlocks.tube ){//this.worldObj.getTileEntity(this.pos.down()) instanceof TileEntityTube
				TileEntityTube t = (TileEntityTube) this.worldObj.getTileEntity(this.pos.down());
				
				if( t.getPowered() )
				{
					//System.out.println("LIGHT");
					//block.setLightLevel(1.0f);
					this.worldObj.setBlockState( this.pos, KnolthBlocks.lightbulb_lit.getDefaultState(), 2);
				}
				else
				{
					//System.out.println("DARKNESS");
					this.worldObj.setBlockState( this.pos, KnolthBlocks.lightbulb.getDefaultState(), 2 );
				}
				
				//newPower = t.power;
			}
			else if ( this.worldObj.getBlockState(this.pos.down()).getBlock() == KnolthBlocks.battery )
			{
				this.worldObj.setBlockState( this.pos, KnolthBlocks.lightbulb_lit.getDefaultState(), 2 );
			}
			else
			{
				this.worldObj.setBlockState( this.pos, KnolthBlocks.lightbulb.getDefaultState(), 2 );
			}
		}
		//this.power = newPower;
	}
}