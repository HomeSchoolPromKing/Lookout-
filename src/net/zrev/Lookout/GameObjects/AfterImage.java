package net.zrev.Lookout.GameObjects;

import java.util.Timer;
import java.util.TimerTask;

import net.zrev.Lookout.Game.Game;

import org.newdawn.slick.Animation;


public class AfterImage extends Entity {

	public AfterImage(Animation anim, float x, float y, float width, float height){
		super(anim, x, y, width, height);
		isSolid = true;
		movingRight = true;
		init();
	}
	
	private void init(){
		t.scheduleAtFixedRate(new TimerTask() {
			public void run(){
				if(Game.p.movingRight) {
					anim = Game.p.runRight;
				}
				else {
					anim = Game.p.runLeft;
				}
				y = Game.p.y;
			}
		}, 400, 1);
	}
	
	
	
	public void update(int delta){
		if(!Game.p.onGround) {
			if(Game.p.movingLeft) {
				x = Game.p.x + 16;
			}
			else if(Game.p.movingRight){
				x = Game.p.x - 16;
			}
		}
		
	}
	
	Timer t = new Timer();
	
	
}