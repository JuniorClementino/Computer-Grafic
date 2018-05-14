package pdis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import interfaces.ImageInterface;

@SuppressWarnings("serial")
public class PDISuavizaçãoMedia extends JComponent implements ImageInterface {

	private BufferedImage img ;
        private BufferedImage imagem;
        
        int media;
        
        public int[] getValues(int i, int j) {
        Color c = new Color(img.getRGB(i, j));
        int[] values = new int[3];
        values[0] = c.getRed();
        values[1] = c.getGreen();
        values[2] = c.getBlue();

        return values;
    }
	
	public PDISuavizaçãoMedia(BufferedImage img) {
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
                
		
                
		for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                try {
                    
                
                    
                    Color cor = new Color(img.getRGB(i, j));
                    int blue = cor.getBlue();
                    int red = cor.getRed();
                    int green = cor.getGreen();
                    
                    cor = new Color(img.getRGB(i-1, j));
                    blue+= cor.getBlue();
                    red+= cor.getRed();
                    green+= cor.getGreen();
                    
                    cor = new Color(img.getRGB(i+1, j));
                    blue+= cor.getBlue();
                    red+= cor.getRed();
                    green+= cor.getGreen();
                    
                    cor = new Color(img.getRGB(i, j+1));
                    blue+= cor.getBlue();
                    red+= cor.getRed();
                    green+= cor.getGreen();
                    
                     cor = new Color(img.getRGB(i, j-1));
                    blue+= cor.getBlue();
                    red+= cor.getRed();
                    green+= cor.getGreen();
                    
                    
                    
                    
                    
                    
                   
                   
                    int valorVermelho = red / 5;
                    int valorVerde = green / 5;
                    int valorAzul = blue / 5;
                    Color temp = new Color(valorVermelho,valorVerde,valorAzul);
                    out.setRGB(i, j, temp.getRGB());
                    } catch (Exception e) {
                }
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

