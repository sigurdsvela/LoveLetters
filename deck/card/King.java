package deck.card;

import game.Game;
import player.Player;
import deck.card.rule.CardRule;

public class King extends Card {

	public King() {
		this.addRule(new CardRule() {
			
			@Override
			public boolean condition(Game game, Player cardOwner) {
				return true;
			}
			
			@Override
			public void run(Game game, Player cardOwner) {
				Player affectedPlayer = cardOwner.askPlayerForPlayer("Which player do you want to switch hand with?");
				
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
