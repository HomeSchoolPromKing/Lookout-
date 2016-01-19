package net.zrev.Lookout.Core;

import org.newdawn.slick.geom.Rectangle;

import net.zrev.Lookout.Game.Game;
import net.zrev.Lookout.Game.Level;
import net.zrev.Lookout.GameEditor.GameEditor;
import net.zrev.Lookout.GameEditor.Placeable;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.Screens.GameEditorScreen;
import net.zrev.Lookout.Screens.GameScreen;
import static net.zrev.Lookout.Core.Globals.*;

public class Logic {
	public static void logic(){
		if(state == IN_GAME) {
			Game.logic();
			hideCursor();
		}
		else if(state == IN_EDITOR) {
		}
	}


	public static void changeState(){
		for(Entity e : Game.currentLevel.gameObjects) {
			e.isSelected = false;
		}
	}

	public static void removeObject() {
		for(Entity e : Game.currentLevel.gameObjects) {
			if(new Rectangle(Globals.mouseX, Globals.mouseY, 2, 2).intersects(e.getBoundingBox())) {
				if(e.wasPlaced)
					Game.currentLevel.toRemove = e;
			}
		}
	}

	public static void placeObject(){

		Entity e =  (Entity) Game.currentLevel.inventory.get(Game.itemSelected).clone();
		float x, y;
		if(GameEditor.snapObjects && Globals.state == Globals.IN_EDITOR) {
			x = Math.round(Globals.mouseX / 32) * 32;
			y = Math.round(Globals.mouseY / 32) * 32;
		}
		else {
			x =  Globals.mouseX;
			y =  Globals.mouseY;
		}

		try {
			e.x = x;
			e.y = y;
			e.wasPlaced = true;
			e.updateBounds();
			Game.currentLevel.gameObjects.add(e);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	public static boolean shiftHeld = false;
	public static boolean mousePressed = false;
}
