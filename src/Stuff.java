import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Stuff {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame result = new JFrame();
		result.setSize(1600, 900);
		result.setLocation(0, 0);
		result.setLocationRelativeTo(null);
		result.setResizable(false);
		
		ImageIcon test = new ImageIcon("img/Blue.png"); 
		
		JLabel picLabel = new JLabel(test);
		picLabel.setLocation(0, 0);
		result.add(picLabel);
		
		result.setVisible(true);

	}
	
	
	
}
