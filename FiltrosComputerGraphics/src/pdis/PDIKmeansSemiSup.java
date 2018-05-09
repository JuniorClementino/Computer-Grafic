package pdis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

import interfaces.ImageInterface;
import swing.Conf;

@SuppressWarnings("serial")
public class PDIKmeansSemiSup extends JComponent implements ImageInterface {
	private BufferedImage image;
	private Color[] cores = new Color[10];

	//Características para as componentes RGB
	private float[][] r;
	private float[][] g;
	private float[][] b;
	private float[][] mer;
	private float[][] meg;
	private float[][] meb;
	private float[][] var;
	private float[][] vag;
	private float[][] vab;
	private float[][] skr;
	private float[][] skg;
	private float[][] skb;
	private float[][] csr;
	private float[][] csg;
	private float[][] csb;
	//Características para as componentes YCrCb
	private float[][] cy;
	private float[][] cr;
	private float[][] cb;
	private float[][] mey;
	private float[][] mecr;
	private float[][] mecb;
	private float[][] vay;
	private float[][] vacr;
	private float[][] vacb;
	private float[][] sky;
	private float[][] skcr;
	private float[][] skcb;
	private float[][] csy;
	private float[][] cscr;
	private float[][] cscb;
	//Características para as componentes HSV
	private float[][] h;
	private float[][] s;
	private float[][] v;
	private float[][] meh;
	private float[][] mes;
	private float[][] mev;
	private float[][] vah;
	private float[][] vas;
	private float[][] vav;
	private float[][] skh;
	private float[][] sks;
	private float[][] skv;
	private float[][] csh;
	private float[][] css;
	private float[][] csv;
	
	boolean useColor = Conf.feature_color;
	
	boolean useColorR = Conf.feature_color_R;
	boolean useColorG = Conf.feature_color_G;
	boolean useColorB = Conf.feature_color_B;
	boolean useColorY = Conf.feature_color_Y;
	boolean useColorCr = Conf.feature_color_Cr;
	boolean useColorCb = Conf.feature_color_Cb;
	boolean useColorH = Conf.feature_color_H;
	boolean useColorS = Conf.feature_color_S;
	boolean useColorV = Conf.feature_color_V;
	
	boolean useMedia = Conf.feature_media;
	boolean useVariancia = Conf.feature_variancia;
	boolean useSkewness = Conf.feature_skewness;
	boolean useCurtose = Conf.feature_curtose;
	
	public PDIKmeansSemiSup(BufferedImage img, int classes) {
		initCores();
		initCaracteristicas(img, Conf.color_spaces);
		image = processaKmeans(img, classes);
	}
	
