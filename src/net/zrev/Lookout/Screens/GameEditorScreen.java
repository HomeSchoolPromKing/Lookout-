package net.zrev.Lookout.Screens;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Core.Logic;
import net.zrev.Lookout.Core.Resources;
import net.zrev.Lookout.Decorative.BackgroundLayer;
import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Game.Game;
import static net.zrev.Lookout.GameEditor.GameEditor.*;
import net.zrev.Lookout.GameEditor.GameEditor;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Floor;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

public class GameEditorScreen {

	
	public static void draw(Graphics g) {
		GameEditor.update();
		g.setColor(Color.black);
		g.fillRect(0, 0, Globals.width, Globals.height);
		
		BackgroundLayer.draw(g);
		
		if(drawLines) {
			g.setColor(new Color(255,255,255, 0.2F));
			for(int i = 0; i < Globals.width * 4; i+=32) {
				g.drawLine(i + Camera.x, 0, i + Camera.x, Globals.width * 4);
			}
			for(int j = 0; j < Globals.width * 4; j+=32) {
				g.drawLine(0, j + Camera.y, Globals.width * 4, j + Camera.y);
			}
		}
		for(Entity e : Game.currentLevel.gameObjects) {
			//If the entity is within the camera rectangle, draw it.
			if(Camera.shouldRender(e.getBoundingBox())) {
				e.draw(g);
			}
		}
		
		drawItemGhost(g);
		
		drawLeftPanel(g);
	}
	
	private static void drawLeftPanel(Graphics g){
		g.setColor(Color.gray);
		float leftEdge = Camera.x + (Globals.width - 300);
		float panelWidth = 300;
		g.fillRect(leftEdge, Camera.y, panelWidth, Globals.height);
		Entity e = items.get(itemSelected);
		g.drawAnimation(e.anim, leftEdge + (panelWidth / 2) - (e.anim.getWidth() / 2), 50 + Camera.y);
	
		//Draw Item Selected Border
		g.setColor(Color.black);
		g.drawRect((leftEdge + (panelWidth / 2) - 64), 32 + Camera.y, 128, 128);
		
		drawSnapBox(g);
		drawHideDecorations(g);
		drawLinesBox(g);
		drawItemSelectArea(g);
		drawTileSetOptions(g);
		if(highlight != null) {
			g.setColor(new Color(255,0,0,50));
			g.fill(highlight);
		}
	}
	
	
	private static void drawItemSelectArea(Graphics g) {
		if(showItemSelect) {
			g.setColor(new Color(0.2F, 0.2F, 0.2F, 0.75F));
			g.fillRect(100 + Camera.x, 100 + Camera.y, Globals.width - 500, Globals.height - 150);
			Rectangle innerBox = new Rectangle(100 + Camera.x + 5, 105 + Camera.y, Globals.width - 510, Globals.height - 160);
			GradientFill innerBoxFill = new GradientFill(200,300,new Color(0.8F, 0.8F, 0.8F, 0.75F),200,1200, new Color(0.5F, 0.5F, 0.5F, 0.75F));
			g.fill(innerBox, innerBoxFill);
			drawItems(g);
		}
	}
	
	private static void drawItems(Graphics g){
		int col = 0;
		int row = 0;
		for(int i = 0; i < items.size(); i++) {
			if(i % 5 == 0 && i > 0) {
				col++;
				row = 0;
			}
			Entity e = items.get(i);
			g.setColor(Color.black);
			g.drawRect(((row * 128) + 128), ((col * 128) + 128), 96, 96);
			Rectangle temp = new Rectangle((row * 128) + 128, ((col * 128) + 128), 96,96);
			if(event != null && event.intersects(temp)) {
				if(Logic.mousePressed) {
					itemSelected = i;
				}
				g.setColor(new Color(255,0,0,100));
				g.fillRect(((row * 128) + 128), ((col * 128) + 128), 96, 96);
			}
			e.anim.draw((row * 128) + 144, (col * 128) + 144, 64, 64);
			row++;
		}
	}
	
	private static void drawLinesBox(Graphics g){
		if(drawLines) {
			g.setColor(Color.green);
		}
		else {
			g.setColor(Color.red);
		}
		
		g.fillRect(linesBox.getX(), linesBox.getY(), 20, 20);
		g.setFont(Resources.editorFont);
		g.drawString("Draw Lines", linesBox.getX() + 30, linesBox.getY());

	}
	
	
	private static void drawHideDecorations(Graphics g){
		if(hideDecorations) {
			g.setColor(Color.green);
		}
		else {
			g.setColor(Color.red);
		}
		
		g.fillRect(hideDecBox.getX(), hideDecBox.getY(), 20, 20);
		g.setFont(Resources.editorFont);
		g.drawString("Hide Decorations", hideDecBox.getX() + 30, hideDecBox.getY());

	}
	
	private static void drawTileSetOptions(Graphics g){
		if(tileSet == null) {
			try {
				tileSet = new Animation(new SpriteSheet(new Image("tileSet.png"), 64, 64), 100);
				tileSet.stop();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		else {
			for(int i = 0; i < tileSet.getFrameCount(); i++) {
				Rectangle temp = new Rectangle((Globals.width - 250) + (i * 64) + Camera.x, (Globals.height/2) + Camera.y, 64,64);
				if(event != null && event.intersects(temp)) {
					if(Logic.mousePressed) {
						if(items.get(itemSelected) instanceof Floor) {
							Floor f = (Floor) items.get(itemSelected);
							f.tileId = i;
							f.update(0);
							items.set(items.indexOf(items.get(itemSelected)), f);
						}
						
					}
					tileSet.getImage(i).draw(temp.getX(), temp.getY(), new Color(255, 0, 0, 50));
				}
				else {
					tileSet.getImage(i).draw(temp.getX(), temp.getY());
				}
			}
		}
		
	}
	
	private static void drawSnapBox(Graphics g){
		if(snapObjects) {
			g.setColor(Color.green);
		}
		else {
			g.setColor(Color.red);
		}
		
		g.fillRect(snapBox.getX(), snapBox.getY(), 20, 20);
		g.setFont(Resources.editorFont);
		g.drawString("Snap Objects", snapBox.getX() + 30, snapBox.getY());
	}
	
	private static void drawItemGhost(Graphics g){
		g.drawAnimation(items.get(itemSelected).anim, Globals.mouseX, Globals.mouseY);
	}
	
	
	
}
