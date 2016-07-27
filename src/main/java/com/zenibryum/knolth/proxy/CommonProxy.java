package com.zenibryum.knolth.proxy;

import com.zenibryum.knolth.Knolth;
import com.zenibryum.knolth.KnolthGuiHandler;

import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void registerRenders(){
		
	}
	
	public void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Knolth.instance, new KnolthGuiHandler());
	}
}
