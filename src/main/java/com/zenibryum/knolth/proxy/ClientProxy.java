package com.zenibryum.knolth.proxy;

import com.zenibryum.knolth.init.KnolthBlocks;
import com.zenibryum.knolth.init.KnolthItems;
import com.zenibryum.knolth.tileentity.TileEntityTube;
import com.zenibryum.knolth.tileentity.renderer.TileEntityRenderTube;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenders(){
		KnolthBlocks.registerRenders();
		KnolthItems.registerRenders();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTube.class, new TileEntityRenderTube());
	}
}
