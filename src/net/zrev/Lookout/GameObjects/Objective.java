package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

public class Objective extends Entity {

	public Objective(int id, Animation anim, float x, float y, float width,
			float height) {
		super(id, anim, x, y, width, height);
		isSolid = false;
	}

	
	

}
