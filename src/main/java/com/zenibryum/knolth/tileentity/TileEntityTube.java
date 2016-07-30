package com.zenibryum.knolth.tileentity;

import com.zenibryum.knolth.blocks.BlockBattery;
import com.zenibryum.knolth.blocks.BlockGateAnd;
import com.zenibryum.knolth.blocks.BlockGateNot;
import com.zenibryum.knolth.blocks.BlockGateOr;
import com.zenibryum.knolth.blocks.BlockGateXor;
import com.zenibryum.knolth.init.KnolthBlocks;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;

public class TileEntityTube extends TileEntity implements ITickable {
	public EnumFacing[] dirs = new EnumFacing[6];
	public boolean power = false;
	//public boolean isWaiting = false;
	//public boolean foundSource = false;
	
	public TileEntityTube() {
		
	}
	
	public boolean getPowered()
	{
		return power;
	}
	
	@Override
	public void update() {
		this.updateDirs();
		//this.processActivate(this.worldObj, this.pos);
	}
	
	public void propagatePowerOn(EnumFacing providerDirection)
	{
		System.out.println("Propagate ON");
		for ( EnumFacing currentDirection : EnumFacing.VALUES )
		{
			if ( currentDirection == providerDirection.getOpposite() ) // Don't propagate backwards
			{
				continue;
			}
			
			Vec3i directionVector = currentDirection.getDirectionVec();
			BlockPos neighbourPosition = this.pos.add(directionVector);
			
			if( this.worldObj.getTileEntity( neighbourPosition ) instanceof TileEntityTube ){
				TileEntityTube t = (TileEntityTube) this.worldObj.getTileEntity( neighbourPosition );
				if ( !t.power ) // If the neighbour is powered off.. then continue propagation
				{
					t.power = true;
					t.propagatePowerOn( currentDirection );
				}
			}
		}
	}
	
	public void propagatePowerOff(EnumFacing providerDirection)
	{
		//System.out.println("Propagate OFF");
		for ( EnumFacing currentDirection : EnumFacing.VALUES )
		{
			if ( currentDirection == providerDirection.getOpposite() ) // Don't propagate backwards
			{
				continue;
			}
			
			Vec3i directionVector = currentDirection.getDirectionVec();
			BlockPos neighbourPosition = this.pos.add(directionVector);
			
			if( this.worldObj.getTileEntity( neighbourPosition ) instanceof TileEntityTube ){
				TileEntityTube t = (TileEntityTube) this.worldObj.getTileEntity( neighbourPosition );
				if ( t.power ) // If the neighbour is powered on.. then continue propagation
				{
					t.power = false;
					t.propagatePowerOff( currentDirection );
				}
			}
		}
	}
	
	public void onTubeDestroyed()
	{
		//System.out.println("ONTUBEDESTROYED");
		
		//if ( !this.worldObj.isRemote )
		
		if ( this.power )
		{
			for ( EnumFacing currentDirection : EnumFacing.VALUES )
			{
				Vec3i directionVector = currentDirection.getDirectionVec();
				BlockPos neighbourPosition = this.pos.add(directionVector);
				
				if( this.worldObj.getTileEntity( neighbourPosition ) instanceof TileEntityTube ){
					TileEntityTube t = (TileEntityTube) this.worldObj.getTileEntity( neighbourPosition );
					if ( t.power ) // If the neighbour is powered off.. then continue propagation
					{
						t.power = false;
						t.propagatePowerOff( currentDirection );
					}
				}
			}
		}
	}
	
