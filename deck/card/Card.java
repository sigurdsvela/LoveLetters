package deck.card;

import game.Game;

import java.util.ArrayList;

import player.Player;

import deck.card.rule.CardRule;

public abstract class Card {
	private ArrayList<CardRule> onPlayRules;
	private ArrayList<CardRule> onDiscardRules;
	private ArrayList<CardRule> onPlayerDrewCardRules;
	private ArrayList<CardRule> onDrawnRules;
	
	public abstract byte getDistance();
	public abstract String getName();
	
	protected Card() {
		onPlayRules = new ArrayList<CardRule>();
		onDiscardRules = new ArrayList<CardRule>();
		onPlayerDrewCardRules = new ArrayList<CardRule>();
		onDrawnRules = new ArrayList<CardRule>();
	}
	
	protected void addRule(CardRule rule) {
		if ((rule.when() & CardRule.ON_PLAY) == CardRule.ON_PLAY) {
			onPlayRules.add(rule);
		}
		if ((rule.when() & CardRule.ON_DRAWN) == CardRule.ON_DRAWN) {
			onDrawnRules.add(rule);
		}
		if ((rule.when() & CardRule.ON_PLAYER_DREW_CARD) == CardRule.ON_PLAYER_DREW_CARD) {
			onPlayerDrewCardRules.add(rule);
		}
		if ((rule.when() & CardRule.ON_DISCARD) == CardRule.ON_DISCARD) {
			onDiscardRules.add(rule);
		}
	}
	
	private final void triggerRule(Game game, Player cardOwner, ArrayList<CardRule> rules) {
		for (CardRule rule : rules) {
			if (rule.condition(game, cardOwner)) {
				rule.run(game, cardOwner);
				if (rule.message() != null) {
					game.getView().setInformation(rule.message());
				}
			}
		}
	}
	
	/**
	 * Gets triggered by the deck when this card is drawn
	 * 
	 * @param player The player that drew this card.
	 */
	public final void triggerPlay(Game game, Player cardOwner) {
		triggerRule(game, cardOwner, onPlayRules);
	}
	
	/**
	 * Gets triggered by the deck when this card is drawn
	 * 
	 * @param player The player that drew this card.
	 */
	public final void triggerDiscard(Game game, Player cardOwner) {
		triggerRule(game, cardOwner, onDiscardRules);
	}
	
	/**
	 * Gets triggered by the deck when this card is drawn
	 * 
	 * @param player The player that drew this card.
	 */
	public final void triggerCardWasDrawn(Game game, Player cardOwner) {
		triggerRule(game, cardOwner, onDrawnRules);
	}
	
	/**
	 * Gets called by the deck when the player that has this card
	 * on its hand draws a card
	 * @param player The player that drew a card
	 * @param card The card that the player drew
	 */
	public final void triggerPlayerDrewCard(Game game, Player cardOwner) {
		triggerRule(game, cardOwner, onPlayerDrewCardRules);
	}
	
	public String toString() {
		return getName() + "(" + getDistance() + ")";
	}
}
