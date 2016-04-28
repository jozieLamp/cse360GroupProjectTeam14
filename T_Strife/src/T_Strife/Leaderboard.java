package T_Strife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

/**
 * Class to represent the leaderboard. Allows one to add, view, search, retrieve, and store scores.
 * @author Richard Tuznik (PIN 825)
 */
public class Leaderboard extends Scoreboard {
	private String fileName;
	private int playerType; // 0 = all, 1 = disadvantaged, 2 = normal players only
	
	private JTextField searchField = new JTextField("");
	private JButton confirmSearchButton = new JButton("Search Player");
	private JPanel scorePanel;
	private GridLayout leaderboardGridLayout;

	private ButtonGroup typeGroup;
	private ButtonListener buttonListener;
	private RadioButtonListener radioButtonListener;

	private JRadioButtonMenuItem butAll, butDis, butNorm;

	public static void main(String[] args) //TEMPORARY FOR TESTING
	{
		Leaderboard l = new Leaderboard("leaderboard.txt");
		ArrayList<Player> players = new ArrayList<Player>();
		Player playerA = new DisadvantagedPlayer("X");
		Player playerB = new NormalPlayer("Y");
		Player playerC = new NormalPlayer("Z");
		players.add(playerA);
		players.add(playerB);
		players.add(playerC);
		playerA.updateScore(23);
		playerB.updateScore(122);
		playerC.updateScore(98);
		l.addToLeaderboard(players, 50);
		l.displayLeaderBoard();
	}
	
	/**
	 * Leaderboard - Creates a new leaderboard (this is a popup dialog)
	 * @param fileName - The location of the leaderboard text file (leaderboard.txt)
	 */
	public Leaderboard(String fileName) {
		this.fileName = fileName;
		playerType = 0;
		scores = new ArrayList<Score>();
		getStoredLeaderboard();
	}

	/**
	 * getStoredLeaderboard - Retrieves the data in the text file and puts it into an arrayList of type Score
	 */
	public void getStoredLeaderboard() {
		try {
			Scanner in = new Scanner(new FileReader(fileName));

			scores.clear();

			while (in.hasNext()) {
				String[] currentLineElements = in.nextLine().split("\t");

				String name = currentLineElements[0];
				int points = Integer.parseInt(currentLineElements[1]);
				boolean disadvantaged = currentLineElements[2].equals("true");
				int numberTurns = Integer.parseInt(currentLineElements[3]);

				Score nScore = new Score(points, name, disadvantaged,
						numberTurns);
				scores.add(nScore);
			}

			//br.close();
			in.close();
		} catch (Exception e) {
			System.out.println("IO Error: " + e.getMessage());
		}
		//storeLeaderboard();
	}

	/**
	 * addToLeaderboard - Adds the players' scores to the leaderboard and then adds the leaderboard scores to the text file so that it can be saved
	 * @param players The new players to be added to the leaderboard
	 * @param value The current turn number
	 */
	public void addToLeaderboard(ArrayList<Player> players, int turn) {
		getStoredLeaderboard();

		ArrayList<Score> newScores = new ArrayList<Score>();
		for(Player player : players)
		{
			Score score = new Score(player.getScore(), player.getName(), player.getType().equals("Disadvantaged"), turn);
			newScores.add(score);
			
		}
		for (Score score : newScores) {
			boolean add = false;
			int index = 0;
			while (!add && index < scores.size()) {
				Score currentScore = scores.get(index);
				if (score.Points > currentScore.Points
						|| (score.Points == currentScore.Points && score.NumberTurns < currentScore.NumberTurns)
						|| (score.Points == currentScore.Points
								&& score.NumberTurns == currentScore.NumberTurns && score.Name
								.compareTo(currentScore.Name) <= 0))
				{
					add = true;
					index--; //To counteract the next index++
				}
				index++;
			}
			score.NumberTurns = turn;
			scores.add(index, score);
		}

		storeLeaderboard();
	}

