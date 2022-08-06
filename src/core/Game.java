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
		furniture.add(new Furniture("Bedside.png", 3, 235, 95, 95));
		furniture.add(new Furniture("Bed.png", 92, 216, 228, 228));
		furniture.add(new Furniture("Dresser.png", 314, 160, 119, 180));
		furniture.add(new Furniture("Dresser 3.png", 430, 120, 179, 221));
		furniture.add(new Furniture("Block.png", 607, 260, 64, 70));
		furniture.add(new Furniture("Bookshelf.png", 670, 75, 227, 272));
		furniture.add(new Furniture("Pottery Shelf.png", 895, 162, 100, 180));
		furniture.add(new Furniture("Cabinet.png", 994, 250, 114, 89));
		furniture.add(new Furniture("Vanity.png", 1108, 190, 100, 140));
		furniture.add(new Furniture("Table.png", 1208, 250, 89, 83));
	}
}
