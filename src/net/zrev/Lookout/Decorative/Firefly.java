package net.zrev.Lookout.Decorative;

import java.util.Arrays;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Game.Camera;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Firefly extends Rectangle {

	//Rectangle
	public Firefly(float x, float y, float width, float height, Color c, float speedX, float speedY) {
		//super(new float[] {x, y, x, y + height, x + width, y + height, x+width, y});
		super(x, y, width, height);
		this.width = width;
		this.height = height;
		this.c = c;
		this.speedX = speedX;
		this.speedY = speedY;
		origX = x;
		origY = y;
		originalWidth = width;
		originalHeight = height;
		try {
			anim = new Animation(new SpriteSheet( new Image("firefly.png"), 4, 4), 100);
			anim.setPingPong(true);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}

	public void draw(Graphics g) {

		if(scaleFactor <= maxScaleFactor) {
			//g.translate(-(rightEdge - getCenterX()), 1.0F);
			//g.scale(scaleFactor, 1.0F);
			//x *= scaleFactor;
			//g.translate((x + (width / 2)), height / 2);
			if(Camera.shouldRender(this)) {
				this.width = originalWidth * scaleFactor;
				this.height = originalHeight * scaleFactor;
				setWidth(width);
				setHeight(height);
				x = origX - (width / 2);
				y = origY - (height / 2);
				scaleFactor += 0.03;
				g.setColor(c);
				g.drawAnimation(anim, x, y);
				//g.fill(this);
			}
		}
		else {
			if(Camera.shouldRender(this)) {
				//g.setColor(c);
				//g.fill(this);
				g.drawAnimation(anim, x, y);
			}
		}

	}

	public void update(){
		if(x + (width * 2) < Camera.x) {
			BackgroundLayer.toRemove = this;
		}
		if(y + (height * 2) < 0){
			BackgroundLayer.toRemove = this;
		}
		if((x - width) > Globals.width + Camera.x ) {
			BackgroundLayer.toRemove = this;
		}
		if(y - height > Globals.height){
			BackgroundLayer.toRemove = this;
		}
	}

	public Animation anim;
	public float origX, origY;
	public float originalWidth, originalHeight;
	public float width, height;
	public float maxScaleFactor = 1.0F;
	public float scaleFactor = 0.01F;
	public boolean offScreen = false;
	public float speedX;
	public float speedY;
	public Color c;
}
