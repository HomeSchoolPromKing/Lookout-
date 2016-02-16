package net.zrev.Lookout.Game;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import net.zrev.Lookout.Account.UserPreferences;
import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Decorative.BackgroundLayer;
import net.zrev.Lookout.Decorative.Firefly;
import net.zrev.Lookout.GameEditor.GameEditor;
import net.zrev.Lookout.GameEditor.Placeable;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Floor;
import net.zrev.Lookout.GameObjects.Jump;
import net.zrev.Lookout.GameObjects.Player;
import net.zrev.Lookout.GameObjects.Saw;
import net.zrev.Lookout.GameObjects.SwitchDirections;
import net.zrev.Lookout.GameObjects.Trigger;
import net.zrev.Lookout.GameObjects.WinZone;
import net.zrev.Lookout.Screens.GameScreen;
import static net.zrev.Lookout.Core.Globals.*;

public class Game {
	
	public static void startGame(){
		Camera.init(0, 0, 1920, 1080);
		for(Entity e : currentLevel.gameObjects) {
			if(e instanceof Player) {
				p = (Player) e;
			}
		}
		BackgroundLayer.reset();
		initTriggers();
	}
	
	public static void initTriggers(){
		for(Entity e : currentLevel.gameObjects) {
			if(e instanceof Trigger) {
				((Trigger) e).init();
			}
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
		if(GameEditor.snapObjects && Globals.CURRENT_SCREEN == Globals.IN_EDITOR) {
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
	
	public static void update(){
		
		Globals.mouseX += Game.p.velocityX;
		Globals.mouseY += Game.p.velocityY;
		
		hideCursor();
		
		if(currentLevel.isCompleted) {
			//state = IN_END_LEVEL;
			Level.nextLevel();
		}
		else if(currentLevel.isFailed) {
			Level.resetLevel();
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
	}
	
	public static void keyPressed(int key, char c){
		if(key == 1) {
			setGameState(PAUSED);
		}
		if(Character.isDigit(c)) {
			if(Integer.parseInt(c+"") <= Game.currentLevel.inventory.size()) {
				Game.itemSelected = Integer.parseInt(c+"")-1;
			}
			if(Integer.parseInt(c+"") <= GameEditor.items.size()) {
				GameEditor.itemSelected = Integer.parseInt(c+"")-1;
			}
		}
	}
	
	public static Player p = null;
	
	public static int gameWidth = 1920, gameHeight = 1080;
	
	public static int viewWidth = 960, viewHeight = 540;
	
	public static int 
			minOffsetX = -viewWidth,
			maxOffsetX = gameWidth + viewWidth * 2,
			minOffsetY = -viewHeight, maxOffsetY = gameHeight + viewHeight * 2,
			currentOffsetX = 0, currentOffsetY = 0;
	
	public static Level currentLevel = new Level(1);

	public static int itemSelected = 0;

}
