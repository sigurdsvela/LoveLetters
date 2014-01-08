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
	private static final long serialVersionUID = -7832040277685497512L;
	public static final int HEIGHT = 600;
	public static final int WIDTH = 900;
			
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new GameWindowListener());
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
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
