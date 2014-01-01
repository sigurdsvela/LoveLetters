package deck.card;

import deck.card.event.CardWasDrawnEvent;
import ui.Player;

public class Countess extends Card {

	public Countess() {
		this.addActionListenerIsDrawn(new CardWasDrawnEvent() {
			@Override
			public void run(Player player) {
				//player.hasCard(king) || player.hasCard(prince)
				//then print some text to the player and play countess
			}
		});
	}
	
	@Override
	public String getAbilityDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte getDistance() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Countess";
	}

}
