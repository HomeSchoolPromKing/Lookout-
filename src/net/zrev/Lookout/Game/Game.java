package net.zrev.Lookout.Game;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import net.zrev.Lookout.Account.UserPreferences;
import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Decorative.BackgroundLayer;
import net.zrev.Lookout.Decorative.BackgroundShape;
import net.zrev.Lookout.GameEditor.GameEditor;
import net.zrev.Lookout.GameEditor.Placeable;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Floor;
import net.zrev.Lookout.GameObjects.Jump;
import net.zrev.Lookout.GameObjects.Player;
import net.zrev.Lookout.GameObjects.Saw;
import net.zrev.Lookout.GameObjects.SwitchDirections;
import net.zrev.Lookout.GameObjects.WinZone;
import net.zrev.Lookout.Screens.GameScreen;

public class Game {
	
	public static void startGame(){
		Camera.init(0, 0, 1920, 1080);
		for(Entity e : currentLevel.gameObjects) {
			if(e instanceof Player) {
				p = (Player) e;
			}
		}
		BackgroundLayer.reset();
	}
	
	public static void logic(){
		if(currentLevel.isCompleted) {
			Level.nextLevel();
		}
		else if(currentLevel.isFailed) {
			Level.resetLevel();
		}
	}
	
	public static Player p = null;
	
	public static int gameWidth = 1920, gameHeight = 1080;
	
	public static int viewWidth = 960, viewHeight = 540;
	
	public static int 
			minOffsetX = -viewWidth,
			maxOffsetX = gameWidth + viewWidth * 2,
			minOffsetY = -viewHeight, maxOffsetY = gameHeight + viewHeight * 2,
			currentOffsetX = 0, currentOffsetY = 0;
	public static Level currentLevel = new Level(1);

	public static int itemSelected = 0;
}
