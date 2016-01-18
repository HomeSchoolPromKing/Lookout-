package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

public class Floor extends Entity {

	public Floor(int id, Animation anim, float x, float y, float width, float height) {
		super(id, anim, x, y, width, height);
		onGround = true;
		isSolid = true;
	}

	public void update(int delta){
		
	}
}
