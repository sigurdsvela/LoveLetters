package game.state;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import view.CardView;
import view.ChatPanel;
import view.ChatPanel.ChatItem;
import view.GamePanel;
import view.PlayerView;
import view.View;
import view.Window;
import view.event.MessageEvent;
import view.event.MessageEventListener;


public class LocalGame extends GameState{
	GamePanel gamePanel;
	ChatPanel chatPanel;
	Window gameWindow;
	
	public LocalGame() {
	}
	
	@Override
	public void init() {
		gameWindow = game().window();
		
		gameWindow.setLayout(new BorderLayout());
		gamePanel = new GamePanel(60);
		chatPanel = new ChatPanel();
		
		chatPanel.setPreferredSize(new Dimension((int)(Window.WIDTH * 0.3), Window.HEIGHT));
		gamePanel.setPreferredSize(new Dimension((int)(Window.WIDTH * 0.7), Window.HEIGHT));
		
		chatPanel.addMessageListener(new MessageEventListener() {
			public void actionPerformed(MessageEvent message) {
				chatPanel.addChatMessage(game().getPlayerName(), message.getMessage(), ChatItem.ItemType.LOCAL_PLAYER_CHAT_MSG);
			}
		});

		gameWindow.add(chatPanel, BorderLayout.EAST);
		gameWindow.add(gamePanel, BorderLayout.CENTER);
		gameWindow.pack();
		gameWindow.setVisible(true);
		
		gamePanel.start();
		
		//CardView cardView = new CardView("Prince", 5, "Choose any player (including yourself) to discard his or her hand and draw a new card.", 200, 200);
		PlayerView pw = new PlayerView("Joakim", 150, 60);
		PlayerView pw2 = new PlayerView("Joakim", 150, 60);
		PlayerView pw3 = new PlayerView("Joakim", 150, 60);
		PlayerView pw4 = new PlayerView("Joakim", 150, 60);
		gamePanel.addView(pw);
		gamePanel.addView(pw2);
		gamePanel.addView(pw3);
		gamePanel.addView(pw4);
		//gamePanel.addView(cardView);
	}

	@Override
	public void end() {
		
	}

}
