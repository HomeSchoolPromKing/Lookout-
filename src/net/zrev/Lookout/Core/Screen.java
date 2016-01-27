package net.zrev.Lookout.Core;

import net.zrev.Lookout.Screens.GameEditorScreen;
import net.zrev.Lookout.Screens.GameScreen;

import org.newdawn.slick.Graphics;
import static net.zrev.Lookout.Core.Globals.*;
public class Screen {
	
	public static void draw(Graphics g ){
		if(state == IN_GAME)
			GameScreen.draw(g);
		else if(state == IN_EDITOR) {
			GameEditorScreen.draw(g);
		}
		else if(state == IN_END_LEVEL) {
			
		}
	}
}
