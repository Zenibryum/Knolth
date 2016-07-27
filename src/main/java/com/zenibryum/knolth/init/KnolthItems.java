package com.zenibryum.knolth.init;

import java.util.ArrayList;
import java.util.List;

import com.zenibryum.knolth.Knolth;
import com.zenibryum.knolth.Reference;
import com.zenibryum.knolth.items.ItemTubeColored;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class KnolthItems {
	//public static Item test_item;
	
	private static List<Item> knolthItems = new ArrayList<Item>();
	
	private static String[] itemNames = {"testtube", "testtube_water", "alluminium_ingot", "bauxite_dust", "copper_ingot", "enriched_uranium","platinum_ingot", "uranium_dust", "zinc_ingot"};
	
	public static Item manual = new Item(){
		@Override
	    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
	        if (worldIn.isRemote) {
	            playerIn.openGui(Knolth.instance, 0, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
	        }
	        return itemStackIn;
	    }
	}.setUnlocalizedName("manual").setCreativeTab(Knolth.tabKnolth);
	
	public static void init(){
		knolthItems.add(manual);
		for (String itemName : itemNames) {   //for every Block block in knolthBlocks...
			knolthItems.add( new ItemTubeColored().setUnlocalizedName(itemName).setCreativeTab(Knolth.tabKnolth) );
			//test_item = new ItemCannabisPerfume().setUnlocalizedName("test_item").setCreativeTab(Knolth.tabKnolth);
		}
	}
	
	public static void register() {
		for (Item item : knolthItems) {   //for every Block block in knolthBlocks...
			GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5)); //"tile.test_item"
		}
	}
	
	public static void registerRenders() {
		for (Item item : knolthItems) {   //for every Block block in knolthBlocks...
			registerRender(item);
		}
	}
	
	public static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,0,new ModelResourceLocation(Reference.MOD_ID+":"+item.getUnlocalizedName().substring(5), "inventory"));
	}
}
