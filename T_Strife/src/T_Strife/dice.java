package T_Strife;

import java.util.*;
import com.sun.xml.internal.bind.v2.runtime.Name;

public class dice{
	
	
	//declaring variables
	public int die1;
	public int die2;
	
	//Default Constructor
	public dice()
	{
		NormalRoll(null);
		DisRoll(null);
	}
	
	
	void NormalRoll(NormalPlayer player)
	{	
		if(player.getType().equals("Normal"))
		{
			die1 = (int)(Math.random()*10 + 1);
			die2 = (int)(Math.random()*5 + 1);
		}
		
	}	
	
	void DisRoll(DisadvantagedPlayer player)
	{
		if(player.getType().equals("Disadvantaged"))
		{
			//chances needs to be modified later
			die1 = (int)(Math.random()*10 + 1);
			die2 = (int)(Math.random()*5 + 1);
		}
	}
	
	public int getDie1(Player player)
	{
		player.updateScore(player.getScore() + die1);
		
		return die1;
	}
	
	public int getDie2(Player mainPlayer, ArrayList<Player> players)
	{
		
		Scanner Scan = new Scanner(System.in);
		
		if(die2 == 1)
		{	
			
			
			System.out.println("You rolled Split!");
			System.out.println("Choose a player you wanna split points with: ");
			
			String playerName = Scan.next();
			
			
				for(Player player : players)
				{
					if(player.getName().equals(playerName))
						{
							System.out.println("Splitting Points with" + playerName);
							mainPlayer.updateScore((int) ((mainPlayer.getScore() + player.getScore())/2));
							player.updateScore((int) ((mainPlayer.getScore() + player.getScore())/2));
						
							break;
						}
						else if(player.getName() != playerName)
						{
							System.out.println("Name does not Exist try again");
						}
				}
		}
		
		if(die2 == 2)
		{
			System.out.println("You rolled Steal!");
			System.out.println("Choose a player to steal from: ");
			
			String playerName = Scan.next();
				
				for(Player player : players)
				{
					if(player.getName().equals(playerName))
						{
							System.out.println("Stealing points from" + playerName);
							player.updateScore((int) (mainPlayer.getScore() + player.getScore()));
							player.updateScore((int) 0);
							
							break;
						}
						else if(player.getName() != playerName)
						{
							System.out.println("Name does not Exist try again");
						}
				}
			}
		
		if(die2 == 3)
		{
			System.out.println("You rolled multiply!");
			mainPlayer.updateScore((int) (mainPlayer.getScore() *(1.5))) ;
		}
		
		if(die2 == 4)
		{
			System.out.println("You rolled Lose Points");
			mainPlayer.updateScore(mainPlayer.getScore() - die1);
		}
		
		if(die2 == 5)
		{
			System.out.println("You rolled Tax");
		}
		
		return 0;
	}
	
}