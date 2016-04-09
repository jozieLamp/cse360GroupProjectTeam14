	package T_Strife;
	
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	
	public class main_menu
	{
		public main_menu() 
		{
			JFrame frame = new JFrame("Tendentious Strife - Main Menu");
			frame.setSize(400, 200);
	
			JLabel name = new JLabel("TENDENTIOUS STRIFE");
			name.setSize(100, 40);
			
			JPanel buttons = new JPanel(new GridLayout(3,1));
			JButton start = new JButton("Start a Game");
			JButton leader = new JButton("View Leaderboard");
			JButton exit = new JButton("Exit");
			buttons.add(start);
			buttons.add(leader);
			buttons.add(exit);
		
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
	
			frame.add(buttons);
			frame.setVisible(true);
		}
		
		public static void init()
		{
			new main_menu();
		}
	}