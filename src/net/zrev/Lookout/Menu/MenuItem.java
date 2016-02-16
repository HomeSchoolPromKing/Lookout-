package net.zrev.Lookout.Menu;

import java.awt.Font;
import java.io.InputStream;

import static net.zrev.Lookout.Core.Globals.*;
import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Core.Resources;
import net.zrev.Lookout.Game.Game;
import net.zrev.Lookout.GameEditor.GameEditor;
import net.zrev.Lookout.Screens.GameEditorScreen;

import org.newdawn.slick.Color;

import static org.newdawn.slick.Graphics.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.ResourceLoader;

public class MenuItem {

	public MenuItem(String name) {
		this.name = name;
	}

	public void paint(Graphics g, int y) {
		g.setColor(Color.black);
		try {
				//Resources.menufont.addAsciiGlyphs();
				//Resources.menufont.addGlyphs(400, 600);
				//Resources.menufont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
				//Resources.menufont.loadGlyphs();
				if(hoverBox == null) {
					hoverBox = new Rectangle(150, (height + (y * 2) ) / 2, 300, 72);
				}
				Rectangle mouse = new Rectangle(Globals.mouseX, Globals.mouseY, 2, 2);
				if(mouse.intersects(hoverBox)) {
					Menu temp = (Menu) CURRENT_SCREEN;
					temp.menuItemSelected = temp.menuItems.indexOf(this);
					selected = true;
				}
				else {
					selected = false;
				}
				if(!selected)
					Resources.menufont.drawString( 150, (height + (y * 2) ) / 2, name, Color.black);
				else
					Resources.menufont.drawString( 150, (height + (y * 2) ) / 2,">" + name, Color.black);
				

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void select(boolean option) {
		if(option) {
			selected = true;
		}
		else {
			selected = false;
		}
	}

	public void performAction() {
		name = name.toUpperCase();
		if(name.equals("PLAY")) {
			//Start Game
			Game.startGame();
			setGameState(IN_GAME);
		}
		//else if(name.equals("Continue")) {
		//	setGameState(GameState.GAME_ACTIVE);
		//}
		else if(name.equals("START")) {
			Game.startGame();
			setGameState(IN_GAME);
		}
		else if(name.equals("OPTIONS")) {
		//	setGameState(OPTIONSMENU);
		}
		//else if(name.equals("Quit")) {
		//	setGameState(GameState.AREYOUSURE);
		//}
		else if(name.equals("BACK")) {
			setGameState(PREVIOUS_SCREEN);
		}
		else if(name.equals("CREATIVE")) {
			//Game.startGame();
			GameEditor.init();
			setGameState(IN_EDITOR);
		}
		else if(name.equals("EXIT")) {
			System.exit(0);
		}
		//else if(name.equals("Yes")) {
		//	if(GameState.gameAreYouSureScreen())
		//		GameState.setGameState(GameState.TITLE_SCREEN);
		//	else if(GameState.gameNextLevel())
		//		GameState.setGameState(GameState.GAME_ACTIVE);
		//}
		//else if(name.equals("No")){
		//	GameState.setGameState(Globals.previous);
		//}
	}

	public boolean selected = false;
	public String name;
	private Rectangle hoverBox;
	
	//public UnicodeFont menufont = new UnicodeFont(new Font("Arial", Font.PLAIN, 72));
}
