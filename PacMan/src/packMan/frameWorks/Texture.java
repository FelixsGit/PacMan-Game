package packMan.frameWorks;

import java.awt.image.BufferedImage;

import packMan.Window.BufferedImageLoader;

public class Texture {
	
	private SpriteSheet ms,ps,hcs,yfs,gs,pfs,pgs;
	private BufferedImage Map_sheet = null;
	private BufferedImage Player_sheet = null;
	private BufferedImage HealthCross_sheet = null;
	private BufferedImage YellowFood_sheet = null;
	private BufferedImage Ghost_sheet = null;
	private BufferedImage PurpleFood_sheet = null;
	private BufferedImage PurpleGhost_sheet = null;


	
	public BufferedImage[] Map = new BufferedImage[1];
	public BufferedImage[] Player = new BufferedImage[8];
	public BufferedImage[] HealthCross = new BufferedImage[1];
	public BufferedImage[] YellowFood = new BufferedImage[1];
	public BufferedImage[] Ghost = new BufferedImage[8];
	public BufferedImage[] PurpleFood = new BufferedImage[1];
	public BufferedImage[] PurpleGhost = new BufferedImage[8];


	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
		Map_sheet = loader.loadImage("/Map_sheet.png");
		Player_sheet = loader.loadImage("/Player_sheet.png");
		HealthCross_sheet = loader.loadImage("/HealthCross_sheet.png");
		YellowFood_sheet = loader.loadImage("/YellowFood_sheet.png");
		Ghost_sheet = loader.loadImage("/Ghost_sheet.png");
		PurpleFood_sheet = loader.loadImage("/PurpleFood_sheet.png");
		PurpleGhost_sheet = loader.loadImage("/PurpleGhost_sheet.png");
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ms = new SpriteSheet(Map_sheet);
		ps = new SpriteSheet(Player_sheet);
		hcs = new SpriteSheet(HealthCross_sheet);
		yfs = new SpriteSheet(YellowFood_sheet);
		gs = new SpriteSheet(Ghost_sheet);
		pfs = new SpriteSheet(PurpleFood_sheet);
		pgs = new SpriteSheet(PurpleGhost_sheet);
		
		getTextures();
	}
	private void getTextures(){
		
		Map[0] = ms.grabImage(7, 1, 32, 32); //Map Textures
		
		Player[0] = ps.grabImage(11, 1, 32, 32); //Move Right closed
		Player[1] = ps.grabImage(12, 1, 32, 32); //Move Right opened
		
		Player[2] = ps.grabImage(11, 3, 32, 32); //Move Left closed
		Player[3] = ps.grabImage(12, 3, 32, 32); //Move Left opened
		
		Player[4] = ps.grabImage(11, 4, 32, 32); //Move Up closed
		Player[5] = ps.grabImage(12, 4, 32, 32); //Move Up opened
		
		Player[6] = ps.grabImage(11, 2, 32, 32); //Move Down closed
		Player[7] = ps.grabImage(12, 2, 32, 32); //Move Down closed
		
		
		HealthCross[0] = hcs.grabImage(1, 1, 32, 32); //HealthCross Textures
		YellowFood[0] = yfs.grabImage(1, 1, 32, 32); //YellowFood Textures
		
		Ghost[0] = gs.grabImage(1, 1, 32, 32); //Move Right closed
		Ghost[1] = gs.grabImage(2, 1, 32, 32); //Move Right opened
		
		Ghost[2] = gs.grabImage(1, 3, 32, 32); //Move Left closed
		Ghost[3] = gs.grabImage(2, 3, 32, 32); //Move Left opened
		
		Ghost[4] = gs.grabImage(1, 4, 32, 32); //Move Up closed
		Ghost[5] = gs.grabImage(2, 4, 32, 32); //Move Up opened
		
		Ghost[6] = gs.grabImage(1, 2, 32, 32); //Move Down closed
		Ghost[7] = gs.grabImage(2, 2, 32, 32); //Move Down closed
		
		
		PurpleFood[0] = pfs.grabImage(1, 1, 32, 32); //PurpleFood Textures
		
		
		PurpleGhost[0] = pgs.grabImage(9, 1, 32, 32); //Afraid Ghosts
		PurpleGhost[1] = pgs.grabImage(10, 1, 32, 32); //Afraid Ghosts
		
		PurpleGhost[2] = pgs.grabImage(9, 3, 32, 32); //Afraid Ghosts
		PurpleGhost[3] = pgs.grabImage(10, 3, 32, 32); //Afraid Ghosts
		
		PurpleGhost[4] = pgs.grabImage(9, 4, 32, 32); //Afraid Ghosts
		PurpleGhost[5] = pgs.grabImage(10, 4, 32, 32); //Afraid Ghosts
		
		PurpleGhost[6] = pgs.grabImage(9, 2, 32, 32); //Afraid Ghosts
		PurpleGhost[7] = pgs.grabImage(10, 2, 32, 32); //Afraid Ghosts
		

	}
}