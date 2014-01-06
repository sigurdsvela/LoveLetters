package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class View {
	private ArrayList<View> subviews;
	private double x;
	private double y;
	private double width;
	private double height;
	private Color backgroundColor;
	private View superView;
	
	public View() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		subviews = new ArrayList<View>();
		backgroundColor = new Color(255, 255, 255, 0);
	}
	
	/**
	 * @param delta
	 * @param canvas
	 * @return
	 */
	public final void draw(double delta, Graphics2D canvas) {
		canvas.setColor(backgroundColor);
		canvas.fillRect(0, 0, (int)width, (int)height);
		
		Graphics2D graphicsBuffer;
		BufferedImage imageGraphicsBuffer;
		for (View view : subviews) {
			//Create image to draw on
			imageGraphicsBuffer = new BufferedImage((int) view.getWidth(), (int) view.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			//Create a graphics context
			graphicsBuffer = (Graphics2D) imageGraphicsBuffer.createGraphics();
			view.draw( delta, graphicsBuffer );
			
			//Draw the graphics context onto the old one
			canvas.drawImage(imageGraphicsBuffer, null, (int)view.getX(), (int)view.getY());
		}
	}
	
	public void addSubView(View subView) {
		subviews.add(subView);
		subView.superView = this;
	}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public View superView() {
		return superView;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
}
