package deck.card.custom;

import game.Game;
import player.Player;
import deck.card.Card;
import deck.card.Princess;
import deck.card.rule.CardRule;

public class Jester extends Card {

	public Jester() {
		addRule(new CardRule() {
			Card princess = new Princess();
			
			@Override
			public boolean condition(Game game, Player cardOwner) {
				return cardOwner.hasCard( princess.getName() );
			}
			
			@Override
			public void run(Game game, Player cardOwner) {
				// Set players force play index to the index of Princess
				cardOwner.setForceCardIndex( cardOwner.getCardIndex( princess.getName() ));
			}
			
			@Override
			public int when() {
				return CardRule.ON_DRAWN | CardRule.ON_PLAYER_DREW_CARD;
			}
			
			@Override
			public String description() {
				return "If you have this card on your hand whilest the Princess, you must play Princess";
			}
			
		});
	}
	
	@Override
	public byte getDistance() {
		return 0;
	}

	@Override
	public String getName() {
		return "Jester";
	}
	
}
