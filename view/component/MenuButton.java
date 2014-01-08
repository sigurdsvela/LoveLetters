package view.component;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MenuButton extends JButton {
	
	public MenuButton(String text, ActionListener actionListener) {
		setText(text);
		addActionListener(actionListener);
	}
	
}
