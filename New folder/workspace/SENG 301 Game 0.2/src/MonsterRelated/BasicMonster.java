package MonsterRelated;

import java.util.Random;

import mapRelated.BasicMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class BasicMonster extends Creature{
	private boolean isActiveState;
	private char name;
	private Image monster;
	
	public char getName(){return name;}
	
	public BasicMonster(BasicMap map, Image monster)
	{
		super.x = 3;
		super.y = 5;
		super.name = 'm';
		this.monster = monster;
	}
	
	public boolean getMonsterState(){return isActiveState;}
	
	public void setMonsterState(boolean foundPlayer){
		isActiveState = foundPlayer;
	}
	
	public void render(Graphics g){
		g.drawImage(monster, (int)x*32, (int)y*32);
	}
	
	public void update(int [] playerPosition)
	{
		if (search('P'))
			isActiveState = true;
		else 
			isActiveState = false;
		
		if (isActiveState){
			x = findClosestSpot(getPosition(), playerPosition)[0];
			y = findClosestSpot(getPosition(),playerPosition)[1];
			super.setPosition(playerPosition, 'P');
		}
		else
			wander(playerPosition, 'P');
	}
	
	private void wander(int [] playerPosition, char otherName){
	Random gen = new Random();
	x = gen.nextInt(x+1 - (x-1))+x-1;
	y = gen.nextInt(y+1 - (y-1))+y-1;
	super.setPosition(playerPosition, 'P');
	}
	
	
	public int[] findClosestSpot(int[]current, int[] player)
	{
		Random gen = new Random();
		int newY = gen.nextInt(y+1-(y-1))+y-1;
		int newX = gen.nextInt(x+1-(x-1))+x-1;
		if (current[0] == player[0]&& current[1]!= player[1]){
			while(!isTaken(x, newY))
			{	
				newX = x;
			}
		}
			
		else if (current[1] == player[1]&& current[0] != player[0])
		{
			while(!isTaken(newX, y))
			{
				newY = y;
				newX = gen.nextInt(x+1-(x-1))+x-1;			}
		}
		
		else if (current[1]!= player[1]&& current[0]!= player[0])
		{
			while(!isTaken(x, newY))
			{	
				newX = gen.nextInt(x+1-(x-1))+x-1;
				newY = gen.nextInt(y+1-(y-1))+y-1;	
			}
		}
		int [] newPosition = {newX,newY};
		return newPosition;
	}

	
}
