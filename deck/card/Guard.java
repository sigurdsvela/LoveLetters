package deck.card;

import player.Player;
import game.Game;
import deck.card.rule.CardRule;

public class Guard extends Card{

	public Guard() {
		this.addRule(new CardRule() {
			private Player affectedPlayer;
			
			public boolean condition(Game game, Player cardOwner) {
				Player affectedPlayer = cardOwner.askPlayerForPlayer(cardOwner, "Choose a player to guess the card for.");
				this.affectedPlayer = affectedPlayer;
				Card cardGuess = cardOwner.askPlayerForCard(cardOwner, "What card do you think " + affectedPlayer.getName() + " has?");
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
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Guard";
	}
}
