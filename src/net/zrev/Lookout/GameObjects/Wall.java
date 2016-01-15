package net.zrev.Lookout.GameObjects;

import net.zrev.Lookout.Game.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Wall extends Entity {

	public Wall(Animation anim, float x, float y, float width, float height) {
		super(anim, x, y, width, height);
		onGround = true;
		isSolid = true;
	}

	public void update(int delta){
		
	}
}
