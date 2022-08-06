package core;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

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
		furniture.add(new Furniture("Bed.png", 500, 500, 100, 100));
	}
}
