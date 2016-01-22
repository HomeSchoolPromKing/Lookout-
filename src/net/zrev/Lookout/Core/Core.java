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
//		Timer t = new Timer();
//		t.scheduleAtFixedRate(new TimerTask() {
//			public void run(){
//				System.out.println("hi");
//			}
//		}
//			, 100, 1000);
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gameC = gc;
		Display.setResizable(true);
		gameC.setFullscreen(false);
		gameC.setShowFPS(false);

		input = gc.getInput();

		float absstepmax = 50;
		float ymin = -100;
		float ymax = 100;
		float y = 5;

		for(int i = 0; i < Globals.width; i+=10) {
			y += (Math.random() * (2F * absstepmax) - absstepmax - 1F);
			y = Math.max(ymin, Math.min(ymax, y));
			BackgroundLayer.theLights.add(new NorthernLights(i, y));

		}
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
		if(state == 2) {
			if(key == 12) {
				if(zoom == 1.0F)
					zoom = 0.5F;
				else
					zoom = 1.0F;
			}
			if(key == 13) {
				if(zoom == 1.0F)
					zoom = 1.5F;
				else
					zoom = 1.0F;
			}
		}
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
		scaleX = (Display.getWidth() / 1920F) * zoom;
		scaleY =  ((Display.getHeight()) / 1080F) * zoom;
		if(delta >= 30) {
			Globals.delta = delta;
		}
		scaling();
		Camera.update();
		input = gc.getInput();
		if(state == 1) {
			Globals.mouseX += Game.p.velocityX;
			Globals.mouseY += Game.p.velocityY;
		}
		Logic.logic();
		
        if (shakeAmt>0f) {
            shakeTime -= delta;
            //new shakeX/Y
            if (shakeTime <= 0) {
                shake();
            }
        }	
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

	public static void shake() {
		shakeX = (float)(Math.random()*shakeAmt);
		shakeY = (float)(Math.random()*shakeAmt);
		if (SHAKE_SNAP) {
			shakeX = (int)shakeX;
			shakeY = (int)shakeY;
		}
		shakeTime = SHAKE_DELAY;
		shakeAmt -= SHAKE_DECAY*SHAKE_INTENSITY;
		if (shakeAmt<0f)
			shakeAmt = 0f;
	}
	
	
	public static float zoom = 1.0F;
    public static final boolean SHAKE_SNAP = false;
    
    /** How far the shake should extend in pixels. */
    public static final int SHAKE_INTENSITY = 15;
    
    public static final float SHAKE_DECAY = 0.03f;
    
    /** Delay in ms between each new shake. */
    public static final int SHAKE_DELAY = 45;

	private static int shakeTime = SHAKE_DELAY;
	public static float shakeAmt = 0f;
	private static float shakeX = 0f;
	private static float shakeY = 0f;

	public static int lastW = 960, lastH = 540;
	public static AppGameContainer appgc;
	public static GameContainer gameC;
	public static Input input = null;
}
