package com.zenibryum.knolth.gui;

import com.zenibryum.knolth.Knolth;
import com.zenibryum.knolth.tileentity.ContainerCentrifuge;
import com.zenibryum.knolth.tileentity.ContainerElectricFurnace;
import com.zenibryum.knolth.tileentity.ContainerGrinder;
import com.zenibryum.knolth.tileentity.ContainerVacuum;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHandlerGrinder implements IGuiHandler
{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, 

          World world, int x, int y, int z) 

    { 
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity != null)
        {
            if (ID == Knolth.GUI_ENUM.GRINDER.ordinal())
            {
                return new ContainerGrinder(player.inventory, (IInventory)tileEntity);
            }
            if (ID == Knolth.GUI_ENUM.CENTRIFUGE.ordinal())
            {
                return new ContainerCentrifuge(player.inventory, (IInventory)tileEntity);
            }
            
            if (ID == Knolth.GUI_ENUM.VACUUM.ordinal())
            {
                return new ContainerVacuum(player.inventory, (IInventory)tileEntity);
            }
            
            if (ID == Knolth.GUI_ENUM.HEATER.ordinal())
            {
                return new ContainerElectricFurnace(player.inventory, (IInventory)tileEntity);
            }

        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, 

          World world, int x, int y, int z)

    {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity != null)
        {
            if (ID == Knolth.GUI_ENUM.GRINDER.ordinal())
            {
                return new GuiGrinder(player.inventory, 

                      (IInventory)tileEntity);
            }
            if (ID == Knolth.GUI_ENUM.CENTRIFUGE.ordinal())
            {
                return new GuiCentrifuge(player.inventory, (IInventory)tileEntity);
            }
            
            if (ID == Knolth.GUI_ENUM.VACUUM.ordinal())
            {
                return new GuiVacuum(player.inventory, (IInventory)tileEntity);
            }
            
            if (ID == Knolth.GUI_ENUM.HEATER.ordinal())
            {
                return new GuiElectricFurnace(player.inventory, (IInventory)tileEntity);
            }
        }

        return null;
    }
}

