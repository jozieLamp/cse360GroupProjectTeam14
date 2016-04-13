package T_Strife;

public class DisadvantagedPlayer extends Player
{
	private String type;
	
	public DisadvantagedPlayer(String name)
	{
		super(name);
		type = "Disadvantaged";
	}
	
	public String getType()
	{
		return type;
	}
}
