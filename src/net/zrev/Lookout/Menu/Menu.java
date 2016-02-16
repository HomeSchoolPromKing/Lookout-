package net.zrev.Lookout.Menu;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Core.Resources;
import net.zrev.Lookout.Core.Screen;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;


public class Menu extends Screen {
	
	
	public Menu() {
		toTx = new Random().nextInt(1920);
		toTy = new Random().nextInt(1080);
	}
	
	public void inputHandler(int key, char c) {
		switch(key) {
		case Input.KEY_UP:
			changeSelectedUp();
			break;
		case Input.KEY_DOWN:
			changeSelectedDown();
			break;
		case Input.KEY_ENTER:
			menuItems.get(menuItemSelected).performAction();
			break;
		}
	}

	public void changeSelectedUp() {
		if(menuItemSelected-1 < 0) {
			menuItemSelected = menuItems.size()-1;
		}
		else {
			menuItemSelected--;
		}
		resetSelection();
	}

	public void changeSelectedDown() {
		if(menuItemSelected+1 > menuItems.size()-1) {
			menuItemSelected = 0;
		}
		else {
			menuItemSelected++;
		}
		resetSelection();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(0, 0, Globals.width, Globals.height);
		Resources.menuBg.setAlpha(0.3F);
		
		g.pushTransform();
		g.translate(-toTx, -toTy);
		if(bgScale < 3.5F && !bgFlip) {
			bgScale += 0.001F;
		}
		else if(bgScale > 2.0F && bgFlip) {
			bgScale -= 0.001F;
		}
		else if(bgScale <= 2.0F && bgFlip) {
			bgFlip = false;
			toTx = new Random().nextInt(1920);
			toTy = new Random().nextInt(1080);
		}
		else {
			bgFlip = true;
		}
		g.scale(bgScale, bgScale);
		Resources.menuBg.draw();
		g.popTransform();
	}

	public void resetSelection() {
		for(MenuItem mi : menuItems) {
			mi.select(false);	
		}
		menuItems.get(menuItemSelected).select(true);
	}

	private static int toTx = 1920, toTy = 0;
	private static float bgScale = 2.0F;
	private static boolean bgFlip = false;
	
	public ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	public int menuItemSelected = 0;
}
