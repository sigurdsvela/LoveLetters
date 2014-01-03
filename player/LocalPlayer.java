package player;

import game.Game;
import view.View;
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
					setInformation("\nYou do not have a card \""+ card +"\". Please choose another.");
				} else {
					break;
				}
			}
			this.game.getView().setInformation("");
			return playCard(cardToPlay);
		}

		@Override
		public void drawCard(Card card) {
			setInformation("You drew " + card);
			cards.add(card);
		}

		@Override
		public Player askPlayerForPlayer(String message) {
			Player playerToChoose;
			while (true) {
				String playerName = this.game.getView().getInformation(message);
				playerToChoose = game.getPlayer(playerName);
				if (playerToChoose == null) {
					setInformation("\nNo player named " + playerName);
				} else {
					break;
				}
			}
			this.game.getView().setInformation("");
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
					setInformation("\nNo card named " + cardName);
				} else {
					break;
				}
			}
			this.game.getView().setInformation("");
			return cardToChoose;
		}

		@Override
		public Card askPlayerForCard() {
			return askPlayerForCard("Choose a card:");
		}

		@Override
		public void showCards() {
			View view = game.getView();
			view.setInformation("Your hand:");
			for (Card card : cards) {
				view.setInformation(card.toString()); 
			}
			view.setInformation("");
		}

		@Override
		public void setInformation(String information) {
			getGame().getView().setInformation(information);
		}
}
