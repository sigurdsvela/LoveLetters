package game.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.Window;
import view.component.MenuLabel;

public class MainMenu extends GameState{
	Window window;
	MainMenuPanel mainMenuPanel;
	
	@Override
	public void init() {
		window = game().window();
		mainMenuPanel = new MainMenuPanel();
		window.add(mainMenuPanel);
		window.setVisible(true);
	}

	@Override
	public void end() {
	}
	
	private class MainMenuPanel extends JPanel {
		private static final long serialVersionUID = -4928674309987758601L;
		
		public MainMenuPanel() {
			add(new MenuLabel("Hello, " + game().getPlayerName() + "!" ));
			
			add(new MainMenuButton("New Game", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					game().setGameState(new NewLocalGameMenu());
				}
			}));
			
			add(new MainMenuButton("New Remote Game", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("New Remote Game");
				}
			}));
			
			add(new MainMenuButton("Join Remote Game", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Join Remote Game");
				}
			}));
		}
		
		private class MainMenuButton extends JButton {
			public MainMenuButton(String text, ActionListener actionListener) {
				setText(text);
				addActionListener(actionListener);
			}
		}
	}
}
