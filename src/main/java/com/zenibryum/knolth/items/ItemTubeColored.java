package com.zenibryum.knolth.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTubeColored extends Item {
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass)
    {
        //return renderPass > 0 ? 16777215 : this.getColorFromDamage(stack.getMetadata());
    	return 5618517;
    }
}
