package game;

import java.util.ArrayList;

import player.Player;

import deck.card.Card;


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

	@Override
	public Player askPlayerForPlayer(Player player, String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card askPlayerForCard(Player player, String message) {
		// TODO Auto-generated method stub
		return null;
	}
}
