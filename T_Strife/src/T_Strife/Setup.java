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
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel centerPanel = new JPanel(new GridBagLayout());
		JPanel botPanel = new JPanel();
		frame.setSize(400, 400);
		
		JLabel select = new JLabel("Please select the number of players:");
		select.setBounds(50, 50, 250, 20);
		frame.add(select);
		
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
		JPanel names = new JPanel(new GridLayout(6, 2));
		names.add(name1);
		names.add(slot1);
		names.add(name2);
		names.add(slot2);
		names.add(name3);
		names.add(slot3);
		
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
		
		JButton add = new JButton("Add a player");
		JButton remove = new JButton("Remove a player");
		
		class PlayerListener implements ActionListener
		{
			/**
			 * PlayerListener - Takes an action based on the button pressed.
			 * @param event Based on the button pressed, main menu leads to another interface.
			 */
			public void actionPerformed(ActionEvent event)
			{
				int countPlayers = 3;
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
							names.add(name4);
							names.add(slot4);
							names.validate();
							System.out.println(countPlayers);
						}
						else if (countPlayers == 4)
						{
							names.add(name5);
							names.add(slot5);
							names.validate();
						}
						else if (countPlayers == 5)
						{
							names.add(name6);
							names.add(slot6);
							names.validate();
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
							centerPanel.remove(name4);
							centerPanel.remove(slot4);
							names.validate();
						}
						else if (countPlayers == 5)
						{
							centerPanel.remove(name5);
							centerPanel.remove(slot5);
						}
						else if (countPlayers == 6)
						{
							centerPanel.remove(name6);
							centerPanel.remove(slot6);
						}
						countPlayers--;
					}
				}
		}
	} //end of ButtonListener
		
		add.addActionListener(new PlayerListener());
		remove.addActionListener(new PlayerListener());
		centerPanel.add(add);
		centerPanel.add(remove);
		centerPanel.add(names);
		
		
//		String[] choices = {" ", "3", "4", "5", "6"}; 
//		JComboBox<String> numPlayers = new JComboBox<String>(choices);
//		numPlayers.setBounds(265, 50, 50, 20);
//		frame.add(numPlayers);
//		pane.add(numPlayers);
		
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
					Gameboard.init();
				}
			}
		} //end of ButtonListener
		
		start.addActionListener(new StartListener());
		
		panel.add(botPanel, BorderLayout.SOUTH);
		panel.add(centerPanel, BorderLayout.CENTER);
		frame.add(panel);
		
		frame.setVisible(true);
	}

	public static void init()
	{
		new Setup();
	}
}