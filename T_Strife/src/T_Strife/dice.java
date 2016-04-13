package T_Strife;

import java.util.Scanner;
import com.sun.xml.internal.bind.v2.runtime.Name;

public class dice{
	
	
	//declaring variables
	private int die1;
	private int die2;
	
	//Default Constructor
	public dice()
	{
		roll(null);
	}
	
	
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
	
	/*
	public int getDie2(Player player, ArrayList<Player> players)
	{
		
		Scanner Scan = new Scanner(System.in);
		
		if(die2 == 1)
		{
			System.out.println("You rolled Split!");
			System.out.println("Choose a player you wanna split points with: ");
			
			String name = Scan.next();
			
			//temporary logic for testing purposes. Pop-up needs to be coded later for this part
			while(0)
			{	
				
				for(Player player : players)
				{
					if(player.getName().equals(name))
						{
							System.out.println("Splitting Points with" + name)
							player.updateScore((int) ((player.getScore() + player.getName().getScore())/2));
							player.getName().updateScore((int) ((player.getScore() + player.getName().getScore())/2));
						
						break;
						}
						else if(player.getName() != name)
						{
							System.out.println("Name does not Exist try again")
						}
				}
			}
		
		if(die2 == 2)
		{
			System.out.println("You rolled Steal!");
			System.out.println("Choose a player to steal from: ");
			
			String name = Scan.next();
			
			//temporary logic for testing purposes. Pop-up needs to be coded later for this part
			while(0)
			{	
				
				for(Player player : players)
				{
					if(player.getName().equals(name))
						{
							System.out.println("Stealing points from" + name)
							player.updateScore((int) (player.getScore() + player.getName().getScore()));
							player.getName().updateScore((int) 0);
							
							break;
						}
						else if(player.getName() != name)
						{
							System.out.println("Name does not Exist try again")
						}
				}
			}
		
		}
		
		if(die2 == 3)
		{
			System.out.println("You rolled multiply!");
			player.updateScore((int) (player.getScore() *(1.5))) ;
		}
		
		if(die2 == 4)
		{
			System.out.println("You rolled Lose Points");
			player.updateScore(player.getScore() - die1);
		}
		
		if(die2 == 5)
		{
			System.out.println("You rolled Tax");
		}
		
		return 0;
	}
	
}*/
}
	