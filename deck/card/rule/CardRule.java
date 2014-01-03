package deck.card.rule;

import player.Player;
import game.Game;

public abstract class CardRule {
	/**
	 * When this card is played
	 */
	public static final int ON_PLAY = 1;
	
	/**
	 * When this card is discarded
	 */
	public static final int ON_DISCARD = 2;
	
	/**
	 * When the owner of this card draws another card 
	 */
	public static final int ON_PLAYER_DREW_CARD = 4;
	
	/**
	 * When this card is drawn
	 */
	public static final int ON_DRAWN = 8;
	
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
	
	/**
	 * A message to print if this rule is fulfilled (read: condition returns true)
	 * @return The message
	 */
	public String message() { return null; }
	
	public abstract String description();
}