	/**
	 * storeLeaderboard - Writes the current score arraylist to the textfile
	 */
	public void storeLeaderboard() {
		try {
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

	/**
	 * displayLeaderBoard - Creates the GUI representation of the leaderboard
	 */
	public void displayLeaderBoard()
	{
		//JMenu containerMenu = new JMenu("Change Settings");
		JMenu playerTypeSubMenu = new JMenu("Player Type");
		butAll = new JRadioButtonMenuItem("All Players");
		butAll.setSelected(true);
		butDis = new JRadioButtonMenuItem("Disadvantaged Players");
		butNorm = new JRadioButtonMenuItem("Normal Players");
		
		//Event Listeners
		buttonListener = new ButtonListener();
		radioButtonListener = new RadioButtonListener();

		confirmSearchButton.addActionListener(buttonListener);
		butAll.addItemListener(radioButtonListener);
		butDis.addItemListener(radioButtonListener);
		butNorm.addItemListener(radioButtonListener);

		// ButtonGroup makes it so that only one can be selected at a time
		typeGroup = new ButtonGroup();
		typeGroup.add(butAll);
		typeGroup.add(butDis);
		typeGroup.add(butNorm);

		//JMenu menu = new JMenu("PlayerType");
		playerTypeSubMenu.add(butAll);
		playerTypeSubMenu.add(butDis);
		playerTypeSubMenu.add(butNorm);

		//Panels
		leaderboardGridLayout = new GridLayout(scores.size() + 1, 5);
		scorePanel = new JPanel(leaderboardGridLayout);
		
		JLabel rLabel = new JLabel("Rank");
		JLabel nLabel = new JLabel("Name");
		JLabel pLabel = new JLabel("Points");
		JLabel tLabel = new JLabel("Turns");
		JLabel dLabel = new JLabel("Player Type");

		
		//Center each label
		rLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Add borders
		rLabel.setBorder(BorderFactory.createEtchedBorder());
		nLabel.setBorder(BorderFactory.createEtchedBorder());
		pLabel.setBorder(BorderFactory.createEtchedBorder());
		tLabel.setBorder(BorderFactory.createEtchedBorder());
		dLabel.setBorder(BorderFactory.createEtchedBorder());
		
		scorePanel.add(rLabel);
		scorePanel.add(nLabel);
		scorePanel.add(pLabel);
		scorePanel.add(tLabel);
		scorePanel.add(dLabel);
		for (int index = 0; index < scores.size(); index++) {
			Score currentScore = scores.get(index);
			JLabel rankLabel = new JLabel(index + 1 + ".");
			JLabel nameLabel = new JLabel(currentScore.Name);
			JLabel pointsLabel = new JLabel(currentScore.Points + "");
			JLabel turnsLabel = new JLabel(currentScore.NumberTurns + "");
			JLabel disadvLabel = new JLabel(currentScore.Disadvantaged == true ? "Disadvantaged" : "Normal");

			
			//Center each label
			rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			turnsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			disadvLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			//Add borders
			rankLabel.setBorder(BorderFactory.createEtchedBorder());
			nameLabel.setBorder(BorderFactory.createEtchedBorder());
			pointsLabel.setBorder(BorderFactory.createEtchedBorder());
			turnsLabel.setBorder(BorderFactory.createEtchedBorder());
			disadvLabel.setBorder(BorderFactory.createEtchedBorder());
			
			//Add to scorePanel
			scorePanel.add(rankLabel);
			scorePanel.add(nameLabel);
			scorePanel.add(pointsLabel);
			scorePanel.add(turnsLabel);
			scorePanel.add(disadvLabel);

		}
		JScrollPane scrollPane = new JScrollPane(scorePanel);

		JPanel optionPanel = new JPanel(new GridLayout(1, 3));
		JMenuBar playerTypeBar = new JMenuBar();
		playerTypeBar.add(playerTypeSubMenu);
		optionPanel.add(playerTypeBar);
		optionPanel.add(searchField);
		optionPanel.add(confirmSearchButton);

		JPanel basePanel = new JPanel(new BorderLayout());

		basePanel.add(scrollPane, BorderLayout.CENTER);
		basePanel.add(optionPanel, BorderLayout.SOUTH);

		JDialog leaderboardPopUp = new JDialog(new JFrame(), "Leaderboard");
		leaderboardPopUp.add(basePanel);
		leaderboardPopUp.setVisible(true);
        leaderboardPopUp.setSize(700, 400);
	}

	/**
	 * searchScoreIndex - Finds the index of the score with the desired name
	 * @param name The name of the desired player's score
	 * @return The index of the score with the desired player name
	 */
	public int searchScoreIndex(String name) 
	{
		for(int index = 0; index < scores.size(); index++)
		{
			if (scores.get(index).Name.equals(name))
			{
				return index;
			}
		}
		return -1;
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int desiredScoreIndex = searchScoreIndex(searchField.getText());
			if(desiredScoreIndex != -1)
			{
				scorePanel.removeAll();
				leaderboardGridLayout.setRows(2);
				//Description Labels
				JLabel rLabel = new JLabel("Rank");
				JLabel nLabel = new JLabel("Name");
				JLabel pLabel = new JLabel("Points");
				JLabel tLabel = new JLabel("Turns");
				JLabel dLabel = new JLabel("Player Type");

				
				//Center each label
				rLabel.setHorizontalAlignment(SwingConstants.CENTER);
				nLabel.setHorizontalAlignment(SwingConstants.CENTER);
				pLabel.setHorizontalAlignment(SwingConstants.CENTER);
				tLabel.setHorizontalAlignment(SwingConstants.CENTER);
				dLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
				//Add borders
				rLabel.setBorder(BorderFactory.createEtchedBorder());
				nLabel.setBorder(BorderFactory.createEtchedBorder());
				pLabel.setBorder(BorderFactory.createEtchedBorder());
				tLabel.setBorder(BorderFactory.createEtchedBorder());
				dLabel.setBorder(BorderFactory.createEtchedBorder());
				
				//Add to scorePanel
				scorePanel.add(rLabel);
				scorePanel.add(nLabel);
				scorePanel.add(pLabel);
				scorePanel.add(tLabel);
				scorePanel.add(dLabel);
				
				//Desired player statistics
				Score desiredScore = scores.get(desiredScoreIndex);

				JLabel rankLabel = new JLabel(desiredScoreIndex + 1 + ".");
				JLabel nameLabel = new JLabel(desiredScore.Name);
				JLabel pointsLabel = new JLabel(desiredScore.Points + "");
				JLabel turnsLabel = new JLabel(desiredScore.NumberTurns + "");
				JLabel disadvLabel = new JLabel(desiredScore.Disadvantaged == true ? "Disadvantaged" : "Normal");

				
				//Center each label
				rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
				nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
				pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
				turnsLabel.setHorizontalAlignment(SwingConstants.CENTER);
				disadvLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
				//Add borders
				rankLabel.setBorder(BorderFactory.createEtchedBorder());
				nameLabel.setBorder(BorderFactory.createEtchedBorder());
				pointsLabel.setBorder(BorderFactory.createEtchedBorder());
				turnsLabel.setBorder(BorderFactory.createEtchedBorder());
				disadvLabel.setBorder(BorderFactory.createEtchedBorder());
				
				//Add to scorePanel
				scorePanel.add(rankLabel);
				scorePanel.add(nameLabel);
				scorePanel.add(pointsLabel);
				scorePanel.add(turnsLabel);
				scorePanel.add(disadvLabel);
				
				scorePanel.validate();
			}
			else
			{
				System.out.println("Player not found.");
				
				scorePanel.removeAll();
				leaderboardGridLayout.setRows(scores.size() + 1);
				
				JLabel rLabel = new JLabel("Rank");
				JLabel nLabel = new JLabel("Name");
				JLabel pLabel = new JLabel("Points");
				JLabel tLabel = new JLabel("Turns");
				JLabel dLabel = new JLabel("Player Type");

				
				//Center each label
				rLabel.setHorizontalAlignment(SwingConstants.CENTER);
				nLabel.setHorizontalAlignment(SwingConstants.CENTER);
				pLabel.setHorizontalAlignment(SwingConstants.CENTER);
				tLabel.setHorizontalAlignment(SwingConstants.CENTER);
				dLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
				//Add borders
				rLabel.setBorder(BorderFactory.createEtchedBorder());
				nLabel.setBorder(BorderFactory.createEtchedBorder());
				pLabel.setBorder(BorderFactory.createEtchedBorder());
				tLabel.setBorder(BorderFactory.createEtchedBorder());
				dLabel.setBorder(BorderFactory.createEtchedBorder());
				
				scorePanel.add(rLabel);
				scorePanel.add(nLabel);
				scorePanel.add(pLabel);
				scorePanel.add(tLabel);
				scorePanel.add(dLabel);
				for (int index = 0; index < scores.size(); index++) {
					Score currentScore = scores.get(index);
					JLabel rankLabel = new JLabel(index + 1 + ".");
					JLabel nameLabel = new JLabel(currentScore.Name);
					JLabel pointsLabel = new JLabel(currentScore.Points + "");
					JLabel turnsLabel = new JLabel(currentScore.NumberTurns + "");
					JLabel disadvLabel = new JLabel(currentScore.Disadvantaged == true ? "Disadvantaged" : "Normal");

					
					//Center each label
					rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
					nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
					pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
					turnsLabel.setHorizontalAlignment(SwingConstants.CENTER);
					disadvLabel.setHorizontalAlignment(SwingConstants.CENTER);
					
					//Add borders
					rankLabel.setBorder(BorderFactory.createEtchedBorder());
					nameLabel.setBorder(BorderFactory.createEtchedBorder());
					pointsLabel.setBorder(BorderFactory.createEtchedBorder());
					turnsLabel.setBorder(BorderFactory.createEtchedBorder());
					disadvLabel.setBorder(BorderFactory.createEtchedBorder());
					
					//Add to scorePanel
					scorePanel.add(rankLabel);
					scorePanel.add(nameLabel);
					scorePanel.add(pointsLabel);
					scorePanel.add(turnsLabel);
					scorePanel.add(disadvLabel);
					scorePanel.validate();
				}
			}
		}
	}

	private class RadioButtonListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			Object dif = e.getSource();
			
			int numberOfDisadvantagedPlayers = 0;
			for(Score score : scores)
			{
				if(score.Disadvantaged)
					numberOfDisadvantagedPlayers++;
			}
			
			// Change type depending on which type button was pressed
			if (dif.equals(butAll)) 
			{
				playerType = 0;
				leaderboardGridLayout.setRows(scores.size() + 1);
			}
			else if (dif.equals(butDis))
			{
				playerType = 1;
				leaderboardGridLayout.setRows(numberOfDisadvantagedPlayers + 1);
			} 
			else if (dif.equals(butNorm)) 
			{
				playerType = 2;
				leaderboardGridLayout.setRows((scores.size() - numberOfDisadvantagedPlayers) + 1);
			}
			
			scorePanel.removeAll();
			
			JLabel rLabel = new JLabel("Rank");
			JLabel nLabel = new JLabel("Name");
			JLabel pLabel = new JLabel("Points");
			JLabel tLabel = new JLabel("Turns");
			JLabel dLabel = new JLabel("Player Type");

			
			//Center each label
			rLabel.setHorizontalAlignment(SwingConstants.CENTER);
			nLabel.setHorizontalAlignment(SwingConstants.CENTER);
			pLabel.setHorizontalAlignment(SwingConstants.CENTER);
			tLabel.setHorizontalAlignment(SwingConstants.CENTER);
			dLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			//Add borders
			rLabel.setBorder(BorderFactory.createEtchedBorder());
			nLabel.setBorder(BorderFactory.createEtchedBorder());
			pLabel.setBorder(BorderFactory.createEtchedBorder());
			tLabel.setBorder(BorderFactory.createEtchedBorder());
			dLabel.setBorder(BorderFactory.createEtchedBorder());
			
			scorePanel.add(rLabel);
			scorePanel.add(nLabel);
			scorePanel.add(pLabel);
			scorePanel.add(tLabel);
			scorePanel.add(dLabel);
			for (int index = 0; index < scores.size(); index++) 
			{
				Score currentScore = scores.get(index);
				if(playerType == 0 || (playerType == 1 && currentScore.Disadvantaged == true) || (playerType == 2 && currentScore.Disadvantaged == false))
				{
					JLabel rankLabel = new JLabel(index + 1 + ".");
					JLabel nameLabel = new JLabel(currentScore.Name);
					JLabel pointsLabel = new JLabel(currentScore.Points + "");
					JLabel turnsLabel = new JLabel(currentScore.NumberTurns + "");
					JLabel disadvLabel = new JLabel(currentScore.Disadvantaged == true ? "Disadvantaged" : "Normal");

					
					//Center each label
					rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
					nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
					pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
					turnsLabel.setHorizontalAlignment(SwingConstants.CENTER);
					disadvLabel.setHorizontalAlignment(SwingConstants.CENTER);
					
					//Add borders
					rankLabel.setBorder(BorderFactory.createEtchedBorder());
					nameLabel.setBorder(BorderFactory.createEtchedBorder());
					pointsLabel.setBorder(BorderFactory.createEtchedBorder());
					turnsLabel.setBorder(BorderFactory.createEtchedBorder());
					disadvLabel.setBorder(BorderFactory.createEtchedBorder());
					
					//Add to scorePanel
					scorePanel.add(rankLabel);
					scorePanel.add(nameLabel);
					scorePanel.add(pointsLabel);
					scorePanel.add(turnsLabel);
					scorePanel.add(disadvLabel);
					scorePanel.validate();
				}
			}
			scorePanel.validate();
		}
	}
}
