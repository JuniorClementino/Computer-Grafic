package pdis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import interfaces.ImageInterface;

@SuppressWarnings("serial")
public class PDICorYCbCr extends JComponent implements ImageInterface {

	private BufferedImage img;
        int media;
        double [][] matriz = new double [3][3];
        double Y,Cb, Cr;
	
	public PDICorYCbCr(BufferedImage img) {
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
        matriz[0][0] = 0.2568;
        matriz[0][1] = 0.5041;
        matriz[0][2] = 0.0979;
        matriz[1][0] = -0.1482;
        matriz[1][1] = -0.2910;
        matriz[1][2] = 0.4392;
        matriz[2][0] = 0.4392;
        matriz[2][1] = -0.3678;
        matriz[2][2] = -0.0714;
                
		
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
                                int r = c.getRed();
                                int g = c.getGreen();
                                int b = c.getBlue();
                                
                                
                                int linha =0;
                                int coluna =0;
                                
                                Y = matriz[linha][coluna] * r;
                coluna++;
                Y += matriz[linha][coluna] * g;
                coluna++;
                Y += matriz[linha][coluna] * b;
                Y += 16;
                linha++;
                coluna = 0;
                Cb = matriz[linha][coluna] * r;
                coluna++;
                Cb += matriz[linha][coluna] * g;
                coluna++;
                Cb += matriz[linha][coluna] * b;
                Cb += 128;
                linha++;
                coluna = 0;
                Cr = matriz[linha][coluna] * r;
                coluna++;
                Cr += matriz[linha][coluna] * g;
                coluna++;
                Cr += matriz[linha][coluna] * b;
                Cr += 128;
                Color corYCbCr = new Color((int)Y,(int)Cb,(int)Cr);
                
                out.setRGB(x, y, corYCbCr.getRGB());
                               
                              
                                
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

