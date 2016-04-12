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
		JFrame frame = new JFrame();
//		JOptionPane pane = new JOptionPane("Please select the number of players:");
		JPanel panel = new JPanel(new BorderLayout());
		JPanel centerPanel = new JPanel(new GridBagLayout());
		JPanel botPanel = new JPanel();
		frame.setSize(400, 400);
		
		
		JLabel select = new JLabel("Please select the number of players:");
		select.setBounds(50, 50, 250, 20);
		frame.add(select);
		
		
//		JDialog test = new JDialog();
//		test.setTitle("hi");
//		test.setModal(true);
		
		JTextField slot1 = new JTextField();
		slot1.setSize(100, 20);
		JTextField slot2 = new JTextField();
		slot2.setSize(100, 20);
		JTextField slot3 = new JTextField();
		slot3.setSize(100, 20);
		JTextField slot4 = new JTextField();
		slot4.setSize(100, 20);
		JTextField slot5 = new JTextField();
		slot5.setSize(100, 20);
		JTextField slot6 = new JTextField();
		slot6.setSize(100, 20);
		JLabel name1 = new JLabel("Player 1: ");
		name1.setSize(50, 20);
		JLabel name2 = new JLabel("Player 2: ");
		name2.setSize(50, 20);
		JLabel name3 = new JLabel("Player 3: ");
		name3.setSize(50, 20);
		JLabel name4 = new JLabel("Player 4: ");
		name4.setSize(50, 20);
		JLabel name5 = new JLabel("Player 5: ");
		name5.setSize(50, 20);
		JLabel name6 = new JLabel("Player 6: ");
		name6.setSize(50, 20);
		
		JPanel namePanel3 = new JPanel(new GridLayout(3, 2));
		namePanel3.add(name1);
		namePanel3.add(slot1);
		namePanel3.add(name2);
		namePanel3.add(slot2);
		namePanel3.add(name3);
		namePanel3.add(slot3);
		
		JPanel namePanel4 = new JPanel(new GridLayout(1, 2));
		namePanel4.add(name4);
		namePanel4.add(slot4);
		
		JPanel namePanel5 = new JPanel(new GridLayout(1, 2));
		namePanel5.add(name5);
		namePanel5.add(slot5);
		
		JPanel namePanel6 = new JPanel(new GridLayout(1, 2));
		namePanel6.add(name6);
		namePanel6.add(slot6);
		
		JLabel test = new JLabel("test");
		class ItemChangeListener implements ItemListener
		{
			public void itemStateChanged(ItemEvent event) 
			{
				if (event.getStateChange() == (int)ItemEvent.SELECTED)
				{
					Object item = event.getItem();
					if (item == "3")
					{
						frame.add(test);
						panel.add(namePanel3);
						panel.add(namePanel4);
						namePanel3.setVisible(true);
					}
					if (item.equals("4"))
					{
						panel.remove(namePanel3);
						panel.remove(namePanel4);
						panel.remove(namePanel5);
						panel.remove(namePanel6);
						panel.add(namePanel3);
						panel.add(namePanel4);
						panel.setVisible(true);
					}
					if (item.equals("5"))
					{
						panel.remove(namePanel3);
						panel.remove(namePanel4);
						panel.remove(namePanel5);
						panel.remove(namePanel6);
						panel.add(namePanel3);
						panel.add(namePanel4);
						panel.add(namePanel5);
						panel.setVisible(true);
					}
					if (item.equals("6"))
					{
						panel.remove(namePanel3);
						panel.remove(namePanel4);
						panel.remove(namePanel5);
						panel.remove(namePanel6);
						panel.add(namePanel3);
						panel.add(namePanel4);
						panel.add(namePanel5);
						panel.add(namePanel6);
						panel.setVisible(true);
					}
				}
			}
		}
		
		String[] choices = {" ", "3", "4", "5", "6"}; 
		JComboBox<String> numPlayers = new JComboBox<String>(choices);
		numPlayers.setBounds(265, 50, 50, 20);
		numPlayers.addItemListener(new ItemChangeListener());
		frame.add(numPlayers);
//		pane.add(numPlayers);
		
		JButton start = new JButton("Start Game");
		start.setBounds(200, 350, 100, 20);
		botPanel.add(start);
		
		class ButtonListener implements ActionListener
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
					//Gameboard.init();
				}
			}
		} //end of ButtonListener
		
		start.addActionListener(new ButtonListener());
		
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