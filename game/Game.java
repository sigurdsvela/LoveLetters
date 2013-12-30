package game;

import java.util.ArrayList;

import ui.Player;

/**
 * Starts a new Local game
 */
public class Game {
	private ArrayList<Player> players;
	
	public Game() {
		players = new ArrayList<Player>();
	}
	
	public void playerJoin(Player player) {
		players.add(player);
	}
	
	public void start() {
		
	}
}
