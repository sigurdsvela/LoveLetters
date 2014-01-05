package player;

import game.Game;

import java.util.ArrayList;

import view.View;
import deck.card.Card;

public class BotPlayer extends Player{
	private static final ArrayList<String> botNames = new ArrayList<String>() {
		private static final long serialVersionUID = 6205065756127785845L;
	{
		add("ola");
		add("jack");
		add("jon");
		add("vanessa");
		add("christy");
		add("emily");
		add("botonator");
		add("thack");
		add("jokke");
		add("siggi");
	}};
	
	private BotPlayer(String name, Game game) {
		super(name, game);
	}
	
	public BotPlayer(Game game) {
		super(popRandomBotName(), game);
	}
	
	/**
	 * Remove and return a random name from the bots.
	 * @return
	 */
	private static String popRandomBotName() {
		int index = (int) Math.random() * botNames.size();
		String name = botNames.get(index);
		botNames.remove(index);
		return name;
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
		view.println(message);
		Player playerToChoose;
		Player[] playersInRound = game.getPlayersInThisRound();
		
		// If all players except bot is protected choose bot, else choose random.
		if ( game.getNumPlayersInRound() == (game.getNumProtectedPlayersInRound() + 1)) {
			// All but bot is protected
			playerToChoose = this;
			view.println(getName() + " chose himself/herself. All other players are protected!\n");
		} else {
			while (true) {
				int playerIndex = (int) (Math.random() * playersInRound.length);
				playerToChoose = playersInRound[playerIndex];
				if (compareTo(playerToChoose) != 0 && !playerToChoose.isPlayerProtected()) {
					break;
				}
			}
			view.println(getName() + " chose " + playerToChoose.getName() + ".\n");
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
		view.println(message);
		Card cardToChoose;
		Card[] cardsToChooseFrom = game.getDeck().getCardTypes();
	
		int cardIndex = (int) (Math.random() * cardsToChooseFrom.length);
		cardToChoose = cardsToChooseFrom[cardIndex];
		view.println(getName() + " chose " + cardToChoose.getName() + ".\n");
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
