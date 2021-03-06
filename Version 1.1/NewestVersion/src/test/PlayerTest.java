package test;

import static org.junit.Assert.*;


import mapRelated.BasicMap;

import org.junit.Test;

import playerRelated.Player;

public class PlayerTest {


	
	@Test
	public void testExperiencePoints(){
		Player player = new Player (10, 10);
		player.addExperiencePoints(10);
		assertEquals(0, player.getExperiencePoints());
		assertEquals(20,player.getPointsNextLevel());
		player.addExperiencePoints(20);
		assertEquals(40,player.getPointsNextLevel());
		player.addExperiencePoints(40);
		assertEquals(80,player.getPointsNextLevel());
		player.addExperiencePoints(80);
		assertEquals(160,player.getPointsNextLevel());
		player.addExperiencePoints(160);
		assertEquals(320,player.getPointsNextLevel());	
		player.addExperiencePoints(320);
		assertEquals(640,player.getPointsNextLevel());	
		assertEquals(7, player.getCurrentLevel());
		
	}

	@Test
	public void testNegativeExperiencePoints(){
		//Arrange
		Player player = new Player (10,10);		
		assertEquals("Can't gain negative EXP",player.addExperiencePoints(-100));
	}
	@Test
	public void test_NoLevelUp() {
		Player player = new Player(10,10);
		assertEquals(null, player.addExperiencePoints(1));
	}
	
	
	@Test 
	public void testPlayerLevelUp(){
		Player player = new Player (10,10);		
		assertEquals("Player has leveled up",player.addExperiencePoints(2000));
	}
	
	@Test
	public void testPlayerMovement(){
		
		//Mock keyboard was call upon private method player movement
		//The private methods would've originally been called depending on input from the keyboard
		//In it's place characters have been used to individually call each method.
		//Reflections (Private Objects for Java) proved to carry too many complications than it was worth.
		
		String [][] testArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		char [][] testMap = new char [BasicMap.widthByTiles][BasicMap.heightByTiles];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap [i][c] = ' ';
			}
		}
		BasicMap map = new BasicMap(testMap);
		Player p = new Player(4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, map);
		p.setEntityArray(testArray);
		int [] expected = {3*BasicMap.TILESIZE, 5*BasicMap.TILESIZE};
		p.mockKeyBoard('l');
		assertArrayEquals(expected, p.getPosition());
		p.mockKeyBoard('r');
		expected [0] = 4*BasicMap.TILESIZE;
		assertArrayEquals(expected, p.getPosition());
		expected [1] = 6*BasicMap.TILESIZE;
		p.mockKeyBoard('d');
		assertArrayEquals(expected, p.getPosition());
		expected [1] = 5*BasicMap.TILESIZE;
		p.mockKeyBoard('u');
		assertArrayEquals(expected, p.getPosition());
		
		p.mockKeyBoard('a');
		expected [0] -= BasicMap.TILESIZE;
		expected [1] -= BasicMap.TILESIZE;
		assertArrayEquals(expected, p.getPosition());
		
		p.mockKeyBoard('b');
		expected [0] += BasicMap.TILESIZE;
		expected [1] -= BasicMap.TILESIZE;
		assertArrayEquals(expected, p.getPosition());
		
		p.mockKeyBoard('c');
		expected [0] -= BasicMap.TILESIZE;
		expected [1] += BasicMap.TILESIZE;
		assertArrayEquals(expected, p.getPosition());
		
		
		p.mockKeyBoard('f');
		expected [0] += BasicMap.TILESIZE;
		expected [1] += BasicMap.TILESIZE;
		
		assertArrayEquals(expected, p.getPosition());
		p.mockKeyBoard('y');
		assertArrayEquals(expected, p.getPosition());
		p.mockKeyBoard('g');
		assertArrayEquals(expected, p.getPosition());		
	}
	
	/*
	@Test
	public void testPlayerMoveUp() throws SecurityException, NoSuchMethodException, IllegalAccessException,InvocationTargetException{
		Player p = new Player(4*BasicMap.TILESIZE,5*BasicMap.TILESIZE);

		Method privateUpMethod = Player.class.getDeclaredMethod("moveUp", String.class);

		privateUpMethod.setAccessible(true);

	    privateUpMethod.invoke(p, null);

	}
	*/	
		
		
}

