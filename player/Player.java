package player;

import game.state.Game;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import view.PlayerView;
import view.View;
import deck.card.Card;

/**
 */
public abstract class Player implements Comparable<Player> {
	
	/**
	 * Holds the view to represent this player on screen
	 */
	protected PlayerView playerView;
	
	/**
	 * Holds the game this player is in
	 */
	protected Game game;
	
	/**
	 * Holds the cards of this players hand
	 */
	protected LinkedList<Card> cards;
	
	/**
	 * Holds the index of a card to be forced to play if any
	 * -1 indicate no force, other is the index in cards to force play
	 */
	protected int forceCardIndex;
	
	/**
	 * Holds the info of wether this player
	 * is protected against cards abilities or not.
	 */
	protected boolean isProtected;
	
	/**
	 * Delivered
	 */
	protected int lettersDelivered;
	
	/**
	 * Is this player in on this round
	 */
	protected boolean inThisRound;
	
	private boolean isThisPlayersTurn;
	
	/**
	 * The index of the card that this player want to play
	 */
	private int cardToPlay;
	
	private ArrayList<ActionListener> turnDoneListeners;
	
	/**
	 * This players name
	 */
	protected String name;
	
	public Player(String name, Game game) {
		this.name = name;
		this.game = game;
		playerView = new PlayerView(name, 100, 100);
		playerView.setBackgroundColor(Color.CYAN);
		cards = new LinkedList<Card>();
		lettersDelivered = 0;
		forceCardIndex = -1;
		inThisRound = true;
		cardToPlay = -1;
		turnDoneListeners = new ArrayList<ActionListener>();
	}
	
	/**
	 * Notify this player that it should do its turn.
	 */
	public void doTurn() {
		System.out.println("Do turn:" + getName());
	}
	
	/**
	 * Fires the turnDoneListeners.
	 * And resets the players chosen card
	 */
	protected void turnDone() {
		System.out.println("Turn done: " + getName());
		for (ActionListener listener : turnDoneListeners) {
			listener.actionPerformed(null);
		}
	}
	
	/* ABSTRACT METHODS */
	
	public abstract Player askPlayerForPlayer(String message);
	public abstract Player askPlayerForPlayer();
	public abstract Card askPlayerForCard(String message);
	public abstract Card askPlayerForCard();

	/* OTHER METHODS */
	
	public String[] getCardsAsStrings() {
		String[] a = new String[cards.size()];
		for (int i = 0; i < cards.size(); i++) {
			a[i] = cards.get(i).getName();
		}
		return a;
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
	 * Draw a card for the player.
	 * @param card	is card to be drawn
	 */
	public void drawCard(Card card) {
		addCard(card);
		
		// Trigger effect of "on drawn rule" for card AND player "drew a card" for cards in players hand.
		card.triggerCardWasDrawn(game, this);
		for (Card c : getCards()) {
			c.triggerPlayerDrewCard(game, this);
		}
	}
	
	/**
	 * Basically the same as drawCard except
	 * it does not call the trigger method nor print anything.
	 * @param card	to add to hand
	 */
	public void addCard(Card card) {
		cards.add(card);
		playerView.addSubView(card.getView());
		updateLayout();
	}
	
	/**
	 * Update the positioning of the card. This must be done whenever you
	 * add card to 
	 */
	private void updateLayout() {
		int width;
		int topPadding = 50; //TODO HardCoding is bad coding
		
		if (cards.size() > 0) {
			width = (int)cards.get(0).getView().getWidth();
			playerView.setWidth(cards.size() * width);
			playerView.setHeight(topPadding + cards.get(0).getView().getHeight());
			for (int i = 0; i < cards.size(); i++) {
				cards.get(i).getView().setX(i * width);
				cards.get(i).getView().setY(topPadding);
			}
		} else {
			playerView.setWidth(0); //Will be corrected my minHeight
			playerView.setHeight(0); //Will be corrected my minHeight
		}
		
	}
	
	/**
	 * Will be called from playCard();
	 * Actually play the card at cardIndex, trigger its effect if any and remove it from hand.
	 * UNLESS the forceCardIndex is set, then play this card instead.
	 * @param cardIndex
	 * @return Card	that was removed from hand
	 */
	protected Card playCard(int cardIndex) {
		Card card = cards.remove(cardIndex);
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
			//Implement show cards before emptying the hand
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
	 * Check if the player has a specific card
	 * 
	 * @param card The card to check if the player has
	 * @return true if card was found, false else
	 */
	public final boolean hasCard(Card card) {
		return hasCard(card.getName());
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
	
	public final int getForceCardIndex() {
		return forceCardIndex;
	}
	
	public final void setForceCardIndex(int index) {
		forceCardIndex = index;
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
	
	public final boolean isPlayerProtected() {
		return isProtected;
	}
	
	public final void setIsPlayerProtected(boolean b) {
		isProtected = b;
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
	
	public PlayerView getPlayerView() {
		return playerView;
	}

	public void setPlayerView(PlayerView playerView) {
		this.playerView = playerView;
	}

	@Override
	public int compareTo(Player other) {
		return name.compareToIgnoreCase(other.getName());
	}
	
	/**
	 * Get the card that this player want to play, and remove it
	 * from its hand.
	 * If the player has not chosen a card yet, no cards will be removed
	 * and the method will return null.
	 * @return
	 */
	public Card popCardToPlay() {
		if (cardToPlay == -1) return null;
		Card card = cards.get(cardToPlay);
		playerView.setCardStackCard(card);
		cards.remove(cardToPlay);
		cardToPlay = -1;
		playerView.removeSubView(card.getView());
		updateLayout();
		return card;
	}
	
	/**
	 * returns the card that the player would like to play
	 * if the player has not chosen a card, it will return null
	 * @return
	 */
	protected Card getCardToPlay() {
		if (cardToPlay == -1) return null;
		return cards.get(cardToPlay);
	}
	
	protected void setCardToPlay(int cardToPlay) {
		this.cardToPlay = cardToPlay;
	}
	
	public void addTurnDoneListener(ActionListener a) {
		turnDoneListeners.add(a);
	}
}
