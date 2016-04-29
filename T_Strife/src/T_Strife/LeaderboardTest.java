package T_Strife;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.junit.Test;

import T_Strife.Scoreboard.Score;

public class LeaderboardTest {
	@Test
	public void testCreation() {
		Leaderboard scoreboard = new Leaderboard("leaderboardtest.txt");
		assertNotNull(scoreboard);
	}

	@Test
	public void testAdd() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player playerA = new DisadvantagedPlayer("A");
		Player playerB = new NormalPlayer("B");
		Player playerC = new NormalPlayer("C");
		players.add(playerA);
		players.add(playerB);
		players.add(playerC);
		playerA.updateScore(63);
		playerB.updateScore(126);
		playerC.updateScore(36);
		Leaderboard scoreboard = new Leaderboard("leaderboardtest.txt");
		scoreboard.addToLeaderboard(players, 23);
		assertEquals(scoreboard.scores.get(0).Points, 126);
		assertEquals(scoreboard.scores.get(1).Points, 63);
		assertEquals(scoreboard.scores.get(2).Points, 36);
		try {
			PrintWriter pw = new PrintWriter("leaderboardtest.txt");
			pw.close();
		} catch (Exception e) {
		}
	}

	@Test
	public void testSearchScoreIndex() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player playerA = new DisadvantagedPlayer("A");
		Player playerB = new NormalPlayer("B");
		Player playerC = new NormalPlayer("C");
		players.add(playerA);
		players.add(playerB);
		players.add(playerC);
		playerA.updateScore(63);
		playerB.updateScore(126);
		playerC.updateScore(36);
		Leaderboard scoreboard = new Leaderboard("leaderboardtest.txt");
		scoreboard.addToLeaderboard(players, 23);
		assertEquals(scoreboard.searchScoreIndex("A"), 1);
		assertEquals(scoreboard.searchScoreIndex("B"), 0);
		assertEquals(scoreboard.searchScoreIndex("C"), 2);
		assertEquals(scoreboard.searchScoreIndex("D"), -1);
		try {
			PrintWriter pw = new PrintWriter("leaderboardtest.txt");
			pw.close();
		} catch (Exception e) {
		}
	}
}
