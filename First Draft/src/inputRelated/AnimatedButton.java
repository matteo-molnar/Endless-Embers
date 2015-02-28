package inputRelated;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

//Source of Code :https://evilzone.org/java/%28java-fames-tut%29-slick2d-buttons-buttons-buttons/
public class AnimatedButton extends MouseOverArea {



	private boolean activated = false;
	 private boolean lastMouseOver = false;
	 private final Animation animation;
	 private final Image inactiveButton;
	 private final Image activeButton;
     private final StateBasedGame sbg;
     private final int stateID;

     private final List <ButtonAction> actions = new ArrayList <ButtonAction>();
 	
	public AnimatedButton(GUIContext container, Animation animation, int x, int y,
			StateBasedGame sbg, int stateID, Image inactiveButton, Image activeButton) throws SlickException {
		 	super(container, inactiveButton, x, y);
	        super.setMouseDownColor(Color.white);
	        super.setMouseOverColor(Color.green);
	        this.animation = animation;
	        this.sbg = sbg;
	        this.stateID = stateID;
	 
	        this.inactiveButton = inactiveButton;
	        this.activeButton = activeButton;
	}
	public void add(ButtonAction action){
		actions.add(action);
	}
	
	
	 @Override
	    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
	        if (sbg.getCurrentStateID() == stateID) {
	            if (isMouseOver() && !lastMouseOver && !isActivated()) {
	                lastMouseOver = true;
	            } else if (!isMouseOver()) {
	                lastMouseOver = false;
	            }
	        }
	        super.mouseMoved(oldx, oldy, newx, newy);
	    }
	 
	    @Override
	    public void render(GUIContext guic, Graphics g) {
	        if (activated) {
	            g.drawImage(activeButton, getX() - 7, getY() - 5);
	            g.drawAnimation(animation, getX() + 2, getY() + 2);
	        } else {
	            g.drawImage(inactiveButton, getX() - 7, getY() - 5);
	            super.render(guic, g);
	        }
	    }
	 
	    public boolean isActivated() {
	        return activated;
	    }
	 
	    protected void setActivated(boolean b) {
	        activated = b;
	    }
	    
	
	    @Override
	    public void mouseClicked(int button, int x, int y, int clickCount) {
	        if (isMouseOver() && sbg.getCurrentStateID() == stateID) {
	            activated = !activated;
	            for (ButtonAction action: actions){
	            	action.perform();
	            }
	        }
	        super.mouseClicked(button, x, y, clickCount);
	    }
	    
	 
}
	
	