	public void onTubeAdded()
	{
		//System.out.println("ONTUBEADDED");
		//this.worldObj.setBlockState( this.pos, KnolthBlocks.lightbulb_lit.getDefaultState() );
		
		//CHECK IF NEARBY TUBES ARE ON... SO THIS GETS ON...
		for ( EnumFacing currentDirection : EnumFacing.VALUES )
		{
			Vec3i directionVector = currentDirection.getDirectionVec();
			BlockPos neighbourPosition = this.pos.add(directionVector);
			
			if( this.worldObj.getTileEntity( neighbourPosition ) instanceof TileEntityTube ){
				TileEntityTube t = (TileEntityTube) this.worldObj.getTileEntity( neighbourPosition );
				if ( t.power ) // If the neighbour is powered on, then this is powered on.
				{
					this.power = true;
					break;
				}
			}
		}
		
		//Now propagate if this block is powered
		
		if ( this.power )
		{
			for ( EnumFacing currentDirection : EnumFacing.VALUES )
			{
				Vec3i directionVector = currentDirection.getDirectionVec();
				BlockPos neighbourPosition = this.pos.add(directionVector);
				
				if( this.worldObj.getTileEntity( neighbourPosition ) instanceof TileEntityTube ){
					TileEntityTube t = (TileEntityTube) this.worldObj.getTileEntity( neighbourPosition );
					if ( !t.power ) // If the neighbour is powered off.. then continue propagation
					{
						t.power = true;
						t.propagatePowerOn( currentDirection );
					}
				}
			}
		}
		
	}
	/*
	public void processActivate(World worldIn, BlockPos pos) {
		
		 * Clase noi
		 * 
		 * Blocks
		 * 
		 * BlockGate (pt fiecare gate)
		 * BlockLightbulb
		 * 
		 * TileEntity
		 * 
		 * TileEntityBattery
		 * TileEntityGate (pt fiecare gate)
		 * TileEntityLightbulb
		 * 
		 * In functia asta decid cablurile valoarea lui power
		 * Pentru a opri buclele in functie folosesc isWaiting
		 * Pentru a le spune cablurilor ca, daca power e true, nu e de la o stare anterioara folosesc foundSource
		 * Sunt 6 siruri principale de if/elseuri ce verifica blocurile alaturate
		 * Primele doua verifica pe verticala conexiunea cu cablurile si cu bateriile
		 * Ultimile 4 verifica conexiunea cu outputul de la gateuri
		 * Inputurile de la bec si de la gateuri isi testeaza singure conexiunile in functiile lor update
		 * FYI nu am testat nimic inca, mio fost prea frica :))))
		 * E posibil sa nu se vada prea bine becul din cauza texturilor
		 * Daca ti se pare ca trebuie modificate, texturile se numesc light_base, _bottom si _side
		 * Becul nu isi schimba textura sau altceva, numai isi schimba luminozitatea
		 * Inca ceva, initial scrisesem in cazul in care sunt doua cabluri conectate if(t.power && !t.isWaiting) in loc de
		 * if(!t.isWaiting)
		 * Acum mi se pare nefolositoare prima conditie, dar e posibil sa fi gandit altfel inainte
		 
		if(worldIn.getTileEntity(pos.up()) instanceof TileEntityTube){
			TileEntityTube t = (TileEntityTube) worldIn.getTileEntity(pos.up());
			this.isWaiting = true;
			if(!t.isWaiting) processActivate(worldIn, pos.up());
			this.foundSource = t.foundSource;
			this.isWaiting = false;
			if(t.foundSource) this.power = t.power;
		} else if(worldIn.getTileEntity(pos.up()) instanceof TileEntityBattery) {
			TileEntityBattery t = (TileEntityBattery) worldIn.getTileEntity(pos.up());
			this.power = t.power;
			this.foundSource = true;
		}
		
		if(worldIn.getTileEntity(pos.down()) instanceof TileEntityTube){
			TileEntityTube t = (TileEntityTube) worldIn.getTileEntity(pos.down());
			this.isWaiting = true;
			if(!t.isWaiting) processActivate(worldIn, pos.down());
			this.foundSource = t.foundSource;
			this.isWaiting = false;
			if(t.foundSource) this.power = t.power;
		} else if(worldIn.getTileEntity(pos.down()) instanceof TileEntityBattery) {
			TileEntityBattery t = (TileEntityBattery) worldIn.getTileEntity(pos.down());
			this.power = t.power;
			this.foundSource = true;
		}
		
		if(worldIn.getTileEntity(pos.north()) instanceof TileEntityTube){
			TileEntityTube t = (TileEntityTube) worldIn.getTileEntity(pos.north());
			this.isWaiting = true;
			if(!t.isWaiting) processActivate(worldIn, pos.north());
			this.foundSource = t.foundSource;
			this.isWaiting = false;
			if(t.foundSource) this.power = t.power;
		} else if(worldIn.getTileEntity(pos.north()) instanceof TileEntityGateAnd) {
			TileEntityGateAnd t = (TileEntityGateAnd) worldIn.getTileEntity(pos.north());
			IBlockState state = worldIn.getBlockState(pos.north());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.SOUTH) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		} else if(worldIn.getTileEntity(pos.north()) instanceof TileEntityGateNot) {
			TileEntityGateNot t = (TileEntityGateNot) worldIn.getTileEntity(pos.north());
			IBlockState state = worldIn.getBlockState(pos.north());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.SOUTH) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		} else if(worldIn.getTileEntity(pos.north()) instanceof TileEntityGateOr) {
			TileEntityGateOr t = (TileEntityGateOr) worldIn.getTileEntity(pos.north());
			IBlockState state = worldIn.getBlockState(pos.north());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.SOUTH) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		} else if(worldIn.getTileEntity(pos.north()) instanceof TileEntityGateXor) {
			TileEntityGateXor t = (TileEntityGateXor) worldIn.getTileEntity(pos.north());
			IBlockState state = worldIn.getBlockState(pos.north());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.SOUTH) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		}
		
		if(worldIn.getTileEntity(pos.south()) instanceof TileEntityTube){
			TileEntityTube t = (TileEntityTube) worldIn.getTileEntity(pos.south());
			this.isWaiting = true;
			if(!t.isWaiting) processActivate(worldIn, pos.south());
			this.foundSource = t.foundSource;
			this.isWaiting = false;
			if(t.foundSource) this.power = t.power;
		} else if(worldIn.getTileEntity(pos.south()) instanceof TileEntityGateAnd) {
			TileEntityGateAnd t = (TileEntityGateAnd) worldIn.getTileEntity(pos.south());
			IBlockState state = worldIn.getBlockState(pos.south());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.NORTH) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		} else if(worldIn.getTileEntity(pos.south()) instanceof TileEntityGateNot) {
			TileEntityGateNot t = (TileEntityGateNot) worldIn.getTileEntity(pos.south());
			IBlockState state = worldIn.getBlockState(pos.south());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.NORTH) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		} else if(worldIn.getTileEntity(pos.south()) instanceof TileEntityGateOr) {
			TileEntityGateOr t = (TileEntityGateOr) worldIn.getTileEntity(pos.south());
			IBlockState state = worldIn.getBlockState(pos.south());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.NORTH) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		} else if(worldIn.getTileEntity(pos.south()) instanceof TileEntityGateXor) {
			TileEntityGateXor t = (TileEntityGateXor) worldIn.getTileEntity(pos.south());
			IBlockState state = worldIn.getBlockState(pos.south());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.NORTH) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		}
		
		if(worldIn.getTileEntity(pos.east()) instanceof TileEntityTube){
			TileEntityTube t = (TileEntityTube) worldIn.getTileEntity(pos.east());
			this.isWaiting = true;
			if(!t.isWaiting) processActivate(worldIn, pos.east());
			this.foundSource = t.foundSource;
			this.isWaiting = false;
			if(t.foundSource) this.power = t.power;
		} else if(worldIn.getTileEntity(pos.east()) instanceof TileEntityGateAnd) {
			TileEntityGateAnd t = (TileEntityGateAnd) worldIn.getTileEntity(pos.east());
			IBlockState state = worldIn.getBlockState(pos.east());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.WEST) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		} else if(worldIn.getTileEntity(pos.east()) instanceof TileEntityGateNot) {
			TileEntityGateNot t = (TileEntityGateNot) worldIn.getTileEntity(pos.east());
			IBlockState state = worldIn.getBlockState(pos.east());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.WEST) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		} else if(worldIn.getTileEntity(pos.east()) instanceof TileEntityGateOr) {
			TileEntityGateOr t = (TileEntityGateOr) worldIn.getTileEntity(pos.east());
			IBlockState state = worldIn.getBlockState(pos.east());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.WEST) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		} else if(worldIn.getTileEntity(pos.east()) instanceof TileEntityGateXor) {
			TileEntityGateXor t = (TileEntityGateXor) worldIn.getTileEntity(pos.east());
			IBlockState state = worldIn.getBlockState(pos.east());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.WEST) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		}
		
		if(worldIn.getTileEntity(pos.west()) instanceof TileEntityTube){
			TileEntityTube t = (TileEntityTube) worldIn.getTileEntity(pos.west());
			this.isWaiting = true;
			if(!t.isWaiting) processActivate(worldIn, pos.west());
			this.foundSource = t.foundSource;
			this.isWaiting = false;
			if(t.foundSource) this.power = t.power;
		} else if(worldIn.getTileEntity(pos.west()) instanceof TileEntityGateAnd) {
			TileEntityGateAnd t = (TileEntityGateAnd) worldIn.getTileEntity(pos.west());
			IBlockState state = worldIn.getBlockState(pos.west());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.EAST) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		} else if(worldIn.getTileEntity(pos.west()) instanceof TileEntityGateNot) {
			TileEntityGateNot t = (TileEntityGateNot) worldIn.getTileEntity(pos.west());
			IBlockState state = worldIn.getBlockState(pos.west());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.EAST) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		} else if(worldIn.getTileEntity(pos.west()) instanceof TileEntityGateOr) {
			TileEntityGateOr t = (TileEntityGateOr) worldIn.getTileEntity(pos.west());
			IBlockState state = worldIn.getBlockState(pos.west());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.EAST) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		} else if(worldIn.getTileEntity(pos.west()) instanceof TileEntityGateXor) {
			TileEntityGateXor t = (TileEntityGateXor) worldIn.getTileEntity(pos.west());
			IBlockState state = worldIn.getBlockState(pos.west());
			if(state.getValue(BlockGateAnd.FACING) == EnumFacing.EAST) {
				t.getState(worldIn, pos);
				if(t.canTransmit) {
					this.power = true;
					this.foundSource = true;
				}
			}
		}
	}*/
	
