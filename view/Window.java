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
	 * A Panel
	 */
	private GamePanel gamePanel;
	
	private ChatPanel chatPanel;
	
	/**
	 * Target FPS. How many FPS to we want?
	 */
	private int targetFPS;
	
	public Window(int targetFPS) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.targetFPS = targetFPS;
		
		addWindowListener(new GameWindowListener());
		
		setLocationRelativeTo(null);

		setSize(WIDTH, HEIGHT); //TODO
		
		setLayout(new BorderLayout());
		
		gamePanel = new GamePanel(targetFPS);
		chatPanel = new ChatPanel();
		
		chatPanel.setPreferredSize(new Dimension((int)(WIDTH * 0.2), HEIGHT));
		gamePanel.setPreferredSize(new Dimension((int)(WIDTH * 0.8), HEIGHT));
		
		add(chatPanel, BorderLayout.EAST);
		add(gamePanel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
		gamePanel.start();
	}
	
	public int getTargetFPS() {
		return targetFPS;
	}
	
	/**
	 * Add a view to this window
	 * @param view
	 */
	public void add(JPanel panel) {
		add(panel);
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
