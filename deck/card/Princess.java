package deck.card;

import game.Game;
import player.Player;
import deck.card.rule.CardRule;

public class Princess extends Card {

	public Princess() {
		addRule(new CardRule() {
			Player cardOwner;
			
			@Override
			public boolean condition(Game game, Player cardOwner) {
				this.cardOwner = cardOwner;
				return true;
			}
			
			@Override
			public void run(Game game, Player cardOwner) {
				cardOwner.setIsPlayerInThisRound(false);
			}
			
			@Override
			public int when() {
				return CardRule.ON_PLAY | CardRule.ON_DISCARD;
			}
			@Override
			public String description() {
				return "If you either play Princess or is forced to discard it, you're out of the round.";
			}
			
			public String message() {
				return cardOwner.getName() + " put down " + getName() + " and is therefore out of round.";
			}
		});
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
