package deck.card;

import game.state.Game;
import player.Player;
import deck.card.rule.CardRule;

public class Guard extends Card{

	public Guard() {
		addRule(new CardRule() {
			private Player affectedPlayer;
			
			public boolean condition(Game game, Player cardOwner) {
				affectedPlayer = cardOwner.askPlayerForPlayer("Choose a player to guess the card for.");
				
				// If cardOwner chose itself, do nothing
				if (affectedPlayer.compareTo(cardOwner) == 0) {
					//game.getView().println("Nothing happened because " + cardOwner.getName() + " chose himself/herself.");
					return false;
				}
				
				// Loop over until we get something else than Guard
				Card cardGuess;
				while(true) {
					cardGuess = cardOwner.askPlayerForCard("What card do you think " + affectedPlayer.getName() + " has?");
					if (cardGuess.getName().compareTo(getName()) != 0) {
						break; //Not allowed
					} else {
						//TODO Not allowed to guess guard
					}
				}
				
				if (affectedPlayer.hasCard(cardGuess)) {
					return true;
				} else {
					//game.getView().println(affectedPlayer.getName() + " doesn't have the card " + cardGuess.toString());
					return false;
				}
			}

			public void run(Game game, Player cardOwner) {
				affectedPlayer.setIsPlayerInThisRound(false);
				//game.getView().println("You guessed correctly, " + affectedPlayer.getName() + " is out of this round.");
			}

			public int when() {
				return CardRule.ON_PLAY;
			}
			
			public String description() {
				return "When you play guard, you may guess the hand of one of your opponents." +
						"If you guess correctly, that opponent is out of this round";
			}
			
		});
	}
	
	@Override
	public byte getDistance() {
		return 1;
	}
	
	@Override
	public String getName() {
		return "Guard";
	}
}
