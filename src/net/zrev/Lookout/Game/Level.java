package net.zrev.Lookout.Game;

import java.util.ArrayList;
import java.util.Scanner;

import net.zrev.Core.Lookout.Globals;
import net.zrev.Core.Lookout.Resources;
import net.zrev.Lookout.GameObjects.Entity;

public class Level {
	
	public Level(int level){
		levelId = level;
		loadLevel(level);
		isFailed = false;
		isCompleted = false;
	}
	
	public void loadLevel(int level) {
		try {
			Scanner s = new Scanner(Globals.class.getResourceAsStream("/"+level+".txt"));
			while(s.hasNextLine()) {
				String placeableToParse = s.nextLine();
				parseObject(placeableToParse);
				
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void resetLevel(){
		Game.currentLevel.gameObjects.clear();
		Game.currentLevel.isCompleted = false;
		Game.currentLevel.isFailed = false;
		Game.currentLevel.loadLevel(Game.currentLevel.levelId);
		Game.items.clear();
		Game.itemSelected = 0;
		Game.startGame();
	}

	public static void goToLevel(int level){
		Game.currentLevel.gameObjects.clear();
		Game.currentLevel.isCompleted = false;
		Game.currentLevel.loadLevel(level);
		Game.items.clear();
		Game.itemSelected = 0;
		Game.startGame();
	}
	

	public static void nextLevel(){
		Game.currentLevel.gameObjects.clear();
		Game.currentLevel.isCompleted = false;
		Game.currentLevel.loadLevel(++Game.currentLevel.levelId);
		Game.items.clear();
		Game.itemSelected = 0;
		Game.startGame();
	}
	
	private void parseObject(String line){
		String[] params = line.split("\\t");
		try {
			int id = Integer.parseInt(params[0]);
			float x = Float.parseFloat(params[1]);
			float y = Float.parseFloat(params[2]);
			Entity e = Resources.idToEntity(id, x, y, params);
			if(e != null) {
				gameObjects.add(e);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isFailed = false;
	public boolean isCompleted = false;
	
	public int levelId = 0;
	
	public Entity toRemove = null;
	public ArrayList<Entity> gameObjects = new ArrayList<Entity>();
}
