package net.zrev.Lookout.Screens;

import org.newdawn.slick.Graphics;

import net.zrev.Lookout.Core.Core;
import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Menu.Menu;
import net.zrev.Lookout.Menu.MenuItem;

public class PausedScreen extends Menu {
	
	public PausedScreen(){
		MenuItem start = new MenuItem("RESUME");
		start.select(true);
		menuItems.add(start);
		menuItems.add(new MenuItem("OPTIONS"));
		menuItems.add(new MenuItem("QUIT"));
	}
	

	public void paint(Graphics g){
		Camera.x = 0;
		Camera.y = 0;
		Core.gameC.setDefaultMouseCursor();
		super.paint(g);
		int y = 0;
		for(MenuItem mi : menuItems) {
			mi.paint(g, y);
			y += (120);
		}
	}
	
	

}
