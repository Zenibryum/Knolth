package com.zenibryum.knolth.init;

import com.zenibryum.knolth.tileentity.TileEntityCoordTransporter;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TeleportTileEntities {
	public static void register()
	{
		GameRegistry.registerTileEntity(TileEntityCoordTransporter.class, "knolthCoordTransporter");
	}
}
