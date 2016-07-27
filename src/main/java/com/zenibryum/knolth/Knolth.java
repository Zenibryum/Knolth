package com.zenibryum.knolth;

import com.zenibryum.knolth.gui.GuiHandlerGrinder;
import com.zenibryum.knolth.gui.GuiHandlerMacerator;
import com.zenibryum.knolth.init.KnolthBlocks;
import com.zenibryum.knolth.init.KnolthItems;
import com.zenibryum.knolth.proxy.CommonProxy;
import com.zenibryum.knolth.tileentity.TileEntityCentrifuge;
import com.zenibryum.knolth.tileentity.TileEntityElectricFurnace;
import com.zenibryum.knolth.tileentity.TileEntityGrinder;
import com.zenibryum.knolth.tileentity.TileEntityMulti;
import com.zenibryum.knolth.tileentity.TileEntityVacuum;
import com.zenibryum.knolth.tileentity.renderer.TileEntityRenderTube;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry; 

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Knolth {
	@Mod.Instance("knolth")
	public static Knolth instance;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS )
    public static CommonProxy proxy;
    
    public static final KnolthTab tabKnolth = new KnolthTab("tabKnolth");
    

    public enum GUI_ENUM {
    GRINDER, CENTRIFUGE, HEATER, VACUUM, ZIPPE
    }


    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandlerMacerator());
    	KnolthBlocks.init();
    	KnolthBlocks.register();
    	KnolthItems.init();
    	KnolthItems.register();
    	TileEntityRenderTube.init();

    	GameRegistry.registerTileEntity(TileEntityGrinder.class, "tileEntityGrinder");
    	GameRegistry.registerTileEntity(TileEntityCentrifuge.class, "tileEntityCentrifuge");
    	GameRegistry.registerTileEntity(TileEntityVacuum.class, "tileEntityVacuum");
    	GameRegistry.registerTileEntity(TileEntityElectricFurnace.class, "tileEntityElectricFurnace");
    	GameRegistry.registerTileEntity(TileEntityMulti.class, "tileEntityMulti");


    	
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MOD_ID, new KnolthGuiHandler()); 
        NetworkRegistry.INSTANCE.registerGuiHandler(instance,new GuiHandlerGrinder());  


    	proxy.registerRenders();
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
}