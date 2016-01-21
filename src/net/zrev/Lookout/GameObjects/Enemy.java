package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

public class Enemy extends Entity{

	public Enemy(int id, Animation anim, float x, float y, float width, float height, String[] params) {
		super(id, anim, x, y, width, height);
		isSolid = false;
		controlable = true;
		if(params.length-1 >= 3) {
			enemyID = Integer.parseInt(params[params.length-1]);
		}
		else{
			enemyID = 0;
		}
		movingRight = true;
	}
	
	public void update(int delta){
		super.update(delta);
		
		angle+=4;
	}
	
	public void draw(Graphics g){
		g.pushTransform();
		g.rotate(getBoundingBox().getCenterX(), getBoundingBox().getCenterY(), angle);
		g.drawAnimation(anim, x, y);
		g.popTransform();
	}
	
	public int angle = 0;
	int enemyID = 0;
}
