package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

public class Coin extends Entity {

	public Coin(int id, Animation anim, float x, float y, float width, float height, String[] params) {
		super(id, anim, x, y, width, height);
		isSolid = false;
		passive = true;
		collectable = true;
	}
	
	public void update(int delta){
		//super.update(delta);
	}
	
}

