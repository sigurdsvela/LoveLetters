package game.state;

import game.Application;

public abstract class ApplicationState {
	
	private Application application = null;

	/* ABSTRACT METHODS */
	
	/**
	 * Initializes this application state
	 */
	public abstract void init();
	
	/**
	 * Ends this application state
	 */
	public abstract void end();
	
	/* GETTERS AND SETTERS */

	/**
	 * Set what Application that this Application State targets
	 * @param application
	 */
	public final void setApplication(Application application) {
		this.application = application;
	}
	
	/**
	 * Get the target application.
	 * Application is set when this Application state is set to a Application, 
	 * and will be null if it is currently not the active state of any Application.
	 * @return The game, or null;
	 */
	protected final Application application() {
		return application;
	}
}
