package click_drag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.event.*;

public class GameBoard extends JPanel implements MouseListener, MouseMotionListener
{
 
  private Rectangle gamePiece;
  private boolean clickedOnState = false;

  public GameBoard () 
  {
	  
	  addMouseListener(this);
	  addMouseMotionListener(this);
	  gamePiece = new Rectangle(0,0,50,50);
	  
  }

  public void paintComponent(Graphics g)
  {
      super.paintComponent(g);  
    
      if (clickedOnState)
      {
    	  	g.setColor(Color.BLACK);
      }
      else
      {
    		g.setColor(Color.RED);
      }
    
      g.fillRect(gamePiece.x, gamePiece.y, gamePiece.width, gamePiece.height);
  }
  
  public void mouseClicked(MouseEvent e)
  {
	  
  }
  
  public void mouseEntered(MouseEvent e)
  {
	  
  }
  
  public void mouseExited(MouseEvent e)
  {
	  
  }
  
  public void mousePressed(MouseEvent e) 
  {
	  int x,y;
	  
	  x = e.getX();
	  y = e.getY();
	
	  if (gamePiece.contains(x,y))
		  clickedOnState = true;
		  
	  repaint();
	  
  }
  
  public void mouseReleased(MouseEvent e)
  {
	  int x,y;
	  
	  x = e.getX();
	  y = e.getY();
	
	  if (gamePiece.contains(x,y))
		  clickedOnState = false;
		  
	  repaint();
	  
  }
  
  public void mouseDragged(MouseEvent e)
  {
	  int x,y;
	  
	  x = e.getX();
	  y = e.getY();
	  
	  if (clickedOnState)
	  {
	     gamePiece.x = x;
	     gamePiece.y = y;
	  }

	  repaint();
	    
  }
  
  public void mouseMoved(MouseEvent e)
  {
	  
  }
  
}

