package net.zrev.Lookout.Core;


import java.awt.image.BufferedImage;

import net.zrev.Lookout.Account.UserPreferences;
import net.zrev.Lookout.Game.Camera;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;

public class Globals {
	
	public static void scaling(){
		if(Display.wasResized() || !Display.isResizable()) {
			Display.setResizable(true);
			Camera.width = Globals.width;
			Camera.height = Globals.height;
		}
		Globals.width = ( (float)  Display.getWidth()) / ((float) Display.getWidth() / 1920);
		Globals.height = ( (float)  Display.getHeight()) / ((float) Display.getHeight() / 1080);

	}
	
	public static void hideCursor(){
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		try {
			Core.gameC.setMouseCursor("/images/blankCursor.png", 0, 0);
		} 
		catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public static int delta = 0;
	public static float mouseX = 0, mouseY = 0; 
	public static float scaleX = (float) Display.getWidth() / UserPreferences.startingResX;
	public static float scaleY = (float) Display.getHeight() / UserPreferences.startingResY;
	public static float width = 1920;
	public static float height = 1080;

	public static int state = 1;
	public static final int IN_GAME = 1;
	public static final  int IN_EDITOR = 2;
	
	public static boolean DEBUG = false;
}
