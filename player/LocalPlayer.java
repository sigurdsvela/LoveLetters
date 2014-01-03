package player;

import game.Game;
import deck.card.Card;

public class LocalPlayer extends Player {
		public LocalPlayer(String name, Game game) {
			super(name, game);
		}

		@Override
		public Card playCard() {
			int cardToPlay = -1;
			while (true) {
				String card = this.game.getView().getInformation("Choose a card to play:");
				cardToPlay = getCardIndex(card);
				if (cardToPlay == -1) {
					this.game.getView().getInformation("You do not have a card \""+ card +"\". Please choose another.");
				} else {
					break;
				}
			}
			return playCard(cardToPlay);
		}

		@Override
		public void drawCard(Card card) {
			game.getView().setInformation("You drew " + card.getName());
			cards.add(card);
		}

		@Override
		public Player askPlayerForPlayer(Player player, String message) {
			Player playerToChoose;
			while (true) {
				String playerName = this.game.getView().getInformation("Choose a player:");
				playerToChoose = game.getPlayer(playerName);
				if (playerToChoose == null) {
					this.game.getView().getInformation("No player named " + playerName);
				} else {
					break;
				}
			}
			return playerToChoose;
		}

		@Override
		public Player askPlayerForPlayer(Player player) {
			return null;
		}

		@Override
		public Card askPlayerForCard(Player player, String message) {
			return null;
		}

		@Override
		public Card askPlayerForCard(Player player) {
			return null;
		}
}
