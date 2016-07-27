package com.zenibryum.knolth.tileentity;

import java.util.ArrayList;

import com.zenibryum.knolth.gui.SlotGrinderOutput;
import com.zenibryum.knolth.items.MultiRecipeHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerMulti extends Container
{
    private final IInventory tileMulti;
    private final int sizeInventory;
    private int ticksReactedItemSoFar;
    private int ticksPerItem;
    private int timeCanReact;

    public ContainerMulti(InventoryPlayer parInventoryPlayer, 
          IInventory parIInventory)
    {
        // DEBUG
        System.out.println("ContainerMulti constructor()");
        
        tileMulti = parIInventory;
        sizeInventory = tileMulti.getSizeInventory();
        
        for ( int i = 0; i < 3; i++ )//For the input slots
        {
        	addSlotToContainer(new Slot(tileMulti, i, 6+23*i, 35));
        }
        //6,34
        
        for ( int i = 0; i < 3; i++ ) // For the output slots
        {
        	//addSlotToContainer(new SlotGrinderOutput(parInventoryPlayer.player, tileMulti, i+3, 108+23*i, 35));
        	addSlotToContainer(new Slot( tileMulti, i+3, 108+23*i, 35));
        }
        
        /*
        addSlotToContainer( new Slot(tileMulti, TileEntityMulti.slotEnum.INPUT_SLOT.ordinal(), 56, 35) );
        //addSlotToContainer(new Slot(tileMulti, TileEntityMulti.slotEnum.INPUT_SLOT.ordinal()+2, 56, 15));
          addSlotToContainer(new SlotGrinderOutput(parInventoryPlayer.player, 
                tileMulti, TileEntityMulti.slotEnum.OUTPUT_SLOT.ordinal(), 
                116, 35));
        */
        
        //addSlotToContainer( new Slot(tileMulti, 0, 56, 35) );
        //addSlotToContainer(new Slot(tileMulti, TileEntityMulti.slotEnum.INPUT_SLOT.ordinal()+2, 56, 15));
        //addSlotToContainer(new SlotGrinderOutput(parInventoryPlayer.player, tileMulti, 1, 40, 35));
        
        
        // add player inventory slots
        // note that the slot numbers are within the player inventory so can 
        // be same as the tile entity inventory
        int i;
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                addSlotToContainer(new Slot(parInventoryPlayer, j+i*9+9, 
                8+j*18, 84+i*18));
            }
        }

        // add hotbar slots
        for (i = 0; i < 9; ++i)
        {
            addSlotToContainer(new Slot(parInventoryPlayer, i, 8 + i * 18, 
            142));
        }
    }

    /*
    @Override
    public void addCraftingToCrafters(ICrafting listener)
    {
        super.addCraftingToCrafters(listener);
        listener.func_175173_a(this, tileGrinder);
    }
    */

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)crafters.get(i);

            if (ticksReactedItemSoFar != tileMulti.getField(2))
            {
                icrafting.sendProgressBarUpdate(this, 2, 
                      tileMulti.getField(2));
            }

            if (timeCanReact != tileMulti.getField(0))
            {
                icrafting.sendProgressBarUpdate(this, 0, 
                      tileMulti.getField(0));
            }

            if (ticksPerItem != tileMulti.getField(3))
            {
                icrafting.sendProgressBarUpdate(this, 3, 
                      tileMulti.getField(3));
            }
        }

        ticksReactedItemSoFar = tileMulti.getField(2);
        timeCanReact = tileMulti.getField(0); 
        ticksPerItem = tileMulti.getField(3); 
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        tileMulti.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return tileMulti.isUseableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int slotIndex) //Modified for the 6-slots multiblock
    {
        ItemStack itemStack1 = null;
        Slot slot = (Slot)inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemStack2 = slot.getStack();
            itemStack1 = itemStack2.copy();

            if (3 <= slotIndex && slotIndex < 6) // OUTPUT SLOTS
            {
                if (!mergeItemStack(itemStack2, sizeInventory, 
                      sizeInventory+36, true))
                {
                    return null;
                }

                slot.onSlotChange(itemStack2, itemStack1);
            }
            else if ( 0 <= slotIndex && slotIndex < 3 ) // INPUT SLOTS ( 0 <= slotIndex && slotIndex < 3 )
            {
                // check if there is a grinding recipe for the stack
            	
            	ArrayList<ItemStack> inputItems = new ArrayList<ItemStack>();
                
                for ( int i = 0; i < 3; i++ )
                {
                	Slot current_slot = (Slot)inventorySlots.get(i);
                	inputItems.add( (current_slot.getStack()).copy() );
                }
            	
                if (MultiRecipeHandler.instance().getMultiResult(inputItems) != null)
                {
                    if (!mergeItemStack(itemStack2, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (slotIndex >= sizeInventory 
                     && slotIndex < sizeInventory+27) // player inventory slots
                {
                    if (!mergeItemStack(itemStack2, sizeInventory+27, 
                          sizeInventory+36, false))
                    {
                        return null;
                    }
                }
                else if (slotIndex >= sizeInventory+27 
                      && slotIndex < sizeInventory+36 
                      && !mergeItemStack(itemStack2, sizeInventory+1, 
                      sizeInventory+27, false)) // hotbar slots
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemStack2, sizeInventory, 
                  sizeInventory+36, false))
            {
                return null;
            }

            if (itemStack2.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemStack2.stackSize == itemStack1.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemStack2);
        }

        return itemStack1;
    }
}