package game.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view.Window;
import view.component.MenuButton;
import view.component.MenuSlider;

public class NewLocalGameMenu extends ApplicationState{
	Window window;
	MainMenuPanel mainMenuPanel;
	
	@Override
	public void init() {
		window = application().window();
		mainMenuPanel = new MainMenuPanel();
		window.add(mainMenuPanel);
		window.setVisible(true);
	}

	@Override
	public void end() {
	}
	
	private class MainMenuPanel extends JPanel {
		private static final long serialVersionUID = -4928674309987758601L;
		private MenuSlider botOppponents;
		private MenuSlider lettersToWin;
		
		public MainMenuPanel() {
			
			add(new MenuButton("Back", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					application().setApplicationState(new MainMenu());
				}
			}));
			
			add(new MenuButton("Play", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("New Game");
					// Let game setup players, then go to LocalGame game state
					application().setApplicationState(new LocalGame( botOppponents.getValue(), lettersToWin.getValue() ));
				}
			}));
			
			botOppponents = new MenuSlider(1, 3, 2, "Number of opponents");
			lettersToWin = new MenuSlider(1, 5, 3, "How hard to get is your princess?");
			add(botOppponents);
			add(lettersToWin);
			
		}
	}
}
