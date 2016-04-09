package T_Strife;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public abstract class Scoreboard 
{
	protected class Score
	{
		public int Points;
		public String Name;
		public boolean Disadvantaged;
		public int NumberTurns; //Low is better
		public Score(int points, String name, boolean disadvantaged, int numberTurns)
		{
			Points = points;
			Name = name;
			Disadvantaged = disadvantaged;
			NumberTurns = numberTurns;
		}
	}
	protected ArrayList<Score> scores;
	public Scoreboard()
	{
		
	}
	
	
	public Score getScore(String aName)
	{
		for(Score score : scores)
		{
			if(score.Name.equals(aName))
				return score;
		}
		return null;
	}
}
