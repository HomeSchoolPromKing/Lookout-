package net.zrev.Lookout.Core;

import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Game.Game;
import net.zrev.Lookout.GameEditor.GameEditor;
import net.zrev.Lookout.Screens.GameEditorScreen;
import net.zrev.Lookout.Screens.GameScreen;
import net.zrev.Lookout.Screens.StartMenuScreen;

import org.newdawn.slick.Graphics;

import static net.zrev.Lookout.Core.Globals.*;
public class Screen {
	
	public static void draw(Graphics g ){
		if(CURRENT_SCREEN == IN_GAME) {
			GameScreen.draw(g);
			Game.update();
		}
		else if(CURRENT_SCREEN == IN_EDITOR) {
			GameEditorScreen.draw(g);
		}
		//else if(state == IN_END_LEVEL) {
			
		//}
		else if(CURRENT_SCREEN == HOME_MENU) {
			((StartMenuScreen) HOME_MENU).paint(g);
		}
	}
	
	public static void keyPressed(int key, char c) {
		if(key == 42) {
			shiftHeld = true;
		}
		
		if(c == 'g' || c == 'G'){
			if(CURRENT_SCREEN == IN_GAME) {
				GameEditor.init();
				CURRENT_SCREEN = IN_EDITOR;
			}
			else if(CURRENT_SCREEN == IN_EDITOR) {
				GameEditor.changeState();
				CURRENT_SCREEN = IN_GAME;
			}
		}
		if(CURRENT_SCREEN == HOME_MENU) {
			((StartMenuScreen) HOME_MENU).inputHandler(key, c);
		}
		
		if(CURRENT_SCREEN == IN_EDITOR) {
			GameEditor.keyPressed(key, c);
		}
	}
	
	public static void mouseMoved(int ox, int oy, int nx, int ny) {
		float tempX = (float) Core.input.getMouseX() / scaleX;
		float tempY = (float) Core.input.getMouseY() / scaleY;
		if(CURRENT_SCREEN == IN_GAME) {
			Globals.mouseX = (int) tempX + Camera.x - Game.p.velocityX;
			Globals.mouseY = (int) tempY - Game.p.velocityY + Camera.y;
		}
		if(CURRENT_SCREEN == IN_EDITOR) {
			Globals.mouseX = (int) tempX + Camera.x - Game.p.velocityX;
			Globals.mouseY = (int) tempY - Game.p.velocityY + Camera.y;
			GameEditor.mouseMoved(ox, oy, nx,ny);
		}
	}
	
	public static void mouseDragged(int ox, int oy, int nx, int ny) {
		if(CURRENT_SCREEN == IN_EDITOR) {
			GameEditor.mouseDragged(ox, oy, nx,ny);
		}
	}
	
	public static void mouseWheelMoved(int change) {
		if(CURRENT_SCREEN == IN_GAME) {
			Game.mouseWheelMoved(change);
		}
		if(CURRENT_SCREEN == IN_EDITOR) {
			GameEditor.mouseWheelMoved(change);
		}
	}

	public static void keyReleased(int key, char c) {
		if(key == 42) {
			shiftHeld = false;
		}
		if(CURRENT_SCREEN == IN_EDITOR) {
			GameEditor.keyReleased(key, c);
		}
	}

	public static void mousePressed(int button, int x, int y) {
		mousePressed = true;
		if(CURRENT_SCREEN == IN_GAME) {
			if(button == 0) {
				Game.placeObject();
			}
			else if(button == 1 && CURRENT_SCREEN == IN_GAME) {
				Game.removeObject();
			}
		}
		if(button == 1) {
			rightMousePressed = true;
		}
		
		if(CURRENT_SCREEN == IN_EDITOR) {
			GameEditor.mousePressed(button, x, y);
		}
	}
	
	public static void mouseReleased(int button, int x, int y) {
		mousePressed = false;
		if(button == 1) {
			rightMousePressed = true;
		}
	}
	
	public static boolean shiftHeld = false;
	public static boolean mousePressed = false;
	public static boolean rightMousePressed = false;
	
}
