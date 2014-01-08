package game.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.Window;
import view.component.MenuButton;
import view.component.MenuSlider;

public class NewLocalGameMenu extends GameState{
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
			add(new MenuButton("Back", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					game().setGameState(new MainMenu());
				}
			}));
			
			add(new MenuButton("Play", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("New Game");
					game().setGameState(new LocalGame());
				}
			}));
			
			add(new MenuSlider());
		}
	}
}
