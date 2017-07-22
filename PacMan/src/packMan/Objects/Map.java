package packMan.Objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import packMan.Window.Game;
import packMan.frameWorks.GameObject;
import packMan.frameWorks.ObjectId;
import packMan.frameWorks.Texture;


public class Map extends GameObject{


	Texture tex = Game.getInstance();
	private int type;

	
	public Map(float x, float y, int type , ObjectId id) {
		super(x, y, id);
		this.type = type;
		
	}
	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {

		if(type == 0){
			g.drawImage(tex.Map[0], (int) x, (int) y, null);
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) 32, (int) 32);
	}


}