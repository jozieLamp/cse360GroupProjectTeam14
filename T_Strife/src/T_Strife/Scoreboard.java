package T_Strife;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class to represent a scoreboard. This class holds an ArrayList of Scores which contains players' points, name, type, and turns.
 * @author Richard Tuznik (PIN 825)
 */
public abstract class Scoreboard 
{
	/**
	 * Class to represent a player's score. Has points, name, type, and turns. Treat like a struct
	 * @author Richard Tuznik (PIN 825)
	 */
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
	
	//Used to sort scores
	protected class ScoreCompare implements Comparator<Score>
	{
	    @Override
	    public int compare(Score score1, Score score2)
	    {
			if (score1.Points > score2.Points
					|| (score1.Points == score2.Points
					&& score1.NumberTurns < score2.NumberTurns)
					|| (score1.Points == score2.Points
					&& score1.NumberTurns == score2.NumberTurns
					&& score1.Name.compareTo(score2.Name) <= 0))
				return 1;
			return -1;
	    }
	}
	
	protected ArrayList<Score> scores;
	
	/**
	 * getScore - Gets the score of the player with the desired name
	 * @param aName The name of the desired player
	 * @return The score of the desired player
	 */
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
