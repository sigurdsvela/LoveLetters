package player;

import game.Game;

import java.util.LinkedList;

import view.View;

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
	protected int lettersDelivered;
	
	/**
	 * Is this player in on this round
	 */
	protected boolean inThisRound;
	
	/**
	 * This players name
	 */
	protected String name;
	
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
	
	/* ABSTRACT METHODS */
	/**
	 * Will call this players game.view with information
	 * @param information
	 */
	//public abstract void setInformation(String information);
	
	/**
	/* Play a card from players hand
	 * This method is implemented in individually in all subclasses
	 */
	public abstract Card playCard();

	/**
	 * Draw a card for the player.
	 * @param card	is card to be drawn
	 */
	public void drawCard(Card card) {
		getGame().getView().setInformation(getName() + " drew: " + showCard(card));
		cards.add(card);
	}
	
	/** 
	 * Show a card for the player
	 * @param card	the card to be shown
	 * @return String	is the card to string 
	 */
	public abstract String showCard(Card card);
	
	/**
	 * Show cards in players hand
	 */
	public abstract Player askPlayerForPlayer(String message);
	public abstract Player askPlayerForPlayer();
	public abstract Card askPlayerForCard(String message);
	public abstract Card askPlayerForCard();

	/**
	 * Will print out the cards in players hand.
	 * If useIndividual is true, it will print the cards
	 * based on the subclasses showCard() method.
	 * 
	 * @param useIndividual		is a boolean flag
	 */
	public void showCards(boolean useIndividual) {
		View view = getGame().getView();
		view.setInformation(getName() + "'s hand:");
		for (Card card : cards) {
			if (useIndividual) {
				view.setInformation(showCard(card));
			} else {
				view.setInformation(card.toString()); 
			}
		}
		view.setInformation("");
	}
	
	/**
	 * Will be called from playCard();
	 * Actually play the card, trigger its effect if any and remove it from hand.
	 * @param cardIndex
	 * @return Card	that was removed from hand
	 */
	protected Card playCard(int cardIndex) {
		Card card = cards.remove(cardIndex);
		getGame().getView().setInformation((getName() + " played " + card.toString()));
		card.triggerPlay(game, this);
		return card;
	}
	
	/**
	 * Player discards card at index i
	 * @param i is the indexed card to be discarded
	 * @return Card	is the discarded card
	 */
	public final Card discardCard(int i) {
		// Return null if cards is empty or i >= cards size (error)
		if (cards.isEmpty() || i >= cards.size()) return null;
		cards.get(i).triggerDiscard(game, this);
		return cards.remove(i);
	}
	
	/**
	 * Will empty the hand of the player
	 * @param showFirst	will reveal hand before emptying it.
	 */
	public final void emptyHand(boolean showFirst) {
		if (showFirst) {
			showCards(false);
		}
		
		cards.clear();
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
	 * Return card in hand with biggest distance
	 */
	public final Card getBiggestDistanceCard() {
		Card biggestDistance = cards.getFirst();
		for (Card c : cards) {
			if (c.getDistance() > biggestDistance.getDistance()) {
				biggestDistance = c;
			}
		}
		return biggestDistance;
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
			if (theCardName.compareToIgnoreCase(cardName) == 0) {
				return card;
			}
		}
		return null;
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
		
	/**
	 * Returns the index of the card name passed in.
	 * -1 if the card was not found
	 * @param cardName The card to find
	 * @return
	 */
	public final int getCardIndex(String cardName) {
		for (int i = 0; i < cards.size(); i++) {
			String theCardName = cards.get(i).getName();
			if (theCardName.compareToIgnoreCase(cardName) == 0) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Get all cards in players hand
	 * @return card[] is the players hand
	 */
	public final Card[] getCards() {
		Card[] toArray = new Card[ cards.size() ];
		return cards.toArray(toArray);
	}
 	
	/**
	 * Check if the player has a specific card
	 * 
	 * @param card The card to check if the player has
	 * @return true if card was found, false else
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
	 * Get this players game
	 * @return Game
	 */
	public final Game getGame() {
		return game;
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
