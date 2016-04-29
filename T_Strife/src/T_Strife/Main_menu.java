
	package T_Strife;
	
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	import java.util.ArrayList;
	import java.util.Random;
	import java.util.Scanner;
	
	
	public class Main_menu
	{
		JFrame frame;
		JButton start, leader, exit;
		Leaderboard board;
		
		
		public Main_menu() 
		{
//			JFrame.setDefaultLookAndFeelDecorated(true);
			frame = new JFrame("Tendentious Strife - Main Menu");
			frame.setSize(400, 400);
			
			board = new Leaderboard("leaderboard.txt");
			
			JPanel panel = new JPanel(new BorderLayout());
			JLabel name = new JLabel("TENDENTIOUS STRIFE");
			name.setBounds(200, 50, 100, 20);
			panel.add(name, BorderLayout.NORTH);
			
			
			JPanel centerPanel = new JPanel(new GridLayout(3, 1));
			
			start = new JButton("Start a Game");
			start.setBounds(200, 200, 100, 20);
			leader = new JButton("View Leaderboard");
			leader.setBounds(200, 250, 100, 20);
			exit = new JButton("Exit");
			exit.setBounds(200, 300, 100, 20);
			centerPanel.add(start);
			centerPanel.add(leader);
			centerPanel.add(exit);
			
			start.addActionListener(new ButtonListener());
			leader.addActionListener(new ButtonListener());
			exit.addActionListener(new ButtonListener());
	
//			frame.add(buttons);
			panel.setBounds(200, 50, 100, 20);
			frame.add(panel);
			frame.add(centerPanel);
			frame.setVisible(true);
		}
		
		public static void main(String[] args)
		{
			new Main_menu();
		}

		private class ButtonListener implements ActionListener
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
					frame.setVisible(false);
					Setup.init();
				}
				if (action == leader)
				{
					board.displayLeaderBoard();
				}
				if (action == exit)
					System.exit(0);
			}
		} //end of ButtonListener
	}
