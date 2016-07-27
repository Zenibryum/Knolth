package com.zenibryum.knolth.gui;

import net.minecraft.client.gui.GuiButton;

public class MyGuiButton extends GuiButton{
	public MyGuiButton(int buttonId, int x, int y, String buttonText)
    {
        this(buttonId, x, y, 200, 20, buttonText);
    }
	
    public MyGuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, boolean enabled, boolean visible)
    {
    	super(buttonId, x, y, widthIn, heightIn, buttonText);
        super.enabled = enabled;
        super.visible = visible;
    }
	
	public MyGuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		this(buttonId, x, y, widthIn, heightIn, buttonText, true, true);
	}
}
