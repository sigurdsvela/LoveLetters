package deck;

import java.util.LinkedList;

import deck.card.Baron;
import deck.card.Card;
import deck.card.Countess;
import deck.card.Guard;
import deck.card.Handmaid;
import deck.card.King;
import deck.card.Priest;
import deck.card.Prince;
import deck.card.Princess;
import deck.card.custom.Jester;

public class Deck {
	private LinkedList<Card> cards;
	private final Card[] typesOfCards = { new Guard(), new Priest(), new Baron(), new Handmaid(), new Prince(),
						new King(), new Countess(), new Princess(), new Jester() };
	
	public Deck() {
		//Initialize the card array
		cards = new LinkedList<Card>();
		cards.add(new deck.card.Guard());
		cards.add(new deck.card.Guard());
		cards.add(new deck.card.Guard());
		cards.add(new deck.card.Guard());
		cards.add(new deck.card.Guard());
		cards.add(new deck.card.Priest());
		cards.add(new deck.card.Priest());
		cards.add(new deck.card.Baron());
		cards.add(new deck.card.Baron());
		cards.add(new deck.card.Handmaid());
		cards.add(new deck.card.Handmaid());
		cards.add(new deck.card.Prince());
		cards.add(new deck.card.Prince());
		cards.add(new deck.card.King());
		cards.add(new deck.card.Countess());
		cards.add(new deck.card.Princess());
		cards.add(new deck.card.custom.Jester());
	}
	
	/**
	 * Shuffle a deck by looping n times
	 * and for each time swapping place of two cards
	 */
	public void shuffle() {
		for (int i = 0; i < cards.size(); i++) {
			int j = (int) ( (i+1) * Math.random() );
			Card temp = cards.get(i);
			cards.set(i, cards.get(j));
			cards.set(j, temp);
		}
	}

	/**
	 * @deprecated Did not produce a uniform shuffer, the new one does, and does not require
	 *	an int
	 */
	@Deprecated
	public void shuffle(int i) {
		shuffle();
	}
	
	
	/**
	 * Deal the next card in the deck
	 * or return null if none left
	 */
	public Card draw() {
		if (cards.isEmpty()) return null;
		else return cards.poll();
	}
	
	/**
	 * Peek at the top card, or return null if empty deck
	 */
	public Card peek() {
		return cards.peek();
	}
	
	/**
	 * Will return an array of the types of cards
	 * @return
	 */
	public Card[] getCardTypes() {
		return typesOfCards;
	}
	
	/**
	 * Will return a Card with given cardName
	 * if one exists in types of cards array
	 * @param cardName
	 * @return Card
	 */
	public Card getCard(String cardName) {
		for (Card c : typesOfCards) {
			if (c.getName().compareToIgnoreCase(cardName) == 0) return c;
		}
		return null;
	}
}
