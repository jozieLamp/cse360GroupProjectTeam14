package T_Strife;

import java.util.Scanner;

public class conditionCheck 
{
	static Scanner scan = new Scanner(System.in);
	
	public static Player[] split(Player[] playerList, int currentPlayer)
	{
		int iterator = 0;
		boolean found = false;
		
		//SUBJECT TO CHANGE
		System.out.println("Enter the a NAME");
		String playName = scan.next();
		while(!found)
		{
			while(iterator < playerList.length && !found)
			{
				if(playerList[iterator].equals(playName))
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
			
				int average = ((playOne + playTwo) / 2);
			
				playerList[currentPlayer].updateScore(average);
				playerList[iterator].updateScore(average);
			}
			else // player not found
			{
				System.out.println("Player " + playName + " was not found");
			}
		//****
		}
		return playerList;
	}
	
	
	
	public static Player[] steal(Player[] playerList, int currentPlayer, int rollOne, int numPlayers)
	{
		int iterator = 0;
		boolean found = false;
		
		//SUBJECT TO CHANGE
		System.out.println("Enter the a NAME");
		String playName = scan.next();
		while(!found)
		{
			while(iterator < numPlayers && !found)
			{
				if(playerList[iterator].equals(playName))
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
	
	
	
	public static Player[] multiply(Player[] playerList, int currentPlayer)
	{
		int playOneScore = playerList[currentPlayer].getScore();
		
		playerList[currentPlayer].updateScore((int)(playOneScore * 1.5));
		
		return playerList;
	}

	
	
	public static Player[] lose(Player[] playerList, int currentPlayer, int rollOne)
	{
		int playOneScore = playerList[currentPlayer].getScore();
		
		playerList[currentPlayer].updateScore(playOneScore - rollOne);
		
		return playerList;
	}
	
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
				taxOnCurrent = (int)(currentPlayerScore * .10);
				
				playerList[iterator].updateScore(currentPlayerScore - taxOnCurrent);
				totalTax += taxOnCurrent;
	
			}	
		}
		
		currentPlayerScore = playerList[currentPlayer].getScore();
		playerList[currentPlayer].updateScore(currentPlayerScore + totalTax);
		
		return playerList;
	}
	
	
}
