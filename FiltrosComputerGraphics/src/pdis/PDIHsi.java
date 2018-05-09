package pdis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import interfaces.ImageInterface;

@SuppressWarnings("serial")
public class PDIHsi extends JComponent implements ImageInterface {

	private BufferedImage img;
         
    int largura = 0;
    int altura = 0;
    double R;
    double G;
    double B;
	
        public double menor(double um, double dois, double tres) {
        double var = 0;
        if (um < dois && um < tres) {
            var = um;
        }
        if (dois < um && dois < tres) {
            var = dois;
        }
        if (tres < dois && tres < um) {
            var = tres;
        }
        return var;
    }
        
	public PDIHsi(BufferedImage img) {
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
      
                
		
		
        double tempe = 0;
        
        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                Color temp = new Color(img.getRGB(i, j));
                R = temp.getRed();
                G = temp.getGreen();
                B = temp.getBlue();
                double valorR = R / 255;
                double valorG = G / 255;
                double valorB = B / 255;

                double numerador = 1/2 *((valorR - valorG) + (valorR - valorB));
                double denumerador = Math.sqrt((Math.pow((valorR - valorG), 2) + (valorR - valorB) * (valorG - valorB)));
                if (numerador < 0) {
                    numerador = numerador * -1;
                }
                if (denumerador < 0) {
                    denumerador = denumerador * -1;
                }
                double valorAngulo = Math.acos(numerador/denumerador+0.000001);
                if(valorB<=valorG){
                    
                    tempe = valorAngulo;
                
                }
                if(valorB > valorG){
                    double x = 360 - valorAngulo;
                    x = x/360;                    
                    tempe = x;
                }
                
               double valorS = 1 - (3/(R+G+B))*menor(R,G,B);
               
               double valorI = (valorR+valorB+valorG)/3;
               tempe = tempe * 100;
               valorI = valorI * 100;
               valorS = valorS * 100;
               
               Color corHsi =new Color((int)tempe,(int)valorS,(int)valorI);
                out.setRGB(i, j, corHsi.getRGB());
                               
                              
                                
                                //System.out.println("->"+ycf+" "+cbf+" "+crf);
                                
                                
                                       
                                
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

