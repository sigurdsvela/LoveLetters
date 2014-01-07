package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	private static final int HEIGHT = 600;
	private static final int WIDTH = 900;
	
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
	private GamePanel gamePanel;
	
	private ChatPanel chatPanel;
	
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
	
	/**
	 * Target FPS. How many FPS to we want?
	 */
	private int targetFPS;
	
	private ArrayList<View> views;
	
	public Window(int targetFPS) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.targetTime = 1000000 / targetFPS;
		this.targetFPS = targetFPS;
		
		views = new ArrayList<View>();
		
		addWindowListener(new GameWindowListener());
		
		setLocationRelativeTo(null);

		setSize(WIDTH, HEIGHT); //TODO
		
		setLayout(new BorderLayout());
		
		gamePanel = new GamePanel();
		chatPanel = new ChatPanel();
		
		chatPanel.setPreferredSize(new Dimension((int)(WIDTH * 0.2), HEIGHT));
		gamePanel.setPreferredSize(new Dimension((int)(WIDTH * 0.8), HEIGHT));
		
		add(chatPanel, BorderLayout.EAST);
		add(gamePanel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
		gamePanel.start();
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
	
	/**
	 * Get FPS rounded in nearest multiple of <i>round</i>
	 * @param round
	 * @return
	 */
	public int getFPS(int round) {
		return (int)((getFPS() / round) + (getFPS() % round > 0 ? 1 : 0)) * round;
	}
	
	private class GamePanel extends JPanel implements ActionListener{
		private static final long serialVersionUID = 6118343586368646652L;
		private long start = -1;
		private Timer timer;
		
		public GamePanel() {
			timer = new Timer(1000/targetFPS, this);
			setBackground(Color.WHITE);
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
