package T_Strife;

/**
 * Inherting from the Player class, this disadvantaged player has less winning opportunities than other players
 * @author Josephine Lamp
 */
public class DisadvantagedPlayer extends Player
{
	/** Variable type to denote what type of player this player is */
	private String type;
	
	/**
	 * Constructor with added type
	 * @param name of the player
	 */
	public DisadvantagedPlayer(String name)
	{
		super(name);
		type = "Disadvantaged";
	}
	
	/**
	 * Method to return the type of player
	 * @return type of player
	 */
	public String getType()
	{
		return type;
	}
}
