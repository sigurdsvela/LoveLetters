package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class View {
	private ArrayList<View> subviews;
	private ArrayList<MouseAdapter> mouseAdapters;
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
		mouseAdapters = new ArrayList<MouseAdapter>();
		backgroundColor = new Color(255, 255, 255, 0);
		addMouseAdapter(new StdViewMouseAdapter());
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
	
	public void addMouseAdapter(MouseAdapter listener) {
		mouseAdapters.add(listener);
	}
	
	ArrayList<MouseAdapter> getMouseAdapters() { //Package Visibility
		return mouseAdapters;
	}
	
	public View superView() {
		return superView;
	}
	
	/**
	 * Get views that intersects with a point
	 * @return
	 */
	private ArrayList<View> getIntersectingViews(int x, int y) {
		ArrayList<View> intercectingViews = new ArrayList<View>();
		int viewX;
		int viewY;
		int viewWidth;
		int viewHeight;
		for (View view : subviews) {
			viewX = (int)view.getX();
			viewY = (int)view.getY();
			viewWidth = (int)view.getWidth();
			viewHeight = (int)view.getHeight();
			if (viewX <= x && viewX + viewWidth >= x) {
				if (viewY <= y && viewY + viewHeight >= y) {
					intercectingViews.add(view);
				}
			}
		}
		return intercectingViews;
	}

	public double getX() {
		return x;
	}
	
	/**
	 * Get the X position of this view relative to the document
	 * @return
	 */
	public double getRealX() {
		if (superView() == null) {
			return getX();
		} else {
			return getX() + superView().getX();
		}
	}

	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Get the X position of this view relative to the document
	 * @return
	 */
	public double getRealY() {
		if (superView() == null) {
			return getY();
		} else {
			return getY() + superView().getY();
		}
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
	
	/**
	 * Calls this views subviews mouse adapters
	 */
	private class StdViewMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX()-(int)getRealX(), e.getY()-(int)getRealY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseClicked(e);
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX()-(int)getRealX(), e.getY()-(int)getRealY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseEntered(e);
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX()-(int)getRealX(), e.getY()-(int)getRealY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseExited(e);
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX()-(int)getRealX(), e.getY()-(int)getRealY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mousePressed(e);
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX()-(int)getRealX(), e.getY()-(int)getRealY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseReleased(e);
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX()-(int)getRealX(), e.getY()-(int)getRealY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseDragged(e);
				}
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX()-(int)getRealX(), e.getY()-(int)getRealY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseMoved(e);
				}
			}
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent arg0) {
			// TODO Auto-generated method stub
		}
	}
	
}
