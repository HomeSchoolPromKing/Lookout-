package net.zrev.Lookout.Core;

import net.zrev.Lookout.Game.Game;
import net.zrev.Lookout.Screens.GameEditorScreen;
import net.zrev.Lookout.Screens.GameScreen;

public class Logic {
	public static void logic(){
		if(Core.state == 1) {
			Game.logic();
		}
		else if(Core.state == 2) {
			
		}
	}
}
