package net.zrev.Lookout.GameObjects;

import java.util.Random;

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

	public Player(int id, Animation anim, float x, float y, float width, float height){
		super(id, anim, x, y, width, height);
		isSolid = true;
		initPlayerImages();
		movingRight = true;
		ai = new AfterImage(-999, anim, x, y, width, height);
	}

	public void initPlayerImages() {
		try {
			runRight = new Animation(new SpriteSheet(new Image("characterRunRight.png"), 64, 128), 150);
			runLeft = new Animation(new SpriteSheet(new Image("characterRunLeft.png"), 64, 128), 150);
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
			if(!isHurt) {
				moveX();
			}
			else {
				jumpBack();
			}
			
			whatToDoObjectRight();
			whatToDoObjectLeft();
			Camera.x = x - ((Camera.width - 540) / 2) ;
			Camera.y = y - (Camera.height / 2);
			checkIfDead();
			ai.update(delta);
		}
	}
	
	public void jumpBack(){
		if(isHurt) {
			if(movingLeft && !movingRight) {
				if(velocityX < 0) {
					velocityX = 0.0F;
				}
				if((velocityX + 1.0F) < maxJumpBackX) {
					velocityX += 1.0F;
				}
				else {
					isHurt = false;
					movingRight = true;
					movingLeft = false;
				}
			}
			else if(movingRight && !movingLeft) {
				if(velocityX > 0) {
					velocityX = 0.0F;
				}
				if((Math.abs(velocityX) - 1.0F) < maxJumpBackX) {
					velocityX -= 1.0F;
				}
				else {
					isHurt = false;
					movingRight = false;
					movingLeft = true;
				}
			}
		}
	}
	
	//added ouch method	
	private void ouch() {
		if (movingRight) {
			isHurt = true;
			//Game.p.health -= 1;
			onGround = false;
			Game.p.velocityY = -6.0f;
			Game.p.velocityX = -3.0f;
		}
		else if (movingLeft) {
			isHurt = true;
			//Game.p.health -= 1;
			onGround = false;
			Game.p.velocityY = -6.0f;
			Game.p.velocityX = 3.0f;
		}
	}
	
	private void checkIfDead(){
		if(Game.p.y > Game.maxOffsetY) {
			Game.currentLevel.isFailed = true;
		}
	}
	
	private void moveX(){
		if((objectLeft instanceof Floor) || (objectRight instanceof Floor)) {
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
			velocityX = 8.5F;
		}
		else if(super.movingLeft && !super.movingRight) {
			anim = runLeft;
			velocityX = -8.5F;
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
					//Game.currentLevel.isFailed = true;
					ouch();
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
				ouch();
				Game.p.health = 0;
				//Game.currentLevel.isFailed = true;
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