package deck.card.rule;

import game.Game;
import ui.Player;

public abstract class CardRule {
	/**
	 * When this card is played
	 */
	public final int ON_PLAY = 1;
	
	/**
	 * When this card is discarded
	 */
	public final int ON_DISCARD = 2;
	
	/**
	 * When the owner of this card draws another card 
	 */
	public final int ON_PLAYER_DREW_CARD = 4;
	
	/**
	 * When this card is drawn
	 */
	public final int ON_DRAWN = 8;
	
	/**
	 * Discard
	 * 
	 * The condition for this rule
	 * @param game The game in which this card was played
	 * @param cardOwner The owner of this card
	 * @return
	 */
	public abstract boolean condition(Game game, Player cardOwner);
	
	/**
	 * Apply this rule
	 * @param game The game in which this card was played
	 * @param cardOwner The owner of this card
	 * @return
	 */
	public abstract void run(Game game, Player cardOwner);
	
	/**
	 * When should this rule applyed.
	 * @return
	 */
	public abstract int when();
}
