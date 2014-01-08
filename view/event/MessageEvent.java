package view.event;

public class MessageEvent extends ViewEvent{
	private String message;
	
	public MessageEvent(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
