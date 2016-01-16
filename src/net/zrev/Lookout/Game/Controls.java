package net.zrev.Lookout.Game;

import static net.zrev.Lookout.Core.Globals.scaleX;
import static net.zrev.Lookout.Core.Globals.scaleY;
import net.zrev.Lookout.Core.Core;
import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.GameEditor.GameEditor;

public class Controls {
	
	public static void keyPressed(int key, char c) {
		if(key == 200) {
			Camera.y -= 32;
		}
		else if(key == 208) {
			Camera.y += 32;
		}
		if(key == 203) {
			Camera.x -= 32;
		}
		else if(key == 205) {
			Camera.x += 32;
		}
		if(Character.isDigit(c)) {
			if(Integer.parseInt(c+"") <= Game.items.size()) {
				Game.itemSelected = Integer.parseInt(c+"")-1;
			}
			if(Integer.parseInt(c+"") <= GameEditor.items.size()) {
				GameEditor.itemSelected = Integer.parseInt(c+"")-1;
			}
		}
		if(c == 'g' || c == 'G'){
			if(Core.state == 1) {
				GameEditor.init();
				Core.state = 2;
			}
			else if(Core.state == 2) {
				Core.state = 1;
			}
		}
	}

	public static void keyReleased(int key, char c) {
	}

	public static void mousePressed(int button, int x, int y) {
		if(button == 0)
			Game.placeObject();
		else if(button == 1) {
			Game.removeObject();
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
			if (Game.itemSelected + change <= Game.items.size() - 1) {
				Game.itemSelected++;
			}
		}
	}
	
	public static void mouseDragged(int ox, int oy, int nx, int ny) {
	}
	
	public static void mouseMoved(int ox, int oy, int nx, int ny) {
		float tempX = (float) Core.input.getMouseX() / scaleX;
		float tempY = (float) Core.input.getMouseY() / scaleY;
		Globals.mouseX = (int) tempX + Camera.x + Game.p.velocityX;
		Globals.mouseY = (int) tempY + Game.p.velocityY;
	}
	
	public static void mouseReleased(int button, int x, int y) {
	}
}
