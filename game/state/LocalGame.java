package game.state;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import deck.card.Card;


public class LocalGame extends Game{
	
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
		
		deck = new Deck();
		deck.shuffle(10000);
		
		removedAtStart = deck.draw();
		
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
			player.addTurnDoneListener(new PlayerDoneListener(this));
			player.drawCard(deck.draw());
		}
		
		players.get(0).doTurn();
		players.get(0).drawCard(deck.draw());
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

	/* STANDARD GETTERS AND SETTERS */
	
	public class PlayerDoneListener implements ActionListener {
		private Game game;
		
		public PlayerDoneListener(Game game) {
			this.game = game;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Card card = getCurrentPlayer().popCardToPlay();
			//card.triggerPlay(game, getCurrentPlayer());
			nextPlayer();
			getCurrentPlayer().drawCard(deck.draw());
			getCurrentPlayer().doTurn();
		}
	}
}
