package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import view.event.MessageEvent;
import view.event.MessageEventListener;

public class ChatPanel extends JPanel{
	private ArrayList<ChatItem> messages;
	private JTextField textField;
	private JTextArea textArea;
	
	/*Event Arrays*/
	private ArrayList<MessageEventListener> messageEventListeners;
	
	public ChatPanel() {
		setBackground(Color.BLACK);
		messages = new ArrayList<ChatItem>();
		
		messageEventListeners = new ArrayList<MessageEventListener>();
		
		setLayout(new BorderLayout());
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(getWidth(), 30));
		textField.addKeyListener(new ChatTextFieldListener(textField));
		
		add(textArea, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
	}
	
	public void addChatMessage(String from, String message, ChatItem.ItemType type) {
		
	}
	
	/*Event Methods*/
	public void addMessageListener(MessageEventListener listener) {
		messageEventListeners.add(listener);
	}
	
	public void addKeyListener(KeyListener listener) {
		textField.addKeyListener(listener);
	}
	
	/*Trigger Events*/
	private void triggerMessageEventListeners(String message) {
		for (MessageEventListener listener : messageEventListeners) {
			listener.actionPerformed(new MessageEvent(message));
		}
	}
	
	private class ChatTextFieldListener implements KeyListener {
		private JTextField field;
		
		public ChatTextFieldListener(JTextField field) {
			this.field = field;
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				triggerMessageEventListeners(field.getText());
				field.setText("");
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
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
		
		String getMessage() {
			return message;
		}
		
		ItemType getType() {
			return type;
		}
	}
	
}
