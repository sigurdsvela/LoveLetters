package player;

import game.state.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.CardView;
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
				
				//cardToPlay = getCardIndex(card);
			} else {
				// Tell local player that they have a card they MUST play
				cardToPlay = forCardIndex;
				setForceCardIndex(-1);
			}
			
			return playCard(cardToPlay);
		}

		@Override
		public Player askPlayerForPlayer(String message) {
			Player playerToChoose;
			while (true) {
				String playerName = "";
				playerToChoose = game.getPlayer(playerName);
				if (playerToChoose == null) {
				} else if (playerToChoose.isPlayerProtected()){
				} else if (!playerToChoose.isPlayerInThisRound()) {
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
				String cardName = "";
				cardToChoose = game.getDeck().getCard(cardName);
				if (cardToChoose == null) {
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

		@Override
		public String showCard(Card card) {
			return card.toString();
		}
		
		@Override
		public void addCard(Card card) {
			super.addCard(card);
			card.getView().addMouseAdapter(new CardListener());
		}
		
		public class CardListener extends MouseAdapter {
		}
}
