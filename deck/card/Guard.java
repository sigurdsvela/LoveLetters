package deck.card;

import player.Player;
import game.Game;
import deck.card.rule.CardRule;

public class Guard extends Card{

	public Guard() {
		addRule(new CardRule() {
			private Player affectedPlayer;
			
			public boolean condition(Game game, Player cardOwner) {
				affectedPlayer = cardOwner.askPlayerForPlayer("Choose a player to guess the card for.");
				
				// If cardOwner chose itself, do nothing
				if (affectedPlayer.compareTo(cardOwner) == 0) {
					game.getView().println("Nothing happened because " + cardOwner.getName() + " chose himself/herself.");
					return false;
				}
				
				// Loop over until we get something else than Guard
				Card cardGuess, notAllowed = new Guard();
				while(true) {
					cardGuess = cardOwner.askPlayerForCard("What card do you think " + affectedPlayer.getName() + " has?");
					if (cardGuess.compareTo(notAllowed) != 0) break;
					else cardOwner.getGame().getView().println("Not allowed to guess " + notAllowed.getName());
				}
				
				return affectedPlayer.hasCard(cardGuess);
			}

			public void run(Game game, Player cardOwner) {
				affectedPlayer.setIsPlayerInThisRound(false);
			}

			public int when() {
				return CardRule.ON_PLAY;
			}

			public String message() {
				return "You guessed correctly, " + affectedPlayer.getName() + " is out of this round.";
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
