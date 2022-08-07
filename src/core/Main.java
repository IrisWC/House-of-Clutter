package core;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame window = new JFrame("House of Clutter");
		
		ImageIcon img = new ImageIcon("img/Icon.png");
		window.setIconImage(img.getImage());
		window.setSize(1600, 900);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    
	    Game game = new Game();
	    
	    window.addMouseListener(game);
	    window.addMouseMotionListener(game);
	    window.add(game);
	    window.setVisible(true);
	    
	    game.run();
	}
	
	
	
}
