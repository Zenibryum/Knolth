package com.zenibryum.knolth.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemManual extends Item{
	@Override
    public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		return stack;
    }
}
