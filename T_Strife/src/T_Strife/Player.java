package T_Strife;

/**
 *  Abstract Player class to create players for the dice game.
 *  @author Josephine Lamp (Pin 502)
 */
public abstract class Player 
{
	protected  String name;
	protected int score;
	
	/**
	 * Constructor to create a player
	 * @param String name to take in the new players name
	 */
	public Player(String name)
	{
		this.name = name;
		score = 0;
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
}