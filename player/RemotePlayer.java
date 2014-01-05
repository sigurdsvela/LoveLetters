package player;

import game.Game;

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

	@Override
	public void drawCard(Card card) {
		game.getView().println("You drew " + card.getName());
		cards.add(card);
	}

	@Override
	public Player askPlayerForPlayer(String message) {
		return null;
	}

	@Override
	public Player askPlayerForPlayer() {
		return null;
	}

	@Override
	public Card askPlayerForCard(String message) {
		return null;
	}

	@Override
	public Card askPlayerForCard() {
		return null;
	}

	@Override
	public String showCard(Card card) {
		return null;
	}
}
