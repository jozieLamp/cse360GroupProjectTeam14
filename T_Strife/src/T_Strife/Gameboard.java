package T_Strife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gameboard 
{
	public Gameboard()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		//Not sure what you wanted to do w/ this Ricky
		JFrame frame = new JFrame();
		frame.setSize(800, 400);
		
		JPanel grid = new JPanel(new GridBagLayout());
		JPanel panel = new JPanel();
		
		
		frame.add(panel);
		panel.setBounds(0, 800, 800, 0);
		frame.setVisible(true);
	}
	
	public static void init()
	{
		new Gameboard();
	}
	
	
	
}
