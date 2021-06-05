/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipos;

import java.util.Scanner;

public class Tipos {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String nome = teclado.nextLine();
        byte numero = teclado.nextByte();
        short numero2 = teclado.nextShort();
        int numero3 = teclado.nextInt();
        long numero4 = teclado.nextLong();
        float real = teclado.nextFloat();
        //double realzao = teclado.next

        System.out.printf("A nota de %s é %.2f\n", nome, real);
        
        String valor = Integer.toString(numero3); // convert int to String
        
        String valor1 = "60";
        int num = Integer.parseInt(valor1); // String to int
        
        String valor2 = "70";
        float real1 = Float.parseFloat(valor2); // String to float
        
        
    
    
    }
    
}
