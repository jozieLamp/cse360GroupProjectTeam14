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

import javax.swing.*;

public class Leaderboard extends Scoreboard {
	private String fileName;
	// protected ArrayList<Score> scores;
	private int playerType; // 0 = all, 1 = disadvantaged, 2 = normal players only
	
	//private String[] items = { "All", "Disadvantaged", "Normal" }; // 0 = all, 1= disadvantaged, 2 = normal players only
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
		l.displayLeaderBoard(null);
	}
	public Leaderboard(String fileName) {
		this.fileName = fileName;
		playerType = 0;
		scores = new ArrayList<Score>();
		getStoredLeaderboard();
	}

	private void getStoredLeaderboard() {
		try {
			// This writes to the "leaderboard.txt" file
			File scoreFile = new File(fileName);
			//System.out.println(scoreFile.getAbsolutePath());
			FileReader scoreRead = new FileReader(scoreFile);
			BufferedReader br = new BufferedReader(scoreRead);

			scores.clear();

			while (br.ready()) {
				String[] currentLineElements = br.readLine().split("\t");

				int points = Integer.parseInt(currentLineElements[0]);
				String name = currentLineElements[1];
				boolean disadvantaged = currentLineElements[2].equals("true");
				int numberTurns = Integer.parseInt(currentLineElements[3]);

				Score nScore = new Score(points, name, disadvantaged,
						numberTurns);
				scores.add(nScore);
			}

			br.close();
		} catch (Exception e) {
			System.out.println("IO Error");
		}
	}

	public void addToLeaderboard(ArrayList<Score> newScores, int turn) {
		if (scores == null)
			getStoredLeaderboard();

		for (Score score : newScores) {
			boolean add = false;
			int index = 0;
			while (!add && index < scores.size()) {
				Score currentScore = scores.get(index);
				if (score.Points > currentScore.Points
						|| (score.Points == currentScore.Points && score.NumberTurns < currentScore.NumberTurns)
						|| (score.Points == currentScore.Points
								&& score.NumberTurns == currentScore.NumberTurns && score.Name
								.compareTo(currentScore.Name) <= 0)) {
					add = true;
				}
				index++;
			}
			score.NumberTurns = turn;
			scores.add(index, score);
		}

		storeLeaderboard();
	}

	private void storeLeaderboard() {
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

	// Pass in the JFrame for the game so I can create a popup
	public void displayLeaderBoard(JFrame gameFrame) // Parameter may be unnecessary
	{
		//JMenu containerMenu = new JMenu("Change Settings");
		JMenu playerTypeSubMenu = new JMenu("Player Type");
		butAll = new JRadioButtonMenuItem("All Players");
		butAll.setSelected(true);
		butDis = new JRadioButtonMenuItem("Disadvantaged Players");
		butNorm = new JRadioButtonMenuItem("Normal Players");
		
		//Colors
		/*butRestart.setBackground(Color.blue);
		butRestart.setForeground(Color.white);
		buttonListener = new ButtonListener();
		butRestart.addActionListener(buttonListener);*/
		
		
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
		//scrollPane.add(scorePanel);
 

		JPanel optionPanel = new JPanel(new GridLayout(1, 3));
		JMenuBar playerTypeBar = new JMenuBar();
		playerTypeBar.add(playerTypeSubMenu);
		optionPanel.add(playerTypeBar);
		optionPanel.add(searchField);
		optionPanel.add(confirmSearchButton);

		JPanel basePanel = new JPanel(new BorderLayout());

		basePanel.add(scrollPane, BorderLayout.CENTER);
		basePanel.add(optionPanel, BorderLayout.SOUTH);
		
		/*
		 * JTextArea myTArea= new JTextArea(17, 20);
JScrollPane myScroller=new JScrollPane( myTArea);
String longText="jadda jadda... jadda jadda";
myTArea.setText(longText);
JOptionPane.show...(myParentContainer, myScroller, myTitle, JOptionPane.PLAIN_MESSAGE);
		 */

		//JTextArea textArea = new JTextArea(20, 20);
		//basePanel.add()
		//textArea.add(basePanel); //Incorrect
		//basePanel.setSize(100, 100);
		//JOptionPane.showConfirmDialog(null, basePanel, "Leaderboard", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
		//JOptionPane.showMessageDialog(basePanel,OptionPane.PLAIN_MESSAGE);
		//JOptionPane.showMessageDialog(basePanel, "Leaderboard");
		//JOptionPane.showMessageDialog(parentComponent, message);
		//JOptionPane.showOptionDialog(basePanel,"","Leaderboard", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
		/*
		 * if (result == JOptionPane.OK_OPTION) {
		 * System.out.println(combo.getSelectedItem() + " " + field1.getText() +
		 * " " + field2.getText()); } else { System.out.println("Cancelled"); }
		 */
		JDialog leaderboardPopUp = new JDialog(new JFrame(), "Leaderboard");
		leaderboardPopUp.add(basePanel);
		leaderboardPopUp.setVisible(true);
        leaderboardPopUp.setSize(700, 400);
	}

	private int searchScore(String name) 
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
			int desiredScoreIndex = searchScore(searchField.getText());
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
				
				//scorePanel.revalidate();
				//scorePanel.repaint();
				scorePanel.validate();
			}
			else //Try to condense this code with a method later
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
