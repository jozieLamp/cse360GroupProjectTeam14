package T_Strife;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Gameboard - Class that displays the actual game board. Displays the scoreboard.
 *
 * @author - Ellery Leung (Pin 507)
 */
public class Gameboard 
{
	JFrame frame;
	ArrayList<Player> playerList;
	JPanel buttonPanel;
	JPanel gamePanel;
	JPanel scorePanel;
	JButton buttonFirstRoll;
	JButton buttonSecondRoll;
	Scoreboard scoreboard;
	int dieOne;
	int dieTwo;
	
	/**
	 * Gameboard - constructor for the Gameboard class
	 *
	 * @players Takes in the array of players to display names.
	 */
	public Gameboard(Player[] players)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame();
		frame.setSize(800, 400);
		
		playerList = new ArrayList<Player>(Arrays.asList(players));
		for(int iterator = 0; iterator < playerList.size(); iterator++)
		{
			if(playerList.get(iterator) == null)
				{
					playerList.remove(iterator);
					iterator--;
				}
		}
		
		scorePanel = new JPanel(new GridLayout(playerList.size() + 1, 3));
		scoreboard = new GameScoreboard(playerList, scorePanel);
		
//		JPanel grid = new JPanel(new GridBagLayout());
//		JPanel panel = new JPanel();

		buttonFirstRoll = new JButton("Roll Score Die");
		buttonSecondRoll = new JButton("Roll Condition Die");
		
		buttonPanel = new JPanel(new GridLayout(2, 1));
		buttonPanel.add(buttonFirstRoll);
		buttonPanel.add(buttonSecondRoll);
		
		gamePanel = new JPanel(new GridLayout(1, 2));
		
		gamePanel.add(buttonPanel);
		gamePanel.add(scorePanel);
		
		frame.add(gamePanel);
		frame.setVisible(true);
		
//		boolean winner = false;
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
	public static void rollDie(int type, int currentPlayer, GameData game)
	{
		JLabel display;
		JOptionPane roll = new JOptionPane();
		
		game.dieOne;
		game.dieTwo;
		
		if (type == 1)
			roll.showMessageDialog(new JFrame(), "It is " + game.allPlayers[currentPlayer].getName() + "'s turn. Roll your first die!", "First Die Roll", JOptionPane.INFORMATION_MESSAGE);
		if (type == 2)
			roll.showMessageDialog(new JFrame(), "Now, roll your second die!", "Second Die Roll", JOptionPane.INFORMATION_MESSAGE);
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
	public static void rollSteal(JFrame frame)
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
	public static void rollMult(JFrame frame)
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
	public static void rollLose(JFrame frame)
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
	public static void rollTax(JFrame frame)
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
	
	/**
	 * Wait - Method used to make players wait a few seconds after action.
	 */
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
	
	private class ButtonListener implements ActionListener
	{
		/**
		 * ButtonListener - Takes an action based on the button pressed.
		 * @param event Checks whether the button pressed was for the first or second roll. 
		 */
		public void actionPerformed(ActionEvent event)
		{
			Object action = event.getSource();
			if (action == buttonFirstRoll)
			{
				rollFirstDie(frame, game.gameDice.getDie1());
			}
			if (action == buttonSecondRoll)
			{
				if (game.getDieTwo() == 1)
				{
					rollSplit(frame);
				}
				if (game.getDieTwo() == 2)
				{
					rollSteal(frame);
				}
				if (game.getDieTwo() == 3)
				{
					rollMult(frame);
				}
				if (game.getDieTwo() == 4)
				{
					rollLose(frame);
				}
				if (game.getDieTwo() == 5)
				{
					rollTax(frame);
				}
			}	
		}
	} //end of ButtonListener
}
