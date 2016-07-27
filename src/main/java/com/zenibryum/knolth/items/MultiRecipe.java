package com.zenibryum.knolth.items;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class MultiRecipe {
	public ArrayList<ItemStack> reactIn = new ArrayList();
	public ArrayList<ItemStack> reactOut = new ArrayList();
	
	/*public MultiRecipe ()
	{
		//TODO : Add reaction times ;)
	}*/
	
	public void addReactIn( ItemStack inputItem )
	{
		reactIn.add ( inputItem );
	}
	
	public void addReactOut( ItemStack inputItem )
	{
		reactOut.add ( inputItem );
	}
	
	public ArrayList<ItemStack> getReactIn()
	{
		return this.reactIn;
	}
	
	public ArrayList<ItemStack> getReactedOut()
	{
		return this.reactOut;
	}
	
	/**Gets the necessary amount of ItemStack itme in MultiRecipe recipe*/
	public int getNecessaryAmount( ItemStack item )
	{
		for ( ItemStack inputItem : reactIn )
		{
			if ( item.getItem() == inputItem.getItem() )
			{
				return inputItem.stackSize;
			}
		}
		return 0;
	}
	
	public int getReactOutNumber()
	{
		return reactOut.size();
	}

}