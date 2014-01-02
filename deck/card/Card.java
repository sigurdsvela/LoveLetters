package deck.card;

import java.util.ArrayList;

import ui.Player;
import deck.card.rule.CardRule;

public abstract class Card {
	private ArrayList<CardRule> onPlayRules;
	private ArrayList<CardRule> onHandChangeRules;
	
	public abstract String getAbilityDescription();
	public abstract byte getDistance();
	public abstract String getName();
	
	protected Card() {
	}
	
	/**
	 * Gets triggered by the deck when this card is drawn
	 * 
	 * @param player The player that drew this card.
	 */
	public final void triggerCardWasDrawn(Player player) {
	}
	
	/**
	 * Gets called by the deck when the player that has this card
	 * on its hand draws a card
	 * @param player The player that drew a card
	 * @param card The card that the player drew
	 */
	public final void triggerPlayerDrewCard(Player player, Card card) {
	}
}
