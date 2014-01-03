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
	protected GameState gameState;
	
	protected enum GameState {
		Menu, Game, Exit
	}
	
	public Game() {
		started = false;
		lettersDeliveredToWin = 4;
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
	 * Will return current deck of game
	 * @return Deck	is the deck being used in game
	 */
	public Deck getDeck() {
		return deck;
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
	 * Will return players still
	 * active in current round of game.
	 * @return Player[] active players in round
	 */
	public Player[] getPlayersInThisRound() {
		ArrayList<Player> playersInThisRound = new ArrayList<Player>();
		for (Player p: players) {
			if (p.isPlayerInThisRound()) {
				playersInThisRound.add(p);
			}
		}
		return playersInThisRound.toArray( new Player[ playersInThisRound.size() ] );
	}
	
	/**
	 * Get a player based on name
	 * Returns null if no player with the name <i>name<i> is found
	 * 
	 * @param name
	 * @return
	 */
	public Player getPlayer(String name) {
		for (Player p: players) {
			if (p.getName().compareToIgnoreCase(name) == 0) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Will return number of players in game,
	 * wether or not active in current round.
	 * @return int	number of players in game
	 */
	public int getNumPlayers() {
		return players.size();
	}
	
	/**
	 * Will return the winner of a round.
	 * @return Player	is the winner of round
	 */
	public Player getWinner() {
		Player winner = nextPlayer(); // Just use one player not out of round 
		for (Player tmp : players) {
			if( tmp.isPlayerInThisRound() 
				&& tmp.getCard(0).getDistance() > winner.getCard(0).getDistance()) {
					winner = tmp;
			}
		}
		return winner;
	}
	
	public final View getView() {
		return view;
	}
	
	/**
	 * For initializing a game, 
	 * will be called from constructor of subclasses
	 */
	public abstract void init();
	
	/**
	 * For starting a game.
	 * Will call gameLoop() at end of function
	 */
	public abstract void start();
	
	/**
	 * This is the where the game logic appear.
	 */
	public abstract void gameLoop();
}
