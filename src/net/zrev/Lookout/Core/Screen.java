package net.zrev.Lookout.Core;

import net.zrev.Lookout.GameEditor.GameEditor;
import net.zrev.Lookout.Screens.GameEditorScreen;
import net.zrev.Lookout.Screens.GameScreen;
import net.zrev.Lookout.Screens.StartMenuScreen;

import org.newdawn.slick.Graphics;

import static net.zrev.Lookout.Core.Globals.*;
public class Screen {
	
	public static void draw(Graphics g ){
		if(state == IN_GAME)
			GameScreen.draw(g);
		else if(state == IN_EDITOR) {
			GameEditorScreen.draw(g);
		}
		//else if(state == IN_END_LEVEL) {
			
		//}
		else if(state == HOME_MENU) {
			((StartMenuScreen) HOME_MENU).paint(g);
		}
	}
	
	public static void keyPressed(int key, char c) {
		if(state == HOME_MENU) {
			((StartMenuScreen) HOME_MENU).inputHandler(key, c);
		}
		
		if(state == IN_EDITOR) {
			GameEditor.keyPressed(key, c);
		}
	}
	
	
}
