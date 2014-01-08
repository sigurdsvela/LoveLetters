package game.state;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.Window;

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
			add(new MainMenuButton("New Game"));
			add(new MainMenuButton("New Remote Game"));
			add(new MainMenuButton("Join Remote Game"));
		}
		
		private class MainMenuButton extends JButton {
			public MainMenuButton(String text) {
				setText(text);
			}
		}
	}
	
}
