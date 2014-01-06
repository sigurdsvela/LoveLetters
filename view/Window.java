package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Window extends JFrame{
	/**
	 * Target time between each redraw, in nanoseconds
	 */
	private long targetTime;
	
	/**
	 * How much time it took to finish a game loop last time, in nanoseconds
	 */
	private long elapsed;
	
	/**
	 * A Panel
	 */
	private Panel panel;
	
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
	 * How many FPS are we currently doing
	 */
	private double FPS;
	
	private ArrayList<View> views;
	
	public Window(int maxFPS) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.targetTime = 1000000 / maxFPS;
		views = new ArrayList<View>();
		addWindowListener(new GameWindowListener());
		setLocationRelativeTo(null);
		panel = new Panel();
		add(panel);
		setSize(200, 200); //TODO
		setVisible(true);
		panel.start();
	}
	
	/**
	 * Add a view to this window
	 * @param view
	 */
	public void addView(View view) {
		views.add(view);
	}
	
	/**
	 * Get how many FPS we are doing ATM.
	 * @return FPS
	 */
	public double getFPS() {
		return FPS;
	}
	
	private class Panel extends JPanel implements ActionListener{
		private static final long serialVersionUID = 6118343586368646652L;
		private long start = -1;
		private Timer timer;
		
		public Panel() {
			timer = new Timer(100, this);
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
				//Create image to draw on
				imageGraphicsBuffer = new BufferedImage((int) view.getWidth(), (int) view.getHeight(), BufferedImage.TYPE_INT_ARGB);
				
				//Create a graphics context
				graphicsBuffer = (Graphics2D) imageGraphicsBuffer.createGraphics();
				view.draw( delta, graphicsBuffer );
				
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
			g2d.drawString("fps:" + getFPS(), 10, 10);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			repaint();
		}
		
	}
	
	private class GameWindowListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			
		}
		
	}
	
}
