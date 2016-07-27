package com.zenibryum.knolth.gui;

import com.zenibryum.knolth.tileentity.KnolthTileEntity;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiKnolthTileEntity extends GuiContainer {
    public GuiKnolthTileEntity(InventoryPlayer playerInv, KnolthTileEntity te) {
        super(new ContainerKnolthTileEntity(playerInv, te));
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    
    }
}