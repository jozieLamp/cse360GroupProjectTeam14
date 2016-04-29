package T_Strife;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Random;

/**
 * Setup - Sets up the game after pressing start, but before game initialization
 * Finds out how many players, along with their names input.
 *
 * @author - Ellery Leung (Pin 507)
 */
public class Setup
{
	JFrame frame;
	JButton start;
	JButton add;
	JButton remove;
	JPanel setupPanel;
	JPanel topPanel;
	JPanel centerPanel;
	JPanel botPanel;
	JLabel name1;
	JLabel name2;
	JLabel name3;
	JLabel name4;
	JLabel name5;
	JLabel name6;
	JPanel names;
	JTextField slot1;
	JTextField slot2;
	JTextField slot3;
	JTextField slot4;
	JTextField slot5;
	JTextField slot6;
	int countPlayers = 3;
	String[] playerNames = new String[6];
	
	/**
	 * Constructor for the setup class.
	 */
	public Setup() 
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame();
		frame.setSize(400, 400);
		
		setupPanel = new JPanel(new GridLayout(3, 1));
		topPanel = new JPanel(new GridLayout(1, 2));
		centerPanel = new JPanel();
		botPanel = new JPanel();
		
		name1 = new JLabel("Player 1: ");
		name1.setSize(50, 20);
		name2 = new JLabel("Player 2: ");
		name2.setSize(50, 20);
		name3 = new JLabel("Player 3: ");
		name3.setSize(50, 20);
		slot1 = new JTextField();
		slot1.setSize(100, 20);
		slot2 = new JTextField();
		slot2.setSize(100, 20);
		slot3 = new JTextField();
		slot3.setSize(100, 20);
		name4 = new JLabel("Player 4: ");
		name4.setSize(50, 20);
		name5 = new JLabel("Player 5: ");
		name5.setSize(50, 20);
		name6 = new JLabel("Player 6: ");
		name6.setSize(50, 20);
		slot4 = new JTextField();
		slot4.setSize(100, 20);
		slot5 = new JTextField();
		slot5.setSize(100, 20);
		slot6 = new JTextField();
		slot6.setSize(100, 20);
		
		names = new JPanel(new GridLayout(6, 2));
		names.add(name1);
		names.add(slot1);
		names.add(name2);
		names.add(slot2);
		names.add(name3);
		names.add(slot3);
		names.add(name4);
		names.add(slot4);
		names.add(name5);
		names.add(slot5);
		names.add(name6);
		names.add(slot6);
		
		name4.setVisible(false);
		slot4.setVisible(false);
		name5.setVisible(false);
		slot5.setVisible(false);
		name6.setVisible(false);
		slot6.setVisible(false);
		
		add = new JButton("Add a player");
		remove = new JButton("Remove a player");
	
		PlayerListener playerListen = new PlayerListener();
		add.addActionListener(playerListen);
		remove.addActionListener(playerListen);
		topPanel.add(add);
		topPanel.add(remove);
		
		centerPanel.add(names);
		
		start = new JButton("Start Game");
		start.setBounds(200, 350, 100, 20);
		start.addActionListener(new StartListener());
		botPanel.add(start);
		
		setupPanel.add(topPanel);
		setupPanel.add(centerPanel);
		setupPanel.add(botPanel);
		frame.add(setupPanel);
		
		frame.setVisible(true);
	}

	/**
	 * init - Instantiates Setup.
	 */	
	public static void init()
	{
		new Setup();
	}
	
	/**
	 * StartListener - Button Listener class to find out when they press start
	 */
	private class StartListener implements ActionListener
	{
		/**
		 * actionPerformed - Takes an action based on the button pressed.
		 * @param event Only option here is start - will begin the game.
		 */
		public void actionPerformed(ActionEvent event)
		{
			Object action = event.getSource();
			if (action == start)
			{
				playerNames[0] = slot1.getText();
				playerNames[1] = slot2.getText();
				playerNames[2] = slot3.getText();
				playerNames[3] = slot4.getText();
				playerNames[4] = slot5.getText();
				playerNames[5] = slot6.getText();
			
				GameData game = new GameData();
				Random rand = new Random();
				
				int disadvantagedIndex = rand.nextInt(countPlayers);
				int winPoints = 10;
				
				Controller.setNumPlayers(countPlayers, game);
				Controller.setPlayers(playerNames, disadvantagedIndex, game);
				Controller.setWinPoints(winPoints, game);
				
				frame.setVisible(false);
				
				game.gameboard = new Gameboard(Controller.getPlayers(game), game);

				
			}
		}
	} //end of StartListener
	
	/**
	 * PlayerListener - Button Listener class to check for when a user adds or removes a player.
	 */
	private class PlayerListener implements ActionListener
	{
		/**
		 * PlayerListener - Takes an action based on the button pressed.
		 * @param event Based on the button pressed, main menu leads to another interface.
		 */
		public void actionPerformed(ActionEvent event)
		{
			Object action = event.getSource();
			if (action == add)
			{
				if (countPlayers == 6)
				{
					JOptionPane error = new JOptionPane();
					error.showMessageDialog(frame, "Sorry, the maximum is 6 players.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if (countPlayers == 3)
					{
						name4.setVisible(true);
						slot4.setVisible(true);
						name5.setVisible(false);
						slot5.setVisible(false);
						name6.setVisible(false);
						slot6.setVisible(false);
					}
					else if (countPlayers == 4)
					{
						name4.setVisible(true);
						slot4.setVisible(true);
						name5.setVisible(true);
						slot5.setVisible(true);
						name6.setVisible(false);
						slot6.setVisible(false);
					}
					else if (countPlayers == 5)
					{
						name4.setVisible(true);
						slot4.setVisible(true);
						name5.setVisible(true);
						slot5.setVisible(true);
						name6.setVisible(true);
						slot6.setVisible(true);
					}
					countPlayers++;
				}
			}
			if (action == remove)
			{
				if (countPlayers == 3)
				{
					JOptionPane error = new JOptionPane();
					error.showMessageDialog(frame, "Sorry, the minimum is 3 players.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if (countPlayers == 4)
					{
						name4.setVisible(false);
						slot4.setVisible(false);
						name5.setVisible(false);
						slot5.setVisible(false);
						name6.setVisible(false);
						slot6.setVisible(false);
					}
					else if (countPlayers == 5)
					{
						name4.setVisible(true);
						slot4.setVisible(true);
						name5.setVisible(false);
						slot5.setVisible(false);
						name6.setVisible(false);
						slot6.setVisible(false);
					}
					else if (countPlayers == 6)
					{
						name4.setVisible(true);
						slot4.setVisible(true);
						name5.setVisible(true);
						slot5.setVisible(true);
						name6.setVisible(false);
						slot6.setVisible(false);
					}
					countPlayers--;
				}
			}
		}
	} //end of ButtonListener
}
