package game;

import game.state.MainMenu;

public class LocalGame extends Game {
	
	public String localPlayerName;
	
	public LocalGame(int players) {
		this.init();
		this.start();
	}
	
	public void init() {
		
 	}
	
	@Override
	public void start() {
		setGameState(new MainMenu());
	}
}
