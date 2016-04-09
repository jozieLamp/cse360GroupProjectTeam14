package gameboard;

import javax.swing.*;
import java.awt.*;

public class gameboard extends JPanel
{
	public gameboard()
	{
		this.width = 800;
		this.height = 400;
		
		JPanel scoreboard = new JPanel(new GridLayout(6,2));
//TEMPORARY PLACEMENT OF VALUE ARRAY
		String[] names = {"Josie", "Ricky", "Brandon", "Simon", "Ellery", "Josie 2.0"};
		int[] scores = {5, 7, 3, 20, 80, 42};
		
		
	}
	
	public void init()
	{
		setup test = new setup();
		setSize(800, 400);
//		Container cont = getContentPane();
//		cont.add(test);
	} //end of init
} //end of gameboard
