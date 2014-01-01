package deck.card.event;

import ui.Player;
import deck.card.Card;

public abstract class PlayerDrewCardEvent extends CardEvent{
	public abstract void run(Player player, Card card);
}
