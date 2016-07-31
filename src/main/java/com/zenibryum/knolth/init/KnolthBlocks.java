package com.zenibryum.knolth.init;

import java.util.ArrayList;
import java.util.List;

import com.zenibryum.knolth.Knolth;
import com.zenibryum.knolth.Reference;
import com.zenibryum.knolth.blocks.BlockBattery;
import com.zenibryum.knolth.blocks.BlockCentrifuge;
import com.zenibryum.knolth.blocks.BlockElectricFurnace;
import com.zenibryum.knolth.blocks.BlockGateAnd;
import com.zenibryum.knolth.blocks.BlockGateNot;
import com.zenibryum.knolth.blocks.BlockGateOr;
import com.zenibryum.knolth.blocks.BlockGateXor;
import com.zenibryum.knolth.blocks.BlockGrinder;
import com.zenibryum.knolth.blocks.BlockKnolthOre;
import com.zenibryum.knolth.blocks.BlockLaser;
import com.zenibryum.knolth.blocks.BlockLightbulb;
import com.zenibryum.knolth.blocks.BlockMulti;
import com.zenibryum.knolth.blocks.BlockMultiEdge;
import com.zenibryum.knolth.blocks.BlockMultiPart;
import com.zenibryum.knolth.blocks.BlockNormal;
import com.zenibryum.knolth.blocks.BlockOrientable;
import com.zenibryum.knolth.blocks.BlockTerminal;
import com.zenibryum.knolth.blocks.BlockTiny;
import com.zenibryum.knolth.blocks.BlockTube;
import com.zenibryum.knolth.blocks.BlockVacuum;
import com.zenibryum.knolth.tileentity.TileEntityBattery;
import com.zenibryum.knolth.tileentity.TileEntityCentrifuge;
import com.zenibryum.knolth.tileentity.TileEntityElectricFurnace;
import com.zenibryum.knolth.tileentity.TileEntityGateAnd;
import com.zenibryum.knolth.tileentity.TileEntityGateNot;
import com.zenibryum.knolth.tileentity.TileEntityGateOr;
import com.zenibryum.knolth.tileentity.TileEntityGrinder;
import com.zenibryum.knolth.tileentity.TileEntityLightbulb;
import com.zenibryum.knolth.tileentity.TileEntityMulti;
import com.zenibryum.knolth.tileentity.TileEntityTube;
import com.zenibryum.knolth.tileentity.TileEntityVacuum;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class KnolthBlocks {
    //public static Block test_block;
    private static List<Block> knolthBlocks = new ArrayList<Block>();
    
    private static String[] blockNames = {"workspace_part", "workspace_corner"};
    private static String[] oreNames = { "copper_ore", "zinc_ore", "alluminium_ore", "platinum_ore" };
    //workspace_side
    
    public static Block uraniumOre = new BlockKnolthOre().setUnlocalizedName("uranium_ore").setCreativeTab(Knolth.tabKnolth);
    public static Block terminal = new BlockTerminal(Material.anvil).setUnlocalizedName("terminal").setCreativeTab(Knolth.tabKnolth);
    //public static Block macerator = new BlockMacerator(false).setUnlocalizedName("macerator").setCreativeTab(Knolth.tabKnolth);
    //public static Block lit_macerator = new BlockMacerator(true).setUnlocalizedName("lit_macerator").setCreativeTab(Knolth.tabKnolth);
    public static Block electric_furnace = new BlockElectricFurnace().setUnlocalizedName("electric_furnace").setCreativeTab(Knolth.tabKnolth);
    public static Block lit_electric_furnace = new BlockOrientable(Material.anvil).setUnlocalizedName("lit_electric_furnace").setCreativeTab(Knolth.tabKnolth);
    public static Block microscope = new BlockOrientable(Material.anvil).setUnlocalizedName("microscope").setCreativeTab(Knolth.tabKnolth);
    //public static Block compressor = new BlockOrientable(Material.anvil).setUnlocalizedName("compressor").setCreativeTab(Knolth.tabKnolth);
    public static Block centrifuge = new BlockCentrifuge().setUnlocalizedName("centrifuge").setCreativeTab(Knolth.tabKnolth);
    public static Block tiny_box = new BlockTiny().setUnlocalizedName("tiny_box").setCreativeTab(Knolth.tabKnolth);
    public static Block tube = new BlockTube().setUnlocalizedName("tube").setCreativeTab(Knolth.tabKnolth);
    public static Block grinder = new BlockGrinder().setUnlocalizedName("macerator").setCreativeTab(Knolth.tabKnolth);
    public static Block vacuum = new BlockVacuum().setUnlocalizedName("compressor").setCreativeTab(Knolth.tabKnolth);
    public static Block gate_and = new BlockGateAnd(Material.cactus).setUnlocalizedName("gate_and").setCreativeTab(Knolth.tabKnolth);
    public static Block gate_or = new BlockGateOr(Material.cactus).setUnlocalizedName("gate_or").setCreativeTab(Knolth.tabKnolth);
    public static Block gate_xor = new BlockGateXor(Material.cactus).setUnlocalizedName("gate_xor").setCreativeTab(Knolth.tabKnolth);
    public static Block gate_not = new BlockGateNot(Material.cactus).setUnlocalizedName("gate_not").setCreativeTab(Knolth.tabKnolth);
    public static Block laser = new BlockLaser(Material.anvil).setUnlocalizedName("laser").setCreativeTab(Knolth.tabKnolth);
    public static Block battery = new BlockBattery(Material.anvil).setUnlocalizedName("battery").setCreativeTab(Knolth.tabKnolth);
    public static Block multi_block = new BlockMulti().setUnlocalizedName("multiblock").setCreativeTab(Knolth.tabKnolth);
    public static Block multi_block_part = new BlockMultiPart().setUnlocalizedName("multiblockpart").setCreativeTab(Knolth.tabKnolth);
    public static Block multi_block_edge = new BlockMultiEdge().setUnlocalizedName("multiblockedge").setCreativeTab(Knolth.tabKnolth);
    public static Block lightbulb = new BlockLightbulb(Material.ice, false).setUnlocalizedName("lightbulb").setCreativeTab(Knolth.tabKnolth);
    public static Block lightbulb_lit = new BlockLightbulb(Material.ice, true).setUnlocalizedName("lightbulb_lit");//.setCreativeTab(Knolth.tabKnolth);
    //public static Block lightbulb = new BlockLight( false ).setUnlocalizedName("lightbulb").setCreativeTab(Knolth.tabKnolth);
    //public static Block lightbulb_lit = new BlockLight( true ).setUnlocalizedName("lightbulb_lit").setCreativeTab(Knolth.tabKnolth);
    
    public static void init() {
        knolthBlocks.add(terminal);
        //knolthBlocks.add(macerator);
        //knolthBlocks.add(lit_macerator);
        knolthBlocks.add(electric_furnace);
        knolthBlocks.add(lit_electric_furnace);
        knolthBlocks.add(microscope);
        //knolthBlocks.add(compressor);
        knolthBlocks.add(centrifuge);
        knolthBlocks.add(tiny_box);
        knolthBlocks.add(tube);
        knolthBlocks.add(grinder);
        knolthBlocks.add(vacuum);
        knolthBlocks.add(uraniumOre);
        knolthBlocks.add(gate_and);
        knolthBlocks.add(gate_or);
        knolthBlocks.add(gate_xor);
        knolthBlocks.add(gate_not);
        knolthBlocks.add(laser);
        knolthBlocks.add(battery);
        knolthBlocks.add(multi_block);
        knolthBlocks.add(multi_block_part);
        knolthBlocks.add(multi_block_edge);
        knolthBlocks.add(lightbulb);
        knolthBlocks.add(lightbulb_lit);
        
        for (String orename : oreNames) {   //for every Block block in knolthBlocks...
            knolthBlocks.add( new BlockKnolthOre().setUnlocalizedName(orename).setCreativeTab(Knolth.tabKnolth) );
        }
        
        for (String blockname : blockNames) {   //for every Block block in knolthBlocks...
            knolthBlocks.add( new BlockNormal(Material.rock).setUnlocalizedName(blockname).setCreativeTab(Knolth.tabKnolth) );
        }
    }
    
    public static void register() {
        for (Block block : knolthBlocks) {   //for every Block block in knolthBlocks...
            GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5)); // tile.workspace_part
        }
        GameRegistry.registerTileEntity(TileEntityTube.class, "Tube");
        GameRegistry.registerTileEntity(TileEntityGrinder.class, "tileEntityGrinder");
    	GameRegistry.registerTileEntity(TileEntityCentrifuge.class, "tileEntityCentrifuge");
    	GameRegistry.registerTileEntity(TileEntityVacuum.class, "tileEntityVacuum");
    	GameRegistry.registerTileEntity(TileEntityElectricFurnace.class, "tileEntityElectricFurnace");
    	GameRegistry.registerTileEntity(TileEntityMulti.class, "tileEntityMulti");
    	GameRegistry.registerTileEntity(TileEntityBattery.class, "tileEntityBattery");
    	GameRegistry.registerTileEntity(TileEntityLightbulb.class, "tileEntityLightbulb");
    	
    	GameRegistry.registerTileEntity(TileEntityGateNot.class, "TileEntityGateNot");
    	GameRegistry.registerTileEntity(TileEntityGateAnd.class, "tileEntityGateAnd");
    	GameRegistry.registerTileEntity(TileEntityGateOr.class, "TileEntityGateOr");
    }
    
    public static void registerRenders() {
        for (Block block : knolthBlocks) {   //for every Block block in knolthBlocks...
            registerRender(block);
        }
    }
    
    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,0,new ModelResourceLocation(Reference.MOD_ID+":"+ item.getUnlocalizedName().substring(5), "inventory"));
    }
    
}