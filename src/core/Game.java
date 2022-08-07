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
	private ArrayList<Integer> trash;
	
	private boolean atStart, inGame;
	private String currentPage;
	private Rectangle startBtn, credBtn, infoBtn, ctrlBtn, backBtn, undoBtn, submitBtn;
	private Furniture holding;
	
	public Game() {
		super();
		
		furniture = new ArrayList<Furniture>();
		addFurniture();
		trash = new ArrayList<Integer>();
		
		atStart = false; 
		inGame = true;
		
		currentPage = "backgroundTB.png"; //FIX this for start of game later
		
		startBtn = new Rectangle(550, 479, 500, 125);
		infoBtn = new Rectangle(150, 685, 400, 125);
		credBtn = new Rectangle(600, 685, 400, 125);
		ctrlBtn = new Rectangle(1050, 685, 400, 125);
		
		backBtn = new Rectangle(90, 65, 75, 50);
		
		undoBtn = new Rectangle(1329, 755, 241, 50);
		submitBtn = new Rectangle(1329, 820, 241, 50);
		
		
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
	    
	    if(inGame) {
			for (Furniture f : furniture) {
				f.draw(g, this);
			}
			//g.drawImage(new ImageIcon("img/").getImage(), 1299, 0, 301, 900, this); //TODO
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
		
		if(SwingUtilities.isRightMouseButton(e) && inGame) {
			for (int i = furniture.size() - 1; i >= 0; i--) {
				if (!furniture.get(i).checkRemoved() && furniture.get(i).contains(xmouse, ymouse)) {
					furniture.get(i).remove();
					trash.add(i);
					break;
				}
			}
		} 
		else if (SwingUtilities.isLeftMouseButton(e)){
			if(atStart) {
				if(backBtn.contains(xmouse, ymouse)) {
					currentPage = "Menu.png";
				}
				else if(infoBtn.contains(xmouse, ymouse)) {
					currentPage = "Info.png";
				}
				else if(credBtn.contains(xmouse, ymouse)) {
					currentPage = "Credits.png";
				}
				else if(ctrlBtn.contains(xmouse, ymouse)) {
					currentPage = "Controls.png";
				}
				else if(startBtn.contains(xmouse, ymouse)) {
					currentPage = "backgroundTB.png";
					atStart = false;
					inGame = true;
				}
			}
			else if(inGame) {
				if (undoBtn.contains(xmouse, ymouse) && !trash.isEmpty()) {
					furniture.get(trash.get(trash.size() - 1)).place();
					trash.remove(trash.size() - 1);
				} 
				else if (submitBtn.contains(xmouse, ymouse)) {
					if(checkCompletion()) {
						currentPage = "End.png";
						inGame = false;
					} 
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
		//500, 500
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
		furniture.add(new Furniture("Desk", 501, 433, 63, 100));
		furniture.add(new Furniture("Cabinet", 994, 250, 114, 89));
		furniture.add(new Furniture("Table", 1208, 250, 89, 83));
		furniture.add(new Furniture("Table 3", 755, 400, 130, 108));
		//wall
		furniture.add(new Furniture("Window", 134, 41, 144, 139));
		furniture.add(new Furniture("Picture 3", 19, 22, 60, 62));
		furniture.add(new Furniture("Picture 5", 56, 101, 50, 68));
		furniture.add(new Furniture("Picture 2", 311, 15, 40, 40));
		furniture.add(new Furniture("Clock", 349, 59, 50, 50));
		furniture.add(new Furniture("Picture 3", 295, 103, 39, 55));
		furniture.add(new Furniture("Picture 3", 384, 111, 40, 40));
		furniture.add(new Furniture("Picture 4", 424, 5, 52, 71));
		furniture.add(new Furniture("Picture 5", 508, 49, 44, 59));
		furniture.add(new Furniture("Picture 2", 582, 11, 80, 74));
		furniture.add(new Furniture("Picture 2", 811, 14, 35, 45));
		furniture.add(new Furniture("Picture 3", 870, 43, 30, 30));
		furniture.add(new Furniture("Picture", 918, 20, 130, 92));
		furniture.add(new Furniture("Picture 3", 1029, 145, 45, 45));
		furniture.add(new Furniture("Picture 4", 1087, 80, 59, 81));
		furniture.add(new Furniture("Picture 2", 1064, 20, 40, 60));
		furniture.add(new Furniture("Picture 3", 1136, 1, 58, 75));
		furniture.add(new Furniture("Picture 3", 1165, 108, 60, 70));
		furniture.add(new Furniture("Picture 2", 1216, 7, 80, 80));
		furniture.add(new Furniture("Picture 3", 1251, 104, 40, 50));
		furniture.add(new Furniture("Picture 2", 1224, 175, 55, 55));
		//barrel
		furniture.add(new Furniture("Barrel", 85, 671, 78, 89));
		furniture.add(new Furniture("Barrel", 13, 705, 78, 89));
		furniture.add(new Furniture("Barrel", 116, 725, 78, 89));
		furniture.add(new Furniture("Barrel", 61, 764, 78, 89));
		furniture.add(new Furniture("Barrel", 171, 761, 78, 89));
		furniture.add(new Furniture("Barrel", 3, 811, 78, 89));
		furniture.add(new Furniture("Barrel", 117, 656, 78, 89));
		furniture.add(new Furniture("Barrel", 61, 697, 78, 89));
		furniture.add(new Furniture("Barrel", 180, 708, 63, 71));
		furniture.add(new Furniture("Barrel", 247, 753, 63, 71));
		furniture.add(new Furniture("Barrel", 83, 826, 63, 71));
		//vegetable + flower
		furniture.add(new Furniture("Flowers", 762, 683, 110, 69));
		furniture.add(new Furniture("Radishes 2", 1119, 743, 95, 64));
		furniture.add(new Furniture("Orange Stuff", 939, 696, 87, 67));
		furniture.add(new Furniture("Carrots", 990, 726, 87, 87));
		furniture.add(new Furniture("Radishes", 1057, 768, 95, 90));
		furniture.add(new Furniture("Radishes", 846, 689, 95, 90));
		furniture.add(new Furniture("Carrots", 900, 733, 87, 87));
		furniture.add(new Furniture("Orange Stuff", 823, 761, 87, 67));
		furniture.add(new Furniture("Radishes", 928, 761, 95, 90));
		furniture.add(new Furniture("Radishes 2", 856, 805, 95, 64));
		furniture.add(new Furniture("Carrots", 997, 799, 87, 87));
		furniture.add(new Furniture("Orange Stuff", 923, 833, 87, 67));
		furniture.add(new Furniture("Flowers", 1066, 830, 110, 69));
		//sit
		furniture.add(new Furniture("Chair", 687, 414, 52, 88));
		furniture.add(new Furniture("Stool", 536, 338, 52, 63));
		furniture.add(new Furniture("Stool 2", 436, 776, 63, 69));
		furniture.add(new Furniture("Stool 2", 751, 776, 63, 69));
		furniture.add(new Furniture("Stool 3", 1132, 326, 57, 62));
		//empty container
		furniture.add(new Furniture("Bin", 723, 57, 51, 53));
		furniture.add(new Furniture("Trough", 1036, 316, 95, 50));
		//books
		furniture.add(new Furniture("Books Stool", 861, 278, 50, 95));
		furniture.add(new Furniture("Books", 645, 302, 40, 64));
		furniture.add(new Furniture("Books 2", 689, 316, 42, 67));
		furniture.add(new Furniture("Books 3", 671, 284, 42, 58));
		furniture.add(new Furniture("Books 2", 740, 305, 42, 67));
		furniture.add(new Furniture("Books", 717, 273, 40, 64));
		furniture.add(new Furniture("Books 3", 773, 335, 42, 58));
		furniture.add(new Furniture("Books 3", 723, 344, 42, 58));
		furniture.add(new Furniture("Books", 667, 345, 40, 64));
		furniture.add(new Furniture("Books 2", 767, 289, 42, 67));
		furniture.add(new Furniture("Books", 704, 308, 40, 64));
		furniture.add(new Furniture("Books 2", 655, 311, 42, 67));
		furniture.add(new Furniture("Books 3", 702, 374, 42, 58));
		furniture.add(new Furniture("Books 3", 695, 245, 42, 58));
		furniture.add(new Furniture("Books", 918, 375, 40, 64));
		furniture.add(new Furniture("Books 2", 623, 361, 42, 67));
			//barrel book
		furniture.add(new Furniture("Books 2", 23, 770, 42, 67));
			//horse
		furniture.add(new Furniture("Horse", 802, 322, 64, 55));
		//cushions
		furniture.add(new Furniture("CushionOR", 150, 340, 47, 40));
		furniture.add(new Furniture("CushionYR", 333, 165, 56, 48));
		furniture.add(new Furniture("CushionRR", 543, 338, 47, 40));
			//block
		furniture.add(new Furniture("CushionO", 618, 241, 51, 44));
		furniture.add(new Furniture("CushionR", 607, 228, 51, 44));
		furniture.add(new Furniture("CushionY", 617, 216, 51, 44));
		furniture.add(new Furniture("CushionR", 617, 202, 51, 44));
		furniture.add(new Furniture("CushionO", 608, 186, 51, 44));
		furniture.add(new Furniture("CushionY", 621, 173, 51, 44));
		furniture.add(new Furniture("CushionO", 608, 160, 51, 44));
		furniture.add(new Furniture("CushionR", 613, 143, 51, 44));
		furniture.add(new Furniture("CushionY", 620, 129, 51, 44));
		furniture.add(new Furniture("CushionR", 611, 113, 51, 44));
			//bed rug
		furniture.add(new Furniture("CushionYR", 50, 499, 56, 48));
		furniture.add(new Furniture("CushionRR", 83, 484, 47, 40));
		furniture.add(new Furniture("CushionY", 121, 536, 51, 44));
		furniture.add(new Furniture("CushionR", 308, 486, 51, 44));
		furniture.add(new Furniture("CushionOR", 289, 516, 55, 55));
		furniture.add(new Furniture("CushionY", 231, 460, 50, 46));
		furniture.add(new Furniture("CushionYR", 274, 528, 40, 40));
		furniture.add(new Furniture("CushionRR", 260, 469, 43, 46));
		furniture.add(new Furniture("CushionR", 207, 505, 43, 46));
			//rug
		furniture.add(new Furniture("CushionO", 1137, 613, 50, 54));
		furniture.add(new Furniture("CushionY", 1211, 432, 51, 44));
		furniture.add(new Furniture("CushionO", 1223, 571, 51, 44));
		furniture.add(new Furniture("CushionOR", 1055, 505, 51, 44));
		furniture.add(new Furniture("CushionRR", 1243, 442, 51, 44));
		furniture.add(new Furniture("CushionY", 1085, 490, 51, 44));
		furniture.add(new Furniture("CushionYR", 1159, 634, 55, 55));
		furniture.add(new Furniture("CushionOR", 1196, 653, 40, 44));
		furniture.add(new Furniture("CushionO", 977, 612, 58, 48));
		furniture.add(new Furniture("CushionR", 1137, 658, 51, 44));
		
		//barrel stuff
		furniture.add(new Furniture("Block", 199, 832, 64, 70));
		furniture.add(new Furniture("Bin", 134, 808, 63, 67));
		furniture.add(new Furniture("Bin", 95, 799, 42, 44));
		furniture.add(new Furniture("Ball", 146, 800, 38, 38));
		furniture.add(new Furniture("Blue Thing", 218, 827, 34, 31));
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
		//reading table stuff
		furniture.add(new Furniture("Book", 796, 438, 58, 42));
		furniture.add(new Furniture("Candle", 812, 388, 42, 43));
		furniture.add(new Furniture("Ink", 769, 399, 23, 39));
		//pottery stuff
		furniture.add(new Furniture("Vase", 955, 310, 50, 55));
		furniture.add(new Furniture("Plates", 921, 334, 40, 40));
		furniture.add(new Furniture("Plates", 989, 350, 30, 30));
		furniture.add(new Furniture("TeaCup", 955, 355, 36, 39));
		furniture.add(new Furniture("TeaCup", 919, 314, 31, 34));
		furniture.add(new Furniture("Plates", 1004, 230, 40, 40));
		furniture.add(new Furniture("Plates", 997, 214, 40, 30));
		furniture.add(new Furniture("Vase", 1060, 218, 42, 46));
		furniture.add(new Furniture("TeaCup", 1048, 248, 31, 34));
		furniture.add(new Furniture("Vase", 1020, 245, 31, 40));
		furniture.add(new Furniture("TeaCup", 1000, 267, 25, 25));
		furniture.add(new Furniture("Vase", 1072, 265, 30, 25));
		furniture.add(new Furniture("TeaCup", 1044, 279, 20, 10));
		//food table stuff
		furniture.add(new Furniture("Vase 2", 602, 732, 45, 54));
		furniture.add(new Furniture("Fish", 616, 795, 63, 42));
		furniture.add(new Furniture("Cup", 531, 769, 46, 42));
		furniture.add(new Furniture("Cup", 677, 757, 46, 42));
		
	}
	
	private boolean checkCompletion() {
		return false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
