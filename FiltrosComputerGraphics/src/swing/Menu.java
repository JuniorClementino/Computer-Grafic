package swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.InternalFrameAdapter;

import swing.JImageWindow.Tipo;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {
	private JMainFrame mainFrame;
	private ButtonGroup groupClasses;
	private JRadioButtonMenuItem[] radioClasses;
	private ButtonGroup groupColorSpaces;
	private JRadioButtonMenuItem[] radioColorSpaces;
	private JCheckBoxMenuItem[] checkFeatures, checkColorSpacesRGB, checkColorSpacesYCrCb, checkColorSpacesHSV;
	private KMeansSemiSup pnlKMeans;
	
	Menu(JMainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.add(newMenu(1, "Arquivo"));
		this.add(newMenu(2, "Processamento"));
		this.add(newMenu(3, "k-Means não-supervisionado"));
	}
	
	public JMenu newMenu(int pos, String titulo) {
		
		JMenu menu = new JMenu(titulo);
		
		switch (pos) {
		case 1:
			menu.add(itemAbrir());
			menu.add(itemSalvar());
			menu.addSeparator();
			menu.add(itemFechar());
			break;
		case 2:
			menu.add(itemInverso());
                        menu.add(itemTrocaCorYCbCr());
                        menu.add(itemMediaCinza());
                        menu.add(itemHsi());
                        menu.add(itemOtsu());
                        menu.add(itemFiltragem());
                        menu.add(itemLimiarizar());
			menu.setEnabled(false);
			break;
		case 3:
			menu.add(newSubMenu(31, "Classes: 2"));
			menu.add(newSubMenu(32, "Espaço de Cores"));
			menu.add(newSubMenu(33, "Características"));
			//menu.add(itemKmeans());
			menu.add(itemKmeansSemiSup());
			menu.setEnabled(false);
			break;
		}
		
		return menu;
	}
	
	public JMenu newSubMenu(int pos, String titulo) {
		
		JMenu menu = new JMenu(titulo);
		
		switch (pos) {
		case 31:
			addRadioGroupClasses(menu, 0);
			break;
		case 32:
			menu.add(newSubMenu(321, "RGB"));
			menu.add(newSubMenu(322, "YCrCb"));
			menu.add(newSubMenu(323, "HSV"));
                        menu.add(newSubMenu(324, "HSI"));
                        menu.add(newSubMenu(325, "OTSU"));
                        menu.add(newSubMenu(325, "Filtragem"));
			//addRadioGroupColorSpaces(menu, 0);
			break;
			case 321:
				addCheckColorSpacesRGB(menu);
				break;
			case 322:
				addCheckColorSpacesYCrCb(menu);
				break;
			case 323:
				addCheckColorSpacesHSV(menu);
				break;
		case 33:
			addCheckFeatures(menu);
			break;
		}
		
		return menu;
	}
	
	//********************
	// Itens Menu Arquivo
	//********************
	
	public JMenuItem itemAbrir() {
		
		JMenuItem item = new JMenuItem("Abrir");
		
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(mainFrame) != JFileChooser.APPROVE_OPTION)
					return;
				
				try {
					BufferedImage imagem = ImageIO.read(chooser.getSelectedFile());
					JImageWindow imgWindow = new JImageWindow(imagem, Tipo.NORMAL);
					imgWindow.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
							mainFrame.setSelected((JImageWindow) e.getSource());
							alterarMenu(true);
						};
						
						public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
							JImageWindow closed = (JImageWindow) e.getSource();
							mainFrame.getDesktopPane().remove(closed);
							if (mainFrame.getDesktopPane().getComponentCount() == 0)
								alterarMenu(false);
						};
					});
					
					imgWindow.setVisible(true);
					mainFrame.getDesktopPane().add(imgWindow);
					imgWindow.setSelected(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, "Falha ao carregar a imagem");
					e1.printStackTrace();
				}
			}
		});
		
		return item;
	}
        
	public JMenuItem itemSalvar() {
		
		JMenuItem item = new JMenuItem("Salvar");
		
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = null;
				JFileChooser chooser = new JFileChooser();
				JavaFilter filter = new JavaFilter();
				chooser.setFileFilter(filter);
				if (chooser.showSaveDialog(mainFrame) != JFileChooser.APPROVE_OPTION)
					return;
				
				try {
					file = new File(chooser.getSelectedFile().getAbsolutePath() + ".png");
					ImageIO.write(mainFrame.getSelected().getImage	(), "PNG", file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		item.setEnabled(false);
		
		return item;
	}
	
	public JMenuItem itemFechar() {
		
		JMenuItem item = new JMenuItem("Fechar");
		
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		return item;
	}
	
	//***************************
	// Itens Menu Processsamento
	//***************************
	
	public JMenuItem itemMediaCinza() {
		
		JMenuItem item = new JMenuItem("Media Cinza");
		
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedImage imagem = mainFrame.getSelected().getImage();
					JImageWindow imgWindow = new JImageWindow(imagem, Tipo.MEDIACINZA);
					imgWindow.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
							mainFrame.setSelected((JImageWindow) e.getSource());
							alterarMenu(true);
						};
						
						public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
							JImageWindow closed = (JImageWindow) e.getSource();
							mainFrame.getDesktopPane().remove(closed);
							if (mainFrame.getDesktopPane().getComponentCount() == 0)
								alterarMenu(false);
						};
					});
					
					imgWindow.setVisible(true);
					imgWindow.setLocation(
							mainFrame.getSelected().getLocation().x + 50, 
							mainFrame.getSelected().getLocation().y + 50);
					
					mainFrame.getDesktopPane().add(imgWindow);
					imgWindow.setSelected(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, "Falha ao carregar a imagem");
					e1.printStackTrace();
				}
			}
		});
		
		return item;
	}
        
        
        public JMenuItem itemHsi(){
            JMenuItem item = new JMenuItem("HSI ");
            item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedImage imagem = mainFrame.getSelected().getImage();
					JImageWindow imgWindow = new JImageWindow(imagem, Tipo.HSI);
					imgWindow.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
							mainFrame.setSelected((JImageWindow) e.getSource());
							alterarMenu(true);
						};
						
						public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
							JImageWindow closed = (JImageWindow) e.getSource();
							mainFrame.getDesktopPane().remove(closed);
							if (mainFrame.getDesktopPane().getComponentCount() == 0)
								alterarMenu(false);
						};
					});
					
					imgWindow.setVisible(true);
					imgWindow.setLocation(
							mainFrame.getSelected().getLocation().x + 50, 
							mainFrame.getSelected().getLocation().y + 50);
					
					mainFrame.getDesktopPane().add(imgWindow);
					imgWindow.setSelected(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, "Falha ao carregar a imagem");
					e1.printStackTrace();
				}
			}
		});
		
		return item;
            
        }
        
        
        
        public JMenuItem itemOtsu(){
            JMenuItem item = new JMenuItem("OTSU");
            item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedImage imagem = mainFrame.getSelected().getImage();
					JImageWindow imgWindow = new JImageWindow(imagem, Tipo.OTSU);
					imgWindow.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
							mainFrame.setSelected((JImageWindow) e.getSource());
							alterarMenu(true);
						};
						
						public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
							JImageWindow closed = (JImageWindow) e.getSource();
							mainFrame.getDesktopPane().remove(closed);
							if (mainFrame.getDesktopPane().getComponentCount() == 0)
								alterarMenu(false);
						};
					});
					
					imgWindow.setVisible(true);
					imgWindow.setLocation(
							mainFrame.getSelected().getLocation().x + 50, 
							mainFrame.getSelected().getLocation().y + 50);
					
					mainFrame.getDesktopPane().add(imgWindow);
					imgWindow.setSelected(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, "Falha ao carregar a imagem");
					e1.printStackTrace();
				}
			}
		});
		
		return item;
            
        }
        public JMenuItem itemFiltragem(){
            JMenuItem item =new JMenuItem("Filtragem");
            item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedImage imagem = mainFrame.getSelected().getImage();
					JImageWindow imgWindow = new JImageWindow(imagem, Tipo.FILTRAGEM);
					imgWindow.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
							mainFrame.setSelected((JImageWindow) e.getSource());
							alterarMenu(true);
						};
						
						public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
							JImageWindow closed = (JImageWindow) e.getSource();
							mainFrame.getDesktopPane().remove(closed);
							if (mainFrame.getDesktopPane().getComponentCount() == 0)
								alterarMenu(false);
						};
					});
					
					imgWindow.setVisible(true);
					imgWindow.setLocation(
							mainFrame.getSelected().getLocation().x + 50, 
							mainFrame.getSelected().getLocation().y + 50);
					
					mainFrame.getDesktopPane().add(imgWindow);
					imgWindow.setSelected(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, "Falha ao carregar a imagem");
					e1.printStackTrace();
				}
			}
		});
		
		return item;
            
        }
        public JMenuItem itemInverso() {
		
		JMenuItem item = new JMenuItem("Inverso ");
		
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedImage imagem = mainFrame.getSelected().getImage();
					JImageWindow imgWindow = new JImageWindow(imagem, Tipo.INVERSO);
					imgWindow.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
							mainFrame.setSelected((JImageWindow) e.getSource());
							alterarMenu(true);
						};
						
						public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
							JImageWindow closed = (JImageWindow) e.getSource();
							mainFrame.getDesktopPane().remove(closed);
							if (mainFrame.getDesktopPane().getComponentCount() == 0)
								alterarMenu(false);
						};
					});
					
					imgWindow.setVisible(true);
					imgWindow.setLocation(
							mainFrame.getSelected().getLocation().x + 50, 
							mainFrame.getSelected().getLocation().y + 50);
					
					mainFrame.getDesktopPane().add(imgWindow);
					imgWindow.setSelected(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, "Falha ao carregar a imagem");
					e1.printStackTrace();
				}
			}
		});
		
		return item;
	}
        
        public JMenuItem itemTrocaCorYCbCr() {
		
		JMenuItem item = new JMenuItem("Item YCBCR");
		
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedImage imagem = mainFrame.getSelected().getImage();
					JImageWindow imgWindow = new JImageWindow(imagem, Tipo.TROCACOR);
					imgWindow.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
							mainFrame.setSelected((JImageWindow) e.getSource());
							alterarMenu(true);
						};
						
						public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
							JImageWindow closed = (JImageWindow) e.getSource();
							mainFrame.getDesktopPane().remove(closed);
							if (mainFrame.getDesktopPane().getComponentCount() == 0)
								alterarMenu(false);
						};
					});
					
					imgWindow.setVisible(true);
					imgWindow.setLocation(
							mainFrame.getSelected().getLocation().x + 50, 
							mainFrame.getSelected().getLocation().y + 50);
					
					mainFrame.getDesktopPane().add(imgWindow);
					imgWindow.setSelected(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, "Falha ao carregar a imagem");
					e1.printStackTrace();
				}
			}
		});
		
		return item;
	}
        
        
         public JMenuItem itemLimiarizar() {
		
		JMenuItem item = new JMenuItem("Limiarizar");
		
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedImage imagem = mainFrame.getSelected().getImage();
					JImageWindow imgWindow = new JImageWindow(imagem, Tipo.LIMIARIZAR);
					imgWindow.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
							mainFrame.setSelected((JImageWindow) e.getSource());
							alterarMenu(true);
						};
						
						public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
							JImageWindow closed = (JImageWindow) e.getSource();
							mainFrame.getDesktopPane().remove(closed);
							if (mainFrame.getDesktopPane().getComponentCount() == 0)
								alterarMenu(false);
						};
					});
					
					imgWindow.setVisible(true);
					imgWindow.setLocation(
							mainFrame.getSelected().getLocation().x + 50, 
							mainFrame.getSelected().getLocation().y + 50);
					
					mainFrame.getDesktopPane().add(imgWindow);
					imgWindow.setSelected(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, "Falha ao carregar a imagem");
					e1.printStackTrace();
				}
			}
		});
		
		return item;
	}
	
	//***************************************
	// Itens Menu K-Means não-Supervisionado
	//***************************************
	
	public void addRadioGroupClasses(JMenu menu, int selected) {
		
		radioClasses = new JRadioButtonMenuItem[9];
		groupClasses = new ButtonGroup();
		RadioClassesHandler classesHandler = new RadioClassesHandler();
		
		for (int i = 0; i < 9; i++) {
			radioClasses[i] = new JRadioButtonMenuItem(""+(i+2));
			menu.add(radioClasses[i]);
			groupClasses.add(radioClasses[i]);
			radioClasses[i].addActionListener(classesHandler);
		}
		radioClasses[selected].setSelected(true);
		
	}
	
	public void addRadioGroupColorSpaces(JMenu menu, int selected) {
		
		radioColorSpaces = new JRadioButtonMenuItem[3];
		groupColorSpaces = new ButtonGroup();
		RadioColorSpacesHandler colorSpacesHandler = new RadioColorSpacesHandler();
		
		radioColorSpaces[0] = new JRadioButtonMenuItem("RGB");
		radioColorSpaces[1] = new JRadioButtonMenuItem("YCrCb");
		radioColorSpaces[2] = new JRadioButtonMenuItem("HSV");
		for (int i = 0; i < 3; i++) {
			menu.add(radioColorSpaces[i]);
			groupColorSpaces.add(radioColorSpaces[i]);
			radioColorSpaces[i].addActionListener(colorSpacesHandler);
		}
		radioColorSpaces[selected].setSelected(true);
		
	}
	
	public void addCheckFeatures(JMenu menu) {
		
		checkFeatures = new JCheckBoxMenuItem[5];
		CheckFeaturesHandler featuresHandler = new CheckFeaturesHandler();
		
		checkFeatures[0] = new JCheckBoxMenuItem("Cor");
		checkFeatures[1] = new JCheckBoxMenuItem("Média");
		checkFeatures[2] = new JCheckBoxMenuItem("Variância");
		checkFeatures[3] = new JCheckBoxMenuItem("Skewness");
		checkFeatures[4] = new JCheckBoxMenuItem("Curtose");
		for (int i = 0; i < 5; i++) {
			menu.add(checkFeatures[i]);
			checkFeatures[i].addItemListener(featuresHandler);
		}
		checkFeatures[0].setSelected(true);
	}
	
	public void addCheckColorSpacesRGB(JMenu menu) {
		
		checkColorSpacesRGB = new JCheckBoxMenuItem[3];
		CheckColorSpaceRGBHandler ColorSpaceRGBHandler = new CheckColorSpaceRGBHandler();
		
		checkColorSpacesRGB[0] = new JCheckBoxMenuItem("R");
		checkColorSpacesRGB[1] = new JCheckBoxMenuItem("G");
		checkColorSpacesRGB[2] = new JCheckBoxMenuItem("B");
		for (int i = 0; i < 3; i++) {
			menu.add(checkColorSpacesRGB[i]);
			checkColorSpacesRGB[i].addItemListener(ColorSpaceRGBHandler);
			checkColorSpacesRGB[i].setSelected(true);
		}
	}
	
	public void addCheckColorSpacesYCrCb(JMenu menu) {
		
		checkColorSpacesYCrCb = new JCheckBoxMenuItem[3];
		CheckColorSpaceYCrCbHandler ColorSpaceYCrCbHandler = new CheckColorSpaceYCrCbHandler();
		
		checkColorSpacesYCrCb[0] = new JCheckBoxMenuItem("Y");
		checkColorSpacesYCrCb[1] = new JCheckBoxMenuItem("Cr");
		checkColorSpacesYCrCb[2] = new JCheckBoxMenuItem("Cb");
		for (int i = 0; i < 3; i++) {
			menu.add(checkColorSpacesYCrCb[i]);
			checkColorSpacesYCrCb[i].addItemListener(ColorSpaceYCrCbHandler);
		}
	}

	public void addCheckColorSpacesHSV(JMenu menu) {
	
		checkColorSpacesHSV = new JCheckBoxMenuItem[3];
		CheckColorSpaceHSVHandler ColorSpaceHSVHandler = new CheckColorSpaceHSVHandler();
		
		checkColorSpacesHSV[0] = new JCheckBoxMenuItem("H");
		checkColorSpacesHSV[1] = new JCheckBoxMenuItem("S");
		checkColorSpacesHSV[2] = new JCheckBoxMenuItem("V");
		for (int i = 0; i < 3; i++) {
			menu.add(checkColorSpacesHSV[i]);
			checkColorSpacesHSV[i].addItemListener(ColorSpaceHSVHandler);
		}
	}
	
	public JMenuItem itemKmeans() {
		
		JMenuItem item = new JMenuItem("Processa K-Means");
		
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedImage imagem = mainFrame.getSelected().getImage();
					JImageWindow imgWindow = new JImageWindow(imagem, Tipo.KMEANS);
					imgWindow.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
							mainFrame.setSelected((JImageWindow) e.getSource());
							alterarMenu(true);
						};
						
						public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
							JImageWindow closed = (JImageWindow) e.getSource();
							mainFrame.getDesktopPane().remove(closed);
							if (mainFrame.getDesktopPane().getComponentCount() == 0)
								alterarMenu(false);
						};
					});
					
					imgWindow.setVisible(true);
					imgWindow.setLocation(
							mainFrame.getSelected().getLocation().x + 50, 
							mainFrame.getSelected().getLocation().y + 50);
					
					mainFrame.getDesktopPane().add(imgWindow);
					imgWindow.setSelected(true);
					
					JImageWindow[] clsWindow = new JImageWindow[Conf.kmeans_classes];
					for (int i = 0; i < Conf.kmeans_classes; i++) {
						clsWindow[i] = new JImageWindow(imagem, Tipo.CLASSE, i);
						clsWindow[i].addInternalFrameListener(new InternalFrameAdapter() {
							public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
								mainFrame.setSelected((JImageWindow) e.getSource());
								alterarMenu(true);
							};
							
							public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
								JImageWindow closed = (JImageWindow) e.getSource();
								mainFrame.getDesktopPane().remove(closed);
								if (mainFrame.getDesktopPane().getComponentCount() == 0)
									alterarMenu(false);
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
		});
		
		return item;
	}
	
	public JMenuItem itemKmeansSemiSup() {
		JMenuItem item = new JMenuItem("K-Means Semi-Supervisionado");
		
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//new Dialog(mainFrame,"Hello",true);
				if (pnlKMeans != null)
					return;
				
				pnlKMeans = new KMeansSemiSup(mainFrame.getSelected().getImage(), Menu.this, mainFrame);
				
				mainFrame.add(pnlKMeans, BorderLayout.EAST);
				mainFrame.validate();
			}
		});
		
		return item;
	}
	
	public void closeKmeansSemiSup() {
		mainFrame.remove(pnlKMeans);
		pnlKMeans = null;
		mainFrame.validate();
	}
	
	public void alterarMenu(boolean completo) {
		if (completo) {
			for (int i=1; i<Menu.this.getMenuCount(); i++)
				Menu.this.getMenu(i).setEnabled(true);
			Menu.this.getMenu(0).getItem(1).setEnabled(true);
		} else {
			for (int i=1; i<Menu.this.getMenuCount(); i++)
				Menu.this.getMenu(i).setEnabled(false);
			Menu.this.getMenu(0).getItem(1).setEnabled(false);
		}
	}
	
	private class RadioClassesHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			for (int i = 0; i < 9; i++) {
				if (radioClasses[i].isSelected()) {
					Conf.kmeans_classes = i + 2;
					Menu.this.getMenu(2).getItem(0).setText("Classes: " + (i+2));
				}
			}
		}
	}
	
	private class RadioColorSpacesHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String text;
			
			for (int i = 0; i < 3; i++) {
				if (radioColorSpaces[i].isSelected()) {
					Conf.color_spaces = i;
					switch (Conf.color_spaces) {
					case 0:
						text = "RGB";
						break;
					case 1:
						text = "YCrCb";
						break;
					case 2:
						text = "HSV";
						break;
					default:
						text = "";	
					}
					Menu.this.getMenu(2).getItem(1).setText("Espaço de Cores: " + text);
				}
			}
			
		}
		
	}
	
	private class CheckFeaturesHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
			for (int i = 0; i < 5; i++) {
				if (event.getSource() == checkFeatures[i]) {
					switch (i) {
					case 0:
						Conf.feature_color = !Conf.feature_color;
						System.out.println("Color: " + Conf.feature_color);
						break;
					case 1:
						Conf.feature_media = !Conf.feature_media;
						System.out.println("Media: " + Conf.feature_media);
						break;
					case 2:
						Conf.feature_variancia = !Conf.feature_variancia;
						System.out.println("Variancia: " + Conf.feature_variancia);
						break;
					case 3:
						Conf.feature_skewness = !Conf.feature_skewness;
						System.out.println("Skewness: " + Conf.feature_skewness);
						break;
					case 4:
						Conf.feature_curtose = !Conf.feature_curtose;
						System.out.println("Curtose: " + Conf.feature_curtose);
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	private class CheckColorSpaceRGBHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
			for (int i = 0; i < 3; i++) {
				if (event.getSource() == checkColorSpacesRGB[i]) {
					switch (i) {
					case 0:
						Conf.feature_color_R = !Conf.feature_color_R;
						break;
					case 1:
						Conf.feature_color_G = !Conf.feature_color_G;
						break;
					case 2:
						Conf.feature_color_B = !Conf.feature_color_B;
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	private class CheckColorSpaceYCrCbHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
			for (int i = 0; i < 3; i++) {
				if (event.getSource() == checkColorSpacesYCrCb[i]) {
					switch (i) {
					case 0:
						Conf.feature_color_Y = !Conf.feature_color_Y;
						break;
					case 1:
						Conf.feature_color_Cr = !Conf.feature_color_Cr;
						break;
					case 2:
						Conf.feature_color_Cb = !Conf.feature_color_Cb;
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	private class CheckColorSpaceHSVHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
			for (int i = 0; i < 3; i++) {
				if (event.getSource() == checkColorSpacesHSV[i]) {
					switch (i) {
					case 0:
						Conf.feature_color_H = !Conf.feature_color_H;
						break;
					case 1:
						Conf.feature_color_S = !Conf.feature_color_S;
						break;
					case 2:
						Conf.feature_color_V = !Conf.feature_color_V;
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	public class JavaFilter extends javax.swing.filechooser.FileFilter
	{
		public boolean accept (File f) {
			return f.getName ().toLowerCase ().endsWith (".png")
	          || f.isDirectory ();
		}
	 
		public String getDescription () {
			return "PNG Files (*.png)";
		}
	}
}