package T_Strife;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

//import DiceGame.Scoreboard.Score;

public class Leaderboard extends Scoreboard
{
	private String fileName;
	protected ArrayList<Score> scores;
	public Leaderboard(String fileName)
	{
		this.fileName = fileName;
	}
	
	private void getStoredLeaderboard()
	{
		try {
			// This writes to the "leaderboard.txt" file
			File scoreFile = new File(fileName);
			FileReader scoreRead = new FileReader(scoreFile);
			BufferedReader br = new BufferedReader(scoreRead);
			
			scores.clear();
			
			while(br.ready())
			{
				String[] currentLineElements = br.readLine().split("\t");
				
				int points = Integer.parseInt(currentLineElements[0]);
				String name = currentLineElements[1];
				boolean disadvantaged = currentLineElements[2] == "true" ? true : false;
				int numberTurns = Integer.parseInt(currentLineElements[3]);
				
				Score nScore = new Score(points, name, disadvantaged, numberTurns);
				scores.add(nScore);
			}
			
			br.close();
		} catch (Exception e) {
			System.out.println("IO Error");
		}
	}
	
	public void addToLeaderboard(ArrayList<Score> newScores, int turn)
	{
		if(scores == null)
			getStoredLeaderboard();
		
		for(Score score : newScores)
		{
			boolean add = false;
			int index = 0;
			while(!add && index < scores.size())
			{
				Score currentScore = scores.get(index);
				if(score.Points > currentScore.Points
				|| (score.Points == currentScore.Points && score.NumberTurns < currentScore.NumberTurns)
				|| (score.Points == currentScore.Points && score.NumberTurns == currentScore.NumberTurns && score.Name.compareTo(currentScore.Name) <= 0))
				{
					add = true;
				}
				index++;
			}
			score.NumberTurns = turn;
			scores.add(index, score);
		}
		
		storeLeaderboard();
	}
	
	private void storeLeaderboard()
	{
		try {
			// This writes to the "leaderboard.txt" file
			File scoreFile = new File(fileName);
			FileWriter scoreWrite = new FileWriter(scoreFile);
			BufferedWriter bw = new BufferedWriter(scoreWrite);
			for (int i = 0; i < scores.size(); i++) {
				Score currentScore = scores.get(i);
				bw.write("" + currentScore.Name + "\t" + currentScore.Points
						+ "\t" + currentScore.Disadvantaged + "\t"
						+ currentScore.NumberTurns);
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			System.out.println("IO Error");
		}
	}
	
	public void displayLeaderBoard(int playerType) //0 = all, 1 = disadvantaged, 2 = normal players only
	{
		
	}
}
