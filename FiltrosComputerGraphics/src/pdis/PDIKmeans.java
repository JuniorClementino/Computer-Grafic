package pdis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import interfaces.ImageInterface;
import swing.Conf;

@SuppressWarnings("serial")
public class PDIKmeans extends JComponent implements ImageInterface {

	private BufferedImage image;
	private Color[] cores = new Color[10];
	private float[][] c1;
	private float[][] c2;
	private float[][] c3;
	private float[][] mec1;
	private float[][] mec2;
	private float[][] mec3;
	private float[][] vac1;
	private float[][] vac2;
	private float[][] vac3;
	private float[][] skc1;
	private float[][] skc2;
	private float[][] skc3;
	private float[][] csc1;
	private float[][] csc2;
	private float[][] csc3;
	
	boolean useColor = Conf.feature_color;
	boolean useMedia = Conf.feature_media;
	boolean useVariancia = Conf.feature_variancia;
	boolean useSkewness = Conf.feature_skewness;
	boolean useCurtose = Conf.feature_curtose;
	
	public PDIKmeans(BufferedImage img) {
		initCores();
		initCaracteristicas(img, Conf.color_spaces);
		image = processaKmeans(img, Conf.kmeans_classes);
	}
	
	private BufferedImage processaKmeans(BufferedImage image, int n) {
		BufferedImage out = new BufferedImage(image.getWidth(), image.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		
		// ************************************
		// *** Declarações e inicializações ***
		// ************************************
		
		// *** Declarando e definindo os primeiros centróides
		float[] centroc1 = new float[n];
		float[] centroc2 = new float[n];
		float[] centroc3 = new float[n];
		float[] centromec1 = new float[n];
		float[] centromec2 = new float[n];
		float[] centromec3 = new float[n];
		float[] centrovac1 = new float[n];
		float[] centrovac2 = new float[n];
		float[] centrovac3 = new float[n];
		float[] centroskc1 = new float[n];
		float[] centroskc2 = new float[n];
		float[] centroskc3 = new float[n];
		float[] centrocsc1 = new float[n];
		float[] centrocsc2 = new float[n];
		float[] centrocsc3 = new float[n];
		int px = (int) (image.getWidth() / (n + 1));
		int py = (int) (image.getHeight() / (n + 1));
		for (int i = 0; i < n; i++) {
			if (useColor) {
				centroc1[i] = c1[(i + 1) * px][(i + 1) * py];
				centroc2[i] = c2[(i + 1) * px][(i + 1) * py];
				centroc3[i] = c3[(i + 1) * px][(i + 1) * py];
			}
			if (useMedia) {
				centromec1[i] = mec1[(i + 1) * px][(i + 1) * py];
				centromec2[i] = mec2[(i + 1) * px][(i + 1) * py];
				centromec3[i] = mec3[(i + 1) * px][(i + 1) * py];
			}
			if (useVariancia) {
				centrovac1[i] = vac1[(i + 1) * px][(i + 1) * py];
				centrovac2[i] = vac2[(i + 1) * px][(i + 1) * py];
				centrovac3[i] = vac3[(i + 1) * px][(i + 1) * py];
			}
			if (useSkewness) {
				centroskc1[i] = skc1[(i + 1) * px][(i + 1) * py];
				centroskc2[i] = skc2[(i + 1) * px][(i + 1) * py];
				centroskc3[i] = skc3[(i + 1) * px][(i + 1) * py];
			}
			if (useCurtose) {
				centrocsc1[i] = csc1[(i + 1) * px][(i + 1) * py];
				centrocsc2[i] = csc2[(i + 1) * px][(i + 1) * py];
				centrocsc3[i] = csc3[(i + 1) * px][(i + 1) * py];
			}
		}
//		if (useColor) {
//			centroc1[0] = c1[95][190];
//			centroc2[0] = c2[95][190];
//			centroc3[0] = c3[95][190];
//			centroc1[1] = c1[199][107];
//			centroc2[1] = c2[199][107];
//			centroc3[1] = c3[199][107];
//			centroc1[2] = c1[177][147];
//			centroc2[2] = c2[177][147];
//			centroc3[2] = c3[177][147];
//			centroc1[3] = c1[153][56];
//			centroc2[3] = c2[153][56];
//			centroc3[3] = c3[153][56];
//			centroc1[4] = c1[332][55];
//			centroc2[4] = c2[332][55];
//			centroc3[4] = c3[332][55];
//			centroc1[5] = c1[315][4];
//			centroc2[5] = c2[315][4];
//			centroc3[5] = c3[315][4];
//			centroc1[6] = c1[38][182];
//			centroc2[6] = c2[38][182];
//			centroc3[6] = c3[38][182];
//		}
//		if (useMedia) {
//			centromec1[0] = mec1[95][190];
//			centromec2[0] = mec2[95][190];
//			centromec3[0] = mec3[95][190];
//			centromec1[1] = mec1[199][107];
//			centromec2[1] = mec2[199][107];
//			centromec3[1] = mec3[199][107];
//			centromec1[2] = mec1[177][147];
//			centromec2[2] = mec2[177][147];
//			centromec3[2] = mec3[177][147];
//			centromec1[3] = mec1[153][56];
//			centromec2[3] = mec2[153][56];
//			centromec3[3] = mec3[153][56];
//			centromec1[4] = mec1[332][55];
//			centromec2[4] = mec2[332][55];
//			centromec3[4] = mec3[332][55];
//			centromec1[5] = mec1[315][4];
//			centromec2[5] = mec2[315][4];
//			centromec3[5] = mec3[315][4];
//			centromec1[6] = mec1[38][182];
//			centromec2[6] = mec2[38][182];
//			centromec3[6] = mec3[38][182];
//		}
//		if (useVariancia) {
//			centrovac1[0] = vac1[95][190];
//			centrovac2[0] = vac2[95][190];
//			centrovac3[0] = vac3[95][190];
//			centrovac1[1] = vac1[199][107];
//			centrovac2[1] = vac2[199][107];
//			centrovac3[1] = vac3[199][107];
//			centrovac1[2] = vac1[177][147];
//			centrovac2[2] = vac2[177][147];
//			centrovac3[2] = vac3[177][147];
//			centrovac1[3] = vac1[153][56];
//			centrovac2[3] = vac2[153][56];
//			centrovac3[3] = vac3[153][56];
//			centrovac1[4] = vac1[332][55];
//			centrovac2[4] = vac2[332][55];
//			centrovac3[4] = vac3[332][55];
//			centrovac1[5] = vac1[315][4];
//			centrovac2[5] = vac2[315][4];
//			centrovac3[5] = vac3[315][4];
//			centrovac1[6] = vac1[38][182];
//			centrovac2[6] = vac2[38][182];
//			centrovac3[6] = vac3[38][182];
//		}
//		if (useCurtose) {
//			centroskc1[0] = skc1[95][190];
//			centroskc2[0] = skc2[95][190];
//			centroskc3[0] = skc3[95][190];
//			centroskc1[1] = skc1[199][107];
//			centroskc2[1] = skc2[199][107];
//			centroskc3[1] = skc3[199][107];
//			centroskc1[2] = skc1[177][147];
//			centroskc2[2] = skc2[177][147];
//			centroskc3[2] = skc3[177][147];
//			centroskc1[3] = skc1[153][56];
//			centroskc2[3] = skc2[153][56];
//			centroskc3[3] = skc3[153][56];
//			centroskc1[4] = skc1[332][55];
//			centroskc2[4] = skc2[332][55];
//			centroskc3[4] = skc3[332][55];
//			centroskc1[5] = skc1[315][4];
//			centroskc2[5] = skc2[315][4];
//			centroskc3[5] = skc3[315][4];
//			centroskc1[6] = skc1[38][182];
//			centroskc2[6] = skc2[38][182];
//			centroskc3[6] = skc3[38][182];
//		}
//		if (useSkewness) {
//			centrocsc1[0] = csc1[95][190];
//			centrocsc2[0] = csc2[95][190];
//			centrocsc3[0] = csc3[95][190];
//			centrocsc1[1] = csc1[199][107];
//			centrocsc2[1] = csc2[199][107];
//			centrocsc3[1] = csc3[199][107];
//			centrocsc1[2] = csc1[177][147];
//			centrocsc2[2] = csc2[177][147];
//			centrocsc3[2] = csc3[177][147];
//			centrocsc1[3] = csc1[153][56];
//			centrocsc2[3] = csc2[153][56];
//			centrocsc3[3] = csc3[153][56];
//			centrocsc1[4] = csc1[332][55];
//			centrocsc2[4] = csc2[332][55];
//			centrocsc3[4] = csc3[332][55];
//			centrocsc1[5] = csc1[315][4];
//			centrocsc2[5] = csc2[315][4];
//			centrocsc3[5] = csc3[315][4];
//			centrocsc1[6] = csc1[38][182];
//			centrocsc2[6] = csc2[38][182];
//			centrocsc3[6] = csc3[38][182];
//		}
		
		// *** Declarando e inicializando as estruturas que calcularão os novos centróides
		float[] somaNovoCentroc1 = new float[n];
		float[] somaNovoCentroc2 = new float[n];
		float[] somaNovoCentroc3 = new float[n];
		float[] somaNovoCentromec1 = new float[n];
		float[] somaNovoCentromec2 = new float[n];
		float[] somaNovoCentromec3 = new float[n];
		float[] somaNovoCentrovac1 = new float[n];
		float[] somaNovoCentrovac2 = new float[n];
		float[] somaNovoCentrovac3 = new float[n];
		float[] somaNovoCentroskc1 = new float[n];
		float[] somaNovoCentroskc2 = new float[n];
		float[] somaNovoCentroskc3 = new float[n];
		float[] somaNovoCentrocsc1 = new float[n];
		float[] somaNovoCentrocsc2 = new float[n];
		float[] somaNovoCentrocsc3 = new float[n];
		int[] count = new int[n];
		for (int i = 0; i < n; i++) {
			somaNovoCentroc1[i]= 0;
			somaNovoCentroc2[i]= 0;
			somaNovoCentroc3[i]= 0;
			somaNovoCentromec1[i]= 0;
			somaNovoCentromec2[i]= 0;
			somaNovoCentromec3[i]= 0;
			somaNovoCentrovac1[i]= 0;
			somaNovoCentrovac2[i]= 0;
			somaNovoCentrovac3[i]= 0;
			somaNovoCentroskc1[i]= 0;
			somaNovoCentroskc2[i]= 0;
			somaNovoCentroskc3[i]= 0;
			somaNovoCentrocsc1[i]= 0;
			somaNovoCentrocsc2[i]= 0;
			somaNovoCentrocsc3[i]= 0;
			count[i] = 0;
		}
		// *** Declarando e inicializando os vetores de classe
		int[][] k = new int[image.getWidth()][image.getHeight()];
		int[][] oldk = new int[image.getWidth()][image.getHeight()];
		int changek = 0;
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				k[x][y] = 0;
				oldk[x][y] = 0;
			}
		}
		
