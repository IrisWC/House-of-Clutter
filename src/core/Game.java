package core;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import assets.*;

public class Game extends JPanel implements MouseListener, MouseMotionListener {
	
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
		
		if(SwingUtilities.isRightMouseButton(e)) {
		for(int i = furniture.size() - 1; i >= 0; i--) {
			if(!furniture.get(i).checkRemoved() && furniture.get(i).contains(xmouse, ymouse)) {
				furniture.get(i).remove();
				break;
			}
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
			if(!furniture.get(i).checkRemoved() && furniture.get(i).contains(xmouse, ymouse)) {
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
		if(holding != null && SwingUtilities.isLeftMouseButton(e)) {
			int width = getWidth();
			int height = getHeight();
			
			Point mouse = getMousePosition();
			
			if(mouse != null) {
				int xmouse = (int) (mouse.getX() * WIDTH / width);
				int ymouse = (int) (mouse.getY() * HEIGHT / height);

				holding.moveWith(xmouse, ymouse);
			}
		}
		
		//System.out.println("mouse was dragged");
	}
	
	private void addFurniture() {
		//end 1300 pixels
		//rug
		furniture.add(new Furniture("Rug", 8, 360, 393, 273));
		furniture.add(new Furniture("Rug 3", 640, 330, 295, 227));
		furniture.add(new Furniture("Rug 5", 1010, 573, 254, 192));
		furniture.add(new Furniture("Rug 6", 1045, 413, 250, 227));
		furniture.add(new Furniture("Rug 2", 1077, 558, 217, 167));
		furniture.add(new Furniture("Rug 4", 942, 463, 293, 179));
		//furniture
		furniture.add(new Furniture("Bookshelf", 670, 75, 227, 272));
		furniture.add(new Furniture("Bed", 92, 216, 228, 228));
		furniture.add(new Furniture("Dresser 3", 430, 120, 179, 221));
		furniture.add(new Furniture("Dresser", 314, 160, 119, 180));
		furniture.add(new Furniture("Bedside", 3, 235, 95, 95));
		furniture.add(new Furniture("Block", 607, 260, 64, 70));
		furniture.add(new Furniture("Pottery Shelf", 895, 162, 100, 180));
		furniture.add(new Furniture("Vanity", 1108, 190, 100, 140));
		furniture.add(new Furniture("Table 4", 512, 735, 226, 151));
		furniture.add(new Furniture("Table 2", 1232, 773, 64, 122));
		furniture.add(new Furniture("Cabinet", 994, 250, 114, 89));
		furniture.add(new Furniture("Table", 1208, 250, 89, 83));
		furniture.add(new Furniture("Table 3", 755, 400, 130, 108));
		furniture.add(new Furniture("Chair", 687, 414, 52, 88));
		furniture.add(new Furniture("Stool", 545, 326, 52, 63));
		furniture.add(new Furniture("Stool 2", 436, 776, 63, 69));
		furniture.add(new Furniture("Stool 2", 751, 776, 63, 69));
		furniture.add(new Furniture("Stool 3", 1132, 326, 57, 62));
		//barrel
		
		//empty container
		furniture.add(new Furniture("Bin", 723, 57, 51, 53));
		furniture.add(new Furniture("Trough", 1036, 316, 95, 50));
		//books
		furniture.add(new Furniture("Books Stool", 861, 278, 50, 95));
//		furniture.add(new Furniture("Books", 134, 41, 144, 139));
//		furniture.add(new Furniture("Books 2", 134, 41, 144, 139));
//		furniture.add(new Furniture("Books 3", 134, 41, 144, 139));
		//wall
		furniture.add(new Furniture("Window", 134, 41, 144, 139));
		furniture.add(new Furniture("Picture 3", 19, 22, 45, 62));
		furniture.add(new Furniture("Picture 5", 56, 101, 50, 67));
		furniture.add(new Furniture("Clock", 349, 59, 50, 50));
		furniture.add(new Furniture("Picture 3", 294, 112, 36, 48));
		furniture.add(new Furniture("Picture 4", 424, 5, 52, 70));
		furniture.add(new Furniture("Picture 5", 500, 55, 42, 56));
		furniture.add(new Furniture("Picture", 529, 8, 63, 42));
		furniture.add(new Furniture("Picture 2", 605, 49, 60, 69));
		//near bed stuff
		furniture.add(new Furniture("Vase 3", 18, 212, 28, 44));
		furniture.add(new Furniture("Lamp", 50, 195, 45, 63));
		furniture.add(new Furniture("HorseR", 40, 400, 91, 79));
		//dresser to book
		furniture.add(new Furniture("Chest", 393, 310, 81, 63));
		furniture.add(new Furniture("Plates", 728, 42, 40, 40));
		//near vanity stuff
		furniture.add(new Furniture("Chest", 909, 142, 65, 50));
		furniture.add(new Furniture("Blue Thing", 1151, 330, 34, 31));
		furniture.add(new Furniture("Ink", 1263, 226, 23, 39));
		furniture.add(new Furniture("Ink", 1272, 243, 23, 39));
		furniture.add(new Furniture("Vase 2", 1231, 238, 42, 50));
		furniture.add(new Furniture("Ink", 1265, 262, 23, 39));
		furniture.add(new Furniture("Ink", 1214, 257, 23, 39));
		furniture.add(new Furniture("Ink", 1227, 268, 23, 39));
		//block cushions
		//cushions
		furniture.add(new Furniture("CushionOR", 150, 340, 47, 40));
		//pottery stuff
		
		//cabinet stuff
		
		//vanity stuff
		
		//table stuff
		
	}
	
	private boolean checkCompletion() {
		return false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
