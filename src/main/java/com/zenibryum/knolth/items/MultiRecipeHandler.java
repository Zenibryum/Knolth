package com.zenibryum.knolth.items;

import java.util.ArrayList;
import java.util.Map;

import com.zenibryum.knolth.init.KnolthBlocks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MultiRecipeHandler
{
    private static final MultiRecipeHandler multiBase = new MultiRecipeHandler();
    /** The list of grinding results. */
    private ArrayList<MultiRecipe> recipeList = new ArrayList();

    public static MultiRecipeHandler instance()
    {
        return multiBase;
    }

    private MultiRecipeHandler()
    {
    	//Java Arrays for the input itemstacks and for the ouput itemstacks,
    	//using java arrays' function for .contains() ... to check if the neccesary items for the reaction are Contained in the supplied items
    	
    	MultiRecipe magnesium = new MultiRecipe();
    	magnesium.addReactIn( new ItemStack( Item.getItemFromBlock(KnolthBlocks.uraniumOre), 1) );
    	magnesium.addReactIn( new ItemStack( Item.getItemFromBlock(KnolthBlocks.centrifuge), 2) );
    	magnesium.addReactOut(new ItemStack( Item.getItemFromBlock(KnolthBlocks.grinder), 5) );
    	addMultiRecipe(magnesium);
    	
    	
    	/*
        addGrindingRecipe(new ItemStack(Item.getItemFromBlock(Blocks.stonebrick)), new ItemStack(Item.getItemFromBlock(Blocks.gravel)), 0.7F);
        addGrindingRecipe(new ItemStack(Item.getItemFromBlock(Blocks.stone_slab)), new ItemStack(Item.getItemFromBlock(Blocks.gravel)), 0.7F);
        addGrindingRecipe(new ItemStack(Item.getItemFromBlock(Blocks.stone_slab2)), new ItemStack(Item.getItemFromBlock(Blocks.gravel)), 0.7F);
        addGrindingRecipe(new ItemStack(Item.getItemFromBlock(Blocks.stone_stairs)), new ItemStack(Item.getItemFromBlock(Blocks.gravel)), 0.7F);
    
    */
    }

    public void addMultiRecipe( MultiRecipe recipe)
    {
        recipeList.add(recipe);
    }

    /**
     * Returns the recipe that uses the inputItems
     */
    public MultiRecipe getMultiResult(ArrayList<ItemStack> inputItems)
    {
    	for (MultiRecipe recipe: recipeList) { // For every recipe registered..
    		
    		ArrayList<ItemStack> reactList = recipe.getReactIn(); // get the reactants list
    		boolean found = true; //suppose this is the recipe we are looking for
    		for ( ItemStack component : reactList ) // for every reactant in the list
    		{
    			
    			boolean isthere = false; // suppose we didn't find the reactant
    			for ( ItemStack input : inputItems ) // for every input item in the inputItems
    			{
    				if ( input.getItem() == component.getItem() ) // check if it matches the reactant
    				{
    					isthere = true; // if it does, mark that, and break;
    					break;
    				}
    			}
    			if ( !isthere ) // if it didn't find the reactant in the recipe list
    			{
    				found = false; // The recipes isn't the one we're looking for
    			}
    			
    			
    		}
    		if ( found ) // if it does find all the reactants in the inputItems
    		{
    			//System.out.println("RECIPE FOUND : " + inputItems.size());
    			return recipe; // return the recipe
    		}
    	}
    	//System.out.println("getMultiResult has been called for itemcount : " + inputItems.size());
    	return null; // Nothing can be crafted from inputItems, return this 'errorcode'
    	
    	
    	
    	
    	//return magnesium;
    	
    	/*
        Iterator iterator = multiList.entrySet().iterator();
        Entry entry;
        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!areItemStacksEqual(parItemStack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
        */
    	//return new ItemStack();
    }

    private boolean areItemStacksEqual(ItemStack parItemStack1, ItemStack parItemStack2)
    {
        return parItemStack2.getItem() == parItemStack1.getItem() 

              && (parItemStack2.getMetadata() == 32767 

              || parItemStack2.getMetadata() == parItemStack1

              .getMetadata());
    }
/*
    public Map getGrindingList()
    {
        return multiList;
    }
/*
    public float getGrindingExperience(ItemStack parItemStack)
    {
        Iterator iterator = experienceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!areItemStacksEqual(parItemStack, 

              (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
    */
}

