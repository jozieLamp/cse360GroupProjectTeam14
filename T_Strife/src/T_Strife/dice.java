package T_Strife;

import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.Name;

public class dice{
	
	
	//declaring variables
	public int die1;
	public int die2;
	
	//Default Constructor
	public dice(){
	
		roll(null);
		
	}
	
	
	void roll(String type)
	{	
		if(type == "Normal")
		{
			die1 = (int)(Math.random()*10 + 1);
			die2 = (int)(Math.random()*5 + 1);
		}
		
		else if(type == "Disadvantaged")
		{
			die1 = (int)(Math.random()*10 + 1);
			die2 = (int)(Math.random()*6 + 1);
		}
	}	
	
	public int getDie1(Player player)
	{
		player.updateScore(player.getScore() + die1);
		
		return die1;
	}
	
	public int getDie2(Player player, int score)
	{
		
		Scanner Scan = new Scanner(System.in);
		
		if(die2 == 1)
		{
			System.out.println("You rolled Split!");
			System.out.println("Choose a player you wanna split points with: ");
			/*
			String Name = Scan.next();
			Player.score = (Name.score + Player.score) / 2;
 			Name.score = (Name.score + Player.score) / 2
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
	