package game;

import game.state.Name;

public class LoveLetters extends Game {
	
	public LoveLetters(int players) {
		setGameState(new Name());
	}
	
}
