package net.zrev.Lookout.GameObjects;

import java.util.Random;

import net.zrev.Lookout.Core.Core;
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
		isSolid = false;
		initPlayerImages();
		movingLeft = false;
		movingRight = true;
		controlable = true;
		//ai = new AfterImage(-999, anim, x, y, width, height);
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
	
	public void screenShake(int delta){
        if (shakeAmt>0f) {
            shakeTime -= delta;
            //new shakeX/Y
            if (shakeTime <= 0) {
                shake();
            }
        }	
	}
	
	public static void shake() {
		Core.shakeX = (float)(Math.random()*shakeAmt);
		Core.shakeY = (float)(Math.random()*shakeAmt);
		if (SHAKE_SNAP) {
			Core.shakeX = (int)Core.shakeX;
			Core.shakeY = (int)Core.shakeY;
		}
		shakeTime = SHAKE_DELAY;
		shakeAmt -= SHAKE_DECAY*SHAKE_INTENSITY;
		if (shakeAmt<0f)
			shakeAmt = 0f;
	}

	public void draw(Graphics g) {
		super.draw(g);
		//g.drawAnimation(ai.anim, ai.x, ai.y, new Color(1f,1f,1f,0.5f));
	}

	public void update(int delta){
		if(!Game.currentLevel.isFailed && !Game.currentLevel.isCompleted) {
			super.update(delta);
			Camera.x = x - ((Camera.width - 540) / 2) ;
			Camera.y = y - (Camera.height / 2);
			checkIfDead();
			if(movingRight)
				anim = runRight;
			else if(movingLeft)
				anim = runLeft;
			//ai.update(delta);
		}
		screenShake(delta);
	}

	public void handleAction(Entity e, int direction) {
		super.handleAction(e, direction);

		if(e instanceof WinZone) {
			Game.currentLevel.isCompleted =  true;
		}
		
		if(e instanceof Saw) {
			shakeAmt = SHAKE_INTENSITY;
			shake();
		}
		if(e instanceof Enemy) {
			shakeAmt = SHAKE_INTENSITY;
			shake();
		}
		if(e instanceof Floor) {
			//Is breakable
			if(((Floor) e).tileId == 3 && (this.spedUp || Math.abs(this.velocityY)  > 4.0F)) {
				Game.currentLevel.toRemove = e;
			}
		}
	}

	private void checkIfDead(){
		if(Game.p.y > Game.maxOffsetY) {
			Game.currentLevel.isFailed = true;
		}
	}

	
    public static final boolean SHAKE_SNAP = false;
    
    /** How far the shake should extend in pixels. */
    public static final int SHAKE_INTENSITY = 15;
    
    public static final float SHAKE_DECAY = 0.03f;
    
    /** Delay in ms between each new shake. */
    public static final int SHAKE_DELAY = 45;

	private static int shakeTime = SHAKE_DELAY;
	public static float shakeAmt = 0f;


	private AfterImage ai = null;
	public Animation runRight = null, runLeft = null;

}