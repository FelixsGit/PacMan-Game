package packMan.Window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import packMan.Objects.Ghost;
import packMan.Objects.HealthCross;
import packMan.Objects.Map;
import packMan.Objects.Player;
import packMan.Objects.PurpleFood;
import packMan.Objects.YellowFood;
import packMan.frameWorks.KeyInput;
import packMan.frameWorks.MouseInput;
import packMan.frameWorks.ObjectId;
import packMan.frameWorks.Texture;






public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -3136661530675726978L;
	public static int WIDTH;
	public static int HEIGHT; 
	private boolean running = false;
	private Thread thread;
	private Handler handler;
	static Texture tex;
	public static BufferedImage level = null;
	@SuppressWarnings("unused")
	private BufferedImage backgroundGame = null;
	private BufferedImage backgroundMainMenu = null;
	public static boolean afraidGhosts = false;
	public static Clip MusicClip;
	
	private BufferedImageLoader loader = new BufferedImageLoader();
	public static boolean doRestart = false;
	public static String difficulty = "Medium";

	
	public static enum STATE{
		
		GAME,
		MAINMENU,
		PAUSED,
		LEVELONE,
		LEVELTWO,
		SCOREBOARD,
		MENUHELP,
		SETTINGS,
		QUIT,
		WINNER,
		
	}
		
	public static STATE state = STATE.MAINMENU;

	
	public void init() {
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		backgroundMainMenu = loader.loadImage("/backgroundMainMenu.png");
		backgroundGame = loader.loadImage("/backgroundGame.png");
		
		level = loader.loadImage("/levelOne.png"); //loading the level
		
		handler = new Handler();
		loadImageLevel(level);
		this.addKeyListener(new KeyInput(handler));	
		this.addMouseListener(new MouseInput());
	}

	public synchronized void start(){
		if (running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 45;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int updates = 0;
		//int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				//updates++;
				delta--;
			}
			render();
			//frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				//frames = 0;
				//updates = 0;
			}
		}
	}
	private void tick(){
		if(doRestart){
			Game.level = loader.loadImage("/levelOne.png");
			tex = new Texture();
			handler = new Handler();
			loadImageLevel(level);
			this.addKeyListener(new KeyInput(handler));	
			doRestart = false;

		}
		if(Game.state == Game.STATE.LEVELONE){
			handler.tick();
			if(Player.foodEaten == 87){
				Player.life = 300;
				Game.level = loader.loadImage("/levelTwo.png");
				Game.afraidGhosts = false;
				tex = new Texture();
				handler = new Handler();
				loadImageLevel(level);
				this.addKeyListener(new KeyInput(handler));	
				Game.state = Game.STATE.LEVELTWO;
			}
		}
		if(Game.state == Game.STATE.LEVELTWO){
			handler.tick();
			if(Player.foodEaten == 318){
				Game.level = loader.loadImage("/levelOne.png");
				tex = new Texture();
				handler = new Handler();
				loadImageLevel(level);
				this.addKeyListener(new KeyInput(handler));	
			}
		}
	}
	
	private void render(){

		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		//Draw here
		
		
		if(Game.state == Game.STATE.LEVELONE || Game.state == Game.STATE.LEVELTWO){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			Font fnt00 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt00);
			if(Game.difficulty.equals("Easy")){
				g.setColor(Color.GREEN);
			}
			if(Game.difficulty.equals("Medium")){
				g.setColor(Color.yellow);
			}
			if(Game.difficulty.equals("Hard")){
				g.setColor(Color.red);
			}
			g.drawString("Difficulty: " + Game.difficulty, 32, 50);
			//g.drawImage(backgroundGame, 0, 0, null);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(32, 150, Game.WIDTH - 64, Game.HEIGHT - 175);
			handler.render(g);

		}
		
		if(Game.state == Game.STATE.MENUHELP){
			g.drawImage(backgroundMainMenu, 0, 0, null);
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			Graphics2D g2d = (Graphics2D) g;
			g.setColor(Color.WHITE);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt1);
			g.drawString("Back", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 40);
			g.setFont(fnt2);
			g.setColor(Color.green);
			g.drawString("Controlls", 100, 350);
			g.setFont(fnt1);
			g.setColor(Color.white);
			g.drawString("Move up with UP", 100, 400);
			g.drawString("Move down with DOWN", 100, 450);
			g.drawString("Move right up with RIGHT", 100, 500);
			g.drawString("Move left with LEFT", 100, 550);
			Font fnt00 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt00);
			if(Game.difficulty.equals("Easy")){
				g.setColor(Color.GREEN);
			}
			if(Game.difficulty.equals("Medium")){
				g.setColor(Color.yellow);
			}
			if(Game.difficulty.equals("Hard")){
				g.setColor(Color.red);
			}
			g.drawString("Difficulty: " + Game.difficulty, 5, 20);
			g.setColor(Color.yellow);
			g.setFont(fnt1);
			g.drawString("Eating yellow fruit gives 1 point", 100 , 650);
			g.setColor(Color.MAGENTA);
			g.drawString("Eating purple fruits will make the ghosts eatable.",100 , 700);
			g.drawString("Ghost give 20 points each when eaten, and eating",100 , 730);
			g.drawString("them will remove them from the field ",100 , 760);
			
			
		}
		if(Game.state == Game.STATE.WINNER){
			Graphics2D g2d = (Graphics2D) g;
			Rectangle menuButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.drawImage(backgroundMainMenu,0 , 0, null);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 50);
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 30);
			g.setFont(fnt1);
			g.setColor(Color.white);
			g.setColor(Color.green);
			g.drawString("YOU WON!!!!", 100, 400);
			if(Player.points >= Player.highScore){
				g.drawString("You got a new highscore:  "+ Player.highScore, 100, 500);
			}
			else{
				Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 30);
				g.setFont(fnt3);
				g.drawString("Your highscore is "+ Player.highScore, 100, 450);
				g.setColor(Color.WHITE);
				g.drawString("Your Score Was: "+ Player.points, 100, 500);
			}
			g.setFont(fnt2);
		
			g.setFont(fnt2);
			g.setColor(Color.yellow);
			g.drawString("You ate a total of "+Player.finnalFoodEaten+" yellow fruit", 100 , 600);
			g.setColor(Color.magenta);
			g.drawString("You killed a total of "+Player.ghostKilled+" ghosts", 100, 650);
			g.setColor(Color.WHITE);
			
			Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt3);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(menuButton);
		}
		
		if(Game.state == Game.STATE.SCOREBOARD){
			Graphics2D g2d = (Graphics2D) g;
			Rectangle menuButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.drawImage(backgroundMainMenu,0 , 0, null);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 40);
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 30);
			g.setFont(fnt1);
			g.setColor(Color.white);
			if(Player.points >= Player.highScore){
				g.setColor(Color.green);
				g.drawString("You got a new highscore:  "+ Player.highScore, 100, 400);
			}
			else{
				g.setFont(fnt2);
				g.setColor(Color.green);
				g.drawString("Your highscore is "+ Player.highScore, 100, 400);
				g.setColor(Color.WHITE);
				g.drawString("Your Score Was: "+ Player.points, 100, 450);
	
			}
			Font fnt4 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 50);
			g.setFont(fnt4);
			g.setColor(Color.RED);
			g.drawString("You died!", 100, 350);
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.setColor(Color.yellow);
			g.drawString("You ate a total of "+Player.foodEaten +" yellow fruit", 100 , 550);
			g.setColor(Color.MAGENTA);
			g.drawString("You ate a total of "+Player.ghostKilled +" ghosts", 100, 600);
			g.setColor(Color.WHITE);
			Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt3);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(menuButton);
			
		}
		
		if(Game.state == STATE.MAINMENU){
			g.drawImage(backgroundMainMenu, 0, 0, null);
			Rectangle playButton = new Rectangle(100, Game.HEIGHT/2 - 150 , 100, 50);
			Rectangle settingsButton = new Rectangle(100, Game.HEIGHT/2 + 0, 100, 50);
			Rectangle helpButton = new Rectangle(100, Game.HEIGHT/2  - 75, 120, 50);
			Rectangle quitButton = new Rectangle(100, Game.HEIGHT/2 + 75, 100, 50);
			Graphics2D g2d = (Graphics2D) g;
			Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 50);
			g.setFont(fnt0);
			g.setColor(Color.black);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 30);
			g.setFont(fnt1);
			g.setColor(Color.yellow);
			g.drawString("Play", playButton.x + 10  , playButton.y + 35);
			g.drawString("Settings", playButton.x + 5  , playButton.y + 110);
			g.drawString("Help", playButton.x + 10  , playButton.y + 185);
			g.drawString("Quit", playButton.x   , playButton.y + 260);
			g.setColor(Color.DARK_GRAY);
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt2);
			g.setFont(fnt1);
			g.setColor(Color.WHITE);
			g2d.draw(playButton);
			g2d.draw(settingsButton);
			g2d.draw(helpButton);
			g2d.draw(quitButton);
			g.setColor(Color.DARK_GRAY);
			Font fnt00 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt00);
			if(Game.difficulty.equals("Easy")){
				g.setColor(Color.GREEN);
			}
			if(Game.difficulty.equals("Medium")){
				g.setColor(Color.yellow);
			}
			if(Game.difficulty.equals("Hard")){
				g.setColor(Color.red);
			}
			g.drawString("Difficulty: " + Game.difficulty, 5, 20);
			g.setColor(Color.white);
			g.drawString("By Felix Toppar 26/06/2017", 5 , Game.HEIGHT - 10);
		}
		if(Game.state == STATE.MENUHELP){
			Font fnt00 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt00);
			if(Game.difficulty.equals("Easy")){
				g.setColor(Color.GREEN);
			}
			if(Game.difficulty.equals("Medium")){
				g.setColor(Color.yellow);
			}
			if(Game.difficulty.equals("Hard")){
				g.setColor(Color.red);
			}
			g.drawString("Difficulty: " + Game.difficulty, 5, 20);

		}
		if(Game.state == STATE.SETTINGS){
			g.drawImage(backgroundMainMenu, 0, 0, null);
			Rectangle HardButton = new Rectangle(100, Game.HEIGHT/2 - 60 , 100, 50);
			Rectangle MedimButton = new Rectangle(100, Game.HEIGHT/2 + 10, 100, 50);
			Rectangle EasyButton = new Rectangle(100, Game.HEIGHT/2  +85, 100, 50);
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			Graphics2D g2d = (Graphics2D) g;
			Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 25);
			g.setFont(fnt0);
			g.setColor(Color.green);
			g.drawString("Easy", HardButton.x + 10  , HardButton.y + 35);
			g.setColor(Color.yellow);
			g.drawString("Medium", HardButton.x +10  , HardButton.y + 110);
			g.setColor(Color.red);
			g.drawString("Hard", HardButton.x + 10  , HardButton.y + 185);
			g.setColor(Color.WHITE);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(HardButton);
			g2d.draw(MedimButton);
			g2d.draw(EasyButton);
			g2d.draw(backButton);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 40);
			g.setFont(fnt1);
			g.setColor(Color.WHITE);
			g.drawString("Choose Difficulty", 100, 350);
			Font fnt00 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt00);
			if(Game.difficulty.equals("Easy")){
				g.setColor(Color.GREEN);
			}
			if(Game.difficulty.equals("Medium")){
				g.setColor(Color.yellow);
			}
			if(Game.difficulty.equals("Hard")){
				g.setColor(Color.red);
			}
			g.drawString("Difficulty: " + Game.difficulty, 5, 20);
		}
		
		if(Game.state == STATE.QUIT){
			System.exit(1);
		}
		if(Game.state == STATE.PAUSED){
			
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 50);
			g.setColor(Color.RED);
			g.setFont(fnt0);
			g.drawString("Paused", Game.WIDTH/2 - 85 , Game.HEIGHT/2 + 50);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt1);
			g.drawString("Press Enter To Continue...", Game.WIDTH/2 - 100 ,Game.HEIGHT/2 + 70);
			Graphics2D g2d = (Graphics2D) g;
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt2);
			g.setColor(Color.black);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);	
			Rectangle menuButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			g.setColor(Color.red);
			Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt3);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(menuButton);

		}
	
		
		//Stop Drawing here
		g.dispose();
		bs.show();
	}
	public void loadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green =(pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 0 && blue == 0){
					handler.addObject(new Map(xx*32, yy*32, 0, ObjectId.Map));
				}
				if(red == 0 && green == 255 && blue == 255){
					handler.addObject(new Player(xx*32, yy*32, handler, ObjectId.Player));
				}
				if(red == 255 && green == 0 && blue == 0){
					handler.addObject(new HealthCross(xx*32, yy*32, handler, ObjectId.HealthCross));
				}
				if(red == 255 && green == 216 && blue == 0){
					handler.addObject(new YellowFood(xx*32, yy*32, handler, ObjectId.YellowFood));
				}
				if(red == 64 && green == 64 && blue == 64){
					handler.addObject(new Ghost(xx*32, yy*32, handler, ObjectId.Ghost));
				}
				if(red == 72 && green == 0 && blue == 255){
					handler.addObject(new PurpleFood(xx*32, yy*32, handler, ObjectId.PurpleFood));
				}
			}
		}
	}
	public static Texture getInstance(){
		return tex;
	}
public static void main(String[] args){
		
		File MenuMusic = new File("res/MenuMusic.wav");
		new Window(1280, 960, "PackMan", new Game());
		playMusic(MenuMusic, true);
		
	}
	public static void playSound(File Sound){
		try{
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			
		}catch(Exception e){
			
		}
	}
	public static void playMusic(File Sound, boolean run){
		if(run){
			try{
	
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
				MusicClip = AudioSystem.getClip();
				MusicClip.open(audioInputStream);
				MusicClip.start();
			
			}catch(Exception e){
				
			}
		}
		else
			MusicClip.stop();
	}
}