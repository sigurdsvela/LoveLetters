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
	
	private boolean isHidden = false;
	
	public CardView(String name, int distance, String description, double x, double y) {
		super(x, y, 150, 209);
		
		Canvas canvas = new Canvas(); //To get font metrics
		
		distanceFont = new Font("Arial", Font.BOLD, 40);
		nameFont = new Font("Arial", Font.BOLD, 12);
		descriptionFont = new Font("Arial", Font.PLAIN, 12);
		
		this.name = StringUtils.wrap(name, canvas.getFontMetrics(nameFont), 130);
		this.distance = distance;
		this.description = StringUtils.wrap(description, canvas.getFontMetrics(descriptionFont), 130);
		
		image = new ImageIcon(ResourceLoader.getResourceURL("image/card/guard.jpg")).getImage();
		setBackgroundColor(new Color(175,135,73));
		imageHeight = (int)(image.getHeight(null) * (getWidth()/image.getWidth(null)));
		imageWidth = (int) getWidth();
		
		View view = new View(0, 0, getWidth(), getWidth());
		view.setBackgroundColor(Color.BLACK);
		view.addMouseAdapter(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Clicked Sub-Card");
			}
		});
		addSubView(view);
		
		addMouseAdapter(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Clicked Card");
			}
		});
		
	}
	
	@Override
	protected void draw(double delta, Graphics2D canvas) {
		if (isHidden) {
			canvas.drawImage(image, 10, 0, imageWidth - 20, imageHeight, null);
			canvas.setColor(Color.BLACK);
			
			canvas.setFont(nameFont);
			for (int i = 0; i < name.size(); i++) {
				canvas.drawString(name.get(i), 70, 20 + (i * 10));
			}
			
			canvas.setFont(distanceFont);
			canvas.drawString("" + distance, 15, 40);
			
			
			canvas.setFont(descriptionFont);
			for (int i = 0; i < description.size(); i++) {
				canvas.drawString(description.get(i), 15, 150 + (i * 10));
			}
		} else {
			
		}
	}
	
	public void hide() {
		isHidden = true;
	}
	
	public void show() {
		isHidden = false;
	}
	
	
}
