package game;

import game.state.ApplicationState;
import view.Window;

public abstract class Application {

	/**
	 * Holds the name of the player who is using
	 * the machine this application runs on
	 */
	protected String localHostPlayerName;
	
	/**
	 * Holds the current state of this application
	 */
	private ApplicationState state;
	
	/**
	 * Holds the window this application is using
	 */
	private Window window;
	
	public Application() {
		window = new Window();
	}
	
	/* CUSTOM GETTERS AND SETTERS */
	public Window window() {
		return window;
	}
	
	/**
	 * Set this Applications game state to the given game state.
	 * Will call the current game states end method and the new
	 * game states init method. 
	 * 
	 * @param gameState	is the new game state
	 */
	public void setApplicationState(ApplicationState gameState) {
		if (state != null) { //Did we have a state before?
			state.end();
			state.setApplication(null);
		}
		
		state = gameState;
		state.setApplication(this);
		window.getContentPane().removeAll();
		state.init();
	}

	/* STANDARD GETTERS AND SETTERS */
	public String getLocalHostPlayerName() {
		return localHostPlayerName;
	}

	public void setLocalHostPlayerName(String localHostPlayerName) {
		this.localHostPlayerName = localHostPlayerName;
	}
}
