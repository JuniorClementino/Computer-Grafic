package swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;

import swing.JImageWindow.Tipo;

@SuppressWarnings("serial")
public class KMeansSemiSup extends JPanel {
	private JMainFrame mainFrame;
	private Menu menu;
	private BufferedImage img;
	private JPanel pnlImage;
	private JPanel pnlButtons;
	private JButton btnKMeans;
	private JList lstPontos;
	private DefaultListModel lstModelPontos;
	private double factor;
	private int count = 0;
	private int maxCount;
	private int centroides[][];
	
	public KMeansSemiSup(BufferedImage img, Menu menu, JMainFrame mainFrame) {
		this.img = img;
		this.menu = menu;
		this.mainFrame = mainFrame;
		setLayout(new BorderLayout());
		
		add(getPnlImage(), BorderLayout.NORTH);
		add(getPnlButtons(), BorderLayout.CENTER);
		
		maxCount = Conf.kmeans_classes;
		centroides = new int[maxCount][2];
	}
	
	private JPanel getPnlImage() {
		pnlImage = new JPanel();
		
		factor = 580.0f / img.getWidth();
		pnlImage.setPreferredSize(new Dimension(600, ((int) (img.getHeight() * factor)) + 20));
		
		ImageCanvas imgCanvas = new ImageCanvas(img, factor);
		MouseHandler mouseHandler = new MouseHandler();
		imgCanvas.addMouseListener(mouseHandler);
		pnlImage.add(imgCanvas);
		
		return pnlImage;
	}
	
	private JPanel getPnlButtons() {
		pnlButtons = new JPanel();
		
		pnlButtons.setPreferredSize(new Dimension(300, 200));
		pnlButtons.setLayout(new FlowLayout());
		pnlButtons.add(getBtnKMeans());
		pnlButtons.add(getLstPontos());
		
		return pnlButtons;
	}
	
	public JScrollPane getLstPontos() {
		lstModelPontos = new DefaultListModel();
		lstPontos = new JList(lstModelPontos);
		
		lstPontos.setVisibleRowCount(11);
		lstPontos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(lstPontos);
		
		return scrollPane;
	}
	
	public JButton getBtnKMeans() {
		
		btnKMeans = new JButton("K-Means");
		btnKMeans.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (count != maxCount) {
					JOptionPane.showMessageDialog(null, "Número insuficiente de centróides");
				} else {
					try {
						Conf.kmeans_centroides = new int[maxCount][2];
						for (int i = 0; i < maxCount; i++) {
							Conf.kmeans_centroides[i][0] = centroides[i][0];
							Conf.kmeans_centroides[i][1] = centroides[i][1];
						}
						BufferedImage imagem = mainFrame.getSelected().getImage();
						JImageWindow imgWindow = new JImageWindow(imagem, Tipo.KMEANS_SS, maxCount);
						imgWindow.addInternalFrameListener(new InternalFrameAdapter() {
							public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
								mainFrame.setSelected((JImageWindow) e.getSource());
								menu.alterarMenu(true);
							};
							
							public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
								JImageWindow closed = (JImageWindow) e.getSource();
								mainFrame.getDesktopPane().remove(closed);
								if (mainFrame.getDesktopPane().getComponentCount() == 0)
									menu.alterarMenu(false);
							};
						});
						
						imgWindow.setVisible(true);
						imgWindow.setLocation(
								mainFrame.getSelected().getLocation().x + 50, 
								mainFrame.getSelected().getLocation().y + 50);
						
						mainFrame.getDesktopPane().add(imgWindow);
						imgWindow.setSelected(true);
						
						JImageWindow[] clsWindow = new JImageWindow[maxCount];
						for (int i = 0; i < maxCount; i++) {
							clsWindow[i] = new JImageWindow(imagem, Tipo.CLASSE, i);
							clsWindow[i].addInternalFrameListener(new InternalFrameAdapter() {
								public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
									mainFrame.setSelected((JImageWindow) e.getSource());
									menu.alterarMenu(true);
								};
								
								public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
									JImageWindow closed = (JImageWindow) e.getSource();
									mainFrame.getDesktopPane().remove(closed);
									if (mainFrame.getDesktopPane().getComponentCount() == 0)
										menu.alterarMenu(false);
								};
							});
							
							clsWindow[i].setVisible(true);
							clsWindow[i].setLocation(
									mainFrame.getSelected().getLocation().x + 50, 
									mainFrame.getSelected().getLocation().y + 50);
							
							mainFrame.getDesktopPane().add(clsWindow[i]);
							clsWindow[i].setSelected(true);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(mainFrame, "Falha ao carregar a imagem");
						e1.printStackTrace();
					}
				}
				menu.closeKmeansSemiSup();
			}
		});
		
		return btnKMeans;
	}
	
	public class MouseHandler implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent event) {
			//System.out.println(String.format("Clicado em [%d, %d]", event.getX(), event.getY()));
			int x, y;
			
			if (count < maxCount) {
				x = (int) (event.getX() / factor);
				y = (int) (event.getY() / factor);
				
				lstModelPontos.addElement(String.format("Coordenada [%d, %d]", x, y));
				
				centroides[count][0] = x;
				centroides[count][1] = y;
				
				count++;
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
