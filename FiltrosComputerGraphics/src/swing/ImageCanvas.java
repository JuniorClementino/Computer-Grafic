package swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import interfaces.ImageInterface;

@SuppressWarnings("serial")
public class ImageCanvas extends JComponent implements ImageInterface {

	private BufferedImage img;
	double factor;
	
	public ImageCanvas(BufferedImage img) {
		this.img = img;
		factor = 1.0f;
	}
	
	public ImageCanvas(BufferedImage img, double f) {
		this.img = img;
		factor = f;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension( (int) (img.getWidth() * factor), (int) (img.getHeight() * factor));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, (int) (img.getWidth() * factor), (int) (img.getHeight() * factor), null);
	}

	public BufferedImage getImage() {
		return img;
	}

}
