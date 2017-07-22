package packMan.frameWorks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import packMan.Window.Game;
import packMan.Window.Handler;



public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private File IngameMusic = new File("res/GameBackgroundMusic.wav");
	private boolean activeLevelOne = false;
	private boolean activeLevelTwo = false;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player){
				if(key == KeyEvent.VK_UP){
					tempObject.setVelY(-5);
				}
				if(key == KeyEvent.VK_RIGHT){
					tempObject.setVelX(5);
				}
				if(key == KeyEvent.VK_LEFT){
					tempObject.setVelX(-5);
				}
				if(key == KeyEvent.VK_DOWN){
					tempObject.setVelY(5);
				}
				if(Game.state == Game.STATE.LEVELONE || Game.state == Game.STATE.LEVELTWO){
					if(Game.state == Game.STATE.LEVELONE){
						activeLevelOne = true;
					}
					if(Game.state == Game.STATE.LEVELTWO){
						activeLevelTwo = true;
					}
					
					if(key == KeyEvent.VK_ESCAPE){
						Game.state = Game.STATE.PAUSED;
						Game.playMusic(IngameMusic, false);
					}
				}
				if(Game.state == Game.STATE.PAUSED && activeLevelOne == true){
					if(key == KeyEvent.VK_ENTER){
						Game.state = Game.STATE.LEVELONE;
						Game.playMusic(IngameMusic, true);
						activeLevelOne = false;
					}
				}
				if(Game.state == Game.STATE.PAUSED && activeLevelTwo == true){
					if(key == KeyEvent.VK_ENTER){
						Game.state = Game.STATE.LEVELTWO;
						Game.playMusic(IngameMusic, true);
						activeLevelTwo = false;
					}
				}
			}
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player){
				if(key == KeyEvent.VK_UP){
					tempObject.setVelY(0);
				}
				if(key == KeyEvent.VK_RIGHT){
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_LEFT){
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_DOWN){
					tempObject.setVelY(0);
				}
				if(Game.state == Game.STATE.GAME){
					if(key == KeyEvent.VK_ESCAPE){
						Game.state = Game.STATE.PAUSED;
					}
				}
			}
		}		
	}
}