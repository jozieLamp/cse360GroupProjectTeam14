package T_Strife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Setup
{
	/**
	 * Constructor for the setup class.
	 */
	public Setup() 
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame();
		
		JPanel setupPanel = new JPanel(new GridLayout(3, 1));
		JPanel topPanel = new JPanel(new GridLayout(1, 2));
		JPanel centerPanel = new JPanel();
		JPanel botPanel = new JPanel();
		frame.setSize(400, 400);
		
		JLabel name1 = new JLabel("Player 1: ");
		name1.setSize(50, 20);
		JLabel name2 = new JLabel("Player 2: ");
		name2.setSize(50, 20);
		JLabel name3 = new JLabel("Player 3: ");
		name3.setSize(50, 20);
		JTextField slot1 = new JTextField();
		slot1.setSize(100, 20);
		JTextField slot2 = new JTextField();
		slot2.setSize(100, 20);
		JTextField slot3 = new JTextField();
		slot3.setSize(100, 20);
		JLabel name4 = new JLabel("Player 4: ");
		name4.setSize(50, 20);
		JLabel name5 = new JLabel("Player 5: ");
		name5.setSize(50, 20);
		JLabel name6 = new JLabel("Player 6: ");
		name6.setSize(50, 20);
		JTextField slot4 = new JTextField();
		slot4.setSize(100, 20);
		JTextField slot5 = new JTextField();
		slot5.setSize(100, 20);
		JTextField slot6 = new JTextField();
		slot6.setSize(100, 20);
		
		JPanel names = new JPanel(new GridLayout(6, 2));
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
		
		JButton add = new JButton("Add a player");
		JButton remove = new JButton("Remove a player");
		
		class PlayerListener implements ActionListener
		{
			/**
			 * PlayerListener - Takes an action based on the button pressed.
			 * @param event Based on the button pressed, main menu leads to another interface.
			 */
			int countPlayers = 3;
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
//							countPlayers = 4;
						}
						else if (countPlayers == 4)
						{
							name4.setVisible(true);
							slot4.setVisible(true);
							name5.setVisible(true);
							slot5.setVisible(true);
							name6.setVisible(false);
							slot6.setVisible(false);
//							countPlayers = 5;
						}
						else if (countPlayers == 5)
						{
							name4.setVisible(true);
							slot4.setVisible(true);
							name5.setVisible(true);
							slot5.setVisible(true);
							name6.setVisible(true);
							slot6.setVisible(true);
//							countPlayers = 6;
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
//							countPlayers = 3;
						}
						else if (countPlayers == 5)
						{
							name4.setVisible(true);
							slot4.setVisible(true);
							name5.setVisible(false);
							slot5.setVisible(false);
							name6.setVisible(false);
							slot6.setVisible(false);
//							countPlayers = 4;
						}
						else if (countPlayers == 6)
						{
							name4.setVisible(true);
							slot4.setVisible(true);
							name5.setVisible(true);
							slot5.setVisible(true);
							name6.setVisible(false);
							slot6.setVisible(false);
//							countPlayers = 5;
						}
						countPlayers--;
					}
				}
			}
		} //end of ButtonListener
		
		PlayerListener pl = new PlayerListener();
		add.addActionListener(pl);
		remove.addActionListener(pl);
		topPanel.add(add);
		topPanel.add(remove);
		centerPanel.add(names);
		
		JButton start = new JButton("Start Game");
		start.setBounds(200, 350, 100, 20);
		botPanel.add(start);
		
		class StartListener implements ActionListener
		{
			/**
			 * ButtonListener - Takes an action based on the button pressed.
			 * @param event Only option here is start - will begin the game.
			 */
			public void actionPerformed(ActionEvent event)
			{
				Object action = event.getSource();
				if (action == start)
				{
					System.out.println("Go to game screen");
					frame.setVisible(false);
//					
				}
			}
		} //end of ButtonListener
		
		start.addActionListener(new StartListener());
		botPanel.add(start);
		
		setupPanel.add(topPanel);
		setupPanel.add(centerPanel);
		setupPanel.add(botPanel);
		frame.add(setupPanel);
		
		frame.setVisible(true);
	}
	
	public int incrNumPlayers(int numPlayers)
	{
		return numPlayers++;
	}
	
	public int decrNumPlayers(int numPlayers)
	{
		return numPlayers--;
	}
	
	public static void init()
	{
		new Setup();
	}
}