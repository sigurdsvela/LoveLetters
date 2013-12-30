package deck;

import deck.card.Card;

public class Deck {
	private Card[] cards;
	
	public Deck() {
		//Initialize the cards array
		cards = new Card[10];
		cards[0] = new deck.card.Guard();
	}
	
	public Card nextCard() {
		return null;
	}

}
