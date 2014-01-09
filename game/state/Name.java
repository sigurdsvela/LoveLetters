package game.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view.Window;
import view.component.MenuButton;
import view.component.MenuLabel;
import view.component.MenuTextField;

public class Name extends ApplicationState {
	private Window window;
	private NamePanel namePanel;
	
	@Override
	public void init() {
		window = application().window();
		namePanel = new NamePanel();
		window.add(namePanel);
		window.pack();
		window.setVisible(true);
	}

	@Override
	public void end() {
		
	}
	
	private class NamePanel extends JPanel {
		private static final long serialVersionUID = 3985825001704083964L;
		
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
					String text = menuTextfield.getText().trim();
					if( text.isEmpty() ) {
						menuLabel.setText("Name cannot be empty, please enter name:");
						menuTextfield.setText("");
					} else {
						application().setLocalHostPlayerName(menuTextfield.getText());
						application().setApplicationState(new MainMenu());
					}
				}
			}));
		}
	}
}
