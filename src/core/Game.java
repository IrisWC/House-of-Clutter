package core;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import assets.*;

public class Game extends JPanel implements MouseListener {
	
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	
	private ArrayList<Furniture> furniture;
	
	public Game() {
		super();
		
		furniture = new ArrayList<Furniture>();
		addFurniture();
	}
	
	public void run() {
		while(true) {
			repaint();
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);;
		
		int width = getWidth();
		int height = getHeight();
		
		double ratioX = (double)width/WIDTH;
	    double ratioY = (double)height/HEIGHT;
	    
	    Graphics2D g2 = (Graphics2D)g;
	    AffineTransform at = g2.getTransform();
	    g2.scale(ratioX,ratioY);
	    
	    g.drawImage(new ImageIcon("img/backgroundTB.png").getImage(), 0, 0, 1600, 900, this);
	    
	    for(Furniture f : furniture) {
	    	f.draw(g, this);
	    }
	    
	    g2.setTransform(at);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void addFurniture() {
		//end 1300 pixels
		//top line of furniture
		furniture.add(new Furniture("Bedside.png", 5, 210, 125, 125));
		furniture.add(new Furniture("Bed.png", 122, 216, 246, 246));
		furniture.add(new Furniture("Dresser.png", 320, 115, 167, 257));
		furniture.add(new Furniture("Dresser 3.png", 550, 110, 250, 310));
		furniture.add(new Furniture("Block.png", 500, 200, 83, 92));
		furniture.add(new Furniture("Bookshelf.png", 600, 500, 500, 600));
		furniture.add(new Furniture("Pottery Shelf.png", 750, 450, 250, 450));
		furniture.add(new Furniture("Cabinet.png", 800, 500, 320, 250));
		furniture.add(new Furniture("Vanity.png", 1000, 500, 250, 350));
		furniture.add(new Furniture("Table.png", 1200, 500, 380, 250));
	}
}
