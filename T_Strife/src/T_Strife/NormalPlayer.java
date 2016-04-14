package T_Strife;

public class NormalPlayer extends Player
{
	private String type;
	
	public NormalPlayer(String name)
	{
		super(name);
		type = "Normal";
	}
	
	public String getType()
	{
		return type;
	}
	
}
