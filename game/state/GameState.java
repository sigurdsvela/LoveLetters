package game.state;

import java.awt.Graphics2D;

import view.View;

public abstract class GameState {
	public abstract void init();
	public abstract void end();
	protected void addView(View view) {
		
	}
}
