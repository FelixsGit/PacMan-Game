package packMan.Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.LinkedList;

import packMan.Window.Animation;
import packMan.Window.BufferedImageLoader;
import packMan.Window.Game;
import packMan.Window.Handler;
import packMan.frameWorks.GameObject;
import packMan.frameWorks.ObjectId;
import packMan.frameWorks.Texture;





public class Player extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	public static int life = 300;
	public static int points = 0;
	public static int foodEaten = 0;
	public static int highScore;
	public static int ghostKilled = 0;
	public static int ghostDamage = 5;
	public static int finnalFoodEaten = 0;
	public static int finnalPoints = 0;
	public static int finnalGhostKilled = 0;
	
	File EatingYellowFoodSound = new File("res/EatingYellowFoodSound.wav");
	File EatingHealthCrossSound = new File("res/EatingHealthCrossSound.wav");
	File EatingGhostSound = new File("res/EatingGhostSound.wav");
	File GhostAttackSound = new File("res/GhostAttackSound.wav");
	File DeathSound = new File("res/DeathSound.wav");
	File EatingPurpleFoodSound = new File("res/EatingPurpleFoodSound.wav");
	File ClickSound = new File("res/ClickSound.wav");

	private File IngameMusic = new File("res/GameBackgroundMusic.wav");
	private File ScoreBoardMusic = new File("res/scoreBoardMusic.wav");
	private File WinnerMusic = new File("res/WinnerMusic.wav");
	
	private Animation playerWalkRight;
	private Animation playerWalkLeft;
	private Animation playerWalkUp;
	private Animation playerWalkDown;
	BufferedImageLoader loader = new BufferedImageLoader();
	
	
	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		playerWalkRight = new Animation( 5, tex.Player[0], tex.Player[1]);
		playerWalkLeft = new Animation(5, tex.Player[2], tex.Player[3]);
		playerWalkUp = new Animation(5, tex.Player[4], tex.Player[5]);
		playerWalkDown = new Animation(5, tex.Player[6], tex.Player[7]);
	}

	public void tick(LinkedList<GameObject> object) {
		
		x += velX;
		y += velY;
		
		playerWalkRight.runAnimation();
		playerWalkLeft.runAnimation();
		playerWalkUp.runAnimation();
		playerWalkDown.runAnimation();
		
		CollisionPlayer(object);
	//	if(points > highScore){
		//	highScore = points;
		//}
		
		
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
			if(tempObject.getId() == ObjectId.HealthCross){
				if(tempObject.getBounds().intersects(getBounds())){
					handler.removeObject(tempObject);
					Game.playSound(EatingHealthCrossSound);
					life += 30;
					if(life >= 300){
						life = 300;
					}
				}
			}
			if(tempObject.getId() == ObjectId.YellowFood){
				if(tempObject.getBounds().intersects(getBounds())){
					handler.removeObject(tempObject);
					points++;
					foodEaten++;
					//After eating 318 food, pacman wins.
					if(foodEaten == 318){
						if(points > highScore){
							highScore = points;
						}
						Game.state = Game.STATE.WINNER;
						Game.playMusic(IngameMusic, false);
						Game.playMusic(WinnerMusic, true);
						finnalFoodEaten = foodEaten;
					}
				}
			}
			if(tempObject.getId() == ObjectId.PurpleFood){
				if(tempObject.getBounds().intersects(getBounds())){
					handler.removeObject(tempObject);
					Game.playSound(EatingPurpleFoodSound);
					Game.afraidGhosts = true;
				}
			}
			if(tempObject.getId() == ObjectId.Ghost && Game.afraidGhosts == false){
				if(tempObject.getBounds().intersects(getBounds())){
					life -= ghostDamage;
					Game.playSound(GhostAttackSound);
	
					if(life <= 0){
						if(points > highScore){
							highScore = points;
						}
						life = 300;
						finnalGhostKilled = ghostKilled; 
						Game.doRestart = true;
						Game.playSound(DeathSound);
						Game.state = Game.STATE.SCOREBOARD;
						Game.playMusic(IngameMusic, false);
						Game.playMusic(ScoreBoardMusic, true);
					}
				}
			}
			if(tempObject.getId() == ObjectId.Ghost && Game.afraidGhosts == true){
				if(tempObject.getBounds().intersects(getBounds())){
					Game.playSound(EatingGhostSound);
					points += 20;
					ghostKilled++;
					handler.removeObject(tempObject);
				}
			}
		}	
	}

	public void render(Graphics g) {
		
		if(velX > 0){
			playerWalkRight.drawAnimations(g, (int) x, (int) y , 40, 40);
		}
		else if(velX < 0){
			playerWalkLeft.drawAnimations(g, (int) x, (int) y, 40, 40);
		}
		else if(velY < 0){
			playerWalkUp.drawAnimations(g, (int) x, (int) y, 40, 40);
		}
		else if(velY > 0){
			playerWalkDown.drawAnimations(g, (int) x, (int) y, 40, 40);
		}
		else{
			playerWalkRight.drawAnimations(g, (int) x, (int) y , 40, 40);
		}

		//Draw health bar
		Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 40);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(Game.WIDTH - 325, 50 , 300, 50);
		g.setColor(Color.GREEN);
		g.fillRect(Game.WIDTH -325, 50, life, 50);
		//Draw points
		g.setColor(Color.YELLOW);
		if(points > highScore){
			g.setColor(Color.GREEN);
		}
		g.setFont(fnt0);
		g.drawString("Points: " + points, 32, 100);
		g.setColor(Color.yellow);
		
		//Draw High Score
		g.setFont(fnt0);
		g.drawString("High Score: " + highScore, 300, 100);	
		
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
