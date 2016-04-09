package T_Strife;
import java.util.ArrayList;

public class GameScoreboard extends Scoreboard
{
	int numPlayers;
	ArrayList<Player> players;
	
	public GameScoreboard(int numPlayers, ArrayList<Player> players)
	{
		this.numPlayers = numPlayers;
		this.players = players;
	}
	
	public void updateScores(ArrayList<Player> players)
	{
		for(Score score : scores)
		{
			for(Player player : players)
				if(player.getName().equals(score.Name))
				{
					score.Points = player.getScore();
				}
		}
	}
	
	public void displayScoreboard()
	{
		
	}
}