package net.zrev.Lookout.Decorative;

import java.util.Random;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Game.Camera;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class NorthernLights {

	public NorthernLights(float x, float y){
		r = new Random();
		startColor = new Color(0, r.nextInt(255), r.nextInt(255), r.nextInt(100));
		
		currentColor = startColor;
		currentColor = currentColor.darker(0.65F);
		height = r.nextInt(500) + 150;
		this.y = y + Camera.y;
		delayMax = r.nextInt(100) + 50;
		this.x = x;
		try {
			a = new Animation(new SpriteSheet(new Image("nlights.png"), 12, 594), 100);
		} 
		catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		a.draw(Camera.x + x, Camera.y + y + 300, 100, height + 200 , currentColor);
	}
	public void update(){
		delay++;
		if(delay >= delayMax) {
			brightness++;
			if(brightness <= 50 && brightness >= 0) {
				currentColor = currentColor.brighter(0.01F);
				if(height < 300)
					height += r.nextInt(5);
			}
			else if( brightness < 0) {
				currentColor = currentColor.darker(0.01F);
					height -= r.nextInt(5);
			}
			
			if(brightness >= 50) {
				brightness = -50;
			}
		}
	}
	
	
	public float y;
	public float height;
	public int brightness = 0;
	public Random r;
	private int delay = 0, delayMax;
	public float x;
	public Color startColor, endColor, currentColor;
	public Animation a;
}
