package T_Strife;

class Controller {
		
		private int numPlayers = 0;
		private int pointCap = 0;
		private Player[] allPlayers = new Player[6];
		private dice gameDice = new dice();
		
		public void setNumPlayers(int totalPlayers)
		{	
			numPlayers = totalPlayers;
		}
		
		
		public void setPlayers(String[] playNames, int disAdvanIndex)
		{
			for(int i = 0; i<numPlayers; i++)
			{
				if(i != disAdvanIndex)
					allPlayers[i] = new NormalPlayer(playNames[i]);
				else
					allPlayers[i] = new DisadvantagedPlayer(playNames[i]);
			}
		}

		public void setWinPoints(int pointLimit)
		{
			pointCap = pointLimit;
		}
		
		
		public Player[] game()
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
				
				for(int i = 0; i < numPlayers; i ++) // iterates through players for each turn
				{
					gameDice.roll(allPlayers[i]);
					
					dieOne = gameDice.getDie1();
					dieTwo = gameDice.getDie2();
					
					if(allPlayers[i].getType().equals("Normal"))
						type = 0;
					else
						type = 1;
					
					// play animation, whatever we decide on an transition between rolls
					
					System.out.println("You rolled a " + dieOne + "on the score dice");
					
					allPlayers[i].updateScore(dieOne + allPlayers[i].getScore());
					// animation
					
					System.out.println("You rolled a " + dieTwo + "on the condition dice");
					
					
					switch(dieTwo)
					{
					case 1: // Split
						allPlayers = conditionCheck.split(allPlayers, i);
						break;
					
					case 2: // Steal
						allPlayers = conditionCheck.steal(allPlayers, i, dieOne, numPlayers);
						break;
					
					case 3: // Multiply
						allPlayers = conditionCheck.multiply(allPlayers, i);
						break;
					
					case 4: // Lose Points
						allPlayers = conditionCheck.lose(allPlayers, i, dieOne);
						break;
					
					case 5: // Tax
						allPlayers = conditionCheck.tax(allPlayers, i, numPlayers);
						break;
					
					default: // do Nothing
					}
					
					if(allPlayers[i].getScore() >= pointCap)
						gameWon = true;
				}
			}
			
			return allPlayers;
			
		}
		
}
