package net.zrev.Lookout.Game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import net.zrev.Lookout.Account.UserPreferences;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Player;
import net.zrev.Lookout.Screens.GameScreen;

public class Game {

	public static void draw(Graphics g){
		GameScreen.draw(g);
	}
	
	public static void logic(){
		Camera.update();
	}
	
	

	public static void startGame(){
		Camera.init(0, 0, 1920, 1080);
		for(Entity e : currentLevel.gameObjects) {
			if(e instanceof Player) {
				p = (Player) e;
			}
		}
	}
	
	private void generatePlayers(){
	}
	
	
	public static Player p = null;
	
	public static int gameWidth = 1920, gameHeight = 1080;
	public static int viewWidth = 960, viewHeight = 540;
	public static int minOffsetX = -viewWidth, maxOffsetX = gameWidth + viewWidth,
			minOffsetY = -viewHeight, maxOffsetY = gameHeight + viewHeight,
			currentOffsetX = 0, currentOffsetY = 0;
	public static Level currentLevel = new Level(1);
	public static float gravity = 5;
}
