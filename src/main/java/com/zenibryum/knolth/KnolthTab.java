package com.zenibryum.knolth;

import com.zenibryum.knolth.init.KnolthItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class KnolthTab extends CreativeTabs{
	public KnolthTab(String label){
		super(label);
		this.setBackgroundImageName("knolth.png");
	}
	
	@Override
	public Item getTabIconItem(){
		return KnolthItems.manual;
	}
}
