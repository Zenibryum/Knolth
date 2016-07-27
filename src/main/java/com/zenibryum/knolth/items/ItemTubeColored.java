package com.zenibryum.knolth.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTubeColored extends Item {
	public int color = 0;
	
	public ItemTubeColored(int colorIn)
	{
		this.color = colorIn;
	}
	
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass)
    {
        //return renderPass > 0 ? 16777215 : this.getColorFromDamage(stack.getMetadata());
    	return this.color;
    }
}
