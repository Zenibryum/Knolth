package com.zenibryum.knolth.init;
 
import java.util.ArrayList;
import java.util.List;

import com.zenibryum.knolth.Knolth;
import com.zenibryum.knolth.Reference;
import com.zenibryum.knolth.blocks.BlockCentrifuge;
import com.zenibryum.knolth.blocks.BlockElectricFurnace;
import com.zenibryum.knolth.blocks.BlockGrinder;
import com.zenibryum.knolth.blocks.BlockKnolthOre;
import com.zenibryum.knolth.blocks.BlockNormal;
import com.zenibryum.knolth.blocks.BlockOrientable;
import com.zenibryum.knolth.blocks.BlockTerminal;
import com.zenibryum.knolth.blocks.BlockTiny;
import com.zenibryum.knolth.blocks.BlockTube;
import com.zenibryum.knolth.blocks.BlockVacuum;
import com.zenibryum.knolth.tileentity.TileEntityTube;

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