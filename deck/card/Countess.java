package deck.card;

import player.Player;
import game.Application;
import deck.card.rule.CardRule;

public class Countess extends Card {

	public Countess() {
		addRule(new CardRule() {

			public boolean condition(Application game, Player cardOwner) {
				return cardOwner.hasCard( new King() ) || cardOwner.hasCard( new Prince() );
			}

			public void run(Application game, Player cardOwner) {
				// Set players force play index to the index of Countess
				cardOwner.setForceCardIndex( cardOwner.getCardIndex(getName()) );
			}

			public int when() {
				return CardRule.ON_PLAYER_DREW_CARD | CardRule.ON_DRAWN;
			}

			@Override
			public String description() {
				return "If you have this card on your hand whilest" +
						"also the King or the Prince" +
						"you must play contess";
			}
		});
	}
	
	@Override
	public byte getDistance() {
		return 7;
	}

	@Override
	public String getName() {
		return "Countess";
	}
}