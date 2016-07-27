package com.zenibryum.knolth.gui.manual;

import java.io.IOException;

import com.zenibryum.knolth.Knolth;
import com.zenibryum.knolth.Reference;
import com.zenibryum.knolth.init.KnolthItems;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiManualCh extends GuiScreen{
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	    this.drawDefaultBackground();
	    this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/manual.png"));
		this.drawModalRectWithCustomSizedTexture(150, 40, 0, 0, 384, 266, 384, 266);
		drawOverlay();
		//drawText();
		System.out.println("ManualCh");
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
	
	@Override
	public void initGui() {
	}
	
	protected void actionPerformed(GuiButton button) throws IOException {
	}
	
	public void drawOverlay() {
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/manual_temp_uranium.png"));
		this.drawModalRectWithCustomSizedTexture(150, 40, 0, 0, 384, 266, 384, 266); //403, 42
	}
}
