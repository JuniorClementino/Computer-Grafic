/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrizcompgra;

import java.util.Scanner;

/**
 *
 * @author a120077
 */
public class MatrizCompGra {
    

    public static void main (String args[]){
        
                double matrizPadrao [][] = new double[3][3];
                matrizPadrao [0][0]= 0.2468;
                matrizPadrao[0][1] = 0.5041;
                matrizPadrao[0][2] = 0.0979;
                matrizPadrao[1][0] = -0.1482;
                matrizPadrao[1][1] = -0.2910;
                matrizPadrao[1][2] = 0.4392;
                matrizPadrao[2][0] = 0.4392;
                matrizPadrao[2][1] = -0.3678;
                matrizPadrao[2][2] = -0.0714;
                double r,g,b;
                double y,cb, cr;
                int linha, coluna;
                linha=0;
                coluna=0;
                
     
		Scanner valoresRgb = new Scanner (System.in);
                
                System.out.println("Digite o valor de R");
                r = valoresRgb.nextDouble();
                
                System.out.println("Digite o valor de G");
                g = valoresRgb.nextDouble();
                
                System.out.println("Digite o valor de B");
                b = valoresRgb.nextDouble();
                
                y = matrizPadrao[linha][coluna]*r;
                coluna++;
                y= matrizPadrao[linha][coluna]*g;
                coluna++;
                y= matrizPadrao[linha][coluna]*b;
                y+=16;
                coluna=0;
                
                cb = matrizPadrao[linha][coluna]*r;
                coluna++;
                cb= matrizPadrao[linha][coluna]*g;
                coluna++;
                cb= matrizPadrao[linha][coluna]*b;
                cb+=128;
                coluna=0;
                
                cr = matrizPadrao[linha][coluna]*r;
                coluna++;
                cr= matrizPadrao[linha][coluna]*g;
                coluna++;
                cr= matrizPadrao[linha][coluna]*b;
                cr+=128;
                
                
                 System.out.println("Valores de Y - Cb - Cr");
             
             System.out.println("| "+y+"  |");
             System.out.println("| "+cb+" |");
             System.out.println("| "+cr+" |");
                
              
    
}}
