package deck.card;

import game.Game;
import ui.Player;
import deck.card.rule.CardRule;

public class Countess extends Card {

	public Countess() {
		this.addRule(new CardRule() {

			public boolean condition(Game game, Player cardOwner) {
				return cardOwner.hasCard("king") || cardOwner.hasCard("prince");
			}

			public void run(Game game, Player cardOwner) {
				
			}

			public int when() {
				return CardRule.ON_PLAYER_DREW_CARD | CardRule.ON_DRAWN;
			}

			public String message() {
				return "Contess was caught, she is running away!";
			}

			@Override
			public String description() {
				return "If you have this card on your hand whilest" +
						"also the King or the Prine" +
						"you must play contess";
			}
			
		});
	}
	
	@Override
	public byte getDistance() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Countess";
	}

}
