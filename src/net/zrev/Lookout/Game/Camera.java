package net.zrev.Lookout.Game;

import org.newdawn.slick.geom.Rectangle;

import static net.zrev.Core.Lookout.Globals.*;

public class Camera {

	public static void init(float xx, float yy, float widthh, float heightt){
		x = xx;
		y = yy;
		width = widthh;
		height = heightt;
		screen = new Rectangle(x, y, width, height);
	}
	
	public static void update(){
		if(x > Game.maxOffsetX)
		    x = Game.maxOffsetX;
		else if (x < Game.minOffsetX)
		    x = Game.minOffsetX;
		
		if(y > Game.maxOffsetY)
		    y = Game.maxOffsetY;
		else if (y < Game.minOffsetY)
		    y = Game.minOffsetY;
		    
		screen = new Rectangle(x, y, width, height);
	}
	
	
	public static boolean shouldRender(Rectangle instance){
		if(screen != null && screen.intersects(instance)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static Rectangle screen;
	public static float x, y, width, height;
	
}
