package pdis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import interfaces.ImageInterface;
import java.util.Scanner;

@SuppressWarnings("serial")
public class PDIBinariza extends JComponent implements ImageInterface {

	private BufferedImage img;
	Scanner tec = new Scanner (System.in);
	public PDIBinariza(BufferedImage img) {
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		int qtd[] = new int[26];
                for (int i = 0; i < qtd.length; i++) {
                    qtd[i]=0;                
                }
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
				int red = c.getRed();
                                int green = c.getGreen();
                                int blue = c.getBlue();
                                
                                int gray = (red+green+blue)/3;
                                
                                int i = (int) gray/10;
                                qtd[i] = qtd[i]+1;
                                
                                
                                
			}
		}
		for (int i = 0; i < qtd.length; i++) {
                    System.out.println("i"+i+" = "+ qtd[i]);                
                }
                
                
                System.out.println("Digite o limiar");                                
                                
                int limiar = tec.nextInt();

                for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
				int red = c.getRed();
                                int green = c.getGreen();
                                int blue = c.getBlue();
                                
                                int gray = (red+green+blue)/3;
                                if(gray>limiar){
                                    out.setRGB(x, y, Color.WHITE.getRGB());
                                }
                                else {
                                    out.setRGB(x, y, Color.BLACK.getRGB());
                                }
                        }
                }
		this.img = out;
	}
        
        public PDIBinariza(BufferedImage img, int metodo) {
            
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		int[] histograma = new int[256];
                double[] probs = new double[256];
                for (int i = 0; i < histograma.length; i++) {
                    histograma[i]=0;                
                }
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
				int red = c.getRed();
                                int green = c.getGreen();
                                int blue = c.getBlue();
                                
                                int gray = (red+green+blue)/3;
                                
                                int i = (int) gray;
                                histograma[i] = histograma[i]+1;                                                                                                
			}
		}
		for (int i = 0; i < histograma.length; i++) {
                    System.out.println("i"+i+" = "+ histograma[i]);                
                }
                
                
                System.out.println("Digite o limiar");                                
                                
                int limiar = tec.nextInt();

                for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
				int red = c.getRed();
                                int green = c.getGreen();
                                int blue = c.getBlue();
                                
                                int gray = (red+green+blue)/3;
                                if(gray>limiar){
                                    out.setRGB(x, y, Color.WHITE.getRGB());
                                }
                                else {
                                    out.setRGB(x, y, Color.BLACK.getRGB());
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
