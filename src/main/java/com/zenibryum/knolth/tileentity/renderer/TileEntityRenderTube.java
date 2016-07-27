package com.zenibryum.knolth.tileentity.renderer;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.zenibryum.knolth.Reference;
import com.zenibryum.knolth.tileentity.TileEntityCoordTransporter;
import com.zenibryum.knolth.tileentity.TileEntityTube;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRenderTube extends TileEntitySpecialRenderer{
	
	private static ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "models/block/tube.png");
	static float x = 11F/32F;
	static float texturePixel = 1F/32F;
	static boolean drawInside = true;
	
	private static ArrayList<point> PointsCore = new ArrayList<point>();
	
	private static ArrayList<point> PointsConnection = new ArrayList<point>();
	
	private static ArrayList<point> PointsStraight = new ArrayList<point>();
	
	public static void init() {
		ConnectionInit();
		CoreInit();
		StraightInit();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double translationX, double translationY, double translationZ, float f, int destroyStage) {
		//Points.add(new point(1-x, x, 1-x, y, y));
		GL11.glTranslated(translationX, translationY, translationZ);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.bindTexture(texture);
		
		TileEntityTube tube = (TileEntityTube) tileentity;
		
		if ( !tube.onlyOneOpposite() ){
			drawCore(tileentity);
			for(int i = 0; i < tube.dirs.length; i++){
				if (tube.dirs[i]!=null){
					drawConnection(tube.dirs[i]);
				}
			}
		}
		else{
			for(int i = 0; i < tube.dirs.length; i++){
				if (tube.dirs[i]!=null){
					drawStraight(tube.dirs[i]);
					break;
				}
			}
		}
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-translationX, -translationY, -translationZ);
	}
	
	public void drawStraight(EnumFacing dir) {
		Tessellator tess = Tessellator.getInstance();
		WorldRenderer wr = tess.getWorldRenderer();
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		
		if( dir.equals(EnumFacing.NORTH) || dir.equals(EnumFacing.SOUTH) ) {
			GL11.glRotatef(90, 1, 0, 0);
		} else if( dir.equals(EnumFacing.WEST) || dir.equals(EnumFacing.EAST) ) {
			GL11.glRotatef(90, 0, 0, 1);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		for(point p : PointsStraight) {
			wr.pos(p.x, p.y, p.z);
			wr.tex(p.u, p.v);
			wr.endVertex();
		}
		tess.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		
		if( dir.equals(EnumFacing.NORTH) || dir.equals(EnumFacing.SOUTH) ) {
			GL11.glRotatef(-90, 1, 0, 0);
		} else if( dir.equals(EnumFacing.WEST) || dir.equals(EnumFacing.EAST) ) {
			GL11.glRotatef(-90, 0, 0, 1);
		}
		
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		//PointsConnection = new ArrayList<point>();
	}
	
	public void drawConnection(EnumFacing dir) {
		Tessellator tess = Tessellator.getInstance();
		WorldRenderer wr = tess.getWorldRenderer();
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(dir.equals(EnumFacing.UP)) {
			//...
		} else if(dir.equals(EnumFacing.DOWN)) {
			GL11.glRotatef(180, 1, 0, 0);
		} else if(dir.equals(EnumFacing.NORTH)) {
			GL11.glRotatef(270, 1, 0, 0);
		} else if(dir.equals(EnumFacing.SOUTH)) {
			GL11.glRotatef(90, 1, 0, 0);
		} else if(dir.equals(EnumFacing.WEST)) {
			GL11.glRotatef(90, 0, 0, 1);
		} else if(dir.equals(EnumFacing.EAST)) {
			GL11.glRotatef(270, 0, 0, 1);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		for(point p : PointsConnection) {
			wr.pos(p.x, p.y, p.z);
			wr.tex(p.u, p.v);
			wr.endVertex();
		}
		tess.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(dir.equals(EnumFacing.UP)) {
			
		} else if(dir.equals(EnumFacing.DOWN)) {
			GL11.glRotatef(-180, 1, 0, 0);
		} else if(dir.equals(EnumFacing.NORTH)) {
			GL11.glRotatef(-270, 1, 0, 0);
		} else if(dir.equals(EnumFacing.SOUTH)) {
			GL11.glRotatef(-90, 1, 0, 0);
		} else if(dir.equals(EnumFacing.WEST)) {
			GL11.glRotatef(-90, 0, 0, 1);
		} else if(dir.equals(EnumFacing.EAST)) {
			GL11.glRotatef(-270, 0, 0, 1);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		//PointsConnection = new ArrayList<point>();
	}
	
	public void drawCore(TileEntity tileentity) {
		Tessellator tess = Tessellator.getInstance();
		WorldRenderer wr = tess.getWorldRenderer();
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		for (point p : PointsCore) {
			wr.pos(p.x, p.y, p.z);
			wr.tex(p.u, p.v);
			wr.endVertex();
		}
		tess.draw();
	}
	
	public static class point {
		private float x = 0F;
		private float y = 0F;
		private float z = 0F;
		private float u = 0F;
		private float v = 0F;
		
		public point(float x, float y, float z, float u, float v){
			this.x = x;
			this.y = y;
			this.z = z;
			this.u = u;
			this.v = v;
		}
	}
	
	private static void StraightInit() {
		PointsStraight.add(new point(1-x, 0, 1-x, 10*texturePixel, 5*texturePixel));
		PointsStraight.add(new point(1-x, 1, 1-x, 26*texturePixel, 5*texturePixel));
		PointsStraight.add(new point(x, 1, 1-x, 26*texturePixel, 0));
		PointsStraight.add(new point(x, 0, 1-x, 10*texturePixel, 0));

		PointsStraight.add(new point(x, 0, x, 10*texturePixel, 5*texturePixel));
		PointsStraight.add(new point(x, 1, x, 26*texturePixel, 5*texturePixel));
		PointsStraight.add(new point(1-x, 1, x, 26*texturePixel, 0));
		PointsStraight.add(new point(1-x, 0, x, 10*texturePixel, 0));
		
		PointsStraight.add(new point(1-x, 0, x, 10*texturePixel, 5*texturePixel));
		PointsStraight.add(new point(1-x, 1, x, 26*texturePixel, 5*texturePixel));
		PointsStraight.add(new point(1-x, 1, 1-x, 26*texturePixel, 0));
		PointsStraight.add(new point(1-x, 0, 1-x, 10*texturePixel, 0));
		
		PointsStraight.add(new point(x, 0, 1-x, 10*texturePixel, 5*texturePixel));
		PointsStraight.add(new point(x, 1, 1-x, 26*texturePixel, 5*texturePixel));
		PointsStraight.add(new point(x, 1, x, 26*texturePixel, 0));
		PointsStraight.add(new point(x, 0, x, 10*texturePixel, 0));
		
		if(drawInside) {
			PointsStraight.add(new point(x, 0, 1-x, 10*texturePixel, 0));
			PointsStraight.add(new point(x, 1, 1-x, 26*texturePixel, 0));
			PointsStraight.add(new point(1-x, 1, 1-x, 26*texturePixel, 5*texturePixel));
			PointsStraight.add(new point(1-x, 0, 1-x, 10*texturePixel, 5*texturePixel));
			
			PointsStraight.add(new point(1-x, 0, x, 10*texturePixel, 0));
			PointsStraight.add(new point(1-x, 1, x, 26*texturePixel, 0));
			PointsStraight.add(new point(x, 1, x, 26*texturePixel, 5*texturePixel));
			PointsStraight.add(new point(x, 0, x, 10*texturePixel, 5*texturePixel));
			
			PointsStraight.add(new point(1-x, 0, 1-x, 10*texturePixel, 0));
			PointsStraight.add(new point(1-x, 1, 1-x, 26*texturePixel, 0));
			PointsStraight.add(new point(1-x, 1, x, 26*texturePixel, 5*texturePixel));
			PointsStraight.add(new point(1-x, 0, x, 10*texturePixel, 5*texturePixel));
			
			PointsStraight.add(new point(x, 0, x, 10*texturePixel, 0));
			PointsStraight.add(new point(x, 1, x, 26*texturePixel, 0));
			PointsStraight.add(new point(x, 1, 1-x, 26*texturePixel, 5*texturePixel));
			PointsStraight.add(new point(x, 0, 1-x, 10*texturePixel, 5*texturePixel));
		}
	}
	
	private static void ConnectionInit() {
		PointsConnection.add(new point(1-x, 1-x, 1-x, 5*texturePixel, 5*texturePixel));
		PointsConnection.add(new point(1-x, 1, 1-x, 10*texturePixel, 5*texturePixel));
		PointsConnection.add(new point(x, 1, 1-x, 10*texturePixel, 0));
		PointsConnection.add(new point(x, 1-x, 1-x, 5*texturePixel, 0));

		PointsConnection.add(new point(x, 1-x, x, 5*texturePixel, 5*texturePixel));
		PointsConnection.add(new point(x, 1, x, 10*texturePixel, 5*texturePixel));
		PointsConnection.add(new point(1-x, 1, x, 10*texturePixel, 0));
		PointsConnection.add(new point(1-x, 1-x, x, 5*texturePixel, 0));
		
		PointsConnection.add(new point(1-x, 1-x, x, 5*texturePixel, 5*texturePixel));
		PointsConnection.add(new point(1-x, 1, x, 10*texturePixel, 5*texturePixel));
		PointsConnection.add(new point(1-x, 1, 1-x, 10*texturePixel, 0));
		PointsConnection.add(new point(1-x, 1-x, 1-x, 5*texturePixel, 0));
		
		PointsConnection.add(new point(x, 1-x, 1-x, 5*texturePixel, 5*texturePixel));
		PointsConnection.add(new point(x, 1, 1-x, 10*texturePixel, 5*texturePixel));
		PointsConnection.add(new point(x, 1, x, 10*texturePixel, 0));
		PointsConnection.add(new point(x, 1-x, x, 5*texturePixel, 0));
		
		if(drawInside) {
			PointsConnection.add(new point(x, 1-x, 1-x, 5*texturePixel, 5*texturePixel));
			PointsConnection.add(new point(x, 1, 1-x, 10*texturePixel, 5*texturePixel));
			PointsConnection.add(new point(1-x, 1, 1-x, 10*texturePixel, 0));
			PointsConnection.add(new point(1-x, 1-x, 1-x, 5*texturePixel, 0));
			
			PointsConnection.add(new point(1-x, 1-x, x, 5*texturePixel, 5*texturePixel));
			PointsConnection.add(new point(1-x, 1, x, 10*texturePixel, 5*texturePixel));
			PointsConnection.add(new point(x, 1, x, 10*texturePixel, 0));
			PointsConnection.add(new point(x, 1-x, x, 5*texturePixel, 0));
			
			PointsConnection.add(new point(1-x, 1-x, 1-x, 5*texturePixel, 5*texturePixel));
			PointsConnection.add(new point(1-x, 1, 1-x, 10*texturePixel, 5*texturePixel));
			PointsConnection.add(new point(1-x, 1, x, 10*texturePixel, 0));
			PointsConnection.add(new point(1-x, 1-x, x, 5*texturePixel, 0));
			
			PointsConnection.add(new point(x, 1-x, x, 5*texturePixel, 5*texturePixel));
			PointsConnection.add(new point(x, 1, x, 10*texturePixel, 5*texturePixel));
			PointsConnection.add(new point(x, 1, 1-x, 10*texturePixel, 0));
			PointsConnection.add(new point(x, 1-x, 1-x, 5*texturePixel, 0));
		}
	}
	
	private static void CoreInit() {
		PointsCore.add(new point(1-x, x, 1-x, 5*texturePixel, 5*texturePixel));
		PointsCore.add(new point(1-x, 1-x, 1-x, 5*texturePixel, 0));
		PointsCore.add(new point(x, 1-x, 1-x, 0, 0));
		PointsCore.add(new point(x, x, 1-x, 0, 5*texturePixel));
		
		PointsCore.add(new point(1-x, x, x, 5*texturePixel, 5*texturePixel));
		PointsCore.add(new point(1-x, 1-x, x, 5*texturePixel, 0));
		PointsCore.add(new point(1-x, 1-x, 1-x, 0, 0));
		PointsCore.add(new point(1-x, x, 1-x, 0, 5*texturePixel));
		
		PointsCore.add(new point(1-x, 1-x, 1-x, 5*texturePixel, 5*texturePixel));
		PointsCore.add(new point(1-x, 1-x, x, 5*texturePixel, 0));
		PointsCore.add(new point(x, 1-x, x, 0, 0));
		PointsCore.add(new point(x, 1-x, 1-x, 0, 5*texturePixel));
		
		PointsCore.add(new point(x, x, x, 5*texturePixel, 5*texturePixel));
		PointsCore.add(new point(x, 1-x, x, 5*texturePixel, 0));
		PointsCore.add(new point(1-x, 1-x, x, 0, 0));
		PointsCore.add(new point(1-x, x, x, 0, 5*texturePixel));
		
		PointsCore.add(new point(x, x, 1-x, 5*texturePixel, 5*texturePixel));
		PointsCore.add(new point(x, 1-x, 1-x, 5*texturePixel, 0));
		PointsCore.add(new point(x, 1-x, x, 0, 0));
		PointsCore.add(new point(x, x, x, 0, 5*texturePixel));
		
		PointsCore.add(new point(x, x, 1-x, 5*texturePixel, 5*texturePixel));
		PointsCore.add(new point(x, x, x, 5*texturePixel, 0));
		PointsCore.add(new point(1-x, x, x, 0, 0));
		PointsCore.add(new point(1-x, x, 1-x, 0, 5*texturePixel));
		
		if(drawInside) {
			PointsCore.add(new point(x, x, 1-x, 5*texturePixel, 5*texturePixel));
			PointsCore.add(new point(x, 1-x, 1-x, 5*texturePixel, 0));
			PointsCore.add(new point(1-x, 1-x, 1-x, 0, 0));
			PointsCore.add(new point(1-x, x, 1-x, 0, 5*texturePixel));
			
			PointsCore.add(new point(1-x, x, 1-x, 5*texturePixel, 5*texturePixel));
			PointsCore.add(new point(1-x, 1-x, 1-x, 5*texturePixel, 0));
			PointsCore.add(new point(1-x, 1-x, 1-x, 0, 0));
			PointsCore.add(new point(1-x, x, x, 0, 5*texturePixel));
			
			PointsCore.add(new point(x, 1-x, 1-x, 5*texturePixel, 5*texturePixel));
			PointsCore.add(new point(x, 1-x, x, 5*texturePixel, 0));
			PointsCore.add(new point(1-x, 1-x, x, 0, 0));
			PointsCore.add(new point(1-x, 1-x, 1-x, 0, 5*texturePixel));
			
			PointsCore.add(new point(1-x, x, x, 5*texturePixel, 5*texturePixel));
			PointsCore.add(new point(1-x, 1-x, x, 5*texturePixel, 0));
			PointsCore.add(new point(x, 1-x, x, 0, 0));
			PointsCore.add(new point(x, x, x, 0, 5*texturePixel));
			
			PointsCore.add(new point(x, x, x, 5*texturePixel, 5*texturePixel));
			PointsCore.add(new point(x, 1-x, x, 5*texturePixel, 0));
			PointsCore.add(new point(x, 1-x, 1-x, 0, 0));
			PointsCore.add(new point(x, x, 1-x, 0, 5*texturePixel));
			
			PointsCore.add(new point(1-x, x, 1-x, 5*texturePixel, 5*texturePixel));
			PointsCore.add(new point(1-x, x, x, 5*texturePixel, 0));
			PointsCore.add(new point(x, x, x, 0, 0));
			PointsCore.add(new point(x, x, 1-x, 0, 5*texturePixel));
		}
	}
}
