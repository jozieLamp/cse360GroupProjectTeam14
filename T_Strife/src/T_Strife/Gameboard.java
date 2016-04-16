package T_Strife;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Gameboard - Class that displays the actual game board. Displays the scoreboard.
 *
 * @author - Ellery Leung (Pin 507)
 */
public class Gameboard 
{
	public JFrame frame;
	public ArrayList<Player> playerList;
	public JPanel buttonPanel;
	public JPanel gamePanel;
	public JPanel scorePanel;
	public JButton buttonFirstRoll;
	public JButton buttonSecondRoll;
	public GameScoreboard scoreboard;


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

		buttonFirstRoll = new JButton("Roll Score Die");
		buttonSecondRoll = new JButton("Roll Condition Die");
		
		buttonFirstRoll.addActionListener(new ButtonListener());
		buttonSecondRoll.addActionListener(new ButtonListener());
		
		buttonPanel = new JPanel(new GridLayout(2, 1));
		buttonPanel.add(buttonFirstRoll);
		buttonPanel.add(buttonSecondRoll);
		
		gamePanel = new JPanel(new GridLayout(1, 2));
		
		gamePanel.add(buttonPanel);
		gamePanel.add(scorePanel);
		
		frame.add(gamePanel);
		frame.setVisible(true);
	
	}
	

	/**
	 * rollDie - Used to display message for rolling the die; creates a popup.
	 * 
	 * @param frame Passes the frame through to display over it
	 * @param type	Selects which die is being rolled (1st or 2nd)
	 */
	public void rollDie(int type, int currentPlayer, GameData game)
	{
		JLabel display;
		JOptionPane roll = new JOptionPane();
		
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
	public void rollSplit(GameData game)
	{ 
		JLabel display;
		JOptionPane roll = new JOptionPane();
		
		roll.showMessageDialog(new JFrame(), "\nNumber rolled: 1" + 
												"\nAction taken: Split Points!" +
												"\n(Select another player and split your points with them in the Console)", 
												"Roll Split", JOptionPane.INFORMATION_MESSAGE);
		
		
	//	JLabel display;
	//	display = new JLabel("\nNumber rolled: 1" + 
	//			 "\nAction taken: Split Points!" +
	//			 "\n(Select another player and split your points with them)");
	//	display.setBounds(300, 250, 200, 100);
	//	game.gameboard.frame.add(display);
	//	game.gameboard.frame.validate();
	//	Wait();
	//	game.gameboard.frame.remove(display);
	//	game.gameboard.frame.validate();
	}
	
	/**
	 * rollSteal - Displays message for rolling a steal
	 * 
	 * @param frame Passes the frame through to display over it
	 */
	public void rollSteal(GameData game)
	{
		JLabel display;
		JOptionPane roll = new JOptionPane();
		
		roll.showMessageDialog(new JFrame(), "\nNumber rolled: 2" +
				 							"\nAction taken: Steal Points!" + 
				 							"\n(Select another player and take that many points from them in the Console)", 
											"Roll Split", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * rollMult - Displays message for rolling a multiply
	 * 
	 * @param frame Passes the frame through to display over it
	 */
	public void rollMult(GameData game)
	{
		JLabel display;
		JOptionPane roll = new JOptionPane();
		
		roll.showMessageDialog(new JFrame(), "\nNumber rolled: 3" +
											"\nAction taken: Multiply Points!" + 
											"\n(Your point total is multiplied by 1.5)", 
											"Roll Split", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * rollLose - Displays message for rolling a lose
	 * 
	 * @param frame Passes the frame through to display over it
	 */
	public void rollLose(GameData game)
	{
		JLabel display;
		JOptionPane roll = new JOptionPane();
		
		roll.showMessageDialog(new JFrame(), "Number rolled: 4" +
											"\nAction taken: Lose Points!" + 
											"\n(You lose the points earned in the first roll. Sorry!)", 
											"Roll Split", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * rollTax - Displays message for rolling a tax
	 * 
	 * @param frame Passes the frame through to display over it
	 */
	public void rollTax(GameData game)
	{
		JLabel display;
		JOptionPane roll = new JOptionPane();
		
		roll.showMessageDialog(new JFrame(), "Number rolled: 4" +
				 							"\nAction taken: Tax Points!" +
				 							"\n(You tax every other player for 10% of their points!)", 
											"Roll Split", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * rollFirstDie - Displays a message for rolling the first die.
	 * 
	 * @param frame Passes the frame through to display over it
	 * @param roll Passes the value rolled through to display it
	 */
	public GameData rollFirstDie(GameData game)
	{
		Controller.toArrList(game);
		
		game.gameboard.scoreboard.updateScores(game.playerList);
		
		JLabel display;
			display = new JLabel("You rolled a " + game.dieOne + "\n Add this to your score!" );
			display.setBounds(300, 250, 200, 100);
			game.gameboard.frame.add(display);
		Wait();
		game.gameboard.frame.remove(display);
		
		return game;
	}
	
	/**
	 * Wait - Method used to make players wait a few seconds after action.
	 */
	public void Wait()
	{
		try
		{
			Thread.sleep(500);
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
		{/*
			Object action = event.getSource();
			if (action == buttonFirstRoll)
			{
				rollFirstDie(frame, game.gameDice.getDie1());
			}
			if (action == buttonSecondRoll)
			{
				if (game.gameDice.getDie2() == 1)
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
*/	} //end of ButtonListener
}}
	
	
