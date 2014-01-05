package game;

import java.util.ArrayList;
import java.util.LinkedList;

import player.Player;

import view.View;
import deck.Deck;
import deck.card.Card;

public abstract class Game {
	protected ArrayList<Player> players;
	protected Card removedAtStart;
	protected int currentPlayerIndex;
	protected int lettersDeliveredToWin;
	protected boolean started;
	protected Deck deck;
	protected View view;
	protected GameState gameState;
	
	/**
	 *  Enum used for deciding which part of the game is running
	 */
	protected enum GameState {
		MENU, GAME, EXIT
	}
	
	public Game() {
		started = false;
		lettersDeliveredToWin = 4;
	}
	
	/* ABSTRACT METHODS */
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
	
	/* OTHER METHODS */
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
	 * 					OR the same player as current if only one player left in round	
	 * 					OR null is returned if no players are left in round (this is an error)
	 */
	public Player nextPlayer() {
		int numPlayersInRound = getNumPlayersInRound();
		
		// Return null or current player if number of players in round is 0 or 1
		if (numPlayersInRound == 0) return null;
		else if (numPlayersInRound == 1) return players.get(currentPlayerIndex);
		
		// Update currentIndex pointer; increment by one
		// AND set it to 0 if we exceeded number of players in game.
		// (This is creating a loop abstraction to the linkedlist)
		if (currentPlayerIndex++ >= getNumPlayers() - 1) currentPlayerIndex = 0;	
		
		// Return player if it's still active in this round
		if( players.get(currentPlayerIndex).isPlayerInThisRound() ) return players.get(currentPlayerIndex);
		// else call nextPlayer() recursive.
		else return nextPlayer();
	}
	
	/**
	 * Will return number of protected
	 * players in active round.
	 * @return int	is the number of protected players
	 */
	public int getNumProtectedPlayersInRound() {
		int cnt = 0;
		for (Player p : players) {
			if (p.isPlayerProtected() && p.isPlayerInThisRound()) cnt++;
		}
		return cnt;
	}
	
	/**
	 * Will return players in active round
	 * that is protected.
	 * @return Player[] protected players in round
	 */
	public Player[] getProtectedPlayersInThisRound() {
		ArrayList<Player> playersProtectedInThisRound = new ArrayList<Player>();
		for (Player p : players) {
			if (p.isPlayerInThisRound() && p.isPlayerProtected()) {
				playersProtectedInThisRound.add(p);
			}
		}
		return playersProtectedInThisRound.toArray( new Player[ playersProtectedInThisRound.size() ] ); 
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
	 * Will return an array of the winners of a round
	 * @return Player[]	is the winners of a round
	 * 			OR null if no players left in round (error)
	 */
	public Player[] getWinners() {
		Player[] playersInRound = getPlayersInThisRound();
		ArrayList<Player> winners = new ArrayList<Player>();
		
		if (playersInRound.length == 0) return null; //  Return null if there are no players left in round (error)
 		
		// Get biggest distance value among players
		int biggestDistance = -1, tmpDistance;
		for (Player p : playersInRound) {
			tmpDistance = p.getCard(0).getDistance();
			if (tmpDistance > biggestDistance) {
				biggestDistance = tmpDistance;
			}
		}
		
		// Add all players onto winners array with distance equal to biggest
		for (Player tmp : playersInRound) {
			if (tmp.getCard(0).getDistance() == biggestDistance) {
				winners.add(tmp);
			}
		}
		
		// Convert back from linked list to Player array and return it
		return winners.toArray( new Player[ winners.size() ] );
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
	 * Will return view of the game
	 * For localGame this is the local players view,
	 * but for remoteGame it's the remote player using that
	 * remoteGames view.
	 * @return View
	 */
	public final View getView() {
		return view;
	}
	
	/**
	 * Will return current deck of game
	 * @return Deck	is the deck being used in game
	 */
	public Deck getDeck() {
		return deck;
	}
	
	/**
	 * Get the card that was set aside at start
	 * @return Das card
	 */
	public Card getRemovedAtStart() {
		return removedAtStart;
	}
}
