package game;

import java.util.ArrayList;

import player.Player;

import view.View;
import deck.Deck;
import deck.card.Card;

/**
 * Starts a new Local game
 */
public abstract class Game {
	protected ArrayList<Player> players;
	protected Card removedAtStart;
	protected int currentPlayerIndex;
	protected int lettersDeliveredToWin;
	protected boolean started;
	protected Deck deck;
	protected View view;
	
	public Game() {
		started = false;
		lettersDeliveredToWin = 4;
		
		// Initialize players and a deck. 
		players = new ArrayList<Player>();
		deck = new Deck();
		deck.shuffle(10000);
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
	 * Will update currentPlayerIndex and return next player
	 * @return Player	is the next player in the player list
	 */
	public Player nextPlayer() {
		if (currentPlayerIndex++ >= getNumPlayersInRound() - 1) currentPlayerIndex = 0;	
		if( !players.get(currentPlayerIndex).isPlayerInThisRound() ) return nextPlayer();
		else return players.get(currentPlayerIndex);
	}
	
	/**
	 * Will return number of players still
	 * active in current round of game.
	 * @return int	number of active players in round
	 */
	public int getNumPlayersInRound() {
		int cnt = 0;
		for (Player p : players) {
			if (p.isPlayerInThisRound()) cnt++;
		}
		return cnt;	
	}
	
	/**
	 * Will return number of players in game,
	 * wether or not active in current round.
	 * @return int	number of players in game
	 */
	public int getNumPlayers() {
		return players.size();
	}
	
	public abstract Player askPlayerForPlayer(Player player, String message);
	public abstract Player askPlayerForPlayer(Player player);
	public abstract Card askPlayerForCard(Player player, String message);
	public abstract Card askPlayerForCard(Player player);
	
	public abstract void start();
	public abstract void init();
	public abstract void gameLoop();
}
