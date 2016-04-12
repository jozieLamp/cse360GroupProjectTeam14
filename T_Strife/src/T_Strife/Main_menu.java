	package T_Strife;
	
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	
	public class Main_menu
	{
		public Main_menu() 
		{
			JFrame.setDefaultLookAndFeelDecorated(true);
			JFrame frame = new JFrame("Tendentious Strife - Main Menu");
			frame.setSize(400, 400);
			
			JPanel panel = new JPanel(new BorderLayout());
			JLabel name = new JLabel("TENDENTIOUS STRIFE");
			name.setBounds(200, 50, 100, 20);
			panel.add(name, BorderLayout.NORTH);
			
			
			JPanel centerPanel = new JPanel(new GridLayout(3, 1));
//			JPanel buttons = new JPanel(new GridLayout(3,1));
			JButton start = new JButton("Start a Game");
			start.setBounds(200, 200, 100, 20);
			JButton leader = new JButton("View Leaderboard");
			leader.setBounds(200, 250, 100, 20);
			JButton exit = new JButton("Exit");
			exit.setBounds(200, 300, 100, 20);
//			buttons.add(start);
//			buttons.add(leader);
//			buttons.add(exit);
			centerPanel.add(start);
			centerPanel.add(leader);
			centerPanel.add(exit);
		
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
						Setup.init();
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
	}