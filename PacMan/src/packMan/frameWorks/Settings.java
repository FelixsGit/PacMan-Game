package packMan.frameWorks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import packMan.Window.Game;


public class Settings {

	public void render(Graphics g){
		
		Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 50);
		g.setFont(fnt0);
		g.setColor(Color.black);
		Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
		g.setFont(fnt1);
		g.drawString("Back", Game.WIDTH - 135 , Game.HEIGHT - 65);
		g2d.draw(backButton);
	}
}
