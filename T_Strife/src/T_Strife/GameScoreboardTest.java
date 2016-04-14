package T_Strife;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.junit.Test;

public class GameScoreboardTest {

	@Test
	public void testCreation() 
	{
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new DisadvantagedPlayer("A"));
		players.add(new NormalPlayer("B"));
		players.add(new NormalPlayer("C"));
		GameScoreboard scoreboard = new GameScoreboard(players, new JPanel());
		assertNotNull(scoreboard);
	}
	@Test
	public void testUpdateScores()
	{
		ArrayList<Player> players = new ArrayList<Player>();
		Player playerA = new DisadvantagedPlayer("A");
		Player playerB = new NormalPlayer("B");
		Player playerC = new NormalPlayer("C");
		players.add(playerA);
		players.add(playerB);
		players.add(playerC);
		GameScoreboard scoreboard = new GameScoreboard(players, new JPanel());
		playerA.updateScore(23);
		playerB.updateScore(43);
		playerC.updateScore(36);
		scoreboard.updateScores(players);
		assertEquals(scoreboard.scores.get(0).Points, 23);
		assertEquals(scoreboard.scores.get(1).Points, 43);
		assertEquals(scoreboard.scores.get(2).Points, 36);
	}
}
