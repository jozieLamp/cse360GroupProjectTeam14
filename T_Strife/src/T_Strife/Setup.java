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
		JOptionPane pane = new JOptionPane();
		frame.setSize(400, 200);
		
		JPanel top = new JPanel(new BorderLayout());
		JLabel selectNum = new JLabel("Please select the number of players.");
		selectNum.setSize(100, 20);
		top.add(selectNum, BorderLayout.WEST);
		
		String[] choices = {"3", "4", "5", "6"}; 
		JComboBox<String> numPlayers = new JComboBox<String>(choices);
		numPlayers.setSize(100, 20);
		top.add(numPlayers, BorderLayout.EAST);
		
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
		name1.setSize(100, 20);
		JLabel name2 = new JLabel("Player 2: ");
		name2.setSize(100, 20);
		JLabel name3 = new JLabel("Player 3: ");
		name3.setSize(100, 20);
		JLabel name4 = new JLabel("Player 4: ");
		name4.setSize(100, 20);
		JLabel name5 = new JLabel("Player 5: ");
		name5.setSize(100, 20);
		JLabel name6 = new JLabel("Player 6: ");
		name6.setSize(100, 20);
		
		JPanel namePanel = new JPanel(new GridLayout(6, 2));
		namePanel.add(name1);
		namePanel.add(slot1);
		namePanel.add(name2);
		namePanel.add(slot2);
		namePanel.add(name3);
		namePanel.add(slot3);
		namePanel.add(name4);
		namePanel.add(slot4);
		namePanel.add(name5);
		namePanel.add(slot5);
		namePanel.add(name6);
		namePanel.add(slot6);
		
		JButton start = new JButton("Start Game");
		start.setSize(100, 20);
		
		pane.add(namePanel);
		pane.add(top);
		pane.add(start);
		frame.add(pane);
		frame.setVisible(true);
	}

	public static void init()
	{
		new Setup();
	}
}