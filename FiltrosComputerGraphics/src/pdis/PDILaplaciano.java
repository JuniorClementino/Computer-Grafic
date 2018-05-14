    package pdis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import interfaces.ImageInterface;
import java.io.IOException;

@SuppressWarnings("serial")
public class PDILaplaciano extends JComponent implements ImageInterface {

	private BufferedImage img;
        private BufferedImage imgFinal;
        
        int media;
        
         
           int[][] aux;
       
    
        public void filtroLaplaciano() throws IOException {
        
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Color c = new Color(imgFinal.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int cinza = (r + g + b) / 3;
                aux[i][j] = cinza;
            }}}
	
	public PDILaplaciano(BufferedImage img) {
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
            int[][] matriz3X3 = new int[3][3];
            int altura = img.getHeight();
            int largura = img.getWidth();
        imgFinal = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        
            int[][] aux = new int[largura][altura];
		 
        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                Color c = new Color(img.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int cinza = (r + g + b) / 3;
                aux[i][j] = cinza;
            }
        }
        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                try {
                    int total = aux[i][j] * -8;
                    total += aux[i + 1][j] * 1;
                    total += aux[i][j + 1] * 1;
                    total += aux[i - 1][j] * 1;
                    total += aux[i][j - 1] * 1;
                    total += aux[i - 1][j - 1] * 1;
                    total += aux[i + 1][j + 1] *1;
                    total += aux[i - 1][j + 1] *1;
                    total += aux[i + 1][j - 1] * 1;
                    if (total < 0) {
                        total = total * -1;
                    }
                    if (total > 255) {
                        total = 255;
                    }
                    Color c = new Color(total, total, total);
                    imgFinal.setRGB(i, j, c.getRGB());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    if (j == 0) {
                        if (i == 0) {
                            int total = aux[i][j] * -8;
                            total += aux[i + 1][j] * 1;
                            total += aux[i][j + 1] * 1;
                            total += aux[i + 1][j] * 1;
                            if (total < 0) {
                                total = total * -1;
                            }
                            total = total / 3;
                            if (total > 255) {
                                total = 255;
                            }
                            Color c = new Color(total, total, total);
                            imgFinal.setRGB(i, j, c.getRGB());

                        }
                        if (i == largura - 1) {
                            int total = aux[i][j] * -8;
                            total += aux[i - 1][j] * 1;
                            total += aux[i][j + 1] * 1;
                            total += aux[i - 1][j + 1] *1;
                            if (total < 0) {
                                total = total * -1;
                            }
                            total = total / 3;
                            if (total > 255) {
                                total = 255;
                            }
                            Color c = new Color(total, total, total);
                            imgFinal.setRGB(i, j, c.getRGB());
                        }
                        if (i != 0 && i != largura - 1) {
                            int total = aux[i][j] * -8;
                            total += aux[i - 1][j] * 1;
                            total += aux[i - 1][j + 1] * 1;
                            total += aux[i][j + 1] *1;
                            total += aux[i + 1][j + 1] * 1;
                            total += aux[i + 1][j] * 1;
                            if (total < 0) {
                                total = total * -1;
                            }
                            total = total / 4;
                            if (total > 255) {
                                total = 255;
                            }
                            Color c = new Color(total, total, total);
                            imgFinal.setRGB(i, j, c.getRGB());
                        }
                    }
                    if (j == altura - 1) {
                        if (i == 0) {
                            int total = aux[i][j] * -8;
                            total += aux[i + 1][j] * 1;
                            total += aux[i][j - 1] * 1;
                            total += aux[i + 1][j - 1] *1;
                            if (total < 0) {
                                total = total * -1;
                            }
                            total = total / 3;
                            if (total > 255) {
                                total = 255;
                            }
                            Color c = new Color(total, total, total);
                            imgFinal.setRGB(i, j, c.getRGB());

                        }
                        if (i == largura - 1) {
                            int total = aux[i][j] * -8;
                            total += aux[i - 1][j] * 1;
                            total += aux[i][j - 1] * 1;
                            total += aux[i - 1][j - 1] *1;
                            if (total < 0) {
                                total = total * -1;
                            }
                            total = total / 3;

                            if (total > 255) {
                                total = 255;
                            }
                            Color c = new Color(total, total, total);
                            imgFinal.setRGB(i, j, c.getRGB());

                        }
                        if (i != 0 && i != largura - 1) {
                            int total = aux[i][j] * -8;
                            total += aux[i - 1][j] * 1;
                            total += aux[i - 1][j - 1] * 1;
                            total += aux[i][j - 1] * 1;
                            total += aux[i + 1][j - 1] *1;
                            total += aux[i + 1][j] * 1;
                            if (total < 0) {
                                total = total * -1;
                            }
                            total = total / 4;

                            if (total > 255) {
                                total = 255;
                            }
                            Color c = new Color(total, total, total);
                            imgFinal.setRGB(i, j, c.getRGB());
                              
                                
                                //System.out.println("->"+ycf+" "+cbf+" "+crf);
                                
                                
                                       
                                
			}
		}
		
		this.img = imgFinal;
                
	}}}}
	
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

