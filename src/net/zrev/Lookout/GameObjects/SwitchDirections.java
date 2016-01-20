package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

public class SwitchDirections extends Entity {

	public SwitchDirections(int id, Animation anim, float x, float y, float width, float height, int direction) {
		super(id, anim, x, y, width, height);
		isSolid = false;
		this.direction = direction;
		passive = true;
	}
	
	public void update(int delta){
		super.update(delta);
	}
	
	public String save(){
		return id + "\t" + x + "\t" + y + "\t" + direction;
	}
	
	public int direction;
	
}
