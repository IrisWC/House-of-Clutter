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
	private Furniture holding;
	
	public Game() {
		super();
		
		furniture = new ArrayList<Furniture>();
		addFurniture();
		
		atStart = true; 
		inGame = false;
		atEnd = false;
		
		currentPage = "backgroundTB.png"; //FIX this for start of game later
		
		holding = null;
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
		
		for(int i = furniture.size() - 1; i >= 0; i--) {
			if(furniture.get(i).contains(xmouse, ymouse)) {
				furniture.get(i).remove();
				break;
			}
		}
		
		//System.out.println("mouse was clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int width = getWidth();
		int height = getHeight();
		
		Point mouse = getMousePosition();
		int xmouse = (int)(mouse.getX() * WIDTH / width);
		int ymouse = (int)(mouse.getY() * HEIGHT/height);
		
		for(int i = furniture.size() - 1; i >= 0; i--) {
			if(furniture.get(i).contains(xmouse, ymouse)) {
				holding = furniture.get(i);
				break;
			}
		}
		
		//System.out.println(holding.getName());
		//System.out.println("mouse was pressed");
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		int width = getWidth();
//		int height = getHeight();
//		
//		Point mouse = getMousePosition();
//		int xmouse = (int)(mouse.getX() * WIDTH / width);
//		int ymouse = (int)(mouse.getY() * HEIGHT/height);
		
		holding = null;
		//System.out.println("mouse was released");
		
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
		if(holding != null) {
			int width = getWidth();
			int height = getHeight();
			
			Point mouse = getMousePosition();
			int xmouse = (int)(mouse.getX() * WIDTH / width);
			int ymouse = (int)(mouse.getY() * HEIGHT/height);
			
			holding.moveWith(xmouse, ymouse);
		}
		
		//System.out.println("mouse was dragged");
	}
	
	private void addFurniture() {
		//end 1300 pixels
		//wall
//		furniture.add(new Furniture("Picture 3.png", 3, 10, 95, 95));
//		furniture.add(new Furniture("Picture 5.png", 18, 20, 28, 44));
		//bedside + stuff
		furniture.add(new Furniture("Bedside", 3, 235, 95, 95));
		furniture.add(new Furniture("Vase 3", 18, 212, 28, 44));
		furniture.add(new Furniture("Lamp", 50, 195, 45, 63));
		//bed + rug + stuff
		furniture.add(new Furniture("Rug", 17, 365, 393, 250));
		furniture.add(new Furniture("Bed", 92, 216, 228, 228));
		furniture.add(new Furniture("CushionOR", 150, 340, 47, 40));
//		furniture.add(new Furniture("Horse.png", 92, 216, 228, 228));
//		furniture.add(new Furniture("CushionO.png", 92, 216, 228, 228));
//		furniture.add(new Furniture("CushionR.png", 92, 216, 228, 228));
//		furniture.add(new Furniture("CUshionY.png", 92, 216, 228, 228));
//		furniture.add(new Furniture("Bed.png", 92, 216, 228, 228));
//		furniture.add(new Furniture("Bed.png", 92, 216, 228, 228));
//		furniture.add(new Furniture("Bed.png", 92, 216, 228, 228));
		//dresser + cushion
		furniture.add(new Furniture("Dresser", 314, 160, 119, 180));
		furniture.add(new Furniture("Dresser 3", 430, 120, 179, 221));
		//block + cushions
		furniture.add(new Furniture("Block", 607, 260, 64, 70));
		//shelf + 
		furniture.add(new Furniture("Bookshelf", 670, 75, 227, 272));
		//shelf + 
		furniture.add(new Furniture("Pottery Shelf", 895, 162, 100, 180));
		//cabinet + stuff
		furniture.add(new Furniture("Cabinet", 994, 250, 114, 89));
		//vanity + blue thing
		furniture.add(new Furniture("Vanity", 1108, 190, 100, 140));
		//table + flower
		furniture.add(new Furniture("Table", 1208, 250, 89, 83));
	}
	
	private boolean checkCompletion() {
		return false;
	}
}
