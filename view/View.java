package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JScrollPane;

public class View {
	private ArrayList<View> subviews;
	private double x;
	private double y;
	private double width;
	private double height;
	private Color backgroundColor;
	private View superView;
	
	public View(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		subviews = new ArrayList<View>();
		backgroundColor = new Color(255, 255, 255, 0);
	}
	
	public View() {
		this(0, 0, 100, 100);
	}
	
	/**
	 * Draw this view, and its subviews onto the <i>canvas</i>
	 * @param delta
	 * @param canvas
	 * @return
	 */
	public final void update(double delta, Graphics2D canvas) {
		//Standard elements are drawn here, like the background color
		canvas.setColor(backgroundColor);
		canvas.fillRect(0, 0, (int)width, (int)height);
		
		draw(delta, canvas);
		
		Graphics2D graphicsBuffer;
		BufferedImage imageGraphicsBuffer;
		for (View view : subviews) {
			if (view.getHeight() == 0 || view.getWidth() == 0) continue; //If hidden, don't draw it
			
			//Create image to draw on
			imageGraphicsBuffer = new BufferedImage((int) view.getWidth(), (int) view.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			//Create a graphics context
			graphicsBuffer = (Graphics2D) imageGraphicsBuffer.createGraphics();
			view.update( delta, graphicsBuffer );
			
			//Draw the graphics context onto the old one
			canvas.drawImage(imageGraphicsBuffer, null, (int)view.getX(), (int)view.getY());
			graphicsBuffer.dispose();
		}
	}
	
	/**
	 * Used by subclasses of views to draw stuff...
	 * @param delta
	 * @param canvas
	 */
	protected void draw(double delta, Graphics2D canvas) {}
	
	public void addSubView(View subView) {
		subviews.add(subView);
		subView.superView = this;
	}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public void setBorderRadius(int topLeft, int topRight, int bottomRight, int bottonLeft) {
		
	}
	
	public void setBorderRadius(int radius) {
		setBorderRadius(radius, radius, radius, radius);
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
