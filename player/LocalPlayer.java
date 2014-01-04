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
			int cardToPlay = -1, forCardIndex = getForceCardIndex();
			
			if (forceCardIndex == -1) {
				// Get a card selected from local player
				while (true) {
					String card = this.game.getView().getInformation("Choose a card to play:");
					cardToPlay = getCardIndex(card);
					if (cardToPlay == -1) {
						getGame().getView().setInformation("\nYou do not have a card \""+ card +"\". Please choose another.");
					} else {
						break;
					}
				}
			} else {
				// Tell local player that they have a card they MUST play
				getGame().getView().getInformation("You MUST play " + getCard(forCardIndex).toString() + " (Enter to continue...)");
				cardToPlay = forCardIndex;
				setForceCardIndex(-1);
			}
			
			this.game.getView().setInformation("");
			return playCard(cardToPlay);
		}

		@Override
		public Player askPlayerForPlayer(String message) {
			Player playerToChoose;
			while (true) {
				String playerName = this.game.getView().getInformation(message);
				playerToChoose = game.getPlayer(playerName);
				if (playerToChoose == null) {
					getGame().getView().setInformation("\nNo player named " + playerName);
				} else if (playerToChoose.isPlayerProtected()){
					getGame().getView().setInformation("\n" + playerToChoose.getName() + " is protected this round.");
				} else if (!playerToChoose.isPlayerInThisRound()) {
					getGame().getView().setInformation("\n" + playerToChoose.getName() + " is out of this round.");
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
					getGame().getView().setInformation("\nNo card named " + cardName);
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
		public String showCard(Card card) {
			return card.toString();
		}
}
