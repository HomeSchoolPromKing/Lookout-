package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

public class WinZone extends Entity {

	public WinZone(Animation anim, float x, float y, float width, float height) {
		super(anim, x, y, width, height);
		onGround = true;
		isSolid = false;
	}
	
	public void update(int delta){
		onGround = true;
	}
}
