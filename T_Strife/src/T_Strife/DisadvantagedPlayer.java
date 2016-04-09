package T_Strife;

public class DisadvantagedPlayer extends Player
{
	String type;
	
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
