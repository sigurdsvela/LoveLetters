package view.event;


/**
 * Recived when you got a message
 */
public abstract class MessageEventListener extends ViewEventListener{
	public abstract void actionPerformed(MessageEvent message);
}
