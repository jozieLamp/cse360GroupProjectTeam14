package T_Strife;

/**
 * Dice class to create dice objects to use in game play
 */
public class dice
{
	/** Dice integer variables for the condition and number dice */
	private int die1;
	private int die2;
	private int chance1;
	private int chance2;
	
	/**
	 * Roll method to generate a random number for gameplay
	 * @param player the player who is rolling the dice
	 */
	void roll(Player player)
	{		
		
		if(player.getType().equals("Normal"))
		{
			die1 = (int)(Math.random()*10 + 1);
			die2 = (int)(Math.random()*5 + 1);
		}
		
		else if(player.getType().equals("Disadvantaged"))
		{
			
			die1 = (int)(Math.random()*10 + 1);
			die2 = (int)(Math.random()*5 + 1);
			chance1 = (int)(Math.random()*10 + 1);
			chance2 = (int)(Math.random()*10 + 1);
		
			//30% chance of having 1 points less than what they originally rolled
			if(chance1 == 1 || chance1 == 2 || chance1 == 3)
			{	
				die1 = die1 - 1;				
				
				if(die1 < 0)
				{
					die1 = 1;
				}
			}
			
			//30% chance of having 2 points less than what they originally rolled
			else if(chance1 == 4 || chance1 == 5 || chance1 == 6)
			{
				die1 = die1 - 2;
				
				if(die1 < 0)
				{
					die1 = 1;
				}
			}
			
			//20% chance of having 3 points less than what they originally rolled
			else if(chance1 == 7 || chance1 == 8)
			{
				die1 = die1 - 3;
				
				if(die1 < 0)
				{
					die1 = 1;
				}
			}
			
			//20% chance of dice not being affected negatively
			else
			{
				//do nothing 
			}
			
			}
			
			//60% chance of rolling lose
			if(chance2 == 1 || chance2 == 2 || chance2 == 3)
			{
				die2 = 4;
			}
			else
			{
				
			}
	}
	
	
	/**
	 * Returns the dice number
	 * @return the dice integer number for the integer dice
	 */
	public int getDie1()
	{
		return die1;
	}
	
	/**
	 * Returns the condition dice number
	 * @return the condition dice number for the condition dice
	 */
	public int getDie2()
	{
		return die2;
	}
}
	
