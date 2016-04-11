package T_Strife;

import java.util.Scanner;
import com.sun.xml.internal.bind.v2.runtime.Name;

public class dice{
	
	
	//declaring variables
	public int die1;
	public int die2;
	
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
	
	public int getDie1(Player player)
	{
		player.updateScore(player.getScore() + die1);
		
		return die1;
	}
	
	public int getDie2(Player player)
	{
		
		Scanner Scan = new Scanner(System.in);
		
		if(die2 == 1)
		{
			System.out.println("You rolled Split!");
			System.out.println("Choose a player you wanna split points with: ");
			
			/*
			String name = Scan.next();
			
			//temp logic
			While(0)
			if(//named player equals(name))
			{
				System.out.println("Splitting Points with" + player)
				player.updateScore((int) ((player.getScore() + player.name)/2));
 			
 				break;
			}
			else if
			{
				System.out.println("Name does not Exist")
			}
			*/
		}
		
		if(die2 == 2)
		{
			System.out.println("You rolled Steal!");
			System.out.println("Choose a player to steal from: ");
			/*
			String name = Scan.next();
			*/
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
	
}
	