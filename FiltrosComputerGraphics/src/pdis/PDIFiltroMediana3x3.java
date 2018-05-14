package pdis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import interfaces.ImageInterface;

@SuppressWarnings("serial")
public class PDIFiltroMediana3x3 extends JComponent implements ImageInterface {

    private BufferedImage img;
    int media;

    public int mediana(int[] array) {
        for (int i = 1; i < array.length; i++) {

            for (int j = 0; j < array.length - 1; j++) {

                if (array[j] > array[j + 1]) {
                    int aux = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = aux;
                }
            }

        }
        if (array.length % 2 == 0) {
            int valor = (array[array.length / 2] + array[array.length / 2 + 1]) / 2;
            return valor;
        }
        return array[((int) (array.length / 2)) + 1];

    }

    public PDIFiltroMediana3x3(BufferedImage img) {
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        int largura = img.getWidth();
        int altura = img.getHeight();
        
        int[][] matrizImgFinal = new int[largura][altura];
        int[] a3x3 = new int[9];

        for (int i = 0; i < largura - 1; i++) {
            for (int j = 0; j < altura - 1; j++) {
                Color cor = new Color(img.getRGB(i, j));
                int resultado = (cor.getBlue() + cor.getRed() + cor.getGreen()) / 3;
                matrizImgFinal[i][j] = resultado;
            }
        }

        for (int y = 0; y < largura - 1; y++) {
            for (int x = 0; x < altura - 1; x++) {
                try {

                    a3x3[0] = matrizImgFinal[y][x];
                    a3x3[1] = matrizImgFinal[y + 1][x];
                    a3x3[2] = matrizImgFinal[y - 1][x];

                    a3x3[3] = matrizImgFinal[y][x + 1];
                    a3x3[4] = matrizImgFinal[y - 1][x + 1];
                    a3x3[5] = matrizImgFinal[y + 1][x + 1];

                    a3x3[6] = matrizImgFinal[y][x - 1];
                    a3x3[7] = matrizImgFinal[y - 1][x - 1];
                    a3x3[8] = matrizImgFinal[y + 1][x - 1];
                    int mediana = mediana(a3x3);
                    Color corNova = new Color(mediana, mediana, mediana);
                    out.setRGB(x, y, corNova.getRGB());
                } catch (ArrayIndexOutOfBoundsException e) {
                    if (x == 0) {
                        if (y == 0) {
                            int[] auxA = new int[4];
                            auxA[0] = matrizImgFinal[y][x];
                            auxA[1] = matrizImgFinal[y + 1][x];
                            auxA[2] = Integer.max(y + 1, x + 1);
                            auxA[3] = Integer.max(y, x + 1);
                            int mediana = mediana(a3x3);
                            Color corNova = new Color(mediana, mediana, mediana);
                            out.setRGB(y, x, corNova.getRGB());

                        }
                        if (y == largura - 1) {
                            int[] auxA = new int[4];
                            auxA[0] = matrizImgFinal[y][x];
                            auxA[1] = matrizImgFinal[y - 1][x];
                            auxA[2] = Integer.max(y - 1, x + 1);
                            auxA[3] = Integer.max(y, x + 1);
                            int mediana = mediana(a3x3);
                            Color corNova = new Color(mediana, mediana, mediana);
                            out.setRGB(y, x, corNova.getRGB());

                        }
                        if(x>0&&y< largura -1){
                            int[] auxA = new int[6];
                            auxA[0] = matrizImgFinal[y][x];
                            auxA[1] = matrizImgFinal[y - 1][x + 1];
                            auxA[2] = matrizImgFinal[y + 1][x + 1];
                            auxA[3] = matrizImgFinal[y + 1][x];
                            auxA[4] = matrizImgFinal[y - 1][x + 1];
                            auxA[5] = matrizImgFinal[y - 1][x];
                            int mediana = mediana(a3x3);
                            Color corNova = new Color(mediana, mediana, mediana);
                            out.setRGB(y, x, corNova.getRGB());
                        }
                    }

                   if (x == altura - 1) {
                        if (y == 0) {
                            int[] auxA = new int[4];
                            auxA[0] = matrizImgFinal[y][x];
                            auxA[1] = matrizImgFinal[y][x - 1];
                            auxA[2] = matrizImgFinal[y + 1][x - 1];
                            auxA[3] = matrizImgFinal[y + 1][x];
                            int mediana = mediana(a3x3);
                            Color corNova = new Color(mediana, mediana, mediana);
                            out.setRGB(y, x, corNova.getRGB());
                        }
                        if (y == largura - 1) {
                            int[] auxA = new int[4];
                            auxA[0] = matrizImgFinal[y][x];
                            auxA[1] = matrizImgFinal[y][x - 1];
                            auxA[2] = matrizImgFinal[y - 1][x - 1];
                            auxA[3] = matrizImgFinal[y - 1][x];
                             int mediana = mediana(a3x3);
                            Color corNova = new Color(mediana, mediana, mediana);
                            out.setRGB(y, x, corNova.getRGB());
                        }
                        if (y > altura - 1 && x < largura - 1) {
                            int[] auxA = new int[6];
                            auxA[0] = matrizImgFinal[y][x];
                            auxA[1] = matrizImgFinal[y - 1][x - 1];
                            auxA[2] = matrizImgFinal[y + 1][x - 1];
                            auxA[3] = matrizImgFinal[y + 1][x];
                            auxA[4] = matrizImgFinal[y - 1][x - 1];
                            auxA[5] = matrizImgFinal[y - 1][x];
                            int mediana = mediana(a3x3);
                            Color corNova = new Color(mediana, mediana, mediana);
                            out.setRGB(y, x, corNova.getRGB());
                        }

                    }

                }

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
