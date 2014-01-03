package player;

import game.Game;

import java.util.LinkedList;

import deck.card.Card;

/**
 */
public abstract class Player {
	
	/**
	 * Holds the game this player is in
	 */
	protected Game game;
	
	/**
	 * Holds the cards of this players hand
	 */
	protected LinkedList<Card> cards;
	
	/**
	 * Delivered
	 */
	private int lettersDelivered;
	
	/**
	 * Is this player in on this round
	 */
	private boolean inThisRound;
	
	/**
	 * This players name
	 */
	private String name;
	
	/**
	 * Holds the max amount of cards a player can have
	 */
	public static final int NUMBER_OF_CARDS = 2;
	
	/**
	 * 
	 */
	public Player(String name, Game game) {
		cards = new LinkedList<Card>();
		this.name = name;
		this.game = game;
		lettersDelivered = 0;
		inThisRound = true;
	}
	
	/**
	/* Play a card from players hand
	 */
	public abstract Card playCard();

	/**
	 * Player discards card <b>i</b>
	 * This usually means that the card does nothing
	 * @param i
	 * 
	 * @throws IndexOutOfBoundsException
	 */
	public final void discardCard(short i) {
		cards.get(i).triggerDiscard(game, this);
		cards.remove(i);
	}
	
	/**
	 * Check if the player has a specific card
	 * 
	 * @param cardName The name of the card to check if the player has
	 * @return Do you really need an explanation expect boolean????
	 */
	public final boolean hasCard(String cardName) {
		return (getCard(cardName) != null);
	}
	
	/**
	 * Returns the card object of the card name passed in.
	 * Null if the player does not have that card.
	 * @param cardName
	 * @return
	 */
	public final Card getCard(String cardName) {
		for (Card card : cards) {
			String theCardName = card.getName();
			if (theCardName.compareTo(cardName) == 0) {
				return card;
			}
		}
		return null;
	}
	
	/**
	 * Check if the player has a specific card
	 * 
	 * @param card The card to check if the player has
	 * @return
	 */
	public final boolean hasCard(Card card) {
		return hasCard(card.getName());
	}
	
	/**
	 * Get this players name
	 * @return The name
	 */
	public final String getName() {
		return name;
	}
	
	/**
	 * Make a player draw a card
	 * @param card
	 */
	public abstract void drawCard(Card card);

	/**
	 * Returns a card based on index.
	 * Card are stored in a FIFO list,
	 * so oldest card i stored first.
	 * 
	 * Returns null if the card is nor found
	 * @return Card
	 */
	public final Card getCard(int index) {
		if (index >= cards.size() || index < 0) return null;
		else return cards.get(index);
	}
	
	public final Card[] getCards() {
		Card[] toArray = new Card[ cards.size() ];
		return cards.toArray(toArray);
	}
 	
	public final int getLettersDelivired() {
		return lettersDelivered;
	}
	
	public final void incrementLettersDelivired() {
		lettersDelivered++;
	}
	
	public final void decrementLettersDelivered() {
		lettersDelivered--;
	}
	
	public final boolean isPlayerInThisRound() {
		return inThisRound;
	}
	
	public final void setIsPlayerInThisRound(boolean b) {
		inThisRound = b;
	}
	
	
}
