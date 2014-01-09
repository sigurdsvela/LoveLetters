package view.component;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MenuButton extends JButton {
	private static final long serialVersionUID = -6982562214779256140L;

	public MenuButton(String text, ActionListener actionListener) {
		setText(text);
		addActionListener(actionListener);
	}
	
}
