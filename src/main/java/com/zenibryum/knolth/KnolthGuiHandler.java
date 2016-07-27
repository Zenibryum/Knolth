package com.zenibryum.knolth;

import com.zenibryum.knolth.gui.ContainerKnolthTileEntity;
import com.zenibryum.knolth.gui.manual.GuiManual;
import com.zenibryum.knolth.gui.manual.GuiManualCh;
import com.zenibryum.knolth.tileentity.KnolthTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KnolthGuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		System.out.println("ID HANDLING SERVER");
       	return new ContainerKnolthTileEntity(player.inventory, (KnolthTileEntity) tileEntity);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		System.out.println("ID HANDLING TESTING");
       	if(ID == 0){
       		return new GuiManual();
		}
		return null;
	}
}
