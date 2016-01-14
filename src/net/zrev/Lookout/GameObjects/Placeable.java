package net.zrev.Lookout.GameObjects;

import net.zrev.Lookout.Core.Globals;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Placeable extends Entity implements Cloneable {

	public Placeable(Animation anim, float width, float height, int id) {
		super(anim, -99999, -99999, width, height);
		this.id = id;
	}

	public static Entity convertToEntity(Placeable p) {
		int x = (int) Globals.mouseX;
		int y = (int) Globals.mouseY;
		try {
			switch(p.id) {
			case 1:
				Animation floor = new Animation(new SpriteSheet(new Image("floor.png"), 128, 32), 100);
				Floor f = new Floor(floor, x, y, floor.getWidth(), floor.getHeight());
				f.wasPlaced = true;
				return f;
			case 2:
				Animation switchDir = new Animation(new SpriteSheet(new Image("switchDirLeft.png"), 96, 64), 150);
				SwitchDirections ls = new SwitchDirections(switchDir, x, y, switchDir.getWidth(), switchDir.getHeight(), 1);
				ls.wasPlaced = true;
				return ls;
			case 3:
				Animation switchDir2 = new Animation(new SpriteSheet(new Image("switchDirRight.png"), 96, 64), 150);
				SwitchDirections rs = new SwitchDirections(switchDir2, x, y, switchDir2.getWidth(), switchDir2.getHeight(), 2);
				rs.wasPlaced = true;
				return rs;
			case 4:
				Animation jumpButton = new Animation(new SpriteSheet(new Image("jumpButton.png"), 64, 96), 100);
				Jump j = new Jump(jumpButton, x, y, jumpButton.getWidth(), jumpButton.getHeight());
				j.wasPlaced = true;
				return j;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int id;
}
