package assets;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Furniture {
	
	private int x, y, width, height;
	private Image img;
	private boolean removed;
	
	public Furniture(String image, int xcord, int ycord, int w, int h) {
		img = new ImageIcon("img/" + image).getImage();
		x = xcord;
		y = ycord;
		width = w;
		height = h;
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
	
	public void draw(Graphics g, ImageObserver io) {
		if (!removed)
			g.drawImage(img, x, y, width, height, io);
	}
}
