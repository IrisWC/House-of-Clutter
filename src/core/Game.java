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
	private boolean atStart, inGame, atEnd;
	private String currentPage;
	private Rectangle startBtn, despBtn, infoBtn, controlBtn, taskBtn, trashBtn, finishBtn;
	
	public Game() {
		super();
		
		furniture = new ArrayList<Furniture>();
		addFurniture();
		
		atStart = true; 
		inGame = false;
		atEnd = false;
		
		currentPage = "backgroundTB.png"; //FIX this for start of game later
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
	    
	    g.drawImage(new ImageIcon("img/" + currentPage).getImage(), 0, 0, 1600, 900, this);
	    
	    for(Furniture f : furniture) {
	    	f.draw(g, this);
	    }
	    
	    g2.setTransform(at);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int width = getWidth();
		int height = getHeight();
//		
//		System.out.println(width + " x " + height);
		
//		int x = e.getX();
//		int y = e.getY();
//		
//		System.out.println(x + ", " + y);
//		
//		Point mouse = getMousePosition();
//		System.out.println(mouse.getX() + ", " + mouse.getY());
		
//		int x = (int)(e.getX() * WIDTH / width);
//		int y = (int)(e.getY() * HEIGHT/height);
//		
//		System.out.println(x + ", " + y);
		
		Point mouse = getMousePosition();
		int xmouse = (int)(mouse.getX() * WIDTH / width);
		int ymouse = (int)(mouse.getY() * HEIGHT/height);
		
		//System.out.println(xmouse + ", " + ymouse);
		
		for(Furniture f : furniture) {
			if(f.contains(xmouse, ymouse))
				f.remove();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseDragged(MouseEvent e) {
		    
	}
	
	private void addFurniture() {
		//end 1300 pixels
		//wall
//		furniture.add(new Furniture("Picture 3.png", 3, 10, 95, 95));
//		furniture.add(new Furniture("Picture 5.png", 18, 20, 28, 44));
		//bedside + stuff
		furniture.add(new Furniture("Bedside.png", 3, 235, 95, 95));
		furniture.add(new Furniture("Vase 3.png", 18, 212, 28, 44));
		furniture.add(new Furniture("Lamp.png", 50, 195, 45, 63));
		//bed + rug + stuff
		furniture.add(new Furniture("Rug.png", 17, 365, 393, 250));
		furniture.add(new Furniture("Bed.png", 92, 216, 228, 228));
		furniture.add(new Furniture("CushionOR.png", 150, 340, 47, 40));
//		furniture.add(new Furniture("Horse.png", 92, 216, 228, 228));
//		furniture.add(new Furniture("CushionO.png", 92, 216, 228, 228));
//		furniture.add(new Furniture("CushionR.png", 92, 216, 228, 228));
//		furniture.add(new Furniture("CUshionY.png", 92, 216, 228, 228));
//		furniture.add(new Furniture("Bed.png", 92, 216, 228, 228));
//		furniture.add(new Furniture("Bed.png", 92, 216, 228, 228));
//		furniture.add(new Furniture("Bed.png", 92, 216, 228, 228));
		//dresser + cushion
		furniture.add(new Furniture("Dresser.png", 314, 160, 119, 180));
		furniture.add(new Furniture("Dresser 3.png", 430, 120, 179, 221));
		//block + cushions
		furniture.add(new Furniture("Block.png", 607, 260, 64, 70));
		//shelf + 
		furniture.add(new Furniture("Bookshelf.png", 670, 75, 227, 272));
		//shelf + 
		furniture.add(new Furniture("Pottery Shelf.png", 895, 162, 100, 180));
		//cabinet + stuff
		furniture.add(new Furniture("Cabinet.png", 994, 250, 114, 89));
		//vanity + blue thing
		furniture.add(new Furniture("Vanity.png", 1108, 190, 100, 140));
		//table + flower
		furniture.add(new Furniture("Table.png", 1208, 250, 89, 83));
	}
	
}
