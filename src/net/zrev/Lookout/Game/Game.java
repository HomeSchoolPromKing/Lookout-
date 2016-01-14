package net.zrev.Lookout.Game;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import net.zrev.Lookout.Account.UserPreferences;
import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Floor;
import net.zrev.Lookout.GameObjects.Jump;
import net.zrev.Lookout.GameObjects.Placeable;
import net.zrev.Lookout.GameObjects.Player;
import net.zrev.Lookout.GameObjects.Saw;
import net.zrev.Lookout.GameObjects.SwitchDirections;
import net.zrev.Lookout.GameObjects.WinZone;
import net.zrev.Lookout.Screens.GameOverScreen;
import net.zrev.Lookout.Screens.GameScreen;
import net.zrev.Lookout.Screens.NextLevelScreen;

public class Game {

	public static void initItems(){
		try {
			Scanner s = new Scanner(Globals.class.getResourceAsStream("/items.txt"));
			while(s.hasNextLine()) {
				String[] params = s.nextLine().split("\\t");
				int id = Integer.parseInt(params[0]);
				String imageName = params[1];
				int width = Integer.parseInt(params[2]);
				int height = Integer.parseInt(params[3]);
				Animation anim = new Animation(new SpriteSheet(new Image(imageName), width, height), 100);
				items.add(new Placeable(anim, width, height, id));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//Game.items.add(new Jump(jumpButton, x, y, jumpButton.getWidth(), jumpButton.getHeight()));
	}
	
	public static void removeObject() {
		for(Entity e : currentLevel.gameObjects) {
			if(new Rectangle(Globals.mouseX, Globals.mouseY, 2, 2).intersects(e.getBoundingBox())) {
				if(e.wasPlaced)
					currentLevel.toRemove = e;
			}
		}
	}

	public static void placeObject(){
		Entity e =  (Entity) items.get(itemSelected).clone();
		e.x = Globals.mouseX;
		e.y = Globals.mouseY;
		currentLevel.gameObjects.add(Placeable.convertToEntity((Placeable) e));
	}
	
	
	public static void startGame(){
		Camera.init(0, 0, 1920, 1080);
		for(Entity e : currentLevel.gameObjects) {
			if(e instanceof Player) {
				p = (Player) e;
			}
		}
		initItems();
	}
	
	public static void logic(){
		if(currentLevel.isCompleted) {
			Level.nextLevel();
		}
		else if(currentLevel.isFailed) {
			Level.resetLevel();
		}
	}

	
	public static Player p = null;

	public static int gameWidth = 1920, gameHeight = 1080;
	public static int viewWidth = 960, viewHeight = 540;
	public static int minOffsetX = -viewWidth, maxOffsetX = gameWidth + viewWidth,
			minOffsetY = -viewHeight, maxOffsetY = gameHeight + viewHeight,
			currentOffsetX = 0, currentOffsetY = 0;
	public static Level currentLevel = new Level(1);

	public static int itemSelected = 0;
	public static ArrayList<Entity> items = new ArrayList<Entity>();


}
