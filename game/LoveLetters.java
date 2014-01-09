package game;

import game.state.Name;

public class LoveLetters extends Application {
	
	public LoveLetters(int players) {
		setApplicationState(new Name());
	}
	
}
