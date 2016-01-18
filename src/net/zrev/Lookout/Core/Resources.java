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
			bg = new Image("bg3.png");
			bg55 = new Image("bg55.png");
			InputStream inputStream = Globals.class.getResourceAsStream("/Roman SD.ttf");
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(36f); // set font size
			gameFont = new TrueTypeFont(awtFont2, false);
			
			
			awtFont2 = awtFont2.deriveFont(20f); // set font size
			editorFont = new TrueTypeFont(awtFont2, false);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(DEBUG) {
			System.out.println("Done loading resources.");
		}
		initated = true;
	}
	
	public static TrueTypeFont gameFont, editorFont;
	public static Image bg, bg55;
	public static boolean initated = false;
}
