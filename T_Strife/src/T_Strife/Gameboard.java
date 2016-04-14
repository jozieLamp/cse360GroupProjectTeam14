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
//		JLabel rolled1 = new JLabel("You rolled a: " + dice.getDie1() + "\n Add this to your score!");
		JLabel rolled2;
		
//		if (dice.getDie2() == 1)
//		{
//			rolled2 = new JLabel("Number rolled: 1\nAction taken: Split");
//		}
//		if (dice.getDie2() == 2)
//		{
//			rolled2 = new JLabel("Number rolled: 2\nAction taken: Steal Points");
//		}
//		if (dice.getDie2() == 3)
//		{
//			rolled2 = new JLabel("Number rolled: 3\nAction taken: Multiply Points");
//		}
//		if (dice.getDie2() == 4)
//		{
//			rolled2 = new JLabel("Number rolled: 4\nAction taken: Lose Points Gained");
//		}
//		if (dice.getDie2() == 5)
//		{
//			rolled2 = new JLabel("Number rolled: 4\nAction taken: Tax");
//		}
		
//		panel.add(rolled1);
//		panel.add(rolled2);
		
		frame.add(panel);
		frame.setVisible(true);
		
		boolean winner = false;
		while (!winner)
		{
			rollDie(frame, 1);
			//rollMessage(frame, 1, );
			// 1st die roll happens. Then:
			rollDie(frame, 2);
			//rollMessage(frame, 2, dice.getDie2());
		}
	}
	
	/**
	 * rollDie - Used to display message for rolling the die; creates a popup.
	 * 
	 * @param frame Passes the frame through to display over it
	 * @param type	Selects which die is being rolled (1st or 2nd)
	 */
	public static void rollDie(JFrame frame, int type)
	{
		JOptionPane roll = new JOptionPane();
		if (type == 1)
			roll.showMessageDialog(frame, "It is Player X's turn. Roll your first die!", "First Die Roll", JOptionPane.INFORMATION_MESSAGE);
		if (type == 2)
			roll.showMessageDialog(frame, "Now, roll your second die!", "Second Die Roll", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void rollMessage(JFrame frame, int type, int roll)
	{
		JLabel display;
		if (type == 1)
		{
			display = new JLabel("You rolled a " + roll + "\n Add this to your score!" );
			display.setBounds(300, 250, 200, 100);
			frame.add(display);
		}
			
		if (type == 2)
		{
			if (roll == 1)
			{
				display = new JLabel("\nNumber rolled: 1" + 
									 "\nAction taken: Split Points!" +
									 "\n(Select another player and split your points with them)");
				display.setBounds(300, 250, 200, 100);
				frame.add(display);
			}
			if (roll == 2)
			{
				display = new JLabel("\nNumber rolled: 2" +
									 "\nAction taken: Steal Points!" + 
									 "\n(Select another player and take that many points from them)");
				display.setBounds(300, 250, 200, 100);
				frame.add(display);
			}
			if (roll == 3)
			{
				display = new JLabel("\nNumber rolled: 3" +
									 "\nAction taken: Multiply Points!" + 
									 "\n(Your point total is multiplied by 1.5)");
				display.setBounds(300, 250, 200, 100);
				frame.add(display);
			}
			if (roll == 4)
			{
				display = new JLabel("Number rolled: 4" +
									 "\nAction taken: Lose Points!" + 
									 "\n(You lose the points earned in the first roll. Sorry!)");
				display.setBounds(300, 250, 200, 100);
				frame.add(display);
			}
			if (roll == 5)
			{
				display = new JLabel("Number rolled: 4" +
									 "\nAction taken: Tax Points!" +
									 "\n(You tax every other player for 10% of their points!)");
				display.setBounds(300, 250, 200, 100);
				frame.add(display);
			}				
		}
		Wait();
	}
	
	public static void Wait()
	{
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void init()
	{
		new Gameboard();
	}
	
	
	
}
