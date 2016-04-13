package T_Strife;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import T_Strife.Scoreboard.Score;

public class GameScoreboard extends Scoreboard
{
	private int numPlayers;
	private JPanel scorePanel;
	//ArrayList<Player> players;
	
	 //Score Panel should be GridLayout(numPlayers + 1,3)
	public GameScoreboard(ArrayList<Player> players, JPanel scorePanel)
	{
		this.numPlayers = players.size();
		this.scorePanel = scorePanel;
		scores = new ArrayList<Score>();
		for(Player player : players)
		{
			//Only the first two values matter
			Score currentScore = new Score(player.getScore(), player.getName(), false, 0);
			scores.add(currentScore);
		}
		updateScoreBoard();
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
		updateScoreBoard();
	}
	
	//Pass in the JPanel where the score will be
	public void updateScoreBoard()
	{
		JLabel rLabel = new JLabel("Rank");
		JLabel nLabel = new JLabel("Name");
		JLabel pLabel = new JLabel("Points");
		
		//Center each label
		rLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Add borders
		rLabel.setBorder(BorderFactory.createEtchedBorder());
		nLabel.setBorder(BorderFactory.createEtchedBorder());
		pLabel.setBorder(BorderFactory.createEtchedBorder());

		scorePanel.add(rLabel);
		scorePanel.add(nLabel);
		scorePanel.add(pLabel);

		for (int index = 0; index < scores.size(); index++) {
			Score currentScore = scores.get(index);
			JLabel rankLabel = new JLabel(index + 1 + ".");
			JLabel nameLabel = new JLabel(currentScore.Name);
			JLabel pointsLabel = new JLabel(currentScore.Points + "");

			//Center each label
			rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);

			//Add borders
			rankLabel.setBorder(BorderFactory.createEtchedBorder());
			nameLabel.setBorder(BorderFactory.createEtchedBorder());
			pointsLabel.setBorder(BorderFactory.createEtchedBorder());

			//Add to scorePanel
			scorePanel.add(rankLabel);
			scorePanel.add(nameLabel);
			scorePanel.add(pointsLabel);
		}
	}
}
