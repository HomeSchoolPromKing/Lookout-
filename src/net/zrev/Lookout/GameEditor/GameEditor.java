package net.zrev.Lookout.GameEditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import net.zrev.Lookout.Core.Core;
import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Core.Logic;
import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Game.Game;
import net.zrev.Lookout.Game.Level;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Jump;
import net.zrev.Lookout.Screens.GameEditorScreen;
import static net.zrev.Lookout.Core.Globals.*;
import static net.zrev.Lookout.GameEditor.GameEditor.snapBox;

public class GameEditor {

	public static void init(){
		Camera.x = 0;
		Camera.y = 0;

		//Init UI items
		linesBox = new Rectangle(Camera.x + (Globals.width - 300) + 10, 250, 200, 30);
		snapBox = new Rectangle(Camera.x + (Globals.width - 300) + 10, 300, 200, 30);
		hideDecBox = new Rectangle(Camera.x + (Globals.width - 300) + 10, 350, 200, 30);

		Core.gameC.setDefaultMouseCursor();
		loadItems();
	}

	public static void update(){
		linesBox = new Rectangle(Camera.x + (Globals.width - 300) + 10, Camera.y + 250, 200, 30);
		snapBox = new Rectangle(Camera.x + (Globals.width - 300) + 10, Camera.y +  300, 200, 30);
		hideDecBox = new Rectangle(Camera.x + (Globals.width - 300) + 10, Camera.y +  350, 200, 30);
	}

	public static void keyPressed(int key, char c) {
		if(key == 211) {
			ArrayList<Entity> removeThese = new ArrayList<Entity>();
			for(Entity e : Game.currentLevel.gameObjects) {
				if(e.isSelected)
					removeThese.add(e); 
			}
			for(Entity e : removeThese) {
				if(Game.currentLevel.gameObjects.contains(e)) {
					Game.currentLevel.gameObjects.remove(e);
				}
			}
			removeThese.clear();
		}
		
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

		if(key == 57) {
			saveLevel();
		}

		if(key == 28) {
			showItemSelect = !showItemSelect;
		}
	}

	public static void mouseDragged(int ox, int oy, int nx, int ny) {
		for(Entity e : Game.currentLevel.gameObjects) {
			if(e.isSelected) {
				if(snapObjects) {
					e.x -= Math.round(((ox / scaleX) - (nx / scaleX)) / 32) * 32;
					e.y -= Math.round(((oy / scaleY) - (ny / scaleY)) / 32) * 32;
					//System.out.println(Globals.mouseX - e.x);
					//e.x = Math.round((Globals.mouseX - e.x) / 32) * 32;
					//e.y = Math.round((Globals.mouseY - e.y) / 32) * 32;
					e.updateBounds();
				}
				else {
					e.x -= (((ox / scaleX) - (nx / scaleX)) / 32) * 32;
					e.y -= (((oy / scaleY) - (ny / scaleY)) / 32) * 32;
					e.updateBounds();
				}
			}
		}
		
		if(Logic.rightMousePressed && Logic.shiftHeld) {
			if(highlight != null) {
				highlight.setWidth((nx / scaleX) - highlight.getX());
				highlight.setHeight((ny / scaleY) - highlight.getY());
			}
		}
	}

	public static void mouseWheelMoved(int change) {
		if(change < 0) {
			if (itemSelected + change >= 0) {
				itemSelected--;
			}
		}
		if(change > 0 ) {
			if (itemSelected + change <= items.size() - 1) {
				itemSelected++;
			}
		}
	}

	public static void mouseMoved(int ox, int oy, int nx, int ny) {
		event = new Rectangle(Globals.mouseX, Globals.mouseY, 2, 2);
	}

	private static void placeObject(){
		Entity e =  (Entity) items.get(GameEditor.itemSelected).clone();
		float x, y;
		if(GameEditor.snapObjects && Globals.state == Globals.IN_EDITOR) {
			x = Math.round(Globals.mouseX / 32) * 32;
			y = Math.round(Globals.mouseY / 32) * 32;
		}
		else {
			x =  Globals.mouseX;
			y =  Globals.mouseY;
		}

		if(GameEditor.canPlace(x, y)) {
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
	}
	
	private static void placeObject(float x, float y){
		Entity e =  (Entity) items.get(GameEditor.itemSelected).clone();
		if(GameEditor.canPlace(x, y)) {
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
	}

	private static void shiftPlace(){
		if(startShift == null && Logic.shiftHeld) {
			if(snapObjects) {
				startShift = new Point(Math.round(Globals.mouseX / 32) * 32,
						Math.round(Globals.mouseY / 32) * 32);
			}
			else {
				startShift = new Point(Globals.mouseX, Globals.mouseY);
			}
		}
		else if(startShift != null && Logic.shiftHeld) {
			for(int i = (int) startShift.getX(); i < Globals.mouseX; i+=items.get(itemSelected).width) {
				for(int j = (int) startShift.getY(); j < Globals.mouseY; j+=items.get(itemSelected).height) {
					placeObject(i, j);
				}
			}
			startShift = null;
		}
	}
	
	public static void mousePressed(int button, int x, int y){
		if(button == 0 && !Logic.shiftHeld) {
			placeObject();
		}
		else {
			if(Logic.shiftHeld && button == 0) {
				shiftPlace();
			}
		}
		event = new Rectangle(Globals.mouseX, Globals.mouseY, 2, 2);
		if(event.intersects(snapBox)) {
			snapObjects = !snapObjects;
		}

		if(event.intersects(hideDecBox)) {
			hideDecorations = !hideDecorations;
		}

		if(event.intersects(linesBox)) {
			drawLines = !drawLines;
		}
		if(button == 1) {
			Entity ee = null;
			for(Entity e : Game.currentLevel.gameObjects) {
				if(event.intersects(e.getBoundingBox()) || (highlight != null && highlight.intersects(e.getBoundingBox()))) {
					e.isSelected = !e.isSelected;
					ee = e;
				}
			}
			if(ee == null) {
				Logic.changeState();
			}
			if(highlight == null || Math.abs(highlight.getWidth()) > 0 )
				highlight = new Rectangle(Globals.mouseX, Globals.mouseY, 1, 1);
		}
	}


	public static void saveLevel(){
		System.out.println("Saving level");
		for(Entity e : Game.currentLevel.gameObjects) {
			System.out.println(e.save());
		}
		System.out.println("------Decorations------");


		System.out.println("------Items------");
	}


	public static boolean canPlace(float x, float y) {
		if(state == IN_EDITOR && x < Globals.width + Camera.x - 300 && !showItemSelect)
			return true;
		else if(state == IN_GAME)
			return true;
		else
			return false;
	}

	private static void loadItems(){
		if(items.size() <= 0) {
			try {
				Scanner s = new Scanner(Globals.class.getResourceAsStream("/items.txt"));
				s.nextLine();
				while(s.hasNextLine()) {
					String[] params = s.nextLine().split("\\t");
					int id = Integer.parseInt(params[0]);
					items.add(Level.idToEntity(id, -99999, -99999, params));
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	
	public static Rectangle highlight = null;
	public static Point startShift = null;
	public static Animation tileSet;
	public static Rectangle event;
	public static boolean showItemSelect = false;
	public static boolean hideDecorations = false;
	public static boolean snapObjects = false;
	public static boolean drawLines = true;
	
	public static int itemSelected = 0;
	public static ArrayList<Entity> items = new ArrayList<Entity>();
	public static Rectangle snapBox, hideDecBox, linesBox;
}
