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
		public Player askPlayerForPlayer(String message) {
			Player playerToChoose;
			while (true) {
				String playerName = this.game.getView().getInformation(message);
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
		public Player askPlayerForPlayer() {
			return askPlayerForPlayer("Choose a player:");
		}

		@Override
		public Card askPlayerForCard(String message) {
			Card cardToChoose;
			while (true) {
				String cardName = this.game.getView().getInformation(message);
				cardToChoose = game.getDeck().getCard(cardName);
				if (cardToChoose == null) {
					this.game.getView().getInformation("No card named " + cardName);
				} else {
					break;
				}
			}
			return cardToChoose;
		}

		@Override
		public Card askPlayerForCard() {
			return askPlayerForCard("Choose a card:");
		}
}
