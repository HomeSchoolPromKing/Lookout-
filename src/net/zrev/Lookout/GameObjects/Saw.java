package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

public class Saw extends Entity {

	public Saw(Animation anim, float x, float y, float width, float height) {
		super(anim, x, y, width, height);
		onGround = true;
		isSolid = false;
	}
	
	public void update(int delta){
		super.update(delta);
	}
	
}
