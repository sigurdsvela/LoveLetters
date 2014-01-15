package player;

import java.util.HashMap;
import java.util.Set;

/**
 * A set of player
 * Allows for flagging player as not in this round,
 * turn system etc.
 */
public class PlayerSet {
	HashMap<String, Player> allPlayers = new HashMap<String, Player>();
	HashMap<String, Player> playersInThisRound = new HashMap<String, Player>();
	Set<String> playersSet;
	Set<String> playersInRoundSet;
	
	/**
	 * Points to the index in playersInRoundSet of the current player
	 */
	int currentPlayer;
	
	public PlayerSet() {
		currentPlayer = -1;
	}
	
	/**
	 * Adds a player to this PlayerSet
	 * If a player with that name allready exists, it will return false, if
	 * not, and we succeed, it will return true.
	 * @param player The player to add
	 * @return
	 */
	public boolean addPlayer(Player player) {
		if (playerExists(player.getName())) return false;
		allPlayers.put(player.getName(), player);
		playersSet = allPlayers.keySet();
		return true;
	}
	
	public boolean playerExists(String name) {
		return allPlayers.containsKey(name);
	}
	
	private void updateSets() {
		playersInThisRound.clear();
		playersSet = allPlayers.keySet();
		for (String name : playersSet) {
			if (allPlayers.get(name).isPlayerInThisRound()) {
				playersInThisRound.put(name, allPlayers.get(name));
			}
		}
		playersInRoundSet = playersInThisRound.keySet();
	}
	
	/**
	 * Returns the current player, if there is none,
	 * it will return null.
	 * @return
	 */
	public Player currentPlayer() {
		if (currentPlayer == -1) {
			return null;
		} else {
			return allPlayers.get(playersInRoundSet.)
		}
	}
	
}
