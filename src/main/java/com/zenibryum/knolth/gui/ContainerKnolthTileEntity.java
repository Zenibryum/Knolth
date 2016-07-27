package com.zenibryum.knolth.gui;

import com.zenibryum.knolth.tileentity.KnolthTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerKnolthTileEntity extends Container{
	private KnolthTileEntity te;

    public ContainerKnolthTileEntity(IInventory playerInv, TileEntity te) {
    	this.te = (KnolthTileEntity) te;

        // Tile Entity, Slot 0-8, Slot IDs 0-8
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                this.addSlotToContainer(new Slot((IInventory) te, x + y * 3, 62 + x * 18, 17 + y * 18));
            }
        }

        // Player Inventory, Slot 9-35, Slot IDs 9-35
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        // Player Inventory, Slot 0-8, Slot IDs 36-44
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.te.isUseableByPlayer(playerIn);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
    	ItemStack previous = null;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < 9) {
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 9, 45, true))
                    return null;
            } else {
                // From Player Inventory to TE Inventory
                if (!this.mergeItemStack(current, 0, 9, false))
                    return null;
            }

            if (current.stackSize == 0)
                slot.putStack((ItemStack) null);
            else
                slot.onSlotChanged();

            if (current.stackSize == previous.stackSize)
                return null;
            slot.onPickupFromSlot(playerIn, current);
        }
        return previous;
    }
    
    public class SingleItemSlot extends Slot {

        public SingleItemSlot(IInventory inventory, int index, int xPosition, int yPosition) {
            super(inventory, index, xPosition, yPosition);
        }

        @Override
        public int getSlotStackLimit() {
            return 1;
        }
    }
    
    @Override
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex) {
        boolean success = false;
        int index = startIndex;
        if (useEndIndex) index = endIndex - 1;
        Slot slot;
        ItemStack stackinslot;
        if (stack.isStackable()) {
            while (stack.stackSize > 0 && (!useEndIndex && index < endIndex || useEndIndex && index >= startIndex)) {
                slot = (Slot) this.inventorySlots.get(index);
                stackinslot = slot.getStack();
                if (stackinslot != null && stackinslot.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == stackinslot.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, stackinslot)) {
                    int l = stackinslot.stackSize + stack.stackSize;
                    int maxsize = Math.min(stack.getMaxStackSize(), slot.getItemStackLimit(stack));
                    if (l <= maxsize) {
                        stack.stackSize = 0;
                        stackinslot.stackSize = l;
                        slot.onSlotChanged();
                        success = true;
                    } else if (stackinslot.stackSize < maxsize) {
                        stack.stackSize -= stack.getMaxStackSize() - stackinslot.stackSize;
                        stackinslot.stackSize = stack.getMaxStackSize();
                        slot.onSlotChanged();
                        success = true;
                    }
                }
                if (useEndIndex) {
                    --index;
                } else {
                    ++index;
                }
            }
        }
        if (stack.stackSize > 0) {
            if (useEndIndex) {
                index = endIndex - 1;
            } else {
                index = startIndex;
            }
            while (!useEndIndex && index < endIndex || useEndIndex && index >= startIndex && stack.stackSize > 0) {
                slot = (Slot) this.inventorySlots.get(index);
                stackinslot = slot.getStack();
                // Forge: Make sure to respect isItemValid in the slot.
                if (stackinslot == null && slot.isItemValid(stack)) {
                    if (stack.stackSize < slot.getItemStackLimit(stack)) {
                        slot.putStack(stack.copy());
                        stack.stackSize = 0;
                        success = true;
                        break;
                    } else {
                        ItemStack newstack = stack.copy();
                        newstack.stackSize = slot.getItemStackLimit(stack);
                        slot.putStack(newstack);
                        stack.stackSize -= slot.getItemStackLimit(stack);
                        success = true;
                    }
                }
                if (useEndIndex) {
                    --index;
                } else {
                    ++index;
                }
            }
        }
        return success;
    }
    
}
