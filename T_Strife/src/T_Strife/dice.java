package T_Strife;

/**
 * Dice class to create dice objects to use in game play
 */
public class dice
{
	/** Dice integer variables for the condition and number dice */
	//declaring variables
	private int die1;
	private int die2;
	
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
	
