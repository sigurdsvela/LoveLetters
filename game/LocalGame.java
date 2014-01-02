package game;

import ui.BotPlayer;
import ui.LocalPlayer;
import ui.Player;
import view.TerminalView;
import deck.card.Card;

public class LocalGame extends Game {
	private TerminalView view;
	
	public LocalGame() {
		this.init();
		this.start();
	}
	
	@Override
	public void init() {
		int opponents = -1;
		view = new TerminalView();
		view.setInformation("Welcome to LoveLetters!");
		
		// Get name for player
		view.setInformation("What is your name, charmer?");
		players.add(new LocalPlayer( view.getInformation() ));
		
		// Get number of wished opponents from view
		while (true) {
			view.setInformation("Please specify wished number of opponents? (1-3) ");
			try {
				opponents = Integer.parseInt(view.getInformation());
				if (opponents > 0 && opponents < 4) break;

			} catch (Exception e) {
				view.setInformation("Please specify a number.");
			}
		}
		
		// Add wished number of BOTs to the player list
		int bNameIndex;
		for (int c = 0; c < opponents; c++) {
			bNameIndex =  (int) (BotPlayer.NUM_BOT_NAMES * Math.random());
			playerJoin(new BotPlayer( BotPlayer.botNames[bNameIndex] ));
		}
		
		// Pick randomly first player
		currentPlayerIndex =  (int) (getNumPlayers() * Math.random());
		view.setInformation("Player " + players.get(currentPlayerIndex).getName() + " goes first.");
		
		// Remove first card of shuffled deck
		view.setInformation("Setting a card aside.");
		removedAtStart = deck.draw();
		
		// Each player draws a starting card
		view.setInformation("Each player draws their card.");
		for (Player p : players) {
			p.drawCard(deck.draw());
			if (p instanceof LocalPlayer) {
				// Show in view the local players card
				view.setInformation("===========================================");
				view.setInformation("Your card is: " + p.getCard(0).getName());
				view.setInformation("===========================================");
			}
		}
 	}
	
	@Override
	public void start() {
		gameLoop();
	}
	
	@Override
	public void gameLoop() {
		Player currentPlayer;
		Card currentCard;
		
		while( getNumPlayers() > 1 && deck.peek() != null ) {
			// Retrieve current player and let the player draw a card.
			currentPlayer = players.get(currentPlayerIndex);
			currentPlayer.drawCard(currentCard = deck.draw());

			view.setInformation("");
			view.setInformation("Players turn: " + currentPlayer.getName());
			view.setInformation("Draw card: " + currentCard.getName());
			nextPlayer();
		}
	}

	public Player askPlayerForPlayer(Player player, String message) {
		return null;
	}
	
	public Player askPlayerForPlayer(Player player) {
		return askPlayerForPlayer(player, "Choose a player");
	}

	public Card askPlayerForCard(Player player, String message) {
		return null;
	}
	
	public Card askPlayerForCard(Player player) {
		return askPlayerForCard(player, "Choose a card");
	}
}
