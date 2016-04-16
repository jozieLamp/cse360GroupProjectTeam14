package T_Strife;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Controller - Class that controls the logic of the game. Uses GameScoreboard, GameData
 *
 * @author - Brandon Nydam (PIN 618)
 */
public class Controller {
	
	/**
	 * setNumPlayers - Takes the value of totalPlayers, and stores it in GameData.
	 *
	 * @param totalPlayers Total number of players
	 * @param game object of GameData that stores many values
	 */
	public static void setNumPlayers(int totalPlayers, GameData game)
	{	
		game.numPlayers = totalPlayers;
	}
	
	/**
	 * setPlayers - Sets the value for each player as to whether they are a Normal or Disadvantaged player.
	 *		Also instantiates each Player
	 *
	 *@param playNames - String array that has each player's name
	 *@param disAdvanIndex - Index of the disadvantaged player
	 *@param game - object of GameData that stores values
	 */
	public static void setPlayers(String[] playNames, int disAdvanIndex, GameData game)
	{
		for(int i = 0;i<game.numPlayers; i++)
		{
			if(i != disAdvanIndex)
				game.allPlayers[i] = new NormalPlayer(playNames[i]);
			else
				game.allPlayers[i] = new DisadvantagedPlayer(playNames[i]);
		}
	}

	/**
	 * setWinPoints - Method that sets the point limit (for victory).
	 *
	 *@param pointLimit - Integer value that is the point limit to win
	 *@param game - object of GameData that stores values
	 */
	public static void setWinPoints(int pointLimit, GameData game)
	{
		game.pointCap = pointLimit;
	}
	
	/**
	 * getPlayers - Returns the array of players
	 *
	 *@param game - object of GameData that is used to help return the array.
	 */
	public static Player[] getPlayers(GameData game)
	{
		return game.allPlayers;
	}
	
	/**
	 * getDieOne - Method that returns the value of the first die.
	 *
	 *@param game - object of GameData to return the first die value.
	 */
	public static int getDieOne(GameData game)
	{
		return game.dieOne;
	}

	/**
	 * getDieTwo - Method that returns the value of the second die.
	 *
	 *@param game - object of GameData to return the second die value.
	 */
	public static int getDieTwo(GameData game)
	{
		return game.dieTwo;
	}
	
	/**
	 * game - Controls the logic of the game
	 *
	 *@param game - Used for accessing data values in our game
	 */
	public static GameData game(GameData game)
	{
		boolean gameWon = false;
		Scanner scan = new Scanner(System.in);
		
		int type;
		
		game.turns = 0;
		
		while(!gameWon) // repeats until game is won
		{
			game.turns++;
			game.dieOne = -1;
			game.dieTwo = -1;
			type = -1; // type of player, 0 for Normal, 1 for Disadvantaged
			
			for(int currentPlayer = 0; currentPlayer < game.numPlayers; currentPlayer ++) // iterates through players for each turn
			{				
				game.gameDice.roll(game.allPlayers[currentPlayer]);
				
				game.dieOne = game.gameDice.getDie1();
				game.dieTwo = game.gameDice.getDie2();

				game.gameboard.rollDie(1, currentPlayer, game);				
				
				if(game.allPlayers[currentPlayer].getType().equals("Normal"))
					type = 0;
				else
					type = 1;
				
				// play animation, whatever we decide on an transition between rolls

				//Debug
				System.out.println("You rolled a " + game.dieOne + " on the score dice");
				
				game.allPlayers[currentPlayer].updateScore(game.allPlayers[currentPlayer].getScore() + game.dieOne);
				
				//game.gameboard.waitForButtonPush(); // needs to implement buttons from GUI
				game = game.gameboard.rollFirstDie(game);
				
				//game.gameboard.roll
				// animation
				game.gameboard.rollDie(2, currentPlayer, game);
				//game.gameboard.waitForButtonPush(); // needs to implement buttons from GUI
				
				System.out.println("You rolled a " + game.dieTwo + " on the condition dice");
				
				game.gameboard.scoreboard.updateScoreBoard();

				
				switch(game.dieTwo)
				{
				case 1: // Split

					game.gameboard.rollSplit(game);
					game.allPlayers = conditionCheck.split(game.allPlayers, currentPlayer);
					Controller.toArrList(game);
					game.gameboard.scoreboard.updateScores(game.playerList);
					break;
				
				case 2: // Steal

					game.gameboard.rollSteal(game);
					game.allPlayers = conditionCheck.steal(game.allPlayers, currentPlayer, game.dieOne, game.numPlayers);
					Controller.toArrList(game);
					game.gameboard.scoreboard.updateScores(game.playerList);
					break;
				
				case 3: // Multiply

					game.gameboard.rollMult(game);
					game.allPlayers = conditionCheck.multiply(game.allPlayers, currentPlayer);
					Controller.toArrList(game);
					game.gameboard.scoreboard.updateScores(game.playerList);
					break;
				
				case 4: // Lose Points

					game.gameboard.rollLose(game);
					game.allPlayers = conditionCheck.lose(game.allPlayers, currentPlayer, game.dieOne);
					Controller.toArrList(game);
					game.gameboard.scoreboard.updateScores(game.playerList);
					break;
				
				case 5: // Tax
					
				
					game.gameboard.rollTax(game);
					game.allPlayers = conditionCheck.tax(game.allPlayers, currentPlayer, game.numPlayers);
					Controller.toArrList(game);
					game.gameboard.scoreboard.updateScores(game.playerList);
					break;
				
				default: // do Nothing
				}
				
				if(game.allPlayers[currentPlayer].getScore() >= game.pointCap)
					gameWon = true;
				
				
			}
		}
		
		return game;
		
	}
	


	public static void toArrList(GameData game)
	{
		game.playerList = new ArrayList<Player>(Arrays.asList(game.allPlayers));
		for(int iterator = 0; iterator < game.playerList.size(); iterator++)
		{
			if(game.playerList.get(iterator) == null)
				{
					game.playerList.remove(iterator);
					iterator--;
				}
		}
	}
	
	
}

/**
 * GameData - Class that stores many values to be accessed through methods.
 */
class GameData
{
	ArrayList<Player> playerList;
	public int numPlayers = 0;
	public int pointCap = 0;
	public int dieOne = 0;
	public int dieTwo = 0;
	public Player[] allPlayers = new Player[6];
	public dice gameDice = new dice();
	public int turns = 0;
	public Gameboard gameboard;
	
}
