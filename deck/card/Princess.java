 package deck.card;

import game.state.Game;
import player.Player;
import deck.card.rule.CardRule;

public class Princess extends Card {

	public Princess() {
		addRule(new CardRule() {
			
			@Override
			public boolean condition(Game game, Player cardOwner) {
				return true;
			}
			
			@Override
			public void run(Game game, Player cardOwner) {
				cardOwner.setIsPlayerInThisRound(false);
				//game.getView().println(cardOwner.getName() + " put down " + getName() + " and is therefore out of round.");
			}
			
			@Override
			public int when() {
				return CardRule.ON_PLAY | CardRule.ON_DISCARD;
			}
			@Override
			public String description() {
				return "You loose the round if you play Princess.";
			}
		});
		makeCardView();
	}
	
	@Override
	public byte getDistance() {
		return 8;
	}

	@Override
	public String getName() {
		return "Princess";
	}
	
}
