package T_Strife;

public class Controller {

	public static void setNumPlayers(int totalPlayers, GameData game)
	{	
		game.numPlayers = totalPlayers;
	}
	
	
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

	public static void setWinPoints(int pointLimit, GameData game)
	{
		game.pointCap = pointLimit;
	}
	
	public static Player[] getPlayers(GameData game)
	{
		return game.allPlayers;
	}
	
	public static int getDieOne(GameData game)
	{
		return game.dieOne;
	}

	public static int getDieTwo(GameData game)
	{
		retunr game.dieTwo;
	}

	public static GameData game(GameData game)
	{
		boolean gameWon = false;
		
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

				Gameboard.rollDie(1, currentPlayer, game);				
	
				if(game.allPlayers[currentPlayer].getType().equals("Normal"))
					type = 0;
				else
					type = 1;
				
				// play animation, whatever we decide on an transition between rolls
				
				System.out.println("You rolled a " + game.dieOne + "on the score dice");
				
				// animation
				
				System.out.println("You rolled a " + game.dieTwo + "on the condition dice");
				
				
				switch(game.dieTwo)
				{
				case 1: // Split

					game.allPlayers = conditionCheck.split(game.allPlayers, currentPlayer);

					break;
				
				case 2: // Steal

					game.allPlayers = conditionCheck.steal(game.allPlayers, currentPlayer, game.dieOne, game.numPlayers);

					break;
				
				case 3: // Multiply

					game.allPlayers = conditionCheck.multiply(game.allPlayers, currentPlayer);

					break;
				
				case 4: // Lose Points

					game.allPlayers = conditionCheck.lose(game.allPlayers, currentPlayer, game.dieOne);
					
					break;
				
				case 5: // Tax
					
					game.allPlayers = conditionCheck.tax(game.allPlayers, currentPlayer, game.numPlayers);

					break;
				
				default: // do Nothing
				}
				
				if(game.allPlayers[currentPlayer].getScore() >= game.pointCap)
					gameWon = true;
			}
		}
		
		return game;
		
	}
	
}

class GameData
{
	public int numPlayers = 0;
	public int pointCap = 0;
	public int dieOne = 0;
	public int dieTwo = 0;
	public Player[] allPlayers = new Player[6];
	public dice gameDice = new dice();
	public int turns = 0;
}
