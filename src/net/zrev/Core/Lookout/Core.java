package net.zrev.Core.Lookout;


import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Game.Game;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
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
		appgc.setMouseGrabbed(true);
		gameC.setMouseGrabbed(true);
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
		Game.placeObject();
	}

	public void mouseWheelMoved(int change) {
		super.mouseWheelMoved(change);
		change = change / Math.abs(change);
		if(change < 0) {
			if (Game.itemSelected + change < 0) {
				
			}
			else {
				Game.itemSelected--;
			}
		}
		if(change > 0 ) {
			if (Game.itemSelected + change > Game.items.size() - 1) {
		
			}
			else {
				Game.itemSelected++;
			}
		}
		
	}
	
	public void mouseDragged(int ox, int oy, int nx, int ny) {
		super.mouseDragged(ox, oy, nx, ny);
	}
	public void mouseMoved(int ox, int oy, int nx, int ny) {
		super.mouseMoved(ox, oy, nx, ny);
		float tempX = (float) input.getMouseX() / scaleX;
		float tempY = (float) input.getMouseY() / scaleY;
		Globals.mouseX = (int) tempX + Camera.x + Game.p.velocityX;
		Globals.mouseY = (int) tempY + Game.p.velocityY;
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
		Globals.mouseX += Game.p.velocityX;
		Globals.mouseY += Game.p.velocityY;
		Game.logic();
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.scale((float) (Display.getWidth() ) / 1920, (float) (Display.getHeight()) / 1080);
		Globals.width = ( (float)  Display.getWidth()) / ((float) Display.getWidth() / 1920);
		Globals.height = ( (float)  Display.getHeight()) / ((float) Display.getHeight() / 1080);
		//g.translate(150, 0);
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
