package net.zrev.Lookout.Decorative;

import java.util.ArrayList;
import java.util.Random;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Core.Resources;
import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Game.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class BackgroundLayer {


	public static void draw(Graphics g){
		g.setColor(Color.black);
		g.fillRect(Camera.x, Camera.y, Camera.width, Camera.height);

		if(Resources.initated) {
			g.pushTransform();
			g.scale(2.0F, 2.5F);
			g.drawImage(Resources.bg, -400, -400);
			g.popTransform();
			g.drawImage(Resources.bg55, Camera.x, Camera.y);
		}

		for(BackgroundShape bs : backgroundShapes) {
			bs.draw(g);
			bs.setX(bs.getX() - bs.speedX);
			bs.setY(bs.getY() - bs.speedY);
			bs.update();
		}
		update();
	}


	public static void loadBackgroundShapes(){
		for(int i = 0; i < 20; i++) {
			backgroundShapes.add(generateShape());
		}
	}

	public static void reset(){
		backgroundShapes.clear();
		loadBackgroundShapes();
	}

	private static BackgroundShape generateShape(){
		float tX = new Random().nextInt((int) (Globals.width * 1.5F)) + Camera.x;
		float tY = new Random().nextInt((int) Globals.height);
		float tW = new Random().nextInt(32)+32;
		float tH = new Random().nextInt(32)+32;
		
		int multiplerX = 0;
		int multiplerY = 0;
		if(new Random().nextBoolean()) {
			multiplerX= 1;
		}
		else {
			multiplerX = -1;
		}
		
		if(new Random().nextBoolean()) {
			multiplerY= 1;
		}
		else {
			multiplerY = -1;
		}
		
		float speedX = Math.abs(new Random().nextInt(3)+2);
		float speedY = Math.abs(new Random().nextInt(3)+2);
		speedX *= multiplerX;
		speedY *= multiplerY;
		BackgroundShape toAdd = new BackgroundShape(tX, tY, tW, tH, randColor(), speedX + 1, speedY + 1);
		return toAdd;
	}
	
	private static Color randColor() {
		Color sqColor = null;
		switch(new Random().nextInt(5)) {
		case 0:
			sqColor = new Color(46, 9, 39);
			break;
		case 1:
			sqColor = new Color(217, 0, 0);
			break;
		case 2:
			sqColor = new Color(255, 216, 0);
			break;
		case 3:
			sqColor = new Color(255, 140, 0);
			break;
		case 4:
			sqColor = new Color(4, 117, 111);
			break;
		default:
			sqColor = new Color(1, 1, 1);
			break;
		}
		return sqColor;
	}

	private static void update(){
		backgroundShapes.remove(toRemove);

		if(backgroundShapes.size() < 20) {
			backgroundShapes.add(generateShape());

		}
	}

	public static ArrayList<BackgroundShape> backgroundShapes = new ArrayList<BackgroundShape>();
	public static BackgroundShape toRemove = null;
}
