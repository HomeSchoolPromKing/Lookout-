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

import net.zrev.Core.Lookout.Globals;
import net.zrev.Lookout.Account.UserPreferences;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Floor;
import net.zrev.Lookout.GameObjects.Jump;
import net.zrev.Lookout.GameObjects.Placeable;
import net.zrev.Lookout.GameObjects.Player;
import net.zrev.Lookout.GameObjects.Saw;
import net.zrev.Lookout.GameObjects.SwitchDirections;
import net.zrev.Lookout.GameObjects.WinZone;
import net.zrev.Lookout.Screens.GameScreen;

public class Game {

	public static void draw(Graphics g){
		GameScreen.draw(g);
	}

	public static void logic(){
		Camera.update();
	}

	public static void initItems(){
		try {
			Scanner s = new Scanner(new File("data/items.txt"));
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

	public static void placeObject(){
		Entity e =  (Entity) items.get(itemSelected).clone();
		e.x = Globals.mouseX;
		e.y = Globals.mouseY;
		currentLevel.gameObjects.add(convertToEntity((Placeable) e));
	}

	private static Entity convertToEntity(Placeable p) {
		int x = (int) Globals.mouseX;
		int y = (int) Globals.mouseY;
		try {
			switch(p.id) {
			case 1:
				Animation floor = new Animation(new SpriteSheet(new Image("floor.png"), 128, 32), 100);
				return new Floor(floor, x, y, floor.getWidth(), floor.getHeight());
			case 2:
				Animation switchDir = new Animation(new SpriteSheet(new Image("switchDirLeft.png"), 96, 64), 150);
				return new SwitchDirections(switchDir, x, y, switchDir.getWidth(), switchDir.getHeight(), 1);
			case 3:
				Animation switchDir2 = new Animation(new SpriteSheet(new Image("switchDirRight.png"), 96, 64), 150);
				return new SwitchDirections(switchDir2, x, y, switchDir2.getWidth(), switchDir2.getHeight(), 2);
			case 4:
				Animation jumpButton = new Animation(new SpriteSheet(new Image("jumpButton.png"), 64, 96), 100);
				return new Jump(jumpButton, x, y, jumpButton.getWidth(), jumpButton.getHeight());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void startGame(){
		Camera.init(0, 0, 1920, 1080);
		for(Entity e : currentLevel.gameObjects) {
			if(e instanceof Player) {
				p = (Player) e;
			}
		}
		initItems();
		System.out.println(items.size() + " " + items.get(0).anim.getImage(0).getName());
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
