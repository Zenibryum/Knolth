package com.zenibryum.knolth.tileentity;

import java.util.ArrayList;

import com.zenibryum.knolth.items.GrinderRecipes;
import com.zenibryum.knolth.items.MultiRecipe;
import com.zenibryum.knolth.items.MultiRecipeHandler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityMulti extends TileEntityLockable implements ISidedInventory, ITickable//,IUpdatePlayerListBox
{
// enumerate the slots

public enum slotEnum 
{
  INPUT_SLOT_0,INPUT_SLOT_1,INPUT_SLOT_2, OUTPUT_SLOT_0, OUTPUT_SLOT_1, OUTPUT_SLOT_2
}
private static final int[] slotsTop = new int[] { 0,1,2 };
private static final int[] slotsBottom = new int[] { 3,4,5 };
private static final int[] slotsSides = new int[] {};
private ItemStack[] multiItemStackArray = new ItemStack[6];
private int timeCanReact; 

private int currentItemReactTime;
private int ticksReactedItemSoFar;
private int ticksPerItem;
private String multiCustomName;

@Override

public boolean shouldRefresh(World parWorld, BlockPos parPos, IBlockState parOldState, IBlockState parNewState)
{
  return false;
}

@Override
public int getSizeInventory()
{
  return multiItemStackArray.length;
}

@Override
public ItemStack removeStackFromSlot(int index)
{
	ItemStack stack = multiItemStackArray[index];
	multiItemStackArray[index] = null;
	return stack;
}

@Override
public ItemStack getStackInSlot(int index)
{
  return multiItemStackArray[index];
}

@Override
public ItemStack decrStackSize(int index, int count)
{
  if (multiItemStackArray[index] != null)
  {
      ItemStack itemstack;

      if (multiItemStackArray[index].stackSize <= count)
      {
          itemstack = multiItemStackArray[index];
          multiItemStackArray[index] = null;
          return itemstack;
      }
      else
      {
          itemstack = multiItemStackArray[index].splitStack(count);

          if (multiItemStackArray[index].stackSize == 0)
          {
              multiItemStackArray[index] = null;
          }

          return itemstack;
      }
  }
  else
  {
      return null;
  }
}

/**
* When some containers are closed they call this on each slot, then 

* drop whatever it returns as an EntityItem -
* like when you close a workbench GUI.
*/
/*
@Override
public ItemStack getStackInSlotOnClosing(int index)
{
  if (grinderItemStackArray[index] != null)
  {
      ItemStack itemstack = grinderItemStackArray[index];
      grinderItemStackArray[index] = null;
      return itemstack;
  }
  else
  {
      return null;
  }
}
*/

@Override
public void setInventorySlotContents(int index, ItemStack stack)
{
  boolean isSameItemStackAlreadyInSlot = stack != null 

        && stack.isItemEqual(multiItemStackArray[index]) 

        && ItemStack.areItemStackTagsEqual(stack, 

        multiItemStackArray[index]);
  multiItemStackArray[index] = stack;

  if (stack != null && stack.stackSize > getInventoryStackLimit())
  {
      stack.stackSize = getInventoryStackLimit();
  }

  // if input slot, reset the grinding timers
  if (index < 3 //the slots 0,1,2 are < 3 and they are the input slots
        && !isSameItemStackAlreadyInSlot)
  {      
      MultiRecipe recipe =  getRecipe();
      
      if ( recipe != null )
    	  ticksPerItem = timeToReactOneBatch( recipe );// warning : might return null;
      else
    	  ticksPerItem = 200;
      
      ticksReactedItemSoFar = 0;
      markDirty();
  }
}

@Override
public String getName()
{
  return hasCustomName() ? multiCustomName : "container.multi";
}

@Override
public boolean hasCustomName()
{
  return multiCustomName != null && multiCustomName.length() > 0;
}

public void setCustomInventoryName(String parCustomName)
{
  multiCustomName = parCustomName;
}

@Override
public void readFromNBT(NBTTagCompound compound)
{
  super.readFromNBT(compound);
  NBTTagList nbttaglist = compound.getTagList("Items", 10);
  multiItemStackArray = new ItemStack[getSizeInventory()];

  for (int i = 0; i < nbttaglist.tagCount(); ++i)
  {
      NBTTagCompound nbtTagCompound = nbttaglist.getCompoundTagAt(i);
      byte b0 = nbtTagCompound.getByte("Slot");

      if (b0 >= 0 && b0 < multiItemStackArray.length)
      {
          multiItemStackArray[b0] = ItemStack.loadItemStackFromNBT(

                nbtTagCompound);
      }
  }

  timeCanReact = compound.getShort("GrindTime");
  ticksReactedItemSoFar = compound.getShort("CookTime");
  ticksPerItem = compound.getShort("CookTimeTotal");

  if (compound.hasKey("CustomName", 8))
  {
      multiCustomName = compound.getString("CustomName");
  }
}

@Override
public void writeToNBT(NBTTagCompound compound)
{
  super.writeToNBT(compound);
  compound.setShort("GrindTime", (short)timeCanReact);
  compound.setShort("CookTime", (short)ticksReactedItemSoFar);
  compound.setShort("CookTimeTotal", (short)ticksPerItem);
  NBTTagList nbttaglist = new NBTTagList();

  for (int i = 0; i < multiItemStackArray.length; ++i)
  {
      if (multiItemStackArray[i] != null)
      {
          NBTTagCompound nbtTagCompound = new NBTTagCompound();
          nbtTagCompound.setByte("Slot", (byte)i);
          multiItemStackArray[i].writeToNBT(nbtTagCompound);
          nbttaglist.appendTag(nbtTagCompound);
      }
  }

  compound.setTag("Items", nbttaglist);

  if (hasCustomName())
  {
      compound.setString("CustomName", multiCustomName);
  }
}

@Override

public int getInventoryStackLimit()
{
  return 64;
}

public boolean reactingSomething()
{
  return true;
}

// this function indicates whether container texture should be drawn
@SideOnly(Side.CLIENT)
public static boolean func_174903_a(IInventory parIInventory)
{
  return true ;
}

public MultiRecipe getRecipe()
{
    ArrayList<ItemStack> inputItems = new ArrayList<ItemStack>();
    
    for ( int i = 0; i < 3; i++ )
    {
    	if ( multiItemStackArray[i] != null)
    		inputItems.add( multiItemStackArray[i] );
    }
    
    return MultiRecipeHandler.instance().getMultiResult(inputItems);
}

@Override
public void update()
{
  boolean hasBeenReacting = reactingSomething();
  boolean changedReactingState = false;

  if (reactingSomething())
  {
      --timeCanReact;
  }

  if (!worldObj.isRemote)
  {
      // if something in input slot
      if (multiItemStackArray[0] != null || multiItemStackArray[1] != null || multiItemStackArray[2] != null)
      {                
           // start grinding
          if (!reactingSomething() && canReact())
          {

               timeCanReact = 150;


               if (reactingSomething())
               {
                   changedReactingState = true;
               }
          }

          // continue grinding
          if (reactingSomething() && canReact())
          {
              ++ticksReactedItemSoFar;
              
              // check if completed grinding an item
              if (ticksReactedItemSoFar == ticksPerItem)
              {
                  ticksReactedItemSoFar = 0;
                  
                  MultiRecipe recipe = getRecipe();
                  if (recipe != null)
                	  ticksPerItem = timeToReactOneBatch( recipe );// warning : might return null;
                  else
                	  ticksPerItem = 200;
                  reactItem();
                  changedReactingState = true;
              }
          }
          else
          {
              ticksReactedItemSoFar = 0;
          }
      }

      // started or stopped grinding, update block to change to active 

      // or inactive model
      if (hasBeenReacting != reactingSomething())
      {
          changedReactingState = true;
          //BlockGrinder.changeBlockBasedOnGrindingStatus(grindingSomething(), worldObj, pos);
      }
  }

  if (changedReactingState)
  {
      markDirty();
  }
}

public int timeToReactOneBatch(MultiRecipe recipe)
{
  return 200; //TODO : add time depending on recipe ;)
}


private boolean canReact()
{
  // if nothing in input slot
  if (multiItemStackArray[0] == null && multiItemStackArray[1] == null && multiItemStackArray[2] == null)
  {
      return false;
  }
  else // check if it has a grinding recipe
  {
      //ItemStack itemStackToOutput = GrinderRecipes.instance().getGrindingResult(multiItemStackArray[slotEnum.INPUT_SLOT.ordinal()]);
      
	    ArrayList<ItemStack> inputItems = new ArrayList<ItemStack>();
	    
	    for ( int i = 0; i < 3; i++ )
	    {
	    	if ( multiItemStackArray[i] != null)
	    		inputItems.add( multiItemStackArray[i] );
	    }
	    
	    //MultiRecipe recipe = MultiRecipeHandler.instance().getMultiResult(inputItems);
	    
	    MultiRecipe recipe = getRecipe();
      
	    ArrayList<ItemStack> reactIn;
      
      if ( recipe == null ){
    	  return false;
      }
      else{
    	  reactIn = recipe.getReactIn(); // Get reactants
      }
      
      
      for ( ItemStack inputStack : reactIn )
      {
    	  for ( int i = 0; i < 3; i++ )
    	  {
    		  if ( multiItemStackArray[i] != null )
    		  {
	    		  if ( multiItemStackArray[i].getItem() == inputStack.getItem() )
	    		  {
	    			  if ( multiItemStackArray[i].stackSize < inputStack.stackSize )
	    				  return false;
	    		  }
    		  }
    	  }
      }
      
      
      int requiredSlots = recipe.getReactOutNumber();
      
      ArrayList<ItemStack> outputItems = recipe.getReactedOut();
      
      for ( int i = 3; i < 6; i++ )
      {
    	  if ( multiItemStackArray[i] == null )
    		  --requiredSlots;
      }
      
      //TODO : Merge the for above and the if below?
      
      if (requiredSlots > 0) // if not enough empty slots are available, look for overlays
      {
    	  for ( int i = 3; i < 6; i++ )
    	  {
    		  if ( multiItemStackArray[i] != null )
    		  {
    			  for ( ItemStack itemStack : outputItems )
    			  {
    				  if ( multiItemStackArray[i].getItem() == itemStack.getItem() )
    				  {
    					  int result = multiItemStackArray[i].stackSize + itemStack.stackSize;
    					  if ( ( result <= getInventoryStackLimit() )
    						  && ( result <= multiItemStackArray[i].getMaxStackSize() ) )
    					  {
    						  --requiredSlots;break;
    					  }
    				  }
    			  }
    		  }
    	  }
      }
      
      //if (!multiItemStackArray[slotEnum.OUTPUT_SLOT.ordinal()].isItemEqual(itemStackToOutput)) return false; 
      //int result = multiItemStackArray[slotEnum.OUTPUT_SLOT.ordinal()].stackSize + itemStackToOutput.stackSize;
      //return result <= getInventoryStackLimit() && result <= multiItemStackArray[slotEnum.OUTPUT_SLOT.ordinal()].getMaxStackSize();
      
      if ( requiredSlots <= 0 )
    	  return true;
      else
    	  return false;
  }
}

public void reactItem()
{
  System.out.println("reactItem() has been called");
  if (canReact())
  {   
		System.out.println("checks show it can react");
	    ArrayList<ItemStack> inputItems = new ArrayList<ItemStack>();
	    
	    for ( int i = 0; i < 3; i++ )
	    {
	    	if ( multiItemStackArray[i] != null)
	    		inputItems.add( multiItemStackArray[i] );
	    }
	    
	  MultiRecipe recipe =  MultiRecipeHandler.instance().getMultiResult(inputItems);
      
      ArrayList<ItemStack> outputItems = recipe.getReactedOut();
      
      //for ( int i = 3; i < 6; i++ )

	      for ( ItemStack outputItem : outputItems ) // Add outputs
	      {
	
		      // check if output slot is empty
	    	  for ( int i = 3; i < 6; i++ ){
			      if (multiItemStackArray[i] == null)
			      {
			          multiItemStackArray[i] = outputItem.copy();
			          break;
			      }
			      else if (multiItemStackArray[i].getItem() == outputItem.getItem())
			      {
			          multiItemStackArray[i].stackSize += outputItem.stackSize; 
			          break;
			      }
	    	  }
	      }
	      
	      //Remove inputs
	      
	      for ( ItemStack inputItem : inputItems )
	      {
	    	  for ( int i = 0; i < 3; i++ )
	    	  {
	    		  if ( multiItemStackArray[i] == inputItem )
	    		  {
				      multiItemStackArray[i].stackSize -= recipe.getNecessaryAmount(inputItem);
				
				      if (multiItemStackArray[i].stackSize <= 0)
				      {
				          multiItemStackArray[i] = null;
				      }
	    		  }
	    	  }
	      }

  }
}

@Override
public boolean isUseableByPlayer(EntityPlayer playerIn)
{
  return worldObj.getTileEntity(pos) != this ? false : 

        playerIn.getDistanceSq(pos.getX()+0.5D, pos.getY()+0.5D, 

        pos.getZ()+0.5D) <= 64.0D;
}

@Override
public void openInventory(EntityPlayer playerIn) {}

@Override
public void closeInventory(EntityPlayer playerIn) {}

@Override
public boolean isItemValidForSlot(int index, ItemStack stack) // Modified for multiblock
{
  return 0 <= index && index < 3; 
}

@Override
public int[] getSlotsForFace(EnumFacing side)
{
  return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
}

@Override
public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
{
  return isItemValidForSlot(index, itemStackIn);
}


@Override
public boolean canExtractItem(int parSlotIndex, ItemStack parStack, EnumFacing parFacing)
{
  return true;
}

@Override
public String getGuiID()
{
  return "knolth:multi";
}

@Override
public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
{
  // DEBUG
  System.out.println("TileEntityMulti createContainer()");
  return new ContainerMulti(playerInventory, this);
}

@Override
public int getField(int id)
{
  switch (id)
  {
      case 0:
          return timeCanReact;
      case 1:
          return currentItemReactTime;
      case 2:
          return ticksReactedItemSoFar;
      case 3:
          return ticksPerItem;
      default:
          return 0;
  }
}

@Override
public void setField(int id, int value)
{
  switch (id)
  {
      case 0:
          timeCanReact = value;
          break;
      case 1:
          currentItemReactTime = value;
          break;
      case 2:
          ticksReactedItemSoFar = value;
          break;
      case 3:
          ticksPerItem = value;
          break;
  default:
      break;
  }
}

@Override
public int getFieldCount()
{
  return 4;
}

@Override
public void clear()
{
  for (int i = 0; i < multiItemStackArray.length; ++i)
  {
      multiItemStackArray[i] = null;
  }
}
}