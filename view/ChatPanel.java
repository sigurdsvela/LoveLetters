package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ChatPanel extends JPanel{
	ArrayList<ChatItem> messages;
	
	public ChatPanel() {
		setBackground(Color.BLACK);
		messages = new ArrayList<ChatItem>();
	}
	
	public static class ChatItem {
		public static enum ItemType {
			LOCAL_PLAYER_CHAT_MSG(Color.GREEN),
			REMOTE_PLAYER_CHAT_MSG(Color.GREEN),
			DEALER_MESSAGE(Color.BLACK);
			
			private Color messageColor;
			ItemType(Color messageColor) {
				this.messageColor = messageColor;
			}
			
			Color getMessageColor() {
				return messageColor;
			}
			
		}
		
		private String message;
		private ItemType type;
		
		public ChatItem (String message, ItemType type) {
			this.message = message;
			this.type = type;
		}
		
	}
	
}
