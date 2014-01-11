package game.state;

import java.util.ArrayList;

import player.Player;
import view.ChatPanel;
import view.GamePanel;
import view.Window;
import deck.Deck;
import deck.card.Card;

public abstract class Game extends ApplicationState {
	
	/**
	 * The game panel used for this game
	 */
	protected GamePanel gamePanel;
	
	/**
	 * The chat panel used for this game
	 */
	protected ChatPanel chatPanel;
	
	/**
	 * The game window used for this game
	 */
	protected Window gameWindow;
	
	/**
	 * Holds the players in this game
	 */
	protected ArrayList<Player> players;
	
	/**
	 * The deck used in this game
	 */
	protected Deck deck;
	
	/**
	 * The card removed at start of each round
	 */
	protected Card removedAtStart;
	
	/**
	 * Holds the index of current players turn in players array
	 */
	protected int currentPlayerIndex;
	
	/**
	 * Number of letters required to win this game
	 */
	protected int numLettersToWin;
	
	public Game() {
		players = new ArrayList<Player>();
		numLettersToWin = 4;
	}
	
	/* ABSTRACT METHODS */
	
	/**
	 * Join a player. This can only be done if the game
	 * has not already started or if a player with players name
	 * already exist in game.
	 * @param player
	 * @return true if player is added, false otherwise
	 */
	public abstract boolean playerJoin(Player player);
	
	/* GAME METHODS */
	
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
		else if (numPlayersInRound == 1) return getPlayersInThisRound()[0];
		
		// Update currentIndex pointer; increment by one
		// AND set it to 0 if we exceeded number of players in game.
		// (This is creating a loop abstraction to the linkedlist)
		if (currentPlayerIndex++ >= getNumPlayers() - 1) currentPlayerIndex = 0;	
		
		// Return player if it's still active in this round
		if( players.get(currentPlayerIndex).isPlayerInThisRound() ) return players.get(currentPlayerIndex);
		// else call nextPlayer() recursive.
		else return nextPlayer();
	}
	
	/* CUSTOM GETTERS AND SETTERS */
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
	 * Will return number of players in game
	 * @return int	number of players in game
	 */
	public int getNumPlayers() {
		return players.size();
	}
	
	/**
	 * Will return players in game
	 * @return Player[] players in game
	 */
	public Player[] getPlayers() {
		return players.toArray( new Player[ players.size() ] );
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
	public Player[] getProtectedPlayersInRound() {
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

	/* STANDARD GETTERS AND SETTERS */
	
	public Card getRemovedAtStart() {
		return removedAtStart;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public ChatPanel getChatPanel() {
		return chatPanel;
	}

	public void setChatPanel(ChatPanel chatPanel) {
		this.chatPanel = chatPanel;
	}

	public Window getGameWindow() {
		return gameWindow;
	}

	public void setGameWindow(Window gameWindow) {
		this.gameWindow = gameWindow;
	}
	
}
