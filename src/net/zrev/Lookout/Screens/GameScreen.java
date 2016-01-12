package net.zrev.Lookout.Screens;

import net.zrev.Core.Lookout.Core;
import net.zrev.Core.Lookout.Globals;
import net.zrev.Core.Lookout.Resources;
import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Game.Game;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class GameScreen {

	public static void draw(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(Camera.x, Camera.y, Camera.width, Camera.height);
		if(Resources.initated) {
			//for(int i = -1920; i < 3840; i+=1920) {
			//	for(int j = -1080; j < 2160; j+=1080) {
					g.drawImage(Resources.bg, 0, 0);
			//	}
			//}
		}
		for(Entity e : Game.currentLevel.gameObjects) {
			if(e instanceof Player) {
				((Player) e ).update(Globals.delta);
			}
			else {
				e.update(Globals.delta);
			}
			//If the entity is within the camera rectangle, draw it.
			if(Camera.shouldRender(e.getBoundingBox())) {
				e.draw(g);
			}
		}

		if(Game.currentLevel.toRemove != null)
			Game.currentLevel.gameObjects.remove(Game.currentLevel.toRemove);

	}
	

}
