package net.zrev.Lookout.Game;

import static net.zrev.Lookout.Core.Globals.*;
import net.zrev.Lookout.Core.Core;
import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Core.Logic;
import net.zrev.Lookout.Core.Screen;
import net.zrev.Lookout.GameEditor.GameEditor;

public class Controls {

	public static void keyPressed(int key, char c) {
		Screen.keyPressed(key, c);
		if(key == 42) {
			Logic.shiftHeld = true;
		}
		
		if(Character.isDigit(c)) {
			if(Integer.parseInt(c+"") <= Game.currentLevel.inventory.size()) {
				Game.itemSelected = Integer.parseInt(c+"")-1;
			}
			if(Integer.parseInt(c+"") <= GameEditor.items.size()) {
				GameEditor.itemSelected = Integer.parseInt(c+"")-1;
			}
		}
		if(c == 'g' || c == 'G'){
			if(state == IN_GAME) {
				GameEditor.init();
				Logic.changeState();
				state = IN_EDITOR;
			}
			else if(state == IN_EDITOR) {
				Logic.changeState();
				state = IN_GAME;
			}
		}
	}

	public static void keyReleased(int key, char c) {
		if(key == 42) {
			Logic.shiftHeld = false;
		}
	}

	public static void mousePressed(int button, int x, int y) {
		Logic.mousePressed = true;
		if(state == IN_GAME) {
			if(button == 0) {
				Logic.placeObject();
			}
			else if(button == 1 && state == IN_GAME) {
				Logic.removeObject();
			}
		}
		if(button == 1) {
			Logic.rightMousePressed = true;
		}
		
		if(state == IN_EDITOR) {
			GameEditor.mousePressed(button, x, y);
		}
	}

	public static void mouseWheelMoved(int change) {
		change = change / Math.abs(change);
		if(change < 0) {
			if (Game.itemSelected + change >= 0) {
				Game.itemSelected--;
			}
		}
		if(change > 0 ) {
			if (Game.itemSelected + change <= Game.currentLevel.inventory.size() - 1) {
				Game.itemSelected++;
			}
		}
		if(state == IN_EDITOR) {
			GameEditor.mouseWheelMoved(change);
		}
	}

	public static void mouseDragged(int ox, int oy, int nx, int ny) {
		if(state == IN_EDITOR) {
			GameEditor.mouseDragged(ox, oy, nx,ny);
		}
	}

	public static void mouseMoved(int ox, int oy, int nx, int ny) {
		float tempX = (float) Core.input.getMouseX() / scaleX;
		float tempY = (float) Core.input.getMouseY() / scaleY;
		if(state == IN_GAME) {
			Globals.mouseX = (int) tempX + Camera.x - Game.p.velocityX;
			Globals.mouseY = (int) tempY - Game.p.velocityY + Camera.y;
		}
		if(state == IN_EDITOR) {
			GameEditor.mouseMoved(ox, oy, nx,ny);
		}
	}

	public static void mouseReleased(int button, int x, int y) {
		Logic.mousePressed = false;
		if(button == 1) {
			Logic.rightMousePressed = true;
		}
	}
}
