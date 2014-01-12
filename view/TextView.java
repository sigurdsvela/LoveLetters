package view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import libs.StringUtils;

public class TextView extends View{
	private String text;
	private int lineHeight;
	private List<String> wrappedText;
	private Wrapping wrapping;
	private FontMetrics fm;
	
	public TextView(String text) {
		this.text = text;
		wrappedText = new ArrayList<String>();
		lineHeight = 10;
		wrapping = Wrapping.SOFT;
	}
	
	public TextView() {
		this("");
	}

	@Override
	public void draw(double delta, Graphics2D canvas) {
		fm = canvas.getFontMetrics();
		setWrapping(wrapping); //TODO Could be done a bit smarter
		canvas.setColor(Color.BLACK);
		for (int i = 0; i < wrappedText.size(); i++) {
			System.out.println("Drawing string at Y:" + (i * lineHeight));
			canvas.drawString(wrappedText.get(i), 0, i * lineHeight);
		}
	}
	
	public void setLineHeight(int lineHeight) {
		this.lineHeight = lineHeight;
	}
	
	public int getLineHeight() {
		return lineHeight;
	}
	
	public void setText(String text) {
		this.text = text;
		setWrapping(wrapping); //Rewrap the text
	}
	
	public String getText() {
		return text;
	}
	
	public void setWidth(double width) {
		super.setWidth(width);
	}
	
	public void setWrapping(Wrapping wrapping) {
		switch (wrapping) {
		case SOFT:
			wrappedText = StringUtils.wrap(getText(), fm, (int)getWidth());
		case NONE:
			wrappedText = StringUtils.splitIntoLines(getText());
		}
	}
	
	public static enum Wrapping {
		SOFT, NONE;
	}
}
