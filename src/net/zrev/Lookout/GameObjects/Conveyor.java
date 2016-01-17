package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

public class Conveyor extends Entity{

	public Conveyor(Animation anim, float x, float y, float width, float height) {
		super(anim, x, y, width, height);
		onGround = true;
		isSolid = true;
	}

	
	public void update(int delta){
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	private float speed = 3;
}