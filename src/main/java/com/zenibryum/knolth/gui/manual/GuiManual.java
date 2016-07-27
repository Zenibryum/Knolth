package com.zenibryum.knolth.gui.manual;

import java.io.IOException;

import com.zenibryum.knolth.Configs;
import com.zenibryum.knolth.Reference;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiManual extends GuiScreen{
	private int mouseX, mouseY;
	private float partialTicks;
	
	public static ResourceLocation manual, overlay0, plo, pao, clo, cao, page;
	
	/*pl - physics lesson
	 * pa - physics application
	 * cl - chemistry lesson
	 * ca - chemistry application
	 * 
	 * Suffixes :
	 * r = resource ( texture )
	 * t = title
	 * o = overlay
	 * n = number
	 * b = button
	 * 
	 */
	
	public static ResourceLocation[] plr = new ResourceLocation[100];
	public static ResourceLocation[] par = new ResourceLocation[100];
	public static ResourceLocation[] clr = new ResourceLocation[100];
	public static ResourceLocation[] car = new ResourceLocation[100];
	
	private static GuiButton home, ph, ch, papps, pless, capps, cless;
	
	private static GuiButton[] plb = new GuiButton[100];
	private static GuiButton[] pab = new GuiButton[100];
	private static GuiButton[] clb = new GuiButton[100];
	private static GuiButton[] cab = new GuiButton[100];
	
	private static int pln, pan, cln, can;
	private static String[] plt = new String[100];
	private static String[] pat = new String[100];
	private static String[] clt = new String[100];
	private static String[] cat = new String[100];
	
	public static ResourceLocation lesson1;
	
	public static void init() // This is used for loading textures
	{
		//Define manual texture and main menu overlay
		manual = new ResourceLocation(Reference.MOD_ID, "textures/gui/manual.png");
		overlay0 = new ResourceLocation(Reference.MOD_ID, "textures/gui/0.png");
		plo = new ResourceLocation(Reference.MOD_ID, "textures/gui/1.png");
		pao = new ResourceLocation(Reference.MOD_ID, "textures/gui/2.png");
		clo = new ResourceLocation(Reference.MOD_ID, "textures/gui/3.png");
		cao = new ResourceLocation(Reference.MOD_ID, "textures/gui/4.png");
		
		//Define buttons used for navigation below
		home = new GuiButton(1, 353, 62, 60, 13, "Home"); // The home button
		
		ph = new GuiButton(1, 353, 62, 60, 13, "1. Physics");
		ch = new GuiButton(1, 353, 98, 70, 13, "2. Chemistry");
		
		pless = new GuiButton(1, 353, 134, 50, 13, "Physics Lessons");
		papps = new GuiButton(1, 353, 147, 110, 13, "Physics Practical Applications");
		
		cless = new GuiButton(1, 353, 134, 50, 13, "Chemistry Lessons");
		capps = new GuiButton(1, 353, 147, 110, 13, "Chemistry Practical Applications");
		
		//Import lessons and apps below
		
		//lesson1 = new ResourceLocation(Reference.MOD_ID, "textures/gui/cb1.png"); // This will be automatically imported in the future
		
		
		//The first overlay you see is the overlay0 ( main menu overlay )
		page = overlay0;
		
		loadpages();
	}
	
	public static void loadpages()
	{		
		// i = 0 .. 3 - number of pages
		
		/*-Lectii Fizica
		-Aplcatii Fizica
		-Lectii Chimie
		-Aplicatii Chimie
		p - physics
		c - chemistry
		a - lessons
		b - applications
		ex: pa1 - physics lesson 1
		 */
		
		//pln, pan, cln, can;
		
		pln = Integer.parseInt( Configs.Config[0] );
		pan = Integer.parseInt( Configs.Config[1] );
		cln = Integer.parseInt( Configs.Config[2] );
		can = Integer.parseInt( Configs.Config[3] );
		
		for (int i = 0; i < pln; i++) {
    		plt[i] = (Configs.Config[4+i]) ;
    	}
		for (int i = 0; i < pan; i++) {
    		pat[i] = (Configs.Config[4+pln+i]) ;
    	}
		for (int i = 0; i < cln; i++) {
    		clt[i] = (Configs.Config[4+pln+pan+i]) ;
    	}
		for (int i = 0; i < cln; i++) {
    		cat[i] = (Configs.Config[4+pln+pan+cln+i]) ;
		}
		
		//plr,par,clr,car
		
		for ( int i = 0; i < pln; i++ ) //pa[NO.]
		{
			plr[i] = new ResourceLocation(Reference.MOD_ID, "textures/gui/pl" + String.valueOf(i) + ".png");
		}
		for ( int i = 0; i < pan; i++ ) //pa[NO.]
		{
			par[i] = new ResourceLocation(Reference.MOD_ID, "textures/gui/pa" + String.valueOf(i) + ".png");
		}
		for ( int i = 0; i < cln; i++ ) //pa[NO.]
		{
			clr[i] = new ResourceLocation(Reference.MOD_ID, "textures/gui/cl" + String.valueOf(i) + ".png");
		}
		for ( int i = 0; i < can; i++ ) //pa[NO.]
		{
			car[i] = new ResourceLocation(Reference.MOD_ID, "textures/gui/ca" + String.valueOf(i) + ".png");
		}
		
		int yoffset = 0, yincrement = 15;
		for ( int i = 0; i < pln; i++ )
		{
			plb[i] = new GuiButton(1, 353, 76+yoffset, 60, 13, plt[i] );
			yoffset+=yincrement;
		}
		
		yoffset = 0;
		for ( int i = 0; i < pan; i++ )
		{
			pab[i] = new GuiButton(1, 353, 76+yoffset, 60, 13, pat[i] );
			yoffset+=yincrement;
		}
		
		yoffset = 0;
		for ( int i = 0; i < cln; i++ )
		{
			clb[i] = new GuiButton(1, 353, 76+yoffset, 60, 13, clt[i] );
			yoffset+=yincrement;
		}
		
		yoffset = 0;
		for ( int i = 0; i < can; i++ )
		{
			cab[i] = new GuiButton(1, 353, 76+yoffset, 60, 13, cat[i] );
			yoffset+=yincrement;
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	    this.mouseX = mouseX;
	    this.mouseY = mouseY;
	    this.partialTicks = partialTicks;
		this.drawDefaultBackground();
		this.mc.getTextureManager().bindTexture(manual);
		this.drawModalRectWithCustomSizedTexture(150, 40, 0, 0, 384, 266, 384, 266);
		drawOverlay();
		//System.out.println("Manual");
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		//System.out.println("Button list size :" + buttonList.size());
	}
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
	
	
	//plo, pao, clo, cao
	@Override
	public void initGui() {
		this.buttonList.clear();
		if ( page == overlay0 ){ // Main Menu overlay
			this.buttonList.add(ph);
			this.buttonList.add(ch);
		} else if ( page == plo ) // Physics Lessons Menu
		{
			for ( int i = 0; i < pln; i++ )
			{
				this.buttonList.add( plb[i] );
			}
		} else if ( page == pao ) // Physics Applications Menu
		{
			for ( int i = 0; i < pan; i++ )
			{
				this.buttonList.add( pab[i] );
			}
		} else if ( page == clo ) // Physics Applications Menu
		{
			for ( int i = 0; i < cln; i++ )
			{
				this.buttonList.add( clb[i] );
			}
		} else if ( page == cao ) // Physics Applications Menu
		{
			for ( int i = 0; i < can; i++ )
			{
				this.buttonList.add( cab[i] );
			}
		}
		
		if ( page != overlay0 )
		{
			this.buttonList.add(home);
		}
	}
	
	protected void actionPerformed(GuiButton button) throws IOException {
	    if ( button == ph ) {
			this.buttonList.clear();
			this.buttonList.add(ph);
			this.buttonList.add(ch);
			this.buttonList.add(pless);
			this.buttonList.add(papps);
			return;
	    } else if ( button == ch ) {
			this.buttonList.clear();
			this.buttonList.add(ph);
			this.buttonList.add(ch);
			this.buttonList.add(cless);
			this.buttonList.add(capps);
			return;
	    } else if( button == pless ) {
	    	//ADD SOME CODE HERE
	    	/*this.drawDefaultBackground();
		    this.mc.getTextureManager().bindTexture(manual);
			this.drawModalRectWithCustomSizedTexture(150, 40, 0, 0, 384, 266, 384, 266);
		    drawOverlay();
			super.drawScreen(mouseX, mouseY, partialTicks);*/
	    	page = plo;
	    	this.buttonList.clear();
	    	this.buttonList.add(home);
			for ( int i = 0; i < pln; i++ )
			{
				this.buttonList.add( plb[i] );
			}
			return;
	    	
	    } else if ( button == papps ) {
	    	page = pao;
	    	this.buttonList.clear();
	    	this.buttonList.add(home);
			for ( int i = 0; i < pan; i++ )
			{
				this.buttonList.add( pab[i] );
			}
			return;
	    	
	    } else if ( button == cless ) {
	    	page = clo;
	    	this.buttonList.clear();
	    	this.buttonList.add(home);
			for ( int i = 0; i < cln; i++ )
			{
				this.buttonList.add( clb[i] );
			}
			return;
	    	
	    } else if ( button == capps ) {
	    	page = cao;
	    	this.buttonList.clear();
	    	this.buttonList.add(home);
			for ( int i = 0; i < can; i++ )
			{
				this.buttonList.add( cab[i] );
			}
			return;
	    	
	    } else if ( button == home ) {
			this.buttonList.clear();
			this.buttonList.add(ph);
			this.buttonList.add(ch);
			page = overlay0;
			return;
	    }
	    
	    //STUDY THE CASES WHEN YOU PRESS ON A LESSON BUTTON
	    
		for ( int i = 0; i < pln; i++ )
		{
			if ( button == plb[i] )
			{
				this.buttonList.clear();
				this.buttonList.add(home);
				page = plr[i];
			}
		}
		
		for ( int i = 0; i < pan; i++ )
		{
			if ( button == pab[i] )
			{
				this.buttonList.clear();
				this.buttonList.add(home);
				page = par[i];
			}
		}
		
		for ( int i = 0; i < cln; i++ )
		{
			if ( button == clb[i] )
			{
				this.buttonList.clear();
				this.buttonList.add(home);
				page = clr[i];
			}
		}
		
		for ( int i = 0; i < can; i++ )
		{
			if ( button == cab[i] )
			{
				this.buttonList.clear();
				this.buttonList.add(home);
				page = car[i];
			}
		}
	    
	}
	
	public void drawOverlay() {
		this.mc.getTextureManager().bindTexture(page);
		this.drawModalRectWithCustomSizedTexture(150, 40, 0, 0, 384, 266, 384, 266); //403, 42
		this.fontRendererObj.drawString("1. Physics", 353, 62, 0);

	}
	
	public void drawOptions() {

	}
}
