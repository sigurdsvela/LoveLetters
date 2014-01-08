package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A panel that can contain views
 */
public class GamePanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 6118343586368646652L;
	private long start = -1;
	private Timer timer;
	private ArrayList<View> views;
	
	/**
	 * The difference between the elapsed time and 60fps.
	 * This is used for normalization of movement. So on each game loop iteration
	 * on might set the views with to
	 * View.setWidth(View.getWidth() - (3 * delta))
	 * This makes sure that if the FPS is high or low, the movement, per second, will still
	 * be the same
	 * 
	 * <i>currentFPS</i> * delta = 60
	 */
	private double delta;
	
	/**
	 * Target FPS. How many FPS to we want?
	 */
	private int targetFPS;
	
	/**
	 * Target time between each redraw, in nanoseconds
	 */
	private long targetTime;
	
	/**
	 * How much time it took to finish a game loop last time, in nanoseconds
	 */
	private long elapsed;
	
	/**
	 * How many FPS are we currently doing
	 */
	private double FPS;
	
	public GamePanel(int targetFPS) {
		this.targetTime = 1000000 / targetFPS;
		this.targetFPS = targetFPS;
		timer = new Timer(1000/targetFPS, this);
		setBackground(Color.WHITE);
		views = new ArrayList<View>();
		
		addMouseListener(new GamePanelMouseListener());
		addMouseMotionListener(new GamePanelMouseMotionListener());
		addMouseWheelListener(new GamePanelMouseWheelListener());
	}
	
	public void addView(View view) {
		if (view == null) throw new NullPointerException();
		views.add(view);
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
		for (View view : views) {
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
	
	public void start() {
		timer.start();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		Graphics2D graphicsBuffer;
		BufferedImage imageGraphicsBuffer;
		for (View view : views) {
			if (view.getHeight() == 0 || view.getWidth() == 0) continue; //If hidden, don't draw it
			
			//Create image to draw on
			imageGraphicsBuffer = new BufferedImage((int) view.getWidth(), (int) view.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			//Create a graphics context
			graphicsBuffer = (Graphics2D) imageGraphicsBuffer.createGraphics();
			view.update( delta, graphicsBuffer );
			
			//Draw the graphics context onto the old one
			g2d.drawImage(imageGraphicsBuffer, null, (int)view.getX(), (int)view.getY());
			
			graphicsBuffer.dispose();
		}
		
		if (start == -1) {
			elapsed = 0;
		} else {
			elapsed = System.nanoTime() - start;
		}
		start = System.nanoTime();
		
		delta = (double)elapsed / (1000000000 / 60);
		if (elapsed > 0) {
			FPS = (1000000000/(double)elapsed);
		}
		
		g2d.setColor(Color.GRAY);
		g2d.drawString("fps:" + Math.round(getFPS(1)), 10, 10);
	}
	
	public double getFPS() {
		return FPS;
	}
	
	public int getFPS(int round) {
		return (int)((getFPS() / round) + (getFPS() % round > 0 ? 1 : 0)) * round;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
	
	private class GamePanelMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX(), e.getY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseClicked(e);
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX(), e.getY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseEntered(e);
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX(), e.getY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseExited(e);
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX(), e.getY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mousePressed(e);
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX(), e.getY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseReleased(e);
				}
			}
		}

	}
	
	private class GamePanelMouseMotionListener implements MouseMotionListener {
		@Override
		public void mouseDragged(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX(), e.getY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseDragged(e);
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			for (View view : getIntersectingViews(e.getX(), e.getY())) {
				for (MouseAdapter ma : view.getMouseAdapters()) {
					ma.mouseMoved(e);
				}
			}
		}
	}
	
	private class GamePanelMouseWheelListener implements MouseWheelListener {
		@Override
		public void mouseWheelMoved(MouseWheelEvent arg0) {
			// TODO Auto-generated method stub
		}
	}
	
	
	
}
