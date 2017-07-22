package packMan.frameWorks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import packMan.Window.Game;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH/2 - 60, Game.HEIGHT/2 - 75 , 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH/2 - 60, Game.HEIGHT/2  , 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2 - 60, Game.HEIGHT/2 + 75, 100, 50);
	public Rectangle settingsButton = new Rectangle(Game.WIDTH/2 - 65, Game.HEIGHT/2 + 150, 120, 50);
	
	public void render(Graphics g){
		
		
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.black);
		
		Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 30);
		g.setFont(fnt1);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Play", playButton.x + 15  , playButton.y + 35);
		g.drawString("Help", playButton.x + 15  , playButton.y + 110);
		g.drawString("Quit", playButton.x + 15  , playButton.y + 185);
		g.drawString("Settings", playButton.x   , playButton.y + 260);
		g.setColor(Color.DARK_GRAY);
		Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
		g.setFont(fnt2);
		g.setFont(fnt1);
		g.setColor(Color.black);
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
		g2d.draw(settingsButton);
		g.setColor(Color.DARK_GRAY);
		Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 80);
		g.setFont(fnt3);
		g.drawString("PONG", playButton.x - 50, 200);
	}
}
