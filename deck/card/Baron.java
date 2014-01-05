package deck.card;

import game.Game;
import player.Player;
import deck.card.rule.CardRule;

public class Baron extends Card {

	public Baron() {
		addRule(new CardRule() {
			private Player loosingPlayer, versusPlayer;
			
			@Override
			public boolean condition(Game game, Player cardOwner) {
				versusPlayer = cardOwner.askPlayerForPlayer("Who do you want to battle?");
				
				// If cardOwner chose itself cancel battle.
				if (versusPlayer.compareTo(cardOwner) == 0) {
					game.getView().println("Battle is cancelled due to lack of opponent.");
					return false;
				} else {
					return true;
				}
			}

			@Override
			public void run(Game game, Player cardOwner) {
				// Run the epic battle of the history!
				Card versusPlayerCard, cardOwnerCard;

				versusPlayerCard = versusPlayer.getBiggestDistanceCard();
				cardOwnerCard = cardOwner.getBiggestDistanceCard();
				
				if (versusPlayerCard.getDistance() == cardOwnerCard.getDistance()) {
					loosingPlayer = null;
				} else if(versusPlayerCard.getDistance() > cardOwnerCard.getDistance()) {
					loosingPlayer = cardOwner;
					cardOwner.setIsPlayerInThisRound(false);
				} else {
					loosingPlayer = versusPlayer;
					versusPlayer.setIsPlayerInThisRound(false);
				}
				
				// Announce winner of battle!
				String msg = "";
				if(loosingPlayer == null) {
					msg = "No player won.";
				} else {
					msg = loosingPlayer.getName() + " lost and is out of the round\n";
					msg += loosingPlayer.getName() + " had the cards:\n";
					for (Card c : loosingPlayer.getCards()) {
						msg += c.toString();
					}
				}
				game.getView().println(msg);
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
		return 3;
	}

	@Override
	public String getName() {
		return "Baron";
	}

}
