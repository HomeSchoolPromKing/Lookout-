package net.zrev.Lookout.GameObjects;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Game.Camera;
import net.zrev.Lookout.Game.Game;
import net.zrev.Lookout.Game.Level;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player extends Entity {

	public Player(Animation anim, float x, float y, float width, float height){
		super(anim, x, y, width, height);
		isSolid = true;
		initPlayerImages();
		movingRight = true;
		ai = new AfterImage(anim, x, y, width, height);
	}

	public void initPlayerImages() {
		try {
			runRight = new Animation(new SpriteSheet(new Image("characterRunRight.png"), 34, 64), 150);
			runLeft = new Animation(new SpriteSheet(new Image("characterRunLeft.png"), 34, 64), 150);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		if(!onGround)
		g.drawAnimation(ai.anim, ai.x, ai.y, new Color(1f,1f,1f,0.5f));
	}

	public void update(int delta){
		if(!Game.currentLevel.isFailed && !Game.currentLevel.isCompleted) {
			super.update(delta);
			moveX();
			
			whatToDoObjectRight();
			whatToDoObjectLeft();
			Camera.x = x - ((Camera.width - 540) / 2) ;
			Camera.y = y - (Camera.height / 2);
			checkIfDead();
			ai.update(delta);
		}
	}
	
	private void checkIfDead(){
		if(Game.p.y > Game.maxOffsetY) {
			Game.currentLevel.isFailed = true;
		}
	}
	
	private void moveX(){
		if((objectLeft instanceof Wall) || (objectRight instanceof Wall)) {
			movingLeft = false;
			movingRight = false;
		}
		
		if(objectLeft == null && !movingLeft && !movingRight) {
			movingLeft = true;
		}
		if(objectRight == null && !movingLeft && !movingRight) {
			movingRight = true;
		}
		
		if(super.movingRight && !super.movingLeft) {
			anim = runRight;
			velocityX = 4.5F;
		}
		else if(super.movingLeft && !super.movingRight) {
			anim = runLeft;
			velocityX = -4.5F;
		}
		else if(!movingLeft && !movingRight) {
			velocityX = 0F;
		}
	}

	private void whatToDoObjectRight() {
		if(objectRight != null) {
			if(objectRight instanceof SwitchDirections) {
				if(((SwitchDirections) objectRight).direction == 1) {
					super.movingLeft = true;
					super.movingRight = false;
					Game.currentLevel.toRemove = objectRight;
				}
				else if(objectRight instanceof Jump) {
					Game.p.jump();
					ai.jump();
					Game.currentLevel.toRemove = objectRight;
				}
				else if(objectRight instanceof WinZone) {
					Game.currentLevel.isCompleted = true;
				}
				else if(objectRight instanceof Saw) {
					Game.p.health = 0;
					Game.currentLevel.isFailed = true;
				}
			}
		}
	}
	
	private void whatToDoObjectLeft() {
		if(objectLeft != null) {
			if(objectLeft instanceof SwitchDirections) {
				if(((SwitchDirections) objectLeft).direction == 2) {
					super.movingLeft = false;
					super.movingRight = true;
				}
				Game.currentLevel.toRemove = objectLeft;
			}
			else if(objectLeft instanceof Jump) {
				Game.p.jump();
				Game.currentLevel.toRemove = objectLeft;
			}
			else if(objectLeft instanceof WinZone) {
				Game.currentLevel.isCompleted = true;
			}
			else if(objectLeft instanceof Saw) {
				Game.p.health = 0;
				Game.currentLevel.isFailed = true;
			}
		}
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	private AfterImage ai = null;
	public Animation runRight = null, runLeft = null;
	private int health = 0;
}