	private BufferedImage processaKmeans(BufferedImage image, int n) {
		BufferedImage out = new BufferedImage(image.getWidth(), image.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		
		// ************************************
		// *** Declarações e inicializações ***
		// ************************************
		
		// *** Declarando e definindo os primeiros centróides
		float[] centror = new float[n];
		float[] centromer = new float[n];
		float[] centrovar = new float[n];
		float[] centroskr = new float[n];
		float[] centrocsr = new float[n];
		float[] centrog = new float[n];
		float[] centromeg = new float[n];
		float[] centrovag = new float[n];
		float[] centroskg = new float[n];
		float[] centrocsg = new float[n];
		float[] centrob = new float[n];
		float[] centromeb = new float[n];
		float[] centrovab = new float[n];
		float[] centroskb = new float[n];
		float[] centrocsb = new float[n];
		float[] centroy = new float[n];
		float[] centromey = new float[n];
		float[] centrovay = new float[n];
		float[] centrosky = new float[n];
		float[] centrocsy = new float[n];
		float[] centrocr = new float[n];
		float[] centromecr = new float[n];
		float[] centrovacr = new float[n];
		float[] centroskcr = new float[n];
		float[] centrocscr = new float[n];
		float[] centrocb = new float[n];
		float[] centromecb = new float[n];
		float[] centrovacb = new float[n];
		float[] centroskcb = new float[n];
		float[] centrocscb = new float[n];
		float[] centroh = new float[n];
		float[] centromeh = new float[n];
		float[] centrovah = new float[n];
		float[] centroskh = new float[n];
		float[] centrocsh = new float[n];
		float[] centros = new float[n];
		float[] centromes = new float[n];
		float[] centrovas = new float[n];
		float[] centrosks = new float[n];
		float[] centrocss = new float[n];
		float[] centrov = new float[n];
		float[] centromev = new float[n];
		float[] centrovav = new float[n];
		float[] centroskv = new float[n];
		float[] centrocsv = new float[n];
		
		for (int i = 0; i < n; i++) {
			if (useColorR) {
				centror[i] = r[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useMedia)
					centromer[i] = mer[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useVariancia)
					centrovar[i] = var[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useSkewness)
					centroskr[i] = skr[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useCurtose)
					centrocsr[i] = csr[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
			}
			if (useColorG) {
				centrog[i] = g[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useMedia)
					centromeg[i] = meg[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useVariancia)
					centrovag[i] = vag[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useSkewness)
					centroskg[i] = skg[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useCurtose)
					centrocsg[i] = csg[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
			}
			if (useColorB) {
				centrob[i] = b[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useMedia)
					centromeb[i] = meb[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useVariancia)
					centrovab[i] = vab[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useSkewness)
					centroskb[i] = skb[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useCurtose)
					centrocsb[i] = csb[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
			}
			if (useColorY) {
				centroy[i] = cy[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useMedia)
					centromey[i] = mey[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useVariancia)
					centrovay[i] = vay[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useSkewness)
					centrosky[i] = sky[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useCurtose)
					centrocsy[i] = csy[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
			}
			if (useColorCr) {
				centrocr[i] = cr[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useMedia)
					centromecr[i] = mecr[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useVariancia)
					centrovacr[i] = vacr[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useSkewness)
					centroskcr[i] = skcr[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useCurtose)
					centrocscr[i] = cscr[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
			}
			if (useColorCb) {
				centrocb[i] = cb[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useMedia)
					centromecb[i] = mecb[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useVariancia)
					centrovacb[i] = vacb[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useSkewness)
					centroskcb[i] = skcb[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useCurtose)
					centrocscb[i] = cscb[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
			}
			if (useColorH) {
				centroh[i] = h[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useMedia)
					centromeh[i] = meh[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useVariancia)
					centrovah[i] = vah[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useSkewness)
					centroskh[i] = skh[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useCurtose)
					centrocsh[i] = csh[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
			}
			if (useColorS) {
				centros[i] = s[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useMedia)
					centromes[i] = mes[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useVariancia)
					centrovas[i] = vas[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useSkewness)
					centrosks[i] = sks[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useCurtose)
					centrocss[i] = css[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
			}
			if (useColorV) {
				centrov[i] = v[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useMedia)
					centromev[i] = mev[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useVariancia)
					centrovav[i] = vav[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useSkewness)
					centroskv[i] = skv[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
				if (useCurtose)
					centrocsv[i] = csv[Conf.kmeans_centroides[i][0]][Conf.kmeans_centroides[i][1]];
			}
		}

		// *** Declarando e inicializando as estruturas que calcularão os novos centróides
		float[] somaNovoCentror = new float[n];
		float[] somaNovoCentromer = new float[n];
		float[] somaNovoCentrovar = new float[n];
		float[] somaNovoCentroskr = new float[n];
		float[] somaNovoCentrocsr = new float[n];
		float[] somaNovoCentrog = new float[n];
		float[] somaNovoCentromeg = new float[n];
		float[] somaNovoCentrovag = new float[n];
		float[] somaNovoCentroskg = new float[n];
		float[] somaNovoCentrocsg = new float[n];
		float[] somaNovoCentrob = new float[n];
		float[] somaNovoCentromeb = new float[n];
		float[] somaNovoCentrovab = new float[n];
		float[] somaNovoCentroskb = new float[n];
		float[] somaNovoCentrocsb = new float[n];
		float[] somaNovoCentroy = new float[n];
		float[] somaNovoCentromey = new float[n];
		float[] somaNovoCentrovay = new float[n];
		float[] somaNovoCentrosky = new float[n];
		float[] somaNovoCentrocsy = new float[n];
		float[] somaNovoCentrocr = new float[n];
		float[] somaNovoCentromecr = new float[n];
		float[] somaNovoCentrovacr = new float[n];
		float[] somaNovoCentroskcr = new float[n];
		float[] somaNovoCentrocscr = new float[n];
		float[] somaNovoCentrocb = new float[n];
		float[] somaNovoCentromecb = new float[n];
		float[] somaNovoCentrovacb = new float[n];
		float[] somaNovoCentroskcb = new float[n];
		float[] somaNovoCentrocscb = new float[n];
		float[] somaNovoCentroh = new float[n];
		float[] somaNovoCentromeh = new float[n];
		float[] somaNovoCentrovah = new float[n];
		float[] somaNovoCentroskh = new float[n];
		float[] somaNovoCentrocsh = new float[n];
		float[] somaNovoCentros = new float[n];
		float[] somaNovoCentromes = new float[n];
		float[] somaNovoCentrovas = new float[n];
		float[] somaNovoCentrosks = new float[n];
		float[] somaNovoCentrocss = new float[n];
		float[] somaNovoCentrov = new float[n];
		float[] somaNovoCentromev = new float[n];
		float[] somaNovoCentrovav = new float[n];
		float[] somaNovoCentroskv = new float[n];
		float[] somaNovoCentrocsv = new float[n];
		int[] count = new int[n];
		for (int i = 0; i < n; i++) {
			somaNovoCentror[i]= 0;
			somaNovoCentromer[i]= 0;
			somaNovoCentrovar[i]= 0;
			somaNovoCentroskr[i]= 0;
			somaNovoCentrocsr[i]= 0;
			somaNovoCentrog[i]= 0;
			somaNovoCentromeg[i]= 0;
			somaNovoCentrovag[i]= 0;
			somaNovoCentroskg[i]= 0;
			somaNovoCentrocsg[i]= 0;
			somaNovoCentrob[i]= 0;
			somaNovoCentromeb[i]= 0;
			somaNovoCentrovab[i]= 0;
			somaNovoCentroskb[i]= 0;
			somaNovoCentrocsb[i]= 0;
			somaNovoCentroy[i]= 0;
			somaNovoCentromey[i]= 0;
			somaNovoCentrovay[i]= 0;
			somaNovoCentrosky[i]= 0;
			somaNovoCentrocsy[i]= 0;
			somaNovoCentrocr[i]= 0;
			somaNovoCentromecr[i]= 0;
			somaNovoCentrovacr[i]= 0;
			somaNovoCentroskcr[i]= 0;
			somaNovoCentrocscr[i]= 0;
			somaNovoCentrocb[i]= 0;
			somaNovoCentromecb[i]= 0;
			somaNovoCentrovacb[i]= 0;
			somaNovoCentroskcb[i]= 0;
			somaNovoCentrocscb[i]= 0;
			somaNovoCentroh[i]= 0;
			somaNovoCentromeh[i]= 0;
			somaNovoCentrovah[i]= 0;
			somaNovoCentroskh[i]= 0;
			somaNovoCentrocsh[i]= 0;
			somaNovoCentros[i]= 0;
			somaNovoCentromes[i]= 0;
			somaNovoCentrovas[i]= 0;
			somaNovoCentrosks[i]= 0;
			somaNovoCentrocss[i]= 0;
			somaNovoCentrov[i]= 0;
			somaNovoCentromev[i]= 0;
			somaNovoCentrovav[i]= 0;
			somaNovoCentroskv[i]= 0;
			somaNovoCentrocsv[i]= 0;
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
						
						if (useColorR) {
							soma += Math.pow(r[x][y] - centror[i], 2.0);
							if (useMedia)
								soma += Math.pow(mer[x][y] - centromer[i], 2.0);
							if (useVariancia)
								soma += Math.pow(var[x][y] - centrovar[i], 2.0);
							if (useSkewness)
								soma += Math.pow(skr[x][y] - centroskr[i], 2.0);
							if (useCurtose)
								soma += Math.pow(csr[x][y] - centrocsr[i], 2.0);
						}
						if (useColorG) {
							soma += Math.pow(g[x][y] - centrog[i], 2.0);
							if (useMedia)
								soma += Math.pow(meg[x][y] - centromeg[i], 2.0);
							if (useVariancia)
								soma += Math.pow(vag[x][y] - centrovag[i], 2.0);
							if (useSkewness)
								soma += Math.pow(skg[x][y] - centroskg[i], 2.0);
							if (useCurtose)
								soma += Math.pow(csg[x][y] - centrocsg[i], 2.0);
						}
						if (useColorB) {
							soma += Math.pow(b[x][y] - centrob[i], 2.0);
							if (useMedia)
								soma += Math.pow(meb[x][y] - centromeb[i], 2.0);
							if (useVariancia)
								soma += Math.pow(vab[x][y] - centrovab[i], 2.0);
							if (useSkewness)
								soma += Math.pow(skb[x][y] - centroskb[i], 2.0);
							if (useCurtose)
								soma += Math.pow(csb[x][y] - centrocsb[i], 2.0);
						}
						if (useColorY) {
							soma += Math.pow(cy[x][y] - centroy[i], 2.0);
							if (useMedia)
								soma += Math.pow(mey[x][y] - centromey[i], 2.0);
							if (useVariancia)
								soma += Math.pow(vay[x][y] - centrovay[i], 2.0);
							if (useSkewness)
								soma += Math.pow(sky[x][y] - centrosky[i], 2.0);
							if (useCurtose)
								soma += Math.pow(csy[x][y] - centrocsy[i], 2.0);
						}
						if (useColorCr) {
							soma += Math.pow(cr[x][y] - centrocr[i], 2.0);
							if (useMedia)
								soma += Math.pow(mecr[x][y] - centromecr[i], 2.0);
							if (useVariancia)
								soma += Math.pow(vacr[x][y] - centrovacr[i], 2.0);
							if (useSkewness)
								soma += Math.pow(skcr[x][y] - centroskcr[i], 2.0);
							if (useCurtose)
								soma += Math.pow(cscr[x][y] - centrocscr[i], 2.0);
						}
						if (useColorCb) {
							soma += Math.pow(cb[x][y] - centrocb[i], 2.0);
							if (useMedia)
								soma += Math.pow(mecb[x][y] - centromecb[i], 2.0);
							if (useVariancia)
								soma += Math.pow(vacb[x][y] - centrovacb[i], 2.0);
							if (useSkewness)
								soma += Math.pow(skcb[x][y] - centroskcb[i], 2.0);
							if (useCurtose)
								soma += Math.pow(cscb[x][y] - centrocscb[i], 2.0);
						}
						if (useColorH) {
							soma += Math.pow(h[x][y] - centroh[i], 2.0);
							if (useMedia)
								soma += Math.pow(meh[x][y] - centromeh[i], 2.0);
							if (useVariancia)
								soma += Math.pow(vah[x][y] - centrovah[i], 2.0);
							if (useSkewness)
								soma += Math.pow(skh[x][y] - centroskh[i], 2.0);
							if (useCurtose)
								soma += Math.pow(csh[x][y] - centrocsh[i], 2.0);
						}
						if (useColorS) {
							soma += Math.pow(s[x][y] - centros[i], 2.0);
							if (useMedia)
								soma += Math.pow(mes[x][y] - centromes[i], 2.0);
							if (useVariancia)
								soma += Math.pow(vas[x][y] - centrovas[i], 2.0);
							if (useSkewness)
								soma += Math.pow(sks[x][y] - centrosks[i], 2.0);
							if (useCurtose)
								soma += Math.pow(css[x][y] - centrocss[i], 2.0);
						}
						if (useColorV) {
							soma += Math.pow(v[x][y] - centrov[i], 2.0);
							if (useMedia)
								soma += Math.pow(mev[x][y] - centromev[i], 2.0);
							if (useVariancia)
								soma += Math.pow(vav[x][y] - centrovav[i], 2.0);
							if (useSkewness)
								soma += Math.pow(skv[x][y] - centroskv[i], 2.0);
							if (useCurtose)
								soma += Math.pow(csv[x][y] - centrocsv[i], 2.0);
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
					
					if (useColorR) {
						somaNovoCentror[k[x][y]] += r[x][y];
						if (useMedia)
							somaNovoCentromer[k[x][y]] += mer[x][y];
						if (useVariancia)
							somaNovoCentrovar[k[x][y]] += var[x][y];
						if (useSkewness)
							somaNovoCentroskr[k[x][y]] += skr[x][y];
						if (useCurtose)
							somaNovoCentrocsr[k[x][y]] += csr[x][y];
					}
					if (useColorG) {
						somaNovoCentrog[k[x][y]] += g[x][y];
						if (useMedia)
							somaNovoCentromeg[k[x][y]] += meg[x][y];
						if (useVariancia)
							somaNovoCentrovag[k[x][y]] += vag[x][y];
						if (useSkewness)
							somaNovoCentroskg[k[x][y]] += skg[x][y];
						if (useCurtose)
							somaNovoCentrocsg[k[x][y]] += csg[x][y];
					}
					if (useColorB) {
						somaNovoCentrob[k[x][y]] += b[x][y];
						if (useMedia)
							somaNovoCentromeb[k[x][y]] += meb[x][y];
						if (useVariancia)
							somaNovoCentrovab[k[x][y]] += vab[x][y];
						if (useSkewness)
							somaNovoCentroskb[k[x][y]] += skb[x][y];
						if (useCurtose)
							somaNovoCentrocsb[k[x][y]] += csb[x][y];
					}
					if (useColorY) {
						somaNovoCentroy[k[x][y]] += cy[x][y];
						if (useMedia)
							somaNovoCentromey[k[x][y]] += mey[x][y];
						if (useVariancia)
							somaNovoCentrovay[k[x][y]] += vay[x][y];
						if (useSkewness)
							somaNovoCentrosky[k[x][y]] += sky[x][y];
						if (useCurtose)
							somaNovoCentrocsy[k[x][y]] += csy[x][y];
					}
					if (useColorCr) {
						somaNovoCentrocr[k[x][y]] += cr[x][y];
						if (useMedia)
							somaNovoCentromecr[k[x][y]] += mecr[x][y];
						if (useVariancia)
							somaNovoCentrovacr[k[x][y]] += vacr[x][y];
						if (useSkewness)
							somaNovoCentroskcr[k[x][y]] += skcr[x][y];
						if (useCurtose)
							somaNovoCentrocscr[k[x][y]] += cscr[x][y];
					}
					if (useColorCb) {
						somaNovoCentrocb[k[x][y]] += cb[x][y];
						if (useMedia)
							somaNovoCentromecb[k[x][y]] += mecb[x][y];
						if (useVariancia)
							somaNovoCentrovacb[k[x][y]] += vacb[x][y];
						if (useSkewness)
							somaNovoCentroskcb[k[x][y]] += skcb[x][y];
						if (useCurtose)
							somaNovoCentrocscb[k[x][y]] += cscb[x][y];
					}
					if (useColorH) {
						somaNovoCentroh[k[x][y]] += h[x][y];
						if (useMedia)
							somaNovoCentromeh[k[x][y]] += meh[x][y];
						if (useVariancia)
							somaNovoCentrovah[k[x][y]] += vah[x][y];
						if (useSkewness)
							somaNovoCentroskh[k[x][y]] += skh[x][y];
						if (useCurtose)
							somaNovoCentrocsh[k[x][y]] += csh[x][y];
					}
					if (useColorS) {
						somaNovoCentros[k[x][y]] += s[x][y];
						if (useMedia)
							somaNovoCentromes[k[x][y]] += mes[x][y];
						if (useVariancia)
							somaNovoCentrovas[k[x][y]] += vas[x][y];
						if (useSkewness)
							somaNovoCentrosks[k[x][y]] += sks[x][y];
						if (useCurtose)
							somaNovoCentrocss[k[x][y]] += css[x][y];
					}
					if (useColorV) {
						somaNovoCentrov[k[x][y]] += v[x][y];
						if (useMedia)
							somaNovoCentromev[k[x][y]] += mev[x][y];
						if (useVariancia)
							somaNovoCentrovav[k[x][y]] += vav[x][y];
						if (useSkewness)
							somaNovoCentroskv[k[x][y]] += skv[x][y];
						if (useCurtose)
							somaNovoCentrocsv[k[x][y]] += csv[x][y];
					}
					count[k[x][y]]++;
				}
			}
			
			for (int i = 0; i < n; i++) {
				if (useColorR) {
					centror[i] = somaNovoCentror[i] / count[i];
					if (useMedia)
						centromer[i] = somaNovoCentromer[i] / count[i];
					if (useVariancia)
						centrovar[i] = somaNovoCentrovar[i] / count[i];
					if (useSkewness)
						centroskr[i] = somaNovoCentroskr[i] / count[i];
					if (useCurtose)
						centrocsr[i] = somaNovoCentrocsr[i] / count[i];
				}
				if (useColorG) {
					centrog[i] = somaNovoCentrog[i] / count[i];
					if (useMedia)
						centromeg[i] = somaNovoCentromeg[i] / count[i];
					if (useVariancia)
						centrovag[i] = somaNovoCentrovag[i] / count[i];
					if (useSkewness)
						centroskg[i] = somaNovoCentroskg[i] / count[i];
					if (useCurtose)
						centrocsg[i] = somaNovoCentrocsg[i] / count[i];
				}
				if (useColorB) {
					centrob[i] = somaNovoCentrob[i] / count[i];
					if (useMedia)
						centromeb[i] = somaNovoCentromeb[i] / count[i];
					if (useVariancia)
						centrovab[i] = somaNovoCentrovab[i] / count[i];
					if (useSkewness)
						centroskb[i] = somaNovoCentroskb[i] / count[i];
					if (useCurtose)
						centrocsb[i] = somaNovoCentrocsb[i] / count[i];
				}
				if (useColorY) {
					centroy[i] = somaNovoCentroy[i] / count[i];
					if (useMedia)
						centromey[i] = somaNovoCentromey[i] / count[i];
					if (useVariancia)
						centrovay[i] = somaNovoCentrovay[i] / count[i];
					if (useSkewness)
						centrosky[i] = somaNovoCentrosky[i] / count[i];
					if (useCurtose)
						centrocsy[i] = somaNovoCentrocsy[i] / count[i];
				}
				if (useColorCr) {
					centrocr[i] = somaNovoCentrocr[i] / count[i];
					if (useMedia)
						centromecr[i] = somaNovoCentromecr[i] / count[i];
					if (useVariancia)
						centrovacr[i] = somaNovoCentrovacr[i] / count[i];
					if (useSkewness)
						centroskcr[i] = somaNovoCentroskcr[i] / count[i];
					if (useCurtose)
						centrocscr[i] = somaNovoCentrocscr[i] / count[i];
				}
				if (useColorCb) {
					centrocb[i] = somaNovoCentrocb[i] / count[i];
					if (useMedia)
						centromecb[i] = somaNovoCentromecb[i] / count[i];
					if (useVariancia)
						centrovacb[i] = somaNovoCentrovacb[i] / count[i];
					if (useSkewness)
						centroskcb[i] = somaNovoCentroskcb[i] / count[i];
					if (useCurtose)
						centrocscb[i] = somaNovoCentrocscb[i] / count[i];
				}
				if (useColorH) {
					centroh[i] = somaNovoCentroh[i] / count[i];
					if (useMedia)
						centromeh[i] = somaNovoCentromeh[i] / count[i];
					if (useVariancia)
						centrovah[i] = somaNovoCentrovah[i] / count[i];
					if (useSkewness)
						centroskh[i] = somaNovoCentroskh[i] / count[i];
					if (useCurtose)
						centrocsh[i] = somaNovoCentrocsh[i] / count[i];
				}
				if (useColorS) {
					centros[i] = somaNovoCentros[i] / count[i];
					if (useMedia)
						centromes[i] = somaNovoCentromes[i] / count[i];
					if (useVariancia)
						centrovas[i] = somaNovoCentrovas[i] / count[i];
					if (useSkewness)
						centrosks[i] = somaNovoCentrosks[i] / count[i];
					if (useCurtose)
						centrocss[i] = somaNovoCentrocss[i] / count[i];
				}
				if (useColorV) {
					centrov[i] = somaNovoCentrov[i] / count[i];
					if (useMedia)
						centromev[i] = somaNovoCentromev[i] / count[i];
					if (useVariancia)
						centrovav[i] = somaNovoCentrovav[i] / count[i];
					if (useSkewness)
						centroskv[i] = somaNovoCentroskv[i] / count[i];
					if (useCurtose)
						centrocsv[i] = somaNovoCentrocsv[i] / count[i];
				}
			}

			for (int i = 0; i < n; i++) {
				somaNovoCentror[i]= 0;
				somaNovoCentromer[i]= 0;
				somaNovoCentrovar[i]= 0;
				somaNovoCentroskr[i]= 0;
				somaNovoCentrocsr[i]= 0;
				somaNovoCentrog[i]= 0;
				somaNovoCentromeg[i]= 0;
				somaNovoCentrovag[i]= 0;
				somaNovoCentroskg[i]= 0;
				somaNovoCentrocsg[i]= 0;
				somaNovoCentrob[i]= 0;
				somaNovoCentromeb[i]= 0;
				somaNovoCentrovab[i]= 0;
				somaNovoCentroskb[i]= 0;
				somaNovoCentrocsb[i]= 0;
				somaNovoCentroy[i]= 0;
				somaNovoCentromey[i]= 0;
				somaNovoCentrovay[i]= 0;
				somaNovoCentrosky[i]= 0;
				somaNovoCentrocsy[i]= 0;
				somaNovoCentrocr[i]= 0;
				somaNovoCentromecr[i]= 0;
				somaNovoCentrovacr[i]= 0;
				somaNovoCentroskcr[i]= 0;
				somaNovoCentrocscr[i]= 0;
				somaNovoCentrocb[i]= 0;
				somaNovoCentromecb[i]= 0;
				somaNovoCentrovacb[i]= 0;
				somaNovoCentroskcb[i]= 0;
				somaNovoCentrocscb[i]= 0;
				somaNovoCentroh[i]= 0;
				somaNovoCentromeh[i]= 0;
				somaNovoCentrovah[i]= 0;
				somaNovoCentroskh[i]= 0;
				somaNovoCentrocsh[i]= 0;
				somaNovoCentros[i]= 0;
				somaNovoCentromes[i]= 0;
				somaNovoCentrovas[i]= 0;
				somaNovoCentrosks[i]= 0;
				somaNovoCentrocss[i]= 0;
				somaNovoCentrov[i]= 0;
				somaNovoCentromev[i]= 0;
				somaNovoCentrovav[i]= 0;
				somaNovoCentroskv[i]= 0;
				somaNovoCentrocsv[i]= 0;
				count[i] = 0;
			}
			System.out.println("Changes: " + changek);
			//if (changek < image.getWidth()*image.getHeight()*0.001)
				break;
			//changek = 0;
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
		if (useColorR) {
			r = new float[image.getWidth()][image.getHeight()];
			if (useMedia || useVariancia || useSkewness || useCurtose)
				mer = new float[image.getWidth()][image.getHeight()];
			if (useVariancia)
				var = new float[image.getWidth()][image.getHeight()];
			if (useSkewness)
				skr = new float[image.getWidth()][image.getHeight()];
			if (useCurtose)
				csr = new float[image.getWidth()][image.getHeight()];
		}
		if (useColorG) {
			g = new float[image.getWidth()][image.getHeight()];
			if (useMedia || useVariancia || useSkewness || useCurtose)
				meg = new float[image.getWidth()][image.getHeight()];
			if (useVariancia)
				vag = new float[image.getWidth()][image.getHeight()];
			if (useSkewness)
				skg = new float[image.getWidth()][image.getHeight()];
			if (useCurtose)
				csg = new float[image.getWidth()][image.getHeight()];
		}
		if (useColorB) {
			b = new float[image.getWidth()][image.getHeight()];
			if (useMedia || useVariancia || useSkewness || useCurtose)
				meb = new float[image.getWidth()][image.getHeight()];
			if (useVariancia)
				vab = new float[image.getWidth()][image.getHeight()];
			if (useSkewness)
				skb = new float[image.getWidth()][image.getHeight()];
			if (useCurtose)
				csb = new float[image.getWidth()][image.getHeight()];
		}
		if (useColorY) {
			cy = new float[image.getWidth()][image.getHeight()];
			if (useMedia || useVariancia || useSkewness || useCurtose)
				mey = new float[image.getWidth()][image.getHeight()];
			if (useVariancia)
				vay = new float[image.getWidth()][image.getHeight()];
			if (useSkewness)
				sky = new float[image.getWidth()][image.getHeight()];
			if (useCurtose)
				csy = new float[image.getWidth()][image.getHeight()];
		}
		if (useColorCr) {
			cr = new float[image.getWidth()][image.getHeight()];
			if (useMedia || useVariancia || useSkewness || useCurtose)
				mecr = new float[image.getWidth()][image.getHeight()];
			if (useVariancia)
				vacr = new float[image.getWidth()][image.getHeight()];
			if (useSkewness)
				skcr = new float[image.getWidth()][image.getHeight()];
			if (useCurtose)
				cscr = new float[image.getWidth()][image.getHeight()];
		}
		if (useColorCb) {
			cb = new float[image.getWidth()][image.getHeight()];
			if (useMedia || useVariancia || useSkewness || useCurtose)
				mecb = new float[image.getWidth()][image.getHeight()];
			if (useVariancia)
				vacb = new float[image.getWidth()][image.getHeight()];
			if (useSkewness)
				skcb = new float[image.getWidth()][image.getHeight()];
			if (useCurtose)
				cscb = new float[image.getWidth()][image.getHeight()];
		}
		if (useColorH) {
			h = new float[image.getWidth()][image.getHeight()];
			if (useMedia || useVariancia || useSkewness || useCurtose)
				meh = new float[image.getWidth()][image.getHeight()];
			if (useVariancia)
				vah = new float[image.getWidth()][image.getHeight()];
			if (useSkewness)
				skh = new float[image.getWidth()][image.getHeight()];
			if (useCurtose)
				csh = new float[image.getWidth()][image.getHeight()];
		}
		if (useColorS) {
			s = new float[image.getWidth()][image.getHeight()];
			if (useMedia || useVariancia || useSkewness || useCurtose)
				mes = new float[image.getWidth()][image.getHeight()];
			if (useVariancia)
				vas = new float[image.getWidth()][image.getHeight()];
			if (useSkewness)
				sks = new float[image.getWidth()][image.getHeight()];
			if (useCurtose)
				css = new float[image.getWidth()][image.getHeight()];
		}
		if (useColorV) {
			v = new float[image.getWidth()][image.getHeight()];
			if (useMedia || useVariancia || useSkewness || useCurtose)
				mev = new float[image.getWidth()][image.getHeight()];
			if (useVariancia)
				vav = new float[image.getWidth()][image.getHeight()];
			if (useSkewness)
				skv = new float[image.getWidth()][image.getHeight()];
			if (useCurtose)
				csv = new float[image.getWidth()][image.getHeight()];
		}
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				Color c = new Color(image.getRGB(x, y));
				float[] hsv = new float[3];
				Color.RGBtoHSB(c.getRed(),c.getGreen(),c.getBlue(),hsv);
				
				//Cálculos para a componente de cor R
				if (useColorR) {
					r[x][y] = c.getRed();
					//Cálculo da Média
					if (useMedia || useVariancia || useSkewness || useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							mer[x][y] = r[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = r[x-1][y-1];  color[1] = r[x][y-1];  color[2] = r[x+1][y-1];
							color[3] = r[x-1][y];    color[4] = r[x][y];    color[5] = r[x+1][y];
							color[6] = r[x-1][y+1];  color[7] = r[x][y+1];  color[8] = r[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += color[i];
							
							mer[x][y] = soma / 9;
						}
					}
					//Cálculo da Variância
					if (useVariancia) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							var[x][y] = r[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = r[x-1][y-1];  color[1] = r[x][y-1];  color[2] = r[x+1][y-1];
							color[3] = r[x-1][y];    color[4] = r[x][y];    color[5] = r[x+1][y];
							color[6] = r[x-1][y+1];  color[7] = r[x][y+1];  color[8] = r[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += Math.pow(color[i] - mer[x][y], 2.0);
							
							var[x][y] = soma / 9;
						}
					}
					//Cálculo da Skewness
					if (useSkewness) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							skr[x][y] = r[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = r[x-1][y-1];  color[1] = r[x][y-1];  color[2] = r[x+1][y-1];
							color[3] = r[x-1][y];    color[4] = r[x][y];    color[5] = r[x+1][y];
							color[6] = r[x-1][y+1];  color[7] = r[x][y+1];  color[8] = r[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - mer[x][y], 3.0);
								soma2 += Math.pow(color[i] - mer[x][y], 2.0);
							}
							
							skr[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 1.5));
						}
					}
					//Cálculo da Curtose
					if (useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							csr[x][y] = r[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = r[x-1][y-1];  color[1] = r[x][y-1];  color[2] = r[x+1][y-1];
							color[3] = r[x-1][y];    color[4] = r[x][y];    color[5] = r[x+1][y];
							color[6] = r[x-1][y+1];  color[7] = r[x][y+1];  color[8] = r[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - mer[x][y], 4.0);
								soma2 += Math.pow(color[i] - mer[x][y], 2.0);
							}
							
							csr[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 2.0));
						}
					}
				}
				
				//Cálculos para a componente de cor G
				if (useColorG) {
					g[x][y] = c.getGreen();
					//Cálculo da Média
					if (useMedia || useVariancia || useSkewness || useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							meg[x][y] = g[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = g[x-1][y-1];  color[1] = g[x][y-1];  color[2] = g[x+1][y-1];
							color[3] = g[x-1][y];    color[4] = g[x][y];    color[5] = g[x+1][y];
							color[6] = g[x-1][y+1];  color[7] = g[x][y+1];  color[8] = g[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += color[i];
							
							meg[x][y] = soma / 9;
						}
					}
					//Cálculo da Variância
					if (useVariancia) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							vag[x][y] = g[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = g[x-1][y-1];  color[1] = g[x][y-1];  color[2] = g[x+1][y-1];
							color[3] = g[x-1][y];    color[4] = g[x][y];    color[5] = g[x+1][y];
							color[6] = g[x-1][y+1];  color[7] = g[x][y+1];  color[8] = g[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += Math.pow(color[i] - meg[x][y], 2.0);
							
							vag[x][y] = soma / 9;
						}
					}
					//Cálculo da Skewness
					if (useSkewness) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							skg[x][y] = g[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = g[x-1][y-1];  color[1] = g[x][y-1];  color[2] = g[x+1][y-1];
							color[3] = g[x-1][y];    color[4] = g[x][y];    color[5] = g[x+1][y];
							color[6] = g[x-1][y+1];  color[7] = g[x][y+1];  color[8] = g[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - meg[x][y], 3.0);
								soma2 += Math.pow(color[i] - meg[x][y], 2.0);
							}
							
							skg[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 1.5));
						}
					}
					//Cálculo da Curtose
					if (useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							csg[x][y] = g[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = g[x-1][y-1];  color[1] = g[x][y-1];  color[2] = g[x+1][y-1];
							color[3] = g[x-1][y];    color[4] = g[x][y];    color[5] = g[x+1][y];
							color[6] = g[x-1][y+1];  color[7] = g[x][y+1];  color[8] = g[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - meg[x][y], 4.0);
								soma2 += Math.pow(color[i] - meg[x][y], 2.0);
							}
							
							csg[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 2.0));
						}
					}
				}
				
				//Cálculos para a componente de cor B
				if (useColorB) {
					b[x][y] = c.getBlue();
					//Cálculo da Média
					if (useMedia || useVariancia || useSkewness || useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							meb[x][y] = b[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = b[x-1][y-1];  color[1] = b[x][y-1];  color[2] = b[x+1][y-1];
							color[3] = b[x-1][y];    color[4] = b[x][y];    color[5] = b[x+1][y];
							color[6] = b[x-1][y+1];  color[7] = b[x][y+1];  color[8] = b[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += color[i];
							
							meb[x][y] = soma / 9;
						}
					}
					//Cálculo da Variância
					if (useVariancia) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							vab[x][y] = b[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = b[x-1][y-1];  color[1] = b[x][y-1];  color[2] = b[x+1][y-1];
							color[3] = b[x-1][y];    color[4] = b[x][y];    color[5] = b[x+1][y];
							color[6] = b[x-1][y+1];  color[7] = b[x][y+1];  color[8] = b[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += Math.pow(color[i] - meb[x][y], 2.0);
							
							vab[x][y] = soma / 9;
						}
					}
					//Cálculo da Skewness
					if (useSkewness) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							skb[x][y] = b[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = b[x-1][y-1];  color[1] = b[x][y-1];  color[2] = b[x+1][y-1];
							color[3] = b[x-1][y];    color[4] = b[x][y];    color[5] = b[x+1][y];
							color[6] = b[x-1][y+1];  color[7] = b[x][y+1];  color[8] = b[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - meb[x][y], 3.0);
								soma2 += Math.pow(color[i] - meb[x][y], 2.0);
							}
							
							skb[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 1.5));
						}
					}
					//Cálculo da Curtose
					if (useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							csb[x][y] = b[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = b[x-1][y-1];  color[1] = b[x][y-1];  color[2] = b[x+1][y-1];
							color[3] = b[x-1][y];    color[4] = b[x][y];    color[5] = b[x+1][y];
							color[6] = b[x-1][y+1];  color[7] = b[x][y+1];  color[8] = b[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - meb[x][y], 4.0);
								soma2 += Math.pow(color[i] - meb[x][y], 2.0);
							}
							
							csb[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 2.0));
						}
					}
				}
				
				//Cálculos para a componente de cor Y
				if (useColorY) {
					cy[x][y] = (float) (0.257*c.getRed() + 0.504*c.getBlue() + 0.098*c.getGreen() + 16);
					//Cálculo da Média
					if (useMedia || useVariancia || useSkewness || useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							mey[x][y] = cy[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = cy[x-1][y-1];  color[1] = cy[x][y-1];  color[2] = cy[x+1][y-1];
							color[3] = cy[x-1][y];    color[4] = cy[x][y];    color[5] = cy[x+1][y];
							color[6] = cy[x-1][y+1];  color[7] = cy[x][y+1];  color[8] = cy[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += color[i];
							
							mey[x][y] = soma / 9;
						}
					}
					//Cálculo da Variância
					if (useVariancia) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							vay[x][y] = cy[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = cy[x-1][y-1];  color[1] = cy[x][y-1];  color[2] = cy[x+1][y-1];
							color[3] = cy[x-1][y];    color[4] = cy[x][y];    color[5] = cy[x+1][y];
							color[6] = cy[x-1][y+1];  color[7] = cy[x][y+1];  color[8] = cy[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += Math.pow(color[i] - mey[x][y], 2.0);
							
							vay[x][y] = soma / 9;
						}
					}
					//Cálculo da Skewness
					if (useSkewness) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							sky[x][y] = cy[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = cy[x-1][y-1];  color[1] = cy[x][y-1];  color[2] = cy[x+1][y-1];
							color[3] = cy[x-1][y];    color[4] = cy[x][y];    color[5] = cy[x+1][y];
							color[6] = cy[x-1][y+1];  color[7] = cy[x][y+1];  color[8] = cy[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - mey[x][y], 3.0);
								soma2 += Math.pow(color[i] - mey[x][y], 2.0);
							}
							
							sky[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 1.5));
						}
					}
					//Cálculo da Curtose
					if (useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							csy[x][y] = cy[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = cy[x-1][y-1];  color[1] = cy[x][y-1];  color[2] = cy[x+1][y-1];
							color[3] = cy[x-1][y];    color[4] = cy[x][y];    color[5] = cy[x+1][y];
							color[6] = cy[x-1][y+1];  color[7] = cy[x][y+1];  color[8] = cy[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - mey[x][y], 4.0);
								soma2 += Math.pow(color[i] - mey[x][y], 2.0);
							}
							
							csy[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 2.0));
						}
					}
				}
				
				//Cálculos para a componente de cor Cr
				if (useColorCr) {
					cr[x][y] = (float) (-0.148*c.getRed() - 0.291*c.getGreen() + 0.439*c.getBlue() + 128);
					//Cálculo da Média
					if (useMedia || useVariancia || useSkewness || useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							mecr[x][y] = cr[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = cr[x-1][y-1];  color[1] = cr[x][y-1];  color[2] = cr[x+1][y-1];
							color[3] = cr[x-1][y];    color[4] = cr[x][y];    color[5] = cr[x+1][y];
							color[6] = cr[x-1][y+1];  color[7] = cr[x][y+1];  color[8] = cr[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += color[i];
							
							mecr[x][y] = soma / 9;
						}
					}
					//Cálculo da Variância
					if (useVariancia) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							vacr[x][y] = cr[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = cr[x-1][y-1];  color[1] = cr[x][y-1];  color[2] = cr[x+1][y-1];
							color[3] = cr[x-1][y];    color[4] = cr[x][y];    color[5] = cr[x+1][y];
							color[6] = cr[x-1][y+1];  color[7] = cr[x][y+1];  color[8] = cr[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += Math.pow(color[i] - mecr[x][y], 2.0);
							
							vacr[x][y] = soma / 9;
						}
					}
					//Cálculo da Skewness
					if (useSkewness) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							skcr[x][y] = cr[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = cr[x-1][y-1];  color[1] = cr[x][y-1];  color[2] = cr[x+1][y-1];
							color[3] = cr[x-1][y];    color[4] = cr[x][y];    color[5] = cr[x+1][y];
							color[6] = cr[x-1][y+1];  color[7] = cr[x][y+1];  color[8] = cr[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - mecr[x][y], 3.0);
								soma2 += Math.pow(color[i] - mecr[x][y], 2.0);
							}
							
							skcr[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 1.5));
						}
					}
					//Cálculo da Curtose
					if (useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							cscr[x][y] = cr[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = cr[x-1][y-1];  color[1] = cr[x][y-1];  color[2] = cr[x+1][y-1];
							color[3] = cr[x-1][y];    color[4] = cr[x][y];    color[5] = cr[x+1][y];
							color[6] = cr[x-1][y+1];  color[7] = cr[x][y+1];  color[8] = cr[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - mecr[x][y], 4.0);
								soma2 += Math.pow(color[i] - mecr[x][y], 2.0);
							}
							
							cscr[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 2.0));
						}
					}
				}
				
				//Cálculos para a componente de cor Cb
				if (useColorCb) {
					cb[x][y] = (float) (0.439*c.getRed() - 0.368*c.getGreen() - 0.071*c.getBlue() + 128);
					//Cálculo da Média
					if (useMedia || useVariancia || useSkewness || useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							mecb[x][y] = cb[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = cb[x-1][y-1];  color[1] = cb[x][y-1];  color[2] = cb[x+1][y-1];
							color[3] = cb[x-1][y];    color[4] = cb[x][y];    color[5] = cb[x+1][y];
							color[6] = cb[x-1][y+1];  color[7] = cb[x][y+1];  color[8] = cb[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += color[i];
							
							mecb[x][y] = soma / 9;
						}
					}
					//Cálculo da Variância
					if (useVariancia) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							vacb[x][y] = cb[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = cb[x-1][y-1];  color[1] = cb[x][y-1];  color[2] = cb[x+1][y-1];
							color[3] = cb[x-1][y];    color[4] = cb[x][y];    color[5] = cb[x+1][y];
							color[6] = cb[x-1][y+1];  color[7] = cb[x][y+1];  color[8] = cb[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += Math.pow(color[i] - mecb[x][y], 2.0);
							
							vacb[x][y] = soma / 9;
						}
					}
					//Cálculo da Skewness
					if (useSkewness) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							skcb[x][y] = cb[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = cb[x-1][y-1];  color[1] = cb[x][y-1];  color[2] = cb[x+1][y-1];
							color[3] = cb[x-1][y];    color[4] = cb[x][y];    color[5] = cb[x+1][y];
							color[6] = cb[x-1][y+1];  color[7] = cb[x][y+1];  color[8] = cb[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - mecb[x][y], 3.0);
								soma2 += Math.pow(color[i] - mecb[x][y], 2.0);
							}
							
							skcb[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 1.5));
						}
					}
					//Cálculo da Curtose
					if (useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							cscb[x][y] = cb[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = cb[x-1][y-1];  color[1] = cb[x][y-1];  color[2] = cb[x+1][y-1];
							color[3] = cb[x-1][y];    color[4] = cb[x][y];    color[5] = cb[x+1][y];
							color[6] = cb[x-1][y+1];  color[7] = cb[x][y+1];  color[8] = cb[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - mecb[x][y], 4.0);
								soma2 += Math.pow(color[i] - mecb[x][y], 2.0);
							}
							
							cscb[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 2.0));
						}
					}
				}
				
				//Cálculos para a componente de cor H
				if (useColorH) {
					
					h[x][y] = hsv[0];
					//Cálculo da Média
					if (useMedia || useVariancia || useSkewness || useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							meh[x][y] = h[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = h[x-1][y-1];  color[1] = h[x][y-1];  color[2] = h[x+1][y-1];
							color[3] = h[x-1][y];    color[4] = h[x][y];    color[5] = h[x+1][y];
							color[6] = h[x-1][y+1];  color[7] = h[x][y+1];  color[8] = h[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += color[i];
							
							meh[x][y] = soma / 9;
						}
					}
					//Cálculo da Variância
					if (useVariancia) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							vah[x][y] = h[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = h[x-1][y-1];  color[1] = h[x][y-1];  color[2] = h[x+1][y-1];
							color[3] = h[x-1][y];    color[4] = h[x][y];    color[5] = h[x+1][y];
							color[6] = h[x-1][y+1];  color[7] = h[x][y+1];  color[8] = h[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += Math.pow(color[i] - meh[x][y], 2.0);
							
							vah[x][y] = soma / 9;
						}
					}
					//Cálculo da Skewness
					if (useSkewness) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							skh[x][y] = h[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = h[x-1][y-1];  color[1] = h[x][y-1];  color[2] = h[x+1][y-1];
							color[3] = h[x-1][y];    color[4] = h[x][y];    color[5] = h[x+1][y];
							color[6] = h[x-1][y+1];  color[7] = h[x][y+1];  color[8] = h[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - meh[x][y], 3.0);
								soma2 += Math.pow(color[i] - meh[x][y], 2.0);
							}
							
							skh[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 1.5));
						}
					}
					//Cálculo da Curtose
					if (useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							csh[x][y] = h[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = h[x-1][y-1];  color[1] = h[x][y-1];  color[2] = h[x+1][y-1];
							color[3] = h[x-1][y];    color[4] = h[x][y];    color[5] = h[x+1][y];
							color[6] = h[x-1][y+1];  color[7] = h[x][y+1];  color[8] = h[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - meh[x][y], 4.0);
								soma2 += Math.pow(color[i] - meh[x][y], 2.0);
							}
							
							csh[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 2.0));
						}
					}
				}
				
				//Cálculos para a componente de cor S
				if (useColorS) {
					s[x][y] = hsv[1];
					//Cálculo da Média
					if (useMedia || useVariancia || useSkewness || useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							mes[x][y] = s[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = s[x-1][y-1];  color[1] = s[x][y-1];  color[2] = s[x+1][y-1];
							color[3] = s[x-1][y];    color[4] = s[x][y];    color[5] = s[x+1][y];
							color[6] = s[x-1][y+1];  color[7] = s[x][y+1];  color[8] = s[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += color[i];
							
							mes[x][y] = soma / 9;
						}
					}
					//Cálculo da Variância
					if (useVariancia) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							vas[x][y] = s[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = s[x-1][y-1];  color[1] = s[x][y-1];  color[2] = s[x+1][y-1];
							color[3] = s[x-1][y];    color[4] = s[x][y];    color[5] = s[x+1][y];
							color[6] = s[x-1][y+1];  color[7] = s[x][y+1];  color[8] = s[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += Math.pow(color[i] - mes[x][y], 2.0);
							
							vas[x][y] = soma / 9;
						}
					}
					//Cálculo da Skewness
					if (useSkewness) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							sks[x][y] = s[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = s[x-1][y-1];  color[1] = s[x][y-1];  color[2] = s[x+1][y-1];
							color[3] = s[x-1][y];    color[4] = s[x][y];    color[5] = s[x+1][y];
							color[6] = s[x-1][y+1];  color[7] = s[x][y+1];  color[8] = s[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - mes[x][y], 3.0);
								soma2 += Math.pow(color[i] - mes[x][y], 2.0);
							}
							
							sks[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 1.5));
						}
					}
					//Cálculo da Curtose
					if (useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							css[x][y] = s[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = s[x-1][y-1];  color[1] = s[x][y-1];  color[2] = s[x+1][y-1];
							color[3] = s[x-1][y];    color[4] = s[x][y];    color[5] = s[x+1][y];
							color[6] = s[x-1][y+1];  color[7] = s[x][y+1];  color[8] = s[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - mes[x][y], 4.0);
								soma2 += Math.pow(color[i] - mes[x][y], 2.0);
							}
							
							css[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 2.0));
						}
					}
				}
				
				//Cálculos para a componente de cor V
				if (useColorV) {
					v[x][y] = hsv[2];
					//Cálculo da Média
					if (useMedia || useVariancia || useSkewness || useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							mev[x][y] = v[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = v[x-1][y-1];  color[1] = v[x][y-1];  color[2] = v[x+1][y-1];
							color[3] = v[x-1][y];    color[4] = v[x][y];    color[5] = v[x+1][y];
							color[6] = v[x-1][y+1];  color[7] = v[x][y+1];  color[8] = v[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += color[i];
							
							mev[x][y] = soma / 9;
						}
					}
					//Cálculo da Variância
					if (useVariancia) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							vav[x][y] = v[x][y];
						else {
							float[] color = new float[9];
							float soma = 0;
							
							color[0] = v[x-1][y-1];  color[1] = v[x][y-1];  color[2] = v[x+1][y-1];
							color[3] = v[x-1][y];    color[4] = v[x][y];    color[5] = v[x+1][y];
							color[6] = v[x-1][y+1];  color[7] = v[x][y+1];  color[8] = v[x+1][y+1];

							for (int i = 0; i < 9; i++)
								soma += Math.pow(color[i] - mev[x][y], 2.0);
							
							vav[x][y] = soma / 9;
						}
					}
					//Cálculo da Skewness
					if (useSkewness) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							skv[x][y] = v[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = v[x-1][y-1];  color[1] = v[x][y-1];  color[2] = v[x+1][y-1];
							color[3] = v[x-1][y];    color[4] = v[x][y];    color[5] = v[x+1][y];
							color[6] = v[x-1][y+1];  color[7] = v[x][y+1];  color[8] = v[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - mev[x][y], 3.0);
								soma2 += Math.pow(color[i] - mev[x][y], 2.0);
							}
							
							skv[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 1.5));
						}
					}
					//Cálculo da Curtose
					if (useCurtose) {
						if (x == 0 || y == 0 || x == image.getWidth()-1 || y == image.getHeight()-1)
							csv[x][y] = v[x][y];
						else {
							float[] color = new float[9];
							float soma1 = 0; float soma2 = 0;
							
							color[0] = v[x-1][y-1];  color[1] = v[x][y-1];  color[2] = v[x+1][y-1];
							color[3] = v[x-1][y];    color[4] = v[x][y];    color[5] = v[x+1][y];
							color[6] = v[x-1][y+1];  color[7] = v[x][y+1];  color[8] = v[x+1][y+1];
							
							for (int i = 0; i < 9; i++) {
								soma1 += Math.pow(color[i] - mev[x][y], 4.0);
								soma2 += Math.pow(color[i] - mev[x][y], 2.0);
							}
							
							csv[x][y] = (float) ((soma1 / 9) / Math.pow((soma2 / 9), 2.0));
						}
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
