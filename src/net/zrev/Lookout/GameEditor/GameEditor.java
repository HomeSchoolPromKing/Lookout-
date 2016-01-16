package net.zrev.Lookout.GameEditor;

import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Placeable;

public class GameEditor {

	public static void init(){
		Camera.x = 0;
		Camera.y = 0;
		loadItems();
	}
	
	private static void loadItems(){
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
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static int itemSelected = 0;
	public static ArrayList<Entity> items = new ArrayList<Entity>();
}
