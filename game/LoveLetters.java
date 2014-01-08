package game;

import game.state.LocalGame;

public class LoveLetters extends Game {
	
	public LoveLetters(int players) {
		setGameState(new LocalGame());
	}
	
	
}
