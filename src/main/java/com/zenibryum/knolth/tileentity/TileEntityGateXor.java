package com.zenibryum.knolth.tileentity;

import com.zenibryum.knolth.blocks.BlockGateNot;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityGateXor extends TileEntity implements ITickable{
	@Override
	public void update() {
		
        EnumFacing facing = this.worldObj.getBlockState(this.pos).getValue(BlockGateNot.FACING);
        
        BlockPos outputPos = this.pos.add( facing.getDirectionVec() );
        BlockPos input_leftPos = this.pos.add( facing.getOpposite().rotateY().getDirectionVec() );
        BlockPos input_rightPos = this.pos.add( facing.rotateY().getDirectionVec() );
        
        //Verify the input, then propagate correct power into output
        
        boolean inputLeft = false;
        boolean inputRight = false;
        boolean correctOutput;
        
        if ( this.worldObj.getTileEntity( input_leftPos ) instanceof TileEntityTube )
        {
			TileEntityTube tInput = (TileEntityTube) this.worldObj.getTileEntity( input_leftPos );
			if ( tInput.power ) // If the neighbour is powered off.. then continue propagation
			{
				inputLeft = true;
			}
        }
        
        if ( this.worldObj.getTileEntity( input_rightPos ) instanceof TileEntityTube )
        {
			TileEntityTube tInput = (TileEntityTube) this.worldObj.getTileEntity( input_rightPos );
			if ( tInput.power ) // If the neighbour is powered off.. then continue propagation
			{
				inputRight = true;
			}
        }
        
        correctOutput = inputRight ^ inputLeft;
        
        if ( this.worldObj.getTileEntity( outputPos ) instanceof TileEntityTube )
        {
			TileEntityTube tOutput = (TileEntityTube) this.worldObj.getTileEntity( outputPos );
			if ( correctOutput != tOutput.power ) // If the neighbour is powered off.. then continue propagation
			{
				if ( correctOutput )
				{
					tOutput.power = true;
					tOutput.propagatePowerOn(facing);
				}
				else
				{
					tOutput.power = false;
					tOutput.propagatePowerOff(facing);
				}
			}
        }
        
        //this.worldObj.setBlockState( this.pos.add( facing.getDirectionVec() ), Blocks.cobblestone.getDefaultState() );
		
	}
}

