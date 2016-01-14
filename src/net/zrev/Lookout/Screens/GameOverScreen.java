package net.zrev.Lookout.Screens;

import java.awt.Font;

import net.zrev.Lookout.Core.Globals;
import net.zrev.Lookout.Core.Resources;
import net.zrev.Lookout.Game.Camera;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class GameOverScreen {

	public static void draw(Graphics g) {
		g.pushTransform();
		g.setColor(Color.red);
		g.fillRect(Camera.x, Camera.y, Globals.width, Globals.height);
		g.scale(1.5F, 1.5F);
		g.setColor(Color.black);
		g.setFont(Resources.gameFont);
		g.drawString("Game Over", (float) ((Globals.width / 3.5) - 25), Globals.height / 3);
		g.popTransform();
	}

}
