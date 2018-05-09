package swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import interfaces.ImageInterface;
import java.io.IOException;
import pdis.PDIClasse;
import pdis.PDICorYCbCr;
import pdis.PDIInverso;
import pdis.PDIKmeans;
import pdis.PDIKmeansSemiSup;
import pdis.PDIBinariza;
import pdis.PDIFiltragem;
import pdis.PDIHsi;
import pdis.PDIMediaEscalaCinza;
import pdis.PDIOtsu;

@SuppressWarnings("serial")
public class JImageWindow extends JInternalFrame {
	enum Tipo {
		NORMAL, 
		INVERSO,MEDIACINZA, TROCACOR,HSI,OTSU, FILTRAGEM,KMEANS, KMEANS_SS, CLASSE, LIMIARIZAR
	}
	
	private ImageInterface canvas;
	
	public JImageWindow(BufferedImage img, Tipo tipo) throws IOException
	{
		setMaximizable(true);
		setClosable(true);
		setResizable(true);
		Dimension d = new Dimension(img.getWidth()+28, img.getHeight()+50);
		if (img.getWidth() > 1024)
			d.width = 1024;
		if (img.getHeight() > 700)
			d.height = 700;
		setSize(d.width, d.height);
		setLayout(new BorderLayout());
		switch (tipo) {
		case NORMAL:
			canvas = new ImageCanvas(img);
			break;
		case INVERSO:
			canvas = new PDIInverso(img);
			break;
                case MEDIACINZA:
			canvas = new PDIMediaEscalaCinza(img);
			break;
		case KMEANS:
			canvas = new PDIKmeans(img);
			break;
                case TROCACOR:
                        canvas = new PDICorYCbCr(img);
                        break;
                case HSI:
                        canvas = new PDIHsi(img);
                        break;
                case OTSU:
                        canvas = new PDIOtsu(img);
                        break;
                case FILTRAGEM:
                        canvas = new PDIFiltragem(img);
                        break;
                case LIMIARIZAR:
                        canvas = new PDIBinariza(img);
		default:
			break;
		}
		JScrollPane scroll = new JScrollPane((JComponent)canvas);

		add(scroll, BorderLayout.CENTER);

	}
	
	public JImageWindow(BufferedImage img, Tipo tipo, int n) {
		setMaximizable(true);
		setClosable(true);
		setResizable(true);
		Dimension d = new Dimension(img.getWidth()+28, img.getHeight()+50);
		if (img.getWidth() > 1024)
			d.width = 1024;
		if (img.getHeight() > 700)
			d.height = 700;
		setSize(d.width, d.height);
		setLayout(new BorderLayout());
		switch (tipo) {
		case CLASSE:
			canvas = new PDIClasse(img, n);
			break;
		case KMEANS_SS:
			canvas = new PDIKmeansSemiSup(img, n);
			break;
		default:
			break;
		}
		JScrollPane scroll = new JScrollPane((JComponent)canvas);

		add(scroll, BorderLayout.CENTER);

	}
	
	public BufferedImage getImage()
	{
		return canvas.getImage();
	}
}
