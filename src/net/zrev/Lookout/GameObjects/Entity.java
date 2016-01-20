package net.zrev.Lookout.GameObjects;

import java.util.Random;

import net.zrev.Lookout.Game.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import static net.zrev.Lookout.Core.Globals.*;

public abstract class Entity implements Cloneable {

	public Entity(int id, Animation anim, float x, float y, float width, float height){
		boundingBox = new Rectangle(x, y, width, height);
		
		left = new Rectangle(x, y + 10, 5, - 20);
		right = new Rectangle(x + width - 5, y + 10, 5, height - 20);
		top = new Rectangle(x + 10, y, width - 20, 5);
		bottom = new Rectangle(x + 10, y + height - 5, width - 20, 5);
		
		this.anim = anim;
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g){
		if(DEBUG || anim == null) {
			//g.drawRect(x, y, width, height);
			//g.setColor(Color.orange);
			//g.draw(rightCollision);
			//g.setColor(Color.blue);
			//g.draw(leftCollision);
			g.drawAnimation(anim, x, y);
			g.setColor(new Color(0, 255, 0, 50));
			g.fill(getBoundingBox());
		}
		else {
			if(!isSelected) {
				g.drawAnimation(anim, x, y);
			}
			else {
				g.drawAnimation(anim, x, y, new Color(255, 255, 0, 1.0F));
			}
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

	public void updateBounds(){
		boundingBox = new Rectangle(x, y, width, height);
		left = new Rectangle(x, y + 10, 5, height - 20);
		right = new Rectangle(x + width - 5, y + 10, 5, height - 20);
		top = new Rectangle(x + 10, y, width - 20, 5);
		bottom = new Rectangle(x + 10, y + height - 5, width - 20, 5);
	}

	public void update(int delta){
		updateBounds();
		for(Entity e : Game.currentLevel.gameObjects) {
			if(e != this) {
				if(getBoundingBox().intersects(e.getBoundingBox()) && e instanceof Jump && !jumping) {
					jump();
					e.collected = true;
				}
				if(top.intersects(e.getBoundingBox()) && e.isSolid && !e.passive) {
					velocityY = 0.0F;
					if(jumping) {
						jumping = false;
						gravity = 0.0F;
						falling = true;
					}
					handleAction(e, 0);
				}
				if(bottom.intersects(e.getBoundingBox())) {
					handleAction(e, 2);
					velocityY = 0.0F;
					if(this.y + this.height > e.y && this instanceof Player && falling && e instanceof Floor) {
						this.y = e.y - this.height;
					}
					if(falling) falling = false;
				}
				else {
					if(!falling && !jumping) {
						gravity = 0.0F;
						falling = true;
					}
				}
				if(right.intersects(e.getBoundingBox())) {
					velocityX = 0.0F;
					//x = e.x - e.width;
					handleAction(e, 1);
				}
				if(left.intersects(e.getBoundingBox())) {
					velocityX = 0.0F;
					//x = e.x + e.width;
					handleAction(e, 3);
				}
				if(e.collected) {
					Game.currentLevel.toRemove = e;
				}
			}
		}
		
		if(!passive) {
			if(movingRight && !movingLeft) {
				velocityX = 8.0F;
			}
			if(movingLeft && !movingRight) {
				velocityX = -8.0F;
			}
		}
		
		if(jumping) {
			gravity -= 0.5F;
			velocityY = (int) -gravity;
			if(gravity < 1.0F) {
				jumping = false; 
				falling = true;
			}
		}
		if(falling) {
			gravity += 0.5F;
			velocityY = (int) gravity;
		}
		
		if(!passive) {
			x += velocityX;
			y += velocityY;
		}
	}
	
	public void handleAction(Entity e, int direction){
		
	}

	public Entity collision() {
		Entity toReturn = null;

		return toReturn;
	}

	public void jump() {
		if(!jumping) {
			y-= 10;
			gravity = 12.0F;
			jumping = true;
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

	public String save(){
		return id + "\t" + x + "\t" + y + "\t";
	}


	
	
	public int id;
	
	public boolean passive = false;
	
	public boolean wasPlaced = false;
	
	public boolean isSelected = false;
	
	public boolean movingRight = false, movingLeft = false;
	
	public Entity collision = null;
	
	public float velocityX = 0;
	public float velocityY = 0.0F;
	
	public float gravity = 0.0f; 
	
	public boolean falling = false, jumping = false;
	
	public boolean isSolid = false;
	
	public boolean isHurt = false;
	
	public boolean collected = false;
	
	public Animation anim;
	
	public float x, y, width, height;
	
	public Rectangle boundingBox, left, right, top, bottom;
}