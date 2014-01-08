package game.state;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import view.ChatPanel;
import view.ChatPanel.ChatItem;
import view.GamePanel;
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
		
		chatPanel.setPreferredSize(new Dimension((int)(Window.WIDTH * 0.2), Window.HEIGHT));
		gamePanel.setPreferredSize(new Dimension((int)(Window.WIDTH * 0.8), Window.HEIGHT));
		
		chatPanel.addMessageListener(new MessageEventListener() {
			public void actionPerformed(MessageEvent message) {
				chatPanel.addChatMessage("Some dude", message.getMessage(), ChatItem.ItemType.LOCAL_PLAYER_CHAT_MSG);
			}
		});

		gameWindow.add(chatPanel, BorderLayout.EAST);
		gameWindow.add(gamePanel, BorderLayout.CENTER);
		gameWindow.pack();
		gameWindow.setVisible(true);
		
		gamePanel.start();
		
		View testView = new View(100, 100, 100, 100);
		testView.setBackgroundColor(Color.black);
		gamePanel.addView(testView);
	}

	@Override
	public void end() {
		
	}

}
