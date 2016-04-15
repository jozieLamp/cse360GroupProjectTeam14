package T_Strife;

/**
 * Inherited class from the Player class, in which the Normal player has equal chances of winning the game
 * @author Josephine Lamp
 */
public class NormalPlayer extends Player
{
	/** String type to denote the type of player */
	private String type;
	
	/**
	 * Constructor to create normal player
	 * @param name of player to be added
	 */
	public NormalPlayer(String name)
	{
		super(name);
		type = "Normal";
	}
	
	/**
	 * Returns the type of player in the game
	 * @return type of player
	 */
	public String getType()
	{
		return type;
	}
	
}
