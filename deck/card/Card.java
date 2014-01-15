package deck.card;

import game.state.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import player.Player;
import view.CardView;
import view.View;
import deck.card.rule.CardRule;

public abstract class Card implements Comparable<Card> {
	private ArrayList<CardRule> onPlayRules;
	private ArrayList<CardRule> onDiscardRules;
	private ArrayList<CardRule> onPlayerDrewCardRules;
	private ArrayList<CardRule> onDrawnRules;
	private ArrayList<MouseAdapter> mouseAdapters;
	
	private CardView cardView;
	
	protected Card() {
		onPlayRules = new ArrayList<CardRule>();
		onDiscardRules = new ArrayList<CardRule>();
		onPlayerDrewCardRules = new ArrayList<CardRule>();
		onDrawnRules = new ArrayList<CardRule>();
		mouseAdapters = new ArrayList<MouseAdapter>();
	}
	
	public CardView getView() {
		return cardView;
	}
	
	public void addToView(View view) {
		view.addSubView(getView());
	}
	
	protected void makeCardView() {
		cardView = new CardView(getName(), getDistance(), getDescription());
		cardView.addMouseAdapter(new StdMouseAdapter(this));
	}
	
	public void addMouseAdapter(MouseAdapter adapter) {
		mouseAdapters.add(adapter);
	}
	
	public String getDescription() {
		ArrayList<CardRule> rules = new ArrayList<CardRule>();
		rules.addAll(onPlayRules);
		rules.addAll(onDiscardRules);
		rules.addAll(onPlayerDrewCardRules);
		rules.addAll(onDrawnRules);
		String description = "";
		for (CardRule rule : rules) {
			description += rule.description();
		}
		return description;
	}
	
	/**
	 * Get the distance of this bard
	 * @return The distance
	 */
	public abstract byte getDistance();
	
	/**
	 * Get the name of this card
	 * @return
	 */
	public abstract String getName();
	
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
	
	@Override
	public int compareTo(Card other) {
		return getName().compareToIgnoreCase(other.getName());
	}
	
	private class StdMouseAdapter extends MouseAdapter {
		private Card card;
		public StdMouseAdapter(Card card) {
			this.card = card;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			e.setSource(card);
			for (MouseAdapter adapter : mouseAdapters) {
				adapter.mouseClicked(e);
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			e.setSource(card);
			for (MouseAdapter adapter : mouseAdapters) {
				adapter.mouseDragged(e);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			e.setSource(card);
			for (MouseAdapter adapter : mouseAdapters) {
				adapter.mouseEntered(e);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			e.setSource(card);
			for (MouseAdapter adapter : mouseAdapters) {
				adapter.mouseExited(e);
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			e.setSource(card);
			for (MouseAdapter adapter : mouseAdapters) {
				adapter.mouseMoved(e);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			e.setSource(card);
			for (MouseAdapter adapter : mouseAdapters) {
				adapter.mousePressed(e);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			e.setSource(card);
			for (MouseAdapter adapter : mouseAdapters) {
				adapter.mouseReleased(e);
			}
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			e.setSource(card);
			for (MouseAdapter adapter : mouseAdapters) {
				adapter.mouseWheelMoved(e);
			}
		}
		
	}
}
