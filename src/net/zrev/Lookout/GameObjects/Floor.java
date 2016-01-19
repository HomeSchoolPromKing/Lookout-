package net.zrev.Lookout.GameObjects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Floor extends Entity {

	public Floor(int id, Animation anim, float x, float y, float width, float height, String[] params) {
		super(id, anim, x, y, width, height);
		try {
			if(params.length-1 >= 3) {
				super.anim = new Animation(new SpriteSheet(new Image("tileSet.png"), 64, 64), 100);
				int frame = Integer.parseInt(params[params.length-1]);
				tileId = frame;
				super.anim.setCurrentFrame(frame);
				super.anim.stop();
			}
			else {
				super.anim = new Animation(new SpriteSheet(new Image("tileSet.png"), 64, 64), 100);
				super.anim.setCurrentFrame(tileId);
				super.anim.stop();
			}

		} 
		catch (SlickException e) {
			e.printStackTrace();
		}

		onGround = true;
		isSolid = true;
	}

	public String save(){
		return id + "\t" + x + "\t" + y + "\t" + tileId;
	}

	public void update(int delta){
		try {
			if(tileId != anim.getFrame()) {
				super.anim = new Animation(new SpriteSheet(new Image("tileSet.png"), 64, 64), 100);
				super.anim.setCurrentFrame(tileId);
				super.anim.stop();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int tileId = 2;
}
