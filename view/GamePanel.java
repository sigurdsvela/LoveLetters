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

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A panel that can contain views
 */
public class GamePanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 6118343586368646652L;
	
	private long start = -1;
	private Timer timer;
	private View rootView;
	
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
		
		rootView = new View();
		
		addMouseListener(new GamePanelMouseListener());
		addMouseMotionListener(new GamePanelMouseMotionListener());
		addMouseWheelListener(new GamePanelMouseWheelListener());
	}
	
	public void addView(View view) {
		if (view == null) throw new NullPointerException();
		rootView.addSubView(view);
	}
	
	public void start() {
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		rootView.update(delta, g2d);
		rootView.setWidth(getWidth());
		rootView.setHeight(getHeight());
		
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
			for (MouseAdapter ma : rootView.getMouseAdapters()) {
				ma.mouseClicked(e);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			for (MouseAdapter ma : rootView.getMouseAdapters()) {
				ma.mouseEntered(e);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			for (MouseAdapter ma : rootView.getMouseAdapters()) {
				ma.mouseExited(e);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			for (MouseAdapter ma : rootView.getMouseAdapters()) {
				ma.mousePressed(e);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			for (MouseAdapter ma : rootView.getMouseAdapters()) {
				ma.mouseReleased(e);
			}
		}

	}
	
	private class GamePanelMouseMotionListener implements MouseMotionListener {
		@Override
		public void mouseDragged(MouseEvent e) {
			for (MouseAdapter ma : rootView.getMouseAdapters()) {
				ma.mouseDragged(e);
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			for (MouseAdapter ma : rootView.getMouseAdapters()) {
				ma.mouseMoved(e);
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
