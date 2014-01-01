package deck.card.event;

import ui.Player;

/**
 * Event that is triggered when a card is drawn, in the card that was drawn.
 */
public abstract class CardWasDrawnEvent extends CardEvent{
	public abstract void run(Player player);
}
