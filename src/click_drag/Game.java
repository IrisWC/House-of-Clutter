package click_drag;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;

public class Game {

	public Game() {
	
	}

	public static void main(String[] args) 
	{
		JFrame w = new JFrame("Simple Window");
	    w.setBounds(100, 100, 640, 480);
	    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    GameBoard board = new GameBoard();
	    board.setBackground(Color.WHITE);
	   
	    Container c = w.getContentPane();
	    c.add(board);
	 
	    w.setResizable(true);
	    w.setVisible(true);
	  
	}
}
