package net.zrev.Lookout.Menu;

import java.awt.Font;
import java.io.InputStream;

import static net.zrev.Lookout.Core.Globals.*;
import net.zrev.Lookout.Game.Game;

import org.newdawn.slick.Color;

import static org.newdawn.slick.Graphics.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

public class MenuItem {

	public MenuItem(String name) {
		this.name = name;
	}

	public void paint(Graphics g, int y) {
		g.setColor(Color.black);
		try {
				menufont.addAsciiGlyphs();
				menufont.addGlyphs(400, 600);
				menufont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
				menufont.loadGlyphs();
				if(!selected)
					menufont.drawString( (width / 2), (height + y) / 2, name, Color.black);
				else
					menufont.drawString((width / 2), (height + y) / 2,">" + name, Color.black);

		} 
		catch (SlickException e) {
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
		if(name.equals("Play")) {
			//Start Game
			Game.startGame();
			setGameState(IN_GAME);
		}
		//else if(name.equals("Continue")) {
		//	setGameState(GameState.GAME_ACTIVE);
		//}
		else if(name.equals("Start")) {
			Game.startGame();
			setGameState(IN_GAME);
		}
		else if(name.equals("Options")) {
		//	setGameState(OPTIONSMENU);
		}
		//else if(name.equals("Quit")) {
		//	setGameState(GameState.AREYOUSURE);
		//}
		else if(name.equals("Back")) {
			setGameState(PREVIOUS_SCREEN);
		}
		//else if(name.equals("Creative")) {
		//	Game.startGame();
		//Engine.init();
		//	setGameState(IN_EDITOR);
		//}
		else if(name.equals("Exit")) {
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

	public UnicodeFont menufont = new UnicodeFont(new Font("Arial", Font.PLAIN, 72));;
}
