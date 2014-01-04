package deck.card;

import game.Game;
import player.Player;
import deck.card.rule.CardRule;

public class Handmaid extends Card {

	public Handmaid() {
		this.addRule(new CardRule() {
			
			@Override
			public boolean condition(Game game, Player cardOwner) {
				return true;
			}
			
			@Override
			public void run(Game game, Player cardOwner) {
				// Protect the player!
				cardOwner.setIsPlayerProtected(true);
			}

			@Override
			public int when() {
				return CardRule.ON_PLAY;
			}
			
			@Override
			public String description() {
				return "When handmaid is played, the player who played her is protected until their next turn.";
			}
		});
	}
	
	@Override
	public byte getDistance() {
		return 4;
	}

	@Override
	public String getName() {
		return "Handmaid";
	}

}
