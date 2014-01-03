package deck.card;

import game.Game;
import player.Player;
import deck.card.rule.CardRule;

public class Priest extends Card {

	public Priest() {
		this.addRule(new CardRule() {
			public boolean condition(Game game, Player cardOwner) {
				return true;
			}

			public void run(Game game, Player cardOwner) {
				Player affectedPlayer = cardOwner.askPlayerForPlayer("Which player do you want to see the card(s) of?");
				Card[] affectedPlayerCards = affectedPlayer.getCards();
				cardOwner.setInformation(affectedPlayer.getName() + "  has the card(s) :");
				for (Card c : affectedPlayerCards) {
					cardOwner.setInformation(c.toString());
				}
			}

			public int when() {
				return CardRule.ON_PLAY;
			}

			public String message() {
				return null;
			}

			public String description() {
				return "When you play priest, you may choose a player and look at his or her hand.";
			}
			
		});
	}
	
	@Override
	public byte getDistance() {
		return 2;
	}

	@Override
	public String getName() {
		return "Priest";
	}
}
