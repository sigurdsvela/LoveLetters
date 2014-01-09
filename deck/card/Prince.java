package deck.card;

import game.Application;
import player.Player;
import deck.card.rule.CardRule;

public class Prince extends Card {

	public Prince() {
		addRule(new CardRule() {
			
			@Override
			public boolean condition(Application game, Player cardOwner) {
				return true;
			}
			
			@Override
			public void run(Application game, Player cardOwner) {
				Player affectedPlayer = cardOwner.askPlayerForPlayer("Which player do you want to discard their hand?");
				game.getView().println(affectedPlayer.getName() + " must discard their hand:");
				affectedPlayer.showCards(false);
				
				// Discard cards in hand
				while(affectedPlayer.discardCard(0) != null) affectedPlayer.discardCard(0);
				
				// Draw a card from deck or the removed card at start if deck is empty
				// if player is not out of round
				if (affectedPlayer.isPlayerInThisRound()) {
					Card toBeDrawn;
					if (game.getDeck().peek() == null) {
						toBeDrawn = game.getRemovedAtStart();
					} else {
						toBeDrawn = game.getDeck().draw();
					}
					
					affectedPlayer.drawCard(toBeDrawn);
				}
			}
			
			@Override
			public int when() {
				return CardRule.ON_PLAY;
			}

			@Override
			public String description() {
				return "When you play prince, you may choose a player and that player must discard their hand.";
			}

		});
	}
	
	@Override
	public byte getDistance() {
		return 5;
	}

	@Override
	public String getName() {
		return "Prince";
	}

}
