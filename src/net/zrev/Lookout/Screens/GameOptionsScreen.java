package net.zrev.Lookout.Screens;


import net.zrev.Lookout.Menu.Menu;
import net.zrev.Lookout.Menu.MenuItem;

import org.newdawn.slick.Graphics;




public class GameOptionsScreen extends Menu {
	
	public GameOptionsScreen(){
		MenuItem start = new MenuItem("Graphics");
		start.select(true);
		menuItems.add(start);
		menuItems.add(new MenuItem("Audio"));
		menuItems.add(new MenuItem("Back"));
		done = true;
	}

	public void paint(Graphics g){
		super.paint(g);
		int y = 0;
		for(MenuItem mi : menuItems) {
			mi.paint(g, y);
			y+= (120);
		}
	}
	
	
	
	public static boolean done = false;

}