		// **************************
		// *** Corpo do Algoritmo ***
		// **************************
		
		// *** Cálculo das distâncias euclidianas ***
		for (int itera = 0; itera < 500; itera++) {
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					float maiorDist = 32765;
					
					for (int i = 0; i < n; i++) {
						float soma = 0;
						if (useColor) {
							soma += Math.pow(c1[x][y] - centroc1[i], 2.0);
							soma += Math.pow(c2[x][y] - centroc2[i], 2.0);
							soma += Math.pow(c3[x][y] - centroc3[i], 2.0);
						}
						if (useMedia) {
							soma += Math.pow(mec1[x][y] - centromec1[i], 2.0);
							soma += Math.pow(mec2[x][y] - centromec2[i], 2.0);
							soma += Math.pow(mec3[x][y] - centromec3[i], 2.0);
						}
						if (useVariancia) {
							soma += Math.pow(vac1[x][y] - centrovac1[i], 2.0);
							soma += Math.pow(vac2[x][y] - centrovac2[i], 2.0);
							soma += Math.pow(vac3[x][y] - centrovac3[i], 2.0);
						}
						if (useSkewness) {
							soma += Math.pow(skc1[x][y] - centroskc1[i], 2.0);
							soma += Math.pow(skc2[x][y] - centroskc2[i], 2.0);
							soma += Math.pow(skc3[x][y] - centroskc3[i], 2.0);
						}
						if (useCurtose) {
							soma += Math.pow(csc1[x][y] - centrocsc1[i], 2.0);
							soma += Math.pow(csc2[x][y] - centrocsc2[i], 2.0);
							soma += Math.pow(csc3[x][y] - centrocsc3[i], 2.0);
						}
						float dist = (float) Math.sqrt(soma);
						
						if (dist < maiorDist) {
							maiorDist = dist;
							k[x][y] = i;
						}
					}
					
					if (k[x][y] != oldk[x][y]) {
						changek++;
						oldk[x][y] = k[x][y];
					}
					
					// Somando para o cálculo dos novos centróides
					if (useColor) {
						somaNovoCentroc1[k[x][y]] += c1[x][y];
						somaNovoCentroc2[k[x][y]] += c2[x][y];
						somaNovoCentroc3[k[x][y]] += c3[x][y];
					}
					if (useMedia) {
						somaNovoCentromec1[k[x][y]] += mec1[x][y];
						somaNovoCentromec2[k[x][y]] += mec2[x][y];
						somaNovoCentromec3[k[x][y]] += mec3[x][y];
					}
					if (useVariancia) {
						somaNovoCentrovac1[k[x][y]] += vac1[x][y];
						somaNovoCentrovac2[k[x][y]] += vac2[x][y];
						somaNovoCentrovac3[k[x][y]] += vac3[x][y];
					}
					if (useSkewness) {
						somaNovoCentroskc1[k[x][y]] += skc1[x][y];
						somaNovoCentroskc2[k[x][y]] += skc2[x][y];
						somaNovoCentroskc3[k[x][y]] += skc3[x][y];
					}
					if (useCurtose) {
						somaNovoCentrocsc1[k[x][y]] += csc1[x][y];
						somaNovoCentrocsc2[k[x][y]] += csc2[x][y];
						somaNovoCentrocsc3[k[x][y]] += csc3[x][y];
					}
					count[k[x][y]]++;
				}
			}
			// *** Cálculo dos novos centróides
			for (int i = 0; i < n; i++) {
				if (useColor) {
					centroc1[i] = somaNovoCentroc1[i] / count[i];
					centroc2[i] = somaNovoCentroc2[i] / count[i];
					centroc3[i] = somaNovoCentroc3[i] / count[i];
				}
				if (useMedia) {
					centromec1[i] = somaNovoCentromec1[i] / count[i];
					centromec2[i] = somaNovoCentromec2[i] / count[i];
					centromec3[i] = somaNovoCentromec3[i] / count[i];
				}
				if (useVariancia) {
					centrovac1[i] = somaNovoCentrovac1[i] / count[i];
					centrovac2[i] = somaNovoCentrovac2[i] / count[i];
					centrovac3[i] = somaNovoCentrovac3[i] / count[i];
				}
				if (useSkewness) {
					centroskc1[i] = somaNovoCentroskc1[i] / count[i];
					centroskc2[i] = somaNovoCentroskc2[i] / count[i];
					centroskc3[i] = somaNovoCentroskc3[i] / count[i];
				}
				if (useCurtose) {
					centrocsc1[i] = somaNovoCentrocsc1[i] / count[i];
					centrocsc2[i] = somaNovoCentrocsc2[i] / count[i];
					centrocsc3[i] = somaNovoCentrocsc3[i] / count[i];
				}
			}
			for (int i = 0; i < n; i++) {
				somaNovoCentroc1[i]= 0;
				somaNovoCentroc2[i]= 0;
				somaNovoCentroc3[i]= 0;
				somaNovoCentromec1[i]= 0;
				somaNovoCentromec2[i]= 0;
				somaNovoCentromec3[i]= 0;
				somaNovoCentrovac1[i]= 0;
				somaNovoCentrovac2[i]= 0;
				somaNovoCentrovac3[i]= 0;
				somaNovoCentroskc1[i]= 0;
				somaNovoCentroskc2[i]= 0;
				somaNovoCentroskc3[i]= 0;
				somaNovoCentrocsc1[i]= 0;
				somaNovoCentrocsc2[i]= 0;
				somaNovoCentrocsc3[i]= 0;
				count[i] = 0;
			}
			System.out.println("Changes: " + changek);
			if (changek < image.getWidth()*image.getHeight()*0.001)
				break;
			changek = 0;
		}
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				out.setRGB(x, y, cores[k[x][y]].getRGB());
			}
		}
		
		Conf.classes = new int[image.getWidth()][image.getHeight()];
		Conf.classes = k;
		
		return out;
	}
	
	private void initCores() {
		cores[0] = Color.red;
		cores[1] = Color.green;
		cores[2] = Color.BLUE;
		cores[3] = Color.YELLOW;
		cores[4] = Color.CYAN;
		cores[5] = Color.MAGENTA;
		cores[6] = Color.ORANGE;
		cores[7] = Color.WHITE;
		cores[8] = Color.DARK_GRAY;
		cores[9] = Color.BLACK;
	}
	
	private void initCaracteristicas(BufferedImage image, int ec) {		
		c1 = new float[image.getWidth()][image.getHeight()];
		c2 = new float[image.getWidth()][image.getHeight()];
		c3 = new float[image.getWidth()][image.getHeight()];
		if (useMedia || useVariancia || useSkewness || useCurtose) {
			mec1 = new float[image.getWidth()][image.getHeight()];
			mec2 = new float[image.getWidth()][image.getHeight()];
			mec3 = new float[image.getWidth()][image.getHeight()];
		}
		if (useVariancia) {
			vac1 = new float[image.getWidth()][image.getHeight()];
			vac2 = new float[image.getWidth()][image.getHeight()];
			vac3 = new float[image.getWidth()][image.getHeight()];
		}
		if (useSkewness) {
			skc1 = new float[image.getWidth()][image.getHeight()];
			skc2 = new float[image.getWidth()][image.getHeight()];
			skc3 = new float[image.getWidth()][image.getHeight()];
		}
		if (useCurtose) {
			csc1 = new float[image.getWidth()][image.getHeight()];
			csc2 = new float[image.getWidth()][image.getHeight()];
			csc3 = new float[image.getWidth()][image.getHeight()];
		}
		
		switch(ec) {
		case 0:
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					Color c = new Color(image.getRGB(x, y));
					
					c1[x][y] = c.getRed();
					c2[x][y] = c.getGreen();
					c3[x][y] = c.getBlue();
				}
			}
			break;
		case 1:
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					Color c = new Color(image.getRGB(x, y));
					
					c1[x][y] =  (float) (0.257*c.getRed() + 0.504*c.getBlue() + 0.098*c.getGreen() + 16);
					c2[x][y] = (float) (-0.148*c.getRed() - 0.291*c.getGreen() + 0.439*c.getBlue() + 128);
					c3[x][y] =  (float) (0.439*c.getRed() - 0.368*c.getGreen() - 0.071*c.getBlue() + 128);
				}
			}
			break;
		case 2:
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					Color c = new Color(image.getRGB(x, y));
					
					float[] hsv = new float[3];
					Color.RGBtoHSB(c.getRed(),c.getGreen(),c.getBlue(),hsv);
					
					c1[x][y] = hsv[0];
					c2[x][y] = hsv[1];
					c3[x][y] = hsv[2];
				}
			}
			break;
		case 3:
			break;
		}
		
		if (useMedia || useVariancia || useSkewness || useCurtose) {
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1) {
						mec1[x][y] = c1[x][y];
						mec2[x][y] = c2[x][y];
						mec3[x][y] = c3[x][y];
					} else {
						float[][] color = new float[9][3];
						color[0][0] = c1[x-1][y-1];
						color[0][1] = c2[x-1][y-1];
						color[0][2] = c3[x-1][y-1];
						color[1][0] = c1[x][y-1];
						color[1][1] = c2[x][y-1];
						color[1][2] = c3[x][y-1];
						color[2][0] = c1[x+1][y-1];
						color[2][1] = c2[x+1][y-1];
						color[2][2] = c3[x+1][y-1];
						color[3][0] = c1[x-1][y];
						color[3][1] = c2[x-1][y];
						color[3][2] = c3[x-1][y];
						color[4][0] = c1[x][y];
						color[4][1] = c2[x][y];
						color[4][2] = c3[x][y];
						color[5][0] = c1[x+1][y];
						color[5][1] = c2[x+1][y];
						color[5][2] = c3[x+1][y];
						color[6][0] = c1[x-1][y+1];
						color[6][1] = c2[x-1][y+1];
						color[6][2] = c3[x-1][y+1];
						color[7][0] = c1[x][y+1];
						color[7][1] = c2[x][y+1];
						color[7][2] = c3[x][y+1];
						color[8][0] = c1[x+1][y+1];
						color[8][1] = c2[x+1][y+1];
						color[8][2] = c3[x+1][y+1];
						
						float somaR = 0;
						float somaG = 0;
						float somaB = 0;
						for (int i = 0; i < 9; i++) {
							somaR += color[i][0];
							somaG += color[i][1];
							somaB += color[i][2];
						}	
						mec1[x][y] = somaR / 9;
						mec2[x][y] = somaG / 9;
						mec3[x][y] = somaB / 9;
					}
				}
			}
		}
		if (useVariancia) {
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1) {
						vac1[x][y] = c1[x][y];
						vac2[x][y] = c2[x][y];
						vac3[x][y] = c3[x][y];
					} else {
						float[][] color = new float[9][3];
						color[0][0] = c1[x-1][y-1];
						color[0][1] = c2[x-1][y-1];
						color[0][2] = c3[x-1][y-1];
						color[1][0] = c1[x][y-1];
						color[1][1] = c2[x][y-1];
						color[1][2] = c3[x][y-1];
						color[2][0] = c1[x+1][y-1];
						color[2][1] = c2[x+1][y-1];
						color[2][2] = c3[x+1][y-1];
						color[3][0] = c1[x-1][y];
						color[3][1] = c2[x-1][y];
						color[3][2] = c3[x-1][y];
						color[4][0] = c1[x][y];
						color[4][1] = c2[x][y];
						color[4][2] = c3[x][y];
						color[5][0] = c1[x+1][y];
						color[5][1] = c2[x+1][y];
						color[5][2] = c3[x+1][y];
						color[6][0] = c1[x-1][y+1];
						color[6][1] = c2[x-1][y+1];
						color[6][2] = c3[x-1][y+1];
						color[7][0] = c1[x][y+1];
						color[7][1] = c2[x][y+1];
						color[7][2] = c3[x][y+1];
						color[8][0] = c1[x+1][y+1];
						color[8][1] = c2[x+1][y+1];
						color[8][2] = c3[x+1][y+1];
						float somaR = 0;
						float somaG = 0;
						float somaB = 0;
						for (int i = 0; i < 9; i++) {
							somaR += Math.pow(color[i][0] - mec1[x][y], 2.0);
							somaG += Math.pow(color[i][1] - mec2[x][y], 2.0);
							somaB += Math.pow(color[i][2] - mec3[x][y], 2.0);
						}	
						vac1[x][y] = somaR / 9;
						vac2[x][y] = somaG / 9;
						vac3[x][y] = somaB / 9;
					}
				}
			}
		}
		if (useSkewness) {
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1) {
						skc1[x][y] = c1[x][y];
						skc2[x][y] = c2[x][y];
						skc3[x][y] = c3[x][y];
					} else {
						float[][] color = new float[9][3];
						color[0][0] = c1[x-1][y-1];
						color[0][1] = c2[x-1][y-1];
						color[0][2] = c3[x-1][y-1];
						color[1][0] = c1[x][y-1];
						color[1][1] = c2[x][y-1];
						color[1][2] = c3[x][y-1];
						color[2][0] = c1[x+1][y-1];
						color[2][1] = c2[x+1][y-1];
						color[2][2] = c3[x+1][y-1];
						color[3][0] = c1[x-1][y];
						color[3][1] = c2[x-1][y];
						color[3][2] = c3[x-1][y];
						color[4][0] = c1[x][y];
						color[4][1] = c2[x][y];
						color[4][2] = c3[x][y];
						color[5][0] = c1[x+1][y];
						color[5][1] = c2[x+1][y];
						color[5][2] = c3[x+1][y];
						color[6][0] = c1[x-1][y+1];
						color[6][1] = c2[x-1][y+1];
						color[6][2] = c3[x-1][y+1];
						color[7][0] = c1[x][y+1];
						color[7][1] = c2[x][y+1];
						color[7][2] = c3[x][y+1];
						color[8][0] = c1[x+1][y+1];
						color[8][1] = c2[x+1][y+1];
						color[8][2] = c3[x+1][y+1];
						float somaR1 = 0;
						float somaG1 = 0;
						float somaB1 = 0;
						float somaR2 = 0;
						float somaG2 = 0;
						float somaB2 = 0;
						for (int i = 0; i < 9; i++) {
							somaR1 += Math.pow(color[i][0] - mec1[x][y], 3.0);
							somaG1 += Math.pow(color[i][1] - mec2[x][y], 3.0);
							somaB1 += Math.pow(color[i][2] - mec3[x][y], 3.0);
							somaR2 += Math.pow(color[i][0] - mec1[x][y], 2.0);
							somaG2 += Math.pow(color[i][1] - mec2[x][y], 2.0);
							somaB2 += Math.pow(color[i][2] - mec3[x][y], 2.0);
						}
						skc1[x][y] = (float) ((somaR1 / 9) / Math.pow((somaR2 / 9), 1.5));
						skc2[x][y] = (float) ((somaG1 / 9) / Math.pow((somaG2 / 9), 1.5));
						skc3[x][y] = (float) ((somaB1 / 9) / Math.pow((somaB2 / 9), 1.5));
					}
				}
			}
		}
		if (useCurtose) {
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1) {
						csc1[x][y] = c1[x][y];
						csc2[x][y] = c2[x][y];
						csc3[x][y] = c3[x][y];
					} else {
						float[][] color = new float[9][3];
						color[0][0] = c1[x-1][y-1];
						color[0][1] = c2[x-1][y-1];
						color[0][2] = c3[x-1][y-1];
						color[1][0] = c1[x][y-1];
						color[1][1] = c2[x][y-1];
						color[1][2] = c3[x][y-1];
						color[2][0] = c1[x+1][y-1];
						color[2][1] = c2[x+1][y-1];
						color[2][2] = c3[x+1][y-1];
						color[3][0] = c1[x-1][y];
						color[3][1] = c2[x-1][y];
						color[3][2] = c3[x-1][y];
						color[4][0] = c1[x][y];
						color[4][1] = c2[x][y];
						color[4][2] = c3[x][y];
						color[5][0] = c1[x+1][y];
						color[5][1] = c2[x+1][y];
						color[5][2] = c3[x+1][y];
						color[6][0] = c1[x-1][y+1];
						color[6][1] = c2[x-1][y+1];
						color[6][2] = c3[x-1][y+1];
						color[7][0] = c1[x][y+1];
						color[7][1] = c2[x][y+1];
						color[7][2] = c3[x][y+1];
						color[8][0] = c1[x+1][y+1];
						color[8][1] = c2[x+1][y+1];
						color[8][2] = c3[x+1][y+1];
						float somaR1 = 0;
						float somaG1 = 0;
						float somaB1 = 0;
						float somaR2 = 0;
						float somaG2 = 0;
						float somaB2 = 0;
						for (int i = 0; i < 9; i++) {
							somaR1 += Math.pow(color[i][0] - mec1[x][y], 4.0);
							somaG1 += Math.pow(color[i][1] - mec2[x][y], 4.0);
							somaB1 += Math.pow(color[i][2] - mec3[x][y], 4.0);
							somaR2 += Math.pow(color[i][0] - mec1[x][y], 2.0);
							somaG2 += Math.pow(color[i][1] - mec2[x][y], 2.0);
							somaB2 += Math.pow(color[i][2] - mec3[x][y], 2.0);
						}
						csc1[x][y] = (float) ((somaR1 / 9) / Math.pow((somaR2 / 9), 2.0));
						csc2[x][y] = (float) ((somaG1 / 9) / Math.pow((somaG2 / 9), 2.0));
						csc3[x][y] = (float) ((somaB1 / 9) / Math.pow((somaB2 / 9), 2.0));
					}
				}
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(), image.getHeight());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	public BufferedImage getImage() {
		return image;
	}
	
}
