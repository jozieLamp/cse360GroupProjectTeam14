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
	
	public static void startNewGame()
	{
		
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
