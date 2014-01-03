package ui;

import deck.card.Card;
import game.Game;

/**
 * Creates a new remote player
 * This servers as a representation of the remote player.
 * This is the class that handles the waiting for the response of a player etc.
 */
public class RemotePlayer extends Player{
	public RemotePlayer(String name, Game game) {
		super(name, game);
	}

	@Override
	public Card playCard() {
		return null;
	}
}
