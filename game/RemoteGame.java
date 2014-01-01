package game;

import java.util.ArrayList;

import deck.card.Card;

import ui.Player;

/**
 * Creates a new game that joins a remote game
 */
public class RemoteGame extends Game {
	public RemoteGame(String ip) {
		
	}
	
	public void playerJoin(Player player) {
		super.playerJoin(player);
	}

	@Override
	public void start() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public void gameLoop() {
		
	}

	@Override
	public Player askPlayerForPlayer(Player player) {
		return null;
	}

	@Override
	public Card askPlayerForCard(Player player) {
		return null;
	}
}
