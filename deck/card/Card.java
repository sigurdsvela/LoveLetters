package deck.card;

import java.util.ArrayList;

import ui.Player;
import deck.card.event.CardWasDrawnEvent;
import deck.card.event.PlayerDrewCardEvent;

public abstract class Card {
	private ArrayList<CardWasDrawnEvent> cardWasDrawnEvents;
	private ArrayList<PlayerDrewCardEvent> playerDrewCardEvents;
	
	public abstract String getAbilityDescription();
	public abstract byte getDistance();
	public abstract String getName();
	
	protected Card() {
		cardWasDrawnEvents = new ArrayList<CardWasDrawnEvent>();
	}
	
	/**
	 * Add action listener to when the player that has this card 
	 * @param event
	 */
	protected void addActionListenerPlayerDrewCard(PlayerDrewCardEvent event) {
		playerDrewCardEvents.add(event);
	}
	
	/**
	 * Add action listener to when this card is draw
	 * @param event
	 */
	protected void addActionListenerIsDrawn(CardWasDrawnEvent event) {
		cardWasDrawnEvents.add(event);
	}
	
	/**
	 * Gets triggered by the deck when this card is drawn
	 * 
	 * @param player The player that drew this card.
	 */
	public final void triggerCardWasDrawn(Player player) {
		for (CardWasDrawnEvent event : cardWasDrawnEvents) {
			event.run(player);
		}
	}
	
	/**
	 * Gets called by the deck when the player that has this card
	 * on its hand draws a card
	 * @param player The player that drew a card
	 * @param card The card that the player drew
	 */
	public final void triggerPlayerDrewCard(Player player, Card card) {
		for (PlayerDrewCardEvent event : playerDrewCardEvents) {
			event.run(player, card);
		}
	}
}
