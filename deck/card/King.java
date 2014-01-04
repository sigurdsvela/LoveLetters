package deck.card;

import game.Game;
import player.Player;
import deck.card.rule.CardRule;

public class King extends Card {

	public King() {
		this.addRule(new CardRule() {
			private Player affectedPlayer;
			
			@Override
			public boolean condition(Game game, Player cardOwner) {
				Player affectedPlayer = cardOwner.askPlayerForPlayer("Which player do you want to switch hand with?");
				this.affectedPlayer = affectedPlayer;
				
				// If cardOwner chose itself, do nothing
				if (affectedPlayer.compareTo(cardOwner) == 0) {
					game.getView().setInformation("Nothing happened because" + cardOwner.getName() + " chose himself/herself.");
					return false;
				}
				
				return true;
			}
			
			@Override
			public void run(Game game, Player cardOwner) {
				// Do the switch of hands through two loops
				Card[] tmpHand = cardOwner.getCards();
				
				cardOwner.emptyHand(false);
				for (Card c : affectedPlayer.getCards()) {
					cardOwner.addCard(c);
				}
				
				affectedPlayer.emptyHand(false);
				for (Card c : tmpHand) {
					affectedPlayer.addCard(c);
				}
				
				// "Reveal the cards"
				game.getView().setInformation(cardOwner.getName() + "'s new hand:");
				cardOwner.showCards(true);
				game.getView().setInformation(affectedPlayer.getName() + "'s new hand:");
				affectedPlayer.showCards(true);
				
			}
			
			@Override
			public int when() {
				return CardRule.ON_PLAY;
			}
			
			@Override
			public String description() {
				return "When you play king, switch hand with another player of your choice.";
			}

		});
	}
	
	@Override
	public byte getDistance() {
		return 6;
	}

	@Override
	public String getName() {
		return "King";
	}
	
}
