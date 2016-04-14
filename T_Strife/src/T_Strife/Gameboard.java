package T_Strife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Gameboard 
{
	public Gameboard(ArrayList<Player> players)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame();
		frame.setSize(800, 400);
		
		JPanel scorePanel = new JPanel(new GridLayout(players.size()+ 1, 3));
		Scoreboard scoreboard = new GameScoreboard(players, scorePanel);
		
		JPanel grid = new JPanel(new GridBagLayout());
		JPanel panel = new JPanel();

		JButton buttonFirstRoll = new JButton("Roll Score Die");
		JButton buttonSecondRoll = new JButton("Roll Condition Die");
		JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
		buttonPanel.add(buttonFirstRoll);
		buttonPanel.add(buttonSecondRoll);
		
		
		JPanel gamePanel = new JPanel(new GridLayout(1, 2));
		
		gamePanel.add(buttonPanel);
		gamePanel.add(scorePanel);
		
		frame.add(gamePanel);
		frame.setVisible(true);
		
		boolean winner = false;
//		while (!winner)
//		{
//			rollDie(frame, 1);
//			//rollMessage(frame, 1, );
//			// 1st die roll happens. Then:
//			rollDie(frame, 2);
//			//rollMessage(frame, 2, dice.getDie2());
//		}
	}
	
	/**
	 * rollDie - Used to display message for rolling the die; creates a popup.
	 * 
	 * @param frame Passes the frame through to display over it
	 * @param type	Selects which die is being rolled (1st or 2nd)
	 */
	public static void rollDie(JFrame frame, int type)
	{
		JLabel display;
		JOptionPane roll = new JOptionPane();
		if (type == 1)
			roll.showMessageDialog(frame, "It is Player X's turn. Roll your first die!", "First Die Roll", JOptionPane.INFORMATION_MESSAGE);
		if (type == 2)
			roll.showMessageDialog(frame, "Now, roll your second die!", "Second Die Roll", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * rollSplit - Displays message for rolling a split
	 * 
	 * @param frame Passes the frame through to display over it
	 */
	public static void rollSplit(JFrame frame)
	{ 
		JLabel display;
		display = new JLabel("\nNumber rolled: 1" + 
				 "\nAction taken: Split Points!" +
				 "\n(Select another player and split your points with them)");
		display.setBounds(300, 250, 200, 100);
		frame.add(display);
		Wait();
		frame.remove(display);
	}
	
	/**
	 * rollSteal - Displays message for rolling a steal
	 * 
	 * @param frame Passes the frame through to display over it
	 */
	public static void rollSteal()
	{
		JLabel display;
		display = new JLabel("\nNumber rolled: 2" +
				 "\nAction taken: Steal Points!" + 
				 "\n(Select another player and take that many points from them)");
		display.setBounds(300, 250, 200, 100);
		frame.add(display);
		Wait();
		frame.remove(display);
	}
	
	/**
	 * rollMult - Displays message for rolling a multiply
	 * 
	 * @param frame Passes the frame through to display over it
	 */
	public static void rollMult()
	{
		JLabel display;
		display = new JLabel("\nNumber rolled: 3" +
				 "\nAction taken: Multiply Points!" + 
				 "\n(Your point total is multiplied by 1.5)");
		display.setBounds(300, 250, 200, 100);
		frame.add(display);
		Wait();
		frame.remove(display);
	}
	
	/**
	 * rollLose - Displays message for rolling a lose
	 * 
	 * @param frame Passes the frame through to display over it
	 */
	public static void rollLose()
	{
		JLabel display;
		display = new JLabel("Number rolled: 4" +
				 "\nAction taken: Lose Points!" + 
				 "\n(You lose the points earned in the first roll. Sorry!)");
		display.setBounds(300, 250, 200, 100);
		frame.add(display);
		Wait();
		frame.remove(display);
	}
	
	/**
	 * rollTax - Displays message for rolling a tax
	 * 
	 * @param frame Passes the frame through to display over it
	 */
	public static void rollTax()
	{
		JLabel display;
		display = new JLabel("Number rolled: 4" +
				 "\nAction taken: Tax Points!" +
				 "\n(You tax every other player for 10% of their points!)");
		display.setBounds(300, 250, 200, 100);
		frame.add(display);
		Wait();
		frame.remove(display);
	}
	
	/**
	 * rollFirstDie - Displays a message for rolling the first die.
	 * 
	 * @param frame Passes the frame through to display over it
	 * @param roll Passes the value rolled through to display it
	 */
	public static void rollFirstDie(JFrame frame, int roll)
	{
		JLabel display;
			display = new JLabel("You rolled a " + roll + "\n Add this to your score!" );
			display.setBounds(300, 250, 200, 100);
			frame.add(display);
		Wait();
		frame.remove(display);
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
}
