package player;

import game.Game;
import view.View;
import deck.card.Card;

public class BotPlayer extends Player{
	public static final int NUM_BOT_NAMES = 7;
	public static final String[] botNames = {"Ola", "Jack", "Jon", "Master", "Vanessa", "Christy", "Emily", "Botonator"};
	public BotPlayer(String name, Game game) {
		super(name, game);
	}
	
	@Override
	public Card playCard() {
		int cardToPlayIndex;
		
		// Choose a random card OR if set, the forced cards index
		if (getForceCardIndex() == -1) {
			cardToPlayIndex = (int) (Math.random() * cards.size());
		} else {
			cardToPlayIndex = getForceCardIndex();
			setForceCardIndex(-1);
		}
		return playCard(cardToPlayIndex);
	}
	
	@Override
	public Player askPlayerForPlayer(String message) {
		View view = game.getView();
		view.setInformation(message);
		Player playerToChoose;
		Player[] playersInRound = game.getPlayersInThisRound();
		
		// If all players except bot is protected choose bot, else choose random.
		if ( game.getNumPlayersInRound() == (game.getNumProtectedPlayersInRound() + 1)) {
			// All but bot is protected
			playerToChoose = this;
			view.setInformation(name + " chose himself/herself. All other players are protected!\n");
		} else {
			while (true) {
				int playerIndex = (int) (Math.random() * playersInRound.length);
				playerToChoose = playersInRound[playerIndex];
				if (compareTo(playerToChoose) != 0 && !playerToChoose.isPlayerProtected()) {
					break;
				}
			}
			view.setInformation(name + " chose " + playerToChoose.getName() + ".\n");
		}
		
		return playerToChoose;
	}
	
	@Override
	public Player askPlayerForPlayer() {
		return askPlayerForPlayer("Choose a player:");
	}
	
	@Override
	public Card askPlayerForCard(String message) {
		View view = game.getView();
		view.setInformation(message);
		Card cardToChoose;
		Card[] cardsToChooseFrom = game.getDeck().getCardTypes();
	
		int cardIndex = (int) (Math.random() * cardsToChooseFrom.length);
		cardToChoose = cardsToChooseFrom[cardIndex];
		view.setInformation(name + " chose " + cardToChoose.getName() + ".\n");
		return cardToChoose;
	}
	
	@Override
	public Card askPlayerForCard() {
		return askPlayerForCard("Choose a card:");
	}

	@Override
	public String showCard(Card card) {
		return "Hidden Card";
	}

}
