package packMan.frameWorks;



import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import packMan.Objects.Ghost;
import packMan.Objects.Player;
import packMan.Window.Game;



public class MouseInput implements MouseListener{
	
	private File buttonClick = new File("res/ClickSound.wav");
	private File IngameMusic = new File("res/GameBackgroundMusic.wav");
	private File MenuMusic = new File("res/MenuMusic.wav");
	private File ScoreBoardMusic = new File("res/scoreBoardMusic.wav");
	private File WinnerMusic = new File("res/WinnerMusic.wav");
	static Texture tex;
	public static BufferedImage level = null;
	Graphics g;
	Game game;

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		
		/*
		g.setColor(Color.RED);
		g.drawRect(100, Game.HEIGHT/2 - 150, 100, 50);
		
		g.drawRect(100, Game.HEIGHT/2 - 75, 120, 50);
		
		g.drawRect(100, Game.HEIGHT/2 , 100, 50);
		
		g.drawRect(100, Game.HEIGHT/2 + 75, 100, 50);
		*/
		
		if(Game.state == Game.STATE.MAINMENU){
			int xm = e.getX();
			int ym = e.getY();		
			if((xm >= 100) && (xm <= 200)){
				if((ym >= Game.HEIGHT/2 - 150 ) && (ym <= Game.HEIGHT/2 - 100)){
					//Pressed playbutton
					Game.state = Game.STATE.LEVELONE;
					Game.playSound(buttonClick);
					Game.playMusic(MenuMusic, false);
					Game.playMusic(IngameMusic, true);
					Player.points = 0;
					Player.foodEaten = 0;
					Player.ghostKilled = 0;
					Game.afraidGhosts = false;
					Player.life = 300;
				}
			}
			if((xm >= 100) && (xm <= 220)){
				if((ym >= Game.HEIGHT/2 - 75) && (ym <= Game.HEIGHT/2 - 25)){
					Game.state = Game.STATE.SETTINGS;
					Game.playSound(buttonClick);
					//Pressed settingsButton
				}
			}
			if((xm >= 100) && (xm <= 200)){
				if((ym >= Game.HEIGHT/2 ) && (ym <= Game.HEIGHT/2 + 50)){
					Game.playSound(buttonClick);
					//Pressed helpButton
					Game.state = Game.STATE.MENUHELP;
				}
			}
			if((xm >= 100) && (xm <= 200)){
				if((ym >= Game.HEIGHT/2 + 75) && (ym <= Game.HEIGHT/2 + 125)){
					Game.playSound(buttonClick);
					//Pressed quitButton
					Game.state = Game.STATE.QUIT;
				}
			}
		}
		if(Game.state == Game.STATE.SETTINGS){
			int xm = e.getX();
			int ym = e.getY();		
			if((xm >= 100) && (xm <= 200)){
				if((ym >= Game.HEIGHT/2 - 60 ) && (ym <= Game.HEIGHT/2 - 10)){
					//Pressed EasyButton
					Game.playSound(buttonClick);
					Player.ghostDamage = 2;
					Ghost.ghostSpeed = 2;
					Game.difficulty = "Easy";
				}
			}
			if((xm >= 100) && (xm <= 200)){
				if((ym >= Game.HEIGHT/2 + 10) && (ym <= Game.HEIGHT/2 + 60)){
					//Pressed MediumButton
					Game.playSound(buttonClick);
					Player.ghostDamage = 5;
					Ghost.ghostSpeed = (float) 2.5;
					Game.difficulty = "Medium";
				}
			}
			if((xm >= 100) && (xm <= 200)){
				if((ym >= Game.HEIGHT/2 + 85) && (ym <= Game.HEIGHT/2 + 135)){
					//Pressed HardButton
					Game.playSound(buttonClick);
					Player.ghostDamage = 10;
					Ghost.ghostSpeed = (float) 3;
					Game.difficulty = "Hard";
				}
			}
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					//Pressed backButton
					Game.playSound(buttonClick);
					Game.state = Game.STATE.MAINMENU;
				}
			}
		}
		if(Game.state == Game.STATE.SCOREBOARD){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					Game.playSound(buttonClick);
					//Pressed MenuButton
					Game.state = Game.STATE.MAINMENU;
					Game.playMusic(ScoreBoardMusic, false);
					Game.playMusic(MenuMusic, true);
				}
			}
		}
		if(Game.state == Game.STATE.WINNER){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					Game.playSound(buttonClick);
					//Pressed MenuButton
					Game.state = Game.STATE.MAINMENU;
					Game.playMusic(WinnerMusic, false);
					Game.playMusic(MenuMusic, true);
					Game.doRestart = true;
				}
			}
		}
		if(Game.state == Game.STATE.PAUSED){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					Game.playSound(buttonClick);
					Game.playMusic(MenuMusic, true);
					Game.state = Game.STATE.MAINMENU;
					Game.doRestart = true;
				}
			}
		}
		if(Game.state == Game.STATE.MENUHELP){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					Game.playSound(buttonClick);
					//Pressed MenuButton
					Game.state = Game.STATE.MAINMENU;
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private void addKeyListener(KeyInput keyInput) {
		
	}

	public void mouseReleased(MouseEvent e) {

	}
}