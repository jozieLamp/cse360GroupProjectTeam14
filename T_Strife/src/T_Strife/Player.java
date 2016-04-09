package T_Strife;


public abstract class Player 
{
	protected  String name;
	protected int score;
	
	public Player(String name)
	{
		this.name = name;
		score = 0;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String newName)
	{
		name = newName;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void updateScore(int newScore)
	{
		score = newScore;
	}
}
