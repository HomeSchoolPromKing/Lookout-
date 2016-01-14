package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;

public class Placeable extends Entity implements Cloneable {

	public Placeable(Animation anim, float width, float height, int id) {
		super(anim, -99999, -99999, width, height);
		this.id = id;
	}
	
	
	
	
	public int id;
}
