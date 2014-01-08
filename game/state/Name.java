package game.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view.Window;
import view.component.MenuButton;
import view.component.MenuLabel;
import view.component.MenuTextField;

public class Name extends GameState {
	private Window window;
	private NamePanel namePanel;
	
	@Override
	public void init() {
		window = game().window();
		namePanel = new NamePanel();
		window.add(namePanel);
		window.pack();
		window.setVisible(true);
	}

	@Override
	public void end() {
		
	}
	
	private class NamePanel extends JPanel {
		private MenuTextField menuTextfield;
		private MenuLabel menuLabel;
		
		public NamePanel() {
			menuTextfield = new MenuTextField();
			menuLabel = new MenuLabel("Your name:");
			add(menuLabel);
			add(menuTextfield);
			add(new MenuButton("Continue", new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if( menuTextfield.getText().isEmpty() ) {
						menuLabel.setText("Name cannot be empty, please enter name:");
					} else {
						game().setPlayerName(menuTextfield.getText());
						game().setGameState(new MainMenu());
					}
				}
			}));
		}
	}

}
