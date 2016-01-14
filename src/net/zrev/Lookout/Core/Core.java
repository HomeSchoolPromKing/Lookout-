package net.zrev.Lookout.Core;

import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Game.Controls;
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
		appgc.setMouseGrabbed(true);
		gameC.setMouseGrabbed(true);
		input = gc.getInput();
		try {
			Resources.init();
			Game.startGame();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		Controls.keyPressed(key, c);
	}

	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
		Controls.keyReleased(key, c);
	}

	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		Controls.mousePressed(button, x, y);
	}

	public void mouseWheelMoved(int change) {
		super.mouseWheelMoved(change);
		Controls.mouseWheelMoved(change);
	}
	
	public void mouseDragged(int ox, int oy, int nx, int ny) {
		super.mouseDragged(ox, oy, nx, ny);
		Controls.mouseDragged(ox, oy, nx, ny);
	}
	
	public void mouseMoved(int ox, int oy, int nx, int ny) {
		super.mouseMoved(ox, oy, nx, ny);
		Controls.mouseMoved(ox, oy, nx, ny);
	}
	
	public void mouseReleased(int button, int x, int y) {
		super.mouseReleased(button, x, y);
		Controls.mouseReleased(button, x, y);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if(delta >= 30) {
			Globals.delta = delta;
		}
		
		scaling();
		Camera.update();
		input = gc.getInput();
		Globals.mouseX += Game.p.velocityX;
		Globals.mouseY += Game.p.velocityY;
		Logic.logic();
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.scale((float) (Display.getWidth() ) / 1920, (float) (Display.getHeight()) / 1080);
		g.translate(-Camera.x, -Camera.y);
		Screen.draw(g);
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
	
	public static int state = 1;
	public static int lastW = 960, lastH = 540;
	public static AppGameContainer appgc;
	public static GameContainer gameC;
	public static Input input = null;
}