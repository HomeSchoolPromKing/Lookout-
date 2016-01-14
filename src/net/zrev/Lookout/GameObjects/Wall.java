package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

public class Wall extends Entity {

	public Wall(Animation anim, float x, float y, float width, float height) {
		super(anim, x, y, width, height);
		onGround = true;
		isSolid = true;
	}

	public void update(int delta){
		
	}
}