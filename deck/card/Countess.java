package deck.card;

import player.Player;
import game.Game;
import deck.card.rule.CardRule;

public class Countess extends Card {

	public Countess() {
		this.addRule(new CardRule() {

			public boolean condition(Game game, Player cardOwner) {
				return cardOwner.hasCard( new King() ) || cardOwner.hasCard( new Prince() );
			}

			public void run(Game game, Player cardOwner) {
				// Set players force play index to the index of Countess
				cardOwner.setForceCardIndex( cardOwner.getCardIndex(getName()) );
			}

			public int when() {
				return CardRule.ON_PLAYER_DREW_CARD | CardRule.ON_DRAWN;
			}

			public String message() {
				return null;
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