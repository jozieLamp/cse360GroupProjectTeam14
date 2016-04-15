package T_Strife;

/**
 *  Abstract Player class to create players for the dice game.
 *  @author Josephine Lamp (Pin 502)
 */
public abstract class Player 
{
	protected  String name;
	protected int score;
	protected int currentDie;
	protected int currentConditionDie;
	
	/**
	 * Constructor to create a player
	 * @param String name to take in the new players name
	 */
	public Player(String name)
	{
		this.name = name;
		score = 0;
		currentDie = -1;
		currentConditionDice = -1;
	}
	
	/** Method to return the type of a player */
	abstract String getType();
	
	/**
	 * Return the name of a player
	 * @return the name of a player
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Change the player name
	 * @param newName the new name to set it to
	 */
	public void setName(String newName)
	{
		name = newName;
	}
	
	/**
	 * Return the current player score
	 * @return the score the player has
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * Update the new score for the player
	 * @param newScore new score to update the player score
	 */
	public void updateScore(int newScore)
	{
		score = newScore;
	}
	
	/**
	 * Return the number dice currenlty rolled
	 * @return the currentDie number
	 */
	public int getCurrentDieRoll()
	{
		return currentDie;
	}
	
	/**
	 * Update the current number dice the player has rolled
	 * @param newCurrentDie the new dice roll number
	 */
	public void setCurrentDieRoll(int newCurrentDie)
	{
		currentDie = newCurrentDie;
	}
	
	/**
	 * Return the condition dice currenlty rolled
	 * @return the currentDie number condition
	 */
	public int getCurrentConditionDieRoll()
	{
		return currentConditionDie;
	}
	
	/**
	 * Update the current condition dice the player has rolled
	 * @param newCurrentDie the new dice roll condition number
	 */
	public void setCurrentConditionDieRoll(int newCurrentConditionDie)
	{
		currentConditionDie = newCurrentConditionDie;
	}
}