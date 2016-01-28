package net.zrev.Lookout.Core;


import java.awt.image.BufferedImage;

import net.zrev.Lookout.Account.UserPreferences;
import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Screens.GameEditorScreen;
import net.zrev.Lookout.Screens.GameScreen;
import net.zrev.Lookout.Screens.StartMenuScreen;

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
		Globals.width = (Display.getWidth()) / ((Display.getWidth() / 1920F) * Core.zoom);
		Globals.height = ( Display.getHeight()) / ((Display.getHeight() / 1080F) * Core.zoom);

	}
	
	public static void setGameState(Screen newState){
		state = newState;
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

	
	public static final Screen IN_GAME = new GameScreen();
	public static final  Screen IN_EDITOR = new GameEditorScreen();
	
	public static final int IN_END_LEVEL = 3;
	
	public static final Screen HOME_MENU = new StartMenuScreen();
	
	public static Screen state = HOME_MENU;
	
	public static boolean DEBUG = false;
}
