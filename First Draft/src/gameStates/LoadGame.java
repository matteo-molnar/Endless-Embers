package gameStates;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LoadGame extends BasicGameState{

	//Temp Loading Screen to differentiate states
	private Animation loadingScreen;
	private StateBasedGame game;
	private GameContainer gc;
	//Temp I.D
	public static final int ID = 2;
	
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame)
			throws SlickException {
		this.gc = gc;
		this.game = stateGame;//Gets the State game as a class variable for later use.
		Image []  loadingImages = {new Image("res/interface/Sleep3.png"), //Loads first image of the animation
								   new Image("res/interface/Sleep2.png"), //Loads second image of the animation
				                   new Image ("res/interface/Sleep1.png")};//Loads third image of the animation
		int [] duration = {400,400,700}; //400 ms for the first image, 400 ms for the second etc...
		loadingScreen = new Animation (loadingImages, duration,false);//Creates the animation.
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics g)
			throws SlickException {
		g.drawString("LOADING GAME...", 100, 50);//Draws a string to screen
		g.drawString("Press 'Q' to quit", 100, 0);//Ditto
		loadingScreen.draw( 100, 100);//Draws the animation to screen

	}

	public void keyReleased (int key, char c){
	switch (key){//Given this key it will go through the cases, comparing them and reacts like so
	case Input.KEY_Q:
		gc.exit();//Exits game. 
		break;//Needed for switch-cases to differentiate different cases
		}	
	}


	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
		loadingScreen.update(delta);//Updates the loading screen as this state constantly updates.
	}

	@Override
	public int getID() {
		return ID;//Returns State ID.
	}

}