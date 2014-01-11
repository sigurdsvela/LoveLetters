package deck.card;

import game.state.Game;
import player.Player;
import deck.card.rule.CardRule;

public class Countess extends Card {

	public Countess() {
		addRule(new CardRule() {
			
			public boolean condition(Game game, Player cardOwner) {
				return cardOwner.hasCard( "King" ) || cardOwner.hasCard( "Prince" );
			}

			public void run(Game game, Player cardOwner) {
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
		makeCardView();
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