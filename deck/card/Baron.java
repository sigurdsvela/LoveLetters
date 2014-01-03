package deck.card;

import game.Game;
import player.Player;
import deck.card.rule.CardRule;

public class Baron extends Card {

	public Baron() {
		this.addRule(new CardRule() {
			Player winningPlayer;
			Player versusPlayer;
			Player cardOwner;
			
			@Override
			public boolean condition(Game game, Player cardOwner) {
				this.cardOwner = cardOwner;
				return true;
			}

			@Override
			public void run(Game game, Player cardOwner) {
				cardOwner.askPlayerForCard("Who do you want to battle?");
			}

			@Override
			public int when() {
				return CardRule.ON_PLAY;
			}

			@Override
			public String message() {
				return null;
			}

			@Override
			public String description() {
				return null;
			}
			
		});
	}
	
	@Override
	public byte getDistance() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Baron";
	}

}
