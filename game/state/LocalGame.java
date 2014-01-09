package game.state;

import java.awt.BorderLayout;
import java.awt.Dimension;

import player.BotPlayer;
import player.LocalPlayer;
import player.Player;
import view.ChatPanel;
import view.ChatPanel.ChatItem;
import view.GamePanel;
import view.Window;
import view.event.MessageEvent;
import view.event.MessageEventListener;
import deck.Deck;


public class LocalGame extends Game{
	/**
	 * The game panel used for this game
	 */
	private GamePanel gamePanel;
	
	/**
	 * The chat panel used for this game
	 */
	private ChatPanel chatPanel;
	
	/**
	 * The game window used for this game
	 */
	private Window gameWindow;
	
	/**
	 * Number of Bot Players used in this game
	 */
	private int botOpponents;
	
	public LocalGame(int botOpponents, int numLettersToWin) {
		this.botOpponents = botOpponents;
		this.numLettersToWin = numLettersToWin;
	}	
	
	/* LOCAL GAME METHODS */
	
	@Override
	public void init() {
		gameWindow = application().window();
		gameWindow.setLayout(new BorderLayout());
		
		chatPanel = new ChatPanel();
		chatPanel.setPreferredSize(new Dimension((int)(Window.WIDTH * 0.3), Window.HEIGHT));
		chatPanel.addMessageListener(new MessageEventListener() {
			public void actionPerformed(MessageEvent message) {
				chatPanel.addChatMessage(application().getLocalHostPlayerName(), 
						message.getMessage(), ChatItem.ItemType.LOCAL_PLAYER_CHAT_MSG);
			}
		});
		
		gamePanel = new GamePanel(60);
		gamePanel.setPreferredSize(new Dimension((int)(Window.WIDTH * 0.7), Window.HEIGHT));
		gamePanel.start();

		gameWindow.add(chatPanel, BorderLayout.EAST);
		gameWindow.add(gamePanel, BorderLayout.CENTER);
		gameWindow.pack();
		gameWindow.setVisible(true);
		
		
		// Add on players to game
        playerJoin( new LocalPlayer(application().getLocalHostPlayerName(), this) );
        
        // Add wished number of BOTs to the game
        int c = 0;
        while(c < botOpponents) {
        	if(playerJoin( new BotPlayer(this) )) c++;
        }
        
        // Add on playerView to gamePanel
        for(Player player : getPlayers()) {
        	gamePanel.addView(player.getPlayerView());
        }
        
        gameLoop();
	}

	private void gameLoop() {
		// For updating ChatPanel
		ChatItem.ItemType dealerItem = ChatItem.ItemType.DEALER_MESSAGE;
		String dealerString = "Dealer";
		
		// Load in game in namespace due to used a lot
		Player currentPlayer;
		Player[] winners;
		
        while(true) {
    		chatPanel.addChatMessage(dealerString, "=== NEW ROUND ===", dealerItem);
            
            // Shuffle up deck and get ready to play! 
            deck = new Deck();
            deck.shuffle(10000);
            
            // Remove first card of shuffled deck
            chatPanel.addChatMessage(dealerString, "Setting a card aside.", dealerItem);
            removedAtStart = deck.draw();
            
            // Empty hand, update in round flag and draw a card for each player
            chatPanel.addChatMessage(dealerString, "Each player draws their card.", dealerItem);
            for (Player p : getPlayers() ) {
            	p.emptyHand(false);
                p.setIsPlayerInThisRound(true);
                p.setIsPlayerProtected(false);
                p.drawCard(deck.draw());
            }
            
            // Who goes first?
            chatPanel.addChatMessage(dealerString, "Player " + players.get(currentPlayerIndex).getName() + " goes first.", dealerItem);
            
            // This is main content of a round;
            // Loop over until deck is empty or number of players in round is less than 2
            while( getNumPlayersInRound() > 1 && deck.peek() != null ) {
                // Retrieve current player and let the player draw a card.
                currentPlayer = players.get(currentPlayerIndex);
                
                chatPanel.addChatMessage(dealerString, "=== " + currentPlayer.getName() + "'s turn ===", dealerItem);
                currentPlayer.setIsPlayerProtected(false);
                currentPlayer.drawCard(deck.draw());
                currentPlayer.showCards(true);
                
                // Let current player play a card
                currentPlayer.playCard();
                
                // Next players turn!
                nextPlayer(); 
            }
            
            // End of round - retrieve winner(s), update letters delivered 
            // and announce winner(s) of round
            winners = getWinners();
            for (Player winner : winners) {
                winner.incrementLettersDelivired();
                chatPanel.addChatMessage(dealerString,"Player " + winner.getName() + " won the round with Card: " 
                        + winner.getCard(0).getName() + " (" + winner.getCard(0).getDistance() + ")", dealerItem);
            }
            
            // Announce end of round
            chatPanel.addChatMessage(dealerString,"=== END OF ROUND ===", dealerItem);
            
            // Show number of letters each player has delivered
            for (Player p : getPlayers()) {
            	chatPanel.addChatMessage(dealerString,"Player: " + p.getName() + " has delivered " + p.getLettersDelivired() + " love letter(s)", dealerItem);
            }
            
            // Check if someone is going on a date with the princess!
            boolean endOfGame = false;
            if (winners != null) {
            	// Announce winner(s)
                for (Player winner : winners) {
                    if (winner.getLettersDelivired() >= numLettersToWin) {
                    	endOfGame = true;
                        chatPanel.addChatMessage(dealerString,"Player " + winner.getName() + " won the game.", dealerItem);
                    }
                }
            }
            
            if (endOfGame) {
                // Announce end of game
            	chatPanel.addChatMessage(dealerString,"=== END OF GAME ===", dealerItem);
            }
        }
	}
	
	@Override
	public void end() {
		
	}

	@Override
	public boolean playerJoin(Player player) {
        if (getPlayer( player.getName() ) != null) return false;
        players.add(player);
        return true;
	}

}
