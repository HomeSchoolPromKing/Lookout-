package net.zrev.Core.Lookout;


import net.zrev.Lookout.Account.UserPreferences;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

public class Globals {
	
	public static void scaling(){
//		if(Display.getWidth() != gc.getWidth() || Display.getHeight() != gc.getHeight()) {
//			try {
//				scaleX = (float) Display.getWidth() / startingResX;
//				scaleY = (float) (Display.getHeight() - guiOffset) / startingResY;
//				appgc.setDisplayMode(Display.getWidth(), Display.getHeight(), false);
//				appgc.reinit();
//				Display.setResizable(true);
//			} 
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}

		//scaleX = (float) Display.getWidth() / startingResX;
		//scaleY = (float) (Display.getHeight() - guiOffset)  / startingResY;
		
		if(Display.wasResized() || !Display.isResizable()) {
			Display.setResizable(true);
		}
	}
	

	public static int delta = 0;
	public static float mouseX = 0, mouseY = 0; 
	public static float scaleX = (float) Display.getWidth() / UserPreferences.startingResX;
	public static float scaleY = (float) Display.getHeight() / UserPreferences.startingResY;
	public static float width = 1920;
	public static float height = 1080;

	public static boolean DEBUG = false;
}