	public void updateDirs() {
		BlockPos position = this.getPos();
		World worldObj = this.worldObj;
		//this.worldObj.getTileEntity(position)
		
		for ( int i = 0; i < 6; i++ )
		{
			dirs[i] = null;
		}
		
		for ( EnumFacing currentDirection : EnumFacing.VALUES )
		{
			//if( worldObj.getTileEntity( position.add( currentDirection.getDirectionVec() ) ) instanceof TileEntityTube )
			if ( worldObj.getBlockState( position.add( currentDirection.getDirectionVec() )).getBlock() == KnolthBlocks.tube )
				dirs[ currentDirection.ordinal() ] = currentDirection;
		}
		
		for ( EnumFacing currentDirection : EnumFacing.HORIZONTALS )
		{
			//Block currentBlock = worldObj.getBlockState( position.add( currentDirection.getDirectionVec() ) ).getBlock();
			Block currentBlock = worldObj.getBlockState( position.add( currentDirection.getDirectionVec() )).getBlock();
			if( currentBlock == KnolthBlocks.gate_and ||
				currentBlock == KnolthBlocks.gate_not ||
				currentBlock == KnolthBlocks.gate_or ||
				currentBlock == KnolthBlocks.gate_xor
				)
				dirs[ currentDirection.ordinal() ] = currentDirection;
		}
		
		if(worldObj.getBlockState(pos.up()).getBlock() instanceof BlockBattery) dirs[0] = EnumFacing.UP;
		if(worldObj.getBlockState(pos.down()).getBlock() instanceof BlockBattery) dirs[1] = EnumFacing.DOWN;
		
		///** All facings in D-U-N-S-W-E order */
		
		//position.up() returns a BlockPos, offsetted 1 block up..
		/*
		if(worldObj.getTileEntity(position.up()) instanceof TileEntityTube) dirs[0] = EnumFacing.UP; else dirs[0] = null;
		if(worldObj.getTileEntity(position.down()) instanceof TileEntityTube) dirs[1] = EnumFacing.DOWN; else dirs[1] = null;
		if(worldObj.getTileEntity(position.north()) instanceof TileEntityTube) dirs[2] = EnumFacing.NORTH; else dirs[2] = null;
		if(worldObj.getTileEntity(position.south()) instanceof TileEntityTube) dirs[3] = EnumFacing.SOUTH; else dirs[3] = null;
		if(worldObj.getTileEntity(position.east()) instanceof TileEntityTube) dirs[4] = EnumFacing.EAST; else dirs[4] = null;
		if(worldObj.getTileEntity(position.west()) instanceof TileEntityTube) dirs[5] = EnumFacing.WEST; else dirs[5] = null;
	    */

		
		/*
		if(worldObj.getBlockState(pos.north()).getBlock() instanceof BlockGateAnd) dirs[2] = EnumFacing.NORTH;
		if(worldObj.getBlockState(pos.south()).getBlock() instanceof BlockGateAnd) dirs[3] = EnumFacing.SOUTH;
		if(worldObj.getBlockState(pos.east()).getBlock() instanceof BlockGateAnd) dirs[4] = EnumFacing.EAST;
		if(worldObj.getBlockState(pos.west()).getBlock() instanceof BlockGateAnd) dirs[5] = EnumFacing.WEST;
		
		if(worldObj.getBlockState(pos.north()).getBlock() instanceof BlockGateNot) dirs[2] = EnumFacing.NORTH;
		if(worldObj.getBlockState(pos.south()).getBlock() instanceof BlockGateNot) dirs[3] = EnumFacing.SOUTH;
		if(worldObj.getBlockState(pos.east()).getBlock() instanceof BlockGateNot) dirs[4] = EnumFacing.EAST;
		if(worldObj.getBlockState(pos.west()).getBlock() instanceof BlockGateNot) dirs[5] = EnumFacing.WEST;
		
		if(worldObj.getBlockState(pos.north()).getBlock() instanceof BlockGateOr) dirs[2] = EnumFacing.NORTH;
		if(worldObj.getBlockState(pos.south()).getBlock() instanceof BlockGateOr) dirs[3] = EnumFacing.SOUTH;
		if(worldObj.getBlockState(pos.east()).getBlock() instanceof BlockGateOr) dirs[4] = EnumFacing.EAST;
		if(worldObj.getBlockState(pos.west()).getBlock() instanceof BlockGateOr) dirs[5] = EnumFacing.WEST;
		
		if(worldObj.getBlockState(pos.north()).getBlock() instanceof BlockGateXor) dirs[2] = EnumFacing.NORTH;
		if(worldObj.getBlockState(pos.south()).getBlock() instanceof BlockGateXor) dirs[3] = EnumFacing.SOUTH;
		if(worldObj.getBlockState(pos.east()).getBlock() instanceof BlockGateXor) dirs[4] = EnumFacing.EAST;
		if(worldObj.getBlockState(pos.west()).getBlock() instanceof BlockGateXor) dirs[5] = EnumFacing.WEST;
		*/
    }
	
	
	public boolean onlyOneOpposite()
	{
		int x,y,z,dirsAvailable;
		x=y=z=dirsAvailable=0;
		for(int i = 0; i < dirs.length; i++)
		{
			if(dirs[i] != null)
			{
				dirsAvailable++;
				x += dirs[i].getFrontOffsetX();
				y += dirs[i].getFrontOffsetY();
				z += dirs[i].getFrontOffsetZ();
			}
		}
		
		if(dirsAvailable == 2)
			if (x==0 && y==0 && z==0)
				return true;
		
		return false;
	}
	
}
