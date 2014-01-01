package deck;

import deck.card.Card;

public class Deck {
	private final int NCARDS = 16;
	private int currentCard;
	private Card[] cards;
	
	public Deck() {
		//Initialize the card array
		cards = new Card[ NCARDS ];
		cards[0] = new deck.card.Guard();
		cards[1] = new deck.card.Guard();
		cards[2] = new deck.card.Guard();
		cards[3] = new deck.card.Guard();
		cards[4] = new deck.card.Guard();
		cards[5] = new deck.card.Priest();
		cards[6] = new deck.card.Priest();
		cards[7] = new deck.card.Baron();
		cards[8] = new deck.card.Baron();
		cards[9] = new deck.card.Handmaid();
		cards[10] = new deck.card.Handmaid();
		cards[11] = new deck.card.Prince();
		cards[12] = new deck.card.Prince();
		cards[13] = new deck.card.King();
		cards[14] = new deck.card.Countess();
		cards[15] = new deck.card.Princess();
		
		//Set current card to top of deck
		currentCard = 0;
	}
	
	/**
	 * Shuffle a deck by looping n times
	 * and for each time swapping place of two cards
	 */
	public void shuffle(int n) {
		int i, j, k;
		for (k = 0; k < n; k++) {
			// Generate two random numbers in between 0 <= j, i <= NCARDS
			i = (int) ( NCARDS * Math.random() );
			j = (int) ( NCARDS * Math.random() );
			
			// Swap the cards
			Card tmp = cards[i];
			cards[i] = cards[j];
			cards[j] = tmp;
		}
		
		// Reset current card to deal from top
		currentCard = 0;
	}
	
	/**
	 * Deal the next card in the deck
	 * or return null if none left
	 */
	public Card draw() {
		if ( currentCard < NCARDS ) {
			return cards[currentCard++];
		} else {
			return null;
		}
	}

}
