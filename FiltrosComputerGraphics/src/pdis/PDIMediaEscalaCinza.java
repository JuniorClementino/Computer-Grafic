    package pdis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import interfaces.ImageInterface;

@SuppressWarnings("serial")
public class PDIMediaEscalaCinza extends JComponent implements ImageInterface {

	private BufferedImage img;
        int media;
	
	public PDIMediaEscalaCinza(BufferedImage img) {
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
                                int r = c.getRed();
                                int g = c.getGreen();
                                int b = c.getBlue();
                                
                                
                                media=(r+g+b)/3; 
                               
                              
                                
                                //System.out.println("->"+ycf+" "+cbf+" "+crf);
                                
                                
                                       
                                Color i = new Color(media,media,media);
                     
				                                                      
                                out.setRGB(x, y, i.getRGB());
			}
		}
		
		this.img = out;
                
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(img.getWidth(), img.getHeight());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}

	public BufferedImage getImage() {
		return img;
	}
	
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

