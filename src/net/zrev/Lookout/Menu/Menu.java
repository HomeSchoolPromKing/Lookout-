package net.zrev.Lookout.Menu;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Core.Screen;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Menu extends Screen {
	
	
	public Menu() {
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
	}

	public void resetSelection() {
		for(MenuItem mi : menuItems) {
			mi.select(false);	
		}
		menuItems.get(menuItemSelected).select(true);
	}

	public ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	public int menuItemSelected = 0;
}
