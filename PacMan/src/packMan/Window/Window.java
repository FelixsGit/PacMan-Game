package packMan.Window;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * This Class will create the game window
 */
public class Window {
	
	public Window(int w, int h, String title, Game game){
		game.setPreferredSize(new Dimension(w,h));
		game.setMaximumSize(new Dimension(w,h));
		game.setMinimumSize(new Dimension(w,h));
		
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setSize(w, h);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
}