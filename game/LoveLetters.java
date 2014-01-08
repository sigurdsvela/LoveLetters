package game;

import game.state.MainMenu;

public class LoveLetters extends Game {
	
	public LoveLetters(int players) {
		setGameState(new MainMenu());
	}
	
}
