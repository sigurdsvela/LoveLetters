package game;

import java.util.ArrayList;

import ui.Player;

/**
 * Starts a new Local game
 */
public class Game {
	private ArrayList<Player> players;
	private boolean started;
	
	public Game() {
		started = false;
		players = new ArrayList<Player>();
	}
	
	/**
	 * Join a player. This can only be done if the game
	 * has not already started
	 * @param player
	 */
	public void playerJoin(Player player) {
		if (hasStarted()) return;
		players.add(player);
	}
	
	public boolean hasStarted() {
		return started;
	}
	
	/**
	 * Start the game
	 */
	public void start() {
		if (hasStarted()) return;
		started = true;
	}
}
