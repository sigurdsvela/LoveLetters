package deck;

import java.util.LinkedList;

import deck.card.Card;

public class Deck {
	private LinkedList<Card> cards;
	
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
	}
	
	/**
	 * Shuffle a deck by looping n times
	 * and for each time swapping place of two cards
	 */
	public void shuffle(int n) {
		int i, j, k;
		for (k = 0; k < n; k++) {
			// Generate two random numbers in between 0 <= j, i <= NCARDS
			i = (int) ( cards.size() * Math.random() );
			j = (int) ( cards.size() * Math.random() );
			
			// Swap the cards
			Card tmp = cards.get(i);
			cards.set(i, cards.get(j));
			cards.set(j, tmp);
		}
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
}
