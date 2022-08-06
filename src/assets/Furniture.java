package assets;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Furniture {
	
	private int x, y;
	private Image img;
	private boolean removed;
	
	public Furniture(String image, int xcord, int ycord) {
		img = new ImageIcon(image).getImage();
		x = xcord;
		y = ycord;
		removed = false;
	}
	
	public boolean checkRemoved() {
		return removed;
	}
	
	public void place() {
		removed = false;
	}
	
	public void remove() {
		removed = true;
	}
}
