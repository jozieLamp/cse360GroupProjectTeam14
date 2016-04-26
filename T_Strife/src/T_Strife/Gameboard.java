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
	
	private GameData game;
	private int type;
	private int currentPlayer;
	private boolean gameWon;

	/**
	 * Gameboard - constructor for the Gameboard class
	 *
	 * @players Takes in the array of players to display names.
	 */
	public Gameboard(Player[] players, GameData aGame)
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
	
		game = aGame;
		type = 0;
		game.dieOne = -1;
		game.dieTwo = -1;
		currentPlayer = 0;
		gameWon = false;
		buttonSecondRoll.setEnabled(false);
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
	public String rollSplit(GameData game)
	{ 
		JLabel display;
		String message = JOptionPane.showInputDialog("\nNumber rolled: 1" + 
													 "\nAction taken: Split Points!" +
													 "\n(Select another player and split your points with them in the Console)");
		JOptionPane roll = new JOptionPane();
		
		roll.showMessageDialog(new JFrame(), message, "Roll Split", JOptionPane.INFORMATION_MESSAGE);
		return message;
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
		
		game.gameboard.scoreboard.updateScores(game.playerList, currentPlayer);
		
		JLabel display;
			display = new JLabel("You rolled a " + game.dieOne + "\n Add this to your score!" );
			display.setBounds(300, 250, 200, 100);
			game.gameboard.frame.add(display);
		Wait();
		game.gameboard.frame.remove(display);
		
		return game;
	}
	
	/**
	 * winMessage - Displays a message for when a player has won the game
	 * 
	 * @param game - GameData structure that contains stored values for access
	 */
	public void winMessage(GameData game)
	{
		JLabel display;
		JOptionPane win = new JOptionPane();
		int winIndex = -1;
		int maximum = game.pointCap;
		
		for (int count = 0; count < game.numPlayers; count++)
		{
			if (game.allPlayers[count].getScore() >= maximum)
			{
				maximum = game.allPlayers[count].getScore();
				winIndex = count;
			}
		}
		
		DisadvantagedPlayer sadPlayer;
		for(int count = 0; count < game.numPlayers; count++)
		{
			if(game.allPlayers[count].getType() == "Disadvantaged")
			{
				sadPlayer = new DisadvantagedPlayer(game.allPlayers[count].getName());
			}
		}
		
		win.showMessageDialog(new JFrame(), "A winner is you!" +
											"\n" + game.allPlayers[winIndex].getName() + ", who was a " + game.allPlayers[winIndex].getType() +
											" type of player has won the game by having the most points!" +
											"\n" + game.allPlayers[winIndex].getName() + " had " + maximum + " points!" +
											"\n" + game.allPlayers[winIndex].getName() + " was the Disadvantaged Player!" +
											"\n\n" + "We live in a universe full of \"strife,\" in which, "
											+ "\n(despite our naive idealisms of an equal, fair and supportive society,)"
											+ "\n every action, reaction, and decision is \"tendentious\" and coolly calculated. "
											+ "\nThose who have an optimistic outlook on life eventually succumb to the crushing "
											+ "\npressure of the surrounding world's woes. "
											+ "\nMany games today contain too much joy and happiness. This game sets out to remind "
											+ "\neveryone of their worldly struggles and the sad, disheartening lives many lead. "
											+ "\nWe hope you enjoyed this down-to-earth, sunshine-filled game! "
											+ "\n-The Terrific T_Strife Team");
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
		{
			Object action = event.getSource();
			
			if (action == buttonFirstRoll)
			{
				buttonSecondRoll.setEnabled(true);
				buttonFirstRoll.setEnabled(false);
				game.gameDice.roll(game.allPlayers[currentPlayer]);
				
				game.dieOne = game.gameDice.getDie1();
				game.dieTwo = game.gameDice.getDie2();

				game.gameboard.rollDie(1, currentPlayer, game);				
				
				if(game.allPlayers[currentPlayer].getType().equals("Normal"))
					type = 0;
				else
					type = 1;

				//Debug
				System.out.println("You rolled a " + game.dieOne + " on the score dice");
				
				game.allPlayers[currentPlayer].updateScore(game.allPlayers[currentPlayer].getScore() + game.dieOne);
				game = game.gameboard.rollFirstDie(game);
			}
			if (action == buttonSecondRoll)
			{
				buttonSecondRoll.setEnabled(false);
				buttonFirstRoll.setEnabled(true);
				game.gameboard.rollDie(2, currentPlayer, game);
				//game.gameboard.waitForButtonPush(); // needs to implement buttons from GUI
				
				System.out.println("You rolled a " + game.dieTwo + " on the condition dice");
				
				game.gameboard.scoreboard.updateScoreBoard(playerList, currentPlayer);

				
				switch(game.dieTwo)
				{
				case 1: // Split

					String temp = game.gameboard.rollSplit(game);
					conditionCheck.split(game, currentPlayer, temp);
					Controller.toArrList(game);
					game.gameboard.scoreboard.updateScores(game.playerList, currentPlayer);
					break;
				
				case 2: // Steal

					String temp = game.gameboard.rollSteal(game);
					game.allPlayers = conditionCheck.steal(game.allPlayers, currentPlayer, game.dieOne, game.numPlayers, temp);
					Controller.toArrList(game);
					game.gameboard.scoreboard.updateScores(game.playerList, currentPlayer);
					break;
				
				case 3: // Multiply

					game.gameboard.rollMult(game);
					game.allPlayers = conditionCheck.multiply(game.allPlayers, currentPlayer);
					Controller.toArrList(game);
					game.gameboard.scoreboard.updateScores(game.playerList, currentPlayer);
					break;
				
				case 4: // Lose Points

					game.gameboard.rollLose(game);
					game.allPlayers = conditionCheck.lose(game.allPlayers, currentPlayer, game.dieOne);
					Controller.toArrList(game);
					game.gameboard.scoreboard.updateScores(game.playerList, currentPlayer);
					break;
				
				case 5: // Tax
					
				
					game.gameboard.rollTax(game);
					game.allPlayers = conditionCheck.tax(game.allPlayers, currentPlayer, game.numPlayers);
					Controller.toArrList(game);
					game.gameboard.scoreboard.updateScores(game.playerList, currentPlayer);
					break;
				
				default: // do Nothing
				}
				
				if(game.allPlayers[currentPlayer].getScore() >= game.pointCap)
					gameWon = true;
				
				currentPlayer++;
				
				if(currentPlayer == game.numPlayers)
				{
					currentPlayer = 0;
					game.turns++;
					if(gameWon)
					{
						buttonSecondRoll.setEnabled(false);
						buttonFirstRoll.setEnabled(false);
						
						game.gameboard.winMessage(game);
						game.gameboard.frame.setVisible(false);
						
						Leaderboard leaderboard = new Leaderboard("leaderboard.txt");
						leaderboard.addToLeaderboard(playerList, game.turns);
						
						new Main_menu();
					}
				}
				game.gameboard.scoreboard.updateScoreBoard(playerList, currentPlayer);
		}
	} //end of ButtonListener
}}
