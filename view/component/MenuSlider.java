package view.component;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MenuSlider extends JPanel{
	private static final long serialVersionUID = -3918759413002071597L;

	private JLabel label;
	private JSlider slider;
	
	public MenuSlider() {
		this(1, 3, 2);
	}
	
	public MenuSlider(int min, int max, int defaultValue) {
		setLayout(new BorderLayout());
		
		// Create the Slider for the MenuSlider
		slider = new JSlider();
		slider.setMinimum(1);
		slider.setMaximum(3);
		slider.setValue(defaultValue);
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent ce) {
				JSlider slider = (JSlider)ce.getSource();
				label.setText("Number of opponents: " + slider.getValue());
			}
		});
		
		// Create the Label that displays the value for the slider
		label = new JLabel("Number of opponents: " + slider.getValue());
		
		// Add the components to the panel
		add(slider, BorderLayout.CENTER);
		add(label, BorderLayout.EAST);
	}
	
	public int getValue() {
		return slider.getValue();
	}

}
