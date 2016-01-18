package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

public class WinZone extends Entity {

	public WinZone(int id, Animation anim, float x, float y, float width, float height) {
		super(id, anim, x, y, width, height);
		onGround = true;
		isSolid = false;
	}
	
	public void update(int delta){
		super.update(delta);
		
	}
}
