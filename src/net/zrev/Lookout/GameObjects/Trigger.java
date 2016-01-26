package net.zrev.Lookout.GameObjects;

import java.util.ArrayList;

import net.zrev.Lookout.Game.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Trigger extends Entity {

	public Trigger(int id, Animation anim, float x, float y, float width,
			float height, String[] params) {
		super(id, anim, x, y, width, height);
		anim.stop();
		isSolid = false;
		passive = true;
		this.params = params;
	}

	public void init(){
		if(params.length > 2) {
			triggerType = Integer.parseInt(params[3]);
			if(params.length > 3) {
				for(int i = 4; i < params.length; i++) {
					linkedEntities.add(Game.currentLevel.gameObjects.get(Integer.parseInt(params[i])));
				}
			}
		}
	}

	public void update(int delta){
		super.update(delta);
		if(hitting == null) {
			if(isActive) {
				undo = true;
				isActive = false;
				activated = true;
			}
		}
		else {
			isActive = true;
		}
		hitting = null;

		if(triggerType == 1 && (isActive || undo)) {
			showHideObjects();
		}
		else if(triggerType == 2 && (isActive || undo)) {
			raiseLower();
		}
		else if(triggerType == 3 && (isActive) && !undo) {
			raiseOnce();
		}
	}

	public void draw(Graphics g){
		if(isActive) 
			anim.setCurrentFrame(1);
		else
			anim.setCurrentFrame(0);
		anim.draw(x, y);
	}

	public void showHideObjects(){
		if(undo) {
			for(Entity e : linkedEntities) {
				e.isSelected = false;
			}
			undo = false;
		}
		else {
			for(Entity e : linkedEntities) {
				e.isSelected = true;
			}
		}
	}

	public void raiseLower(){
		if(undo) {
			for(Entity e : linkedEntities) {
				e.y += 128;
				e.updateBounds();
			}
			undo = false;
		}
		else if(!undo && isActive && activated) {
			for(Entity e : linkedEntities) {
				e.y -= 128;
				e.updateBounds();
			}
			isActive = false;
			undo = false;
			activated = false;
		}
	}

	public void raiseOnce(){
		for(Entity e : linkedEntities) {
			e.y -= 128;
			e.updateBounds();
		}
		undo = true;
	}


	private String[] params = null;
	public int triggerType = 0;
	public Entity hitting = null;
	public boolean isActive = false;
	public boolean activated = true;
	public boolean undo = false;
	public ArrayList<Entity> linkedEntities = new ArrayList<Entity>();
	//public ArrayList<Integer> linkedIDs = new ArrayList<Integer>();
}
