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
	private ArrayList<ViewRep> subviews;
	private ArrayList<MouseAdapter> mouseAdapters;
	private double x;
	private double y;
	
	private double width;
	private double height;
	
	private double minWidth;
	private double minHeight;
	
	private Color backgroundColor;
	private View superView;
	
	public View(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		subviews = new ArrayList<ViewRep>();
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
		View view;
		for (ViewRep viewRep : subviews) {
			view = viewRep.view();
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
		subviews.add(new ViewRep(subView));
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
	 * Will return the rootView for a given view system.
	 * The root view can for instance be the view that GamePanel use.
	 * @return View 	is the root view for view cluster.
	 */
	public View rootView() {
		return superView == null? this : superView.rootView();
	}
	
	/**
	 * Get views that intersects with a point
	 * @return
	 */
	private ArrayList<ViewRep> getIntersectingViewReps(int x, int y) {
		ArrayList<ViewRep> intercectingViews = new ArrayList<ViewRep>();
		int viewX;
		int viewY;
		int viewWidth;
		int viewHeight;
		View view;
		for (ViewRep viewRep : subviews) {
			view = viewRep.view();
			viewX = (int)view.getX();
			viewY = (int)view.getY();
			viewWidth = (int)view.getWidth();
			viewHeight = (int)view.getHeight();
			if (viewX <= x && viewX + viewWidth >= x) {
				if (viewY <= y && viewY + viewHeight >= y) {
					intercectingViews.add(viewRep);
				}
			}
		}
		return intercectingViews;
	}
	
	/**
	 * Get views that does not intersects with the point
	 * @param x
	 * @param y
	 * @return
	 */
	private ArrayList<ViewRep> getNonIntersectingViewReps(int x, int y) {
		ArrayList<ViewRep> intercectingViews = new ArrayList<ViewRep>();
		int viewX;
		int viewY;
		int viewWidth;
		int viewHeight;
		View view;
		for (ViewRep viewRep : subviews) {
			view = viewRep.view();
			viewX = (int)view.getX();
			viewY = (int)view.getY();
			viewWidth = (int)view.getWidth();
			viewHeight = (int)view.getHeight();
			if (viewX >= x || viewX + viewWidth <= x) {
				if (viewY >= y || viewY + viewHeight <= y) {
					intercectingViews.add(viewRep);
				}
			}
		}
		return intercectingViews;
	}
	
	/**
	 * Get views that does, and do not intersects with the point
	 * @param x
	 * @param y
	 * @return
	 */
	private IntersectingViewRep getBothIntersectingViewReps(int x, int y) {
		IntersectingViewRep intercectingViews = new IntersectingViewRep();
		int viewX;
		int viewY;
		int viewWidth;
		int viewHeight;
		View view;
		for (ViewRep viewRep : subviews) {
			view = viewRep.view();
			viewX = (int)view.getX();
			viewY = (int)view.getY();
			viewWidth = (int)view.getWidth();
			viewHeight = (int)view.getHeight();
			if ((viewX <= x && viewX + viewWidth >= x) && (viewY <= y && viewY + viewHeight >= y)) {
				intercectingViews.addIntersecting(viewRep);
			} else {
				intercectingViews.addNonIntersecting(viewRep);
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
		if (width < minWidth) {
			width = minWidth;
		} else {
			this.width = width;
		}
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		if (height < minHeight) {
			height = minHeight;
		} else {
			this.height = height;
		}
	}
	
	protected void setMinWidth(double minWidth) {
		this.minWidth = minWidth;
	}
	
	protected void setMinHeight(double minHeight) {
		this.minHeight = minHeight;
	}
	
	/**
	 * Calls this views subviews mouse adapters
	 */
	private class StdViewMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			View view;
			for (ViewRep viewRep : getIntersectingViewReps(e.getX()-(int)getRealX(), e.getY()-(int)getRealY())) {
				view = viewRep.view();
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseClicked(e);
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			View view;
			for (ViewRep viewRep : getIntersectingViewReps(e.getX()-(int)getRealX(), e.getY()-(int)getRealY())) {
				view = viewRep.view();
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mousePressed(e);
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			View view;
			for (ViewRep viewRep : getIntersectingViewReps(e.getX()-(int)getRealX(), e.getY()-(int)getRealY())) {
				view = viewRep.view();
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseReleased(e);
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			View view;
			for (ViewRep viewRep : getIntersectingViewReps(e.getX()-(int)getRealX(), e.getY()-(int)getRealY())) {
				view = viewRep.view();
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseDragged(e);
				}
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			View view;
			
			//Performance, this happend on every mousemove, so its kinda cirtical
			IntersectingViewRep viewReps = getBothIntersectingViewReps(e.getX()-(int)getRealX(), e.getY()-(int)getRealY());
			
			for (ViewRep viewRep : viewReps.getIntersecting()) {
				view = viewRep.view();
				if (!viewRep.getHasEntered()) { //Call Mouse Entered
					viewRep.setHasEntered(true);
					for (MouseAdapter a : view.getMouseAdapters()) {
						a.mouseEntered(e);
					}
				}
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseMoved(e);
				}
			}
			
			for (ViewRep viewRep : viewReps.getNonIntersecting()) { //Call Mouse Exited
				view = viewRep.view();
				if (viewRep.getHasEntered()) {
					viewRep.setHasEntered(false);
					for (MouseAdapter a : view.getMouseAdapters()) {
						a.mouseExited(e);
					}
				}
			}
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent arg0) {
			// TODO Auto-generated method stub
		}
	}
	
	/**
	 * Represents a view.
	 * This class is here to allow for meta data for each view
	 */
	private class ViewRep {
		private View view;
		private boolean hasEntered;
		
		public ViewRep(View view) {
			this.view = view;
			hasEntered = false;
		}
		
		public View view() {
			return view;
		}
		
		/**
		 * Used to calculate when the mouseEntered and mouseExit should be called
		 * @param b
		 * @return
		 */
		public void setHasEntered(boolean b) {
			hasEntered = b;
		}
		
		/**
		 * Used to calculate when the mouseEntered and mouseExit should be called
		 * @return
		 */
		public boolean getHasEntered() {
			return hasEntered;
		}
		
	}
	
	private class IntersectingViewRep {
		
		ArrayList<ViewRep> intersecting;
		ArrayList<ViewRep> nonIntersecting;
		
		public IntersectingViewRep() {
			intersecting = new ArrayList<ViewRep>();
			nonIntersecting = new ArrayList<ViewRep>();
		}
		
		public void addIntersecting(ViewRep viewRep) {
			intersecting.add(viewRep);
		}
		
		public void addNonIntersecting(ViewRep viewRep) {
			nonIntersecting.add(viewRep);
		}
		
		public ArrayList<ViewRep> getNonIntersecting() {
			return nonIntersecting;
		}
		
		public ArrayList<ViewRep> getIntersecting() {
			return intersecting;
		}
		
	}
	
}
