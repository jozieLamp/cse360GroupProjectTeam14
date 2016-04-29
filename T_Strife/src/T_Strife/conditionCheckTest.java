package T_Strife;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class conditionCheckTest {

	@Test
	public void testSplit() {
		
		GameData gamedat = new GameData();		
		Player playerA = new DisadvantagedPlayer("John");
		Player playerB = new NormalPlayer("Bo");
		Player playerC = new NormalPlayer("Carl");

		playerA.updateScore(20);
		playerB.updateScore(35);
		playerC.updateScore(30);
		
		Player[] playerList = {playerA, playerB, playerC};
		
		//For this test current player is playerC (Carl) other player parameter is John
		//Both playerC and playerA should have 25 points
		conditionCheck.split(gamedat, 2, "John");
		
		assertEquals(playerA.getScore(), 25);
		assertEquals(playerB.getScore(), 35);
		assertEquals(playerC.getScore(), 25);
		
		
		//overrides the previous score values
		playerA.updateScore(33);
		playerB.updateScore(52);
		playerC.updateScore(27);
		
		//current player is playerA aka John
		//and the other player parameter for this test is playerB aka Bo
		conditionCheck.split(gamedat, 0, "Bo");
	
		//both A and B should have 42 points, playerC's points will remain same
		assertEquals(playerA.getScore(), 42);
		assertEquals(playerB.getScore(), 42);
		assertEquals(playerC.getScore(), 27);
			
	}

	@Test
	public void testSteal() {

		Player playerA = new DisadvantagedPlayer("John");
		Player playerB = new NormalPlayer("Bo");
		Player playerC = new NormalPlayer("Carl");

		playerA.updateScore(20);
		playerB.updateScore(25);
		playerC.updateScore(30);
		
		Player[] playerList = {playerA, playerB, playerC};
		
		//current player is playerC aka Carl
		//Stealing from Bo
		//parameters are playerList array, position of current player, 7 points dice roll
		//total number of players
		conditionCheck.steal(playerList, 2, 7, 3, "Bo");
		
		
		assertEquals(playerA.getScore(), 20);  //playerA's points will not change
		assertEquals(playerB.getScore(), 18);  //playerB's points will be reduced by 7
		assertEquals(playerC.getScore(), 37);  //playerC's points will be increased by 7
		
		//overrides the scores from the previous test
		playerA.updateScore(72);
		playerB.updateScore(80);
		playerC.updateScore(88);
		
		//current player is playerA, playerA will steal 5 points from playerC aka Carl
		conditionCheck.steal(playerList, 0, 5, 3, "Carl");
		
		assertEquals(playerA.getScore(), 77); //playerA's points will increase by 5
		assertEquals(playerB.getScore(), 80); //playerB's points will remain the same
		assertEquals(playerC.getScore(), 83); //playerC's points will be reduced by 5
	
		//overrides the scores from the previous test
		playerA.updateScore(10);
		playerB.updateScore(0);
		
		//check what happens when playerA attempts to steal from playerB who has 0 points
		//he shouldn't gain any points and playerB's points should remain the same 
		conditionCheck.steal(playerList, 0, 10, 3, "Bo");
		
		assertEquals(playerA.getScore(), 10);
		assertEquals(playerB.getScore(), 0);
		
	}

	@Test
	public void testMultiply() {
		
		Player playerA = new DisadvantagedPlayer("John");
		Player playerB = new NormalPlayer("Bo");
		Player playerC = new NormalPlayer("Carl");

		playerA.updateScore(20);
		playerB.updateScore(25);
		playerC.updateScore(30);
		
		Player[] playerList = {playerA, playerB, playerC};
		
		//multiply condition will increase the player's points by 150%
		conditionCheck.multiply(playerList, 0);
		conditionCheck.multiply(playerList, 1);
		conditionCheck.multiply(playerList, 2);
		
		
		assertEquals(playerA.getScore(), (int)(20*1.5));
		assertEquals(playerB.getScore(), (int)(25*1.5));
		assertEquals(playerC.getScore(), (int)(30*1.5));
		
	}

	@Test
	public void testLose() {

		Player playerA = new DisadvantagedPlayer("John");
		Player playerB = new NormalPlayer("Bo");
		Player playerC = new NormalPlayer("Carl");

		playerA.updateScore(20);
		playerB.updateScore(25);
		playerC.updateScore(30);
		
		Player[] playerList = {playerA, playerB, playerC};
		
		//playerA lose 6 points, playerB lose 9, playerC lose 4
		conditionCheck.lose(playerList, 0, 6);
		conditionCheck.lose(playerList, 1, 9);
		conditionCheck.lose(playerList, 2, 4);
		
		assertEquals(playerA.getScore(), 14);
		assertEquals(playerB.getScore(), 16);
		assertEquals(playerC.getScore(), 26);
		
		//overrides the playerA's score with 0
		playerA.updateScore(0);
		
		//point value will not go below 0 therefore playerA must have 0 points not -6
		conditionCheck.lose(playerList, 0, 6);
		
		assertNotEquals(playerA.getScore(), -6);
		assertEquals(playerA.getScore(), 0);
		
	}

	@Test
	public void testTax() {

		Player playerA = new DisadvantagedPlayer("John");
		Player playerB = new NormalPlayer("Bo");
		Player playerC = new NormalPlayer("Carl");
		Player playerD = new NormalPlayer("Derek");
		Player playerE = new NormalPlayer("Eric");
		Player playerF = new NormalPlayer("Fin");
		
		playerA.updateScore(10);
		playerB.updateScore(20);
		playerC.updateScore(30);
		playerD.updateScore(0);
		playerE.updateScore(40);
		playerF.updateScore(10);
		
		Player[] playerList = {playerA, playerB, playerC, playerD, playerE, playerF};
		
		//playerA will tax the other players
		conditionCheck.tax(playerList, 0, 6);
		
		assertEquals(playerA.getScore(), (20));
		assertEquals(playerB.getScore(), (18));
		assertEquals(playerC.getScore(), (27));
		assertEquals(playerD.getScore(), (0));
		assertEquals(playerE.getScore(), (36));
		assertEquals(playerF.getScore(), (9));
		
	}

}
