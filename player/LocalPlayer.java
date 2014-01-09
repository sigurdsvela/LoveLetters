package player;

import game.state.Game;
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
				String card = this
						.getGame()
						.getView()
						.getMultipleChoiceAnswerIgnoreCase(
								"Choose a card to play:",
								getCardsAsStrings(),
								"\nYou do not have a card \"%a\". Please choose another."
						);
				cardToPlay = getCardIndex(card);
			} else {
				// Tell local player that they have a card they MUST play
				game.getView().getInformation("You MUST play " + getCard(forCardIndex).toString() + " (Enter to continue...)");
				cardToPlay = forCardIndex;
				setForceCardIndex(-1);
			}
			
			this.game.getView().println("");
			return playCard(cardToPlay);
		}

		@Override
		public Player askPlayerForPlayer(String message) {
			Player playerToChoose;
			while (true) {
				String playerName = game.getView().getInformation(message);
				playerToChoose = game.getPlayer(playerName);
				if (playerToChoose == null) {
					getGame().getView().println("\nNo player named " + playerName);
				} else if (playerToChoose.isPlayerProtected()){
					getGame().getView().println("\n" + playerToChoose.getName() + " is protected this round.");
				} else if (!playerToChoose.isPlayerInThisRound()) {
					getGame().getView().println("\n" + playerToChoose.getName() + " is out of this round.");
				} else {
					break;
				}
			}
			this.game.getView().println("");
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
				String cardName = game.getView().getInformation(message);
				cardToChoose = game.getDeck().getCard(cardName);
				if (cardToChoose == null) {
					getGame().getView().println("\nNo card named " + cardName);
				} else {
					break;
				}
			}
			this.game.getView().println("");
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
