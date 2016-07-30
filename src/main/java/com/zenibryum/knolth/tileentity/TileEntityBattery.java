package com.zenibryum.knolth.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityBattery extends TileEntity implements ITickable{
	public final boolean power = true;
	
	@Override
	public void update() {
		//System.out.println("HELLO THERE - BATTERY");
		this.propagatePowerOn();
	}
	
	public void propagatePowerOn()
	{
		//System.out.println("Battery update");
		if ( this.worldObj.getTileEntity( this.pos.up() ) instanceof TileEntityTube )
		{
			TileEntityTube t = (TileEntityTube) this.worldObj.getTileEntity( this.pos.up() );
			if ( !t.power ) // If the neighbor is powered off.. then continue propagation
			{
				t.power = true;
				t.propagatePowerOn( EnumFacing.UP );
			}
		}
	}
	
	//TODO: When you destroy the block, you have to call a propagatePowerOff();
}
