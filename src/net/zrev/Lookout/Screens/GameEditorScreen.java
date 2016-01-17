package net.zrev.Lookout.Screens;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Game.Game;
import net.zrev.Lookout.GameEditor.GameEditor;
import net.zrev.Lookout.GameObjects.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class GameEditorScreen {

	public static void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Globals.width, Globals.height);
		
		//g.setColor(new Color(255,255,255, 0.2F));
		for(int i = 0; i < Globals.width * 4; i+=32) {
			g.drawLine(i + Camera.x, 0, i + Camera.x, Globals.width * 4);
		}
		for(int j = 0; j < Globals.width * 4; j+=32) {
			g.drawLine(0, j + Camera.y, Globals.width * 4, j + Camera.y);
		}
		
		for(Entity e : Game.currentLevel.gameObjects) {
			//If the entity is within the camera rectangle, draw it.
			if(Camera.shouldRender(e.getBoundingBox())) {
				e.draw(g);
			}
		}
		
		drawItemGhost(g);
	}
	
	private static void drawItemGhost(Graphics g){
		g.drawAnimation(GameEditor.items.get(GameEditor.itemSelected).anim, Globals.mouseX, Globals.mouseY);
	}
	
}
