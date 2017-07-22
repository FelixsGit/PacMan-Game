package packMan.Objects;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import packMan.Window.Game;
import packMan.Window.Handler;
import packMan.frameWorks.GameObject;
import packMan.frameWorks.ObjectId;
import packMan.frameWorks.Texture;

public class YellowFood extends GameObject {
	


	private Texture tex = Game.getInstance();
	
	public YellowFood(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		
	
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		
		g.drawImage(tex.YellowFood[0], (int) x, (int) y, 32 ,32 ,null);
		
		/*
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		g2d.draw(getBounds());
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.setColor(Color.red);
		g2d.draw(getBoundsLeft());
		g2d.setColor(Color.GREEN);
		g2d.draw(getBoundsRight());
		*/
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);	
	}
	public Rectangle getBoundsTop(){
		return new Rectangle((int) x + 5, (int) y, 32 - 10, 10);
		
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle((int) x + 5, (int) y + 22, 32 - 10 , 10);
		
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle((int) x, (int) y + 5, 5, 32 - 10);	
		
	}
	public Rectangle getBoundsRight(){
		return new Rectangle((int) x + 27, (int) y + 5, 5, 32 - 10);	
	}
}
