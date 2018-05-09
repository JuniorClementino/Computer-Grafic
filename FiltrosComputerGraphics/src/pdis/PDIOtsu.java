/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdis;

import interfaces.ImageInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author willi
 */
@SuppressWarnings("serial")
public class PDIOtsu extends JComponent implements ImageInterface{

     double[] Threshold = {0.25, 026, 0.27, 0.28, 0.29, 0.3, 0.31, 0.32, 0.33, 0.34, 0.35, 0.36,
        0.37, 0.38, 0.39, 0.40, 0.41, 0.42, 0.43, 0.44, 0.45, 0.46, 0.47, 0.48, 0.49, 0.5, 0.51, 0.52,
        0.53, 0.54, 0.55, 0.56, 0.57, 0.58, 0.59, 0.6, 0.61, 0.62, 0.63, 0.64, 0.65, 0.66, 0.67, 0.68, 0.69
    };
    BufferedImage img;
    BufferedImage imagemFinal;
    int altura;
    int largura;

    public PDIOtsu(BufferedImage img) throws IOException {       
      BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
       int x = 0;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color c = new Color(img.getRGB(i, j));
                int color = img.getRGB(i, j);
                
                int r = (color >>> 16) & 0xFF;
                int g = (color >>> 8) & 0xFF;
                int b = (color) & 0xFF;
                double lum = (r * 0.21f * g * 0.71f * b * 0.7f) / 255;
                x++;
                if (x >= 38) {
                    x = 0;
                }
                if(lum <= this.Threshold[x]){      
                   
                     Color h = new Color((float)0,(float)0,(float)0);
                     out.setRGB(i, j, h.getRGB());
                }else{ 
                    
                    Color h = new Color((float)1,(float)1,(float)1);
                     out.setRGB(i, j, h.getRGB());
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