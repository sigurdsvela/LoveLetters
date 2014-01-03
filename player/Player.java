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
	private Game game;
	
	/**
	 * Holds the cards of this players hand
	 */
	private LinkedList<Card> cards;
	
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
	 * Plays one of the cards on this players hand.
	 * i is the index of the card to play.
	 * If the player has 2 cards, which it should when playing a card
	 * and this method is called with 0 as i, the other card on the player hand
	 * will get a new index of 0.
	 * This is because the list of cards on the players hand is a LinkedList, so when
	 * a card is removed from the players hand, the list "collapses upon itself"
	 * 
	 * @param i The card to play
	 * 
	 * @throws IndexOutOfBoundsException If the card does not exist
	 */
	public final void playCard(short i) {
		cards.get(i).triggerPlay(game, this);
		cards.remove(i);
	}
	
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
		for (Card card : cards) {
			String theCardName = card.getName();
			if (theCardName.compareTo(cardName) == 0) {
				return true;
			}
		}
		return false;
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
	public final void drawCard(Card card) {
		card.triggerCardWasDrawn(game, this);
		if (cards.size() > 0) {
			cards.get(0).triggerPlayerDrewCard(game, this);
		}
		cards.add(card);
	}
	
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
