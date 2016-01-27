package net.zrev.Lookout.Screens;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import net.zrev.Lookout.Core.Core;
import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Core.Resources;
import net.zrev.Lookout.Decorative.BackgroundLayer;
import net.zrev.Lookout.Decorative.BackgroundShape;
import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Game.Game;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Player;
import net.zrev.Lookout.GameObjects.SwitchDirections;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;

public class GameScreen {

	public static void draw(Graphics g){


		BackgroundLayer.draw(g);


		for(Entity e : Game.currentLevel.gameObjects) {
			e.update(Globals.delta);

			//If the entity is within the camera rectangle, draw it.
			if(Camera.shouldRender(e.getBoundingBox())) {
				e.draw(g);
			}
		}

		if(Game.currentLevel.toRemove != null)
			Game.currentLevel.gameObjects.remove(Game.currentLevel.toRemove);

		g.drawAnimation(Game.currentLevel.inventory.get(Game.itemSelected).anim, Globals.mouseX, Globals.mouseY);
		//Down here
		g.setFont(Resources.gameFont);
		g.setColor(Color.white);
		g.drawString("Current Score: " + Game.currentLevel.levelScore, Camera.x + 50 , Camera.y + 50);
		drawItemSelection(g);
	}


	public static void drawItemSelection(Graphics g){
		for(int i = 0; i < Game.currentLevel.inventory.size(); i++) {
			if(Game.itemSelected == i) {
				Resources.itemSelectionSelected.draw((Globals.width / 3F) + (float) (i * Resources.itemSelection.getWidth()) + Camera.x - Game.p.velocityX, Globals.height - Resources.itemSelection.getHeight() + Camera.y - Game.p.velocityY, Resources.itemSelection.getWidth(),  Resources.itemSelection.getHeight());
			}
			else {
				Resources.itemSelection.draw((Globals.width / 3F) + (float) (i * Resources.itemSelection.getWidth()) + Camera.x - Game.p.velocityX, Globals.height - Resources.itemSelection.getHeight() + Camera.y - Game.p.velocityY, Resources.itemSelection.getWidth(),  Resources.itemSelection.getHeight());
			}
			if(i < Game.currentLevel.inventory.size()) {
				Game.currentLevel.inventory.get(i).anim.draw((Globals.width / 3F) + (float) (i * Resources.itemSelection.getWidth()) + Camera.x - Game.p.velocityX, Globals.height - Resources.itemSelection.getHeight() + Camera.y - Game.p.velocityY, Resources.itemSelection.getWidth(),  Resources.itemSelection.getHeight());//
				//(Globals.canvasWidth / 3) + (i * ((Core.guiOffset / Core.scaleX))) + 24, Globals.canvasHeight + offsetTotal, 32 / Core.scaleX, 32 / Core.scaleY
			}
		}
	}


}
