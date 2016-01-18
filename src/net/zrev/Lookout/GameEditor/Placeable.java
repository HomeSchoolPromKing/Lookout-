package net.zrev.Lookout.GameEditor;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.GameObjects.Entity;
import net.zrev.Lookout.GameObjects.Floor;
import net.zrev.Lookout.GameObjects.Jump;
import net.zrev.Lookout.GameObjects.Player;
import net.zrev.Lookout.GameObjects.Saw;
import net.zrev.Lookout.GameObjects.SwitchDirections;
import net.zrev.Lookout.GameObjects.WinZone;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Placeable extends Entity implements Cloneable {

	public Placeable(Animation anim, float width, float height, int id) {
		super(id, anim, -99999, -99999, width, height);
	}

}
