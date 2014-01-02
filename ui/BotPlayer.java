package ui;

import game.Game;

public class BotPlayer extends Player{
	public static final int NUM_BOT_NAMES = 7;
	public static final String[] botNames = {"Ola", "Jack", "Jon", "Master", "Vanessa", "Christy", "Emelie", "Botonator"};
	public BotPlayer(String name, Game game) {
		super(name, game);
	}
}
