package player;

import game.Game;
import view.View;
import deck.card.Card;

public class BotPlayer extends Player{
	public static final int NUM_BOT_NAMES = 7;
	public static final String[] botNames = {"Ola", "Jack", "Jon", "Master", "Vanessa", "Christy", "Emelie", "Botonator"};
	public BotPlayer(String name, Game game) {
		super(name, game);
	}
	
	@Override
	public Card playCard() {
		int cardToPlayIndex = (int) (Math.random() * cards.size());
		return playCard(cardToPlayIndex);
	}
	
	@Override
	public void drawCard(Card card) {
		cards.add(card);
	}
	
	@Override
	public Player askPlayerForPlayer(String message) {
		View view = game.getView();
		view.setInformation(message);
		Player playerToChoose;
		Player[] playersInRound = game.getPlayersInThisRound();
		
		while (true) {
			int playerIndex = (int) (Math.random() * playersInRound.length);
			playerToChoose = playersInRound[playerIndex];
			if (playerToChoose.getName().compareTo(getName()) != 0) {
				break;
			}
		}
		view.setInformation(getName() + " chose " + playerToChoose.getName() + ".\n");
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
		view.setInformation(getName() + " chose " + cardToChoose.getName() + ".\n");
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
