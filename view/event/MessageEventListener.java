package view.event;

import java.awt.event.ActionListener;

/**
 * Recived when you got a message
 */
public abstract class MessageEventListener extends ViewEventListener{
	public abstract void actionPerformed(MessageEvent message);
}
