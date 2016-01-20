package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

public class Jump extends Entity {

	public Jump(int id, Animation anim, float x, float y, float width, float height) {
		super(id, anim, x, y, width, height);
		isSolid = false;
		passive = true;
	}
	
	public void update(int delta){
		super.update(delta);
		
	}
}
