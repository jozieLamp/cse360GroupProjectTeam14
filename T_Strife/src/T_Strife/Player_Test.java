import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test 
	//Test if player creation works
	public void testPlayer() 
	{
		NormalPlayer nPlayer = new NormalPlayer("Bob");
		DisadvantagedPlayer dPlayer = new DisadvantagedPlayer("Joe");
		
		assertNotNull(nPlayer);
		assertNotNull(mPlayer);
	}

	@Test
	//Test if getType works
	public void testGetType() 
	{
		NormalPlayer nPlayer = new NormalPlayer("Bob");
		DisadvantagedPlayer dPlayer = new DisadvantagedPlayer("Joe");
		
		assertEquals("Normal", nPlayer.getType());
		assertEquals("Disadvantaged", dPlayer.getType());
	}

	@Test
	//Test if getName works
	public void testGetName() 
	{
		NormalPlayer nPlayer = new NormalPlayer("Bob");
		DisadvantagedPlayer dPlayer = new DisadvantagedPlayer("Joe");
		
		assertEquals("Bob", nPlayer.getName());
		nPlayer.setName("Bobby");
		assertEquals("Bobby", nPlayer.getName());
		
		assertEquals("Joe", nPlayer.getName());
		nPlayer.setName("Joey");
		assertEquals("Joey", nPlayer.getName());
	}

	@Test
	//Test setName functionality
	public void testSetName() 
	{
		NormalPlayer nPlayer = new NormalPlayer("Bob");
		DisadvantagedPlayer dPlayer = new DisadvantagedPlayer("Joe");
		
		nPlayer.setName("Bobby");
		assertEquals("Bobby", nPlayer.getName());
		
		nPlayer.setName("Joey");
		assertEquals("Joey", nPlayer.getName());
		
		nPlayer.setName("Bea");
		assertEquals("Bea", nPlayer.getName());
		
		nPlayer.setName("Jessie");
		assertEquals("Jessie", nPlayer.getName());
	}

	@Test
	//test getScore functionality
	public void testGetScore() 
	{
		NormalPlayer nPlayer = new NormalPlayer("Bob");
		DisadvantagedPlayer dPlayer = new DisadvantagedPlayer("Joe");
		
		assertEquals(0, nPlayer.getScore());
		assertEquals(0, dPlayer.getScore());
		
		nPlayer.updateScore(10);
		assertEquals(10, nPlayer.getScore());
		
		dPlayer.updateScore(15);
		assertEquals(15, dPlayer.getScore());
		
		nPlayer.updateScore(100);
		assertEquals(100, nPlayer.getScore());
		
		dPlayer.updateScore(150);
		assertEquals(150, dPlayer.getScore());
	}

	@Test
	//test updateScore functionality
	public void testUpdateScore() 
	{
		NormalPlayer nPlayer = new NormalPlayer("Bob");
		DisadvantagedPlayer dPlayer = new DisadvantagedPlayer("Joe");
		
		nPlayer.updateScore(10);
		assertEquals(10, nPlayer.getScore());
		
		dPlayer.updateScore(15);
		assertEquals(15, dPlayer.getScore());
		
		nPlayer.updateScore(100);
		assertEquals(100, nPlayer.getScore());
		
		dPlayer.updateScore(150);
		assertEquals(150, dPlayer.getScore());
	}

}

