package player;

import game.state.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.CardView;
import deck.card.Card;

public class LocalPlayer extends Player {
		private boolean isThisPlayersTurn = false;
	
		public LocalPlayer(String name, Game game) {
			super(name, game);
		}
		
		@Override
		public void doTurn() {
			super.doTurn();
			isThisPlayersTurn = true;
			if (getCardToPlay() != null) { //If the player has allready chosen a card
				turnDone();
			}
			
		}

		@Override
		protected void turnDone() {
			isThisPlayersTurn = false;
			super.turnDone();
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
		public void addCard(Card card) {
			super.addCard(card);
			card.addMouseAdapter(new CardListener());
		}
		
		public class CardListener extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				setCardToPlay(getCardIndex(((Card)e.getSource()).getName()));
				if (isThisPlayersTurn) {
					turnDone();
				}
			}
		}
}
