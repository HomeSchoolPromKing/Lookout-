package net.zrev.Lookout.Game;

import java.util.ArrayList;
import java.util.Scanner;

import net.zrev.Core.Lookout.Globals;
import net.zrev.Core.Lookout.Resources;
import net.zrev.Lookout.GameObjects.Entity;

public class Level {
	
	public Level(int level){
		loadLevel(level);
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
	
	
	public Entity toRemove = null;
	public ArrayList<Entity> gameObjects = new ArrayList<Entity>();
}
