package packMan.Objects;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import packMan.Window.Animation;
import packMan.Window.Game;
import packMan.Window.Handler;
import packMan.frameWorks.GameObject;
import packMan.frameWorks.ObjectId;
import packMan.frameWorks.Texture;



public class Ghost extends GameObject {
	

	private Handler handler;
	private Texture tex = Game.getInstance();
	public static int afraidCounter = 0;
	public static float ghostSpeed = (float) 2.5;
	
	private Animation GhostWalkRight;
	private Animation GhostWalkLeft;
	private Animation GhostWalkUp;
	private Animation GhostWalkDown;
	
	private Animation PurpleGhostWalkRight;
	private Animation PurpleGhostWalkLeft;
	private Animation PurpleGhostWalkUp;
	private Animation PurpleGhostWalkDown;
	

	public Ghost(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		GhostWalkRight = new Animation( 5, tex.Ghost[0], tex.Ghost[1]);
		GhostWalkLeft = new Animation(5, tex.Ghost[2], tex.Ghost[3]);
		GhostWalkUp = new Animation(5, tex.Ghost[4], tex.Ghost[5]);
		GhostWalkDown = new Animation(5, tex.Ghost[6], tex.Ghost[7]);
		
		PurpleGhostWalkRight = new Animation( 5, tex.PurpleGhost[0], tex.PurpleGhost[1]);
		PurpleGhostWalkLeft = new Animation(5, tex.PurpleGhost[2], tex.PurpleGhost[3]);
		PurpleGhostWalkUp = new Animation(5, tex.PurpleGhost[4], tex.PurpleGhost[5]);
		PurpleGhostWalkDown = new Animation(5, tex.PurpleGhost[6], tex.PurpleGhost[7]);
	
	}

	public void tick(LinkedList<GameObject> object) {
		
		GhostWalkRight.runAnimation();
		GhostWalkLeft.runAnimation();
		GhostWalkUp.runAnimation();
		GhostWalkDown.runAnimation();
		
		PurpleGhostWalkRight.runAnimation();
		PurpleGhostWalkLeft.runAnimation();
		PurpleGhostWalkUp.runAnimation();
		PurpleGhostWalkDown.runAnimation();
		

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player){
				
				float dx = tempObject.getX() - getX(), dy = tempObject.getY() - getY();
				float distance = (float)Math.sqrt(dx*dx + dy*dy);
				
				if(!Game.afraidGhosts){
					velX = (float) (dx * ghostSpeed / distance);
					velY = (float) (dy * ghostSpeed / distance);
				}
				else if(Game.afraidGhosts){
					velX = (float) (-dx * 2.5 / distance);
					velY = (float) (-dy * 2.5 / distance);
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
			if(tempObject.getId() == ObjectId.Ghost){
				if(tempObject.getBounds().intersects(getBounds())){
					// Ghost collide with Ghost
				}		
			}
		}
	}

	public void render(Graphics g) {
		if(!Game.afraidGhosts){
			if(velX > 0){
				GhostWalkRight.drawAnimations(g, (int) x, (int) y , 32, 32);
			}
			else if(velX < 0){
				GhostWalkLeft.drawAnimations(g, (int) x, (int) y, 32, 32);
			}
			else if(velY < 0){
				GhostWalkUp.drawAnimations(g, (int) x, (int) y, 32, 32);
			}
			else if(velY > 0){
				GhostWalkDown.drawAnimations(g, (int) x, (int) y, 32, 32);
			}
			else{
				GhostWalkRight.drawAnimations(g, (int) x, (int) y , 32, 32);
			}
		}
		else{
			if(velX > 0){
				PurpleGhostWalkRight.drawAnimations(g, (int) x, (int) y , 32, 32);
			}
			else if(velX < 0){
				PurpleGhostWalkLeft.drawAnimations(g, (int) x, (int) y, 32, 32);
			}
			else if(velY < 0){
				PurpleGhostWalkUp.drawAnimations(g, (int) x, (int) y, 32, 32);
			}
			else if(velY > 0){
				PurpleGhostWalkDown.drawAnimations(g, (int) x, (int) y, 32, 32);
			}
			else{
				PurpleGhostWalkRight.drawAnimations(g, (int) x, (int) y , 40, 40);
			}
		}
		
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
		return new Rectangle((int) x, (int) y, 32, 30);	
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
