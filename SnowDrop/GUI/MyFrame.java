package GUI;

import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class MyFrame extends JFrame{
	
	private ImageIcon logo;
	
	public MyFrame(String frameTitle){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024,768);
		setLocationRelativeTo(null);
		setTitle(frameTitle);
		setLayout(null);
		logo = new ImageIcon("./Resources/logo.jpg");
		setIconImage(logo.getImage());
		//setResizable(false);
		setVisible(true);
	}	
	
}

	