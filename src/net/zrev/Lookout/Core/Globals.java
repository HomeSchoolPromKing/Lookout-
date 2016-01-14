package net.zrev.Lookout.Core;


import net.zrev.Lookout.Account.UserPreferences;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

public class Globals {
	
	public static void scaling(){
		if(Display.wasResized() || !Display.isResizable()) {
			Display.setResizable(true);
		}
		Globals.width = ( (float)  Display.getWidth()) / ((float) Display.getWidth() / 1920);
		Globals.height = ( (float)  Display.getHeight()) / ((float) Display.getHeight() / 1080);
	}

	public static int delta = 0;
	public static float mouseX = 0, mouseY = 0; 
	public static float scaleX = (float) Display.getWidth() / UserPreferences.startingResX;
	public static float scaleY = (float) Display.getHeight() / UserPreferences.startingResY;
	public static float width = 1920;
	public static float height = 1080;

	public static boolean DEBUG = false;
}
