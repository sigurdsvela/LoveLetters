package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class LogView extends View {
	ArrayList<String> log;
	private boolean START_NEW_ON_PRINT;
	private Color fontColor;
	private int lineHeight;
	private int padding;
	
	public LogView(double x, double y, double width, double height) {
		super(x, y, width, height);
		log = new ArrayList<String>();
		START_NEW_ON_PRINT = true;
		fontColor = Color.BLACK;
	}
	
	public LogView() {
		super();
	}
	
	@Override
	protected void draw(double delta, Graphics2D canvas) {
		Color saveColor = canvas.getColor();
		canvas.setColor(fontColor);
		for (int i = 0; i < log.size(); i++) {
			canvas.drawString(log.get(i), 0 + padding, (int) getHeight() - padding - (lineHeight * i));
		}
		canvas.setColor(saveColor);
	}
	
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
	
	public void setLineHeight(int lineHeight) {
		this.lineHeight = lineHeight;
	}
	
	public void setPadding(int padding) {
		this.padding = padding;
	}
	
	public void print(String message) {
		if (START_NEW_ON_PRINT) {
			println(message);
		} else {
			log.set( log.size() - 1, log.get(log.size()-1) + message ); //Append to last entry
		}
		START_NEW_ON_PRINT = false;
	}
	
	public void println(String message) {
		log.add(message);
		START_NEW_ON_PRINT = true;
	}
	
}
