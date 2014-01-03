package deck.card;

import game.Game;
import player.Player;
import deck.card.rule.CardRule;

public class Baron extends Card {

	public Baron() {
		this.addRule(new CardRule() {
			Player loosingPlayer, versusPlayer, cardOwner;
			
			@Override
			public boolean condition(Game game, Player cardOwner) {
				this.cardOwner = cardOwner;
				return true;
			}

			@Override
			public void run(Game game, Player cardOwner) {
				Card versusPlayerCard, cardOwnerCard;
				versusPlayer = cardOwner.askPlayerForPlayer("Who do you want to battle?");
				
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
			}

			@Override
			public int when() {
				return CardRule.ON_PLAY;
			}

			@Override
			public String message() {
				String msg = "";
				
				if(loosingPlayer == null) {
					return "No player won.";
				} else {
					msg += loosingPlayer.getName() + " lost and is out of the round\n";
					msg += loosingPlayer.getName() + " had the cards:\n";
					for (Card c : loosingPlayer.getCards()) {
						msg += c.toString() + "\n";
					}
					return msg;
				}
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
