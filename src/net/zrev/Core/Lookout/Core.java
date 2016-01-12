package net.zrev.Core.Lookout;


import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Game.Game;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import static net.zrev.Core.Lookout.Globals.*;

public class Core extends BasicGame {

	public Core(String gamename) {
		super(gamename);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		gameC = gc;
		Display.setResizable(true);
		gameC.setFullscreen(false);
		gameC.setShowFPS(false);
		input = gc.getInput();
		try {
			Resources.init();
			Game.startGame();
			//Fonts.loadFonts();
			//EngineScreen.initEngineInterface();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
	}

	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
	}

	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		//Game.p.jump();
	}

	public void mouseWheelMoved(int change) {
		super.mouseWheelMoved(change);
		if(change < 0) {
			Game.itemSelected--;
		}
		if(change > 0 ) {
			Game.itemSelected++;
		}
	}
	
	public void mouseDragged(int ox, int oy, int nx, int ny) {
		super.mouseDragged(ox, oy, nx, ny);
	}
	public void mouseMoved(int ox, int oy, int nx, int ny) {
		super.mouseMoved(ox, oy, nx, ny);
	}
	
	public void mouseReleased(int button, int x, int y) {
		super.mouseReleased(button, x, y);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if(delta >= 30) {
			Globals.delta = delta;
		}
		Camera.update();
		scaling();
		input = gc.getInput();
		Game.logic();
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.scale((float) (Display.getWidth() + 150) / 1920, (float) (Display.getHeight()) / 1080);
		g.translate(150, 0);
		g.translate(-Camera.x, -Camera.y);
		Game.draw(g);
	}

	//http://stackoverflow.com/questions/7168747/java-creating-self-extracting-jar-that-can-extract-parts-of-itself-out-of-the-a

	public static void main(String[] args) {
		try {
			Display.setResizable(true);
			appgc = new AppGameContainer(new Core("Lookout!"));
			//appgc.setDisplayMode((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), false);
			appgc.setDisplayMode(960, 540, false);
			appgc.setTargetFrameRate(30);
			appgc.start();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static int lastW = 960, lastH = 540;
	public static AppGameContainer appgc;
	public static float guiOffset = 64;
	public static GameContainer gameC;
	public static Input input = null;
	public static boolean mousePressed = false;
	public boolean running = true;
}
