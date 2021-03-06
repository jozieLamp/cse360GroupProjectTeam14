package T_Strife;

import java.util.Scanner;
/**
 * conditionCheck - Checks what condition needs to be used, and includes the logic for each condition.
 *
 * @author - Brandon Nydam (PIN 618), Simon Kim
 */

public class conditionCheck 
{
	//static Scanner scan = new Scanner(System.in);
	
	/**
	 * Split action in which two player add all of their points together and split them evenly
	 * @param playerlist list of players in game currently
	 * @param currentPlayer, current player who is calling the split condition
	 */
	public static void split(GameData gamedat, int currentPlayer, String name)
	{
		int iterator = 0;
		boolean found = false;
		
		String playName = name;
		//SUBJECT TO CHANGE

		while(!found)
		{
			iterator = 0;
			//System.out.println("Enter the NAME");
			//playName = scan.next();

			while(iterator < gamedat.numPlayers && !found)
			{
				if(gamedat.allPlayers[iterator].getName().equals(playName))
				{
					found = true;
				}
				else
					iterator++;
			}
		
			if (found)
			{
				int playOne = gamedat.allPlayers[currentPlayer].getScore();
				int playTwo = gamedat.allPlayers[iterator].getScore();
			
				int average = ((playOne + playTwo) / 2);
			
				gamedat.allPlayers[currentPlayer].updateScore(average);
				gamedat.allPlayers[iterator].updateScore(average);
			}
			else // player not found
			{
				System.out.println("Player " + playName + " was not found");
			}
		//****
		}
		return;
	}
	
	
	/**
	 * Steal action in which a player steals the number of points rolled on their number dice from another player
	 * @param playerList list of players in the game
	 * @param currentPlayer currentplayer who rolled this action
	 * @param rollOne the number rolled on the dice
	 */
	public static Player[] steal(Player[] playerList, int currentPlayer, int rollOne, int numPlayers, String name)
	{
		int iterator = 0;
		boolean found = false;
		
		String playName = name;
		//SUBJECT TO CHANGE

		while(!found)
		{
			iterator = 0;
			//System.out.println("Enter the a NAME");
			
			while(iterator < numPlayers && !found)
			{
				if(playerList[iterator].getName().equals(playName))
				{
					found = true;
				}
				else
					iterator++;
			}
		
			if (found)
			{
				int playOne = playerList[currentPlayer].getScore();
				int playTwo = playerList[iterator].getScore();
			
				if(playTwo <= rollOne)
				{
					playerList[currentPlayer].updateScore(playTwo + playOne);
					playerList[iterator].updateScore(0);
				}
				else
				{
					playerList[currentPlayer].updateScore(rollOne + playOne);
					playerList[iterator].updateScore(playTwo - rollOne);
				}
			}
			else // player not found
			{
				System.out.println("Player " + playName + " was not found");
			}
		//****
		}
		return playerList;
	}
	
	
	/**
	 * Multiply action in which the player multiplies the number of points rolled by 1.5x and addes it to their score
	 * @param playerList the list of players playing the game
	 * @param currentPlayer the current player who rolled the action
	 */
	public static Player[] multiply(Player[] playerList, int currentPlayer)
	{
		int playOneScore = playerList[currentPlayer].getScore();
		
		playerList[currentPlayer].updateScore((int)(playOneScore * 1.5));
		
		return playerList;
	}

	
	/**
	 * Lose action in which the player loses the number of points rolled on the dice
	 * @param playerList the list of players
	 * @param currentPlayer the current player who rolled the action
	 * @param rollOne the number rolled on the number dice
	 */
	public static Player[] lose(Player[] playerList, int currentPlayer, int rollOne)
	{
		int playOneScore = playerList[currentPlayer].getScore();
		
		//don't lose a point when the player's score is already 0
		if((playerList[currentPlayer].getScore()) == 0)
		{
			playerList[currentPlayer].updateScore(0);
		}
		else
		{
			playerList[currentPlayer].updateScore(playOneScore - rollOne);
		}
		
		return playerList;
	}
	

	
	/**
	 * Tax action in which the player gains 10% of each persons number of points
	 * @param playerList the list of players
	 * @param currentPlayer the current player whos turn it is
	 * @param numPlayers the number of players playing the game
	 */
		public static Player[] tax(Player[] playerList, int currentPlayer, int numPlayers)
	{
		int taxOnCurrent = 0;
		int currentPlayerScore = 0;
		int totalTax = 0;
		
		for(int iterator = 0; iterator < numPlayers; iterator++)
		{
			if(iterator != currentPlayer)
			{	
				currentPlayerScore = playerList[iterator].getScore();
				if(currentPlayerScore == 0)
				{
					totalTax += 0;
				}
				else
				{
					taxOnCurrent = (int) Math.ceil((double)(currentPlayerScore * .10));
					playerList[iterator].updateScore(currentPlayerScore - taxOnCurrent);
					totalTax += taxOnCurrent;
				}
	
			}	
		}
		
		currentPlayerScore = playerList[currentPlayer].getScore();
		playerList[currentPlayer].updateScore(currentPlayerScore + totalTax);
		
		return playerList;
	}
	  
	
}

//String playName = scan.next();
