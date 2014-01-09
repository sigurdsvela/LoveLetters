package game.state;

import game.Game;

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


public class LocalGame extends GameState{
	GamePanel gamePanel;
	ChatPanel chatPanel;
	Window gameWindow;
	
	@Override
	public void init() {
		Game game = game();

		gameWindow = game.window();
		gameWindow.setLayout(new BorderLayout());
		
		chatPanel = new ChatPanel();
		chatPanel.setPreferredSize(new Dimension((int)(Window.WIDTH * 0.3), Window.HEIGHT));
		chatPanel.addMessageListener(new MessageEventListener() {
			public void actionPerformed(MessageEvent message) {
				chatPanel.addChatMessage(game().getPlayerName(), message.getMessage(), ChatItem.ItemType.LOCAL_PLAYER_CHAT_MSG);
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
        game.playerJoin( new LocalPlayer(game.getPlayerName(), game) );
        
        // Add wished number of BOTs to the game
        int c = 0, bots = game.getNumBotPlayers();
        while(c < bots) {
        	if(game.playerJoin( new BotPlayer(game) )) c++;
        }
        
        // Add on playerView to gamePanel
        for(Player player : game.getPlayers()) {
        	gamePanel.addView(player.getPlayerView());
        }
	}

	@Override
	public void end() {
		
	}

}
