package net.zrev.Lookout.GameObjects;

import net.zrev.Lookout.Game.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import static net.zrev.Lookout.Core.Globals.*;

public abstract class Entity implements Cloneable {

	public Entity(Animation anim, float x, float y, float width, float height){
		boundingBox = new Rectangle(x, y, width, height);
		
		aboveCollision = new Rectangle(x, y - 5, width, 5);
		belowCollision = new Rectangle(x, y + height, width, 5);
		
		rightCollision = new Rectangle(x + width - 1, y, 1, height - 10);
		leftCollision = new Rectangle(x, y, 1, height - 10);
		this.anim = anim;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		onGround = false;
	}

	public void draw(Graphics g){
		if(DEBUG || anim == null) {
			g.fill(getBoundingBox());
			g.setColor(Color.green);
			g.drawRect(x, y, width, height);
			g.setColor(Color.orange);
			g.draw(rightCollision);
			g.setColor(Color.blue);
			g.draw(leftCollision);
			g.setColor(Color.black);
			g.draw(belowCollision);
			g.setColor(Color.magenta);
			g.draw(aboveCollision);
		}
		else {
			g.drawAnimation(anim, x, y);
		}
	}
	public Entity clone() {
		try {
			return (Entity) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void update(int delta){
		
		aboveCollision = new Rectangle(x, y - 5, width, 5);
		objectAbove = null;
		
		belowCollision = new Rectangle(x, y + height, width, 5);
		objectBelow = null;
		
		leftCollision = new Rectangle(x-1, y, width/2, height - 15);
		objectLeft = null;
		
		rightCollision = new Rectangle(x + (width / 2) - 1, y, width / 2, height - 15);
		objectRight = null;
		
		
		boundingBox = new Rectangle(x, y, width, height);
		
		
		x += velocityX;
		if(!onGround && isSolid) {
		
			velocityY += gravity;
			y += velocityY;
		}
		
		for(Entity e : Game.currentLevel.gameObjects) {
			if(e != this) {
				collisionLeft(e);
				collisionRight(e);
				collisionBelow(e);
				collisionAbove(e);
			}
		}
		if(objectBelow == null && isSolid) {
			onGround = false;
		}
		
		if(objectLeft !=null && objectLeft instanceof Floor) {
			movingLeft = false;
			movingRight = false;
		}
		
		if(objectRight !=null && objectRight instanceof Floor) {
			movingLeft = false;
			movingRight = false;
		}
		
		if(objectBelow instanceof Floor && isSolid) {
			if((objectLeft == null || !objectLeft.isSolid) && (objectRight == null ||!objectRight.isSolid )) {
				if((this.y + this.height) > objectBelow.y) {
					this.y = objectBelow.y - this.height;
					onGround = true;
					velocityY = 0.0F;
				}
			}
		}
	}

	public void collisionRight(Entity e){
		if(rightCollision.intersects(e.getBoundingBox())) {
			objectRight = e;
		}
	}
	
	public void collisionLeft(Entity e){
		if(leftCollision.intersects(e.getBoundingBox())) {
			objectLeft = e;
		}
	}
	
	public void collisionBelow(Entity e){
		if(belowCollision.intersects(e.getBoundingBox())) {
			objectBelow = e;
		}
	}
	
	public void collisionAbove(Entity e){
		if(aboveCollision.intersects(e.getBoundingBox())) {
			objectAbove = e;
		}
	}
	
	public void jump() {
		onGround = false;
		velocityY = -12.0F;
	}

	public boolean collides(Entity other){
		if(this.getBoundingBox().intersects(other.getBoundingBox())) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setBoundingBox(Rectangle boundingBox){
		this.boundingBox = boundingBox;
	}

	public Rectangle getBoundingBox(){
		return boundingBox;
	}

	public Animation getAnim() {
		return anim;
	}

	public void setAnim(Animation anim) {
		this.anim = anim;
	}
	
	
	
	public boolean wasPlaced = false;
	public Entity objectLeft = null, objectRight = null, objectAbove = null, objectBelow = null;
	private Rectangle leftCollision, rightCollision, aboveCollision, belowCollision;
	public boolean movingRight = false, movingLeft = false;
	public Entity collision = null;
	public boolean onGround = false;
	public float velocityX = 0;
	public float velocityY = 0.0F;
	public float friction = 0.5F;
	public float gravity = 0.5f; 
	public boolean isSolid = false;
	public Animation anim;
	public float x, y, width, height;
	private Rectangle boundingBox;
}
