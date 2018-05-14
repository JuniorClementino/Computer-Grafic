    package pdis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import interfaces.ImageInterface;

@SuppressWarnings("serial")
public class PDILimiarizacao extends JComponent implements ImageInterface {

	private BufferedImage img;
        private BufferedImage imgGray;
        
        int media;
	
	public PDILimiarizacao(BufferedImage img) {
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
                                int r = c.getRed();
                                int g = c.getGreen();
                                int b = c.getBlue();
                             
                                media=(r+g+b)/3; 
                            Color i = new Color(media,media,media);
                                                                    
                                out.setRGB(x, y, i.getRGB());
			}
		}
		
                this.imgGray=out;
                
             int vetorGray [];
             vetorGray = new int[256];
             
             for(int i=0; i<256; i++){
                 vetorGray[i]=0;
             }
             
             for (int y = 0; y < imgGray.getHeight(); y++) {
			for (int x = 0; x < imgGray.getWidth(); x++) {
				Color c = new Color(imgGray.getRGB(x,y));
                                vetorGray[c.getBlue()]+=1;
                        }
             }
                                
            int maxValor=0;
            int maxPos=0;
            for(int i =0; i<256; i++){
                if(vetorGray[i]>maxValor){
                    maxValor= vetorGray[i];
                    maxPos=i;
                
                }        
               
            }
            for (int y = 0; y < imgGray.getHeight(); y++) {
			for (int x = 0; x < imgGray.getWidth(); x++){
                            Color pixelColor = new Color(imgGray.getRGB(x,y));
                            if(pixelColor.getBlue()>maxPos){
                                out.setRGB(x, y, new Color (255,255,255).getRGB());
                                
                            }
                            else{
                                out.setRGB(x, y, new Color (0,0,0).getRGB());
                            }
                                
                                }}
                               
                                
             
             
             
             
                
                
                
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

