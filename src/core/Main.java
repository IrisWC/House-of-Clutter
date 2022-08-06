package core;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame window = new JFrame();
		
//		ImageIcon img = new ImageIcon();
//		window.setIconImage(img.getImage());
		
		window.setSize(1600, 900);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    
	    window.setVisible(true);
	}
	
	
	
}
