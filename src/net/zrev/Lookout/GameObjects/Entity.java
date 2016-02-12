package net.zrev.Lookout.GameObjects;

import net.zrev.Lookout.Game.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

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
					handleAction(e, DDOWN);
					velocityY = 0.0F;
					if(this.y + this.height > e.y && this.controlable == true && falling && e instanceof Floor) {
						this.y = e.y - this.height;
					}
					if(e.isSolid) {
						onGround = true;
						if(falling) falling = false;
					}
				}
				else {
					if(!onGround) {
						if(!falling && !jumping) {
							gravity = 0.0F;
							falling = true;
							onGround = false;
						}
					}
					else {
						resetGround = true;
					}
				}


				if(right.intersects(e.getBoundingBox())) {
					if(e.isSolid) {
						velocityX = 0.0F;
					}
					//x = e.x - e.width;
					handleAction(e, DRIGHT);
				}
				if(left.intersects(e.getBoundingBox())) {
					if(e.isSolid) {
						velocityX = 0.0F;
					}
					//x = e.x + e.width;
					handleAction(e, DLEFT);
				}
				if(e.collected) {
					Game.currentLevel.toRemove = e;
				}

				if(e instanceof Trigger) {
					if(getBoundingBox().intersects(e.getBoundingBox())) {
						if(controlable) {
							((Trigger) e).hitting = e;
						}
					}
				}
			}
		}

		if(!passive) {
			if(movingRight && !movingLeft) {
				if (spedUp) {
					velocityX = 12F;
				}
				else if (stopped) {
					velocityX = 0F;
				}
				else {
					velocityX = 6.0F;
				}
			}
			if(movingLeft && !movingRight) {
				if (spedUp) {
					velocityX = -12F;
				}
				else if (stopped) {
					velocityX = -0F;
				}
				else {
					velocityX = -6.0F;
				}
			}
		}
		updateSpeedUp();
		updateJump();
		updateStopped();

		if(!passive) {
			x += velocityX;
			y += velocityY;
		}
		
		if(resetGround) {
			resetGround = false;
			onGround = false;
		}
	}

	public void handleAction(Entity e, int direction){
		//Is placeable, basically
		if(e.passive) {
			if(e instanceof SwitchDirections && !e.collected) {
				switchDirection(-1);
				e.collected = true;
			}
			else if (e instanceof Coin) {
				if (e.collected == false && this instanceof Player){
					Game.currentLevel.levelScore += 100;
				}
			}
			else if (e instanceof SpeedUp) {
				if (e.collected == false && this.controlable){
					this.spedUp = true;
					this.stopped = false;
				}
			}
			else if (e instanceof Stop) {
				if(e.collected == false &&this.controlable) {
					this.stopped = true;
				}
			}
			if(e.collectable) {
				e.collected = true;
			}
		}
		if(e.isSolid && this.controlable) {
			if(direction == DRIGHT) {
				if(!bottom.intersects(e.getBoundingBox()) && !(e instanceof Jump)) {
					switchDirection(direction);
				}
			}
			if(direction == DLEFT) {
				if(!bottom.intersects(e.getBoundingBox()) && !(e instanceof Jump)) {
					switchDirection(direction);
				}
			}
		}
	}

	public void switchDirection(int direction){
		if(direction == -1) {
			if(movingLeft) {
				movingRight = true;
				movingLeft = false;
			}
			else if(movingRight) {
				movingLeft = true;
				movingRight = false;
			}
		}
		else {
			if(direction == DLEFT) {
				if(movingLeft) {
					movingRight = true;
					movingLeft = false;
				}
			}
			else if(direction == DRIGHT) {
				if(movingRight) {
					movingLeft = true;
					movingRight = false;
				}
			}
		}
	}

	public void updateSpeedUp(){
		if(spedUp) {
			if(currentSpeedUpTimer < maximumSpeedUpTimer) {
				currentSpeedUpTimer++;
			}
			else if(currentSpeedUpTimer >= maximumSpeedUpTimer) {
				spedUp = false;
				currentSpeedUpTimer = 0;
			}
		}
	}

	public void updateStopped(){
		if(stopped) {
			if(currentStoppedTimer < maximumStoppedTimer) {
				currentStoppedTimer++;
			}
			else if(currentStoppedTimer >= maximumStoppedTimer) {
				stopped = false;
				currentStoppedTimer = 0;
			}
		}
	}

	public void updateJump(){
		if(jumping) {
			gravity -= 0.5F;
			velocityY = (int) -gravity;
			if(gravity < 1.0F) {
				jumping = false; 
				falling = true;
			}
		}
		else if(falling) {
			gravity += 0.5F;
			velocityY = (int) gravity;
		}
	}

	public void jump() {
		if(!jumping && onGround) {
			y -= 10;
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

	public int DLEFT = 3, DRIGHT = 1, DUP = 0, DDOWN= 2;

	public int id;

	public int oid;

	public boolean passive = false;

	public boolean wasPlaced = false;

	public boolean isSelected = false;

	public boolean movingRight = false, movingLeft = false;

	public Entity collision = null;

	public float velocityX = 0;
	public float velocityY = 0.0F;

	public float gravity = 0.0f; 

	public boolean falling = false, jumping = false, onGround = false, resetGround = false;;

	public boolean isSolid = false;

	public boolean isHurt = false;

	public boolean collected = false;

	public boolean controlable = false;

	public boolean collectable = false;

	public boolean spedUp = false;

	public boolean stopped = false;

	public int currentSpeedUpTimer = 0, maximumSpeedUpTimer = 100;

	public int currentStoppedTimer = 0, maximumStoppedTimer = 100;

	public Animation anim;

	public float x, y, width, height;

	public Rectangle boundingBox, left, right, top, bottom;
}