package view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import libs.StringUtils;
import deck.card.Card;

public class PlayerView extends View {
	/* STATIC FIELDS AND METHODS TO PlayerView class */
	private static enum PlayerPosition {
		TOP, BOTTOM, LEFT, RIGHT;
	}
	
	private static PlayerPosition nextPosition;
	
	private static PlayerPosition nextPosition() {
		System.out.println("Position: " + nextPosition);
		if( nextPosition == null ) {
			nextPosition = PlayerPosition.BOTTOM;
			return nextPosition;
		}
		
		switch(nextPosition) {
		case BOTTOM:
			nextPosition = PlayerPosition.TOP;
			break;
		case TOP:
			nextPosition = PlayerPosition.LEFT;
			break;
		case LEFT:
			nextPosition = PlayerPosition.RIGHT;
			break;
		case RIGHT:
			nextPosition = PlayerPosition.BOTTOM;
		}
	return nextPosition;
	}
	
	/* FIELDS AND METHODS TO PlayerView instances */
	private PlayerPosition position;
	private String playerName;
	private int numLettersDelivered;
	private CardView cardStackCard;

	public PlayerView(String playerName, int width, int height) {
		this.playerName = playerName;
		position = PlayerView.nextPosition();
		setBackgroundColor(Color.GREEN);
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	protected void draw(double delta, Graphics2D canvas) {
		System.out.println(getHeight());
		// Build string to draw on screen
		StringBuilder sb = new StringBuilder();
		sb.append("Player: " + playerName + "\n");
		sb.append("Letters Delivered: " + numLettersDelivered);
		String label = sb.toString();

		// Update position based on rootView (This will only change if window is resized)
		calculateBounds(rootView(), label, canvas.getFontMetrics(), position);
		
		canvas.setColor(Color.BLACK);
		
		// Draw Player name and Letters delivered on screen in right position
		int fontHeight = canvas.getFontMetrics().getHeight();
		int margin = fontHeight;
		for (String line : label.split("\r\n|\r|\n")) {
			canvas.drawString(line, 0, margin);
			margin += fontHeight;
		}
	}
	
	public void setCardStackCard(CardView card) {
		removeSubView(cardStackCard); //Remove the prevoius card
		cardStackCard = card; //Update our pointer
		addSubView(cardStackCard); //Add the new subview
	}
	
	public void setCardStackCard(Card card) {
		setCardStackCard(card.getView());
	}

	
	/**
	 * Will calculate where to place (x,y) this player view on rootView
	 * @param parent	is the view to use as rootView
	 * @param str	is the string to place on screen
	 * @param fm	is the font metrics
	 * @param position	is the player position
	 */
	public void calculateBounds(View parent, String str, FontMetrics fm, PlayerPosition position) {
		// Can't calculate coordinates if we got now place to be, eh?
		if (position == null ) return;
		
		// Let the fun begin!
		double stringWidth = StringUtils.getStringWidth(str, fm);
		double stringHeight = StringUtils.getStringHeight(str, fm);
		double parentWidth = parent.getWidth();
		double parentHeight = parent.getHeight();

		// Update according to String
		setMinWidth(stringWidth);
		setMinHeight(stringHeight+100); //Plus the card stack
		
		// Update according to Player Position and it's width and height.
		switch(position) {
		case BOTTOM:
			setX( (parentWidth / 2) - (getWidth() / 2 ) );
			setY( parentHeight - getHeight() );
			break;
		case TOP:
			setX( (parentWidth / 2) - (getWidth() / 2) );
			setY( 0 );
			break;
		case LEFT:
			setX( 0 );
			setY( (parentHeight / 2) - (getHeight() / 2) );
			break;
		case RIGHT:
			setX( parentWidth - getWidth() );
			setY( (parentHeight / 2) - (getHeight() / 2) );
			break;
		}
	}
}