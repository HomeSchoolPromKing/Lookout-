package net.zrev.Lookout.Core;

import net.zrev.Lookout.Screens.GameEditorScreen;
import net.zrev.Lookout.Screens.GameScreen;

import org.newdawn.slick.Graphics;

public class Screen {
	
	public static void draw(Graphics g ){
		if(Core.state == 1)
			GameScreen.draw(g);
		else if(Core.state == 2) {
			GameEditorScreen.draw(g);
		}
	}
}
