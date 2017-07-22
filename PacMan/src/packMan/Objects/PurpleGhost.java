package packMan.Objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import packMan.Window.Game;
import packMan.Window.Handler;
import packMan.frameWorks.GameObject;
import packMan.frameWorks.ObjectId;
import packMan.frameWorks.Texture;

public class PurpleGhost extends GameObject {
	

	private Handler handler;
	private Texture tex = Game.getInstance();
	private static int afraidCounter = 0;

	public PurpleGhost(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
	
	}

	public void tick(LinkedList<GameObject> object) {

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player){
				
				float dx = tempObject.getX() - getX(), dy = tempObject.getY() - getY();
				float distance = (float)Math.sqrt(dx*dx + dy*dy);
				
				if(!Game.afraidGhosts){
					velX = dx * 2 / distance;
					velY = dy * 2 / distance;
				}
				else if(Game.afraidGhosts){
					velX = -dx * 3 / distance;
					velY = -dy * 3 / distance;
					afraidCounter++;
				}
				
				if(afraidCounter == 1500){
					afraidCounter = 0;
					Game.afraidGhosts = false;
				}
				
				
				x += velX;
				y += velY;
			}
		}
		CollisionPlayer(object);
	}
	private void CollisionPlayer(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Map){
				if(tempObject.getBounds().intersects(getBoundsBottom())){
				
					velY = 0;
					y = tempObject.getY() - 32;
				}
				if(tempObject.getBounds().intersects(getBoundsTop())){
					
				
					velY = 0;
					y = tempObject.getY() + 32;
				}
				if(tempObject.getBounds().intersects(getBoundsRight())){
					
				
					velX = 0;
					x = tempObject.getX() - 32;
				}
				if(tempObject.getBounds().intersects(getBoundsLeft())){
				
				
					velX = 0;
					x = tempObject.getX() + 32;
				}
			}		
		}
	}

	public void render(Graphics g) {
		
		g.drawImage(tex.PurpleGhost[0], (int) x, (int) y, 40 , 40 ,null);
		
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