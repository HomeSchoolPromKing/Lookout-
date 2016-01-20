package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

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
	}
	
	
	int enemyID = 0;
}
