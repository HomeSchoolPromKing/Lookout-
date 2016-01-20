package net.zrev.Lookout.Game;

import static net.zrev.Lookout.Core.Globals.DEBUG;

import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Core.Resources;
import net.zrev.Lookout.Decorative.Decoration;
import net.zrev.Lookout.GameObjects.Enemy;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Floor;
import net.zrev.Lookout.GameObjects.Jump;
import net.zrev.Lookout.GameObjects.Player;
import net.zrev.Lookout.GameObjects.Saw;
import net.zrev.Lookout.GameObjects.SwitchDirections;
import net.zrev.Lookout.GameObjects.WinZone;

public class Level {
	
	public Level(int level){
		levelId = level;
		loadLevel(level);
		loadDecorations(level);
		loadItems(level);
		isFailed = false;
		isCompleted = false;
	}
	
	public void loadLevel(int level) {
		try {
			Scanner s = new Scanner(Globals.class.getResourceAsStream("/"+level+".txt"));
			while(s.hasNextLine()) {
				String placeableToParse = s.nextLine();
				if(placeableToParse.contains("------Decorations------")) {
					break;
				}
				else {
					parseObject(placeableToParse, false);
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void loadItems(int level){
		try {
			Scanner s = new Scanner(Globals.class.getResourceAsStream("/"+level+".txt"));
			while(s.hasNextLine()) {
				String itemToParse = s.nextLine();
				if(itemToParse.contains("------Items------")) {
					startLoadingItems = true;
				}
				if(startLoadingItems) {
					if(!itemToParse.contains("------Items------"))
						parseObject(itemToParse, true);
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void loadDecorations(int level) {
		try {
			Scanner s = new Scanner(Globals.class.getResourceAsStream("/"+level+".txt"));
			while(s.hasNextLine()) {
				String decorationToParse = s.nextLine();
				if(decorationToParse.contains("------Decorations------")) {
					startLoadingDecorations = true;
				}
				if(startLoadingDecorations) {
					if(!decorationToParse.contains("------Items------"))
						Decoration.parseDecoration(decorationToParse);
					else
						return;
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void resetLevel(){
		Game.currentLevel.gameObjects.clear();
		Level.decorations.clear();
		Game.currentLevel.inventory.clear();
		Game.currentLevel.isCompleted = false;
		Game.currentLevel.isFailed = false;
		Game.currentLevel.startLoadingDecorations = false;
		Game.currentLevel.startLoadingItems = false;
		Game.currentLevel.loadLevel(Game.currentLevel.levelId);
		Game.currentLevel.loadItems(Game.currentLevel.levelId);
		Game.currentLevel.loadDecorations(Game.currentLevel.levelId);
		
		Game.itemSelected = 0;
		Game.startGame();
		
	}

	public static void goToLevel(int level){
		Game.currentLevel.gameObjects.clear();
		Level.decorations.clear();
		Game.currentLevel.inventory.clear();
		Game.currentLevel.isCompleted = false;
		Game.currentLevel.startLoadingDecorations = false;
		Game.currentLevel.startLoadingItems = false;
		Game.currentLevel.loadLevel(level);
		Game.currentLevel.loadItems(Game.currentLevel.levelId);
		Game.currentLevel.loadDecorations(Game.currentLevel.levelId);
		Game.itemSelected = 0;
		Game.startGame();
	}
	

	public static void nextLevel(){
		Game.currentLevel.gameObjects.clear();
		Level.decorations.clear();
		Game.currentLevel.inventory.clear();
		Game.currentLevel.isCompleted = false;
		Game.currentLevel.startLoadingDecorations = false;
		Game.currentLevel.startLoadingItems = false;
		Game.currentLevel.loadLevel(++Game.currentLevel.levelId);
		Game.currentLevel.loadItems(Game.currentLevel.levelId);
		Game.currentLevel.loadDecorations(Game.currentLevel.levelId);
		Game.itemSelected = 0;
		Game.startGame();
	}
	
	private void parseObject(String line, boolean fillingInventory){
		String[] params = line.split("\\t");
		try {
			int id = Integer.parseInt(params[0]);
			float x = Float.parseFloat(params[1]);
			float y = Float.parseFloat(params[2]);
			Entity e = idToEntity(id, x, y, params);
			if(e != null) {
				if(!fillingInventory) {
					gameObjects.add(e);
				}
				else {
					inventory.add(e);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Entity idToEntity(int id, float x, float y, String[] params) throws Exception{
		switch(id) {
		case 1:
			Animation player = new Animation(new SpriteSheet(new Image("characterRunRight.png"), 64, 128), 150);
			return new Player(id, player, x, y, player.getWidth(), player.getHeight());
		case 2:
			Animation floor = new Animation(new SpriteSheet(new Image("floor.png"), 64, 64), 100);
			return new Floor(id, floor, x, y, floor.getWidth(), floor.getHeight(), params);
		case 3:
			Animation switchDir = null;
			int direction = Integer.parseInt(params[3]);
			if(direction == 1) {
				switchDir = new Animation(new SpriteSheet(new Image("switchDirLeft.png"), 96, 64), 150);
			}
			else if(direction == 2) {
				switchDir = new Animation(new SpriteSheet(new Image("switchDirRight.png"), 96, 64), 150);
			}
			return new SwitchDirections(id, switchDir, x, y, switchDir.getWidth(), switchDir.getHeight(), direction);
		case 4:
			Animation winZone = new Animation(new SpriteSheet(new Image("finish.png"), 64, 128), 100);
			winZone.setPingPong(true);
			return new WinZone(id, winZone, x, y, winZone.getWidth(), winZone.getHeight());
		case 5:
			Animation jumpButton = new Animation(new SpriteSheet(new Image("jumpButton.png"), 64, 96), 100);
			return new Jump(id, jumpButton, x, y, jumpButton.getWidth(), jumpButton.getHeight());
		case 6:
			Animation saw = new Animation(new SpriteSheet(new Image("saw.png"), 64, 64), 100);
			return new Saw(id, saw, x, y, saw.getWidth(), saw.getHeight());
		case 7:
			Animation enemy = new Animation(new SpriteSheet(new Image("enemy.png"), 64, 64), 100);
			return new Enemy(id, enemy, x, y, enemy.getWidth(), enemy.getHeight(), params);

		}

		if(DEBUG) {
			System.out.println("Could not find object with that ID.");
		}
		return null;
	}
	
	private boolean startLoadingDecorations = false, startLoadingItems = false;
	public boolean isFailed = false;
	public boolean isCompleted = false;
	
	public int levelId = 0;
	
	public Entity toRemove = null;
	public ArrayList<Entity> gameObjects = new ArrayList<Entity>();
	public static ArrayList<Decoration> decorations = new ArrayList<Decoration>();
	public ArrayList<Entity> inventory = new ArrayList<Entity>();
}
