package game;

import java.util.ArrayList;

import player.BotPlayer;
import player.LocalPlayer;
import player.Player;
import view.TerminalView;
import deck.Deck;

public class LocalGame extends Game {
	
	public String localPlayerName;
	
	public LocalGame() {
		this.init();
		this.start();
	}
	
	@Override
	public void init() {
		view = new TerminalView();
		view.setInformation("Welcome to LoveLetters!");
		
		// Get name for player
		localPlayerName = view.getInformation("What is your name, charmer?");
 	}
	
	@Override
	public void start() {
		gameState = GameState.MENU;
		gameLoop();
	}
	
	@Override
	public void gameLoop() {
		while(true) {
			
			switch(gameState) {
				case MENU:
					int opponents = -1;
					
					// Announce new game
					view.setInformation("==== NEW GAME ====");
		
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
					
					opponents = getView().getIntBetweenBoundaries("Please specify wished number of opponents? (1-3)", 1, 3);
					
					// Clear player list before new game
					players = new ArrayList<Player>();
					
					// Add Local Player in players list
					players.add( new LocalPlayer(localPlayerName, this));
					
					// Add wished number of BOTs to the player list
					int c = 0, botNameIndex;
					String botName;
					while(c < opponents) {
						// Create a random name for BOT
						botNameIndex =  (int) (BotPlayer.NUM_BOT_NAMES * Math.random());
						botName = BotPlayer.botNames[botNameIndex];
						if (getPlayer(botName) == null) {
							// Only add bot with name if no other player in game has same name
							playerJoin(new BotPlayer( botName, this ));
							c++;
						}
					}
					
					// Print name of players
					view.setInformation("\nPlayers in game: ");
					for (Player p : getPlayersInThisRound()) {
						view.setInformation(p.getName());
					}
					view.setInformation("");
					
					// Pick randomly first player
					currentPlayerIndex =  (int) (getNumPlayers() * Math.random());
					
					// Update gamestate variable to Game
					gameState = GameState.GAME;
					break; // End of Main case
					
				case GAME:
					Player currentPlayer;
					Player[] winners;
					
					// Announce new round!
					view.setInformation("====== NEW ROUND ======");
					
					// Shuffle up deck and get ready to play! 
					deck = new Deck();
					deck.shuffle(10000);
					
					// Remove first card of shuffled deck
					view.setInformation("Setting a card aside.\n");
					removedAtStart = deck.draw();
					
					// Empty hand, update in round flag and draw a card for each player
					view.setInformation("Each player draws their card.");
					for (Player p : players) {
						p.emptyHand(false);
						p.setIsPlayerInThisRound(true);
						p.setIsPlayerProtected(false);
						p.drawCard(deck.draw());
					}
					view.setInformation("");
					
					// Who goes first?
					view.setInformation("Player " + players.get(currentPlayerIndex).getName() + " goes first.\n");
					
					// This is main content of a round;
					// Loop over until deck is empty or number of players in round is less than 2
					while( getNumPlayersInRound() > 1 && deck.peek() != null ) {
						// Retrieve current player and let the player draw a card.
						currentPlayer = players.get(currentPlayerIndex);
						view.getInformation("Hit enter to start " + currentPlayer.getName() + "'s turn.");
						view.setInformation("===== START OF " + currentPlayer.getName() + "'s turn =====");
						currentPlayer.setIsPlayerProtected(false);
						currentPlayer.drawCard(deck.draw());
						currentPlayer.showCards(true);
						
						// Let current player play a card
						currentPlayer.playCard();
						
						view.setInformation("===== END OF " + currentPlayer.getName() + "'s turn =====");
						
						// Next players turn!
						nextPlayer(); 
					}
					
					// End of round - retrieve winner(s), update letters delivered 
					// and announce winner(s) of round
					winners = getWinners();
					for (Player winner : winners) {
						winner.incrementLettersDelivired();
						view.setInformation("Player " + winner.getName() + " won the round with Card: " 
								+ winner.getCard(0).getName() + " (" + winner.getCard(0).getDistance() + ")");
					}
					
					// Announce end of round
					view.setInformation("===== END OF ROUND =====");
					
					// Show number of letters each player has delivered
					for (Player p : players) {
						view.setInformation("Player: " + p.getName() + " has delivered " + p.getLettersDelivired() + " love letter(s)");
					}
					
					// Check if someone is going on a date with the princess!
					boolean endOfGame = false;
					if (winners != null) {
						for (Player winner : winners) {
							if (winner.getLettersDelivired() >= lettersDeliveredToWin) {
								endOfGame = true;
								
								// Announce winner(s)
								view.setInformation("Player " + winner.getName() + " won the game.");
							}
						}
					}
					
					if (endOfGame) {
						// Announce end of game
						view.setInformation("===== END OF GAME =====");
						
						// Do you want to play again?
						boolean answer;
						answer = view.getYesOrNo("Do you want to play again?");
						if (answer){
							gameState = GameState.MENU;
							break;
						} else {
							gameState = GameState.EXIT;
							break;
						}
					}
					
					break; // End of Game case
					
				case EXIT:
					view.setInformation("Thanks for playing!");
					System.exit(0);
					break; // End of Exit case
			} // End of switch
		} // End of main loop
	}
}
