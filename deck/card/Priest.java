package deck.card;

import game.Game;
import player.Player;
import deck.card.rule.CardRule;

public class Priest extends Card {

	public Priest() {
		this.addRule(new CardRule() {
			Player affectedPlayer;
			
			public boolean condition(Game game, Player cardOwner) {
				this.affectedPlayer = cardOwner.askPlayerForPlayer("Which player do you want to see the card(s) of?");
				
				if (affectedPlayer.getName().compareTo(cardOwner.getName()) == 0) {
					// This can really only happen if all but cardOwner is protected
					game.getView().setInformation("Forgotten our cards already have we, " + cardOwner.getName() + "?");
					return false;
				} else {
					return true;
				}
			}

			public void run(Game game, Player cardOwner) {
				// Loop over and show for cardOwner affectedPlayers card(s)
				Card[] affectedPlayerCards = affectedPlayer.getCards();
				cardOwner.getGame().getView().setInformation(affectedPlayer.getName() + "  has the card(s) :");
				for (Card c : affectedPlayerCards) {
					cardOwner.getGame().getView().setInformation(cardOwner.showCard(c));
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
