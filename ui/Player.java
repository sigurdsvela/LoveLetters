package ui;

import deck.card.Card;

/**
 */
public abstract class Player {
	/**
	 * Holds the cards of this players hand
	 */
	private Card[] cards;
	
	/**
	 * 
	 */
	private int handSize;
	
	/**
	 * Holds the max amount of cards a player can have
	 */
	public static final int NUMBER_OF_CARDS = 2;
	
	/**
	 * 
	 */
	public Player() {
		cards = new Card[2];
	}
	
	/**
	 * 
	 * @param i
	 */
	protected final void playCard(short i) {
		
	}
	
	
}
