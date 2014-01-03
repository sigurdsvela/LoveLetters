package player;

import deck.card.Card;
import game.Game;

public class BotPlayer extends Player{
	public static final int NUM_BOT_NAMES = 7;
	public static final String[] botNames = {"Ola", "Jack", "Jon", "Master", "Vanessa", "Christy", "Emelie", "Botonator"};
	public BotPlayer(String name, Game game) {
		super(name, game);
	}
	@Override
	public Card playCard() {
		int cardToPlayIndex = (int)Math.random()*cards.size();
		return playCard(cardToPlayIndex);
	}
	@Override
	public void drawCard(Card card) {
		cards.add(card);
	}
}
