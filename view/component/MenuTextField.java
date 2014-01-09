package view.component;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class MenuTextField extends JTextField {
	private static final long serialVersionUID = 5466732286185720779L;

	public MenuTextField(int width, int height, String text, ActionListener actionListener) {
		setPreferredSize(new Dimension(width, height));
		addActionListener(actionListener);
		setText(text);
	}

	public MenuTextField() {
		this(100, 20, "", null);
	}
}
