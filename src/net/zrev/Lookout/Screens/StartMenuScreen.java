package net.zrev.Lookout.Screens;


import net.zrev.Lookout.Menu.Menu;
import net.zrev.Lookout.Menu.MenuItem;

import org.newdawn.slick.Graphics;

public class StartMenuScreen extends Menu {
	
	public StartMenuScreen(){
		MenuItem start = new MenuItem("START");
		start.select(true);
		menuItems.add(start);
		menuItems.add(new MenuItem("CREATIVE"));
		menuItems.add(new MenuItem("OPTIONS"));
		menuItems.add(new MenuItem("EXIT"));
	}
	

	public void paint(Graphics g){
		super.paint(g);
		int y = 0;
		for(MenuItem mi : menuItems) {
			mi.paint(g, y);
			y += (120);
		}
	}
	
	

}
