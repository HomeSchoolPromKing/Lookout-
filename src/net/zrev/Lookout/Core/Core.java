package net.zrev.Lookout.Core;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import net.zrev.Lookout.Decorative.BackgroundLayer;
import net.zrev.Lookout.Decorative.NorthernLights;
import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Game.Game;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import static net.zrev.Lookout.Core.Globals.*;

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
			CURRENT_SCREEN = HOME_MENU;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		Screen.keyPressed(key, c);
		if(key == 87) {
			try {
				if(fullScreen) {
					appgc.setDisplayMode(lastW, lastH, false);
					fullScreen = false;
				}
				else {
					lastW = Display.getWidth();
					lastH = Display.getHeight();
					appgc.setDisplayMode(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, true);
					fullScreen = true;
				}
			} 
			catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}

	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
		Screen.keyReleased(key, c);
	}

	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		Screen.mousePressed(button, x, y);
	}

	public void mouseWheelMoved(int change) {
		super.mouseWheelMoved(change);
		Screen.mouseWheelMoved(change);
	}

	public void mouseDragged(int ox, int oy, int nx, int ny) {
		super.mouseDragged(ox, oy, nx, ny);
		Screen.mouseDragged(ox, oy, nx, ny);
	}

	public void mouseMoved(int ox, int oy, int nx, int ny) {
		super.mouseMoved(ox, oy, nx, ny);
		Screen.mouseMoved(ox, oy, nx, ny);
	}

	public void mouseReleased(int button, int x, int y) {
		super.mouseReleased(button, x, y);
		Screen.mouseReleased(button, x, y);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		scaleX = (Display.getWidth() / 1920F) * zoom;
		scaleY =  ((Display.getHeight()) / 1080F) * zoom;
		scaling();
		input = gc.getInput();
		Camera.update();
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(Display.getWidth() != gc.getWidth() || Display.getHeight() != gc.getHeight()) {
			try {
				scaleX = (Display.getWidth() / 1920F) * zoom;
				scaleY =  ((Display.getHeight()) / 1080F) * zoom;
				appgc.setDisplayMode(Display.getWidth(), Display.getHeight(), false);
				Display.setResizable(true);
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		g.scale(((Display.getWidth() ) / 1920F) * zoom, ((Display.getHeight()) / 1080F) * zoom);
		g.translate(-Camera.x, -Camera.y);
		
        if (shakeX!=0 && shakeY!=0)
            g.translate(shakeX, shakeY);
		
        Screen.draw(g);
		
        if (shakeX!=0 && shakeY!=0)
            g.translate(-shakeX, -shakeY);
	}
	
	//http://stackoverflow.com/questions/7168747/java-creating-self-extracting-jar-that-can-extract-parts-of-itself-out-of-the-a

	public static void main(String[] args) {
		try {
			Display.setResizable(true);
			appgc = new AppGameContainer(new Core("Lookout!"));
			appgc.setDisplayMode(960, 540, false);
			appgc.setTargetFrameRate(30);
			appgc.start();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static float zoom = 1.0F;
	public static float shakeX = 0f;
	public static float shakeY = 0f;
	public static boolean fullScreen = false;

	public static int lastW = 960, lastH = 540;
	public static AppGameContainer appgc;
	public static GameContainer gameC;
	public static Input input = null;
}
