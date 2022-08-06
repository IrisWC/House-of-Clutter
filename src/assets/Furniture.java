package assets;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Furniture {
	
	private int x, y, width, height;
	private Image img;
	private boolean removed;
	private Rectangle hitbox;
	private String name;
	
	public Furniture(String name, int xcord, int ycord, int w, int h) {
		img = new ImageIcon("img/" + name + ".png").getImage();
		this.name = name;
		x = xcord;
		y = ycord;
		width = w;
		height = h;
		removed = false;
		hitbox = new Rectangle(x, y, w, h);
	}
	
	public boolean checkRemoved() {
		return removed;
	}
	
	public void place() {
		removed = false;
	}
	
	public void remove() {
		removed = true;
		System.out.println(x + ", " + y);
	}
	
	public void moveWith(int xcord, int ycord) {
		x = xcord - (width / 2);
		y = ycord - (height / 2);
		hitbox = new Rectangle(x, y, width, height);
	}
	
	public boolean contains(int xcord, int ycord) {
		if(hitbox.contains(xcord, ycord))
			return true;
		return false;
	}
	
	public void draw(Graphics g, ImageObserver io) {
		if (!removed)
			g.drawImage(img, x, y, width, height, io);
	}
	
	public String getName() {
		return name;
	}
}
