package T_Strife;

public class dice{
	
	
	//declaring variables
	private int die1;
	private int die2;
	
	
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
	
	
	public int getDie1()
	{
		return die1;
	}
	
	public int getDie2()
	{
		return die2;
	}
	
	
	
	
}
	
