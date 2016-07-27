package com.zenibryum.knolth.init;

import java.util.ArrayList;
import java.util.List;

import com.zenibryum.knolth.Knolth;
import com.zenibryum.knolth.Reference;
import com.zenibryum.knolth.items.ItemTubeColored;
import com.zenibryum.knolth.utils.SubstanceHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class KnolthItems {
	//public static Item test_item;
	public static World world;
	public static EntityPlayer player;
	
	private static List<Item> knolthItems = new ArrayList<Item>();
	
	private static String[] itemNames = {"testtube", "testtube_water", "alluminium_ingot", "bauxite_dust", "copper_ingot","platinum_ingot", "zinc_ingot"};
	
	public static Item enrichedUranium = new Item().setUnlocalizedName("enriched_uranium").setCreativeTab(Knolth.tabKnolth);
	public static Item crushedUranium = new Item().setUnlocalizedName("crushed_uranium").setCreativeTab(Knolth.tabKnolth);
	public static Item yellowcake = new Item().setUnlocalizedName("uranium_dust").setCreativeTab(Knolth.tabKnolth);
	public static Item NH3 = new Item().setUnlocalizedName("acidbottle").setCreativeTab(Knolth.tabKnolth);
	public static Item H2SO4 = new Item().setUnlocalizedName("H2SO4").setCreativeTab(Knolth.tabKnolth);
	public static Item HF = new Item().setUnlocalizedName("HF").setCreativeTab(Knolth.tabKnolth);
	public static Item Mg = new Item().setUnlocalizedName("Mg").setCreativeTab(Knolth.tabKnolth);
	public static Item UF4 = new ItemTubeColored(SubstanceHelper.UF4).setUnlocalizedName("UF4").setCreativeTab(Knolth.tabKnolth);
	public static Item UranylNitrate = new ItemTubeColored(SubstanceHelper.UranylNitrate).setUnlocalizedName("UranylNitrate").setCreativeTab(Knolth.tabKnolth);
	public static Item AmmoniumDiuranate = new ItemTubeColored(SubstanceHelper.AmmoniumDiuranate).setUnlocalizedName("AmmoniumDiuranate").setCreativeTab(Knolth.tabKnolth);
	public static Item U235 = new Item(){
		@Override
		public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
	        itemStackIn.setItem(enrichedUranium);
			return itemStackIn;
		}
	}.setUnlocalizedName("U235").setCreativeTab(Knolth.tabKnolth);
	public static Item UO2 = new ItemTubeColored(SubstanceHelper.UranylNitrate).setUnlocalizedName("UO2").setCreativeTab(Knolth.tabKnolth);
	public static Item U = new ItemTubeColored(SubstanceHelper.UranylNitrate).setUnlocalizedName("U").setCreativeTab(Knolth.tabKnolth);
	
	public static Item manual = new Item(){
		@Override
	    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
	        world = worldIn;
	        player = playerIn;
	        System.out.println("THIS IS DEBUG RIGT CLICK");
			if (worldIn.isRemote) {
				System.out.println("THIS RAN");
	            playerIn.openGui(Knolth.instance, Knolth.GUI_ENUM.MANUAL.ordinal(), worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
	        }
	        return itemStackIn;
	    }
	}.setUnlocalizedName("manual").setCreativeTab(Knolth.tabKnolth);
	
	public static void init(){
		knolthItems.add(manual);
		knolthItems.add(yellowcake);
		knolthItems.add(NH3);
		knolthItems.add(H2SO4);
		knolthItems.add(HF);
		knolthItems.add(Mg);
		knolthItems.add(UF4);
		knolthItems.add(UranylNitrate);
		knolthItems.add(AmmoniumDiuranate);
		knolthItems.add(U235);
		knolthItems.add(crushedUranium);
		knolthItems.add(U);
		knolthItems.add(UO2);
		knolthItems.add(enrichedUranium);
		for (String itemName : itemNames) {   //for every Block block in knolthBlocks...
			knolthItems.add( new Item().setUnlocalizedName(itemName).setCreativeTab(Knolth.tabKnolth) );
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
