package player;

import game.Game;
import deck.card.Card;

public class LocalPlayer extends Player {
		public LocalPlayer(String name, Game game) {
			super(name, game);
		}

		@Override
		public Card playCard() {
			Card cardToPlay = null;
			while (true) {
				String scard = this.game.getView().getInformation("Choose a card to play:");
				cardToPlay = getCard(scard);
				if (cardToPlay == null) {
					this.game.getView().getInformation("You do not have a card \""+ scard +"\". Please choose another.");
				} else {
					break;
				}
			}
			return cardToPlay;
		}

		@Override
		public void drawCard(Card card) {
			game.getView().setInformation("You draw " + card.getName());
			cards.add(card);
		}
}
