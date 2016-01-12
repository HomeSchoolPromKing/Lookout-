package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

public class SwitchDirections extends Entity {

	public SwitchDirections(Animation anim, float x, float y, float width, float height, int direction) {
		super(anim, x, y, width, height);
		onGround = true;
		isSolid = false;
		this.direction = direction;
	}
	
	public void update(int delta){
		
	}
	
	public int direction;
	
}
