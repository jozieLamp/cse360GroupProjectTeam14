package T_Strife;

public class Controller {

	public static void setNumPlayers(int totalPlayers, GameData game)
	{	
		game.numPlayers = totalPlayers;
	}
	
	
	public static void setPlayers(String[] playNames, int disAdvanIndex, GameData game)
	{
		for(int i = 0; i<game.numPlayers; i++)
		{
			if(i != disAdvanIndex)
				game.allPlayers[i] = new NormalPlayer(playNames[i]);
			else
				game.allPlayers[i] = new DisadvantagedPlayer(playNames[i]);
		}
	}

	public static void setWinPoints(int pointLimit, GameData game)
	{
		game.pointCap = pointLimit;
	}
	
	
	public static Player[] game(GameData game)
	{
		boolean gameWon = false;
		
		int dieOne;
		int dieTwo;
		int type;
		
		
		while(!gameWon) // repeats until game is won
		{
			dieOne = -1;
			dieTwo = -1;
			type = -1; // type of player, 0 for Normal, 1 for Disadvantaged
			
			for(int i = 0; i < game.numPlayers; i ++) // iterates through players for each turn
			{
				game.gameDice.roll(game.allPlayers[i]);
				
				dieOne = game.gameDice.getDie1();
				dieTwo = game.gameDice.getDie2();
				
				if(game.allPlayers[i].getType().equals("Normal"))
					type = 0;
				else
					type = 1;
				
				// play animation, whatever we decide on an transition between rolls
				
				System.out.println("You rolled a " + dieOne + "on the score dice");
				
				// animation
				
				System.out.println("You rolled a " + dieTwo + "on the condition dice");
				
				
				switch(dieTwo)
				{
				case 1: // Split
					//conditionCheck.split(game.allPlayers, i);
					break;
				
				case 2: // Steal
					//conditionCheck.steal(game.allPlayers, i);
					break;
				
				case 3: // Multiply
					//conditionCheck.multiply(game.allPlayers, i);
					break;
				
				case 4: // Lose Points
					//conditionCheck.Lose(game.allPlayers, i);
					break;
				
				case 5: // Tax
					//conditionCheck.tax(game.allPlayers, i);
					break;
				
				default: // do Nothing
				}
				
				if(game.allPlayers[i].getScore() >= game.pointCap)
					gameWon = true;
			}
		}
		
		return game.allPlayers;
		
	}
	
}
}

class GameData
{
public int numPlayers = 0;
public int pointCap = 0;
public Player[] allPlayers = new Player[6];
public Dice gameDice = new Dice();

}

