package deck.card.custom;

import game.state.Game;
import player.Player;
import deck.card.Card;
import deck.card.rule.CardRule;

public class PrinceOfDarkness extends Card{
	
	public PrinceOfDarkness() {
		addRule(new CardRule() {
			
			@Override
			public int when() {
				return CardRule.ON_PLAY;
			}
			
			@Override
			public void run(Game game, Player cardOwner) {
				Player[] players = game.getPlayersInThisRound();
				for (Player player : players) {
					for (Player p : players) {
						//player.showCardsOf(p)
					}
				}
			}
			
			@Override
			public String description() {
				return "When you play Prince of Darkness. Every player, except you, has to show their card to every player except you.";
			}
			
			@Override
			public boolean condition(Game game, Player cardOwner) {
				return true;
			}
		});
		makeCardView();
	}
	
	@Override
	public byte getDistance() {
		return 5;
	}

	@Override
	public String getName() {
		return null;
	}
	
}
