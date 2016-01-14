package net.zrev.Lookout.Core;

import static net.zrev.Lookout.Core.Globals.*;

import java.awt.Font;
import java.io.InputStream;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.ResourceLoader;

import net.zrev.Lookout.Game.Game;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Floor;
import net.zrev.Lookout.GameObjects.Jump;
import net.zrev.Lookout.GameObjects.Player;
import net.zrev.Lookout.GameObjects.Saw;
import net.zrev.Lookout.GameObjects.SwitchDirections;
import net.zrev.Lookout.GameObjects.WinZone;
import net.zrev.Lookout.Screens.GameScreen;

public class Resources {

	public static void init() {
		if(DEBUG) {
			System.out.println("Loading resources.");
		}
		try {
		bg = new Image("nebula.png");
			InputStream inputStream = Globals.class.getResourceAsStream("/Roman SD.ttf");
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(36f); // set font size
			gameFont = new TrueTypeFont(awtFont2, false);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
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
		case 5:
			Animation jumpButton = new Animation(new SpriteSheet(new Image("jumpButton.png"), 64, 96), 100);
			return new Jump(jumpButton, x, y, jumpButton.getWidth(), jumpButton.getHeight());
		case 6:
			Animation saw = new Animation(new SpriteSheet(new Image("saw.png"), 64, 64), 100);
			return new Saw(saw, x, y, saw.getWidth(), saw.getHeight());

		}

		if(DEBUG) {
			System.out.println("Could not find object with that ID.");
		}
		return null;
	}


	public static TrueTypeFont gameFont;
	public static Image bg;
	public static boolean initated = false;
}
