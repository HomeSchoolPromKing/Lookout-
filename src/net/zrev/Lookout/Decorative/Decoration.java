package net.zrev.Lookout.Decorative;

import java.util.ArrayList;
import java.util.Scanner;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Core.Resources;
import net.zrev.Lookout.Game.Game;
import net.zrev.Lookout.Game.Level;
import net.zrev.Lookout.GameObjects.Entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Decoration {

	public Decoration(Animation a, float x, float y){
		this.a = a;
		this.x = x;
		this.y = y;
	}
	
	public static void reset(){
		
	}
	
	public static Decoration idToDecoration(int id, float x, float y, String[] params){
		Animation temp = new Animation();
		Decoration toReturn = null;
		String decorationInDB = findDecoration(id);
		String[] animationParams = decorationInDB.split("\\t");
		int aid = Integer.parseInt(animationParams[0]);
		String name = animationParams[1];
		int width = Integer.parseInt(animationParams[2]);
		int height = Integer.parseInt(animationParams[3]);
		int speed = 100;
		if(animationParams.length > 4) {
			speed = Integer.parseInt(animationParams[4]);
		}
		try {
			temp = new Animation(new SpriteSheet(new Image(name), width, height), speed);
			if(animationParams.length > 5) {
				int pingpong = Integer.parseInt(animationParams[5]);
				if(pingpong == 1) {
					temp.setPingPong(true);
				}
			}
			toReturn = new Decoration(temp, x, y);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	

	public static String findDecoration(int id){
		Scanner s  = new Scanner(Globals.class.getResourceAsStream("/decorations.txt"));
		try {
			while(s.hasNextLine()) {
				String decorationToCheck = s.nextLine();
				String params[] = decorationToCheck.split("\\t");
				int lineId = Integer.parseInt(params[0]);
				if(lineId == id) {
					return decorationToCheck;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void parseDecoration(String line) {
		if(!line.contains("------Decorations------") && !line.contains("------Items------")) {
			String[] params = line.split("\\t");
			try {
				int id = Integer.parseInt(params[0]);
				float x = Float.parseFloat(params[1]);
				float y = Float.parseFloat(params[2]);
				Level.backgroundDecorations.add(idToDecoration(id, x, y, params));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public float x, y;
	public Animation a;
}
