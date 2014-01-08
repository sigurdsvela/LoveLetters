package game.state;

import game.Game;

public abstract class GameState {
	private Game game = null;
	public GameState() {
		
	}
	
	/**
	 * Set what game that this GameState targets
	 * @param game
	 */
	public final void setGame(Game game) {
		this.game = game;
	}
	
	/**
	 * Get the target game.
	 * Game is set when the gamestate is set to a game, and will be null if
	 * it is currently not the active state of any Game.
	 * @return The game, or null;
	 */
	protected final Game game() {
		return game;
	}
	
	public abstract void init();
	public abstract void end();
}
