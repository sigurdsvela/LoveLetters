package player;

import game.Game;
import deck.card.Card;

public class LocalPlayer extends Player {
		public LocalPlayer(String name, Game game) {
			super(name, game);
		}

		@Override
		public Card playCard() {
			return null;
		}

		@Override
		public void drawCard(Card card) {
			game.getView().setInformation("You drew " + card.getName());
			cards.add(card);
		}
}
