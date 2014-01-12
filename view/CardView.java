package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;

import libs.StringUtils;
import res.ResourceLoader;

public class CardView extends View{
	private Image image;
	private List<String> name;
	private int distance;
	private List<String> description;
	private int imageHeight;
	private int imageWidth;
	private static final double ratio = 419/300;
	
	private Font distanceFont;
	private Font nameFont;
	private Font descriptionFont;
	
	private int textPadding = 10;
	
	private boolean isHidden = false;
	
	public CardView(String name, byte distance, String description, double x, double y, double scale) {
		super(x, y, 150, 209);
		
		Canvas canvas = new Canvas(); //To get font metrics
		
		distanceFont = new Font("Arial", Font.BOLD, 40);
		nameFont = new Font("Arial", Font.BOLD, 12);
		descriptionFont = new Font("Arial", Font.PLAIN, 12);
		
		this.name = StringUtils.wrap(name, canvas.getFontMetrics(nameFont), 130);
		this.distance = distance;
		this.description = StringUtils.wrap(description, canvas.getFontMetrics(descriptionFont), 150 - 2*textPadding);
		
		image = new ImageIcon(ResourceLoader.getResourceURL("image/card/" + name.replace(" ", "-") + ".jpg")).getImage();
		setBackgroundColor(new Color(175,135,73));
		imageHeight = (int)(image.getHeight(null) * (getWidth()/image.getWidth(null)));
		imageWidth = (int) getWidth();
	}
	
	public CardView(String name, byte distance, String description, double x, double y) {
		this(name, distance, description, x, y, 1);
	}
	
	public CardView(String name, byte distance, String description, double scale) {
		this(name, distance, description, 0, 0, 1);
	}
	
	public CardView(String name, byte distance, String description) {
		this(name, distance, description, 0, 0, 1);
	}
	
	@Override
	protected void draw(double delta, Graphics2D canvas) {
		if (!isHidden) {
			canvas.drawImage(image, 10, 0, imageWidth - 20, imageHeight, null);
			canvas.setColor(Color.BLACK);
			
			canvas.setFont(nameFont);
			for (int i = 0; i < name.size(); i++) {
				canvas.drawString(name.get(i), 70, 20 + (i * 10));
			}
			
			canvas.setFont(distanceFont);
			canvas.drawString("" + distance, textPadding, 40);
			
			
			canvas.setFont(descriptionFont);
			for (int i = 0; i < description.size(); i++) {
				canvas.drawString(description.get(i), textPadding, 150 + (i * 10));
			}
		}
	}
	
	public void hide() {
		isHidden = true;
	}
	
	public void show() {
		isHidden = false;
	}
	
}
