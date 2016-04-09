package main_menu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import setup.setup;

public class main_menu
{
	public main_menu() 
	{
		JFrame frame = new JFrame("Menu Demo");
		frame.setSize(400, 200);

		JPanel center = new JPanel(new BorderLayout());
		JPanel buttons = new JPanel(new GridLayout(3,1));
		JButton start = new JButton("Start a Game");
		JButton leader = new JButton("View Leaderboard");
		JButton exit = new JButton("Exit");
		buttons.add(start);
		buttons.add(leader);
		buttons.add(exit);
		center.add(buttons, BorderLayout.CENTER);
	
		class ButtonListener implements ActionListener
		{
			/**
			 * ButtonListener - Takes an action based on the button pressed.
			 * @param event Based on the button pressed, main menu leads to another interface.
			 */
			public void actionPerformed(ActionEvent event)
			{
				Object action = event.getSource();
				if (action == start)
				{
					System.out.println("Go to setup screen");
					frame.setVisible(false);
					setup.init();
				}
				if (action == leader)
				{
					System.out.println("Go to leaderboard");
					frame.setVisible(false);
					//leaderboard.init();
				}
					
				if (action == exit)
					System.exit(0);
			}
		} //end of ButtonListener
		
		start.addActionListener(new ButtonListener());
		leader.addActionListener(new ButtonListener());
		exit.addActionListener(new ButtonListener());

		frame.add(center);
		frame.setVisible(true);
	}
	
	public static void main(String args[]) 
	{
		new main_menu();
	}
}



