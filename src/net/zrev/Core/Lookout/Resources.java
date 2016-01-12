package net.zrev.Core.Lookout;

import static net.zrev.Core.Lookout.Globals.*;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Floor;
import net.zrev.Lookout.GameObjects.Player;
import net.zrev.Lookout.GameObjects.SwitchDirections;
import net.zrev.Lookout.GameObjects.WinZone;

public class Resources {

	public static void init() throws Exception {
		if(DEBUG) {
			System.out.println("Loading resources.");
		}
		bg = new Image("bg.png");

		if(DEBUG) {
			System.out.println("Done loading resources.");
		}
		initated = true;
	}

	
	
	public static Entity idToEntity(int id, float x, float y, String[] params) throws Exception{
		switch(id) {
		case 1:
			Animation player = new Animation(new SpriteSheet(new Image("characterRunRight.png"), 34, 64), 150);
			return new Player(player, x, y, player.getWidth(), player.getHeight());

		case 2:
			Animation floor = new Animation(new SpriteSheet(new Image("floor.png"), 128, 32), 100);
			return new Floor(floor, x, y, floor.getWidth(), floor.getHeight());
		case 3:
			
			Animation switchDir = null;
			int direction = Integer.parseInt(params[3]);
			if(direction == 1) {
				switchDir = new Animation(new SpriteSheet(new Image("switchDirLeft.png"), 96, 64), 150);
			}
			else if(direction == 2) {
				switchDir = new Animation(new SpriteSheet(new Image("switchDirRight.png"), 96, 64), 150);
			}
			return new SwitchDirections(switchDir, x, y, switchDir.getWidth(), switchDir.getHeight(), direction);
		case 4:
			Animation winZone = new Animation(new SpriteSheet(new Image("finish.png"), 128, 192), 100);
			return new WinZone(winZone, x, y, winZone.getWidth(), winZone.getHeight());

		}
		
		if(DEBUG) {
			System.out.println("Could not find object with that ID.");
		}
		return null;
	}
	
	public static Image bg;
	public static boolean initated = false;
}
